/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.interfaces;

import br.com.elearning.praticas.model.HistoricoJogador;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Usuario;
import java.util.List;

/**
 *
 * @author Sidney
 */
public interface IHistoricoJogadorDao {

    public void salvarHistoricoJogador(HistoricoJogador h, Pergunta p, Usuario u) throws Exception;

    public HistoricoJogador buscarHistorico(long id) throws Exception;

    public void editarHistorico(Usuario u, HistoricoJogador h) throws Exception;

    public void editarHistoricoPerguntasRespondidas(Usuario u, HistoricoJogador h) throws Exception;

    public List<HistoricoJogador> listarHistoricos() throws Exception;

    public long salvarPergunta(Pergunta p, HistoricoJogador h) throws Exception;
}
