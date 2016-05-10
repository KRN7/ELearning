/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.util.PropertiesUtils;
import br.com.elearning.praticas.model.HistoricoJogador;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.interfaces.IHistoricoJogadorDao;
import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author Sidney
 */
public class DaoHistorico extends DaoGeneric implements IHistoricoJogadorDao {

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
        String sql = "select * from historicoJogador where id = ?";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            HistoricoJogador hisJog = new HistoricoJogador();
            hisJog.setPerguntasCertas(rs.getInt("perguntascertas"));
            hisJog.setPerguntasRespondidas(rs.getInt("perguntasrespondidas"));
            //INCOMPLETO
            return hisJog;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_HISTORICO));
        }

    }

    @Override
    public void editarHistorico(Usuario u, Pergunta p, HistoricoJogador h) throws Exception {
        String sql = "UPDATE historicojogador SET perguntascertas = ?, perguntasrespondidas = ?"; //INCOMPLETA

        long id_pergunta = salvarPergunta(p);

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, h.getPerguntasCertas() + 1);
            pst.setInt(2, h.getPerguntasRespondidas() + 1);
            //MUITO INCOMPLETO
        } catch (SQLException e) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }
    }

    @Override
    public void editarHistorico2(Usuario u, Pergunta p, HistoricoJogador h) throws Exception {//NOME DO METODO & PARAMETROS ERRADOS
        String sql = "UPDATE historicojogador SET perguntasrespondidas = ?"; //INCOMPLETA

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, h.getPerguntasRespondidas() + 1);
            //MUITO INCOMPLETO
        } catch (SQLException e) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }

    }

    @Override
    public long salvarPergunta(Pergunta p) throws Exception {
        String sql = "insert into perguntahistorico (questao, nivel, id_area) values (?, ?, ?);";//ERRADO
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setString(1, p.getQuestao());
            pst.setString(2, p.getNivel());
            pst.setInt(3, (int) p.getArea().getId());
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
