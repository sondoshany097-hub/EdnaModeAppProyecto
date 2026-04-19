/**
 * 
 */
package Controlador;
import Modelo.Cita;
import Modelo.Cliente;

import java.util.*;
import dao.*;

/**
 * hoa el controlar el wasit ben view w data base 
 * 
 * el user bydina awamer w controlar bimarrha li data bese w byrg3 el natiga 
 */
public class CitaController {
	
	private CitaDAO citaDAO;
	
	public CitaController() {
		this.citaDAO = new CitaDAO();
		
	}
	
	public boolean gurdarCita(Cita cita) {
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
