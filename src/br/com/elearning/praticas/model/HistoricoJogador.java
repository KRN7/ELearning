/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.model;

import java.util.List;

/**
 *
 * @author Sidney
 */
public class HistoricoJogador {

    private long id;
    private Usuario usuario;
    private Pergunta pergunta;
    private int perguntasRespondidas;
    private int perguntasCertas;

    public HistoricoJogador() {

    }

    public HistoricoJogador(int perguntasRespondidas, int perguntasCertas) {
        this.perguntasRespondidas = perguntasRespondidas;
        this.perguntasCertas = perguntasCertas;
    }

    public int getPerguntasRespondidas() {
        return perguntasRespondidas;
    }

    public int getPerguntasCertas() {
        return perguntasCertas;
    }

    public void setPerguntasRespondidas(int perguntasRespondidas) {
        this.perguntasRespondidas = perguntasRespondidas;
    }

    public void setPerguntasCertas(int perguntasCertas) {
        this.perguntasCertas = perguntasCertas;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    @Override
    public String toString() {
        return "Resposta{" + "perguntas=" + perguntasRespondidas + ", PerguntasCertas=" + perguntasCertas + '}';
    }

}
