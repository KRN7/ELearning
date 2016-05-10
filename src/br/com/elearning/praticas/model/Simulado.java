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
public class Simulado {

    private long id;
    private int ano;
    private List<Pergunta> perguntas;

    public Simulado() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    @Override
    public String toString() {
        return "Simula: [ ano: " + this.ano + ", perguntas: " + this.perguntas + " ]\n";
    }
}
