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
public class ProdutoQuociente implements Comparable<Object> {

    private IProdutoOferecido produto;
    private double quociente;

    public ProdutoQuociente() {
    }

    public ProdutoQuociente(IProdutoOferecido produto, double quociente) {
        this.produto = produto;
        this.quociente = quociente;
    }

    public IProdutoOferecido getProduto() {
        return produto;
    }

    public double getQuociente() {
        return quociente;
    }

    public void setProduto(IProdutoOferecido produto) {
        this.produto = produto;
    }

    public void setQuociente(double quociente) {
        this.quociente = quociente;
    }

    @Override
    public int compareTo(Object o) {
        ProdutoQuociente pq = (ProdutoQuociente) o;
        if (quociente < pq.getQuociente()) {
            return -1;
        } else {
            if (quociente > pq.getQuociente()) {
                return 1;
            }
        }
        return 0;
    }

}
