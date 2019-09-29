package negocio;

public class Medicamento implements VOMedicamento {
	private long id;
	private String nombre;
	
	public Medicamento() {
		this.id = 0;
		this.nombre = "";
	}
	
	/**
	 * @param id
	 * @param nombre
	 */
	public Medicamento(long id, String nombre) {
		this.id = id;
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
		return "Medicamento [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
