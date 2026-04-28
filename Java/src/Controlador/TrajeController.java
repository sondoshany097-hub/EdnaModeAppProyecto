/**
 * 
 */
package Controlador;
import dao.*;
import Modelo.*;
import java.util.*;

/**
 *Clase controlador para gestionar las operaciones relacionadas con los trajes.
 * Actúa como intermediario entre la vista y la base de datos (DAO).
 * Recibe las solicitudes del usuario, las procesa y devuelve los resultados.
 */

public class TrajeController {
	/**
	 * Objeto DAO para acceder a la base de datos
	 */
	
	private TrajeDAO trajeDAO;
	/**
	 * Constructor: inicializa el objeto TrajeDAO
	 */
	public TrajeController () {
		this.trajeDAO = new TrajeDAO();
	}
	
	/**
	 * Guarda un nuevo traje en la base de datos
	 * @param traje Objeto Traje a guardar
	 * @return true si se guarda correctamente, false en caso contrario
	 */
	public boolean gurdarTraje(Traje traje) {
		return trajeDAO.guardarTraje(traje);
	}
	
	/**
	 * Devuelve la lista de todos los trajes
	 * @return Lista de objetos Traje
	 */
	public List<Traje> listarTraje(){
		return trajeDAO.listarTrajes();	
	}
	
	/**
	 * Devuelve la lista de trajes junto con la información del cliente
	 * @return Lista de arrays de String con datos combinados
	 */
	public List<String[]> listarTrajeConCliente(){
		return trajeDAO.listarTrajesConCliente();
	}
	
	/**
	 * Actualiza los datos de un traje existente
	 * @param traje Objeto Traje con los datos actualizados
	 * @return true si la actualización fue exitosa, false en caso contrario
	 */
	public boolean actiualizarTraje(Traje traje) {
		return trajeDAO.actualizarTraje(traje);
	}
	
	/**
	 * Elimina un traje por su ID
	 * @param idTraje Identificador del traje
	 * @return true si se elimina correctamente, false en caso contrario
	 */
	public boolean eliminarTraje ( int idTraje) {
		return trajeDAO.eliminarTraje(idTraje);
	}
	public List<Traje> listarTrajesPorCliente(int idCliente) {
	    return trajeDAO.obtenerTrajesPorCliente(idCliente);
	}
	}
