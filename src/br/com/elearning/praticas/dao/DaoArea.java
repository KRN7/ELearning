/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.util.Conexao;
import br.com.elearning.praticas.interfaces.IAreaDao;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Felipe
 */
public class DaoArea extends DaoGeneric implements IAreaDao {

    @Override
    public void salvarArea(Area a) throws Exception {
        try {
            String sql = "insert into area (nome) values (?)";
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setString(1, a.getAreaNome());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (PSQLException e) {
            throw new SQLException(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_AREA));
        }

//        catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_AREA));
//        }
    }

    @Override
    public boolean verificarArea(String areaNome) throws Exception {
        Area a = null;
        String sql = "select * from area";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("nome").equalsIgnoreCase(areaNome)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_AREA));
        }
        return false;
    }

    @Override
    public Area buscarArea(String areaNome) throws Exception {
        Area a = null;
        String sql = "select * from area";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("nome").equalsIgnoreCase(areaNome)) {
                    a = new Area();
                    a.setId(rs.getInt("id"));
                    a.setAreaNome(rs.getString("nome"));
                    this.fecharConexao();
                    return a;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_AREA));
        }
        return null;
    }

    @Override
    public Area buscarArea(int id) throws Exception {
        String sql = "select * from area";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    Area a = new Area();
                    a.setId(rs.getInt("id"));
                    a.setAreaNome(rs.getString("nome"));
                    this.fecharConexao();
                    return a;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_AREA));
        }
        return null;
    }

    @Override
    public List<Area> listarArea() throws Exception {
        List<Area> areas = new ArrayList<>();
        String sql = "select * from area";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getLong("id"));
                area.setAreaNome(rs.getString("nome"));
                areas.add(area);
            }
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_AREA));
        }
        return areas;
    }

    @Override
    public void removerArea(Area a) throws Exception {
        String sql = "delete from area a where a.id = ?";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, (int) a.getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_DELETE));
        }
    }

    @Override
    public void editarArea(Area a) throws Exception {
        String sql = "UPDATE area SET nome = ? where id = ?";
        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setString(1, a.getAreaNome());
            pst.setInt(2, (int) a.getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }
    }

}
