/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.interfaces.IHistoricoJogadorDao;
import br.com.elearning.praticas.model.*;
import br.com.elearning.praticas.util.*;
import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author Sidney
 */
public class DaoHistorico extends DaoGeneric implements IHistoricoJogadorDao {

//    private Facade facade = new Facade();

    @Override
    public void salvarHistoricoJogador(HistoricoJogador h, Pergunta p, Usuario u) throws Exception {
        String sql = "insert into historicoJogador (qntcertas, qntrespondidas, id_usuario, id_pergunta) values(?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setLong(1, h.getPerguntasCertas());
            pst.setLong(2, h.getPerguntasRespondidas());
            pst.setLong(3, u.getId());
            pst.setLong(4, p.getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_HISTORICO));
        }
    }

    @Override
    public HistoricoJogador buscarHistorico(long id) throws Exception {
        String sql = "select * from historicoJogador";
//        Usuario user = facade.buscarUsuarioID(id);
//        Pergunta p = facade.buscarPergunta(id);

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.getLong("id") == id) {
                HistoricoJogador hisJog = new HistoricoJogador();
                hisJog.setId(rs.getLong("id"));
                hisJog.setPerguntasCertas(rs.getInt("perguntascertas"));
                hisJog.setPerguntasRespondidas(rs.getInt("perguntasrespondidas"));
                this.getConexao().commit();
                this.fecharConexao();
                return hisJog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_HISTORICO));
        }
        return null;
    }

    @Override
    public void editarHistorico(Usuario u, Pergunta p, HistoricoJogador h) throws Exception {
        String sql = "UPDATE historicojogador SET perguntascertas = ?, perguntasrespondidas = ? where id_pergunta = " + p.getId() + "";

        long id_pergunta = salvarPergunta(p, h);

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, h.getPerguntasCertas() + 1);
            pst.setInt(2, h.getPerguntasRespondidas() + 1);
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }
    }

    @Override
    public void editarHistoricoPerguntasRespondidas(Usuario u, Pergunta p, HistoricoJogador h) throws Exception {//NOME DO METODO & PARAMETROS ERRADOS
        String sql = "UPDATE historicojogador SET perguntasrespondidas = ? where id_pergunta = " + p.getId(); //INCOMPLETA

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, h.getPerguntasRespondidas() + 1);
            pst.setLong(2, h.getId());
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }

    }

    public long salvarPergunta(Pergunta p, HistoricoJogador h) throws Exception {
        String sql = "insert into perguntahistorico (id_historico, id_historico) values (?, ?);";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setLong(1, p.getId());
            pst.setLong(2, h.getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_QUESTION));
        }
        return p.getId();
    }

}
