/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.model;

/**
 *
 * @author Felipe
 */
public class Area {

    private long id;
    private String areaNome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAreaNome() {
        return areaNome;
    }

    public void setAreaNome(String area) {
        this.areaNome = area;
    }

    @Override
    public String toString() {
        return areaNome;
    }

}
