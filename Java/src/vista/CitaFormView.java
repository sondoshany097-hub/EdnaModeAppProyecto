package vista;

import javax.swing.*;
import java.awt.*;

public class CitaFormView extends JFrame {

    /**
     * Campos de formulario
     */
    private JComboBox<String> cbCliente;   
    private JComboBox<String> cbTraje;     
    private JComboBox<String> cbTaller;    
    private JTextField txtFecha;          
    private JTextField txtHora;           
    private JComboBox<String> cbDuracion; 

    /**
     * Constructor
     */
    public CitaFormView() {
        setTitle("Edna Moda - Nueva Cita"); 
        setSize(600, 500);                  
        setLocationRelativeTo(null);       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);                

        inicializarVentana();              
        inicializarComponentes();           
    }

    /**
     * Configuración básica de la ventana
     */
    private void inicializarVentana() {
        getContentPane().setLayout(null);       
        getContentPane().setBackground(Color.WHITE); 
    }

    /**
     * Inicialización de componentes gráficos
     */
    private void inicializarComponentes() {
        Color darkGreen = new Color(85, 107, 47);  
        Color textColor = new Color(40, 40, 40);   
        Color lightGray = new Color(245, 245, 245);

        /**
         * ===== Título =====
         */
        JLabel lblTitle = new JLabel("Formulario de Cita");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 26));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(160, 20, 300, 30);
        getContentPane().add(lblTitle);

        /**
         * ===== Campos de formulario =====
         */

        /**
         * Cliente
         */
        JLabel lblCliente = new JLabel("Nombre de Cliente:");
        lblCliente.setBounds(60, 80, 120, 25);
        getContentPane().add(lblCliente);

       cbCliente = new JComboBox<>(new String[]{"Mr. Incredible", "Elastigirl", "Frozone", "Syndrome"});
        cbCliente.setBounds(200, 80, 280, 30);
        getContentPane().add(cbCliente);

        // Traje
        JLabel lblTraje = new JLabel("Nombre de Traje:");
        lblTraje.setBounds(60, 130, 120, 25);
        getContentPane().add(lblTraje);

        cbTraje = new JComboBox<>(new String[]{"Traje principal", "Traje elástico", "Traje hielo", "Traje villano"});
        cbTraje.setBounds(200, 130, 280, 30);
        getContentPane().add(cbTraje);

        /**
         * Taller
         */
        JLabel lblTaller = new JLabel("Nombre de Taller:");
        lblTaller.setBounds(60, 180, 120, 25);
        getContentPane().add(lblTaller);

        cbTaller = new JComboBox<>(new String[]{"Diseño - Milán", "Costura - Madrid", "Pruebas - París"});
        cbTaller.setBounds(200, 180, 280, 30);
        getContentPane().add(cbTaller);

        /**
         * Fecha
         */
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(60, 230, 120, 25);
        getContentPane().add(lblFecha);

        txtFecha = new JTextField("10/04/2026");
        txtFecha.setBounds(200, 230, 280, 30);
        getContentPane().add(txtFecha);

        /**
         * Hora
         */
        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(60, 280, 120, 25);
        getContentPane().add(lblHora);

        txtHora = new JTextField("10:00");
        txtHora.setBounds(200, 280, 280, 30);
        getContentPane().add(txtHora);

        /**
         * Duración
         */
        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setBounds(60, 330, 120, 25);
        getContentPane().add(lblDuracion);

        cbDuracion = new JComboBox<>(new String[]{"1h", "2h", "3h"});
        cbDuracion.setBounds(200, 330, 280, 30);
        getContentPane().add(cbDuracion);

        /** ===== Botones =====
         * 
         */

     /**
      * Botón Guardar
      */
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 390, 130, 42);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(darkGreen);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        getContentPane().add(btnGuardar);

        /**
         * Botón Cancelar
         */
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 390, 130, 42);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.setBackground(darkGreen);
        btnCancelar.setFocusPainted(false);
        getContentPane().add(btnCancelar);

        /**
         *  ===== Acciones de botones =====
         */

        /**
         * Guardar cita y volver a la lista de citas
         */
        btnGuardar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cita guardada correctamente.");
            new CitasView().setVisible(true);
            dispose();
        });

        /**
         * Cancelar y volver a la lista de citas
         */
        btnCancelar.addActionListener(e -> {
            new CitasView().setVisible(true);
            dispose();
        });
    }
}

