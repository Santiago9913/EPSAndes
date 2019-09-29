package negocio;

public class PacienteUrgencia implements VOPacienteUrgencia {
	private long idPaciente;
	private long idUrgencia;
	
	/**
	 * @param idPaciente
	 * @param idUrgencia
	 */
	public PacienteUrgencia() {
		this.idPaciente = 0;
		this.idUrgencia = 0;
	}
	
	/**
	 * @param idPaciente
	 * @param idUrgencia
	 */
	public PacienteUrgencia(long idPaciente, long idUrgencia) {
		this.idPaciente = idPaciente;
		this.idUrgencia = idUrgencia;
	}

	/**
	 * @return the idPaciente
	 */
	public long getIdPaciente() {
		return idPaciente;
	}

	/**
	 * @param idPaciente the idPaciente to set
	 */
	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	/**
	 * @return the idUrgencia
	 */
	public long getIdUrgencia() {
		return idUrgencia;
	}

	/**
	 * @param idUrgencia the idUrgencia to set
	 */
	public void setIdUrgencia(long idUrgencia) {
		this.idUrgencia = idUrgencia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PacienteUrgencia [idPaciente=" + idPaciente + ", idUrgencia=" + idUrgencia + "]";
	}
	
}
