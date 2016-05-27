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
import br.com.elearning.praticas.model.SimuladoPergunta;
import br.com.elearning.praticas.model.SimuladoUsuario;
import br.com.elearning.praticas.util.DaoFactory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author RicksonEllen
 */
public class Facade implements Serializable {

    private final IUsuarioDao daoUsuario;
    private final IPerguntaDao daoPergunta;
    private final IAreaDao daoArea;
    private final IAlternativaDao daoAlternativa;
    private final IHistoricoJogadorDao daoHistoricoJogador;
    private final ISimuladoDao daoSimulado;

    public Facade() {
        this.daoUsuario = DaoFactory.createDaoUsuario();
        this.daoPergunta = DaoFactory.createDaoPergunta();
        this.daoArea = DaoFactory.createDaoArea();
        this.daoAlternativa = DaoFactory.createDaoAlternativa();
        this.daoHistoricoJogador = DaoFactory.createDaoHistorico();
        this.daoSimulado = DaoFactory.createDaoSimulado();
    }

    //METODOS DO DAO USUARIO
    /**
     * Método salva um Usuario.
     *
     * @author RicksonEllen
     * @param usuario - Usuario a ser salvo
     * @throws Exception - Caso lance Exception
     */
    public void salvarUsuario(Usuario usuario) throws Exception {
        this.daoUsuario.salvarUsuario(usuario);
    }

    /**
     * Método para remover um Usuario
     *
     * @author RicksonEllen
     * @param u - Usuario a ser removido.
     * @throws Exception - Caso lance Exception.
     */
    public void removerUsuario(Usuario u) throws Exception {
        this.daoUsuario.removerUsuario(u);
    }

    /**
     * Método verifica se existe um Usuario com o nome passado por parametro.
     *
     * @author RicksonEllen
     * @param nome - Nome do Usuario.
     * @return Boolean - Retorna true se o usuario existir.
     * @throws Exception - Caso lance Exception.
     */
    public boolean verificarUsuario(String nome) throws Exception {
        return this.daoUsuario.verificarUsuario(nome);
    }

    /**
     * Método busca um Usuario com username e senha igual ao passado por
     * parametro.
     *
     * @author RicksonEllen
     * @param nome - Username do Usuario.
     * @param senha - Senha do Usuario.
     * @return Usuario - Retorna um Usuario, caso tenha um com o username e
     * senha iguais aos passado por parametro.
     * @throws Exception - Caso lance Exception.
     */
    public Usuario buscarUsuarioLogin(String nome, String senha) throws Exception {
        return this.daoUsuario.buscarUsuarioLogin(nome, senha);
    }

    /**
     * Método buscar um usuario que tenha a senha igual a que foi passada por
     * parametro.Método apenas usado para verificar usuario antes de editar.
     *
     * @author RicksonEllen
     * @param senha - Senha do Usuario.
     * @return Usuario - Retorna um usuario, caso ele tenha a mesma senha que
     * foi passada por parametro.
     * @throws Exception - Caso lance Exception.
     */
    public Usuario buscarUsuarioSenha(String senha) throws Exception {
        return this.daoUsuario.buscarUsuarioSenha(senha);
    }

    /**
     * Método busca usuario que tenha o email igual ao que foi passado por
     * parametro. Método usado apenas para verificar usuario antes de enviar
     * email.
     *
     * @author RicksonEllen
     * @param email - Email do Usuario.
     * @return Usuario - Retorna um usuario, caso ele tenha o mesmo email que
     * foi passado por parametro.
     * @throws Exception - Caso lance Exception.
     */
    public Usuario buscarUsuarioEmail(String email) throws Exception {
        return this.daoUsuario.buscarUsuarioEmail(email);
    }

    /**
     * Método busca usuario que tenha o mesmo id passado por parametro. Método
     * usado para buscar historico do usuario.
     *
     * @author RicksonEllen
     * @param id - Id do Usuario.
     * @return Usuario - Retorna um usuario, cado ele tenha o id igual ao
     * passado por parametro.
     * @throws Exception - Caso lance Exception.
     */
    public Usuario buscarUsuarioId(long id) throws Exception {
        return this.daoUsuario.buscarUsuario(id);
    }

