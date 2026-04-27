package Controlador;
import dao.*;
import Modelo.Taller;

import java.util.*;
/**
 * Clase controlador para gestionar las operaciones relacionadas con los talleres.
 * Actúa como intermediario entre la vista y la base de datos (DAO).
 * Recibe las solicitudes del usuario, las procesa y devuelve los resultados.
 */
public class TallerController {
	/**
	 * Objeto DAO para acceder a la base de datos
	 */
	
	private TallerDAO tallerDAO;
	/**
	 * Constructor: inicializa el objeto TallerDAO
	 */
	public TallerController() {
		this.tallerDAO = new TallerDAO();
	}
	
	/**
	 * Guarda un nuevo taller en la base de datos
	 * @param taller Objeto Taller a guardar
	 * @return true si se guarda correctamente, false en caso contrario
	 */
	public boolean guardarTaller(Taller taller) {
		return tallerDAO.guardarTaller(taller);
		}
	
	/**
	 * Devuelve la lista de todos los talleres
	 * @return Lista de objetos Taller
	 */
	public List<Taller> listarTaller(){
		return tallerDAO.listarTalleres();
		}
	
	/**
	 * Actualiza los datos de un taller existente
	 * @param taller Objeto Taller con los datos actualizados
	 * @return true si la actualización fue exitosa, false en caso contrario
	 */
	public boolean actiualizarTaller(Taller taller) {
		return tallerDAO.actualizarTaller(taller);
	}
	
	/**
	 * Elimina un taller por su ID
	 * @param idTaller Identificador del taller
	 * @return true si se elimina correctamente, false en caso contrario
	 */
	public boolean eliminarTaller(int idTaller) {
		return tallerDAO.eliminarTaller(idTaller);
	}
	}
	