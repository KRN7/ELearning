/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.facade;

import br.com.elearning.praticas.model.HistoricoJogador;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.interfaces.IHistoricoJogadorDao;
import br.com.elearning.praticas.interfaces.IUsuarioDao;
import br.com.elearning.praticas.interfaces.IPerguntaDao;
import br.com.elearning.praticas.interfaces.IAlternativaDao;
import br.com.elearning.praticas.interfaces.IAreaDao;
import br.com.elearning.praticas.interfaces.ISimuladoDao;
import br.com.elearning.praticas.model.Simulado;
import br.com.elearning.praticas.util.DaoFactory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author RicksonEllen
 */
public class Facade implements Serializable {

    private IUsuarioDao daoUsuario;
    private IPerguntaDao daoPergunta;
    private IAreaDao daoArea;
    private IAlternativaDao daoAlternativa;
    private IHistoricoJogadorDao daoHistoricoJogador;
    private ISimuladoDao daoSimulado;

    public Facade() {
        this.daoUsuario = DaoFactory.createUsuarioDao();
        this.daoPergunta = DaoFactory.createPerguntaDao();
        this.daoArea = DaoFactory.createAreaDao();
        this.daoAlternativa = DaoFactory.createAlternativaDao();
        this.daoHistoricoJogador = DaoFactory.createHistoricoDao();
        this.daoSimulado = DaoFactory.createSimuladoDao();
    }

    //METODOS DO DAO USUARIO
    public void salvarUsuario(Usuario usuario) throws Exception {
        this.daoUsuario.salvarUsuario(usuario);
    }

    public void removerUsuario(Usuario u) throws Exception {
        this.daoUsuario.removerUsuario(u);
    }

    public boolean verificarUsuario(String nome) throws Exception {
        return this.daoUsuario.verificarUsuario(nome);
    }

    public Usuario buscarUsuario(String nome, String senha) throws Exception {
        return this.daoUsuario.buscarUsuario(nome, senha);
    }

    public List<Usuario> listarUsuario() throws Exception {
        return this.daoUsuario.listarUsuario();
    }

    public void editarUsuario(Usuario usuario) {
        this.daoUsuario.editarUsuario(usuario);
    }

    //METODOS DO DAO PERGUNTA
    public void salvarPergunta(Pergunta p) throws Exception {
        this.daoPergunta.salvarPergunta(p);
    }

    public boolean verificarPergunta(String questao) throws Exception {
        return this.daoPergunta.verificarPergunta(questao);
    }

    public Pergunta buscarPergunta(String nome) throws Exception {
        return this.daoPergunta.buscarPergunta(nome);
    }

    public Pergunta buscarPergunta(long id) throws Exception {
        return this.daoPergunta.buscarPergunta(id);
    }

    public List<Pergunta> listarPergunta() throws Exception {
        return this.daoPergunta.listarPergunta();
    }

    public void removerPergunta(Pergunta p) throws Exception {
        this.daoPergunta.removerPergunta(p);
    }

    //METODOS DO DAO AREA
    public void salvarArea(Area a) throws Exception {
        this.daoArea.salvarArea(a);
    }

    public boolean verificarArea(String area) throws Exception {
        return this.daoArea.verificarArea(area);
    }

    public Area buscarArea(int id) throws Exception {
        return this.daoArea.buscarArea(id);
    }

    public List<Area> listarArea() throws Exception {
        return this.daoArea.listarArea();
    }

    public void removerArea(Area a) throws Exception {
        this.daoArea.removerArea(a);
    }

    public void editarArea(Area a) throws Exception {
        this.daoArea.editarArea(a);
    }

    //METODOS DO DAO ALTERNATIVA
    public long salvarAlternativa(Alternativa a) throws Exception {
        return this.daoAlternativa.salvarAlternativa(a);
    }

    public List<Alternativa> listar() throws Exception {
        return this.daoAlternativa.listar();
    }

    //METODOS DO DAO HISTORICOJOGADOR
    public void salvarHistorico(HistoricoJogador h, Pergunta p, Usuario u) throws Exception {
        this.daoHistoricoJogador.salvarHistoricoJogador(h, p, u);
    }

    //METODOS DO DAO SIMULADO
    public void salvarSimulado(Simulado s) throws Exception {
        this.daoSimulado.salvar(s);
    }

    public Simulado buscar(int ano) throws Exception {
        return this.daoSimulado.buscar(ano);
    }

    public List<Simulado> listarSimulado() throws Exception {
        return this.daoSimulado.listar();
    }

}
