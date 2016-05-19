/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elearning.praticas.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Felipe
 */
public class LoadingScreen extends JFrame {

    int i = 0;
    private Timer timer;
    private JProgressBar progressBar;
    private JPanel contentPane;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new LoadingScreen();
    }

    public LoadingScreen() {
        setTitle("Carregando E-Learning");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 350);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\res\\miniLogo.png"));

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setForeground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (i == 100) {
                    timer.stop();
                } else {
                    i++;
                    progressBar.setValue(i);
                }
            }
        });

        progressBar = new JProgressBar(1, 100);
        progressBar.setString("");
        progressBar.setBounds(0, 344, 300, 8);
        contentPane.add(progressBar);
        progressBar.setBackground(new Color(255, 255, 255));
        progressBar.setForeground(new Color(11, 86, 130));
        progressBar.setStringPainted(true);

        lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        lblNewLabel_1.setIcon(new ImageIcon("src\\res\\layout_loader.jpg"));
        lblNewLabel_1.setBounds(0, 0, 300, 350);
        contentPane.add(lblNewLabel_1);

        timer.start();

        setVisible(true);
    }
}
