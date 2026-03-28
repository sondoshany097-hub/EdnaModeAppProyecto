package vista;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

/**
 * Clase principal que representa el panel de inicio de la aplicación.
 */
public class MainView extends JFrame {

    // Constructor
    public MainView() {
        setTitle("Edna Moda - Panel principal"); // Título de la ventana
        setSize(700, 500);                        // Tamaño de ventana
        setLocationRelativeTo(null);              // Centrar ventana en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra app al salir
        setResizable(false);                      // No permitir redimensionar

        initWindow();      // Configuración básica de la ventana
        initComponents();  // Inicialización de todos los componentes
    }

    // Configuración básica de la ventana
    private void initWindow() {
        getContentPane().setLayout(null);          // Layout absoluto
        getContentPane().setBackground(Color.WHITE); // Fondo blanco
    }

    // Inicialización de componentes gráficos
    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);  // Color verde oscuro para botones
        Color textColor = new Color(40, 40, 40);   // Color de texto
        Color lightGray = new Color(245, 245, 245);// Color gris claro

        // ===== Logo =====
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/Logo.jpeg"));
        Image img = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setBounds(290, 20, 120, 120);
        getContentPane().add(lblLogo);

        // ===== Título de bienvenida =====
        JLabel lblTitle = new JLabel("Bienvenida a Edna Moda");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(190, 155, 350, 35);
        getContentPane().add(lblTitle);

        // ===== Botones de navegación =====
        JButton btnClientes = createButton("Clientes", 250, 220, darkGreen);
        JButton btnCitas = createButton("Citas", 250, 270, darkGreen);
        JButton btnTalleres = createButton("Talleres", 250, 320, darkGreen);
        JButton btnTrajes = createButton("Trajes", 250, 370, darkGreen);
        JButton btnCerrarSesion = createButton("Cerrar sesión", 250, 420, new Color(120, 40, 40));

        getContentPane().add(btnClientes);
        getContentPane().add(btnCitas);
        getContentPane().add(btnTalleres);
        getContentPane().add(btnTrajes);
        getContentPane().add(btnCerrarSesion);

        // ===== Acciones de botones =====
        btnClientes.addActionListener(e -> {
            new ClientesView().setVisible(true);
            dispose(); // Cierra esta ventana
        });

        btnCitas.addActionListener(e -> {
            new CitasView().setVisible(true);
            dispose();
        });

        btnTalleres.addActionListener(e -> {
            new TalleresView().setVisible(true);
            dispose();
        });

        btnTrajes.addActionListener(e -> {
            new TrajesView().setVisible(true);
            dispose();
        });

        btnCerrarSesion.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }

    // Método auxiliar para crear botones con estilo uniforme
    private JButton createButton(String text, int x, int y, Color bgColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 38);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia cursor al pasar por encima
        return button;
    }
}