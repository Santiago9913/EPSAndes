package negocio;

import java.sql.Timestamp;

public class Paciente implements VOPaciente {
	private long id;
	private String nombre;
	private String correo;
	private Timestamp fNacimiento;
	private String estado;
	private String tipoDocumento;
	
	/**
	 * @param id
	 * @param nombre
	 * @param correo
	 * @param fNacimiento
	 * @param estado
	 * @param tipoDocumento
	 */
	public Paciente() {
		this.id = 0;
		this.nombre = "";
		this.correo = "";
		this.fNacimiento = null;
		this.estado = "";
		this.tipoDocumento = "";
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param correo
	 * @param fNacimiento
	 * @param estado
	 * @param tipoDocumento
	 */
	public Paciente(long id, String nombre, String correo, Timestamp fNacimiento, String estado, String tipoDocumento) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.fNacimiento = fNacimiento;
		this.estado = estado;
		this.tipoDocumento = tipoDocumento;
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
	 * @return the fNacimiento
	 */
	public Timestamp getfNacimiento() {
		return fNacimiento;
	}

	/**
	 * @param fNacimiento the fNacimiento to set
	 */
	public void setfNacimiento(Timestamp fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", fNacimiento=" + fNacimiento
				+ ", estado=" + estado + ", tipoDocumento=" + tipoDocumento + "]";
	}
	
}
