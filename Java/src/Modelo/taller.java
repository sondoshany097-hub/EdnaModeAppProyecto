/**
 * 
 */
package Modelo;

/**
 * Clase que representa un taller o sala.
 * Define sus atributos y métodos de acceso (getters y setters).
 */
public class Taller {
	private int IdTaller;
	private String NombreSala;
	private String TipoSala;

	/**
     * Constructor vacío
     */
	public Taller() {
		
	}
	
	/**
     * Constructor con parámetros
     * @param IdTaller
     * @param NombreSala
     * @param TipoSala
     */
	public Taller (int IdTaller, String NombreSala, String TipoSala ) {
		this.IdTaller = IdTaller;
		this.NombreSala = NombreSala;
		this.TipoSala = TipoSala;
	}

	public int getIdTaller() {
		return IdTaller;
		
	}

	public void setIdTaller(int IdTaller) {
		this.IdTaller = IdTaller;
		
		
	}

	public String getNombresala() {
		return NombreSala;
		
	}
	public void setNombreSala(String NombreSala) {
		this.NombreSala = NombreSala;
	}

	public String getTiposala() {
		return TipoSala;
		
	}
	public void setTipoSala(String TipoSala) {
		this.TipoSala = TipoSala;
	}
	}
