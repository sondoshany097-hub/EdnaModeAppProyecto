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
public class TrajeDAO {
	public boolean guardarTraje(Traje traje) {
        String sql = "INSERT INTO TRAJES (ID_CLIENTE, NOMBRE, ESTADO) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, traje.getIdCliente());
            ps.setString(2, traje.getNombreTraje());
            ps.setString(3, traje.getEstado());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error guardar traje: " + e.getMessage());
            return false;
        }
    }
	
	public List<Traje> listarTrajes() {
        List<Traje> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRAJES";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Traje traje = new Traje();
                traje.setIdTraje(rs.getInt("ID_TRAJE"));
                traje.setIdCliente(rs.getInt("ID_CLIENTE"));
                traje.setNombreTraje(rs.getString("NOMBRE"));
                traje.setEstado(rs.getString("ESTADO"));

                lista.add(traje);
            }

        } catch (SQLException e) {
            System.out.println("Error lista de trajes: " + e.getMessage());
        }

        return lista;
    }
	public List<String[]> listarTrajesConCliente() {
        List<String[]> lista = new ArrayList<>();

        String sql = """
                SELECT T.ID_TRAJE, C.NOMBRE AS CLIENTE, T.NOMBRE AS TRAJE, T.ESTADO
                FROM TRAJES T
                JOIN CLIENTES C ON T.ID_CLIENTE = C.ID_CLIENTE
                """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] fila = {
                        rs.getString("ID_TRAJE"),
                        rs.getString("CLIENTE"),
                        rs.getString("TRAJE"),
                        rs.getString("ESTADO")
                };
                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error mostar trajes con cliente: " + e.getMessage());
        }

        return lista;
	}
	public boolean actualizarTraje(Traje traje) {
        String sql = "UPDATE TRAJES SET ID_CLIENTE = ?, NOMBRE = ?, ESTADO = ? WHERE ID_TRAJE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, traje.getIdCliente());
            ps.setString(2, traje.getNombreTraje());
            ps.setString(3, traje.getEstado());
            ps.setInt(4, traje.getIdTraje());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error actualizar traje: " + e.getMessage());
            return false;
        }
    }
	
	 public boolean eliminarTraje(int idTraje) {
	        String sql = "DELETE FROM TRAJES WHERE ID_TRAJE = ?";

	        try (Connection con = ConexionBD.conectar();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, idTraje);
	            ps.executeUpdate();
	            return true;

	        } catch (SQLException e) {
	            System.out.println("Error eliminar traje: " + e.getMessage());
	            return false;
	        }
	        }
	 }
