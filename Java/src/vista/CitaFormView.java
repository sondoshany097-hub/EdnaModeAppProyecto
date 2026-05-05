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

/**
 * Ventana de formulario para la creación y edición de citas.
 * Permite seleccionar cliente, traje y taller, así como introducir
 * fecha, hora y duración de la cita.
 *
 * Forma parte de la capa de vista y se comunica con los controladores
 * para realizar operaciones sobre las citas.
 */
public class CitaFormView extends JFrame {

	/** ComboBox para seleccionar cliente */
    private JComboBox<ItemCombo> cbCliente;
    /** ComboBox para seleccionar traje */
    private JComboBox<ItemCombo> cbTraje;
    /** ComboBox para seleccionar taller */
    private JComboBox<ItemCombo> cbTaller;
    /** Campo de texto para la fecha (formato YYYY-MM-DD) */
    private JTextField txtFecha;
    /** Campo de texto para la hora (formato HH:MM) */
    private JTextField txtHora;
    /** Campo de texto para la duración de la cita */
    private JTextField txtDuracion;

    /** Controladores para gestionar la lógica de negocio */
    private Cita citaEditar;
    private CitaController citaController;
    private ClienteConroller clienteController;
    private TrajeController trajeController;
    private TallerController tallerController;

    /**
     * Constructor por defecto.
     * Inicializa el formulario en modo creación de cita.
     */
    public CitaFormView() {
        this(null);
    }

    /**
     * Constructor que permite editar una cita existente.
     *
     * @param cita Objeto Cita a editar. Si es null, se crea una nueva cita.
     */
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
        cargarTalleres();

        if (citaEditar != null) {
            cargarDatosCita();
        }
    }

    /**
     * Inicializa la configuración básica de la ventana.
     */
    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }
    
    /**
     * Inicializa y configura los componentes gráficos del formulario.
     */

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

    /**
     * Filtra los trajes disponibles según el cliente seleccionado.
     */
    private void filtrarTrajesPorCliente() {
    	ItemCombo cliente = (ItemCombo) cbCliente.getSelectedItem();

        if (cliente == null) return;
        cbTraje.removeAllItems();
        List<Traje> lista = trajeController.listarTrajesPorCliente(cliente.getId());
        for (Traje traje : lista) {
            cbTraje.addItem(new ItemCombo(traje.getIdTraje(), traje.getNombreTraje()));
        }
	}

    /**
     * Abre el formulario para crear un nuevo cliente.
     */
	private void abrirFormularioCliente() {
        setVisible(false);
        new ClienteFormView(this).setVisible(true);
    }
	
	/**
	 * Abre el formulario para crear un nuevo traje.
	 */
    private void abrirFormularioTraje() {
        setVisible(false);
        new TrajeFormView(this).setVisible(true);
    }
 
    /**
     * Recarga los ComboBox manteniendo las selecciones actuales.
     * @param trajeId 
     */
    public void recargarCombos(Integer clienteId, Integer trajeId) {
        cargarClientes();
        cargarTalleres();

        if (clienteId != null) {
            seleccionarCombo(cbCliente, clienteId);
            filtrarTrajesPorCliente();  
        }

        if (trajeId != null) {
            seleccionarCombo(cbTraje, trajeId);
        }
    }

    /**
     * Carga la lista de clientes en el ComboBox.
     */
    private void cargarClientes() {
        cbCliente.removeAllItems();
        List<Cliente> lista = clienteController.listarClientes();

        for (Cliente cliente : lista) {
            cbCliente.addItem(new ItemCombo(cliente.getIdCliente(), cliente.getNombreHero()));
        }
    }
    
    /**
     * Carga la lista de talleres en el ComboBox.
     */
    private void cargarTalleres() {
        cbTaller.removeAllItems();
        List<Taller> lista = tallerController.listarTaller();

        for (Taller taller : lista) {
            String tipo = tallerController.obtenerTipoSalaPorId(taller.getIdTaller());

            cbTaller.addItem(
                new ItemCombo(
                    taller.getIdTaller(),
                    taller.getNombresala() + " - " + tipo
                )
            );
        }
    }

    /**
     * Carga los datos de una cita cuando se está en modo edición.
     */
    private void cargarDatosCita() {
        txtFecha.setText(citaEditar.getFecha());
        txtHora.setText(citaEditar.getHora());
        txtDuracion.setText(String.valueOf(citaEditar.getDuracion()));

        seleccionarCombo(cbCliente, citaEditar.getIdCliente());
        seleccionarCombo(cbTraje, citaEditar.getIdTraje());
        seleccionarCombo(cbTaller, citaEditar.getIdTaller());
    }
    
    /**
     * Selecciona un elemento en un ComboBox según su ID.
     *
     * @param combo ComboBox donde se realizará la selección
     * @param idBuscado ID del elemento a seleccionar
     */
    private void seleccionarCombo(JComboBox<ItemCombo> combo, int idBuscado) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            ItemCombo item = combo.getItemAt(i);
            if (item.getId() == idBuscado) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
 
    /**
     * Valida los datos introducidos y guarda o actualiza la cita.
     * También actualiza el estado del traje según el tipo de sala.
     */
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
            int idTallerSeleccionando = taller.getId();
            String tipoSala = tallerController.obtenerTipoSalaPorId(idTallerSeleccionando);
            boolean trajeActualizado = trajeController.actualizarEstadoTraje(traje.getId(), tipoSala);
            System.out.println("UPDATE RESULT = " + trajeActualizado);
            JOptionPane.showMessageDialog(this, "Operación realizada correctamente.\n"+ 
        "El estado del traje se actualizó a:" + tipoSala);
            new CitasView().setVisible(true);
            dispose();
        }
    }
    /**
     * Aplica estilo visual a un botón.
     *
     * @param button Botón a estilizar
     * @param background Color de fondo
     * @param foreground Color del texto
     */

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

