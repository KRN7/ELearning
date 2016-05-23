package br.com.elearning.praticas.teste;

import br.com.elearning.praticas.dao.DaoAlternativa;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.model.HistoricoJogador;
import br.com.elearning.praticas.dao.DaoHistorico;
import br.com.elearning.praticas.dao.DaoSimulado;
import br.com.elearning.praticas.util.ConnectionFactory;
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Simulado;
import br.com.elearning.praticas.model.SimuladoPergunta;
import br.com.elearning.praticas.model.SimuladoUsuario;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
public class Test {

    private static Facade facade;
    private static DaoAlternativa altDao;

    public static void main(String[] w) {

        facade = new Facade();
        altDao = new DaoAlternativa();
        
        try {
            if (ConnectionFactory.getConnection() != null) {
                System.out.println("conexao ok\n");
                facade = new Facade();
            }

            if (ConnectionFactory.getConnection() == null) {
                System.out.println("conexao falhou\n");
                facade = new Facade();
            }

//ok
            List<Simulado> s = facade.listarSimulado();
            System.out.println(s);
//ok
            List<SimuladoUsuario> su = facade.listarSimuladoUsuario();
            System.out.println(su);
//ok
            List<SimuladoPergunta> sp = facade.listarSimuladoPergunta();
            System.out.println(sp);
            
            Pergunta p = facade.buscarPergunta(4);
            
            Alternativa a = altDao.buscarAlternativa(1);
            System.out.println(p);
            System.out.println(a);
            
            
            a.setAlt1("aki");
            a.setAlt2("ali");
            a.setAlt3("la");
            a.setAlt4("ali");
            a.setAlt5("NDA");
            a.setAltCorreta("la");
            altDao.editarAlternativa(a,p);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

//        int escolha = 100;
//        Scanner in = new Scanner(System.in);
//        while (escolha != 0) {
//
//            System.out.println("0 - sair\n1 - cadastrar usuario\n2 - cadastrar area\n3 - cadastrar pergunta\n4 - remover usuario\n5 - listar usuarios\n6 - remover pergunta\n7 - listar pergunta\n8 - remover area\n9 - listar area\n10 - editar usuario\n11 - editar area\n12 - editar pergunta");
//            escolha = in.nextInt();
//            in.nextLine();
//            switch (escolha) {
//                case 1:   
//                    System.out.println("0 - sair\n1 - cadastrar usuarioADM\n2 - cadastrar usuarioJOGADOR");
//                    escolha = in.nextInt();
//                    in.nextLine();
//
//                    switch (escolha) {
//                        case 1:
//                            Usuario u = new Usuario();
//                            System.out.println("NOME: ");
//                            String nomeAdm = in.nextLine();
//                            u.setNome(nomeAdm);
//                            System.out.println("NICK: ");
//                            String nickAdm = in.nextLine();
//                            u.setNick(nickAdm);
//                            System.out.println("SENHA: ");
//                            String senhaAdm = in.nextLine();
//                            u.setSenha(senhaAdm);
//                            System.out.println("EMAIL: ");
//                            String emailAdm = in.nextLine();
//                            u.setEmail(emailAdm);
//                            u.setTipo("A");
//
//                            try {
//                                new UsuarioDao().salvarUsuario(u);
//                            } catch (Exception ex) {
//                                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            break;
//                        case 2:
//                            Usuario uJogador = new Usuario();
//                            System.out.println("NOME: ");
//                            String nomeJog = in.nextLine();
//                            uJogador.setNome(nomeJog);
//                            System.out.println("NICK: ");
//                            String nickJog = in.nextLine();
//                            uJogador.setNick(nickJog);
//                            System.out.println("SENHA: ");
//                            String senhaJog = in.nextLine();
//                            uJogador.setSenha(senhaJog);
//                            System.out.println("EMAIL: ");
//                            String emailJog = in.nextLine();
//                            uJogador.setEmail(emailJog);
//                            uJogador.setTipo("J");
//
//                            try {
//                                new UsuarioDao().salvarUsuario(uJogador);
//                            } catch (Exception ex) {
//                                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            break;
//                        case 0:
//                            break;
//
//                        default:
//                            System.out.println("OPÇAO INVALIDA");
//                            break;
//                    }
//                    break;
//                case 2:
//                    Area a = new Area();
//
//                    System.out.println("Nome Area: ");
//                    String areaNome = in.nextLine();
//                    a.setAreaNome(areaNome);
//
//                    try {
//                        new AreaDao().salvarArea(a);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    break;
//
//                case 3:
//                    Pergunta p = new Pergunta();
//                    Alternativa alt = new Alternativa();
//
//                    System.out.println("questao: ");
//                    String questao = in.nextLine();
//                    p.setQuestao(questao);
//                    System.out.println("nivel: ");
//                    String nivel = in.nextLine();
//                    p.setNivel(nivel);
//                    System.out.println("idArea: ");
//                    int idArea = in.nextInt();
//                    in.nextLine();
////                    p.getArea().setId(1);
//
//                    try {
//                        new PerguntaDao().salvarPergunta(p, alt);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    System.out.println("alt1: ");
//                    String alt1 = in.nextLine();
//                    alt.setAlt1(alt1);
//                    System.out.println("alt2: ");
//                    String alt2 = in.nextLine();
//                    alt.setAlt2(alt2);
//                    System.out.println("alt3: ");
//                    String alt3 = in.nextLine();
//                    alt.setAlt3(alt3);
//                    System.out.println("alt4: ");
//                    String alt4 = in.nextLine();
//                    alt.setAlt4(alt4);
//                    System.out.println("alt5: ");
//                    String alt5 = in.nextLine();
//                    alt.setAlt5(alt5);
//                    System.out.println("alt Correta: ");
//                    String altCorreta = in.nextLine();
//                    alt.setAltCorreta(altCorreta);
//                    System.out.println("id Pergunt: ");
//                    int idPerg = in.nextInt();
//                    in.nextLine();
////                    alt.setId_pergunta(idPerg);
//
//                    try {
//                        new AlternativaDao().salvarAlternativa(alt);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    break;
//                case 4:
//                    System.out.println("nick: ");
//                    String nick = in.nextLine();
//                    System.out.println("senha: ");
//                    String senha = in.nextLine();
//                    Usuario uB = null;
//                    try {
//                        uB = new UsuarioDao().buscarUsuario(nick, senha);
//                        System.out.println(uB);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    try {
//                        new UsuarioDao().removerUsuario(uB);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    break;
//
//                case 5:
//                    List<Usuario> usuarios = new UsuarioDao().listarUsuario();
//                    System.out.println(usuarios);
//                    break;
//
//                case 6:
//                    System.out.println("questao: ");
//                    String q = in.nextLine();
//                    Pergunta pB = null;
//
//                    try {
//                        pB = new PerguntaDao().buscarPergunta(q);
//                        System.out.println(pB.toString());
//                    } catch (Exception e) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
//                    }
//
//                    try {
//                        new PerguntaDao().removerPergunta(pB);
//                    } catch (Exception e) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                    break;
//
//                case 7:
//                    List<Pergunta> perguntas = null;
//                    try {
//                        perguntas = new PerguntaDao().listarPergunta();
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    System.out.println(perguntas);
//                    break;
//
//                case 8:
//                    System.out.println("area: ");
//                    String ar = in.nextLine();
//                    Area aB = null;
//                    try {
//                        aB = new AreaDao().buscarArea(ar);
//                        System.out.println(aB.toString());
//                    } catch (Exception e) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
//                    }
//
//                    try {
//                        new AreaDao().removerArea(aB);
//                    } catch (Exception e) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                    break;
//                case 9:
//                    List<Area> areas = null;
//                    try {
//                        areas = new AreaDao().listarArea();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(areas);
//                    break;
//                case 10:
//                    System.out.println("nick: ");
//                    String nickE = in.nextLine();
//                    System.out.println("senha: ");
//                    String senhaE = in.nextLine();
//                    Usuario user = null;
//
//                    try {
//                        user = new UsuarioDao().buscarUsuario(nickE, senhaE);
//                        System.out.println(user.toString());
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    System.out.println("novo nome: ");
//                    String nomeE2 = in.nextLine();
//                    System.out.println("novo nick: ");
//                    String nickE2 = in.nextLine();
//                    System.out.println("nova senha: ");
//                    String senhaE2 = in.nextLine();
//                    user.setNome(nomeE2);
//                    user.setNick(nickE2);
//                    user.setSenha(senhaE2);
//                    new UsuarioDao().editarUsuario(user);
//
//                    break;
//                case 11:
//                    System.out.println("nome area: ");
//                    String nomeA = in.nextLine();
//                    Area areaE = null;
//                    try {
//                        areaE = new AreaDao().buscarArea(nomeA);
//                        System.out.println(areaE.toString());
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    System.out.println("nova nome area: ");
//                    String novoNome = in.nextLine();
//                    areaE.setAreaNome(novoNome);
//
//                    new AreaDao().editarArea(areaE);
//                    break;
//                case 12:
//                    System.out.println("questao: ");
//                    String questaoE = in.nextLine();
//                    Pergunta perg = null;
//
//                    try {
//                        perg = new PerguntaDao().buscarPergunta(questaoE);
//                        System.out.println(perg.toString());
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    System.out.println("nova questao: ");
//                    String questaoEN = in.nextLine();
//                    System.out.println("novo nivel: ");
//                    String nivelEN = in.nextLine();
//                    perg.setQuestao(questaoEN);
//                    perg.setNivel(nivelEN);
//
//                    try {
//                        new PerguntaDao().editarPergunta(perg);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    break;
//
//                case 0:
//                    break;
//
//                default:
//                    System.out.println("OPÇAO INVALIDA");
//                    break;
//            }
//        }
    }

}
