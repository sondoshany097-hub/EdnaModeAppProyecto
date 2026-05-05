package dao;

import Modelo.*;
import java.sql.*;
import java.util.*;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD
 * de la tabla TRAJES en la base de datos.
 */
public class TrajeDAO {

	/**
     * Inserta un nuevo traje en la base de datos.
     *
     * @param traje objeto Traje con los datos a guardar
     * @return true si el traje fue insertado correctamente, false en caso contrario
     */
	
    public boolean guardarTraje(Traje traje) {
        String sql = "INSERT INTO TRAJES (ID_CLIENTE, NOMBRE, ESTADO) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, traje.getIdCliente());
            ps.setString(2, traje.getNombreTraje());
            ps.setString(3, traje.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error guardar traje: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inserta un nuevo traje en la base de datos.
     *
     * traje objeto Traje con los datos a guardar
     * @return true si el traje fue insertado correctamente, false en caso contrario
     */
    
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

    /**
     * Obtiene todos los trajes almacenados en la base de datos.
     *
     * @return lista de objetos Traje
     */
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
            System.out.println("Error mostrar trajes con cliente: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Obtiene la lista de trajes junto con el nombre del cliente asociado.
     *
     * @return lista de arrays con datos [idTraje, cliente, traje, estado]
     */
    public boolean actualizarTraje(Traje traje) {
        String sql = "UPDATE TRAJES SET ID_CLIENTE = ?, NOMBRE = ?, ESTADO = ? WHERE ID_TRAJE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, traje.getIdCliente());
            ps.setString(2, traje.getNombreTraje());
            ps.setString(3, traje.getEstado());
            ps.setInt(4, traje.getIdTraje());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar traje: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un traje por su ID.
     *
     * @param idTraje identificador del traje a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarTraje(int idTraje) {
        String sql = "DELETE FROM TRAJES WHERE ID_TRAJE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTraje);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar traje: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un traje por su ID.
     *
     * @param idTraje identificador del traje a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean actualizarEstadoTraje(int idTraje, String estado) {
        String sql = "UPDATE TRAJES SET ESTADO = ? WHERE ID_TRAJE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idTraje);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar estado traje: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza únicamente el estado de un traje.
     *
     */
    public List<Traje> obtenerTrajesPorCliente(int idCliente) {
        List<Traje> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRAJES WHERE ID_CLIENTE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Traje traje = new Traje();
                traje.setIdTraje(rs.getInt("ID_TRAJE"));
                traje.setIdCliente(rs.getInt("ID_CLIENTE"));
                traje.setNombreTraje(rs.getString("NOMBRE"));
                traje.setEstado(rs.getString("ESTADO"));

                lista.add(traje);
            }

        } catch (SQLException e) {
            System.out.println("Error filtrar trajes: " + e.getMessage());
        }

        return lista;
    }
    
    /**
     * Obtiene todos los trajes asociados a un cliente.
     *
     * @param idCliente identificador del cliente
     * @return lista de trajes del cliente
     */
    public int obtenerIdTrajePorCliente(int idCliente) {

        String sql = "SELECT ID_TRAJE FROM TRAJES WHERE ID_CLIENTE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID_TRAJE");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}

