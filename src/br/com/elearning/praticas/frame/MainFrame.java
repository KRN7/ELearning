package br.com.elearning.praticas.frame;

import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JPanel panelInicio;
    private JPanel panelLogin;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblCadastrarse;
    private JTextField tfLogin;
    private JPanel paneImg;
    private JButton btnEntrar;
    private JPasswordField tfSenha;
    private JMenuItem mntmIniciarSimulado;
    private JMenuItem mntmEditarConta;
    private JMenuItem mntmVisualizarHistorico;
    private JMenuItem mntmSair;
    private JSeparator separator;
    private JSeparator separator_1;
    private JMenuBar menuBar;
    private JMenu mnNomeUser;
    private Facade facade;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new MainFrame();
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 521);
        setLocationRelativeTo(null);
        setResizable(false);

        facade = new Facade();

        menuBar = new JMenuBar();
        menuBar.setVisible(false);
        setJMenuBar(menuBar);

        mnNomeUser = new JMenu("");
        menuBar.add(mnNomeUser);

        mntmIniciarSimulado = new JMenuItem("INICIAR SIMULADO");
        mnNomeUser.add(mntmIniciarSimulado);

        separator = new JSeparator();
        mnNomeUser.add(separator);

        mntmEditarConta = new JMenuItem("EDITAR CONTA");
        mnNomeUser.add(mntmEditarConta);

        mntmVisualizarHistorico = new JMenuItem("VISUALIZAR HISTORICO");
        mnNomeUser.add(mntmVisualizarHistorico);

        separator_1 = new JSeparator();
        mnNomeUser.add(separator_1);

        mntmSair = new JMenuItem("SAIR");
        mntmSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuBar.setVisible(false);
                panelInicio.setVisible(false);
                panelLogin.setVisible(true);
            }
        });
        mnNomeUser.add(mntmSair);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelLogin = new JPanel();
        panelLogin.setBackground(Color.WHITE);
        panelLogin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panelLogin.setBounds(289, 111, 382, 220);
        contentPane.add(panelLogin);
        panelLogin.setLayout(null);

        lblLogin = new JLabel("LOGIN:");
        lblLogin.setBounds(10, 82, 46, 14);
        panelLogin.add(lblLogin);

        lblSenha = new JLabel("SENHA:");
        lblSenha.setBounds(10, 107, 46, 14);
        panelLogin.add(lblSenha);

        tfLogin = new JTextField();
        tfLogin.setBounds(66, 79, 136, 20);
        panelLogin.add(tfLogin);
        tfLogin.setColumns(10);

        paneImg = new JPanel();
        paneImg.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        paneImg.setBounds(217, 11, 155, 198);
        panelLogin.add(paneImg);

        btnEntrar = new JButton("ENTRAR");
        btnEntrar = new JButton("ENTRAR");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario user = null;

                try {
                    user = facade.buscarUsuario(tfLogin.getText(), String.valueOf(tfSenha.getPassword()));
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (tfLogin.getText().equals("") || String.valueOf(tfSenha.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(MainFrame.this, "OBRIGATORIO O PREENCHIMENTO DOS CAMPOS LOGIN E SENHA, PARA FAZER LOGIN!", "CAMPO(S) VAZIOS!", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (user == null) {
                        JOptionPane.showMessageDialog(MainFrame.this, "LOGIN OU SENHA INVALIDOS!", "ERROR AO FAZER LOGIN", JOptionPane.WARNING_MESSAGE);
                    }
                    if (user != null) {
                        menuBar.setVisible(true);
                        panelLogin.setVisible(false);
                        mnNomeUser.setText(user.getNome());
                        tfLogin.setText("");
                        tfSenha.setText("");
                    }
                }
            }
        });
        btnEntrar.setBounds(69, 171, 89, 23);
        panelLogin.add(btnEntrar);

        tfSenha = new JPasswordField();
        tfSenha.setBounds(66, 104, 136, 20);
        panelLogin.add(tfSenha);

        panelInicio = new JPanel();
        panelInicio.setBackground(Color.WHITE);
        panelInicio.setBounds(0, 0, 944, 493);
        contentPane.add(panelInicio);
        panelInicio.setLayout(null);

        lblCadastrarse = new JLabel("CADASTRAR-SE");
        lblCadastrarse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "CADASTRO DO JOGADOR AKI!!");
            }
        });
        lblCadastrarse.setBounds(843, 468, 91, 14);
        panelInicio.add(lblCadastrarse);

        setVisible(true);
    }
}
