/**
 * 
 */
package Modelo;

/**
 * 
 */
public class cliente {
	
	private int IdCliente;
	private String NombreHero;
	private String Superpoder;
	private String Colores;
	private String TipoPersonaje;
	
public cliente () {
	
	
}

public cliente (int IdCliente, String NombreHero, String Superpoder, String Colores, String TipoPersonaje) {
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

public String TipoPersonaje() {
	return TipoPersonaje;
}

public void setTipoPersonaje(String TipoPersonaje){
	this.TipoPersonaje = TipoPersonaje;
}

}
