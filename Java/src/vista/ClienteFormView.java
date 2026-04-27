package vista;

import Controlador.ClienteConroller;
import Modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClienteFormView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtSuperpoder;
    private JTextField txtColores;
    private JComboBox<String> cbTipo;

    private Cliente clienteEditar;
    private ClienteConroller clienteController;

    // إذا انفتح من cita form
    private CitaFormView citaFormPadre;

    public ClienteFormView() {
        this(null, null);
    }

    public ClienteFormView(Cliente cliente) {
        this(cliente, null);
    }

    public ClienteFormView(CitaFormView citaFormPadre) {
        this(null, citaFormPadre);
    }

    public ClienteFormView(Cliente cliente, CitaFormView citaFormPadre) {
        this.clienteEditar = cliente;
        this.citaFormPadre = citaFormPadre;
        this.clienteController = new ClienteConroller();

        setTitle(clienteEditar == null ? "Edna Moda - Nuevo Cliente" : "Edna Moda - Editar Cliente");
        setSize(650, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();

        if (clienteEditar != null) {
            cargarDatosCliente();
        }
    }

    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color textColor = new Color(40, 40, 40);

        JLabel lblTitle = new JLabel("Formulario de Cliente");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(170, 29, 320, 35);
        getContentPane().add(lblTitle);

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblSuperpoder = new JLabel("Superpoder:");
        JLabel lblColores = new JLabel("Colores:");
        JLabel lblTipo = new JLabel("Tipo:");

        JLabel[] labels = {lblNombre, lblSuperpoder, lblColores, lblTipo};

        int y = 95;
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setForeground(textColor);
            label.setBounds(80, y, 130, 25);
            getContentPane().add(label);
            y += 60;
        }

        txtNombre = new JTextField();
        txtNombre.setBounds(214, 89, 280, 32);
        getContentPane().add(txtNombre);

        txtSuperpoder = new JTextField();
        txtSuperpoder.setBounds(214, 152, 280, 32);
        getContentPane().add(txtSuperpoder);

        txtColores = new JTextField();
        txtColores.setBounds(214, 208, 280, 32);
        getContentPane().add(txtColores);

        cbTipo = new JComboBox<>(new String[]{"HEROE", "VILLANO"});
        cbTipo.setBounds(214, 270, 280, 32);
        getContentPane().add(cbTipo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        styleButton(btnGuardar, darkGreen, Color.WHITE);
        styleButton(btnCancelar, darkGreen, Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnGuardar.setBounds(170, 410, 130, 42);
        btnCancelar.setBounds(340, 410, 130, 42);

        getContentPane().add(btnGuardar);
        getContentPane().add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarOActualizarCliente());
        btnCancelar.addActionListener(e -> volverPantallaAnterior());
    }

    private void cargarDatosCliente() {
        txtNombre.setText(clienteEditar.getNombreHero() == null ? "" : clienteEditar.getNombreHero());
        txtSuperpoder.setText(clienteEditar.getSuperpoder() == null ? "" : clienteEditar.getSuperpoder());
        txtColores.setText(clienteEditar.getColores() == null ? "" : clienteEditar.getColores());
        cbTipo.setSelectedItem(clienteEditar.getTipoPersonaje());
    }

    private void guardarOActualizarCliente() {
        String nombre = txtNombre.getText().trim();
        String superpoder = txtSuperpoder.getText().trim();
        String colores = txtColores.getText().trim();
        String tipo = cbTipo.getSelectedItem().toString();

        if (nombre.isEmpty() || colores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios.");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNombreHero(nombre);
        cliente.setSuperpoder(superpoder);
        cliente.setColores(colores);
        cliente.setTipoPersonaje(tipo);

        boolean resultado;

        if (clienteEditar == null) {
            resultado = clienteController.gurdarCliente(cliente);
        } else {
            cliente.setIdCliente(clienteEditar.getIdCliente());
            resultado = clienteController.actiualizarCliente(cliente);
        }

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Operación realizada correctamente.");
            volverPantallaAnterior();
        } else {
            JOptionPane.showMessageDialog(this, "Error en la operación.");
        }
    }

    private void volverPantallaAnterior() {
        if (citaFormPadre != null) {
            citaFormPadre.recargarCombos();
            citaFormPadre.setVisible(true);
        } else {
            new ClientesView().setVisible(true);
        }
        dispose();
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

