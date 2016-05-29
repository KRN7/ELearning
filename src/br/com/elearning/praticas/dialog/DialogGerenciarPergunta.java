/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Alternativa;
import br.com.elearning.praticas.model.Area;
import br.com.elearning.praticas.model.Pergunta;
import br.com.elearning.praticas.util.PropertiesUtils;
import br.com.elearning.praticas.util.ReportsFactory;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class DialogGerenciarPergunta extends JDialog {

    private JButton btnCadastrarPergunta;
    private JButton btnVOLTAR;
    private JButton btnRemoverPergunta;
    private JButton btnRelatorio;
    private JButton btnEditarPergunta;
    private JButton btnCadastrarArea;
    private JTable table;
    private Facade facade;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private JButton btnAtualizarTabela;

    public DialogGerenciarPergunta() {

        setSize(944, 493);
        setResizable(false);
        setModal(true);
        setTitle("GERENCIAR PERGUNTA");
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));

        this.facade = new Facade();

        btnCadastrarPergunta = new JButton("CADASTRAR PERGUNTA");
        btnCadastrarPergunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DialogCadastrarPergunta();
            }
        });
        btnCadastrarPergunta.setBounds(765, 14, 153, 23);
        getContentPane().add(btnCadastrarPergunta);

        btnCadastrarArea = new JButton("CADASTRAR AREA");
        btnCadastrarArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeArea = JOptionPane.showInputDialog("INFORME O NOME DA NOVA AREA:");
                if (nomeArea.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, "NENHUM NOME FOI INFORMADO!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Area a = new Area();
                a.setAreaNome(nomeArea);

                try {
                    facade.salvarArea(a);
                    JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_ADD_AREA), "CADASTRAR AREA", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_AREA), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnCadastrarArea.setBounds(778, 48, 125, 23);
        getContentPane().add(btnCadastrarArea);

        btnEditarPergunta = new JButton("EDITAR PERGUNTA");
        btnEditarPergunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarPergunta();
            }
        });
        btnEditarPergunta.setBounds(778, 82, 125, 23);
        getContentPane().add(btnEditarPergunta);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 745, 443);
        getContentPane().add(panel);
        panel.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(10, 11, 725, 421);
        panel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
            "PERGUNTAS", "NIVEL", "AREA", "STATUS"}));
        table.getColumnModel().getColumn(2).setResizable(false);
        tableModel = (DefaultTableModel) table.getModel();
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        btnRelatorio = new JButton("GERAR GABARITO");
        btnRelatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ReportsFactory().reportGabarito();
                } catch (Exception ex) {
                    Logger.getLogger(DialogGerenciarPergunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnRelatorio.setBounds(778, 116, 125, 23);
        getContentPane().add(btnRelatorio);

        btnAtualizarTabela = new JButton("ATUALIZAR TABELA");
        btnAtualizarTabela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTable();
                montarTable();
            }
        });
        btnAtualizarTabela.setBounds(765, 150, 153, 23);
        getContentPane().add(btnAtualizarTabela);

        montarTable();

        setVisible(true);
    }

    private void montarTable() {

        try {
            List<Pergunta> lista;
            lista = facade.listarPergunta();
            String status = null;
            for (Pergunta p : lista) {
                if (p.getStatus()) {
                    status = "ATIVADA";
                }
                if (!p.getStatus()) {
                    status = "DESATIVADA";
                }
                tableModel.addRow(new Object[]{p.getQuestao(), p.getNivel(), p.getArea(), status});
            }
        } catch (Exception ex) {
            Logger.getLogger(DialogGerenciarPergunta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_MONTAR_TABELA));
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
            JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_TABELA_VAZIA));
            return;
        }
        if (linha == -1) {
            JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_SELECIONE_UMA_QUESTAO));
            return;
        }
        String questao = table.getValueAt(linha, 0).toString();
        try {
            Pergunta p = facade.buscarPergunta(questao);
            Alternativa a = facade.buscarAlternativa(p.getId());
            int x = JOptionPane.showConfirmDialog(rootPane, "VOCÊ  TEM CERTEZA QUE REALMENTE DESEJA EDITAR A QUESTÃO? ", "EDITAR PERGUNTA", JOptionPane.OK_CANCEL_OPTION);
            if (x == JOptionPane.OK_OPTION) {
                new DialogEditarPergunta(p, a);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(DialogGerenciarPergunta.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE_QUESTION));
            e.printStackTrace();
        }
    }
}
