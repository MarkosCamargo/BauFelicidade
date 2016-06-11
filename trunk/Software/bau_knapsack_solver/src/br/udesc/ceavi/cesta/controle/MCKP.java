/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.cesta.controle;

import br.udesc.ceavi.cesta.modelo.entidade.Cesta;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface MCKP {
    
    /**
     * Utilize este método para calcular uma solução ótima para o problema da
     * mochila de múltipla escolha (MCKP).
     * 
     * Este é uma variação do problema da mochila tradicional, onde só pode ser
     * escolhido um produto de cada categoria.
     * @param w (weight) é a restrição da mochila, que neste caso é o valor
     * máximo para uma determinada cesta.
     * @return Um objeto cesta contendo todos os produtos escolhidos.
     */
    public List<Integer> calcular(double w);
    
}
