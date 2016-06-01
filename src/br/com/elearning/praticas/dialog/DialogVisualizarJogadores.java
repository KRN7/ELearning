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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

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
import javax.swing.border.TitledBorder;

public class DialogVisualizarJogadores extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JPanel panel;
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
        setTitle("VISUALIZAR JOGADORES");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 11, 820, 436);
        contentPanel.add(panel);
        panel.setLayout(null);
        facade = new Facade();

        scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        scrollPane.setBounds(10, 11, 619, 414);
        panel.add(scrollPane);

        table = new JTable();

        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
            "JOGADORES", "E-MAIL"}));
        tableModel = (DefaultTableModel) table.getModel();
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        btnGerarClassificao = new JButton("GERAR CLASSIFICA\u00C7\u00C3O");
        btnGerarClassificao.setBounds(649, 45, 156, 23);
        panel.add(btnGerarClassificao);

        btnGerarListaDe = new JButton("GERAR LISTA DE USUARIOS");
        btnGerarListaDe.setBounds(641, 11, 169, 23);
        panel.add(btnGerarListaDe);
        btnGerarListaDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ReportsFactory().reportUser();
                } catch (Exception ex) {
                    Logger.getLogger(DialogVisualizarJogadores.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        });
        btnGerarClassificao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ReportsFactory().reportClassificacao();
                } catch (Exception ex) {
                    Logger.getLogger(DialogVisualizarJogadores.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        });

        montarTable();
        setVisible(true);
    }

    private void montarTable() {
        try {
            List<Usuario> lista = facade.listarUsuario();
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
