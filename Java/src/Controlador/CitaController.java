package Controlador;
import Modelo.Cita;
import Modelo.Cliente;

import java.util.*;
import dao.*;

/**
 * Clase controlador para gestionar las operaciones relacionadas con las citas.
 * Actúa como intermediario entre la vista (UI) y la capa de acceso a datos (DAO).
 * Recibe las solicitudes del usuario, las procesa y devuelve los resultados.
 */
public class CitaController {
	/**
	 * objeto DAO que permite acceder a la base de datos.
	 */
	private CitaDAO citaDAO;
	/*
	 * Constructor:inicializa el objeto CitaDAO
	 */
	public CitaController() {
		this.citaDAO = new CitaDAO();
	}
	/**
	 * Guarda una nueva cita en la base de datos
	 * @param cita Objeto Cita a guardar
	 * @return true si se guarda correctamente, false en caso contrario
	 */
	public boolean guardarCita(Cita cita) {
	    return citaDAO.guardarCita(cita);
	}
	
	/**
	 * Devuelve una lista de citas junto con los nombres asociados (por ejemplo, del cliente)
	 * @return Lista de arrays de String con la información de las citas
	 */
	public List<String[]> listarCitasConNombres(){
		return citaDAO.listarCitasConNombres();
	}
	
	/**
	 * Devuelve todas las citas almacenadas
	 * @return Lista de objetos Cita
	 */
	public List<Cita> listarCitas(){
		return citaDAO.listarCitas();
	}
	
	/**
	 * Actualiza una cita existente en la base de datos
	 * @param cita Objeto Cita con los datos actualizados
	 * @return true si la actualización fue exitosa, false en caso contrario
	 */
	public boolean actiualizarCita(Cita cita) {
		return citaDAO.actualizarCliente(cita);
	}
	
	/**
	 * Elimina una cita según su ID
	 * @param idCita Identificador de la cita
	 * @return true si se elimina correctamente, false en caso contrario
	 */
	public boolean eliminarCita(int idCita) {
		return citaDAO.eliminarCitas(idCita);
	}
	}
