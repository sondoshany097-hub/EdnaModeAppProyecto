package vista;

import javax.swing.*;
import java.awt.*;

public class ClienteFormView extends JFrame {

    // Campos de texto para ingresar información del cliente
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtSuperpoder;
    private JTextField txtColores;
    private JComboBox<String> cbTipo; // ComboBox para seleccionar tipo de cliente (HÉROE/VILLANO)

    // Constructor
    public ClienteFormView() {
        setTitle("Edna Moda - Formulario de Cliente"); // Título de la ventana
        setSize(600, 520);                              // Tamaño de la ventana
        setLocationRelativeTo(null);                    // Centrar ventana en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Cerrar app al salir
        setResizable(false);                            // No permitir redimensionar

        initWindow();     // Inicializa ventana con configuración base
        initComponents(); // Inicializa todos los componentes visuales
    }

    // Configuración básica de la ventana
    private void initWindow() {
        getContentPane().setLayout(null);      // Layout absoluto
        getContentPane().setBackground(Color.WHITE); // Fondo blanco
    }

    // Inicializa los componentes gráficos
    private void initComponents() {
        // Colores personalizados
        Color darkGreen = new Color(85, 107, 47);
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);

        // ===== Título =====
        JLabel lblTitle = new JLabel("Formulario de Cliente");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(150, 30, 300, 35);
        getContentPane().add(lblTitle);

        // ===== Campos de formulario =====

        // ID del cliente
        JLabel lblId = new JLabel("ID del cliente:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblId.setForeground(textColor);
        lblId.setBounds(70, 100, 140, 25);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(220, 100, 250, 32);
        getContentPane().add(txtId);

        // Nombre del cliente
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNombre.setForeground(textColor);
        lblNombre.setBounds(70, 150, 140, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(220, 150, 250, 32);
        getContentPane().add(txtNombre);

        // Superpoder del cliente
        JLabel lblSuperpoder = new JLabel("Superpoder:");
        lblSuperpoder.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSuperpoder.setForeground(textColor);
        lblSuperpoder.setBounds(70, 200, 140, 25);
        getContentPane().add(lblSuperpoder);

        txtSuperpoder = new JTextField();
        txtSuperpoder.setBounds(220, 200, 250, 32);
        getContentPane().add(txtSuperpoder);

        // Colores del cliente
        JLabel lblColores = new JLabel("Colores:");
        lblColores.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblColores.setForeground(textColor);
        lblColores.setBounds(70, 250, 140, 25);
        getContentPane().add(lblColores);

        txtColores = new JTextField();
        txtColores.setBounds(220, 250, 250, 32);
        getContentPane().add(txtColores);

        // Tipo de cliente: HÉROE o VILLANO
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTipo.setForeground(textColor);
        lblTipo.setBounds(70, 300, 140, 25);
        getContentPane().add(lblTipo);

        cbTipo = new JComboBox<>(new String[]{"HÉROE", "VILLANO"});
        cbTipo.setBounds(220, 300, 250, 32);
        getContentPane().add(cbTipo);

        // ===== Botones =====

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 390, 130, 42);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(darkGreen);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        getContentPane().add(btnGuardar);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 390, 130, 42);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.setBackground(darkGreen);
        btnCancelar.setFocusPainted(false);
        getContentPane().add(btnCancelar);

        // ===== Acciones de botones =====

        // Guardar cliente y volver a la vista de clientes
        btnGuardar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cliente guardado correctamente.");
            new ClientesView().setVisible(true); // Abrir lista de clientes
            dispose(); // Cierra este formulario
        });

        // Cancelar y volver a la vista de clientes
        btnCancelar.addActionListener(e -> {
            new ClientesView().setVisible(true); // Abrir lista de clientes
            dispose(); // Cierra este formulario
        });
    }
}

