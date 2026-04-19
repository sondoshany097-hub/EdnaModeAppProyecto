/**
 * 
 */
package vista;
import Controlador.TallerController;
import Modelo.Taller;

import javax.swing.*;
import java.awt.*;

public class TallerFormView extends JFrame {

    private JTextField txtId;
    private JTextField txtNombreSala;
    private JComboBox<String> cbTipoSala;

    private Taller tallerEditar;
    private TallerController tallerController;

    public TallerFormView() {
        this(null);
    }

    public TallerFormView(Taller taller) {
        this.tallerEditar = taller;
        this.tallerController = new TallerController();

        setTitle("Edna Moda - Formulario de Taller");
        setSize(620, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();

        if (tallerEditar != null) {
            cargarDatosTaller();
        }
    }

    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);

        JLabel lblTitle = new JLabel("Formulario de Taller");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(160, 30, 320, 35);
        getContentPane().add(lblTitle);

        JLabel lblId = new JLabel("ID del taller:");
        lblId.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblId.setBounds(70, 100, 140, 25);
        getContentPane().add(lblId);

        txtId = new JTextField("Auto-generated");
        txtId.setBounds(230, 100, 250, 32);
        txtId.setEditable(false);
        txtId.setBackground(new Color(235, 235, 235));
        getContentPane().add(txtId);

        JLabel lblNombreSala = new JLabel("Nombre sala:");
        lblNombreSala.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNombreSala.setBounds(70, 160, 140, 25);
        getContentPane().add(lblNombreSala);

        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(230, 160, 250, 32);
        getContentPane().add(txtNombreSala);

        JLabel lblTipoSala = new JLabel("Tipo sala:");
        lblTipoSala.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTipoSala.setBounds(70, 220, 140, 25);
        getContentPane().add(lblTipoSala);

        cbTipoSala = new JComboBox<>(new String[]{"DISEÑO", "COSTURA", "PRUEBAS"});
        cbTipoSala.setBounds(230, 220, 250, 32);
        getContentPane().add(cbTipoSala);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        styleButton(btnGuardar, darkGreen, Color.WHITE);
        styleButton(btnCancelar, lightGray, textColor);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnGuardar.setBounds(150, 350, 130, 42);
        btnCancelar.setBounds(320, 350, 130, 42);

        getContentPane().add(btnGuardar);
        getContentPane().add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarOActualizarTaller());

        btnCancelar.addActionListener(e -> {
            new TalleresView().setVisible(true);
            dispose();
        });
    }

    private void cargarDatosTaller() {
        txtId.setText(String.valueOf(tallerEditar.getIdTaller()));
        txtNombreSala.setText(tallerEditar.getNombresala());
        cbTipoSala.setSelectedItem(tallerEditar.getTiposala());
    }

    private void guardarOActualizarTaller() {
        String nombreSala = txtNombreSala.getText().trim();
        String tipoSala = cbTipoSala.getSelectedItem().toString();

        if (nombreSala.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        Taller taller = new Taller();
        taller.setNombreSala(nombreSala);
        taller.setTipoSala(tipoSala);

        boolean resultado;

        if (tallerEditar == null) {
            resultado = tallerController.guardarTaller(taller);
        } else {
            taller.setIdTaller(tallerEditar.getIdTaller());
            resultado = tallerController.actiualizarTaller(taller);
        }

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Operación realizada correctamente.");
            new TalleresView().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error en la operación.");
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
}

