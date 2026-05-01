package vista;

import Controlador.LoginController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Ventana de inicio de sesión de la aplicación.
 * Permite al usuario autenticarse introduciendo su nombre de usuario
 * y contraseña. En caso de éxito, accede a la ventana principal del sistema.
 * Forma parte de la capa de vista y delega la validación al controlador.
*/
public class LoginView extends JFrame {

	/** Campo de texto para introducir el nombre de usuario */
    private JTextField txtUser;
    
    /** Campo de contraseña para introducir la clave de acceso */
    private JPasswordField txtPassword;
    
    /** Botón para iniciar sesión */
    private JButton btnLogin;

    /** Controlador encargado de validar las credenciales */
    private LoginController loginController;

    /**
     * Constructor de la ventana de login.
     * Inicializa la interfaz gráfica y sus componentes.
    */
    public LoginView() {
        loginController = new LoginController();

        setTitle("Edna Mode - Gestión de citas");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarVentana();
        inicializarComponentes();
    }
    
    /**
     * Configura las propiedades básicas de la ventana.
    */
    private void inicializarVentana() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));
    }
    /**
     * Inicializa y organiza los componentes gráficos de la interfaz,
     * incluyendo campos de texto, etiquetas, botón e imagen del logo.
    */
    private void inicializarComponentes() {
        Color oliveGreen = new Color(85, 107, 47);
        Color oliveHover = new Color(70, 90, 35);

        URL imageUrl = getClass().getResource("/images/Logo.jpeg");
        if (imageUrl != null) {
            ImageIcon logoIcon = new ImageIcon(imageUrl);
            Image img = logoIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
            JLabel lblLogo = new JLabel(new ImageIcon(img));
            lblLogo.setBounds(140, 20, 220, 220);
            getContentPane().add(lblLogo);
        }

        JLabel lblTitle = new JLabel("Edna Mode - Gestión de citas");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(40, 40, 40));
        lblTitle.setBounds(80, 250, 350, 30);
        getContentPane().add(lblTitle);

        JLabel lblUser = new JLabel("Usuario");
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblUser.setForeground(new Color(50, 50, 50));
        lblUser.setBounds(60, 320, 150, 30);
        getContentPane().add(lblUser);

        txtUser = new JTextField();
        txtUser.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtUser.setBounds(60, 360, 360, 45);
        getContentPane().add(txtUser);

        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblPassword.setForeground(new Color(50, 50, 50));
        lblPassword.setBounds(60, 430, 150, 30);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtPassword.setBounds(60, 470, 360, 45);
        getContentPane().add(txtPassword);

        btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(oliveGreen);
        btnLogin.setOpaque(true);
        btnLogin.setContentAreaFilled(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(60, 540, 360, 50);
        getContentPane().add(btnLogin);

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(oliveHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(oliveGreen);
            }
        });

        btnLogin.addActionListener(e -> iniciarSesion());
    }

    /**
     * Gestiona el proceso de inicio de sesión.
     * Valida que los campos no estén vacíos y delega la autenticación
     * al controlador. Si las credenciales son correctas, abre la ventana principal.
    */
    private void iniciarSesion() {
        String username = txtUser.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        boolean acceso = loginController.iniciarSesion(username, password);

        if (acceso) {
            new MainView().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}
	