package vista;
import Controlador.ClienteConroller;
import Modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * View for displaying all clients.
 * No mock data is used. Data comes from MySQL.
 */
public class ClientesView extends JFrame {

    private JTable tableClientes;
    private DefaultTableModel tableModel;
    private JTextField txtBuscar;
    private JComboBox<String> cbTipo;

    private ClienteConroller clienteController;

    public ClientesView() {
        clienteController = new ClienteConroller();

        setTitle("Edna Moda - Clientes");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();
        cargarClientesDesdeBD();
    }

    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);
        Color darkRed = new Color(140, 40, 40);

        JLabel lblTitle = new JLabel("Gestión de Clientes");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 320, 35);
        getContentPane().add(lblTitle);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblBuscar.setBounds(30, 85, 70, 25);
        getContentPane().add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(100, 85, 220, 32);
        getContentPane().add(txtBuscar);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTipo.setBounds(350, 85, 50, 25);
        getContentPane().add(lblTipo);

        cbTipo = new JComboBox<>(new String[]{"Todos", "HEROE", "VILLANO"});
        cbTipo.setBounds(410, 85, 150, 32);
        getContentPane().add(cbTipo);

        JButton btnFiltrar = new JButton("Filtrar");
        styleButton(btnFiltrar, darkGreen, Color.WHITE);
        btnFiltrar.setBounds(590, 85, 120, 32);
        getContentPane().add(btnFiltrar);

        String[] columns = {"ID", "Nombre", "Superpoder", "Colores", "Tipo"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableClientes = new JTable(tableModel);
        styleTable(tableClientes);

        JScrollPane scrollPane = new JScrollPane(tableClientes);
        scrollPane.setBounds(30, 140, 820, 280);
        getContentPane().add(scrollPane);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar");
        JButton btnVolver = new JButton("Volver");

        styleButton(btnNuevo, darkGreen, Color.WHITE);
        styleButton(btnEditar, gold, Color.WHITE);
        styleButton(btnBorrar, darkRed, Color.WHITE);
        styleButton(btnVolver, lightGray, textColor);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnNuevo.setBounds(120, 470, 130, 40);
        btnEditar.setBounds(280, 470, 130, 40);
        btnBorrar.setBounds(440, 470, 130, 40);
        btnVolver.setBounds(600, 470, 130, 40);

        getContentPane().add(btnNuevo);
        getContentPane().add(btnEditar);
        getContentPane().add(btnBorrar);
        getContentPane().add(btnVolver);

        btnNuevo.addActionListener(e -> {
            new ClienteFormView().setVisible(true);
            dispose();
        });

        btnEditar.addActionListener(e -> editarClienteSeleccionado());

        btnBorrar.addActionListener(e -> borrarClienteSeleccionado());

        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });

        btnFiltrar.addActionListener(e -> filtrarClientes());
    }

    private void cargarClientesDesdeBD() {
        tableModel.setRowCount(0);

        List<Cliente> lista = clienteController.listarClientes();

        for (Cliente cliente : lista) {
            tableModel.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNombreHero(),
                    cliente.getSuperpoder(),
                    cliente.getColores(),
                    cliente.getTipoPersonaje()
            });
        }
    }

    private void filtrarClientes() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String tipoSeleccionado = cbTipo.getSelectedItem().toString();

        tableModel.setRowCount(0);

        List<Cliente> lista = clienteController.listarClientes();

        for (Cliente cliente : lista) {
            String nombre = cliente.getNombreHero().toLowerCase();
            String poder = cliente.getSuperpoder() == null ? "" : cliente.getSuperpoder().toLowerCase();
            String colores = cliente.getColores() == null ? "" : cliente.getColores().toLowerCase();
            String tipo = cliente.getTipoPersonaje();

            boolean coincideTexto = nombre.contains(texto) || poder.contains(texto) || colores.contains(texto);
            boolean coincideTipo = tipoSeleccionado.equals("Todos") || tipo.equals(tipoSeleccionado);

            if (coincideTexto && coincideTipo) {
                tableModel.addRow(new Object[]{
                        cliente.getIdCliente(),
                        cliente.getNombreHero(),
                        cliente.getSuperpoder(),
                        cliente.getColores(),
                        cliente.getTipoPersonaje()
                });
            }
        }
    }

    private void editarClienteSeleccionado() {
        int row = tableClientes.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para editar.");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(Integer.parseInt(tableModel.getValueAt(row, 0).toString()));
        cliente.setNombreHero(tableModel.getValueAt(row, 1).toString());
        cliente.setSuperpoder(tableModel.getValueAt(row, 2).toString());
        cliente.setColores(tableModel.getValueAt(row, 3).toString());
        cliente.setTipoPersonaje(tableModel.getValueAt(row, 4).toString());

        new ClienteFormView(cliente).setVisible(true);
        dispose();
    }

    private void borrarClienteSeleccionado() {
        int row = tableClientes.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para borrar.");
            return;
        }

        int idCliente = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas borrar este cliente?",
                "Confirmar borrado",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = clienteController.eliminarCliente(idCliente);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cliente borrado correctamente.");
                cargarClientesDesdeBD();
            } else {
                JOptionPane.showMessageDialog(this, "Error al borrar cliente.");
            }
        }
    }

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

    private void styleTable(JTable table) {
        Color darkGreen = new Color(85, 107, 47);

        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(220, 230, 210));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(220, 220, 220));

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 32));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                label.setBackground(darkGreen);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("SansSerif", Font.BOLD, 14));
                label.setOpaque(true);
                return label;
            }
        });
    }
}

