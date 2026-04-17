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
public class ClienteDAO {
	/**
	 * Etsa method insert nuevo caliente en base de datos
	 */
	
	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public boolean gurdarCliente(Cliente cliente) {
		String sql = "INSERT INTO CLIENTES (NOMBRE, SUPERPODER, COLORES, TIPO) VALUES (?, ?, ?, ?)";
		try (Connection con = ConexionBD.conectar();
		PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, cliente.getNombreHero());
			ps.setString(2, cliente.getSuperpoder());
			ps.setString(3, cliente.getColores());
			ps.setString(4, cliente.getTipoPersonaje());
			ps.executeUpdate();
			return true;
		}
		/**
		 * Cuando hay error me sale esta mensaje
		 */
		catch (SQLException e) {
			System.out.println("Error en guardar cliente" + e.getMessage() );
			return false;
		}
		}
		/**
		 * Devolver todos lo cliente de base de datos
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
	            System.out.println("Error listing clientes: " + e.getMessage());
	        }

	        return lista;
	        
	    }
		/**
         * Update cliente
         */
		
		public boolean actualizarCliente(Cliente cliente) {
			String sql = "UPDATE CLIENTES SET NOMBRE = ?, SUPERPODER = ?, COLORES = ?, TIPO = ? WHERE ID_CLIENTE = ?";
				try (Connection con = ConexionBD.conectar();
				PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, cliente.getNombreHero());
					ps.setString(2, cliente.getSuperpoder());
					ps.setString(3, cliente.getColores());
					ps.setString(4, cliente.getTipoPersonaje());
					ps.setInt(5, cliente.getIdCliente());
					ps.executeUpdate();
					return true;
				}
				/**
				 * Cuando hay error me sale esta mensaje
				 */
				catch (SQLException e) {
					System.out.println("Error actualizar cliente" + e.getMessage() );
					return false;
				}
			}
		/**
		 * Eliminar cliente con ID
		 */
				
		public boolean eliminarCliente(int idCliente) {
			String sql = "DELETE FROM CLIENTES WHERE ID_CLIENTE";
			try (Connection con = ConexionBD.conectar();
					PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, idCliente);
				ps.executeUpdate();
				return true;
				}
			catch (SQLException e) {
				System.out.println("Error eliminar cliente" + e.getMessage() );
				return false;
			}
			
				
		}
			
		}
 		
		
	

