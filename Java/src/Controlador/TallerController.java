package Controlador;

import dao.*;
import Modelo.Taller;
import java.util.*;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los talleres.
 * Actúa como intermediario entre la capa de vista y la capa de acceso a datos (DAO).
 * Recibe las solicitudes, las procesa y delega las operaciones al TallerDAO.
 */
public class TallerController {

    /**
     * Objeto DAO utilizado para acceder a la base de datos de talleres.
     */
    private TallerDAO tallerDAO;

    /**
     * Constructor de la clase TallerController.
     * Inicializa la instancia de TallerDAO.
     */
    public TallerController() {
        this.tallerDAO = new TallerDAO();
    }

    /**
     * Guarda un nuevo taller en la base de datos.
     *
     * @param taller Objeto que contiene los datos del taller a guardar.
     * @return {@code true} si el taller se guarda correctamente,
     *         {@code false} en caso contrario.
     */
    public boolean guardarTaller(Taller taller) {
        return tallerDAO.guardarTaller(taller);
    }

    /**
     * Obtiene una lista con todos los talleres registrados.
     *
     * @return Lista de objetos {@link Taller}.
     */
    public List<Taller> listarTaller() {
        return tallerDAO.listarTalleres();
    }

    /**
     * Actualiza la información de un taller existente.
     *
     * @param taller Objeto con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa,
     *         {@code false} en caso contrario.
     */
    public boolean actualizarTaller(Taller taller) {
        return tallerDAO.actualizarTaller(taller);
    }

    /**
     * Elimina un taller de la base de datos a partir de su identificador.
     *
     * @param idTaller ID del taller a eliminar.
     * @return {@code true} si el taller se elimina correctamente,
     *         {@code false} en caso contrario.
     */
    public boolean eliminarTaller(int idTaller) {
        return tallerDAO.eliminarTaller(idTaller);
    }

    /**
     * Obtiene el tipo de sala asociado a un taller según su ID.
     *
     * @param idTaller ID del taller.
     * @return Tipo de sala como cadena de texto, o {@code null} si no existe.
     */
    public String obtenerTipoSalaPorId(int idTaller) {
        TallerDAO dao = new TallerDAO();
        return dao.obtenerTipoSalaPorId(idTaller);
    }
}
	