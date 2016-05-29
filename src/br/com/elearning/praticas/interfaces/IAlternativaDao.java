/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.interfaces;

import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Pergunta;
import java.util.List;

/**
 *
 * @author Felipe
 */
public interface IAlternativaDao {

    public void salvarAlternativa(Alternativa a, Pergunta p) throws Exception;

    public List<Alternativa> listar() throws Exception;

    public Alternativa buscarAlternativa(long id) throws Exception;

    public void editarAlternativa(Alternativa a) throws Exception;

}
