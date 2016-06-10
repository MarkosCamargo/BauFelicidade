/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cesta;
import java.util.List;

/**
 *
 * @author ignoi
 */
public interface CestaDAO {

    public boolean inserir(Cesta c);

    public boolean deletar(Cesta c);

    public boolean atualizar(Cesta c);

    public Cesta pesquisar(int id);

    public List<Cesta> listar();
    
}
