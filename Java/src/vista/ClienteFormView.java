package vista;

import Controlador.ClienteConroller;
import Controlador.TrajeController;
import Modelo.Cliente;
import Modelo.Traje;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana de formulario para la creación y edición de clientes.
 * Permite introducir y modificar los datos de un cliente, así como
 * gestionar la creación o actualización de su traje asociado.
 * Puede ser utilizada de forma independiente o desde el formulario de citas,
 * permitiendo recargar los datos al volver a la vista anterior.
*/
public class ClienteFormView extends JFrame {
	/** Campo de texto para el nombre del cliente */
	private JTextField txtNombreCliente;
	
	/** Campo de texto para el superpoder del cliente */
    private JTextField txtSuperpoder;
    
    /** Campo de texto para los colores del traje */
    private JTextField txtColores;
    
    /** ComboBox para seleccionar el tipo de personaje (héroe o villano) */
    private JComboBox<String> cbTipo;
    
    /** ComboBox para seleccionar el estado del traje */
    private JComboBox<String> cbEstado;
    
    /** Campo de texto para el nombre del traje */
    private JTextField txtNombreTrajes;
    
    /** Cliente en modo edición; null si es un nuevo registro */
    private Cliente clienteEditar;
    
    /** Controlador encargado de la lógica de clientes */
    private ClienteConroller clienteController;
    
    /** Referencia al formulario de citas que abre esta vista (opcional) */
    private CitaFormView citaFormPadre;

    /**
     * Constructor por defecto.
     * Inicializa el formulario para crear un nuevo cliente.
    */
    public ClienteFormView() {
        this(null, null);
    }

    /**
     * Constructor para editar un cliente existente.
     * @param cliente Objeto Cliente a editar
    */
    public ClienteFormView(Cliente cliente) {
        this(cliente, null);
    }

    /**
     * Constructor utilizado cuando se abre desde el formulario de citas.
     * @param citaFormPadre Referencia al formulario de citas
    */
    public ClienteFormView(CitaFormView citaFormPadre) {
        this(null, citaFormPadre);
    }

