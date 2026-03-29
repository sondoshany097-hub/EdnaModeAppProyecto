package vista;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TrajesView extends JFrame {

    private JTable tableTrajes;
    private DefaultTableModel tableModel;

    public TrajesView() {
        setTitle("Edna Moda - Trajes"); // Título de la ventana
        setSize(900, 560); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al cerrar la ventana
        setResizable(false); // Evita que el usuario cambie el tamaño

        initWindow(); // Configuración básica del contenedor
        initComponents(); // Inicializa los componentes de la UI
    }

    private void initWindow() {
        getContentPane().setLayout(null); // Usando layout absoluto (no recomendable para escalabilidad)
        getContentPane().setBackground(Color.WHITE); // Fondo blanco
    }

    private void initComponents() {
        // Colores personalizados para la UI
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);

        // ===== Título =====
        JLabel lblTitle = new JLabel("Gestión de Trajes");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 300, 35);
        getContentPane().add(lblTitle);

        // ===== Tabla =====
        String[] columns = {"ID", "Cliente", "Nombre del traje", "Estado"};
        // Hacemos la tabla no editable
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el usuario edite directamente la tabla
            }
        };
        tableTrajes = new JTable(tableModel);
        tableTrajes.setRowHeight(28);
        tableTrajes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableTrajes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tableTrajes);
        scrollPane.setBounds(30, 80, 820, 300);
        getContentPane().add(scrollPane);

        // ===== Botones =====
        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(130, 420, 130, 40);
        btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(darkGreen);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBorderPainted(false); // Sin borde
        getContentPane().add(btnNuevo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(290, 420, 130, 40);
        btnEditar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(gold);
        btnEditar.setFocusPainted(false);
        btnEditar.setBorderPainted(false);
        getContentPane().add(btnEditar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(450, 420, 130, 40);
        btnBorrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnBorrar.setForeground(Color.WHITE);
        btnBorrar.setBackground(new Color(140, 40, 40));
        btnBorrar.setFocusPainted(false);
        btnBorrar.setBorderPainted(false);
        getContentPane().add(btnBorrar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(610, 420, 130, 40);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setForeground(new Color(255, 255, 255));
        btnVolver.setBackground(darkGreen);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false); // Mantener estilo uniforme
        getContentPane().add(btnVolver);

        // ===== Acciones de botones =====
        btnNuevo.addActionListener(e -> {
            // Abrir formulario para crear nuevo traje
            new TrajeFormView().setVisible(true);
            dispose(); // Cierra la ventana actual
        });

        btnEditar.addActionListener(e -> {
            int row = tableTrajes.getSelectedRow(); // Fila seleccionada
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un traje para editar.");
            } else {
                // Aquí podrías pasar los datos de la fila al formulario
                new TrajeFormView().setVisible(true);
                dispose();
            }
        });

        btnBorrar.addActionListener(e -> {
            int row = tableTrajes.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un traje para borrar.");
            } else {
                // Confirmación antes de borrar
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que deseas borrar este traje?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row); // Elimina la fila de la tabla
                }
            }
        });

        btnVolver.addActionListener(e -> {
            // Volver a la ventana principal
            new MainView().setVisible(true);
            dispose();
        });
    }
}
