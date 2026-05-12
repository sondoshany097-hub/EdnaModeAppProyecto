package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del menú de la aplicación.
 * Permite acceder a los diferentes módulos del sistema:
 * clientes, trajes, citas, talleres y cierre de sesión.
 * Actúa como punto central de navegación entre vistas.
*/
public class MainView extends JFrame {

	/**
	 * Constructor del menú principal.
	 * Inicializa la ventana y carga los componentes gráficos.
	*/
    public MainView() {
        setTitle("Edna Moda - Main Menu");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();
    }
    
    /**
    Configura las propiedades básicas de la ventana principal.
    */
    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    /**
     * Inicializa y organiza los componentes gráficos del menú,
     * incluyendo botones de navegación hacia los distintos módulos.
    */
    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color darkRed = new Color(140, 40, 40);

        JLabel lblTitle = new JLabel("Menu Principal");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 30));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(242, 64, 235, 40);
        getContentPane().add(lblTitle);

        JButton btnClientes = new JButton("Clientes");
        JButton btnTrajes = new JButton("Trajes");
        JButton btnCitas = new JButton("Citas");
        JButton btnTalleres = new JButton("Talleres");
        JButton btnLogout = new JButton("Logout");

        styleButton(btnClientes, darkGreen, Color.WHITE);
        styleButton(btnTrajes, darkGreen, Color.WHITE);
        styleButton(btnCitas, darkGreen, Color.WHITE);
        styleButton(btnTalleres, darkGreen, Color.WHITE);
        styleButton(btnLogout, darkRed, Color.WHITE);

        btnClientes.setBounds(230, 242, 220, 45);
        btnTrajes.setBounds(230, 185, 220, 45);
        btnCitas.setBounds(230, 128, 220, 45);
        btnTalleres.setBounds(230, 300, 220, 45);
        btnLogout.setBounds(230, 357, 220, 45);

        getContentPane().add(btnClientes);
        getContentPane().add(btnTrajes);
        getContentPane().add(btnCitas);
        getContentPane().add(btnTalleres);
        getContentPane().add(btnLogout);

        btnClientes.addActionListener(e -> {
            new ClientesView().setVisible(true);
            dispose();
        });

        btnTrajes.addActionListener(e -> {
            new TrajesView().setVisible(true);
            dispose();
        });

        btnCitas.addActionListener(e -> {
            new CitasView().setVisible(true);
            dispose();
        });

        btnTalleres.addActionListener(e -> {
            new TalleresView().setVisible(true);
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }
    
    /**
     * Aplica estilos visuales personalizados a los botones.
     * @param button Botón a estilizar
     * @param background Color de fondo
     * @param foreground Color del texto
    */
    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(foreground);
        button.setBackground(background);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}