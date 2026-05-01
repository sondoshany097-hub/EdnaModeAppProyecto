package vista;
import Controlador.ClienteConroller;
import Controlador.TrajeController;
import Modelo.Cliente;
import Modelo.ItemCombo;
import Modelo.Traje;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Ventana de formulario para la creación y edición de trajes.
 * Permite asignar un traje a un cliente, definiendo su nombre y estado.
 * Puede utilizarse de forma independiente o desde el formulario de citas,
 * permitiendo recargar datos en la vista anterior.
*/

public class TrajeFormView extends JFrame {

	/** ComboBox para seleccionar el cliente */
    private JComboBox<ItemCombo> cbCliente;
    
    /** Campo de texto para el nombre del traje */
    private JTextField txtNombre;
    
    /** ComboBox para seleccionar el estado del traje */
    private JComboBox<String> cbEstado;

    /** Traje en modo edición; null si es un nuevo registro */
    private Traje trajeEditar;
    
    /** Controlador encargado de la lógica de trajes */
    private TrajeController trajeController;
    
    /** Controlador de clientes para cargar datos */
    private ClienteConroller clienteController;
    
    /** Referencia al formulario de citas que lo invoca (opcional) */
    private CitaFormView citaFormPadre;

    /**
     * Constructor por defecto.
     * Inicializa el formulario para crear un nuevo traje.
    */
    public TrajeFormView() {
        this(null, null);
    }

    /**
     * Constructor para editar un traje existente.
     * @param traje Objeto Traje a editar
    */
    public TrajeFormView(Traje traje) {
        this(traje, null);
    }
    
    /**
     * Constructor utilizado cuando se abre desde el formulario de citas.
     * @param citaFormPadre Referencia al formulario de citas
    */
    public TrajeFormView(CitaFormView citaFormPadre) {
        this(null, citaFormPadre);
    }

    /**
     * Constructor principal que permite definir traje y vista padre.
     * @param traje Traje a editar (null si es nuevo)
     * @param citaFormPadre Vista de citas que invoca este formulario
    */
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
    
    /**
     * Inicializa la configuración básica de la ventana.
     */
    private void initWindow() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
    }

    /**
     * Inicializa y organiza los componentes gráficos del formulario,
     * incluyendo campos, etiquetas y botones.
    */
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
            label.setBounds(80, y, 220, 25);
            getContentPane().add(label);
            y += 65;
        }

        cbCliente = new JComboBox<>();
        cbCliente.setBounds(230, 100, 280, 32);
        getContentPane().add(cbCliente);

        txtNombre = new JTextField();
        txtNombre.setBounds(230, 165, 280, 32);
        getContentPane().add(txtNombre);

        cbEstado = new JComboBox<>(new String[]{"Diseño", "Costura", "Pruebas"});
        cbEstado.setBounds(230, 228, 280, 32);
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
     * Carga los datos del traje en modo edición.
     */
    private void cargarDatosTraje() {
        txtNombre.setText(trajeEditar.getNombreTraje() == null ? "" : trajeEditar.getNombreTraje());
        cbEstado.setSelectedItem(trajeEditar.getEstado());
        seleccionarCliente(trajeEditar.getIdCliente());
    }

    /**
     * Selecciona un cliente en el ComboBox según su ID.
     * @param idBuscado ID del cliente a seleccionar
     * */
    
    private void seleccionarCliente(int idBuscado) {
        for (int i = 0; i < cbCliente.getItemCount(); i++) {
            ItemCombo item = cbCliente.getItemAt(i);
            if (item.getId() == idBuscado) {
                cbCliente.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Valida los datos introducidos y guarda o actualiza el traje.
     */
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
            resultado = trajeController.guardarOActualizarTraje(traje);
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

    /**
     * Vuelve a la pantalla anterior.
     * Si fue abierto desde citas, recarga los combos en esa vista.
    */
    private void volverPantallaAnterior() {
        if (citaFormPadre != null) {
            citaFormPadre.recargarCombos();
            citaFormPadre.setVisible(true);
        } else {
            new TrajesView().setVisible(true);
        }
        dispose();
    }

    /**
     * Aplica estilos visuales personalizados a un botón.
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
