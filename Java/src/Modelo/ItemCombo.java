/**
 * 
 */
package Modelo;

/**
 * Clase que representa un elemento para un JComboBox.
 * 
 * Permite mostrar un nombre (texto visible) en la vista,
 * mientras internamente se trabaja con un identificador (id).
 */
public class ItemCombo {
	
	private int id;
	private String nombre;
	
	/**
     * Constructor con parámetros
     * @param id Identificador del elemento
     * @param nombre Nombre que se mostrará en el ComboBox
     */
	public ItemCombo(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		}
	
	/**
     * Devuelve el ID del elemento
     */
	public int getId() {
		return id;
	}
	
	/**
     * Devuelve el nombre del elemento
     */
	public String getNombre() {
		return nombre;
	}
	
	 /**
     * Método sobrescrito para mostrar el nombre en el JComboBox
     */
	@Override
	public String toString() {
		return nombre;
	}
	}
