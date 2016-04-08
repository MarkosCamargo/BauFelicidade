/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.pin2.mckp.core;

import br.udesc.pin2.mckp.MCKP;
import br.udesc.pin2.mckp.modelo.IProdutoOferecido;
import br.udesc.pin2.mckp.modelo.IProdutoOferecidoDAO;
import br.udesc.pin2.mckp.modelo.ProdutoQuociente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class Greedy implements MCKP {

    private List<IProdutoOferecido> produtos;
    private IProdutoOferecidoDAO dao;

    public Greedy(IProdutoOferecidoDAO dao) {
        this.dao = dao;
        produtos = dao.getProdutos();
    }

    @Override
    public List<IProdutoOferecido> getCesta(int W) {
        HashMap<String, ArrayList<ProdutoQuociente>> map = new HashMap<>();
        for (IProdutoOferecido produto : produtos) {
            if (map.get(produto.getProduto().getDescricao()) == null) {
                map.put(produto.getProduto().getDescricao(), new ArrayList<>());
            }
            ProdutoQuociente pq = new ProdutoQuociente(produto, (produto.getSatisfacao() / produto.getValor()));
            map.get(produto.getProduto().getDescricao()).add(pq);
        }
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        produtos.removeAll(produtos);
        ArrayList<ProdutoQuociente> pqs = new ArrayList<>();
        for (String key : map.keySet()) {
            pqs.add(map.get(key).get(map.get(key).size() - 1));
        }
        Collections.sort(pqs); 
        int soma = 0;
        while (soma < W){
            for (int i = pqs.size(); i >= 0 ; i--) {
                if ((pqs.get(i).getQuociente() + soma) <= W){
                    produtos.add(pqs.get(i).getProduto());
                }
            }
        }
        return produtos;
    }

}
