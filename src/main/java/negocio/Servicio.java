package negocio;

public class Servicio implements VOServicio {
	private long id;
	private int capacidad;
	
	public Servicio() {
		this.id = 0;
		this.capacidad = 0;
	}
	
	/**
	 * @param id
	 * @param capacidad
	 */
	public Servicio(long id, int capacidad) {
		this.id = id;
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
		return "Servicio [id=" + id + ", capacidad=" + capacidad + "]";
	}
	
}
