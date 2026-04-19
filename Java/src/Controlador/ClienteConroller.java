/**
 * 
 */
package Controlador;
import dao.*;
import Modelo.Cliente;
import java.util.*;


/**
 * 
 */
public class ClienteConroller {
	
	private ClienteDAO clienteDAO;
	
	public ClienteConroller() {
		
		this.clienteDAO =  new ClienteDAO();
		
		}
	
	public boolean gurdarCliente(Cliente cliente) {
		return clienteDAO.gurdarCliente(cliente);
		
	}
	
	public List<Cliente> listarClientes(){
		return clienteDAO.listarClientes();
		
	}
	
	public boolean actiualizarCliente(Cliente cliente) {
		return clienteDAO.actualizarCliente(cliente);
	}
	
	public boolean eliminarCliente ( int idCliente) {
		return clienteDAO.eliminarCliente(idCliente);
	}

}
