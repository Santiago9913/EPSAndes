package it.negocio;

public class Servicio implements VOServicio {
	protected long id;
	protected int capacidad;
	protected String nombre;
	
	public Servicio() {
		this.id = 0;
		this.capacidad = 0;
		this.nombre = "";
	}
	
	/**
	 * @param id
	 * @param capacidad
	 */
	public Servicio(long id, int capacidad, String nombre) {
		this.id = id;
		this.capacidad = capacidad;
		this.nombre = nombre;
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
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", capacidad=" + capacidad + ", nombre=" + nombre + "]";
	}
	
}
