package vista;

import Controlador.ClienteConroller;
import Controlador.TrajeController;
import Modelo.Cliente;
import Modelo.ItemCombo;
import Modelo.Traje;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrajeFormView extends JFrame {

    private JComboBox<ItemCombo> cbCliente;
    private JTextField txtNombre;
    private JComboBox<String> cbEstado;

    private Traje trajeEditar;
    private TrajeController trajeController;
    private ClienteConroller clienteController;
    private CitaFormView citaFormPadre;

    public TrajeFormView() {
        this(null, null);
    }

    public TrajeFormView(Traje traje) {
        this(traje, null);
    }

    public TrajeFormView(CitaFormView citaFormPadre) {
        this(null, citaFormPadre);
    }

    public TrajeFormView(Traje traje, CitaFormView citaFormPadre) {
        this.trajeEditar = traje;
        this.citaFormPadre = citaFormPadre;
        this.trajeController = new TrajeController();
        this.clienteController = new ClienteConroller();

        setTitle("Edna Moda - Formulario de Traje");
        setSize(650, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();
        cargarClientes();

        if (trajeEditar != null) {
            cargarDatosTraje();
        }
    }

    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color textColor = new Color(40, 40, 40);

        JLabel lblTitle = new JLabel("Formulario de Traje");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(170, 25, 320, 35);
        getContentPane().add(lblTitle);

        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblNombre = new JLabel("Nombre de Traje:");
        JLabel lblEstado = new JLabel("Estado de Traje:");

        JLabel[] labels = {lblCliente, lblNombre, lblEstado};

        int y = 100;
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setForeground(textColor);
            label.setBounds(80, y, 120, 25);
            getContentPane().add(label);
            y += 65;
        }

        cbCliente = new JComboBox<>();
        cbCliente.setBounds(210, 100, 280, 32);
        getContentPane().add(cbCliente);

        txtNombre = new JTextField();
        txtNombre.setBounds(210, 165, 280, 32);
        getContentPane().add(txtNombre);

        cbEstado = new JComboBox<>(new String[]{"Diseño", "Costura", "Taller"});
        cbEstado.setBounds(210, 230, 280, 32);
        getContentPane().add(cbEstado);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        styleButton(btnGuardar, darkGreen, Color.WHITE);
        styleButton(btnCancelar, darkGreen, Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(85, 107, 47)));

        btnGuardar.setBounds(170, 390, 130, 42);
        btnCancelar.setBounds(340, 390, 130, 42);

        getContentPane().add(btnGuardar);
        getContentPane().add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarOActualizarTraje());
        btnCancelar.addActionListener(e -> volverPantallaAnterior());
    }

    private void cargarClientes() {
        cbCliente.removeAllItems();
        List<Cliente> lista = clienteController.listarClientes();

        for (Cliente cliente : lista) {
            cbCliente.addItem(new ItemCombo(cliente.getIdCliente(), cliente.getNombreHero()));
        }
    }

    private void cargarDatosTraje() {
        txtNombre.setText(trajeEditar.getNombreTraje() == null ? "" : trajeEditar.getNombreTraje());
        cbEstado.setSelectedItem(trajeEditar.getEstado());
        seleccionarCliente(trajeEditar.getIdCliente());
    }

    private void seleccionarCliente(int idBuscado) {
        for (int i = 0; i < cbCliente.getItemCount(); i++) {
            ItemCombo item = cbCliente.getItemAt(i);
            if (item.getId() == idBuscado) {
                cbCliente.setSelectedIndex(i);
                break;
            }
        }
    }

    private void guardarOActualizarTraje() {
        ItemCombo clienteSeleccionado = (ItemCombo) cbCliente.getSelectedItem();
        String nombre = txtNombre.getText().trim();
        String estado = cbEstado.getSelectedItem().toString();

        if (clienteSeleccionado == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        Traje traje = new Traje();
        traje.setIdCliente(clienteSeleccionado.getId());
        traje.setNombreTraje(nombre);
        traje.setEstado(estado);

        boolean resultado;

        if (trajeEditar == null) {
            resultado = trajeController.gurdarTraje(traje);
        } else {
            traje.setIdTraje(trajeEditar.getIdTraje());
            resultado = trajeController.actiualizarTraje(traje);
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
            new TrajesView().setVisible(true);
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
