/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author ignoi
 */
public class JDBCFactory extends Persistencia {

    @Override
    public CestaDAO getCestaDAO() {
        return new JDBCCestaDAO();
    }

    @Override
    public CategoriaDAO getCategoriaDAO() {
        return new JDBCCategoriaDAO();
    }

    @Override
    public ProdutoDAO getProdutoDAO() {
        return new JDBCProdutoDAO();
    }

}
