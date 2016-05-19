/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.interfaces;

import br.com.elearning.praticas.model.*;
import java.util.List;

/**
 *
 * @author Felipe
 */
public interface ISimuladoDao {

    public void salvar(Simulado s) throws Exception;

    public List<Simulado> listarSimulado() throws Exception;

    public List<SimuladoUsuario> listarSimuladoUsuario() throws Exception;

    public List<SimuladoPergunta> listarSimuladoPergunta() throws Exception;

    public Simulado buscarSimulado(int ano) throws Exception;

    public Simulado buscarSimulado(long id) throws Exception;

}
