package br.com.elearning.praticas.dialog;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.util.Criptografia;
import br.com.elearning.praticas.util.PropertiesUtils;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
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

public class DialogEditarUser extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField tfNome;
    private JTextField tfUsername;
    private JPasswordField tfSenha;
    private JPanel panel;
    private JLabel lblNome;
    private JLabel lblUsername;
    private JLabel lblSenha;
    private JButton btnEditar;
    private Facade facade;
    public static final String TIPO = "J";
    private JPanel panelLogin;
    private JLabel lblSenhaV;
    private JPasswordField tfSenhaV;
    private JTextField tfUsernameV;
    private JLabel lblUsernameV;
    private Usuario user = null;

    /**
     * Create the dialog.
     *
     * @param us - Usuario a ser editado.
     */
    public DialogEditarUser(Usuario us) {
        setSize(335, 257);
        setTitle("EDITAR USUARIO");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setTitle("EDITAR PERGUNTA");
        getContentPane().setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));
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
        lblNome.setBounds(10, 40, 33, 14);
        panel.add(lblNome);

        lblUsername = new JLabel("USERNAME:");
        lblUsername.setBounds(10, 65, 58, 14);
        panel.add(lblUsername);

        lblSenha = new JLabel("SENHA:");
        lblSenha.setBounds(10, 90, 37, 14);
        panel.add(lblSenha);

        tfNome = new JTextField();
        tfNome.setBounds(57, 37, 196, 20);
        panel.add(tfNome);
        tfNome.setColumns(10);

        tfUsername = new JTextField();
        tfUsername.setBounds(76, 62, 177, 20);
        panel.add(tfUsername);
        tfUsername.setColumns(10);

        tfSenha = new JPasswordField();
        tfSenha.setBounds(57, 87, 196, 20);
        panel.add(tfSenha);

        panelLogin = new JPanel();
        panelLogin.setBackground(Color.WHITE);
        panelLogin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLogin.setBounds(32, 30, 263, 145);
        contentPanel.add(panelLogin);
        panelLogin.setLayout(null);

        lblSenhaV = new JLabel("SENHA:");
        lblSenhaV.setBounds(16, 66, 46, 14);
        panelLogin.add(lblSenhaV);

        tfSenhaV = new JPasswordField();
        tfSenhaV.setBounds(84, 63, 157, 20);
        panelLogin.add(tfSenhaV);

        lblUsernameV = new JLabel("USERNAME:");
        lblUsernameV.setBounds(16, 35, 58, 14);
        panelLogin.add(lblUsernameV);

        tfUsernameV = new JTextField();
        tfUsernameV.setBounds(84, 32, 157, 20);
        panelLogin.add(tfUsernameV);
        tfUsernameV.setColumns(10);

        btnEditar = new JButton("ENTRAR");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String senhaMd5 = Criptografia.md5(String.valueOf(tfSenhaV.getPassword()));
                    if (btnEditar.getText().equals("ENTRAR")) {
                        user = facade.buscarUsuarioLogin(tfUsernameV.getText(), senhaMd5);
                        if (user != null) {
                            if (user.getNick().equals(us.getNick())) {
                                setTitle("EDITAR USUARIO - " + user.getNick());
                                btnEditar.setText("EDITAR");
                                panelLogin.setVisible(false);
                                panel.setVisible(true);
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(DialogEditarUser.this, "USERNAME OU SENHA INVALIDOS!", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                    if (btnEditar.getText().equals("EDITAR")) {
                        String newSenha = Criptografia.md5(String.valueOf(tfSenha.getPassword()));
                        user.setNome(tfNome.getText());
                        user.setNick(tfUsername.getText());
                        user.setSenha(newSenha);
                        facade.editarUsuario(user);
                        panel.setVisible(false);
                        panelLogin.setVisible(true);
                        JOptionPane
                                .showMessageDialog(DialogEditarUser.this,
                                        PropertiesUtils
                                        .getMsgValue(PropertiesUtils.MSG_SUCCEED_UPDATE_USER));
                        dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DialogEditarUser.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(DialogEditarUser.this,
                            PropertiesUtils
                            .getMsgValue(PropertiesUtils.MSG_ERRO_UPDATE));
                }
            }
        });
        btnEditar.setBounds(115, 191, 102, 23);
        getRootPane().setDefaultButton(btnEditar);
        contentPanel.add(btnEditar);

        setVisible(true);
    }
}
