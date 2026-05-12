package vista;

import Controlador.TallerController;
import Modelo.Taller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * Ventana de gestión de talleres.
 * Permite visualizar, crear, editar y eliminar talleres registrados en el sistema.
 * Forma parte de la capa de vista y se comunica con el controlador
 * para obtener y manipular los datos de los talleres.
*/
public class TalleresView extends JFrame {
	
	/** Tabla donde se muestran los talleres */
    private JTable tableTalleres;
    
    /** Modelo de la tabla para gestionar los datos */
    private DefaultTableModel tableModel;
    
    /** Controlador encargado de la lógica de talleres */
    private TallerController tallerController;
    
    /** Lista en memoria de los talleres cargados */
    private List<Taller> listaTalleres;

    /**
     * Constructor de la vista de talleres.
     * Inicializa la ventana, los componentes gráficos y carga los datos
     * desde la base de datos.
    */
    public TalleresView() {
        tallerController = new TallerController();

        setTitle("Edna Moda - Talleres");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();
        cargarTalleresDesdeBD();
    }

    /**
     * Inicializa la configuración básica de la ventana.
     */
    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    /**
     * Inicializa y organiza los componentes gráficos de la interfaz,
     * incluyendo tabla, botones y eventos de acción.
    */
    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color darkRed = new Color(140, 40, 40);

        JLabel lblTitle = new JLabel("Gestión de Talleres");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(321, 53, 249, 35);
        getContentPane().add(lblTitle);

        String[] columns = {"Nombre Sala", "Tipo Sala"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableTalleres = new JTable(tableModel);
        styleTable(tableTalleres);

        JScrollPane scrollPane = new JScrollPane(tableTalleres);
        scrollPane.setBounds(30, 100, 820, 300);
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

        btnNuevo.setBounds(120, 470, 130, 40);
        btnEditar.setBounds(280, 470, 130, 40);
        btnBorrar.setBounds(440, 470, 130, 40);
        btnVolver.setBounds(600, 470, 130, 40);

        getContentPane().add(btnNuevo);
        getContentPane().add(btnEditar);
        getContentPane().add(btnBorrar);
        getContentPane().add(btnVolver);

        btnNuevo.addActionListener(e -> {
            new TallerFormView().setVisible(true);
            dispose();
        });

        btnEditar.addActionListener(e -> editarTallerSeleccionado());
        btnBorrar.addActionListener(e -> borrarTallerSeleccionado());

        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });
    }

    /**
     * Carga los talleres desde la base de datos y los muestra en la tabla.
     */
    private void cargarTalleresDesdeBD() {
        tableModel.setRowCount(0);

        listaTalleres = tallerController.listarTaller();

        for (Taller taller : listaTalleres) {
            tableModel.addRow(new Object[]{
                    taller.getNombresala(),
                    taller.getTiposala()
            });
        }
    }

    /**
     * Permite editar el taller seleccionado en la tabla.
     * Abre el formulario en modo edición.
     */
    private void editarTallerSeleccionado() {
        int row = tableTalleres.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un taller para editar.");
            return;
        }

        Taller tallerSeleccionado = listaTalleres.get(row);

        new TallerFormView(tallerSeleccionado).setVisible(true);
        dispose();
    }
    
    /**
     * Elimina el taller seleccionado tras confirmación del usuario.
     */
    private void borrarTallerSeleccionado() {
        int row = tableTalleres.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un taller para borrar.");
            return;
        }

        Taller tallerSeleccionado = listaTalleres.get(row);
        int idTaller = tallerSeleccionado.getIdTaller();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas borrar este taller?",
                "Confirmar borrado",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = tallerController.eliminarTaller(idTaller);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Taller borrado correctamente.");
                cargarTalleresDesdeBD();
            } else {
                JOptionPane.showMessageDialog(this, "Error al borrar taller.");
            }
        }
    }

    /**
     * Aplica estilos visuales personalizados a un botón.
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
     * Aplica estilos visuales a la tabla, incluyendo filas y encabezados.
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