    /**
     * Constructor principal que permite definir cliente y vista padre.
     * @param cliente Cliente a editar (null si es nuevo)
     * @param citaFormPadre Vista de citas que invoca este formulario (opcional)
    */
    public ClienteFormView(Cliente cliente, CitaFormView citaFormPadre) {
        this.clienteEditar = cliente;
        this.citaFormPadre = citaFormPadre;
        this.clienteController = new ClienteConroller();

        setTitle(clienteEditar == null ? "Edna Moda - Nuevo Cliente" : "Edna Moda - Editar Cliente");
        setSize(700, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();

        if (clienteEditar != null) {
            cargarDatosCliente();
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
     * Inicializa y organiza los componentes gráficos del formulario.
    */

    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color textColor = new Color(40, 40, 40);

        JLabel lblTitle = new JLabel("Formulario de Cliente");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(212, 30, 271, 35);
        getContentPane().add(lblTitle);

        JLabel lblNombre = new JLabel("Nombre de Cliente:");
        JLabel lblSuperpoder = new JLabel("Superpoder:");
        JLabel lblColores = new JLabel("Colores:");
        JLabel lblTipo = new JLabel("Tipo:");
        JLabel lblNombreTrajes = new JLabel("Nombre de Traje:");
        JLabel lblEstado = new JLabel("Estado de Traje:");

        JLabel[] labels = {lblNombre, lblSuperpoder, lblColores, lblTipo, lblEstado,lblNombreTrajes };

        int y = 95;
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setForeground(textColor);
            label.setBounds(80, y, 230, 25);
            getContentPane().add(label);
            y += 60;
        }

        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(321, 96, 280, 35);
        getContentPane().add(txtNombreCliente);
        
        txtSuperpoder = new JTextField();
        txtSuperpoder.setBounds(321, 154, 280, 32);
        getContentPane().add(txtSuperpoder);

        txtColores = new JTextField();
        txtColores.setBounds(321, 213, 280, 32);
        getContentPane().add(txtColores);

        cbTipo = new JComboBox<>(new String[]{"HEROE", "VILLANO"});
        cbTipo.setBounds(321, 278, 280, 32);
        getContentPane().add(cbTipo);
        
        txtNombreTrajes = new JTextField();
        txtNombreTrajes.setBounds(330, 395, 271, 32);
        getContentPane().add(txtNombreTrajes);

        cbEstado = new JComboBox<>(new String[]{"Diseño", "Costura", "Pruebas"});
        cbEstado.setBounds(321, 341, 280, 32);
        getContentPane().add(cbEstado);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        styleButton(btnGuardar, darkGreen, Color.WHITE);
        styleButton(btnCancelar, darkGreen, Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnGuardar.setBounds(200, 489, 130, 42);
        btnCancelar.setBounds(381, 489, 130, 42);

        getContentPane().add(btnGuardar);
        getContentPane().add(btnCancelar);
 

        btnGuardar.addActionListener(e -> guardarOActualizarCliente());
        btnCancelar.addActionListener(e -> volverPantallaAnterior());
    }

    /**
     * Carga los datos del cliente en el formulario cuando se encuentra en modo edición.
    */
    private void cargarDatosCliente() {
        txtNombreCliente.setText(clienteEditar.getNombreHero() == null ? "" : clienteEditar.getNombreHero());
        txtSuperpoder.setText(clienteEditar.getSuperpoder() == null ? "" : clienteEditar.getSuperpoder());
        txtColores.setText(clienteEditar.getColores() == null ? "" : clienteEditar.getColores());
        cbTipo.setSelectedItem(clienteEditar.getTipoPersonaje());
    }

    /**
     * Valida los datos introducidos y guarda o actualiza el cliente.
     * También gestiona la creación o actualización del traje asociado al cliente.
    */
    private void guardarOActualizarCliente() {
        String nombre = txtNombreCliente.getText().trim();
        String superpoder = txtSuperpoder.getText().trim();
        String colores = txtColores.getText().trim();
        String tipo = cbTipo.getSelectedItem().toString();

        String nombreTraje = txtNombreTrajes.getText().trim();
        String estado = cbEstado.getSelectedItem().toString();

        if (nombre.isEmpty() || colores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios.");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNombreHero(nombre);
        cliente.setSuperpoder(superpoder);
        cliente.setColores(colores);
        cliente.setTipoPersonaje(tipo);

        int clienteId;

        if (clienteEditar == null) {
            clienteId = clienteController.gurdarCliente(cliente);
        } else {
            cliente.setIdCliente(clienteEditar.getIdCliente());
            clienteController.actiualizarCliente(cliente);
            clienteId = cliente.getIdCliente();
        }

        TrajeController trajeController = new TrajeController();

        Traje traje = new Traje();
        traje.setNombreTraje(nombreTraje);
        traje.setEstado(estado);
        traje.setIdCliente(clienteId);

        if (clienteEditar == null) {
            // INSERT
            trajeController.guardarOActualizarTraje(traje);
        } else {
            // UPDATE
            int idTraje = trajeController.obtenerIdTrajePorCliente(clienteId);
            traje.setIdTraje(idTraje);

            trajeController.actiualizarTraje(traje);
        }
        JOptionPane.showMessageDialog(this, "Guardado correctamente.");
        volverPantallaAnterior(clienteId);
        if (citaFormPadre != null) {
            citaFormPadre.recargarCombos(clienteId, null);
            citaFormPadre.setVisible(true);
        } else {
            new ClientesView().setVisible(true);
        }
        dispose();
    }

    /**
     * Vuelve a la pantalla anterior.
     * Si el formulario fue abierto desde la vista de citas, recarga los datos
     * en dicha vista. En caso contrario, vuelve a la lista de clientes.
    */
    private void volverPantallaAnterior(int clienteId) {
        if (citaFormPadre != null) {
            citaFormPadre.recargarCombos(clienteId, null); 
            citaFormPadre.setVisible(true);
        } else {
            new ClientesView().setVisible(true);
        }
        dispose();
    }
    private void volverPantallaAnterior() {
        if (citaFormPadre != null) {
            citaFormPadre.recargarCombos(null, null); 
            citaFormPadre.setVisible(true);
        } else {
            new ClientesView().setVisible(true);
        }
        dispose();
    }
    /**
     * Aplica estilos visuales a los botones
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
}