    /**
     * Método lista todos os usuarios.
     *
     * @author RicksonEllen
     * @return Retorna uma lista de usuarios.
     * @throws Exception - Caso lance Exception.
     */
    public List<Usuario> listarUsuario() throws Exception {
        return this.daoUsuario.listarUsuario();
    }

    /**
     * Método edita um usuario(Todos os atributos).
     *
     * @author RicksonEllen
     * @param usuario - Usuario a ser editado.
     * @throws Exception - Caso lance Exception.
     */
    public void editarUsuario(Usuario usuario) throws Exception {
        this.daoUsuario.editarUsuario(usuario);
    }

    //METODOS DO DAO PERGUNTA
    /**
     * Método salva um pergunta
     *
     * @author Sidney
     * @param p - Pergunta a ser salva.
     * @return Long - Retorna o id da pergunta, que sera usado no metodo
     * salvarAlternativa.
     * @throws Exception - Caso lance Exception.
     */
    public long salvarPergunta(Pergunta p) throws Exception {
        return this.daoPergunta.salvarPergunta(p);
    }

    /**
     * Método verificar se existe alguma pergunta com a questao passada por
     * parametro.
     *
     * @author Sidney
     * @param questao - Questao da Pergunta.
     * @return Boolean - Retorna true caso exista uma pergunta com a mesma
     * questao(parametro).
     * @throws Exception - Caso lance Exception.
     */
    public boolean verificarPergunta(String questao) throws Exception {
        return this.daoPergunta.verificarPergunta(questao);
    }

    /**
     * Método busca uma pergunta com questao igual a passada por parametro.
     *
     * @author Sidney
     * @param questao - Questao da Pergunta.
     * @return Pergunta - Retorna uma pergunta, caso questao seja igual a
     * passada por parametro.
     * @throws Exception - Caso lance Exception.
     */
    public Pergunta buscarPergunta(String questao) throws Exception {
        return this.daoPergunta.buscarPergunta(questao);
    }

    /**
     * Método busca uma pergunta com id igual ao passado por parametro.
     *
     * @author Sidney
     * @param id - Id da Pergunta.
     * @return Pergunta - Retorna uma pergunta, caso id seja igual ao passado
     * por parametro.
     * @throws Exception - Caso lance Exception.
     */
    public Pergunta buscarPergunta(long id) throws Exception {
        return this.daoPergunta.buscarPergunta(id);
    }
//JAVADOC

    public void editarPergunta(Pergunta p) throws Exception {
        this.daoPergunta.editarPergunta(p);
    }

    /**
     * Método para lista todas as perguntas.
     *
     * @author Sidney
     * @return Retorna uma lista de Pergunta.
     * @throws Exception - Caso lance Exception.
     */
    public List<Pergunta> listarPergunta() throws Exception {
        return this.daoPergunta.listarPergunta();
    }

    /**
     * Método remove uma pergunta.
     *
     * @author Sidney
     * @param p - Pergunta a ser removida.
     * @throws Exception - Caso lance Exception
     */
    public void removerPergunta(Pergunta p) throws Exception {
        this.daoPergunta.removerPergunta(p);
    }

    //METODOS DO DAO AREA
    /**
     * Método salva uma area.
     *
     * @author Felipe
     * @param a - Area a ser salva.
     * @throws Exception - Caso lance Exception.
     */
    public void salvarArea(Area a) throws Exception {
        this.daoArea.salvarArea(a);
    }

    /**
     * Método verifica se exista alguma Area com o mesmo nome.
     *
     * @author Felipe
     * @param nome - Nome da Area.
     * @return Boolean - Retorna true, caso exista uma Area com o mesmo nome.
     * @throws Exception - Caso lance Exception.
     */
    public boolean verificarArea(String nome) throws Exception {
        return this.daoArea.verificarArea(nome);
    }

    /**
     * Método busca uma Area que tenha o id igual ao passado por parametro.
     *
     * @author Felipe
     * @param id - Id da Area.
     * @return Area - Retorna uma area.
     * @throws Exception - Caso lance Exception.
     */
    public Area buscarArea(int id) throws Exception {
        return this.daoArea.buscarArea(id);
    }

    /**
     * Método busca uma Area que tenha o nome igual ao passado por parametro.
     *
     * @author Felipe
     * @param nome - Nome da Area.
     * @return Area - Retorna uma area.
     * @throws Exception - Caso lance Exception.
     */
    public Area buscarArea(String nome) throws Exception {
        return this.daoArea.buscarArea(nome);
    }

