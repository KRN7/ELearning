package br.com.elearning.praticas.frame;

import br.com.elearning.praticas.dialog.DialogCadastrarUsuario;
import br.com.elearning.praticas.dialog.DialogContato;
import br.com.elearning.praticas.dialog.DialogSobre;
import br.com.elearning.praticas.dialog.DialogEditarUser;
import br.com.elearning.praticas.facade.Facade;
import br.com.elearning.praticas.model.Usuario;
import br.com.elearning.praticas.util.Criptografia;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

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
    private JPanel panelJogador;
    private JPanel panelAdmin;
    private JPanel panelLogin;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblCadastrarse;
    private JLabel lblUserImg;
    private JLabel lblImgLogo;
    private JTextField tfLogin;
    private JPanel panelImg;
    private JButton btnEntrar;
    private JPasswordField tfSenha;
    private JMenuItem mntmIniciarSimulado;
    private JMenuItem mntmEditarConta;
    private JMenuItem mntmVisualizarHistorico;
    private JMenuItem mntmSair;
    private JSeparator sep;
    private JSeparator sep1;
    private JMenuBar menuBar;
    private JMenu mnNomeUser;
    private JMenu mnSobre;
    private JMenu mnSeparador;
    private JMenuItem mntmSobre;
    private JMenuItem mntmContato;
    private Facade facade;
    private static LoadingScreen loadScreen;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            loadScreen = new LoadingScreen();
            Thread.sleep(3000);
            loadScreen.dispose();
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
        setTitle("SIMULADO - E_LEARNING");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));

        this.facade = new Facade();

        menuBar = new JMenuBar();
        menuBar.setVisible(false);
        setJMenuBar(menuBar);

        mnNomeUser = new JMenu("");
        menuBar.add(mnNomeUser);

        mnSeparador = new JMenu("|");
        mnSeparador.setEnabled(false);
        menuBar.add(mnSeparador);

        mnSobre = new JMenu("SOBRE - AJUDA");
        menuBar.add(mnSobre);

        mntmSobre = new JMenuItem("SOBRE");
        mntmSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DialogSobre();
            }
        });
        mnSobre.add(mntmSobre);

        mntmContato = new JMenuItem("CONTATE-NOS");
        mntmContato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DialogContato();
            }
        });
        mnSobre.add(mntmContato);

        mntmIniciarSimulado = new JMenuItem();
        mntmIniciarSimulado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrarPrimeiroItem();
            }
        });
        mnNomeUser.add(mntmIniciarSimulado);

        sep = new JSeparator();
        mnNomeUser.add(sep);

        mntmEditarConta = new JMenuItem("EDITAR CONTA");
        mntmEditarConta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new DialogEditarUser();
            }
        });
        mnNomeUser.add(mntmEditarConta);

        mntmVisualizarHistorico = new JMenuItem("");
        mntmVisualizarHistorico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrarSegundoItem();
            }
        });
        mnNomeUser.add(mntmVisualizarHistorico);

        sep1 = new JSeparator();
        mnNomeUser.add(sep1);

        mntmSair = new JMenuItem("SAIR");
        mntmSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuBar.setVisible(false);
                lblImgLogo.setVisible(false);
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

        panelImg = new JPanel();
        panelImg.setBackground(Color.WHITE);
        panelImg.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panelImg.setBounds(217, 11, 155, 198);
        panelLogin.add(panelImg);

        lblUserImg = new JLabel("");
        lblUserImg.setIcon(new ImageIcon("src\\res\\user_icon.png"));
        lblUserImg.setBounds(0, 0, 155, 198);
        panelImg.add(lblUserImg);

        btnEntrar = new JButton("ENTRAR");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario user = null;
                String senhaMd5 = Criptografia.md5(String.valueOf(tfSenha.getPassword()));

                try {
                    user = facade.buscarUsuario(tfLogin.getText(), senhaMd5);
                    System.out.println(user);
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
                        verificarUser(user);
                    }
                }
            }
        });
        btnEntrar.setBounds(69, 171, 89, 23);
        panelLogin.add(btnEntrar);

        tfSenha = new JPasswordField();
        tfSenha.setBounds(66, 104, 136, 20);
        panelLogin.add(tfSenha);

        panelJogador = new JPanel();
        panelJogador.setBackground(Color.WHITE);
        panelJogador.setBounds(0, 0, 944, 493);
        contentPane.add(panelJogador);
        panelJogador.setLayout(null);

        panelAdmin = new JPanel();
        panelAdmin.setBackground(Color.WHITE);
        panelAdmin.setBounds(0, 0, 944, 493);
        contentPane.add(panelAdmin);
        panelAdmin.setLayout(null);

        lblCadastrarse = new JLabel("CADASTRAR-SE");
        lblCadastrarse.setForeground(Color.BLUE);
        lblCadastrarse.setBounds(75, 198, 77, 14);
        panelLogin.add(lblCadastrarse);
        lblCadastrarse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DialogCadastrarUsuario();
            }
        });

        lblImgLogo = new JLabel("");
        lblImgLogo.setIcon(new ImageIcon("src\\res\\mainimg.png"));
        lblImgLogo.setBounds(114, 35, 829, 405);
        panelJogador.add(lblImgLogo);
        lblImgLogo.setVisible(false);

        setVisible(true);
    }

    public void verificarUser(Usuario user) {
        if (user.getTipo().equalsIgnoreCase("j")) {
            menuBar.setVisible(true);
            mntmIniciarSimulado.setText("NOVO SIMULADO");
            mntmVisualizarHistorico.setText("VISUALIZAR HISTORICO");
            panelLogin.setVisible(false);
            mnNomeUser.setText(user.getNome().toUpperCase() + " - JOGADOR");
            tfLogin.setText("");
            tfSenha.setText("");
            lblImgLogo.setVisible(true);
        }
        if (user.getTipo().equalsIgnoreCase("a")) {
            menuBar.setVisible(true);
            mntmIniciarSimulado.setText("CADASTRAR PERGUNTA");
            mntmVisualizarHistorico.setText("VISUALIZAR JOGADORES");
            panelLogin.setVisible(false);
            mnNomeUser.setText(user.getNome().toUpperCase() + " - ADMIN");
            tfLogin.setText("");
            tfSenha.setText("");
            lblImgLogo.setVisible(true);
        }
    }

    public void filtrarPrimeiroItem() {
        if (mntmIniciarSimulado.getText().equals("CADASTRAR PERGUNTA")) {
            JOptionPane.showMessageDialog(MainFrame.this, "DIALOG DE CADASTRAR PERGUNTA AKI");
        }
        if (mntmIniciarSimulado.getText().equals("NOVO SIMULADO")) {
            JOptionPane.showMessageDialog(MainFrame.this, "DIALOG DE NOVO SIMULADO AKI");
        }
    }

    public void filtrarSegundoItem() {
        if (mntmVisualizarHistorico.getText().equals("VISUALIZAR HISTORICO")) {
            JOptionPane.showMessageDialog(MainFrame.this, "VISUALIZAR APENAS O HISTORICO DO JOGADOR AKI");
        }
        if (mntmVisualizarHistorico.getText().equals("VISUALIZAR JOGADORES")) {
            JOptionPane.showMessageDialog(MainFrame.this, "VISUALIZAR JOGADORES AKI");
        }
    }
}
