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
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class DialogEditarPergunta extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblInformeAPergunta;
    private JLabel lblInformeAlternativa1;
    private JLabel lblInformeAlternativa2;
    private JLabel lblInformeAlternativa3;
    private JLabel lblInformeAlternativa4;
    private JTextArea tfPergunta;
    private JScrollPane scrollPane_1;
    private JTextArea tfAlternativa1;
    private JScrollPane scrollPane_2;
    private JTextArea tfAlternativa2;
    private JScrollPane scrollPane_3;
    private JTextArea tfAlternativa3;
    private JScrollPane scrollPane_4;
    private JTextArea tfAlternativa4;
    private JScrollPane scrollPane_5;
    private JTextArea tfAlternativa5;
    private JLabel lblInformeAlternativa;
    private JButton okButton;
    private JButton cancelButton;
    private JButton btnSalvar;
    private JLabel lblArea;
    private JComboBox cbArea;
    private JLabel lblNivel;
    private JComboBox cbNivel;
    private JLabel lblInformeAlternativaCorreta;
    private JScrollPane scrollPane_6;
    private JTextField tfAlternativaCorreta;
    private JRadioButton rdbtnStatus;
    private Pergunta p;
    private Alternativa a;
    private Facade facade;
    private JLabel lblStatusDaPergunta;

    public DialogEditarPergunta(Pergunta perg, Alternativa alt) {
        p = perg;
        a = alt;

        setSize(555, 694);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        this.facade = new Facade();

        lblInformeAPergunta = new JLabel("INFORME ABAIXO A NOVA PERGUNTA:");
        lblInformeAPergunta.setBounds(10, 11, 285, 14);
        contentPanel.add(lblInformeAPergunta);

        lblInformeAlternativa1 = new JLabel("INFORME A NOVA ALTERNATIVA 1:");
        lblInformeAlternativa1.setBounds(10, 103, 285, 14);
        contentPanel.add(lblInformeAlternativa1);

        lblInformeAlternativa2 = new JLabel("INFORME A NOVA ALTERNATIVA 2:");
        lblInformeAlternativa2.setBounds(10, 174, 285, 14);
        contentPanel.add(lblInformeAlternativa2);

        lblInformeAlternativa3 = new JLabel("INFORME A NOVA ALTERNATIVA 3:");
        lblInformeAlternativa3.setBounds(10, 245, 285, 14);
        contentPanel.add(lblInformeAlternativa3);

        lblInformeAlternativa4 = new JLabel("INFORME A NOVA ALTERNATIVA 4:");
        lblInformeAlternativa4.setBounds(10, 316, 285, 14);
        contentPanel.add(lblInformeAlternativa4);

        lblInformeAlternativa = new JLabel("INFORME A NOVA ALTERNATIVA 5:");
        lblInformeAlternativa.setBounds(10, 387, 285, 14);
        contentPanel.add(lblInformeAlternativa);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 37, 285, 55);
        contentPanel.add(scrollPane);

        tfPergunta = new JTextArea();
        tfPergunta.setLineWrap(true);
        scrollPane.setViewportView(tfPergunta);

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 128, 285, 35);
        contentPanel.add(scrollPane_1);

        tfAlternativa1 = new JTextArea();
        tfAlternativa1.setLineWrap(true);
        scrollPane_1.setViewportView(tfAlternativa1);

        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 199, 285, 35);
        contentPanel.add(scrollPane_2);

        tfAlternativa2 = new JTextArea();
        tfAlternativa2.setLineWrap(true);
        scrollPane_2.setViewportView(tfAlternativa2);

        scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(10, 270, 285, 35);
        contentPanel.add(scrollPane_3);

        tfAlternativa3 = new JTextArea();
        tfAlternativa3.setLineWrap(true);
        scrollPane_3.setViewportView(tfAlternativa3);

        scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(10, 341, 285, 35);
        contentPanel.add(scrollPane_4);

        tfAlternativa4 = new JTextArea();
        tfAlternativa4.setLineWrap(true);
        scrollPane_4.setViewportView(tfAlternativa4);

        scrollPane_5 = new JScrollPane();
        scrollPane_5.setBounds(10, 412, 285, 35);
        contentPanel.add(scrollPane_5);

        tfAlternativa5 = new JTextArea();
        tfAlternativa5.setLineWrap(true);
        scrollPane_5.setViewportView(tfAlternativa5);

        btnSalvar = new JButton("EDITAR");
        btnSalvar.setBounds(440, 622, 89, 23);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    p.setQuestao(tfPergunta.getText());
                    Area area = facade.buscarArea(String.valueOf(cbArea.getSelectedItem()));
                    p.setArea(area);
                    p.setNivel(String.valueOf(cbNivel.getSelectedItem()));
                    if (rdbtnStatus.isSelected()) {
                        p.setStatus(false);
                    }
                    if (!rdbtnStatus.isSelected()) {
                        p.setStatus(true);
                    }
                    a.setAlt1(tfAlternativa1.getText());
                    a.setAlt2(tfAlternativa2.getText());
                    a.setAlt3(tfAlternativa3.getText());
                    a.setAlt4(tfAlternativa4.getText());
                    a.setAlt5(tfAlternativa5.getText());
                    a.setAltCorreta(tfAlternativaCorreta.getText());

                    facade.editarPergunta(p);
                    facade.editarAlternativa(a, p);
                    JOptionPane.showMessageDialog(DialogEditarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_UPDATE_QUESTION));
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DialogEditarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
                    Logger.getLogger(DialogEditarPergunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        contentPanel.add(btnSalvar);

        lblArea = new JLabel("INFORME A NOVA AREA:");
        lblArea.setBounds(10, 536, 126, 14);
        contentPanel.add(lblArea);

        cbArea = new JComboBox();
        cbArea.setBounds(10, 561, 80, 20);
        cbArea.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE"}));
        contentPanel.add(cbArea);

        lblNivel = new JLabel("INFORME O NOVO NIVEL:");
        lblNivel.setBounds(187, 536, 126, 14);
        contentPanel.add(lblNivel);

        cbNivel = new JComboBox();
        cbNivel.setBounds(187, 561, 80, 20);
        cbNivel.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE", "FAC\u00CDL", "M\u00C9DIO", "DIF\u00CDCIL"}));
        contentPanel.add(cbNivel);

        lblInformeAlternativaCorreta = new JLabel("INFORME A NOVA ALTERNATIVA CORRETA:");
        lblInformeAlternativaCorreta.setBounds(10, 458, 285, 14);
        contentPanel.add(lblInformeAlternativaCorreta);

        scrollPane_6 = new JScrollPane();
        scrollPane_6.setBounds(10, 483, 285, 35);
        contentPanel.add(scrollPane_6);

        tfAlternativaCorreta = new JTextField();
        scrollPane_6.setViewportView(tfAlternativaCorreta);

        lblStatusDaPergunta = new JLabel("STATUS DA PERGUNTA:");
        lblStatusDaPergunta.setBounds(350, 536, 118, 14);
        contentPanel.add(lblStatusDaPergunta);

        rdbtnStatus = new JRadioButton("MARQUE PARA DESATIVAR:");
        rdbtnStatus.setBounds(343, 560, 161, 23);
        contentPanel.add(rdbtnStatus);
        if (this.p != null) {
            carregarComponentes();
        }

        setVisible(true);
    }

    public void preencher() {
        try {
            List<Area> lista = new Facade().listarArea();
            for (Area a : lista) {
                cbArea.addItem(a);
            }
        } catch (Exception e) {
            //jop
        }
    }

    private void carregarComponentes() {
        tfPergunta.setText(p.getQuestao());
        preencher();
        cbArea.setSelectedItem(p.getArea().getAreaNome());

        switch (p.getNivel()) {
            case "FACIL":
                cbNivel.setSelectedIndex(1);

                break;
            case "MEDIO":
                cbNivel.setSelectedIndex(2);

                break;
            case "DIFICIL":
                cbNivel.setSelectedIndex(3);

                break;
        }
        tfAlternativa1.setText(a.getAlt1());
        tfAlternativa2.setText(a.getAlt2());
        tfAlternativa3.setText(a.getAlt3());
        tfAlternativa4.setText(a.getAlt4());
        tfAlternativa5.setText(a.getAlt5());
        tfAlternativaCorreta.setText(a.getAltCorreta());

    }

}
