package negocio;

public class Medico implements VOMedico {
	private long id;
	private String nombre;
	private String correo;
	private String especialidad;
	
	/**
	 * @param id
	 * @param nombre
	 * @param correo
	 * @param especialidad
	 */
	public Medico() {
		this.id = 0;
		this.nombre = "";
		this.correo = "";
		this.especialidad = "";
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param correo
	 * @param especialidad
	 */
	public Medico(long id, String nombre, String correo, String especialidad) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.especialidad = especialidad;
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

	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Medico [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", especialidad=" + especialidad
				+ "]";
	}
	
}
