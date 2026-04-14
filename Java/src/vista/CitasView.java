/**
 * Vista para la gestión de citas
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CitasView extends JFrame {

    /**
     * Componentes principales
     */
    private JTable tableCitas;             
    private DefaultTableModel tableModel;  
    private JButton btnNueva;              
    private JButton btnEditar;          
    private JButton btnBorrar;            
    private JButton btnVolver;            

    /**
     * Constructor
     */
    public CitasView() {
        setTitle("Edna Moda - Citas");         
        setSize(950, 600);                    
        setLocationRelativeTo(null);          
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);                  

        inicializarVentana();                 
        inicializarComponentes();             
    }

    /**
     * Configuración del panel principal
     */
    private void inicializarVentana() {
        getContentPane().setLayout(null);     
        getContentPane().setBackground(Color.WHITE); 
    }

    /**
     * Creación de todos los componentes visuales
     */
    private void inicializarComponentes() {
        /**
         * Colores personalizados
         */
        Color darkGreen = new Color(85, 107, 47);
        Color gold = new Color(201, 169, 97);
        Color textColor = new Color(40, 40, 40);
        Color lightGray = new Color(245, 245, 245);

        /**
         * ===== Título =====
         */
        JLabel lblTitle = new JLabel("Gestión de Citas");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 20, 300, 40);
        getContentPane().add(lblTitle);

        /**
         * ===== Tabla de citas =====
         */
        String[] columnas = { "Fecha", "Hora", "Duración", "Cliente", "Traje", "Taller"};
        tableModel = new DefaultTableModel(columnas, 0); // 
        tableCitas = new JTable(tableModel);
        tableCitas.setRowHeight(28);
        tableCitas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableCitas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        /**
         * Scroll para la tabla
         */
        JScrollPane scrollPane = new JScrollPane(tableCitas);
        scrollPane.setBounds(30, 80, 880, 330);
        getContentPane().add(scrollPane);

        /**
         * ===== Botones =====
         */

        /**
         * Botón nueva cita
         */
        btnNueva = new JButton("Nueva cita");
        btnNueva.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnNueva.setForeground(Color.WHITE);
        btnNueva.setBackground(darkGreen);
        btnNueva.setFocusPainted(false);
        btnNueva.setBorderPainted(false);
        btnNueva.setBounds(150, 450, 150, 42);
        getContentPane().add(btnNueva);

        /**
         * Botón editar
         */
        btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(gold);
        btnEditar.setFocusPainted(false);
        btnEditar.setBorderPainted(false);
        btnEditar.setBounds(330, 450, 150, 42);
        getContentPane().add(btnEditar);

        /**
         * Botón borrar
         */
        btnBorrar = new JButton("Borrar");
        btnBorrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnBorrar.setForeground(Color.WHITE);
        btnBorrar.setBackground(new Color(140, 40, 40));
        btnBorrar.setFocusPainted(false);
        btnBorrar.setBorderPainted(false);
        btnBorrar.setBounds(510, 450, 150, 42);
        getContentPane().add(btnBorrar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(683, 451, 144, 40);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setForeground(new Color(255, 255, 255));
        btnVolver.setBackground(darkGreen);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false); // Mantener estilo uniforme
        getContentPane().add(btnVolver);

        /**
         *  ===== Acciones de botones =====
         */

        /**
         *  Abrir formulario para nueva cita
         */
        btnNueva.addActionListener(e -> {
            new CitaFormView().setVisible(true);
            dispose(); // Cierra esta ventana
        });

        /**Editar cita seleccionada
         * 
         */
        btnEditar.addActionListener(e -> {
            int fila = tableCitas.getSelectedRow();

            if (fila == -1) {
                // Si no hay fila seleccionada
                JOptionPane.showMessageDialog(this, "Selecciona una cita para editar.");
            } else {
                new CitaFormView().setVisible(true);
                dispose();
            }
        });

        /**
         * Borrar cita seleccionada
         */
        btnBorrar.addActionListener(e -> {
            int fila = tableCitas.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una cita para borrar.");
            } else {
                // Confirmación antes de borrar
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que deseas borrar esta cita?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION
                );

                // Si el usuario confirma
                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(fila); // Elimina fila de la tabla
                }
            }
        });

        /**
         * Volver al menú principal
         */
        btnVolver.addActionListener(e -> {
            new MainView().setVisible(true);
            dispose();
        });
    }
}



