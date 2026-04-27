/**
 * 
 */
package Modelo;

/**
 * Clase que representa una cita.
 * Define los atributos y métodos (getters/setters) necesarios.
 */
public class Cliente {
	private int IdCliente;
	private String NombreHero;
	private String Superpoder;
	private String Colores;
	private String TipoPersonaje;
	
	/**
	  * Constructor vacío
	  */
	public Cliente () {
		
	}

	/**
	 * Constructor con parámetros
	 * @param IdCliente
	 * @param NombreHero
	 * @param Superpoder
	 * @param Colores
	 * @param TipoPersonaje
	 */
	public Cliente (int IdCliente, String NombreHero, String Superpoder, String Colores, String TipoPersonaje) {
		this.IdCliente = IdCliente;
		this.NombreHero = NombreHero;
		this.Superpoder = Superpoder;
		this.Colores = Colores;
		this.TipoPersonaje = TipoPersonaje;
	}


	public int getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(int IdCliente){
		this.IdCliente = IdCliente;
		
	}
	public String getNombreHero() {
		return NombreHero;
	}

	public void setNombreHero(String NombreHero){
		this.NombreHero = NombreHero;
	}

	public String getSuperpoder() {
		return Superpoder;
	}

	public void setSuperpoder(String Superpoder){
		this.Superpoder = Superpoder;
	}
	public String getColores() {
		return Colores;
	}

	public void setColores(String Colores){
		this.Colores = Colores;
	}

	public String getTipoPersonaje() {
		return TipoPersonaje;
	}

	public void setTipoPersonaje(String TipoPersonaje){
		this.TipoPersonaje = TipoPersonaje;
	}

	}
