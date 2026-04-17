/**
 * 
 */
package Modelo;

/**
 * 
 */
public class Cita {
	 private int IdCita;
	 private int IdCliente;
	 private int IdTraje;
	 private int IdTaller;
	 private String Fecha;
	 private String Hora;
	 private int Duracion;

	    public Cita() {
	    }

	    public Cita(int IdCita, int IdCliente, int IdTraje, int IdTaller, String Fecha, String Hora, int Duracion ) {
	        this.IdCita = IdCita;
	        this.IdCliente = IdCliente;
	        this.IdTraje = IdTraje;
	        this.IdTaller = IdTaller;
	        this.Fecha = Fecha;
	        this.Hora = Hora;
	        this.Duracion = Duracion;
	    }

	    public int getIdCita() {
	        return IdCita;
	    }

	    public void setIdCita(int IdCita) {
	        this.IdCita = IdCita;
	    }

	    public int getIdCliente() {
	        return IdCliente;
	    }

	    public void setIdCliente(int IdCliente) {
	        this.IdCliente = IdCliente;
	    }

	    public int getIdTraje() {
	        return IdTraje;
	    }

	    public void setIdTraje(int IdTraje) {
	        this.IdTraje = IdTraje;
	    }

	    public int getIdTaller() {
	        return IdTaller;
	    }

	    public void setIdTaller(int IdTaller) {
	        this.IdTaller = IdTaller;
	    }

	    public String getFecha() {
	        return Fecha;
	    }

	    public void setFecha(String Fecha) {
	        this.Fecha = Fecha;
	    }

	    public String getHora() {
	        return Hora;
	    }

	    public void setHora(String Hora) {
	        this.Hora = Hora;
	    }
	    public int getDuracion() {
	        return Duracion;
	    }

	    public void setDuracion(int Duracon) {
	        this.Duracion = Duracion;
	    }
	    
	}
