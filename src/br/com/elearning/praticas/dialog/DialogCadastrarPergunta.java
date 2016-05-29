package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

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
import javax.swing.border.TitledBorder;

public class DialogCadastrarPergunta extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JButton cancelButton;
    private JLabel lblArea;
    private JComboBox cbArea;
    private JLabel lblNivel;
    private JComboBox cbNivel;
    private JButton btnSalvar;
    private JRadioButton rdbtnAlternativaCorreta;
    private JRadioButton rdbtnAlternativaA;
    private JRadioButton rdbtnAlternativaB;
    private JRadioButton rdbtnAlternativaC;
    private JRadioButton rdbtnAlternativaD;
    private JTextArea tfQuestao;
    private JButton btnAlt1;
    private JButton btnAlt2;
    private JButton btnAlt4;
    private JButton btnAlt3;
    private JButton btnAlt5;
    private JButton btnAltCorreta;
    private JButton btnQuestao;
    private JButton btnAddArea;
    private JPanel panel;
    private JRadioButton rdbtnAlternativaE;
    private Facade facade;

    public static void main(String[] args) {
        new DialogCadastrarPergunta();
    }

    public DialogCadastrarPergunta() {
        setSize(805, 469);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("CADASTRAR PERGUNTA");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        contentPanel.setLayout(null);

        this.facade = new Facade();

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setBounds(10, 15, 779, 381);
        contentPanel.add(panel);
        panel.setLayout(null);

        cbArea = new JComboBox();
        cbArea.setBounds(53, 333, 136, 20);
        panel.add(cbArea);
        cbArea.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE"}));

        lblArea = new JLabel("AREA:");
        lblArea.setBounds(10, 336, 46, 14);
        panel.add(lblArea);

        lblNivel = new JLabel("NIVEL:");
        lblNivel.setBounds(10, 308, 37, 14);
        panel.add(lblNivel);

        cbNivel = new JComboBox();
        cbNivel.setBounds(53, 305, 136, 20);
        panel.add(cbNivel);
        cbNivel.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE",
            "FACIL", "MEDIO", "DIFICIL"}));

        rdbtnAlternativaCorreta = new JRadioButton("ALTERNATIVA CORRETA");
        rdbtnAlternativaCorreta.setBounds(10, 256, 365, 23);
        panel.add(rdbtnAlternativaCorreta);

        rdbtnAlternativaE = new JRadioButton("ALTERNATIVA 5");
        rdbtnAlternativaE.setBounds(10, 233, 365, 20);
        panel.add(rdbtnAlternativaE);

        rdbtnAlternativaD = new JRadioButton("ALTERNATIVA 4");
        rdbtnAlternativaD.setBounds(10, 210, 365, 20);
        panel.add(rdbtnAlternativaD);

        rdbtnAlternativaC = new JRadioButton("ALTERNATIVA 3");
        rdbtnAlternativaC.setBounds(10, 187, 365, 20);
        panel.add(rdbtnAlternativaC);

        rdbtnAlternativaB = new JRadioButton("ALTERNATIVA 2");
        rdbtnAlternativaB.setBounds(10, 164, 365, 20);
        panel.add(rdbtnAlternativaB);

        rdbtnAlternativaA = new JRadioButton("ALTERNATIVA 1");
        rdbtnAlternativaA.setBounds(10, 141, 365, 20);
        panel.add(rdbtnAlternativaA);

        tfQuestao = new JTextArea();
        tfQuestao.setEditable(false);
        tfQuestao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tfQuestao.setBounds(10, 11, 759, 72);
        panel.add(tfQuestao);

        btnAlt1 = new JButton("(clique aqui para add )");
        btnAlt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alt = JOptionPane.showInputDialog("INFORME A ALTERNATIVA 1:", rdbtnAlternativaA.getText());
                rdbtnAlternativaA.setText(alt.toUpperCase());
            }
        });
        btnAlt1.setBounds(381, 141, 179, 23);
        panel.add(btnAlt1);

        btnAlt2 = new JButton("(clique aqui para add)");
        btnAlt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alt = JOptionPane.showInputDialog("INFORME A ALTERNATIVA 2:", rdbtnAlternativaB.getText());
                rdbtnAlternativaB.setText(alt.toUpperCase());
            }
        });
        btnAlt2.setBounds(381, 164, 179, 23);
        panel.add(btnAlt2);

        btnAlt3 = new JButton("(clique aqui para add)");
        btnAlt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alt = JOptionPane.showInputDialog("INFORME A ALTERNATIVA 3:", rdbtnAlternativaC.getText());
                rdbtnAlternativaC.setText(alt.toUpperCase());
            }
        });
        btnAlt3.setBounds(381, 187, 179, 23);
        panel.add(btnAlt3);

        btnAlt4 = new JButton("(clique aqui para add)");
        btnAlt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alt = JOptionPane.showInputDialog("INFORME A ALTERNATIVA 4:", rdbtnAlternativaD.getText());
                rdbtnAlternativaD.setText(alt.toUpperCase());
            }
        });
        btnAlt4.setBounds(381, 210, 179, 23);
        panel.add(btnAlt4);

        btnAlt5 = new JButton("(clique aqui para add)");
        btnAlt5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alt = JOptionPane.showInputDialog("INFORME A ALTERNATIVA 5:", rdbtnAlternativaE.getText());
                rdbtnAlternativaE.setText(alt.toUpperCase());
            }
        });
        btnAlt5.setBounds(381, 233, 179, 23);
        panel.add(btnAlt5);

        btnAltCorreta = new JButton("(clique aqui para add)");
        btnAltCorreta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String alt = JOptionPane.showInputDialog("INFORME A ALTERNATIVA CORRETA:", rdbtnAlternativaCorreta.getText());
                rdbtnAlternativaCorreta.setText(alt.toUpperCase());
            }
        });
        btnAltCorreta.setBounds(381, 257, 179, 23);
        panel.add(btnAltCorreta);

        btnQuestao = new JButton("(clique aqui para add Questao)");
        btnQuestao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String questao = JOptionPane.showInputDialog("INFORME A QUESTAO:", tfQuestao.getText());
                tfQuestao.setText(questao.toUpperCase());
            }
        });
        btnQuestao.setBounds(296, 94, 191, 23);
        panel.add(btnQuestao);

        btnAddArea = new JButton("+");
        btnAddArea.setToolTipText("*ADICIONAR UMA NOVA AREA*");
        btnAddArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeArea = JOptionPane.showInputDialog("INFORME O NOME DA NOVA AREA:");
                if (nomeArea.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(DialogCadastrarPergunta.this, "NENHUM NOME FOI INFORMADO!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Area a = new Area();
                a.setAreaNome(nomeArea);

                try {
                    facade.salvarArea(a);
                    cbArea.removeAllItems();
                    cbArea.addItem("SELECIONE");
                    preencherComboBox(facade);
                    JOptionPane.showMessageDialog(DialogCadastrarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_ADD_AREA));
                } catch (Exception ex) {
                    Logger.getLogger(DialogCadastrarPergunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(DialogCadastrarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_AREA));
                }
            }
        });
        btnAddArea.setBounds(199, 332, 46, 23);
        panel.add(btnAddArea);

        btnSalvar = new JButton("SALVAR");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Pergunta pergunta = new Pergunta();
                    Area a = facade.buscarArea(String.valueOf(cbArea.getSelectedItem()));
                    pergunta.setNivel(String.valueOf(cbNivel.getSelectedItem()));
                    pergunta.setArea(a);
                    pergunta.setStatus(true);
                    pergunta.setQuestao(tfQuestao.getText());
                    Alternativa alt = new Alternativa();
                    alt.setAlt1(rdbtnAlternativaA.getText());
                    alt.setAlt2(rdbtnAlternativaB.getText());
                    alt.setAlt3(rdbtnAlternativaC.getText());
                    alt.setAlt4(rdbtnAlternativaD.getText());
                    alt.setAlt5(rdbtnAlternativaE.getText());
                    alt.setAltCorreta(rdbtnAlternativaCorreta.getText());
                    alt.setPergunta(pergunta);
                    facade.salvarAlternativa(alt, pergunta);
                    JOptionPane.showMessageDialog(DialogCadastrarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_ADD_QUESTION));
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DialogCadastrarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_QUESTION));
                    Logger.getLogger(DialogCadastrarPergunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnSalvar.setBounds(354, 407, 89, 23);
        contentPanel.add(btnSalvar);

        preencherComboBox(facade);

        setVisible(true);
    }

    private void preencherComboBox(Facade f) {
        List<Area> areas = null;
        try {
            areas = f.listarArea();
            for (Area a : areas) {
                cbArea.addItem(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(DialogCadastrarPergunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
