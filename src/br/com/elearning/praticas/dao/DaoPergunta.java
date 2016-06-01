/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.dialog.DialogSimulado;
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.interfaces.IPerguntaDao;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sidney
 */
public class DaoPergunta extends DaoGeneric implements IPerguntaDao {

    @Override
    public long salvarPergunta(Pergunta p) throws Exception {
        long result = -1;
        String sql = "insert into pergunta (questao, nivel, status, id_area) values (?, ?, ?, ?)";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, p.getQuestao());
            pst.setString(2, p.getNivel());
            pst.setBoolean(3, p.getStatus());
            pst.setLong(4, p.getArea().getId());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getLong(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = -1;
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_QUESTION));
        }
        return result;
    }

    @Override
    public boolean verificarPergunta(String nome) throws Exception {
        String sql = "select * from pergunta";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("questao").equalsIgnoreCase(nome)) {
                    this.fecharConexao();
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_QUESTION));
        }
        return false;
    }

    @Override
    public Pergunta buscarPergunta(String nome) throws Exception {
        String sql = "select * from pergunta";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("questao").equalsIgnoreCase(nome)) {
                    Pergunta p = new Pergunta();
                    p.setId(rs.getInt("id"));
                    p.setQuestao(rs.getString("questao"));
                    p.setNivel(rs.getString("nivel"));
                    Area area = new Facade().buscarArea(rs.getInt("id_area"));
                    p.setArea(area);
                    p.setStatus(rs.getBoolean("status"));
                    this.fecharConexao();
                    return p;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_QUESTION));
        }
        return null;
    }

    @Override
    public Pergunta buscarPergunta(long id) throws Exception {
        String sql = "select * from pergunta";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getLong("id") == id) {
                    Pergunta p = new Pergunta();
                    p.setId(rs.getInt("id"));
                    p.setQuestao(rs.getString("questao"));
                    p.setNivel(rs.getString("nivel"));
                    Area area = new Facade().buscarArea(rs.getInt("id_area"));
                    p.setArea(area);
                    p.setStatus(rs.getBoolean("status"));
                    this.fecharConexao();
                    return p;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_QUESTION));
        }
        return null;
    }

    @Override
    public List<Pergunta> listarPergunta() throws Exception {
        List<Pergunta> perguntas = new ArrayList<>();
        String sql = "select * from pergunta";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pergunta p = new Pergunta();
                p.setId(rs.getInt("id"));
                p.setQuestao(rs.getString("questao"));
                p.setNivel(rs.getString("nivel"));
                p.setStatus(rs.getBoolean("status"));
                Area a = new Facade().buscarArea(rs.getInt("id_area"));
                p.setArea(a);
                perguntas.add(p);
            }
            this.fecharConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DaoPergunta.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_QUESTION));
        }
        return perguntas;
    }

    @Override
    public void removerPergunta(Pergunta p) throws Exception {
        String sql = "DELETE FROM pergunta p where p.id = ?";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.getLong("id") == p.getId()) {
                pst.setLong(1, p.getId());
                pst.executeUpdate();
                this.getConexao().commit();
                this.fecharConexao();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_DELETE));
        }
    }

    @Override
    public void editarPergunta(Pergunta p) throws Exception {
        String sql = "UPDATE pergunta SET questao = ?, nivel =?, status=?, id_area =? where id =" + p.getId();
        try {
            PreparedStatement pst = getConexao().prepareStatement(sql);
            pst.setString(1, p.getQuestao());
            pst.setString(2, p.getNivel());
            pst.setBoolean(3, p.getStatus());
            pst.setLong(4, p.getArea().getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new RuntimeException(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }
    }

    @Override
    public List<Pergunta> buscarPerguntasSimulado(String nivel, Area a, int quant) throws Exception {
        List<Pergunta> newPergs = new ArrayList<>();
        List<Pergunta> listaTemp = new ArrayList<>();
        List<Pergunta> pergs = listarPergunta();
        for (Pergunta p : pergs) {
            if (p.getNivel().equals(nivel) && p.getArea().getAreaNome().equals(a.getAreaNome())) {
                newPergs.add(p);
            }
        }
        Random random = new Random();
        int cont = 0;
        if (!newPergs.isEmpty()) {
            if (quant > newPergs.size()) {
                JOptionPane.showMessageDialog(null, "NAO EXISTE ESSE NUMERO DE PERGUNTNAS NESSE NIVEL E AREA!", "ATENÇAO", JOptionPane.WARNING_MESSAGE);
            }
            if (quant <= newPergs.size()) {
                do {
                    if (cont == quant) {
                        break;
                    }
                    int n = random.nextInt(newPergs.size());
                    listaTemp.add(newPergs.get(n));
                    cont++;
                    newPergs.remove(n);
                } while (true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NAO EXISTE PERGUNTA NESSE NIVEL OU AREA!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return listaTemp;
    }
}
