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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;

/**
 *
 * @author Sidney
 */
public class DaoHistorico extends DaoGeneric implements IHistoricoJogadorDao {

    @Override
    public void salvarHistoricoJogador(HistoricoJogador h, Pergunta p, Usuario u) throws Exception {
        String sql = "insert into historicoJogador (qntcertas, qntrespondidas, id_usuario) values(?, ?, ?)";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setLong(1, h.getPerguntasCertas());
            pst.setLong(2, h.getPerguntasRespondidas());
            pst.setLong(3, u.getId());
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

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getLong("id_usuario") == id) {
                    HistoricoJogador hisJog = new HistoricoJogador();
                    hisJog.setId(rs.getLong("id"));
                    hisJog.setPerguntasCertas(rs.getInt("qntcertas"));
                    hisJog.setPerguntasRespondidas(rs.getInt("qntrespondidas"));
                    Usuario u = new Facade().buscarUsuarioId(rs.getLong("id_usuario"));
                    hisJog.setUsuario(u);
                    this.getConexao().commit();
                    this.fecharConexao();
                    return hisJog;
                }
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
    public void editarHistorico(Usuario u, HistoricoJogador h) throws Exception {
        String sql = "UPDATE historicojogador SET qntcertas = ?, qntrespondidas = ? where id_usuario = " + u.getId();
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, h.getPerguntasCertas());
            pst.setInt(2, h.getPerguntasRespondidas());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }
    }

    @Override
    public void editarHistoricoPerguntasRespondidas(Usuario u, HistoricoJogador h) throws Exception {
        String sql = "UPDATE historicojogador SET qntrespondidas = ? where id_usuario = " + u.getId();

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, h.getPerguntasRespondidas());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }

    }

    @Override
    public List<HistoricoJogador> listarHistoricos() throws Exception {
        List<HistoricoJogador> his = new ArrayList<>();
        String sql = "select * from historicojogador";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                HistoricoJogador h = new HistoricoJogador();
                h.setId(rs.getInt("id"));
                h.setPerguntasCertas(rs.getInt("qntcertas"));
                h.setPerguntasRespondidas(rs.getInt("qntrespondidas"));
                Usuario u = new Facade().buscarUsuarioId(rs.getInt("id_usuario"));
                h.setUsuario(u);
                his.add(h);
            }
            this.fecharConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_QUESTION));
        }
        return his;
    }

    @Override
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
