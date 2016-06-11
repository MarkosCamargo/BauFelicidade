/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.cesta.controle;


import java.util.List;

/**
 *
 * @author ignoi
 */
public class NewClass {

    public static void main(String[] args) {
        MCKP p = new Solver();
        List<Integer> lista = p.calcular(12);
        for (Integer integer : lista) {
            System.out.println(integer);
        }
    }

}
