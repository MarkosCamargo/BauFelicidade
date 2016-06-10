/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Produto;

/**
 *
 * @author ignoi
 */
public interface ProdutoDAO {

    public boolean inserir(Produto p);

    public boolean deletar(int id);

    public boolean atualizar(Produto p);

    public Produto pesquisar(int id);

    public List<Produto> listar();

    public int quantProd();
    
    public List<Produto> listarPorCat();

}
