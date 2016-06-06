/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.bau.modelo.DAO.produto;

import br.udesc.ceavi.bau.modelo.entidade.Produto;
import br.udesc.ceavi.bau.util.Conexao;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class JDBCProdutoDAO implements ProdutoDAO {

    @Override
    public boolean inserir(Produto p) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO public.\"Produto\"(\n"
                + "            \"produtoId\", descricao, valor, peso, \"categoriaId\")\n"
                + "    VALUES (?, ?, ?, ?, ?);";
        try {
            stmt = Conexao.getConexao(2).prepareStatement(sql);
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getValor());
            stmt.setDouble(4, p.getPeso());
            stmt.setDouble(5, p.getSatisfacao());
            stmt.executeUpdate();
            stmt.close();
            Conexao.fechar();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Produto inserido com sucesso!");
        return true;
    }

    @Override
    public boolean deletar(int id) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM public.\"Produto\"\n"
                + " WHERE produtoId=?\n"
                + "    VALUES (?);";;
        try {
            stmt = Conexao.getConexao(2).prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            Conexao.fechar();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Produto removido com sucesso!");
        return true;
    }

    @Override
    public boolean atualizar(Produto p) {
        PreparedStatement stmt = null;
        String sql = "UPDATE public.\"Produto\"\n"
                + "   SET \"produtoId\"=?, descricao=?, valor=?, peso=?, \"categoriaId\"=?\n"
                + " WHERE \"produtoId\"=?;\n"
                + "    VALUES (?,?,?,?,?,?);";
        try {
            stmt = Conexao.getConexao(2).prepareStatement(sql);
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getValor());
            stmt.setDouble(4, p.getPeso());
            //esqueci como determinar a categoria
//            stmt.setInt(1, p.getCategoria());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();

            stmt.close();
            Conexao.fechar();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Produto atualizado com sucesso!");
        return true;
    }

    @Override
    public Produto pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
