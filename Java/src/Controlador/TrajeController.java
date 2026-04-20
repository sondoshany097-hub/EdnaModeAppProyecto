/**
 * 
 */
package Controlador;
import dao.*;
import Modelo.*;
import java.util.*;

/**
 *
 * Es el control intermedio entre vista y base de datos 
 * 
 * el usuario nos da la comanod y control para procedarlo a través de la base de datos y devolver el resultado
 */
public class TrajeController {
	
	private TrajeDAO trajeDAO;
	
	public TrajeController () {
		this.trajeDAO = new TrajeDAO();
		
	}
	
	public boolean gurdarTraje(Traje traje) {
		return trajeDAO.guardarTraje(traje);
		
	}
	
	public List<Traje> listarTraje(){
		return trajeDAO.listarTrajes();
		
	}
	public List<String[]> listarTrajeConCliente(){
		return trajeDAO.listarTrajesConCliente();
	}
	
	public boolean actiualizarTraje(Traje traje) {
		return trajeDAO.actualizarTraje(traje);
	}
	
	public boolean eliminarTraje ( int idTraje) {
		return trajeDAO.eliminarTraje(idTraje);
	}
	

}
