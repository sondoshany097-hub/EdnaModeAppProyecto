package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ClientesView extends JFrame {

    // Componentes principales
    private JTable tableClientes;       // Tabla que muestra los clientes
    private DefaultTableModel tableModel; // Modelo de datos de la tabla
    private JTextField txtBuscar;       // Campo para buscar clientes
    private JComboBox<String> cbTipo;   // ComboBox para filtrar por tipo (HÉROE/VILLANO/Todos)

    // Constructor
    public ClientesView() {
        setTitle("Edna Moda - Clientes");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();     // Configuración básica de la ventana
        initComponents(); // Inicialización de todos los componentes
    }

    // Configuración básica de la ventana
    private void initWindow() {
        getContentPane().setLayout(null);        // Layout absoluto
        getContentPane().setBackground(Color.WHITE); // Fondo blanco
    }

    // Inicialización de componentes
    private void initComponents() {
        // Colores personalizados
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);
        Color darkRed = new Color(140, 40, 40);

        // ===== Título =====
        JLabel lblTitle = new JLabel("Gestión de Clientes");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 300, 35);
        getContentPane().add(lblTitle);

        // ===== Buscador y filtro =====
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblBuscar.setForeground(textColor);
        lblBuscar.setBounds(30, 85, 70, 25);
        getContentPane().add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(100, 85, 220, 32);
        txtBuscar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        getContentPane().add(txtBuscar);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTipo.setForeground(textColor);
        lblTipo.setBounds(350, 85, 50, 25);
        getContentPane().add(lblTipo);

        cbTipo = new JComboBox<>(new String[]{"Todos", "HÉROE", "VILLANO"});
        cbTipo.setBounds(410, 85, 150, 32);
        cbTipo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        getContentPane().add(cbTipo);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(590, 85, 120, 32);
        styleButton(btnFiltrar, darkGreen, Color.WHITE); // Aplica estilo personalizado
        getContentPane().add(btnFiltrar);

        // ===== Tabla de clientes =====
        String[] columnas = {"ID", "Nombre", "Superpoder", "Colores", "Tipo"};
        tableModel = new DefaultTableModel(columnas, 0); // Modelo sin filas iniciales
        tableClientes = new JTable(tableModel);
        tableClientes.setRowHeight(28);
        tableClientes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableClientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));




        JScrollPane scrollPane = new JScrollPane(tableClientes);
        scrollPane.setBounds(30, 141, 820, 279);
        getContentPane().add(scrollPane);

        // ===== Botones de acciones =====
        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(120, 470, 130, 40);
        styleButton(btnNuevo, darkGreen, Color.WHITE);
        getContentPane().add(btnNuevo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(280, 470, 130, 40);
        styleButton(btnEditar, gold, Color.WHITE);
        getContentPane().add(btnEditar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(440, 470, 130, 40);
        styleButton(btnBorrar, darkRed, Color.WHITE);
        getContentPane().add(btnBorrar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(new Color(255, 255, 255));
        btnVolver.setBounds(600, 470, 130, 40);
        styleButton(btnVolver, darkGreen, textColor);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        getContentPane().add(btnVolver);

        // ===== Acciones de botones =====
        btnNuevo.addActionListener(e -> {
            new ClienteFormView().setVisible(true);
            dispose();
        });

        btnEditar.addActionListener(e -> {
            int row = tableClientes.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para editar.");
            } else {
                new ClienteFormView().setVisible(true);
                dispose();
            }
        });

        btnBorrar.addActionListener(e -> {
            int row = tableClientes.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para borrar.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que deseas borrar este cliente?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row); // Borra la fila seleccionada
                }
            }
        });

        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });

        // Filtrar tabla por búsqueda y tipo
        btnFiltrar.addActionListener(e -> filtrarClientes());
    }

    // Método para aplicar estilo consistente a botones
    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Método de filtrado de clientes (actualmente limpia la tabla)
    private void filtrarClientes() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String tipoSeleccionado = cbTipo.getSelectedItem().toString();

        // Limpiar tabla antes de filtrar
        tableModel.setRowCount(0);
    }
}


