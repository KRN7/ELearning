/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dialog;

/**
 *
 * @author Felipe
 */
import br.com.elearning.praticas.dao.DaoAlternativa;
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.HistoricoJogador;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.model.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class DialogSimulado extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JPanel panelPrincipal;
    private JPanel panelPerguntas;
    private JButton btnIniciar;
    private JTextField tfQnt;
    private JComboBox cbArea;
    private JComboBox cbNivel;
    private JLabel lblArea;
    private JLabel lblNivel;
    private JLabel lblQuantidade;
    private int x = 0;
    private JScrollPane scrollPane;
    private JRadioButton rdbtnAlt1;
    private JRadioButton rdbtnAlt2;
    private JRadioButton rdbtnAlt3;
    private JRadioButton rdbtnAlt4;
    private JRadioButton rdbtnAlt5;
    private JLabel lblNPergunta;
    private JTextArea tfQuestao;
    private Facade facade;
    private List<Pergunta> pergsAux = null;
    private List<Alternativa> altsAux = null;
    private Alternativa alt = null;
    private HistoricoJogador his;
    private Usuario user;
    private File som;

    /**
     *
     * @param user - Usuario atual.
     */
    public DialogSimulado(Usuario user) {
        this.user = user;

        setSize(741, 479);
        setTitle("SIMULADO");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                "src\\res\\miniLogo.png"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        this.facade = new Facade();
        try {
            this.his = facade.buscarHistorico(this.user.getId());
        } catch (Exception ex) {
            Logger.getLogger(DialogSimulado.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new TitledBorder(null, "",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(10, 11, 715, 395);
        contentPanel.add(panelPrincipal);
        panelPrincipal.setLayout(null);

        cbArea = new JComboBox();
        cbArea.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE"}));
        cbArea.setBounds(61, 44, 177, 20);
        panelPrincipal.add(cbArea);
        preencherArea();

        cbNivel = new JComboBox();
        cbNivel.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE",
            "FACIL", "MEDIO", "DIFICIL"}));
        cbNivel.setBounds(304, 44, 177, 20);
        panelPrincipal.add(cbNivel);

        tfQnt = new JTextField();
        tfQnt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                tfQnt.setText("");
            }
        });
        tfQnt.setBounds(574, 44, 131, 20);
        panelPrincipal.add(tfQnt);
        tfQnt.setColumns(10);

        lblNivel = new JLabel("NIVEL:");
        lblNivel.setBounds(248, 47, 46, 14);
        panelPrincipal.add(lblNivel);

        lblQuantidade = new JLabel("QUANTIDADE:");
        lblQuantidade.setBounds(491, 47, 85, 14);
        panelPrincipal.add(lblQuantidade);

        lblArea = new JLabel("AREA:");
        lblArea.setBounds(10, 47, 46, 14);
        panelPrincipal.add(lblArea);

        btnIniciar = new JButton("INICIAR");
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btnIniciar.getText().equalsIgnoreCase("INICIAR")) {
                    if (cbArea.getSelectedIndex() == 0
                            || cbNivel.getSelectedIndex() == 0
                            || tfQnt.getText().equals("") || tfQnt.getText().equals("0")) {
                        JOptionPane
                                .showMessageDialog(
                                        null,
                                        "OBRIGATORIO O PREENCHIMENTO DE TODOS OS CAMPOS",
                                        "CAMPOS VAZIOS",
                                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        String nivel = String.valueOf(cbNivel.getSelectedItem());
                        int quant = Integer.valueOf(tfQnt.getText());
                        Area area = facade.buscarArea(String.valueOf(cbArea.getSelectedItem()));
                        pergsAux = facade.buscarPerguntasSimulado(nivel, area, quant);
                        altsAux = new DaoAlternativa().listar();
                        if (pergsAux.isEmpty()) {
                            return;
                        } else {
                            panelPrincipal.setVisible(false);
                            panelPerguntas.setVisible(true);
                            btnIniciar.setText("VERIFICAR");
                            tfQuestao.setText(pergsAux.get(x).getQuestao());
                            for (Alternativa a : altsAux) {
                                if (pergsAux.get(x).getId() == a.getPergunta().getId()) {
                                    rdbtnAlt1.setText(a.getAlt1());
                                    rdbtnAlt2.setText(a.getAlt2());
                                    rdbtnAlt3.setText(a.getAlt3());
                                    rdbtnAlt4.setText(a.getAlt4());
                                    rdbtnAlt5.setText(a.getAlt5());
                                    alt = a;
                                }
                            }
                            return;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DialogSimulado.class.getName()).log(Level.SEVERE,
                                null, ex);
                    }
                }
                if (btnIniciar.getText().equalsIgnoreCase("PROXIMO")) {
                    x++;
                    btnIniciar.setText("VERIFICAR");
                    limparRadio();
                    tfQuestao.setText(pergsAux.get(x).getQuestao());
                    String n = String.valueOf(x + 1);
                    lblNPergunta.setText(n + "ª");
                    for (Alternativa a : altsAux) {
                        if (pergsAux.get(x).getId() == a.getPergunta().getId()) {
                            rdbtnAlt1.setText(a.getAlt1());
                            rdbtnAlt2.setText(a.getAlt2());
                            rdbtnAlt3.setText(a.getAlt3());
                            rdbtnAlt4.setText(a.getAlt4());
                            rdbtnAlt5.setText(a.getAlt5());
                            alt = a;
                        }
                    }
                    return;
                }
                if (btnIniciar.getText().equalsIgnoreCase("VERIFICAR")) {
                    verificarResposta(x, alt);
                    btnIniciar.setText("PROXIMO");
                    if (x == pergsAux.size() - 1) {
                        btnIniciar.setText("FINALIZAR");
                        return;
                    }
                    return;
                }
                if (btnIniciar.getText().equals("FINALIZAR")) {
                    JOptionPane.showMessageDialog(DialogSimulado.this,
                            "ACABOU PORRA!!! O//");
                    dispose();
                    return;
                }
            }
        });

        panelPerguntas = new JPanel();
        panelPerguntas.setVisible(false);
        panelPerguntas.setBorder(new TitledBorder(null, "",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPerguntas.setBackground(Color.WHITE);
        panelPerguntas.setBounds(10, 11, 715, 395);
        contentPanel.add(panelPerguntas);
        panelPerguntas.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(39, 11, 666, 127);
        panelPerguntas.add(scrollPane);

        tfQuestao = new JTextArea();
        tfQuestao.setLineWrap(true);
        tfQuestao.setEditable(false);
        scrollPane.setViewportView(tfQuestao);

        rdbtnAlt1 = new JRadioButton();
        rdbtnAlt1.setBackground(Color.WHITE);
        rdbtnAlt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnAlt1.isSelected()) {
                    rdbtnAlt2.setSelected(false);
                    rdbtnAlt3.setSelected(false);
                    rdbtnAlt4.setSelected(false);
                    rdbtnAlt5.setSelected(false);
                }
            }
        });
        rdbtnAlt1.setBounds(6, 186, 699, 23);
        panelPerguntas.add(rdbtnAlt1);

        rdbtnAlt2 = new JRadioButton();
        rdbtnAlt2.setBackground(Color.WHITE);
        rdbtnAlt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnAlt2.isSelected()) {
                    rdbtnAlt1.setSelected(false);
                    rdbtnAlt3.setSelected(false);
                    rdbtnAlt4.setSelected(false);
                    rdbtnAlt5.setSelected(false);
                }
            }
        });
        rdbtnAlt2.setBounds(6, 212, 699, 23);
        panelPerguntas.add(rdbtnAlt2);

        rdbtnAlt3 = new JRadioButton();
        rdbtnAlt3.setBackground(Color.WHITE);
        rdbtnAlt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnAlt3.isSelected()) {
                    rdbtnAlt1.setSelected(false);
                    rdbtnAlt2.setSelected(false);
                    rdbtnAlt4.setSelected(false);
                    rdbtnAlt5.setSelected(false);
                }
            }
        });
        rdbtnAlt3.setBounds(6, 238, 699, 23);
        panelPerguntas.add(rdbtnAlt3);

        rdbtnAlt4 = new JRadioButton();
        rdbtnAlt4.setBackground(Color.WHITE);
        rdbtnAlt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnAlt4.isSelected()) {
                    rdbtnAlt1.setSelected(false);
                    rdbtnAlt2.setSelected(false);
                    rdbtnAlt3.setSelected(false);
                    rdbtnAlt5.setSelected(false);
                }
            }
        });
        rdbtnAlt4.setBounds(6, 264, 699, 23);
        panelPerguntas.add(rdbtnAlt4);

        rdbtnAlt5 = new JRadioButton();
        rdbtnAlt5.setBackground(Color.WHITE);
        rdbtnAlt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnAlt5.isSelected()) {
                    rdbtnAlt2.setSelected(false);
                    rdbtnAlt3.setSelected(false);
                    rdbtnAlt4.setSelected(false);
                    rdbtnAlt1.setSelected(false);
                }
            }
        });
        rdbtnAlt5.setBounds(6, 290, 699, 23);
        panelPerguntas.add(rdbtnAlt5);

        lblNPergunta = new JLabel("1\u00AA");
        lblNPergunta.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNPergunta.setBounds(10, 11, 34, 28);
        panelPerguntas.add(lblNPergunta);
        btnIniciar.setBounds(636, 417, 89, 23);
        contentPanel.add(btnIniciar);

        setVisible(true);
    }

    public void limparRadio() {
        rdbtnAlt1.setSelected(false);
        rdbtnAlt1.setBackground(Color.WHITE);
        rdbtnAlt2.setSelected(false);
        rdbtnAlt2.setBackground(Color.WHITE);
        rdbtnAlt3.setSelected(false);
        rdbtnAlt3.setBackground(Color.WHITE);
        rdbtnAlt4.setSelected(false);
        rdbtnAlt4.setBackground(Color.WHITE);
        rdbtnAlt5.setSelected(false);
        rdbtnAlt5.setBackground(Color.WHITE);
    }

    private void preencherArea() {
        List<Area> lista = null;
        try {
            lista = facade.listarArea();
            for (Area a : lista) {
                cbArea.addItem(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(DialogSimulado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verificarResposta(int x, Alternativa a) {
        try {
            if (!rdbtnAlt1.isSelected() && !rdbtnAlt2.isSelected() && !rdbtnAlt3.isSelected()
                    && !rdbtnAlt4.isSelected() && !rdbtnAlt5.isSelected()) {
                JOptionPane.showMessageDialog(DialogSimulado.this,
                        "VOCE PULOU ESSA PERGUNTA",
                        "ALTERNATIVA NAO SELECIONADA", JOptionPane.WARNING_MESSAGE);
                his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                facade.editarHistoricoRespondidas(his, user);
                return;
            }
            if (rdbtnAlt1.isSelected()) {
                if (rdbtnAlt1.getText().equals(a.getAltCorreta())) {
                    rdbtnAlt1.setBackground(Color.green);
                    his.setPerguntasCertas(his.getPerguntasCertas() + 1);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    System.out.println(his);
                    facade.editarHistorico(his, user);
                    som = new File("src\\sounds\\RespostaCorreta.wav");
                    playSound(som);
                    return;
                }
                if (rdbtnAlt1.getText() != a.getAltCorreta()) {
                    rdbtnAlt1.setBackground(Color.red);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistoricoRespondidas(his, user);
                    som = new File("src\\sounds\\RespostaErrada.wav");
                    playSound(som);
                    return;
                }
            }
            if (rdbtnAlt2.isSelected()) {
                if (rdbtnAlt2.getText().equals(a.getAltCorreta())) {
                    rdbtnAlt2.setBackground(Color.green);
                    his.setPerguntasCertas(his.getPerguntasCertas() + 1);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistorico(his, user);
                    som = new File("src\\sounds\\RespostaCorreta.wav");
                    playSound(som);
                    return;
                }
                if (rdbtnAlt2.getText() != a.getAltCorreta()) {
                    rdbtnAlt2.setBackground(Color.red);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistoricoRespondidas(his, user);
                    som = new File("src\\sounds\\RespostaErrada.wav");
                    playSound(som);
                    return;
                }
            }
            if (rdbtnAlt3.isSelected()) {
                if (rdbtnAlt3.getText().equals(a.getAltCorreta())) {
                    rdbtnAlt3.setBackground(Color.green);
                    his.setPerguntasCertas(his.getPerguntasCertas() + 1);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistorico(his, user);
                    som = new File("src\\sounds\\RespostaCorreta.wav");
                    playSound(som);
                    return;
                }
                if (rdbtnAlt3.getText() != a.getAltCorreta()) {
                    rdbtnAlt3.setBackground(Color.red);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistoricoRespondidas(his, user);
                    som = new File("src\\sounds\\RespostaErrada.wav");
                    playSound(som);
                    return;
                }
            }
            if (rdbtnAlt4.isSelected()) {
                if (rdbtnAlt4.getText().equals(a.getAltCorreta())) {
                    rdbtnAlt4.setBackground(Color.green);
                    his.setPerguntasCertas(his.getPerguntasCertas() + 1);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistorico(his, user);
                    som = new File("src\\sounds\\RespostaCorreta.wav");
                    playSound(som);
                    return;
                }
                if (rdbtnAlt4.getText() != a.getAltCorreta()) {
                    rdbtnAlt4.setBackground(Color.red);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistoricoRespondidas(his, user);
                    som = new File("src\\sounds\\RespostaErrada.wav");
                    playSound(som);
                    return;
                }
            }
            if (rdbtnAlt5.isSelected()) {
                if (rdbtnAlt5.getText().equals(a.getAltCorreta())) {
                    rdbtnAlt5.setBackground(Color.green);
                    his.setPerguntasCertas(his.getPerguntasCertas() + 1);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistorico(his, user);
                    som = new File("src\\sounds\\RespostaCorreta.wav");
                    playSound(som);
                    return;
                }
                if (rdbtnAlt5.getText() != a.getAltCorreta()) {
                    rdbtnAlt5.setBackground(Color.red);
                    his.setPerguntasRespondidas(his.getPerguntasRespondidas() + 1);
                    facade.editarHistoricoRespondidas(his, user);
                    som = new File("src\\sounds\\RespostaErrada.wav");
                    playSound(som);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void playSound(File som) {
        try {
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(som));
            c.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
