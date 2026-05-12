package vista;

import Controlador.CitaController;
import Modelo.Cita;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

/**
 * Ventana principal para la gestión de citas.
 * Permite visualizar todas las citas registradas en una tabla,
 * así como realizar operaciones de creación, edición y eliminación.
 * Forma parte de la capa de vista y se comunica con el controlador
 * para obtener y manipular los datos.
*/
public class CitasView extends JFrame {

	/** Tabla donde se muestran las citas */
    private JTable tableCitas;
    
    /** Modelo de la tabla para gestionar los datos */
    private DefaultTableModel tableModel;
    
    /** Controlador encargado de la lógica de negocio de las citas */
    private CitaController citaController;

    /**
     * Constructor de la vista de citas.
     * Inicializa la ventana, los componentes gráficos y carga los datos
     * desde la base de datos.
    */
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

    /**
     * Configura las propiedades básicas de la ventana.
    */
    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    /**
     * Inicializa y organiza los componentes gráficos de la interfaz.
     * Incluye tabla, botones y eventos asociados.
    */
    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color darkRed = new Color(140, 40, 40);

        JLabel lblTitle = new JLabel("Gestión de Citas");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(377, 53, 210, 35);
        getContentPane().add(lblTitle);

        String[] columns = {"ID", "Cliente", "Traje", "Taller", "Fecha", "Hora", "Duración"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableCitas = new JTable(tableModel);
        styleTable(tableCitas);
        ocultarColumnaId();

        JScrollPane scrollPane = new JScrollPane(tableCitas);
        scrollPane.setBounds(30, 100, 920, 320);
        getContentPane().add(scrollPane);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnBorrar = new JButton("Borrar");
        JButton btnVolver = new JButton("Volver");

        styleButton(btnNuevo, darkGreen, Color.WHITE);
        styleButton(btnEditar, gold, Color.WHITE);
        styleButton(btnBorrar, darkRed, Color.WHITE);
        styleButton(btnVolver, darkGreen, Color.WHITE);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnNuevo.setBounds(144, 469, 130, 40);
        btnEditar.setBounds(298, 470, 130, 40);
        btnBorrar.setBounds(458, 470, 130, 40);
        btnVolver.setBounds(611, 469, 130, 40);

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

    /**
     * Oculta la columna del ID en la tabla.
     * Se mantiene internamente para operaciones pero no se muestra al usuario.
    */
    private void ocultarColumnaId() {
        TableColumn columnaId = tableCitas.getColumnModel().getColumn(0);
        columnaId.setMinWidth(0);
        columnaId.setMaxWidth(0);
        columnaId.setPreferredWidth(0);
    }

    /**
     * Carga las citas desde la base de datos y las muestra en la tabla.
    */
    private void cargarCitasDesdeBD() {
        tableModel.setRowCount(0);
        List<String[]> lista = citaController.listarCitasConNombres();

        for (String[] fila : lista) {
            if (fila.length == 7) {
                tableModel.addRow(fila);
            }
        }
    }

    /**
     * Permite editar la cita seleccionada en la tabla.
     * Abre el formulario en modo edición.
    */
    private void editarCitaSeleccionada() {
        int row = tableCitas.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita.");
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

    /**
     * Elimina la cita seleccionada tras confirmación del usuario.
    */
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

    /**
     * Aplica estilos visuales a los botones.
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
    
    /**
     * Aplica estilos visuales a la tabla.
     * Personaliza colores, fuentes y encabezados.
    */
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