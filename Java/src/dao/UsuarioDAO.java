/**
 * 
 */
package dao;
import java.sql.*;

/**
 * Clase DAO encargada de la autenticación de usuarios.
 */
public class UsuarioDAO {
	/**
     * Verifica si el usuario y contraseña existen en la base de datos.
     * @param username nombre de usuario
     * @param password contraseña
     * @return true si las credenciales son correctas, false si no
     */
	public boolean login(String username, String password) {
		String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ? ";
		Connection con = ConexionBD.conectar();
		if (con == null) {
			System.out.println("base de datos connexion error");
			return false;
		}
		
		 try (PreparedStatement ps = con.prepareStatement(sql)) {
			 ps.setString(1, username);
			 ps.setString(2, password);
			 ResultSet rs = ps.executeQuery();
			 return rs.next();
		 } catch (SQLException e) {
				System.out.println("Login error" +e.getMessage() );
				return false;
				}
		 }}