    /**
     * Método lista todas as areas.
     *
     * @author Felipe
     * @return Retorna uma lista de Area.
     * @throws Exception - Caso lance Exception.
     */
    public List<Area> listarArea() throws Exception {
        return this.daoArea.listarArea();
    }

    /**
     * Método remove uma Area.
     *
     * @author Felipe
     * @param a - Area a ser removida.
     * @throws Exception - Caso lance Exception.
     */
    public void removerArea(Area a) throws Exception {
        this.daoArea.removerArea(a);
    }

    /**
     * Método edita uma Area
     *
     * @param a - Area a ser editada.
     * @throws Exception - Caso lance Exception.
     */
    public void editarArea(Area a) throws Exception {
        this.daoArea.editarArea(a);
    }

    //METODOS DO DAO ALTERNATIVA
    /**
     * Método salva as alternativas e chama o método salvarPergunta.
     *
     * @author Felipe
     * @param a - Alternativa a ser salva.
     * @param p - Pergunta a ser salva.
     * @throws Exception - Caso lance Exception.
     */
    public void salvarAlternativa(Alternativa a, Pergunta p) throws Exception {
        this.daoAlternativa.salvarAlternativa(a, p);
    }

    /**
     * Método edita um Pergunta
     *
     * @param a
     * @param p
     * @throws Exception
     */
    public void editarAlternativa(Alternativa a, Pergunta p) throws Exception {
        this.daoAlternativa.editarAlternativa(a, p);
    }

    /**
     * Método lista todas as alternativas.
     *
     * @author Felipe
     * @return Retorna a lista de Alternativa
     * @throws Exception - Caso lance Exception.
     */
    public List<Alternativa> listar() throws Exception {
        return this.daoAlternativa.listar();
    }

    /**
     *
     * @return
     */
    public Alternativa buscarAlternativa(long id) throws Exception {
        return this.daoAlternativa.buscarAlternativa(id);
    }

    //METODOS DO DAO HISTORICOJOGADOR
    /**
     * Método salva o HistoricoJogador do Usuario
     *
     * @author Sidney
     * @param h - HistoricoJogador a ser salvo.
     * @param p - Pergunta a ser salva.(Apenas o id)
     * @param u - Usuario a ser salvo.(Apenas o id)
     * @throws Exception - Caso lance Exception.
     */
    public void salvarHistorico(HistoricoJogador h, Pergunta p, Usuario u) throws Exception {
        this.daoHistoricoJogador.salvarHistoricoJogador(h, p, u);
    }

    //METODOS DO DAO SIMULADO
    /**
     * Método salva um Simulado.
     *
     * @author Felipe
     * @param s - Simulado a ser salvo.
     * @throws Exception - Caso lance Exception.
     */
    public void salvarSimulado(Simulado s) throws Exception {
        this.daoSimulado.salvar(s);
    }

    /**
     * Método busca um Simulado que tenha o ano igual ao passado por parametro.
     *
     * @author Felipe
     * @param ano - Ano do Simulado.
     * @return Simulado - Retorna um Simulado que tenha o mesmo ano passado por
     * parametro..
     * @throws Exception - Caso lance Exception.
     */
    public Simulado buscar(int ano) throws Exception {
        return this.daoSimulado.buscarSimulado(ano);
    }

    /**
     * Método lista todos os Simulado e usuarios que participaram do Simulado.
     *
     * @author Felipe
     * @return Retorna lista de Simulado e Usuario.
     * @throws Exception - Caso lance Exception.
     */
    public List<SimuladoUsuario> listarSimuladoUsuario() throws Exception {
        return this.daoSimulado.listarSimuladoUsuario();
    }

    /**
     * Método lista Simulado.
     *
     * @author Felipe
     * @return Retorna lista de Simulado.
     * @throws Exception - Caso lance Exception.
     */
    public List<Simulado> listarSimulado() throws Exception {
        return this.daoSimulado.listarSimulado();
    }

    /**
     * Método lista todos os Simulados e as perguntas que ele possui.
     *
     * @author Felipe
     * @return Retorna lista de Simulado e Pergunta.
     * @throws Exception - Caso lance Exception.
     */
    public List<SimuladoPergunta> listarSimuladoPergunta() throws Exception {
        return this.daoSimulado.listarSimuladoPergunta();
    }

}
