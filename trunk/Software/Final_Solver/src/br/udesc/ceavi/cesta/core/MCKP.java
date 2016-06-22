/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.cesta.core;

import java.util.List;

/**
 *
 * @author Sila Siebert
 */
public interface MCKP {

    public List<Integer> calcular(double satisfacaoMax, int cols, int rows, int[] categorias, double[] satisfacoes, int[] produtos, double[] precos);
    
}
