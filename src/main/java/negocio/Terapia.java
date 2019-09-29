package negocio;

public class Terapia extends Servicio implements VOTerapia{
	private long idServicio;
	private int numSesiones;
	private String tipo;
	
	public Terapia() {
		this.idServicio = getId();
		this.nombre = "Terapia";
		this.numSesiones = 0;
		this.tipo = "";
	}
	
	/**
	 * @param idServicio
	 * @param numSesiones
	 * @param tipo
	 */
	public Terapia(long idServicio, int numSesiones, String tipo) {
		this.idServicio = getId();
		this.numSesiones = numSesiones;
		this.tipo = tipo;
	}
	
	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the numSesiones
	 */
	public int getNumSesiones() {
		return numSesiones;
	}

	/**
	 * @param numSesiones the numSesiones to set
	 */
	public void setNumSesiones(int numSesiones) {
		this.numSesiones = numSesiones;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Terapia [idServicio=" + idServicio + ", numSesiones=" + numSesiones + ", tipo=" + tipo + "]";
	}
	
}
