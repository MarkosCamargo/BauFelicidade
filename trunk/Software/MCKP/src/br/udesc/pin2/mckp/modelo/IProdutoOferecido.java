/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.pin2.mckp.modelo;

/**
 *
 * @author ignoi
 */
public interface IProdutoOferecido {

    public IProduto getProduto();

    public String getDetalhes();

    public int getSatisfacao();

    public int getValor();

}
