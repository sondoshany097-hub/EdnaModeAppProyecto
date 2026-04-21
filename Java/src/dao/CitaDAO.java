/**
 * 
 */
package dao;
import Modelo.*;
import java.sql.*;
import java.util.*;
/**
 * 
 */
public class CitaDAO {
	public boolean guardarCita (Cita cita) {
		String sql = "INSERT INTO CITAS (ID_CLIENTE, ID_TRAJE, ID_TALLER, FECHA, HORA, DURACION) VALUES (?,?,?,?,?,?)";
		try (Connection con = ConexionBD.conectar();
		PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, cita.getIdCliente());
            ps.setInt(2, cita.getIdTraje());
            ps.setInt(3, cita.getIdTaller());
            ps.setString(4, cita.getFecha());
            ps.setString(5, cita.getHora());
            ps.setInt(6, cita.getDuracion());
            /**
             *  Ejecuta la operación (INSERT/UPDATE/DELETE) 
             *  y devuelve el número de filas afectadas.
             *  Si es mayor que 0, significa que la operación se realizó correctamente.
             */
            int filas = ps.executeUpdate();
            return filas > 0;
		}
		/**
		 * Cuando hay error me sale esta mensaje
		 */
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}}
		
		public List<Cita> listarCitas(){
			List<Cita> lista = new ArrayList<>();
			String sql = "SELECT * FROM CITAS";
			
		try (Connection con = ConexionBD.conectar();
		     PreparedStatement ps = con.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
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
			
			}catch (SQLException e) {
	            System.out.println("Error listing cita: " + e.getMessage());
	        }

	        return lista;
		}
		/**
		 * Update cita 
		 */
		public boolean actualizarCliente(Cita cita) {
			String sql = "UPDATE CITAS SET ID_CLIENTE = ?, ID_TRAJE = ?, ID_TALLER = ?, FECHA = ?, HORA = ?, DURACION = ? WHERE ID_CITA = ?";

	        try (Connection con = ConexionBD.conectar();
	             PreparedStatement ps = con.prepareStatement(sql)) {

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
		 * Eliminar cita con ID
		 */
		public boolean eliminarCitas(int idCita) {
			String sql = "DELETE FROM CITAS WHERE ID_CITA = ?";
			try (Connection con = ConexionBD.conectar();
					PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, idCita);
				int filas = ps.executeUpdate();
				return filas > 0;
				}
			catch (SQLException e) {
				System.out.println("Error eliminar citas" + e.getMessage() );
				return false;
			}
		}
		/**
		 * Esta consulta obtiene todas las citas desde la tabla CITAS
		 * y las relaciona con las tablas CLIENTES, TRAJES y TALLERES mediante INNER JOIN
		 * 
		 * El objetivo es mostrar los nombres del cliente, traje y taller en lugar de los IDs
		 * 
		 * lo que hace que los datos sean más claros y fáciles de mostrar en la interfaz (JTable)
		 * Finalmente, los resultados se ordenan por el ID de la cita
		 * @return
		 */
		
		public List<String[]> listarCitasConNombres(){
			 List<String[]> lista = new ArrayList<>();
			 String sql =
					    "SELECT c.ID_CITA, cl.NOMBRE AS CLIENTE, t.NOMBRE AS TRAJE, " +
					    "ta.NOMBRE_SALA AS TALLER, c.FECHA, c.HORA, c.DURACION " +
					    "FROM CITAS c " +
					    "INNER JOIN CLIENTES cl ON c.ID_CLIENTE = cl.ID_CLIENTE " +
					    "INNER JOIN TRAJES t ON c.ID_TRAJE = t.ID_TRAJE " +
					    "INNER JOIN TALLERES ta ON c.ID_TALLER = ta.ID_TALLER " +
					    "ORDER BY c.ID_CITA";
		
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


				
	

