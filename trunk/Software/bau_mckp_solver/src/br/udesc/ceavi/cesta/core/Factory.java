/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.cesta.core;

import br.udesc.ceavi.cesta.solver.Solver;

/**
 *
 * @author ignoi
 */
public class Factory {

    public static Solver getMCKPSolver() {
        return new Solver();
    }

}
