/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.interfaces;

import br.com.elearning.praticas.model.Area;
import java.util.List;

/**
 *
 * @author Felipe
 */
public interface IAreaDao {

    public void salvarArea(Area a) throws Exception;

    public boolean verificarArea(String area) throws Exception;

    public Area buscarArea(String nome) throws Exception;
    
    public Area buscarArea(int id) throws Exception;

    public List<Area> listarArea() throws Exception;
    
    public void removerArea(Area a) throws Exception;
    
    public void editarArea(Area a) throws Exception;

}
