package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana para la gestión de Talleres en Edna Moda.
 */
public class TalleresView extends JFrame {

    private JTable tableTalleres;        // Tabla que muestra los talleres
    private DefaultTableModel tableModel; // Modelo de datos de la tabla

    // Constructor
    public TalleresView() {
        setTitle("Edna Moda - Talleres");
        setSize(850, 550);               // Tamaño de la ventana
        setLocationRelativeTo(null);      // Centrar ventana en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);             // No permitir redimensionar

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
        Color darkGreen = new Color(85, 107, 47);  // Botones nuevos
        Color gold = new Color(201, 169, 97);      // Botones editar
        Color lightGray = new Color(245, 245, 245);// Botón volver
        Color textColor = new Color(40, 40, 40);   // Color de texto

        // ===== Título =====
        JLabel lblTitle = new JLabel("Gestión de Talleres");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 300, 35);
        getContentPane().add(lblTitle);

        // ===== Tabla de talleres =====
        String[] columns = {"ID", "Nombre de sala", "Tipo de sala"};
        tableModel = new DefaultTableModel(columns, 0); // Inicialmente sin filas
        tableTalleres = new JTable(tableModel);
        tableTalleres.setRowHeight(28);
        tableTalleres.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableTalleres.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tableTalleres);
        scrollPane.setBounds(30, 80, 780, 280);
        getContentPane().add(scrollPane);

        // ===== Botones de acciones =====
        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(130, 410, 130, 40);
        btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnNuevo.setForeground(new Color(255, 255, 255));
        btnNuevo.setBackground(darkGreen);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBorderPainted(false);
        getContentPane().add(btnNuevo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(290, 410, 130, 40);
        btnEditar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEditar.setForeground(new Color(255, 255, 255));
        btnEditar.setBackground(gold);
        btnEditar.setFocusPainted(false);
        btnEditar.setBorderPainted(false);
        getContentPane().add(btnEditar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(450, 410, 130, 40);
        btnBorrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnBorrar.setForeground(new Color(255, 255, 255));
        btnBorrar.setBackground(new Color(140, 40, 40)); // rojo oscuro
        btnBorrar.setFocusPainted(false);
        btnBorrar.setBorderPainted(false);
        getContentPane().add(btnBorrar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(612, 410, 130, 40);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setForeground(new Color(255, 255, 255));
        btnVolver.setBackground(darkGreen);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false); // Mantener estilo uniforme
        getContentPane().add(btnVolver);

        // ===== Acciones de botones =====
        btnNuevo.addActionListener(e -> {
            new TallerFormView().setVisible(true); // Abrir formulario de nuevo taller
            dispose();                              // Cierra la ventana actual
        });

        btnEditar.addActionListener(e -> {
            int row = tableTalleres.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un taller para editar.");
            } else {
                new TallerFormView().setVisible(true); // Abrir formulario de edición
                dispose();
            }
        });

        btnBorrar.addActionListener(e -> {
            int row = tableTalleres.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un taller para borrar.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que deseas borrar este taller?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row); // Borra la fila seleccionada
                }
            }
        });

        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true); // Volver al panel principal
            dispose();
        });
    }

    // Método auxiliar para aplicar estilo uniforme a botones
    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

