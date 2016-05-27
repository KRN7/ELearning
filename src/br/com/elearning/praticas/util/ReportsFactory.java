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
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Felipe
 */
public class ReportsFactory {

//    private Facade facade = new Facade();
//
//    public void reportUser() throws Exception {
//        try {
//            List<Usuario> users = facade.listarUsuario();
//            List<Usuario> listaUser = new ArrayList<>();
//            for (Usuario u : users) {
//                if (u.getTipo().equalsIgnoreCase("j")) {
//                    listaUser.add(u);
//                }
//            }
//            JasperReport report = JasperCompileManager.compileReport(PropertiesUtils.getConfigValue(PropertiesUtils.TEMPLATE_PATH) + "listausuarios.jrxml");
//            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaUser));
//            JasperExportManager.exportReportToPdfFile(print, PropertiesUtils.getConfigValue(PropertiesUtils.REPORTS_PATH) + PropertiesUtils.getConfigValue(PropertiesUtils.USER_FILE));
//            Runtime run = Runtime.getRuntime();
//            run = Runtime.getRuntime();
//            Process proc = run.exec(PropertiesUtils.getConfigValue(PropertiesUtils.COMMAND) + PropertiesUtils.getConfigValue(PropertiesUtils.USER_FILE));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void reportGabarito() throws Exception {
//        try {
//            List<Alternativa> listaPerg = new DaoAlternativa().listar();
//            System.out.println(listaPerg);
//            JasperReport report = JasperCompileManager.compileReport(PropertiesUtils.getConfigValue(PropertiesUtils.TEMPLATE_PATH) + "gabarito.jrxml");
//            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaPerg));
//            JasperExportManager.exportReportToPdfFile(print, PropertiesUtils.getConfigValue(PropertiesUtils.REPORTS_PATH) + PropertiesUtils.getConfigValue(PropertiesUtils.TEMPLET_FILE));
//            Runtime run = Runtime.getRuntime();
//            run = Runtime.getRuntime();
//            Process proc = run.exec(PropertiesUtils.getConfigValue(PropertiesUtils.COMMAND) + PropertiesUtils.getConfigValue(PropertiesUtils.TEMPLET_FILE));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void reportClassificacao() throws Exception {
//        try {
//            List<HistoricoJogador> listaHis = new DaoHistorico().listarHistoricos();
//            JasperReport report = JasperCompileManager.compileReport(PropertiesUtils.getConfigValue(PropertiesUtils.TEMPLATE_PATH) + "classificacao.jrxml");
//            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaHis));
//            JasperExportManager.exportReportToPdfFile(print, PropertiesUtils.getConfigValue(PropertiesUtils.REPORTS_PATH) + PropertiesUtils.getConfigValue(PropertiesUtils.RANK_FILE));
//            Runtime run = Runtime.getRuntime();
//            run = Runtime.getRuntime();
//            Process proc = run.exec(PropertiesUtils.getConfigValue(PropertiesUtils.COMMAND) + PropertiesUtils.getConfigValue(PropertiesUtils.RANK_FILE));
////            C:\Users\Felipe\Documents\ELearning\src\relatorios
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
