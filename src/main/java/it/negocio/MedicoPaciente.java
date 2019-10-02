package it.negocio;

public class MedicoPaciente implements VOMedicoPaciente {
	private long idMedico;
	private long idPaciente;
	
	/**
	 * @param idMedico
	 * @param idPaciente
	 */
	public MedicoPaciente() {
		this.idMedico = 0;
		this.idPaciente = 0;
	}
	
	/**
	 * @param idMedico
	 * @param idPaciente
	 */
	public MedicoPaciente(long idMedico, long idPaciente) {
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MedicoPaciente [idMedico=" + idMedico + ", idPaciente=" + idPaciente + "]";
	}
	
}
