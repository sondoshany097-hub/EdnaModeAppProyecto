/**
 * 
 */
package Modelo;

/**
 * Clase que representa un traje.
 * Define sus atributos y métodos de acceso (getters y setters).
 */
public class Traje {
	private int IdTraje;
	private int IdCliente;
	private String NombreTraje;
	private String Estado;

	/**
     * Constructor vacío
     */
	public Traje() {
		
	}
	
	/**
     * Constructor con parámetros
     * @param IdTraje
     * @param IdCliente
     * @param NombreTraje
     * @param Estado
     */
	public Traje(int IdTraje, int IdCliente, String NombreTraje, String Estado ) {
		this.IdTraje = IdTraje;
		this.IdCliente = IdCliente;
		this.NombreTraje = NombreTraje;
		this.Estado = Estado;
	}
	
	
	public int getIdTraje() {
		return IdTraje;
		}
	public void setIdTraje(int IdTraje){
		this.IdTraje = IdTraje;
		}
	
	public int getIdCliente() {
		return IdCliente;
		}

	public void setIdCliente(int IdCliente){
		this.IdCliente = IdCliente;
		}
	
	public String getNombreTraje() {
		return NombreTraje;
	}
	public void setNombreTraje(String NombreTraje) {
		this.NombreTraje = NombreTraje;
	}
	
	public String getEstado() {
		return Estado;
	}
	public void setEstado (String Estado) {
		this.Estado = Estado;
	}
	
}

