package vista;

import Controlador.TallerController;
import Modelo.Taller;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de formulario para la creación y edición de talleres.
 * Permite introducir o modificar la información de un taller, incluyendo
 * su nombre y tipo de sala.
 * Forma parte de la capa de vista y se comunica con el controlador
 * para realizar operaciones de guardado o actualización.
*/
public class TallerFormView extends JFrame {
	
	/** Campo de texto para el nombre de la sala */
    private JTextField txtNombreSala;
    
    /** ComboBox para seleccionar el tipo de sala */
    private JComboBox<String> cbTipoSala;

    /** Taller en modo edición; null si es un nuevo registro */
    private Taller tallerEditar;
    
    /** Controlador encargado de la lógica de talleres */
    private TallerController tallerController;

    /**
     * Constructor por defecto.
     * Inicializa el formulario para crear un nuevo taller.
    */
    public TallerFormView() {
        this(null);
    }
    
    /**
     * Constructor para editar un taller existente.
     * @param taller Objeto Taller a editar
    */
    public TallerFormView(Taller taller) {
        this.tallerEditar = taller;
        this.tallerController = new TallerController();

        setTitle("Edna Moda - Formulario de Taller");
        setSize(520, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        initWindow();
        initComponents();

        if (tallerEditar != null) {
            cargarDatosTaller();
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
     * incluyendo campos de texto, etiquetas y botones.
    */
    private void initComponents() {
        Color darkGreen = new Color(85, 107, 47);
        Color textColor = new Color(40, 40, 40);

        JLabel lblTitle = new JLabel("Formulario de Taller");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(darkGreen);
        lblTitle.setBounds(122, 33, 284, 35);
        getContentPane().add(lblTitle);

        JLabel lblNombreSala = new JLabel("Nombre sala:");
        lblNombreSala.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNombreSala.setForeground(textColor);
        lblNombreSala.setBounds(70, 120, 118, 25);
        getContentPane().add(lblNombreSala);

        txtNombreSala = new JTextField();
        txtNombreSala.setBounds(215, 117, 250, 32);
        getContentPane().add(txtNombreSala);

        JLabel lblTipoSala = new JLabel("Tipo sala:");
        lblTipoSala.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTipoSala.setForeground(textColor);
        lblTipoSala.setBounds(80, 181, 108, 25);
        getContentPane().add(lblTipoSala);

        cbTipoSala = new JComboBox<>(new String[]{"Diseño", "Costura", "Pruebas"});
        cbTipoSala.setBounds(215, 179, 250, 32);
        getContentPane().add(cbTipoSala);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        styleButton(btnGuardar, darkGreen, Color.WHITE);
        styleButton(btnCancelar, darkGreen, Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        btnGuardar.setBounds(99, 261, 130, 42);
        btnCancelar.setBounds(276, 261, 130, 42);

        getContentPane().add(btnGuardar);
        getContentPane().add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarOActualizarTaller());

        btnCancelar.addActionListener(e -> {
            new TalleresView().setVisible(true);
            dispose();
        });
    }

    /**
     * Carga los datos del taller en el formulario cuando está en modo edición.
     */
    private void cargarDatosTaller() {
        txtNombreSala.setText(tallerEditar.getNombresala() == null ? "" : tallerEditar.getNombresala());
        cbTipoSala.setSelectedItem(tallerEditar.getTiposala());
    }

    /**
     * Valida los datos introducidos y guarda o actualiza el taller.
     * Si el taller es nuevo, lo crea; si existe, lo actualiza.
     */
    private void guardarOActualizarTaller() {
        String nombreSala = txtNombreSala.getText().trim();
        String tipoSala = cbTipoSala.getSelectedItem() != null
                ? cbTipoSala.getSelectedItem().toString()
                : "";

        if (nombreSala.isEmpty() || tipoSala.isEmpty()) {
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
            resultado = tallerController.actualizarTaller(taller);
        }

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Operación realizada correctamente.");
            new TalleresView().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar.");
        }
    }

    /***Aplica estilos visuales personalizados a un botón.
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

