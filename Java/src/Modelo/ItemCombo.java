/**
 * 
 */
package Modelo;

/**
 * el item combo class perimite el view comboBox mostar el nombre de usuario 
 * pero en el base de datos guarda el id y trabaja con el. 
 */
public class ItemCombo {
	private int id;
	private String nombre;
	
	public ItemCombo(int id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	
	public String toString() {
		return nombre;
	}
	

}
