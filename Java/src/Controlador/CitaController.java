/**
 * 
 */
package Controlador;
import Modelo.Cita;
import Modelo.Cliente;

import java.util.*;
import dao.*;

/**
 * Es el control intermedio entre vista y base de datos 
 * 
 * el usuario nos da la comanod y control para procedarlo a través de la base de datos y devolver el resultado
 */
public class CitaController {
	
	private CitaDAO citaDAO;
	
	public CitaController() {
		this.citaDAO = new CitaDAO();
		
	}
	
	public boolean guardarCita(Cita cita) {
	    return citaDAO.guardarCita(cita);
	}
	
	public List<String[]> listarCitasConNombres(){
		return citaDAO.listarCitasConNombres();
	}
	public List<Cita> listarCitas(){
		return citaDAO.listarCitas();
	}
	
	public boolean actiualizarCita(Cita cita) {
		return citaDAO.actualizarCliente(cita);
	}
	
	public boolean eliminarCita(int idCita) {
		return citaDAO.eliminarCitas(idCita);
	}
	

}
