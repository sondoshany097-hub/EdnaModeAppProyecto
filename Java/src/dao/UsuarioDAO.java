/**
 * 
 */
package dao;
import java.sql.*;

/**
 * 
 */
public class UsuarioDAO {
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
