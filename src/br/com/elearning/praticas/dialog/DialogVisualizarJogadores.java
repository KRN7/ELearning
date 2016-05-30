/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dialog;

/**
 *
 * @author Sidney
 */
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.util.PropertiesUtils;
import br.com.elearning.praticas.util.ReportsFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DialogVisualizarJogadores extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JScrollPane scrollPane;
    private JButton btnGerarListaDe;
    private JButton btnGerarClassificao;
    private Facade facade;
    private DefaultTableModel tableModel;

    /**
     * Create the dialog.
     */
    public DialogVisualizarJogadores() {
        setSize(846, 486);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        facade = new Facade();

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 23, 632, 391);
        contentPanel.add(scrollPane);

        table = new JTable();

        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "JOGADORES", "E-MAIL"
                }
        ));
        tableModel = (DefaultTableModel) table.getModel();
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        btnGerarListaDe = new JButton("GERAR LISTA DE USUARIOS");
        btnGerarListaDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ReportsFactory().reportUser();
                } catch (Exception ex) {
                    Logger.getLogger(DialogVisualizarJogadores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnGerarListaDe.setBounds(652, 37, 168, 50);
        contentPanel.add(btnGerarListaDe);

        btnGerarClassificao = new JButton("GERAR CLASSIFICA\u00C7\u00C3O");
        btnGerarClassificao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ReportsFactory().reportClassificacao();
                } catch (Exception ex) {
                    Logger.getLogger(DialogVisualizarJogadores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnGerarClassificao.setBounds(652, 123, 168, 50);
        contentPanel.add(btnGerarClassificao);

        montarTable();
        setVisible(true);
    }

    private void montarTable() {

        try {
            List<Usuario> lista;
            lista = facade.listarUsuario();
            for (Usuario u : lista) {
                if (u.getTipo().equalsIgnoreCase("J")) {
                    tableModel.addRow(new Object[]{u.getNome(), u.getEmail()});
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DialogGerenciarPergunta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(DialogVisualizarJogadores.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_MONTAR_TABELA));
        }

    }

    private void limparTable() {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
    }

}
