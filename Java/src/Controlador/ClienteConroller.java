/**
 * 
 */
package Controlador;
import dao.*;
import Modelo.Cliente;
import java.util.*;


/**
 * Clase controlador para gestionar las operaciones relacionadas con los clientes.
 * Actúa como intermediario entre la vista y la base de datos (DAO).
 * Recibe las solicitudes del usuario, las procesa y devuelve los resultados.
 */

public class ClienteConroller {
	/**
	 * Objeto DAO para acceder a la base de datos
	 */
	private ClienteDAO clienteDAO;
	/**
	 * Constructor: inicializa el objeto ClienteDAO
	 */
	public ClienteConroller() {
		this.clienteDAO =  new ClienteDAO();
		}
	
	/**
	 * Guarda un nuevo cliente en la base de datos
	 * @param cliente Objeto Cliente a guardar
	 * @return true si se guarda correctamente, false en caso contrario
	 */
	
	public boolean gurdarCliente(Cliente cliente) {
		return clienteDAO.gurdarCliente(cliente);	
	}
	
	/**
	 * Devuelve la lista de todos los clientes
	 * @return Lista de objetos Cliente
	 */
	public List<Cliente> listarClientes(){
		return clienteDAO.listarClientes();
	}
	/**
	 * Actualiza los datos de un cliente existente
	 * @param cliente Objeto Cliente con los datos actualizados
	 * @return true si la actualización fue exitosa, false en caso contrario
	 */
	public boolean actiualizarCliente(Cliente cliente) {
		return clienteDAO.actualizarCliente(cliente);
	}
	/**
	 * Elimina un cliente por su ID
	 * @param idCliente Identificador del cliente
	 * @return true si se elimina correctamente, false en caso contrario
	 */
	public boolean eliminarCliente ( int idCliente) {
		return clienteDAO.eliminarCliente(idCliente);
	}
}
