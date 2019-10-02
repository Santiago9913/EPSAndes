package it.negocio;

public class IPS implements VOIPS {
	private long id;
	private String localizacion;
	private int capacidad;
	
	/**
	 * @param id
	 * @param localizacion
	 * @param capacidad
	 */
	public IPS() {
		this.id = 0;
		this.localizacion = "";
		this.capacidad = 0;
	}
	
	/**
	 * @param id
	 * @param localizacion
	 * @param capacidad
	 */
	public IPS(long id, String localizacion, int capacidad) {
		this.id = id;
		this.localizacion = localizacion;
		this.capacidad = capacidad;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * @param localizacion the localizacion to set
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IPS [id=" + id + ", localizacion=" + localizacion + ", capacidad=" + capacidad + "]";
	}
	
}
