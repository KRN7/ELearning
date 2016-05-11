/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dao;

import br.com.elearning.praticas.interfaces.IUsuarioDao;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RicksonEllen
 */
public class DaoUsuario extends DaoGeneric implements IUsuarioDao {

    @Override
    public void salvarUsuario(Usuario u) throws Exception {

        String sql = "insert into usuario (nome, nick, senha, email, tipo) values (?, ?, ?, ?, ?);";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getNick());
            pst.setString(3, u.getSenha());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getTipo());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_USER));
        }

    }

    @Override
    public void removerUsuario(Usuario u) throws Exception {
        String sql = "delete from usuario u where u.id = ?";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setInt(1, (int) u.getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_DELETE));
        }
    }

    @Override
    public boolean verificarUsuario(String nome) throws Exception {
        String sql = "select * from usuario";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("nick").equalsIgnoreCase(nome) && rs.getString("senha").equalsIgnoreCase(nome)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_USER));
        }
        return false;
    }

    @Override
    public Usuario buscarUsuario(String nick, String senha) throws Exception {
        String sql = "select * from usuario";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("nick").equals(nick) && rs.getString("senha").equals(senha)) {
                    Usuario user = new Usuario();
                    user.setId(rs.getInt("id"));
                    user.setNome(rs.getString("nome"));
                    user.setNick(rs.getString("nick"));
                    user.setSenha(rs.getString("senha"));
                    user.setEmail(rs.getString("email"));
                    user.setTipo(rs.getString("tipo"));
                    this.fecharConexao();
                    return user;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SEARCH_USER));
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuario() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "select * from usuario";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setNick(rs.getString("nick"));
                user.setSenha(rs.getString("senha"));
                user.setEmail(rs.getString("email"));
                user.setTipo(rs.getString("tipo"));
                usuarios.add(user);
            }
            this.fecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_LIST_USER));
        }
        return usuarios;
    }

    @Override
    public void editarUsuario(Usuario u) {
        String sql = "UPDATE usuario SET nome = ?, nick =?, senha=? where id = ?";

        try {
            PreparedStatement pst = this.getConexao().prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getNick());
            pst.setString(3, u.getSenha());
            pst.setInt(4, (int) u.getId());
            pst.executeUpdate();
            this.getConexao().commit();
            this.fecharConexao();
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, e);
            throw new RuntimeException(PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
        }
    }

}
