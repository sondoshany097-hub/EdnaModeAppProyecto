package dao;
import java.sql.*;

/**
 *  Clase encargada de gestionar la conexión a la base de datos MySQL.
 */
public class ConexionBD {
	private String driver = "com.mysql.cj.jdbc.Driver"; 
	
	/*
	 * URL de conexión a la base de datos
	 */
	private static final String URL = "jdbc:mysql://localhost/Proyecto_EdnaMode";
	
	/*
	 * Usuario de la base de datos
	 */
	private static final String USER = "root"; 
	
	/*
	 * Contraseña de la base de datos
	 */
	private static final String PASSWORD = "Root1234";
	
	/**
	 * Establece y devuelve una conexión con la base de datos.
     * @return Connection si la conexión es exitosa, null si falla.
	 */
	public static Connection conectar () {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e ) {
			System.out.println("Connexión error" + e.getMessage());
			return null;		
		}
	}
}
