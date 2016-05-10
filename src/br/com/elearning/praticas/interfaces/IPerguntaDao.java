/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.interfaces;

import br.com.elearning.praticas.model.Pergunta;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IPerguntaDao {

    public void salvarPergunta(Pergunta p) throws Exception;

    public boolean verificarPergunta(String questao) throws Exception;

    public Pergunta buscarPergunta(String nome) throws Exception;

    public Pergunta buscarPergunta(long id) throws Exception;

    public List<Pergunta> listarPergunta() throws Exception;

    public void removerPergunta(Pergunta p) throws Exception;

}
