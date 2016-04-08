/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.pin2.mckp;

import br.udesc.pin2.mckp.modelo.IProdutoOferecido;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface MCKP {

    public List<IProdutoOferecido> getCesta(int W); //W refere-se ao valor máximo da cestas

}
