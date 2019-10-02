package it.negocio;

public class MedicoConsulta implements VOMedicoConsulta {
	private long idMedico;
	private long idConsulta;
	
	/**
	 * @param idMedico
	 * @param idConsulta
	 */
	public MedicoConsulta() {
		this.idMedico = 0;
		this.idConsulta = 0;
	}
	
	/**
	 * @param idMedico
	 * @param idConsulta
	 */
	public MedicoConsulta(long idMedico, long idConsulta) {
		this.idMedico = idMedico;
		this.idConsulta = idConsulta;
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
	 * @return the idConsulta
	 */
	public long getIdConsulta() {
		return idConsulta;
	}

	/**
	 * @param idConsulta the idConsulta to set
	 */
	public void setIdConsulta(long idConsulta) {
		this.idConsulta = idConsulta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MedicoConsulta [idMedico=" + idMedico + ", idConsulta=" + idConsulta + "]";
	}
	
}
