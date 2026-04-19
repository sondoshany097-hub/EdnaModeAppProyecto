/**
 * 
 */
package Controlador;
import dao.*;


/**
 * 
 */
public class LoginController {
	private UsuarioDAO usuarioDAO;
	
	public LoginController() {
		
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public boolean iniciarSesion(String username, String password) {
		return usuarioDAO.login(username, password);
		
	}
}
