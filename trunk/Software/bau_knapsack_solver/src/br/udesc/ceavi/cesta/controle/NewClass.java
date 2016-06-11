/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.cesta.controle;

import br.udesc.ceavi.cesta.modelo.entidade.Cesta;
import br.udesc.ceavi.cesta.modelo.entidade.Produto;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class NewClass {
    
    public static void main(String[] args) {
        MCKP p = new Solver();
        Cesta c = p.calulcar(12);
        List<Produto> produtos = c.getProdutos();
        for (Produto produto : produtos) {
            System.out.println(produto.toString());
        }
    }
    
}
