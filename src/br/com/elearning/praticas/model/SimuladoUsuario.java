/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.model;

import java.util.List;

/**
 *
 * @author Felipe
 */
public class SimuladoUsuario {

    private long id;
    private List<Usuario> users;
    private Simulado simulado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Usuario> getUser() {
        return users;
    }

    public void setUser(List<Usuario> users) {
        this.users = users;
    }

    public Simulado getSimulado() {
        return simulado;
    }

    public void setSimulado(Simulado simulado) {
        this.simulado = simulado;
    }

    @Override
    public String toString() {
        return "Id: " + this.simulado.getId() + ", Ano: " + this.simulado.getAno() + ", Lista de Usuario: " + this.users+"\n";
    }
}
