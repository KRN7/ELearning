package br.com.elearning.praticas.dialog;

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

public class CadastrarPergunta extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblInformeAPergunta;
    private JLabel lblInformeAlternativa1;
    private JLabel lblInformeAlternativa2;
    private JLabel lblInformeAlternativa3;
    private JLabel lblInformeAlternativa4;
    private JLabel lblInformeAlternativaCorreta;
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
    private JScrollPane scrollPane_6;
    private JTextArea tfAlternativaCorreta;
    private JLabel lblInformeAlternativa;
    private JButton okButton;
    private JButton cancelButton;
    private JButton btnSalvar;
    private JLabel lblArea;
    private JComboBox cbArea;
    private JLabel lblNivel;
    private JComboBox cbNivel;
    private Facade facade;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CadastrarPergunta dialog = new CadastrarPergunta();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public CadastrarPergunta() {
        setSize(555, 694);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);

        this.facade = new Facade();

        lblInformeAPergunta = new JLabel("INFORME ABAIXO A PERGUNTA QUE DESEJA CADASTRAR:");
        lblInformeAPergunta.setBounds(10, 11, 285, 14);
        contentPanel.add(lblInformeAPergunta);

        lblInformeAlternativa1 = new JLabel("INFORME ALTERNATIVA 1:");
        lblInformeAlternativa1.setBounds(10, 103, 285, 14);
        contentPanel.add(lblInformeAlternativa1);

        lblInformeAlternativa2 = new JLabel("INFORME ALTERNATIVA 2:");
        lblInformeAlternativa2.setBounds(10, 174, 285, 14);
        contentPanel.add(lblInformeAlternativa2);

        lblInformeAlternativa3 = new JLabel("INFORME ALTERNATIVA 3:");
        lblInformeAlternativa3.setBounds(10, 245, 285, 14);
        contentPanel.add(lblInformeAlternativa3);

        lblInformeAlternativa4 = new JLabel("INFORME ALTERNATIVA 4:");
        lblInformeAlternativa4.setBounds(10, 316, 285, 14);
        contentPanel.add(lblInformeAlternativa4);

        lblInformeAlternativa = new JLabel("INFORME ALTERNATIVA 5:");
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
        lblInformeAlternativaCorreta = new JLabel("INFORME ALTERNATIVA CORRETA:");
        lblInformeAlternativaCorreta.setBounds(10, 458, 285, 14);
        contentPanel.add(lblInformeAlternativaCorreta);

        scrollPane_6 = new JScrollPane();
        scrollPane_6.setBounds(10, 483, 285, 35);
        contentPanel.add(scrollPane_6);

        tfAlternativaCorreta = new JTextArea();
        tfAlternativaCorreta.setLineWrap(true);
        scrollPane_6.setViewportView(tfAlternativaCorreta);

        btnSalvar = new JButton("SALVAR");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Pergunta pergunta = new Pergunta();
                    Area a = facade.buscarArea(String.valueOf(cbArea.getSelectedItem()));
                    pergunta.setNivel(String.valueOf(cbNivel.getSelectedItem()));
                    pergunta.setArea(a);
                    pergunta.setQuestao(tfPergunta.getText());
                    Alternativa alternativa = new Alternativa();
                    alternativa.setAlt1(tfAlternativa1.getText());
                    alternativa.setAlt2(tfAlternativa2.getText());
                    alternativa.setAlt3(tfAlternativa3.getText());
                    alternativa.setAlt4(tfAlternativa4.getText());
                    alternativa.setAlt5(tfAlternativa5.getText());
                    alternativa.setAltCorreta(tfAlternativaCorreta.getText());
                    alternativa.setPergunta(pergunta);
                    facade.salvarAlternativa(alternativa, pergunta);
                    JOptionPane.showMessageDialog(CadastrarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_ADD_QUESTION));
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CadastrarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_QUESTION));
                    Logger.getLogger(CadastrarPergunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnSalvar.setBounds(440, 622, 89, 23);
        contentPanel.add(btnSalvar);

        lblArea = new JLabel("AREA");
        lblArea.setBounds(10, 536, 46, 14);
        contentPanel.add(lblArea);

        cbArea = new JComboBox();
        cbArea.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE"}));
        cbArea.setBounds(10, 561, 62, 20);
        contentPanel.add(cbArea);

        lblNivel = new JLabel("NIVEL");
        lblNivel.setBounds(216, 536, 46, 14);
        contentPanel.add(lblNivel);

        cbNivel = new JComboBox();
        cbNivel.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE", "FAC\u00CDL", "M\u00C9DIO", "DIF\u00CDCIL"}));
        cbNivel.setBounds(216, 561, 62, 20);
        contentPanel.add(cbNivel);

        preencherCoboBox(facade);

        setVisible(true);
    }

    public void preencherCoboBox(Facade f) {
        List<Area> areas = null;
        try {
            areas = f.listarArea();
            for (Area a : areas) {
                cbArea.addItem(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(CadastrarPergunta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
