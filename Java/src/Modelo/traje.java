/**
 * 
 */
package Modelo;

/**
 * 
 */
public class traje {
	private int IdTraje;
	private int IdCliente;
	private String NombreTraje;
	private String Estado;

	
	public traje() {
		
	}
	
	public traje(int IdTraje, int IdCliente, String NombreTraje, String Estado ) {
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
	public void setNombreTraje() {
		this.NombreTraje = NombreTraje;
	}
	
	private String getEstado() {
		return Estado;
	}
	private void setEstado() {
		this.Estado = Estado;
	}
	
}
