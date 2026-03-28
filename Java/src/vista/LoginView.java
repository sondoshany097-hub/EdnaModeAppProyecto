/**
 * 
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */
public class LoginView extends JFrame {
	private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Edna Mode - Gestión de citas");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarVentana();
        inicializarComponentes();
    }

    private void inicializarVentana() {
      
        getContentPane().setLayout(null);

       
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    private void inicializarComponentes() {
        Color oliveGreen = new Color(85, 107, 47);
        Color oliveHover = new Color(70, 90, 35);

        // ===== Logo =====
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/Logo.jpeg"));
        Image img = logoIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(140, 20, 220, 220); // x, y, width, height
        getContentPane().add(lblLogo);

        // ===== Title =====
        JLabel lblTitle = new JLabel("Edna Mode - Gestión de citas");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(40, 40, 40));
        lblTitle.setBounds(80, 250, 350, 30);
        getContentPane().add(lblTitle);

        // ===== User Label =====
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblUser.setForeground(new Color(50, 50, 50));
        lblUser.setBounds(60, 320, 117, 30);
        getContentPane().add(lblUser);

        // ===== User Field =====
        txtUser = new JTextField();
        txtUser.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtUser.setBounds(60, 360, 360, 45);
        getContentPane().add(txtUser);

        // ===== Password Label =====
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblPassword.setForeground(new Color(50, 50, 50));
        lblPassword.setBounds(60, 430, 150, 30);
        getContentPane().add(lblPassword);

        // ===== Password Field =====
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtPassword.setBounds(60, 470, 360, 45);
        getContentPane().add(txtPassword);

        // ===== Login Button =====
        btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(oliveGreen);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(60, 538, 360, 51);
        getContentPane().add(btnLogin);
        btnLogin.setBackground(new Color(85, 107, 47));
        btnLogin.setForeground(Color.WHITE); 
        btnLogin.setOpaque(true);
        btnLogin.setContentAreaFilled(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);

        // Hover effect
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(oliveHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(oliveGreen);
            }
        });

        btnLogin.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });
    }
}

	