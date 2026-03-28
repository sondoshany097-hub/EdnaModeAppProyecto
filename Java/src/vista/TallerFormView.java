/**
 * 
 */
package vista;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana de formulario para agregar o editar un Taller.
 */
public class TallerFormView extends JFrame {

    private JTextField txtId;          // Campo para el ID del taller
    private JTextField txtNombreSala;  // Campo para el nombre de la sala
    private JComboBox<String> cbTipoSala; // ComboBox para seleccionar el tipo de sala

    // Constructor
    public TallerFormView() {
        setTitle("Edna Moda - Formulario de Taller");
        setSize(580, 430);               // Tamaño de ventana
        setLocationRelativeTo(null);      // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);             // No permitir redimensionar

        initWindow();      // Configuración de la ventana
        initComponents();  // Inicialización de componentes
    }

    // Configuración básica de la ventana
    private void initWindow() {
        getContentPane().setLayout(null);          // Layout absoluto
        getContentPane().setBackground(Color.WHITE); // Fondo blanco
    }

    // Inicialización de los componentes del formulario
    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);

        // ===== Título =====
        JLabel lblTitle = new JLabel("Formulario de Taller");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(150, 30, 280, 35);
        getContentPane().add(lblTitle);

        // ===== ID del taller =====
        JLabel lblId = new JLabel("ID del taller:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblId.setForeground(textColor);
        lblId.setBounds(70, 110, 140, 25);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(220, 110, 230, 32);
        getContentPane().add(txtId);

        // ===== Nombre de sala =====
        JLabel lblNombreSala = new JLabel("Nombre de sala:");
        lblNombreSala.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNombreSala.setForeground(textColor);
        lblNombreSala.setBounds(70, 170, 140, 25);
        getContentPane().add(lblNombreSala);

        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(220, 170, 230, 32);
        getContentPane().add(txtNombreSala);

        // ===== Tipo de sala =====
        JLabel lblTipoSala = new JLabel("Tipo de sala:");
        lblTipoSala.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTipoSala.setForeground(textColor);
        lblTipoSala.setBounds(70, 230, 140, 25);
        getContentPane().add(lblTipoSala);

        cbTipoSala = new JComboBox<>(new String[]{
                "Diseño",
                "Costura",
                "Pruebas"
        });
        cbTipoSala.setBounds(220, 230, 230, 32);
        getContentPane().add(cbTipoSala);

        // ===== Botones =====
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(140, 310, 130, 40);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(darkGreen);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        getContentPane().add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 310, 130, 40);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setForeground(textColor);
        btnCancelar.setBackground(lightGray);
        btnCancelar.setFocusPainted(false);
        getContentPane().add(btnCancelar);

        // ===== Acciones de los botones =====
        btnGuardar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Taller guardado correctamente.");
            new TalleresView().setVisible(true); // Volver a la ventana de talleres
            dispose();                             // Cierra el formulario
        });

        btnCancelar.addActionListener(e -> {
            new TalleresView().setVisible(true); // Volver a la ventana de talleres
            dispose();                             // Cierra el formulario
        });
    }
}

