package it.negocio;

public class EPSPaciente implements VOEPSPaciente {
	private long idEps;
	
	private long idPaciente;

	/**
	 * @param idEps
	 * @param idPaciente
	 */
	public EPSPaciente() {
		this.idEps = 0;
		this.idPaciente = 0;
	}
	
	/**
	 * @param idEps
	 * @param idPaciente
	 */
	public EPSPaciente(long idEps, long idPaciente) {
		this.idEps = idEps;
		this.idPaciente = idPaciente;
	}

	/**
	 * @return the idEps
	 */
	public long getIdEps() {
		return idEps;
	}

	/**
	 * @param idEps the idEps to set
	 */
	public void setIdEps(long idEps) {
		this.idEps = idEps;
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
		return "EPSPaciente [idEps=" + idEps + ", idPaciente=" + idPaciente + "]";
	}
	
}
