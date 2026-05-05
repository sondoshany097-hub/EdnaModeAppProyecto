package dao;

import Modelo.*;
import java.sql.*;
import java.util.*;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD
 * de la tabla CITAS en la base de datos.
 */
public class CitaDAO {
	/**
	 * Clase DAO encargada de gestionar las operaciones CRUD
	 * de la tabla CITAS en la base de datos.
	 */
	public boolean guardarCita(Cita cita) {
		String sql = "INSERT INTO CITAS (ID_CLIENTE, ID_TRAJE, ID_TALLER, FECHA, HORA, DURACION) VALUES (?,?,?,?,?,?)";
		try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cita.getIdCliente());
			ps.setInt(2, cita.getIdTraje());
			ps.setInt(3, cita.getIdTaller());
			ps.setString(4, cita.getFecha());
			ps.setString(5, cita.getHora());
			ps.setInt(6, cita.getDuracion());
			/*
			 * Ejecuta la operación (INSERT/UPDATE/DELETE) y devuelve el número de filas
			 * afectadas. Si es mayor que 0, significa que la operación se realizó
			 * correctamente.
			 */
			int filas = ps.executeUpdate();
			return filas > 0;
		}
		/*
		 * Cuando hay error me sale esta mensaje
		 */
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
     * Obtiene todas las citas registradas en la base de datos.
     *
     * @return lista de objetos Cita
     */
	public List<Cita> listarCitas() {
		List<Cita> lista = new ArrayList<>();
		String sql = "SELECT * FROM CITAS";

		try (Connection con = ConexionBD.conectar();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Cita cita = new Cita();
				cita.setIdCita(rs.getInt("ID_CITA"));
				cita.setIdCliente(rs.getInt("ID_CLIENTE"));
				cita.setIdTraje(rs.getInt("ID_TRAJE"));
				cita.setIdTaller(rs.getInt("ID_TALLER"));
				cita.setFecha(rs.getString("FECHA"));
				cita.setHora(rs.getString("HORA"));
				cita.setDuracion(rs.getInt("DURACION"));

				lista.add(cita);
			}

		} catch (SQLException e) {
			System.out.println("Error listing cita: " + e.getMessage());
		}

		return lista;
	}

	/**
     * Actualiza una cita existente en la base de datos.
     *
     * @param cita objeto Cita con los datos actualizados
     * @return true si la actualización fue correcta, false en caso contrario
     */
	public boolean actualizarCliente(Cita cita) {
		String sql = "UPDATE CITAS SET ID_CLIENTE = ?, ID_TRAJE = ?, ID_TALLER = ?, FECHA = ?, HORA = ?, DURACION = ? WHERE ID_CITA = ?";

		try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cita.getIdCliente());
			ps.setInt(2, cita.getIdTraje());
			ps.setInt(3, cita.getIdTaller());
			ps.setString(4, cita.getFecha());
			ps.setString(5, cita.getHora());
			ps.setInt(6, cita.getDuracion());
			ps.setInt(7, cita.getIdCita());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			System.out.println("Error updating cita: " + e.getMessage());
			return false;
		}
		}

	/**
     * Actualiza una cita existente en la base de datos.
     *
     *  cita objeto Cita con los datos actualizados
     * @return true si la actualización fue correcta, false en caso contrario
     */
	public boolean eliminarCitas(int idCita) {
		String sql = "DELETE FROM CITAS WHERE ID_CITA = ?";
		try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCita);
			int filas = ps.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminar citas" + e.getMessage());
			return false;
		}
	}

	/**
     * Obtiene todas las citas con los nombres relacionados (cliente, traje y taller).
     *
     * @return lista de arrays con datos de citas formateados para la vista
     */

	public List<String[]> listarCitasConNombres() {
		List<String[]> lista = new ArrayList<>();
		String sql = "SELECT c.ID_CITA, cl.NOMBRE AS CLIENTE, t.NOMBRE AS TRAJE, "
				+ "ta.NOMBRE_SALA AS TALLER, c.FECHA, c.HORA, c.DURACION " + "FROM CITAS c "
				+ "INNER JOIN CLIENTES cl ON c.ID_CLIENTE = cl.ID_CLIENTE "
				+ "INNER JOIN TRAJES t ON c.ID_TRAJE = t.ID_TRAJE "
				+ "INNER JOIN TALLERES ta ON c.ID_TALLER = ta.ID_TALLER " + "ORDER BY c.ID_CITA";

		try (Connection con = ConexionBD.conectar();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String[] fila = new String[7];
				fila[0] = String.valueOf(rs.getInt("ID_CITA"));
				fila[1] = rs.getString("CLIENTE");
				fila[2] = rs.getString("TRAJE");
				fila[3] = rs.getString("TALLER");
				fila[4] = rs.getString("FECHA");
				fila[5] = rs.getString("HORA");
				fila[6] = String.valueOf(rs.getInt("DURACION"));
				lista.add(fila);
			}
		} catch (SQLException e) {
			System.out.println("Error mostar lista de cita con nombres: " + e.getMessage());
		}

		return lista;
	}

}
