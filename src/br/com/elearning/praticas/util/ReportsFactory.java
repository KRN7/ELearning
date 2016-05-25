/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.util;

import br.com.elearning.praticas.dao.DaoAlternativa;
import br.com.elearning.praticas.dao.DaoHistorico;
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Felipe
 */
public class ReportsFactory {

    private Facade facade = new Facade();
    private static final String REPORTS_PATH = "src/relatorios/";
    private static final String USER_TEMPLATE = "src/relatorios/";
    private static final String TEMPLET_TEMPLATE = "src/relatorios/";
    private static final String RANK_TEMPLATE = "src/relatorios/";
    private static final String TEMPLATE_PATH = "src/templates/";
    private static final String USER_FILE = "Usuarios.pdf";
    private static final String TEMPLET_FILE = "Gabarito.pdf";
    private static final String RANK_FILE = "Classificacao.pdf";

    public void reportUser() throws Exception {
        try {
            List<Usuario> users = facade.listarUsuario();
            List<Usuario> listaUser = new ArrayList<>();
            for (Usuario u : users) {
                if (u.getTipo().equalsIgnoreCase("j")) {
                    listaUser.add(u);
                }
            }
            JasperReport report = JasperCompileManager.compileReport(TEMPLATE_PATH + "listausuarios.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaUser));
            JasperExportManager.exportReportToPdfFile(print, REPORTS_PATH + USER_FILE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reportGabarito() throws Exception {
        try {
            List<Alternativa> listaPerg = new DaoAlternativa().listar();
            System.out.println(listaPerg);
            JasperReport report = JasperCompileManager.compileReport(TEMPLATE_PATH + "gabarito.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaPerg));
            JasperExportManager.exportReportToPdfFile(print, REPORTS_PATH + TEMPLET_FILE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reportClassificacao() throws Exception {
        try {
            List<HistoricoJogador> listaHis = new DaoHistorico().listarHistoricos();
            JasperReport report = JasperCompileManager.compileReport(TEMPLATE_PATH + "classificacao.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaHis));
            JasperExportManager.exportReportToPdfFile(print, REPORTS_PATH + RANK_FILE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
