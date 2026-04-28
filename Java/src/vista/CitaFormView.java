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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CitaFormView extends JFrame {

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
        //cargarTrajes();
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

        JLabel lblTitle = new JLabel("Formulario de Cita");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(30, 24, 280, 35);
        getContentPane().add(lblTitle);

        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblTraje = new JLabel("Traje:");
        JLabel lblTaller = new JLabel("Taller:");
        JLabel lblFecha = new JLabel("Fecha:");
        JLabel lblHora = new JLabel("Hora:");
        JLabel lblDuracion = new JLabel("Duración:");

        JLabel[] labels = {lblCliente, lblTraje, lblTaller, lblFecha, lblHora, lblDuracion};

        int y = 110;
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setBounds(80, y, 120, 30);
            getContentPane().add(label);
            y += 60;
        }

        cbCliente = new JComboBox<>();
        cbCliente.setBounds(220, 110, 280, 32);
        getContentPane().add(cbCliente);

        JButton btnAñadirCliente = new JButton("Añadir Cliente");
        btnAñadirCliente.setBounds(360, 30, 150, 32);
        styleButton(btnAñadirCliente, darkGreen, Color.WHITE);
        getContentPane().add(btnAñadirCliente);

        cbTraje = new JComboBox<>();
        cbTraje.setBounds(220, 170, 280, 32);
        getContentPane().add(cbTraje);

        JButton btnAñadirTraje = new JButton("Añadir Traje");
        btnAñadirTraje.setBounds(522, 30, 150, 32);
        styleButton(btnAñadirTraje, darkGreen, Color.WHITE);
        getContentPane().add(btnAñadirTraje);

        cbTaller = new JComboBox<>();
        cbTaller.setBounds(220, 230, 280, 32);
        getContentPane().add(cbTaller);

        txtFecha = new JTextField("YYYY-MM-DD");
        txtFecha.setBounds(220, 290, 280, 32);
        txtFecha.setToolTipText("YYYY-MM-DD");
        getContentPane().add(txtFecha);

        txtHora = new JTextField("HH:MM");
        txtHora.setBounds(220, 350, 280, 32);
        txtHora.setToolTipText("HH:MM");
        getContentPane().add(txtHora);

        txtDuracion = new JTextField("1");
        txtDuracion.setBounds(220, 410, 280, 32);
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

        btnAñadirCliente.addActionListener(e -> abrirFormularioCliente());
        btnAñadirTraje.addActionListener(e -> abrirFormularioTraje());

        btnGuardar.addActionListener(e -> guardarOActualizarCita());

        btnCancelar.addActionListener(e -> {
            new CitasView().setVisible(true);
            dispose();
        });
        cbCliente.addActionListener(e -> filtrarTrajesPorCliente());
    }

    private void filtrarTrajesPorCliente() {
    	ItemCombo cliente = (ItemCombo) cbCliente.getSelectedItem();

        if (cliente == null) return;
        cbTraje.removeAllItems();
        List<Traje> lista = trajeController.listarTrajesPorCliente(cliente.getId());
        for (Traje traje : lista) {
            cbTraje.addItem(new ItemCombo(traje.getIdTraje(), traje.getNombreTraje()));
        }
	}

	private void abrirFormularioCliente() {
        setVisible(false);
        new ClienteFormView(this).setVisible(true);
    }

    private void abrirFormularioTraje() {
        setVisible(false);
        new TrajeFormView(this).setVisible(true);
    }

    public void recargarCombos() {
        ItemCombo clienteSeleccionado = (ItemCombo) cbCliente.getSelectedItem();
        ItemCombo trajeSeleccionado = (ItemCombo) cbTraje.getSelectedItem();
        ItemCombo tallerSeleccionado = (ItemCombo) cbTaller.getSelectedItem();

        Integer idCliente = clienteSeleccionado != null ? clienteSeleccionado.getId() : null;
        Integer idTraje = trajeSeleccionado != null ? trajeSeleccionado.getId() : null;
        Integer idTaller = tallerSeleccionado != null ? tallerSeleccionado.getId() : null;

        cargarClientes();
        //cargarTrajes();
        cargarTalleres();

        if (idCliente != null) seleccionarCombo(cbCliente, idCliente);
        if (idTraje != null) seleccionarCombo(cbTraje, idTraje);
        if (idTaller != null) seleccionarCombo(cbTaller, idTaller);
    }

    private void cargarClientes() {
        cbCliente.removeAllItems();
        List<Cliente> lista = clienteController.listarClientes();

        for (Cliente cliente : lista) {
            cbCliente.addItem(new ItemCombo(cliente.getIdCliente(), cliente.getNombreHero()));
        }
    }

//    private void cargarTrajes() {
//        cbTraje.removeAllItems();
//        List<Traje> lista = trajeController.listarTraje();
//
//        for (Traje traje : lista) {
//            cbTraje.addItem(new ItemCombo(traje.getIdTraje(), traje.getNombreTraje()));
//        }
//    }

    private void cargarTalleres() {
        cbTaller.removeAllItems();
        List<Taller> lista = tallerController.listarTaller();

        for (Taller taller : lista) {
            cbTaller.addItem(new ItemCombo(taller.getIdTaller(), taller.getNombresala()));
        }
    }

    private void cargarDatosCita() {
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
            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número.");
            return;
        }

        LocalDate fechaCita;
        LocalTime horaCita;

        try {
            fechaCita = LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener formato YYYY-MM-DD.");
            return;
        }

        try {
            horaCita = LocalTime.parse(hora);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "La hora debe tener formato HH:MM.");
            return;
        }

        LocalDateTime fechaHoraCita = LocalDateTime.of(fechaCita, horaCita);

        if (fechaHoraCita.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this, "No se pueden crear citas en el pasado.");
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
            resultado = citaController.guardarCita(cita);
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
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(foreground);
        button.setBackground(background);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

