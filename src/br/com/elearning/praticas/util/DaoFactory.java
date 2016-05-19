/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.util;

import br.com.elearning.praticas.interfaces.IHistoricoJogadorDao;
import br.com.elearning.praticas.interfaces.IUsuarioDao;
import br.com.elearning.praticas.interfaces.IPerguntaDao;
import br.com.elearning.praticas.interfaces.IAlternativaDao;
import br.com.elearning.praticas.interfaces.IAreaDao;
import br.com.elearning.praticas.dao.DaoPergunta;
import br.com.elearning.praticas.dao.DaoAlternativa;
import br.com.elearning.praticas.dao.DaoArea;
import br.com.elearning.praticas.dao.DaoHistorico;
import br.com.elearning.praticas.dao.DaoSimulado;
import br.com.elearning.praticas.dao.DaoUsuario;
import br.com.elearning.praticas.interfaces.ISimuladoDao;

/**
 *
 * @author RicksonEllen
 */
public class DaoFactory {

    public static IUsuarioDao createDaoUsuario() {
        return new DaoUsuario();
    }

    public static IPerguntaDao createDaoPergunta() {
        return new DaoPergunta();
    }

    public static IAreaDao createDaoArea() {
        return new DaoArea();
    }

    public static IAlternativaDao createDaoAlternativa() {
        return new DaoAlternativa();
    }

    public static IHistoricoJogadorDao createDaoHistorico() {
        return new DaoHistorico();
    }

    public static ISimuladoDao createDaoSimulado() {
        return new DaoSimulado();
    }
}
