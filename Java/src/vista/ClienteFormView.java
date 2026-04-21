package vista;
import Controlador.ClienteConroller;
import Modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClienteFormView extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtSuperpoder;
    private JTextField txtColores;
    private JComboBox<String> cbTipo;

    private Cliente clienteEditar;
    private ClienteConroller clienteController;

    public ClienteFormView() {
        this(null);
    }

    public ClienteFormView(Cliente cliente) {
        this.clienteEditar = cliente;
        this.clienteController = new ClienteConroller();

        setTitle("Edna Moda - Formulario de Cliente");
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
        Color lightGray = new Color(245, 245, 245);
        Color textColor = new Color(40, 40, 40);

        JLabel lblTitle = new JLabel("Formulario de Cliente");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(170, 25, 320, 35);
        getContentPane().add(lblTitle);

        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblSuperpoder = new JLabel("Superpoder:");
        JLabel lblColores = new JLabel("Colores:");
        JLabel lblTipo = new JLabel("Tipo:");

        JLabel[] labels = {lblId, lblNombre, lblSuperpoder, lblColores, lblTipo};

        int y = 95;
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setForeground(textColor);
            label.setBounds(80, y, 130, 25);
            getContentPane().add(label);
            y += 60;
        }

        txtId = new JTextField("Auto-generated");
        txtId.setBounds(230, 95, 280, 32);
        txtId.setEditable(false);
        txtId.setBackground(new Color(235, 235, 235));
        getContentPane().add(txtId);

        txtNombre = new JTextField();
        txtNombre.setBounds(230, 155, 280, 32);
        getContentPane().add(txtNombre);

        txtSuperpoder = new JTextField();
        txtSuperpoder.setBounds(230, 215, 280, 32);
        getContentPane().add(txtSuperpoder);

        txtColores = new JTextField();
        txtColores.setBounds(230, 275, 280, 32);
        getContentPane().add(txtColores);

        cbTipo = new JComboBox<>(new String[]{"HEROE", "VILLANO"});
        cbTipo.setBounds(230, 335, 280, 32);
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

        btnCancelar.addActionListener(e -> {
            new ClientesView().setVisible(true);
            dispose();
        });
    }

    private void cargarDatosCliente() {
        txtId.setText(String.valueOf(clienteEditar.getIdCliente()));
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
            new ClientesView().setVisible(true);
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

