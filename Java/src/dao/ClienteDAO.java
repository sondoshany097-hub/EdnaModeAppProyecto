package dao;

import Modelo.*;
import java.sql.*;
import java.util.*;

/**
 * Clase DAO encargada de gestionar las operaciones CRUD
 * de la tabla CLIENTES en la base de datos.
 */
public class ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente objeto Cliente con los datos a guardar
     * @return ID generado del cliente insertado, o -1 si ocurre un error
     */
    public int guardarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES (NOMBRE, SUPERPODER, COLORES, TIPO) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombreHero());
            ps.setString(2, cliente.getSuperpoder());
            ps.setString(3, cliente.getColores());
            ps.setString(4, cliente.getTipoPersonaje());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); 
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en guardar cliente: " + e.getMessage());
        }

        return -1; 
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente objeto Cliente con los datos a guardar
     * @return ID generado del cliente insertado, o -1 si ocurre un error
     */
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTES";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNombreHero(rs.getString("NOMBRE"));
                cliente.setSuperpoder(rs.getString("SUPERPODER"));
                cliente.setColores(rs.getString("COLORES"));
                cliente.setTipoPersonaje(rs.getString("TIPO"));

                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error lista de clientes: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Obtiene todos los clientes registrados en la base de datos.
     *
     * @return lista de objetos Cliente
     */
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTES SET NOMBRE = ?, SUPERPODER = ?, COLORES = ?, TIPO = ? WHERE ID_CLIENTE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombreHero());
            ps.setString(2, cliente.getSuperpoder());
            ps.setString(3, cliente.getColores());
            ps.setString(4, cliente.getTipoPersonaje());
            ps.setInt(5, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param idCliente identificador del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}