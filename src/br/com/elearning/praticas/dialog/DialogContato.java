package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.util.Email;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class DialogContato extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField tfFrom;
    private JScrollPane scrollPane;
    private JTextArea tfConteudo;
    private JTextField tfAssunto;
    private JLabel lblAssunto;
    private JLabel lblFrom;
    private JPanel panel;
    private JButton btnEnviar;
    private Facade facade;

    /**
     * Launch the application.
     */
    /**
     * 
     * @param args - Array de String
     */
    public static void main(String[] args) {
        new DialogContato();
    }

    /**
     * Create the dialog.
     */
    public DialogContato() {
        setSize(552, 387);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        setTitle("CONTATE-NOS");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        this.facade = new Facade();

        panel = new JPanel();
        panel.setBounds(10, 11, 526, 304);
        contentPanel.add(panel);
        panel.setLayout(null);

        lblFrom = new JLabel("USERNAME:");
        lblFrom.setBounds(10, 11, 64, 14);
        panel.add(lblFrom);

        tfFrom = new JTextField();
        tfFrom.setBounds(74, 8, 210, 20);
        panel.add(tfFrom);
        tfFrom.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CONTEUDO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        scrollPane.setBounds(10, 39, 506, 254);
        panel.add(scrollPane);

        tfConteudo = new JTextArea();
        tfConteudo.setLineWrap(true);
        scrollPane.setViewportView(tfConteudo);

        lblAssunto = new JLabel("ASSUNTO:");
        lblAssunto.setBounds(294, 11, 56, 14);
        panel.add(lblAssunto);

        tfAssunto = new JTextField();
        tfAssunto.setBounds(355, 8, 161, 20);
        panel.add(tfAssunto);
        tfAssunto.setColumns(10);

        btnEnviar = new JButton("ENVIAR");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario user = facade.buscarUsuarioEmail(tfFrom.getText());
                    new Email().enviarEmail(user, tfAssunto.getText(), tfConteudo.getText());
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(DialogContato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnEnviar.setBounds(226, 325, 89, 23);
        contentPanel.add(btnEnviar);
        setVisible(true);
    }
}
