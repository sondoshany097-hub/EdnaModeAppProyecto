package vista;
import Controlador.TrajeController;
import Modelo.Traje;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class TrajesView extends JFrame {

    private JTable tableTrajes;
    private DefaultTableModel tableModel;
    private TrajeController trajeController;

    public TrajesView() {
        trajeController = new TrajeController();

        setTitle("Edna Moda - Trajes");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();
        cargarTrajesDesdeBD();
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

        JLabel lblTitle = new JLabel("Gestión de Trajes");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 320, 35);
        getContentPane().add(lblTitle);

        String[] columns = {"Cliente", "Traje", "Estado"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableTrajes = new JTable(tableModel);
        styleTable(tableTrajes);

        JScrollPane scrollPane = new JScrollPane(tableTrajes);
        scrollPane.setBounds(30, 100, 1020, 380);
        getContentPane().add(scrollPane);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar");
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(85, 107, 47));

        styleButton(btnNuevo, darkGreen, Color.WHITE);
        styleButton(btnEditar, gold, Color.WHITE);
        styleButton(btnBorrar, darkRed, Color.WHITE);
        styleButton(btnVolver, darkGreen, Color.WHITE);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(85, 107, 47)));

        btnNuevo.setBounds(150, 560, 160, 45);
        btnEditar.setBounds(370, 560, 160, 45);
        btnBorrar.setBounds(590, 560, 160, 45);
        btnVolver.setBounds(810, 560, 160, 45);

        getContentPane().add(btnNuevo);
        getContentPane().add(btnEditar);
        getContentPane().add(btnBorrar);
        getContentPane().add(btnVolver);

        btnNuevo.addActionListener(e -> {
            new TrajeFormView().setVisible(true);
            dispose();
        });

        btnEditar.addActionListener(e -> editarTrajeSeleccionado());

        btnBorrar.addActionListener(e -> borrarTrajeSeleccionado());

        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });
    }

    private void cargarTrajesDesdeBD() {
        tableModel.setRowCount(0);

        List<String[]> lista = trajeController.listarTrajeConCliente();

        for (String[] fila : lista) {
            tableModel.addRow(fila);
        }
    }

    private void editarTrajeSeleccionado() {
        int row = tableTrajes.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un traje para editar.");
            return;
        }

        int idTraje = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

        List<Traje> lista = trajeController.listarTraje();
        Traje trajeSeleccionado = null;

        for (Traje traje : lista) {
            if (traje.getIdTraje() == idTraje) {
                trajeSeleccionado = traje;
                break;
            }
        }

        if (trajeSeleccionado != null) {
            new TrajeFormView(trajeSeleccionado).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el traje seleccionado.");
        }
    }

    private void borrarTrajeSeleccionado() {
        int row = tableTrajes.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un traje para borrar.");
            return;
        }

        int idTraje = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas borrar este traje?",
                "Confirmar borrado",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = trajeController.eliminarTraje(idTraje);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Traje borrado correctamente.");
                cargarTrajesDesdeBD();
            } else {
                JOptionPane.showMessageDialog(this, "Error al borrar traje.");
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
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

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
