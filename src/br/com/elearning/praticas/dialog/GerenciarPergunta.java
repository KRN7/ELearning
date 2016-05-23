/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.util.PropertiesUtils;
import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GerenciarPergunta extends JDialog {

    private JButton btnCadastrarPergunta;
    private JButton btnVOLTAR;
    private JButton btnRemoverPergunta;
    private JButton btnEditarPerguntas;
    private JTable table;
    private Facade facade;
    private DefaultTableModel tableModel;

    public GerenciarPergunta() {
        setBounds(0, 0, 944, 493);
        getContentPane().setLayout(null);
        this.facade = new Facade();

        btnCadastrarPergunta = new JButton("Cadastrar Pergunta");
        btnCadastrarPergunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CadastrarPergunta();
            }
        });
        btnCadastrarPergunta.setBounds(787, 161, 131, 50);
        getContentPane().add(btnCadastrarPergunta);

        btnRemoverPergunta = new JButton("REMOVER PERGUNTA");
        btnRemoverPergunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnRemoverPergunta.setBounds(787, 91, 131, 50);
        getContentPane().add(btnRemoverPergunta);

        btnVOLTAR = new JButton("VOLTAR");
        btnVOLTAR.setBounds(10, 459, 89, 23);
        getContentPane().add(btnVOLTAR);

        btnEditarPerguntas = new JButton("EDITAR PERGUNTA");
        btnEditarPerguntas.setBounds(787, 21, 131, 50);
        getContentPane().add(btnEditarPerguntas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 0, 761, 448);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"PERGUNTAS", "NIVEL", "AREA"}));
        table.getColumnModel().getColumn(2).setResizable(false);
        tableModel = (DefaultTableModel) table.getModel();
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        montarTable();
        setVisible(true);
    }

    private void montarTable() {

        try {
            List<Pergunta> lista;
            lista = facade.listarPergunta();
            for (Pergunta p : lista) {
                tableModel.addRow(new Object[]{p.getQuestao(), p.getNivel(), p.getArea()});
            }
        } catch (Exception ex) {
            Logger.getLogger(GerenciarPergunta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_MONTAR_TABELA));
        }

    }

    private void limparTable() {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
    }

    private void editarPergunta() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int linha = table.getSelectedRow();

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_TABELA_VAZIA));
            return;
        }
        if (linha == -1) {
            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SELECIONE_UMA_QUESTAO));
            return;
        }
        String questao = table.getValueAt(linha, 0).toString();
        System.out.println("Questão: " + questao + " Linha: " + linha);
        try {
            Pergunta p = facade.buscarPergunta(questao);
            System.out.println(p);
            int x = JOptionPane.showConfirmDialog(rootPane, "VOCÊ  TEM CERTEZA QUE REALMENTE DESEJA REMOVER A QUESTÃO? ", "REMOVER PERGUNTA", JOptionPane.OK_CANCEL_OPTION);
            if (x == JOptionPane.OK_OPTION) {
                facade.removerPergunta(p);
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_DELETE_QUESTION));
            e.printStackTrace();
        }
    }
    
//    private void removerPergunta() {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        int linha = table.getSelectedRow();
//
//        if (model.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_TABELA_VAZIA));
//            return;
//        }
//        if (linha == -1) {
//            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SELECIONE_UMA_QUESTAO));
//            return;
//        }
//        String questao = table.getValueAt(linha, 0).toString();
//        System.out.println("Questão: " + questao + " Linha: " + linha);
//        try {
//            Pergunta p = facade.buscarPergunta(questao);
//            System.out.println(p);
////            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_DELETE_QUESTION));
//            int x = JOptionPane.showConfirmDialog(rootPane, "VOCÊ  TEM CERTEZA QUE REALMENTE DESEJA REMOVER A QUESTÃO? ", "REMOVER PERGUNTA", JOptionPane.OK_CANCEL_OPTION);
//            if (x == JOptionPane.OK_OPTION) {
//                facade.removerPergunta(p);
//                return;
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(GerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_DELETE_QUESTION));
//            e.printStackTrace();
//        }
//    }
}
