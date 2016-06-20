
import br.udesc.ceavi.cesta.core.Factory;
import br.udesc.ceavi.cesta.core.MCKP;
import br.udesc.ceavi.cesta.modelo.dao.core.Persistencia;
import br.udesc.ceavi.cesta.modelo.entidade.Produto;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sila
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Factory f = new Factory();
        MCKP m = f.getMCKPSolver();
        int quantidadeColunas = Persistencia.getPersistencia(1).getProdutoDAO().getQuantidade();

        int quantidadeLinhas = Persistencia.getPersistencia(1).getCategoriaDAO().getQuantidade();

        List<Produto> lista = Persistencia.getPersistencia(1).getProdutoDAO().listarPorCategoria();

        int[] categorias = new int[quantidadeColunas];
        int[] produtos = new int[quantidadeColunas];
        double[] satisfacoes = new double[quantidadeColunas];
        double[] precos = new double[quantidadeColunas];
        int ind = 0;
        for (Produto p : lista) {
            produtos[ind] = p.getId();
            precos[ind] = p.getValor();
            satisfacoes[ind] = p.getSatisfacao();
            categorias[ind] = p.getCategoria().getId();
            ind++;

        }
        m.calcular(12, quantidadeColunas, quantidadeLinhas, categorias, satisfacoes, produtos, precos);

    }

}
