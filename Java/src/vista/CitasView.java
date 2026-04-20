/**
 * Vista para la gestión de citas
 */
package vista;
import Controlador.CitaController;
import Modelo.Cita;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class CitasView extends JFrame {

    private JTable tableCitas;
    private DefaultTableModel tableModel;
    private CitaController citaController;

    public CitasView() {
        citaController = new CitaController();

        setTitle("Edna Moda - Citas");
        setSize(1000, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();
        cargarCitasDesdeBD();
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

        JLabel lblTitle = new JLabel("Gestión de Citas");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 320, 35);
        getContentPane().add(lblTitle);

        String[] columns = {"Cliente", "Traje", "Taller", "Fecha", "Hora", "Duración"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableCitas = new JTable(tableModel);
        styleTable(tableCitas);

        JScrollPane scrollPane = new JScrollPane(tableCitas);
        scrollPane.setBounds(30, 100, 920, 320);
        getContentPane().add(scrollPane);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar");
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(85, 107, 47));

        styleButton(btnNuevo, darkGreen, Color.WHITE);
        styleButton(btnEditar, gold, Color.WHITE);
        styleButton(btnBorrar, darkRed, Color.WHITE);
        styleButton(btnVolver, lightGray, textColor.white);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnNuevo.setBounds(170, 500, 130, 40);
        btnEditar.setBounds(350, 500, 130, 40);
        btnBorrar.setBounds(530, 500, 130, 40);
        btnVolver.setBounds(710, 500, 130, 40);

        getContentPane().add(btnNuevo);
        getContentPane().add(btnEditar);
        getContentPane().add(btnBorrar);
        getContentPane().add(btnVolver);

        btnNuevo.addActionListener(e -> {
            new CitaFormView().setVisible(true);
            dispose();
        });

        btnEditar.addActionListener(e -> editarCitaSeleccionada());
        btnBorrar.addActionListener(e -> borrarCitaSeleccionada());

        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });
    }

    private void cargarCitasDesdeBD() {
        tableModel.setRowCount(0);
        List<String[]> lista = citaController.listarCitasConNombres();

        for (String[] fila : lista) {
            tableModel.addRow(fila);
        }
    }

    private void editarCitaSeleccionada() {
        int row = tableCitas.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita para editar.");
            return;
        }

        int idCita = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

        List<Cita> lista = citaController.listarCitas();
        Cita citaSeleccionada = null;

        for (Cita cita : lista) {
            if (cita.getIdCita() == idCita) {
                citaSeleccionada = cita;
                break;
            }
        }

        if (citaSeleccionada != null) {
            new CitaFormView(citaSeleccionada).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la cita seleccionada.");
        }
    }

    private void borrarCitaSeleccionada() {
        int row = tableCitas.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita para borrar.");
            return;
        }

        int idCita = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas borrar esta cita?",
                "Confirmar borrado",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = citaController.eliminarCita(idCita);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cita borrada correctamente.");
                cargarCitasDesdeBD();
            } else {
                JOptionPane.showMessageDialog(this, "Error al borrar cita.");
            }
        }
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(foreground);
        button.setBackground(new Color(85, 107, 47));
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