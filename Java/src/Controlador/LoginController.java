/**
 * 
 */
package Controlador;
import dao.*;


/**
 * Es el control intermedio entre vista y base de datos 
 * 
 * el usuario nos da la comanod y control para procedarlo a través de la base de datos y devolver el resultado
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
