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
public class Alternativa {

    private long id;
    private String alt1;
    private String alt2;
    private String alt3;
    private String alt4;
    private String alt5;
    private String altCorreta;
    private Pergunta pergunta; //pergunta composicao

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlt1() {
        return alt1;
    }

    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public String getAlt3() {
        return alt3;
    }

    public void setAlt3(String alt3) {
        this.alt3 = alt3;
    }

    public String getAlt4() {
        return alt4;
    }

    public void setAlt4(String alt4) {
        this.alt4 = alt4;
    }

    public String getAlt5() {
        return alt5;
    }

    public void setAlt5(String alt5) {
        this.alt5 = alt5;
    }

    public String getAltCorreta() {
        return altCorreta;
    }

    public void setAltCorreta(String altCorreta) {
        this.altCorreta = altCorreta;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public String toString() {
        return "Alternativa{" + "id=" + id + ", alt1=" + alt1 + ", alt2=" + alt2 + ", alt3=" + alt3 + ", alt4=" + alt4 + ", alt5=" + alt5 + '}';
    }

}
