/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dialog;

/**
 *
 * @author RicksonEllen
 */
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.swing.JTextPane;

public class DialogSimulado extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton btnPrximo;
    private JPanel panel;
    private JComboBox cbNivel;
    private JComboBox cbArea;
    private JLabel lblPerguntas;
    private JTextField tfPerguntas;
    private JPanel panelResposta;
    private JRadioButton rdbtnPrimeiro;
    private JRadioButton rdbtnSegundo;
    private JRadioButton rdbtnTerceiro;
    private JRadioButton rdbtnQuarto;
    private JRadioButton rdbtnQuinto;
    private JLabel lblPergunta;
    private JLabel lblNumPergunta;
    private Facade facade;
    private JTextPane tpQuest;
    private List<Alternativa> lista;
    private int x = 0, pergCertas = 0, pergErradas = 0, pergSemResp = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new DialogSimulado();
    }

    /**
     * Create the dialog.
     */
    public DialogSimulado() {
        setSize(741, 479);
        setTitle("SIMULADO");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        this.facade = new Facade();

        panel = new JPanel();
        panel.setBounds(0, 0, 725, 395);
        contentPanel.add(panel);
        panel.setLayout(null);

        cbNivel = new JComboBox();
        cbNivel.setModel(new DefaultComboBoxModel(new String[]{"SELECIONE", "DIFICIL", "MEDIO", "FACIL"}));
        cbNivel.setBounds(52, 88, 130, 20);
        panel.add(cbNivel);

        cbArea = new JComboBox();
        cbArea.setModel(new DefaultComboBoxModel(new String[]{"\u00C1REA"}));
        cbArea.setBounds(531, 88, 130, 20);
        panel.add(cbArea);
        preencher();

        lblPerguntas = new JLabel("QTD. PERGUNTAS");
        lblPerguntas.setBounds(311, 274, 93, 20);
        panel.add(lblPerguntas);

        tfPerguntas = new JTextField();
        tfPerguntas.setBounds(311, 293, 86, 20);
        panel.add(tfPerguntas);
        tfPerguntas.setColumns(10);

        panelResposta = new JPanel();
        panelResposta.setVisible(false);
        panelResposta.setBounds(0, 0, 725, 395);
        contentPanel.add(panelResposta);
        panelResposta.setLayout(null);

        rdbtnPrimeiro = new JRadioButton("New radio button");
        rdbtnPrimeiro.setBounds(111, 227, 510, 23);
        panelResposta.add(rdbtnPrimeiro);

        rdbtnSegundo = new JRadioButton("New radio button");
        rdbtnSegundo.setBounds(111, 253, 510, 23);
        panelResposta.add(rdbtnSegundo);

        rdbtnTerceiro = new JRadioButton("New radio button");
        rdbtnTerceiro.setBounds(111, 279, 510, 23);
        panelResposta.add(rdbtnTerceiro);

        rdbtnQuarto = new JRadioButton("New radio button");
        rdbtnQuarto.setBounds(111, 305, 510, 23);
        panelResposta.add(rdbtnQuarto);

        rdbtnQuinto = new JRadioButton("New radio button");
        rdbtnQuinto.setBounds(111, 331, 510, 23);
        panelResposta.add(rdbtnQuinto);

        lblPergunta = new JLabel("New label");
        lblPergunta.setBounds(111, 27, 510, 166);
        panelResposta.add(lblPergunta);

        lblNumPergunta = new JLabel("New label");
        lblNumPergunta.setBounds(55, 27, 46, 14);
        panelResposta.add(lblNumPergunta);

        btnPrximo = new JButton("AVAN\u00C7AR");
        btnPrximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(cbNivel.getSelectedIndex() == 0 || cbArea.getSelectedIndex() == 0 || tfPerguntas.getText().equals("")){
                        JOptionPane.showMessageDialog(DialogSimulado.this, "OBRIGATÓRIO PREENCHER TODOS OS CAMPOS!");
                        return;
                    }
                    List<Pergunta> nivel = facade.listarPergunta();
                    boolean flag = true;
                    for(Pergunta p: nivel){
//                        System.out.println("----------------------------------------");
//                        System.out.println("CB: " + (String)cbNivel.getSelectedItem());
//                        System.out.println("NIVEL: " + p.getNivel());
//                        
                        if(p.getNivel().equalsIgnoreCase((String)cbNivel.getSelectedItem())){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        JOptionPane.showMessageDialog(DialogSimulado.this, "NÃO EXISTE PERGUNTA DE NÍVEL " + (String)cbNivel.getSelectedItem());
                    }
                    
                    

                    if (String.valueOf(cbNivel.getSelectedItem()).equals("DIFÍCIL")) {
                        if (Integer.valueOf(tfPerguntas.getText()) > nivel.size()) {
                            JOptionPane.showMessageDialog(DialogSimulado.this, "QUANTIDADE INSUFICIENTE!");
                            Pergunta p = facade.buscarPergunta(String.valueOf(cbArea.getSelectedItem()));
                            return;
                        }
                     
                    }
                    if (String.valueOf(cbNivel.getSelectedItem()).equals("MÉDIO")) {
                        if (Integer.valueOf(tfPerguntas.getText()) > nivel.size()) {
                            JOptionPane.showMessageDialog(DialogSimulado.this, "QUANTIDADE INSUFICIENTE!");
                            Pergunta p = facade.buscarPergunta(String.valueOf(cbArea.getSelectedItem()));
                            return;
                        }
                    }
                    if (String.valueOf(cbNivel.getSelectedItem()).equals("FÁCIL")) {
                        if (Integer.valueOf(tfPerguntas.getText()) > nivel.size()) {
                            JOptionPane.showMessageDialog(DialogSimulado.this, "QUANTIDADE INSUFICIENTE!");
                            Pergunta p = facade.buscarPergunta(String.valueOf(cbArea.getSelectedItem()));
                            return;
                        }
                    }
                    List<Area> area = facade.listarArea();
                    for (Area a : area) {
                        cbArea.getSelectedItem();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DialogSimulado.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(DialogSimulado.this,
                            PropertiesUtils
                            .getMsgValue(PropertiesUtils.MSG_ERRO_START_SIM));
                }
            }
        });
        btnPrximo.setBounds(626, 406, 89, 23);
        contentPanel.add(btnPrximo);

        setVisible(true);
    }

    private void preencher() {
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
    
    public void verificarResposta() {
		if (rdbtnPrimeiro.isSelected() == false && rdbtnSegundo.isSelected() == false
				&& rdbtnTerceiro.isSelected() == false && rdbtnQuarto.isSelected() == false
				&& rdbtnQuinto.isSelected() == false) {
			JOptionPane.showMessageDialog(DialogSimulado.this,
					"Você pulou a pergunta!",
					"Alternativa não selecionada", JOptionPane.WARNING_MESSAGE);
			pergSemResp++;
		}
		if (rdbtnPrimeiro.isSelected()) {
			if (rdbtnPrimeiro.getText().equals(lista.get(x).getAltCorreta())) {

				pergCertas++;
				rdbtnPrimeiro.setBackground(Color.green);
				return;
			}
			if (rdbtnPrimeiro.getText() != lista.get(x).getAltCorreta()) {
				pergErradas++;
				rdbtnPrimeiro.setBackground(Color.red);
				return;
			}
		}
		if (rdbtnSegundo.isSelected()) {
			if (rdbtnSegundo.getText().equals(lista.get(x).getAltCorreta())) {
				pergCertas++;
				rdbtnSegundo.setBackground(Color.green);
				return;
			}
			if (rdbtnSegundo.getText() != lista.get(x).getAltCorreta()) {
				pergErradas++;
				rdbtnSegundo.setBackground(Color.red);
				return;
			}
		}
		if (rdbtnTerceiro.isSelected()) {
			if (rdbtnTerceiro.getText().equals(lista.get(x).getAltCorreta())) {
				pergCertas++;
				rdbtnTerceiro.setBackground(Color.green);
				return;
			}
			if (rdbtnTerceiro.getText() != lista.get(x).getAltCorreta()) {
				pergErradas++;
				rdbtnTerceiro.setBackground(Color.red);
				return;
			}
		}
		if (rdbtnQuarto.isSelected()) {
			if (rdbtnQuarto.getText().equals(lista.get(x).getAltCorreta())) {
				pergCertas++;
				rdbtnQuarto.setBackground(Color.green);
				return;
			}
			if (rdbtnQuarto.getText() != lista.get(x).getAltCorreta()) {
				pergErradas++;
				rdbtnQuarto.setBackground(Color.red);
				return;
			}
		}
		if (rdbtnQuinto.isSelected()) {
			if (rdbtnQuinto.getText().equals(lista.get(x).getAltCorreta())) {
				pergCertas++;
				rdbtnQuinto.setBackground(Color.green);
				return;
			}
			if (rdbtnQuinto.getText() != lista.get(x).getAltCorreta()) {
				pergErradas++;
				rdbtnQuinto.setBackground(Color.red);
				return;
			}
		}
	}
    
    public void setListaFiltrada(List<Alternativa> lista) {
		this.lista = lista;
	}
    
    private void limparAlts() {
		tpQuest.setText("");
		rdbtnPrimeiro.setSelected(false);
		rdbtnPrimeiro.setBackground(new Color(11, 86, 130));
		rdbtnSegundo.setSelected(false);
		rdbtnSegundo.setBackground(new Color(11, 86, 130));
		rdbtnTerceiro.setSelected(false);
		rdbtnTerceiro.setBackground(new Color(11, 86, 130));
		rdbtnQuarto.setSelected(false);
		rdbtnQuarto.setBackground(new Color(11, 86, 130));
		rdbtnQuinto.setSelected(false);
		rdbtnQuinto.setBackground(new Color(11, 86, 130));
	}

	private void mostrarRespCerta() {
		if (rdbtnPrimeiro.isSelected() == false && rdbtnSegundo.isSelected() == false
				&& rdbtnTerceiro.isSelected() == false && rdbtnQuarto.isSelected() == false
				&& rdbtnQuinto.isSelected() == false) {
			return;
		}
		if (rdbtnPrimeiro.getText().equals(lista.get(x).getAltCorreta())) {
			rdbtnPrimeiro.setBackground(Color.green);
			return;
		}
		if (rdbtnSegundo.getText().equals(lista.get(x).getAltCorreta())) {
			rdbtnSegundo.setBackground(Color.green);
			return;
		}
		if (rdbtnTerceiro.getText().equals(lista.get(x).getAltCorreta())) {
			rdbtnTerceiro.setBackground(Color.green);
			return;
		}
		if (rdbtnQuarto.getText().equals(lista.get(x).getAltCorreta())) {
			rdbtnQuarto.setBackground(Color.green);
			return;
		}
		if (rdbtnQuinto.getText().equals(lista.get(x).getAltCorreta())){
			rdbtnQuinto.setBackground(Color.green);
			return;
		}
	}
}
