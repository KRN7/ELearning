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
    private int perguntasRespondidas;
    private int perguntasCertas;

    public HistoricoJogador() {

    }

    public HistoricoJogador(int perguntasRespondidas, int perguntasCertas) {
        this.perguntasRespondidas = perguntasRespondidas;
        this.perguntasCertas = perguntasCertas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPerguntasRespondidas() {
        return perguntasRespondidas;
    }

    public void setPerguntasRespondidas(int perguntasRespondidas) {
        this.perguntasRespondidas = perguntasRespondidas;
    }

    public int getPerguntasCertas() {
        return perguntasCertas;
    }

    public void setPerguntasCertas(int perguntasCertas) {
        this.perguntasCertas = perguntasCertas;
    }

    @Override
    public String toString() {
        return "jogador: { nome: " + usuario.getNome() + ", perguntas=" + perguntasRespondidas + ", PerguntasCertas=" + perguntasCertas + '}';
    }

}
