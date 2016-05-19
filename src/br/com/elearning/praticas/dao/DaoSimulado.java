/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.interfaces.ISimuladoDao;
import br.com.elearning.praticas.model.*;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class DaoSimulado extends DaoGeneric implements ISimuladoDao {

    @Override
    public void salvar(Simulado s) throws Exception {
        String sql = "insert into simulado(ano) values(?)";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, s.getAno());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_QUESTION));
        }
    }

    @Override
    public List<Simulado> listarSimulado() throws Exception {
        List<Simulado> simulado = new ArrayList<>();
        String sql = "select * from simulado";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Simulado s = new Simulado();
                s.setId(rs.getInt("id"));
                s.setAno(rs.getInt("ano"));
                simulado.add(s);
            }
            this.fecharConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_QUESTION));
        }
        return simulado;
    }

    @Override
    public List<SimuladoUsuario> listarSimuladoUsuario() throws Exception {
        List<SimuladoUsuario> simulado = new ArrayList<>();
        String sql = "select s.*, u.* "
                + "from simulado s, usuario u, simuladousuario su "
                + "where su.id_simulado = s.id and su.id_usuario = u.iduser";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Simulado s = new Simulado();
                s.setId(rs.getLong("id"));
                s.setAno(rs.getInt("ano"));
                List<Usuario> users = new ArrayList<>();
                Usuario u = new Usuario();
                u.setId(rs.getLong("iduser"));
                u.setNome(rs.getString("nome"));
                u.setNick(rs.getString("nick"));
                u.setSenha(rs.getString("senha"));
                u.setEmail(rs.getString("email"));
                u.setTipo(rs.getString("tipo"));
                users.add(u);
                SimuladoUsuario su = new SimuladoUsuario();
                su.setSimulado(s);
                su.setUser(users);
                simulado.add(su);
            }
            this.fecharConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_QUESTION));
        }
        return simulado;
    }

    @Override
    public List<SimuladoPergunta> listarSimuladoPergunta() throws Exception {
        List<SimuladoPergunta> simulado = new ArrayList<>();
        String sql = "select s.*, p.id as idPergunta, p.questao, p.nivel, p.id_area  "
                + "from simulado s, pergunta p, simuladopergunta sp "
                + "where sp.id_simulado = s.id and sp.id_pergunta = p.id";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Simulado s = new Simulado();
                s.setId(rs.getLong("id"));
                s.setAno(rs.getInt("ano"));
                List<Pergunta> perguntas = new ArrayList<>();
                Pergunta p = new Pergunta();
                p.setId(rs.getLong("idPergunta"));
                p.setQuestao(rs.getString("questao"));
                p.setNivel(rs.getString("nivel"));
                perguntas.add(p);
                Area a = new Facade().buscarArea(rs.getInt("id_area"));
                p.setArea(a);
                SimuladoPergunta sp = new SimuladoPergunta();
                sp.setSimulado(s);
                sp.setPerguntas(perguntas);
                simulado.add(sp);
            }
            this.fecharConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_QUESTION));
        }
        return simulado;
    }

    @Override
    public Simulado buscarSimulado(int ano) throws Exception {
        String sql = "select * from simulado";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("ano") == ano) {
                    Simulado s = new Simulado();
                    s.setId(rs.getInt("id"));
                    s.setAno(rs.getInt("ano"));
                    this.fecharConexao();
                    return s;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_QUESTION));
        }
        return null;
    }

    @Override
    public Simulado buscarSimulado(long id) throws Exception {
        String sql = "select * from simulado";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    Simulado s = new Simulado();
                    s.setId(rs.getInt("id"));
                    s.setAno(rs.getInt("ano"));
                    this.fecharConexao();
                    return s;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_QUESTION));
        }
        return null;
    }

}
