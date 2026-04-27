/**
 * 
 */
package Controlador;
import dao.*;


/**
 * Clase controlador encargada de gestionar el inicio de sesión.
 * Actúa como intermediario entre la vista y la base de datos (DAO).
 * Recibe las credenciales del usuario, las valida a través del DAO
 * y devuelve el resultado de la autenticación.
 */

public class LoginController {
	/**
	 * Objeto DAO para acceder a los datos de usuarios
	 */
	private UsuarioDAO usuarioDAO;
	/**
	 * Constructor: inicializa el objeto UsuarioDAO
	 */
	public LoginController() {
		this.usuarioDAO = new UsuarioDAO();
		}
	
	/**
	 * Método para iniciar sesión
	 * @param username Nombre de usuario
	 * @param password Contraseña
	 * @return true si las credenciales son correctas, false en caso contrario
	 */
	public boolean iniciarSesion(String username, String password) {
		return usuarioDAO.login(username, password);
		}
	}
