package it.negocio;

public class Administrador implements VOAdministrador {
	private long id;
	private String nombre;
	private String correo;
	
	/**
	 * @param id
	 * @param nombre
	 * @param correo
	 */
	public Administrador() {
		this.id = 0;
		this.nombre = "";
		this.correo = "";
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param correo
	 */
	public Administrador(long id, String nombre, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
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

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Administrador [id=" + id + ", nombre=" + nombre + ", correo=" + correo + "]";
	}
	
}
