/**
 * 
 */
package dao;
import java.sql.*;

/**
 *  La conexion con MySQL base de datos 
 */
public class ConexionBD {
	private String driver = "com.mysql.cj.jdbc.Driver"; 
	private static final String URL = "jdbc:mysql://localhost/Proyecto_EdnaMode"; 
	private static final String USER = "root"; 
	private static final String PASSWORD = "Root1234";
	
	public static Connection conectar () {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e ) {
			System.out.println("Connexión error" + e.getMessage());
			return null;		
		}
	}
			
	
}
