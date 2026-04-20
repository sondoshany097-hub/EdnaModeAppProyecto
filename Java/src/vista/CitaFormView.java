package vista;
import Controlador.CitaController;
import Controlador.ClienteConroller;
import Controlador.TallerController;
import Controlador.TrajeController;
import Modelo.Cita;
import Modelo.Cliente;
import Modelo.ItemCombo;
import Modelo.Taller;
import Modelo.Traje;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CitaFormView extends JFrame {

    private JTextField txtId;
    private JComboBox<ItemCombo> cbCliente;
    private JComboBox<ItemCombo> cbTraje;
    private JComboBox<ItemCombo> cbTaller;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtDuracion;

    private Cita citaEditar;
    private CitaController citaController;
    private ClienteConroller clienteController;
    private TrajeController trajeController;
    private TallerController tallerController;

    public CitaFormView() {
        this(null);
    }

    public CitaFormView(Cita cita) {
        this.citaEditar = cita;
        this.citaController = new CitaController();
        this.clienteController = new ClienteConroller();
        this.trajeController = new TrajeController();
        this.tallerController = new TallerController();

        setTitle(citaEditar == null ? "Edna Moda - Nueva Cita" : "Edna Moda - Editar Cita");
        setSize(700, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();

        cargarClientes();
        cargarTrajes();
        cargarTalleres();

        if (citaEditar != null) {
            cargarDatosCita();
        }
    }

    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color lightGray = new Color(245, 245, 245);

        JLabel lblTitle = new JLabel("Formulario de Cita");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(200, 30, 320, 35);
        getContentPane().add(lblTitle);
        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblTraje = new JLabel("Traje:");
        JLabel lblTaller = new JLabel("Taller:");
        JLabel lblFecha = new JLabel("Fecha:");
        JLabel lblHora = new JLabel("Hora:");
        JLabel lblDuracion = new JLabel("Duración:");

        JLabel[] labels = {lblCliente, lblTraje, lblTaller, lblFecha, lblHora, lblDuracion};

        int y = 100;
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setBounds(80, y, 140, 25);
            getContentPane().add(label);
            y += 55;
        }

        cbCliente = new JComboBox<>();
        cbCliente.setBounds(240, 120, 280, 32);
        getContentPane().add(cbCliente);

        cbTraje = new JComboBox<>();
        cbTraje.setBounds(240, 180, 280, 32);
        getContentPane().add(cbTraje);

        cbTaller = new JComboBox<>();
        cbTaller.setBounds(240, 240, 280, 32);
        getContentPane().add(cbTaller);

        txtFecha = new JTextField();
        txtFecha.setBounds(240, 300, 280, 32);
        txtFecha.setToolTipText("YYYY-MM-DD");
        getContentPane().add(txtFecha);

        txtHora = new JTextField();
        txtHora.setBounds(240, 360, 280, 32);
        txtHora.setToolTipText("HH:MM");
        getContentPane().add(txtHora);

        txtDuracion = new JTextField("1");
        txtDuracion.setBounds(240, 420, 280, 32);
        getContentPane().add(txtDuracion);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        styleButton(btnGuardar, darkGreen, Color.WHITE);
        styleButton(btnCancelar, darkGreen, Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnGuardar.setBounds(180, 510, 130, 42);
        btnCancelar.setBounds(360, 510, 130, 42);

        getContentPane().add(btnGuardar);
        getContentPane().add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarOActualizarCita());

        btnCancelar.addActionListener(e -> {
            new CitasView().setVisible(true);
            dispose();
        });
    }

    private void cargarClientes() {
        cbCliente.removeAllItems();
        List<Cliente> lista = clienteController.listarClientes();

        for (Cliente cliente : lista) {
            cbCliente.addItem(new ItemCombo(cliente.getIdCliente(), cliente.getNombreHero()));
        }
    }

    private void cargarTrajes() {
        cbTraje.removeAllItems();
        List<Traje> lista = trajeController.listarTraje();

        for (Traje traje : lista) {
            cbTraje.addItem(new ItemCombo(traje.getIdTraje(), traje.getNombreTraje()));
        }
    }

    private void cargarTalleres() {
        cbTaller.removeAllItems();
        List<Taller> lista = tallerController.listarTaller();

        for (Taller taller : lista) {
            cbTaller.addItem(new ItemCombo(taller.getIdTaller(), taller.getNombresala()));
        }
    }

    private void cargarDatosCita() {
        txtId.setText(String.valueOf(citaEditar.getIdCita()));
        txtFecha.setText(citaEditar.getFecha());
        txtHora.setText(citaEditar.getHora());
        txtDuracion.setText(String.valueOf(citaEditar.getDuracion()));

        seleccionarCombo(cbCliente, citaEditar.getIdCliente());
        seleccionarCombo(cbTraje, citaEditar.getIdTraje());
        seleccionarCombo(cbTaller, citaEditar.getIdTaller());
    }

    private void seleccionarCombo(JComboBox<ItemCombo> combo, int idBuscado) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            ItemCombo item = combo.getItemAt(i);
            if (item.getId() == idBuscado) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void guardarOActualizarCita() {
        ItemCombo cliente = (ItemCombo) cbCliente.getSelectedItem();
        ItemCombo traje = (ItemCombo) cbTraje.getSelectedItem();
        ItemCombo taller = (ItemCombo) cbTaller.getSelectedItem();

        String fecha = txtFecha.getText().trim();
        String hora = txtHora.getText().trim();
        String duracionTexto = txtDuracion.getText().trim();

        if (cliente == null || traje == null || taller == null ||
                fecha.isEmpty() || hora.isEmpty() || duracionTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(duracionTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número.");
            return;
        }

        Cita cita = new Cita();
        cita.setIdCliente(cliente.getId());
        cita.setIdTraje(traje.getId());
        cita.setIdTaller(taller.getId());
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setDuracion(duracion);

        boolean resultado;

        if (citaEditar == null) {
            resultado = citaController.gurdarCita(cita);
        } else {
            cita.setIdCita(citaEditar.getIdCita());
            resultado = citaController.actiualizarCita(cita);
        }

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Operación realizada correctamente.");
            new CitasView().setVisible(true);
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

