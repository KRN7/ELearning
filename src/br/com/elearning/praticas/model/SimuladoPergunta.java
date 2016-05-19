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
public class SimuladoPergunta {

    private long id;
    private Simulado simulado;
    private List<Pergunta> perguntas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Simulado getSimulado() {
        return simulado;
    }

    public void setSimulado(Simulado simulado) {
        this.simulado = simulado;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    @Override
    public String toString() {
        return "Id: " + this.simulado.getId() + ", Ano: " + this.simulado.getAno() + ", Perguntas: " + this.perguntas;
    }

}
