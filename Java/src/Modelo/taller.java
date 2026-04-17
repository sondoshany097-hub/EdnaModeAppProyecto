/**
 * 
 */
package Modelo;

/**
 * 
 */
public class Taller {
	private int IdTaller;
	private String NombreSala;
	private String TipoSala;


	public Taller() {
		
	}

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
