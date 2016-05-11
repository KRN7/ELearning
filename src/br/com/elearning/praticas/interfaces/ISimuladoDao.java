/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.interfaces;

import br.com.elearning.praticas.model.Simulado;
import java.util.List;

/**
 *
 * @author Felipe
 */
public interface ISimuladoDao {

    public void salvar(Simulado s) throws Exception;

    public List<Simulado> listar() throws Exception;

    public Simulado buscar(int ano) throws Exception;

}
