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

public class EditarUser extends JDialog {

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
    private JButton btnEditar;
    private Facade facade;
    public static final String TIPO = "J";
    private JPanel panelLogin;
    private JLabel lblLogin;
    private JTextField tfLogin;
    private JLabel lblSenha_1;
    private JPasswordField pfSenha;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new EditarUser();
    }

    /**
     * Create the dialog.
     */
    public EditarUser() {
        setSize(335, 257);
        setTitle("EDITAR USUARIO");
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
        panel.setVisible(false);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
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

//        panelLogin = new JPanel();
//        panelLogin.setBounds(0, 0, 263, 145);
//        panel.add(panelLogin);
//        panelLogin.setLayout(null);
//
//        lblLogin = new JLabel("LOGIN:");
//        lblLogin.setBounds(10, 23, 46, 14);
//        panelLogin.add(lblLogin);
//
//        tfLogin = new JTextField();
//        tfLogin.setBounds(62, 20, 191, 20);
//        panelLogin.add(tfLogin);
//        tfLogin.setColumns(10);
//
//        lblSenha_1 = new JLabel("SENHA:");
//        lblSenha_1.setBounds(10, 55, 46, 14);
//        panelLogin.add(lblSenha_1);
//
//        pfSenha = new JPasswordField();
//        pfSenha.setBounds(72, 45, 181, 20);
//        panelLogin.add(pfSenha);

        btnEditar = new JButton("ENTRAR");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario user = null;
                    if (btnEditar.getText().equals("ENTRAR")) {
                        user = facade.buscarUsuario(tfLogin.getText(),
                                String.valueOf(pfSenha.getPassword()));
                        btnEditar.setText("EDITAR");
                        panelLogin.setVisible(false);
                        panel.setVisible(true);
                    }
                    if (btnEditar.getText().equals("EDITAR")) {
                        user.setNome(tfNome.getText());
                        user.setNick(tfUsername.getText());
                        user.setSenha(String.valueOf(tfSenha.getPassword()));
                        facade.editarUsuario(user);
                        panel.setVisible(false);
                        panelLogin.setVisible(true);
                        JOptionPane
                                .showMessageDialog(
                                        EditarUser.this,
                                        PropertiesUtils
                                        .getMsgValue(PropertiesUtils.MSG_SUCCEED_UPDATE_USER));
                        dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(EditarUser.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(
                            EditarUser.this,
                            PropertiesUtils
                            .getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
                }
            }
        });
        btnEditar.setBounds(115, 191, 102, 23);
        contentPanel.add(btnEditar);
        
                panelLogin = new JPanel();
		panelLogin.setBounds(32, 30, 263, 145);
		contentPanel.add(panelLogin);
		panelLogin.setLayout(null);
				
		lblLogin = new JLabel("LOGIN:");
		lblLogin.setBounds(10, 23, 46, 14);
		panelLogin.add(lblLogin);
				
		tfLogin = new JTextField();
		tfLogin.setBounds(62, 20, 191, 20);
		panelLogin.add(tfLogin);
		tfLogin.setColumns(10);
				
		lblSenha_1 = new JLabel("SENHA:");
		lblSenha_1.setBounds(10, 55, 46, 14);
		panelLogin.add(lblSenha_1);
				
                pfSenha = new JPasswordField();
		pfSenha.setBounds(72, 45, 181, 20);
		panelLogin.add(pfSenha);

        setVisible(true);
    }
}
