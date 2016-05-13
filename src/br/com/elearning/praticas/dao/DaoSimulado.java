/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.interfaces.ISimuladoDao;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Simulado;
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
        String sql = "";
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
    public List<Simulado> listar() throws Exception {
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
    public Simulado buscar(int ano) throws Exception {
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
    
}
