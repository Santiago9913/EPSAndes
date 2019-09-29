package negocio;

public class MedicoUrgencia implements VOMedicoUrgencia {
	private long idMedico;
	private long idUrgencia;
	
	public MedicoUrgencia() {
		this.idMedico = 0;
		this.idUrgencia = 0;
	}
	
	/**
	 * @param idMedico
	 * @param idUrgencia
	 */
	public MedicoUrgencia(long idMedico, long idUrgencia) {
		this.idMedico = idMedico;
		this.idUrgencia = idUrgencia;
	}

	/**
	 * @return the idMedico
	 */
	public long getIdMedico() {
		return idMedico;
	}

	/**
	 * @param idMedico the idMedico to set
	 */
	public void setIdMedico(long idMedico) {
		this.idMedico = idMedico;
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
		return "MedicoUrgencia [idMedico=" + idMedico + ", idUrgencia=" + idUrgencia + "]";
	}
	
}
