package br.com.elearning.praticas.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class DialogSobre extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JPanel panel;
    private JLabel lblDesenvolvedores;
    private JLabel lblWandersonFelipeF;
    private JLabel lblRicksonEllenMenezes;
    private JLabel lblSidneyMedeiros;
    private JLabel lblElearning;
    private JLabel lblImg;

    /**
     * Launch the application.
     */
    /**
     * 
     * @param args - Array de String
     */
    public static void main(String[] args) {
        new DialogSobre();
    }

    /**
     * Create the dialog.
     */
    public DialogSobre() {
        setSize(450, 300);
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("SOBRE");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(35, 30, 370, 207);
        contentPanel.add(panel);
        panel.setLayout(null);

        lblDesenvolvedores = new JLabel("DESENVOLVEDORES:");
        lblDesenvolvedores.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDesenvolvedores.setBounds(119, 11, 127, 20);
        panel.add(lblDesenvolvedores);

        lblWandersonFelipeF = new JLabel("WANDERSON FELIPE F. PEREIRA");
        lblWandersonFelipeF.setBounds(102, 29, 160, 14);
        panel.add(lblWandersonFelipeF);

        lblRicksonEllenMenezes = new JLabel("RICKSON ELLEN MENEZES");
        lblRicksonEllenMenezes.setBounds(120, 42, 127, 14);
        panel.add(lblRicksonEllenMenezes);

        lblSidneyMedeiros = new JLabel("SIDNEY MEDEIROS");
        lblSidneyMedeiros.setBounds(133, 54, 96, 14);
        panel.add(lblSidneyMedeiros);

        lblElearning = new JLabel("\u00A9 2015-2016 E-LEARNING                  VERSAO 2.17");
        lblElearning.setBounds(112, 182, 258, 14);
        panel.add(lblElearning);

        lblImg = new JLabel("             IMG");
        lblImg.setIcon(new ImageIcon("src\\res\\logo_sobre.png"));
        lblImg.setBounds(130, 76, 96, 97);
        panel.add(lblImg);

        setVisible(true);
    }
}
