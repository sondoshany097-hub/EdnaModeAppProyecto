package dao;
import java.sql.*;
import java.util.*;
import Modelo.*;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD de la tabla TALLERES.
 */
public class TallerDAO {
	
	/**
     * Inserta un nuevo taller en la base de datos
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
     * Devuelve todos los talleres de la base de datos
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
     * Actualiza un taller existente
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
     * Elimina un taller por su ID
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
	}
