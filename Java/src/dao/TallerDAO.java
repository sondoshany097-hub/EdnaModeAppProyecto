/**
 * 
 */
package dao;
import java.sql.*;
import java.util.*;
import Modelo.*;

/**
 * Insert nuevo taller en base de datos
 */
public class TallerDAO {
	public boolean guardarTaller(Taller taller) {
		String sql = "INSERT INTO TALLERS (NOMBRE_SALA, TIPO_SALA) VALUES (?,?)";
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
	 * mostar todos los talleres del base de datos 
	 * @return
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
	 * Actualizar talleres que existen en base de datos 
	 */
	
	
	public boolean actualizarTaller(Taller taller) {
		String sql = "UPDATE TALLER SET NOMBRE_SALA = ?, TIPO_SALA = ? WHERE ID_TALLER = ?";
		
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
	 * Eliminar traje 
	 * @param idTaller
	 * @return
	 */
	public boolean eliminarTaller(int idTaller) {
        String sql = "DELETE FROM TALLER WHERE ID_TALLER = ?";

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
