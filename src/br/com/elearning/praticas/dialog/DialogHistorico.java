/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.HistoricoJogador;
import br.com.elearning.praticas.model.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class DialogHistorico extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JButton btnAtualizar;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JLabel lblUsuario;
    private JLabel lblNAcertos;
    private JLabel lblAcertos;
    private JLabel lblNRespondidas;
    private DefaultTableModel tableModel;
    private HistoricoJogador his;

    /**
     * Create the dialog.
     */
    public DialogHistorico(Usuario u) {

        setSize(292, 165);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("HISTORICO");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        try {
            this.his = new Facade().buscarHistorico(7);
        } catch (Exception ex) {
            Logger.getLogger(DialogHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }

        panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 11, 266, 115);
        contentPanel.add(panel);
        panel.setLayout(null);

        lblNAcertos = new JLabel("N\u00BA ACERTOS: " + his.getPerguntasCertas());
        lblNAcertos.setBounds(10, 36, 305, 14);
        panel.add(lblNAcertos);

        lblNRespondidas = new JLabel("N\u00BA RESPONDIDAS: " + his.getPerguntasRespondidas());
        lblNRespondidas.setBounds(10, 61, 305, 14);
        panel.add(lblNRespondidas);

        lblUsuario = new JLabel("USUARIO: " + u.getNick());
        lblUsuario.setBounds(10, 11, 305, 14);
        panel.add(lblUsuario);

        lblAcertos = new JLabel("APROVEITAMENTO(%):  " + (his.getPerguntasCertas() * 100) / his.getPerguntasRespondidas() + "%");
        lblAcertos.setBounds(10, 86, 305, 14);
        panel.add(lblAcertos);

        setVisible(true);
    }
}
