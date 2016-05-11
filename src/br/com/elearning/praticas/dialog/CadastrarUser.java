package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CadastrarUser extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField tfNome;
    private JTextField tfUsername;
    private JPasswordField tfSenha;
    private JTextField tfEmail;
    private JPanel panel;
    private JLabel lblNome;
    private JLabel lblUsername;
    private JLabel lblSenha;
    private JLabel lblEmail;
    private JButton btnCadastrar;
    private Facade facade;
    public static final String TIPO = "J";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new CadastrarUser();
    }

    /**
     * Create the dialog.
     */
    public CadastrarUser() {
        setSize(335, 257);
        setTitle("CADASTRAR USUARIO");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        this.facade = new Facade();

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(32, 30, 263, 145);
        contentPanel.add(panel);
        panel.setLayout(null);

        lblNome = new JLabel("NOME:");
        lblNome.setBounds(10, 24, 33, 14);
        panel.add(lblNome);

        lblUsername = new JLabel("USERNAME:");
        lblUsername.setBounds(10, 49, 58, 14);
        panel.add(lblUsername);

        lblSenha = new JLabel("SENHA:");
        lblSenha.setBounds(10, 74, 58, 14);
        panel.add(lblSenha);

        lblEmail = new JLabel("EMAIL:");
        lblEmail.setBounds(10, 103, 39, 14);
        panel.add(lblEmail);

        tfNome = new JTextField();
        tfNome.setBounds(48, 21, 205, 20);
        panel.add(tfNome);
        tfNome.setColumns(10);

        tfUsername = new JTextField();
        tfUsername.setBounds(76, 46, 177, 20);
        panel.add(tfUsername);
        tfUsername.setColumns(10);

        tfSenha = new JPasswordField();
        tfSenha.setBounds(48, 71, 205, 20);
        panel.add(tfSenha);

        tfEmail = new JTextField();
        tfEmail.setBounds(48, 99, 205, 20);
        panel.add(tfEmail);
        tfEmail.setColumns(10);

        btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario user = new Usuario();
                    user.setNome(tfNome.getText());
                    user.setNick(tfUsername.getText());
                    user.setSenha(String.valueOf(tfSenha.getPassword()));
                    user.setEmail(tfEmail.getText());
                    user.setTipo(TIPO);
                    facade.salvarUsuario(user);

                    JOptionPane.showMessageDialog(CadastrarUser.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_SUCCEED_ADD_USER));
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(CadastrarUser.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CadastrarUser.this, PropertiesUtils.getMsgValue(PropertiesUtils.MSG_ERRO_ADD_USER));
                }
            }
        });
        btnCadastrar.setBounds(115, 191, 102, 23);
        contentPanel.add(btnCadastrar);

        setVisible(true);
    }
}
