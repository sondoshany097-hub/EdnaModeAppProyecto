package dao;
import java.sql.*;
import java.util.*;
import Modelo.*;

/**
 * DAO de Talleres.
 * Gestiona las operaciones CRUD sobre la tabla TALLERES en la base de datos.
 */
public class TallerDAO {
	
	/**
     * Inserta un nuevo taller en la base de datos.
     *
     * @param taller objeto Taller con los datos a guardar
     * @return true si se insertó correctamente, false en caso contrario
     */
	
	public boolean guardarTaller(Taller taller) {
		String sql = "INSERT INTO TALLERES (NOMBRE_SALA, TIPO_SALA) VALUES (?,?)";
		try (Connection con = ConexionBD.conectar();
	    PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, taller.getNombresala());
			ps.setString(2, taller.getTiposala());
			ps.executeUpdate();
            return true;
		} catch (SQLException e) {
			System.out.println("Error guardar taller" +e.getMessage() );
			return false;
			}
		}
	
	/**
     * Obtiene la lista completa de talleres.
     *
     * @return lista de talleres registrados en la base de datos
     */
	public List<Taller> listarTalleres() {
        List<Taller> lista = new ArrayList<>();
        String sql = "SELECT * FROM TALLERES";
        
        try (Connection con = ConexionBD.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
        	
        while (rs.next()) {
        	Taller taller = new Taller();
        	taller.setIdTaller(rs.getInt("ID_TALLER"));
        	taller.setNombreSala(rs.getString("NOMBRE_SALA"));
        	taller.setTipoSala(rs.getString("TIPO_SALA"));
        	
        	lista.add(taller);
        }
        } catch (SQLException e ) {
        	System.out.println("Error lista de taller");
        }
        return lista;
        }
	
	/**
     * Actualiza los datos de un taller existente.
     *
     * @param taller objeto Taller con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
	public boolean actualizarTaller(Taller taller) {
		String sql = "UPDATE TALLERES SET NOMBRE_SALA = ?, TIPO_SALA = ? WHERE ID_TALLER = ?";
		
		try (Connection con = ConexionBD.conectar();
	             PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, taller.getNombresala());
			ps.setString(2, taller.getTiposala());
			ps.setInt(3, taller.getIdTaller());
			
			ps.executeUpdate();
			return true;
			
		}catch (SQLException e) {
            System.out.println("Error actualizar taller: " + e.getMessage());
            return false;
        }
	}
	
	/**
     * Elimina un taller por su ID.
     *
     * @param idTaller identificador del taller a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
	public boolean eliminarTaller(int idTaller) {
		String sql = "DELETE FROM TALLERES WHERE ID_TALLER = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTaller);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error eliminar taller: " + e.getMessage());
            return false;
        }
        }
	
	
	/**
     * Obtiene el tipo de sala de un taller por su ID.
     *
     * @param idTaller identificador del taller
     * @return tipo de sala o cadena vacía si no existe
     */

	public String obtenerTipoSalaPorId(int idTaller) {

	    String tipoSala = "";
	    String sql = "SELECT TIPO_SALA FROM TALLERES WHERE ID_TALLER = ?";

	    try (Connection con = ConexionBD.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, idTaller);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            tipoSala = rs.getString("TIPO_SALA");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error obtener tipo sala: " + e.getMessage());
	    }

	    return tipoSala;
	}
	
	}
