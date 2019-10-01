package it.negocio;

public class PacienteReserva implements VOPacienteReserva {
	public long idPaciente;
	public long idConsulta;
	
	/**
	 * @param idPaciente
	 * @param idConsulta
	 */
	public PacienteReserva() {
		this.idPaciente = 0;
		this.idConsulta = 0;
	}
	
	/**
	 * @param idPaciente
	 * @param idConsulta
	 */
	public PacienteReserva(long idPaciente, long idConsulta) {
		this.idPaciente = idPaciente;
		this.idConsulta = idConsulta;
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
		return "PacienteReserva [idPaciente=" + idPaciente + ", idConsulta=" + idConsulta + "]";
	}
	
}
