/**
 * 
 */
package Controlador;
import dao.*;
import Modelo.Taller;

import java.util.*;
/**
 * Es el control intermedio entre vista y base de datos 
 * 
 * el usuario nos da la comanod y control para procedarlo a través de la base de datos y devolver el resultado
 */

public class TallerController {
	
	private TallerDAO tallerDAO;
	
	public TallerController() {
		this.tallerDAO = new TallerDAO();
	}
	
	public boolean guardarTaller(Taller taller) {
		return tallerDAO.guardarTaller(taller);
		
	}
	
	public List<Taller> listarTaller(){
		return tallerDAO.listarTalleres();
		
	}
	
	public boolean actiualizarTaller(Taller taller) {
		return tallerDAO.actualizarTaller(taller);
	}
	
	public boolean eliminarTaller(int idTaller) {
		return tallerDAO.eliminarTaller(idTaller);
	}

}
	