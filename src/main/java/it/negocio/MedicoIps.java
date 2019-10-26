package it.negocio;

public class MedicoIps implements VOMedicoIps {
	public long idIps;
	public long idMedico;
	
	/**
	 * @param idIps
	 * @param idMedico
	 */
	public MedicoIps() {
		this.idIps = 0;
		this.idMedico = 0;
	}
	
	/**
	 * @param idIps
	 * @param idMedico
	 */
	public MedicoIps(long idIps, long idMedico) {
		this.idIps = idIps;
		this.idMedico = idMedico;
	}

	/**
	 * @return the idIps
	 */
	public long getIdIps() {
		return idIps;
	}

	/**
	 * @param idIps the idIps to set
	 */
	public void setIdIps(long idIps) {
		this.idIps = idIps;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IPSMedico [idIps=" + idIps + ", idMedico=" + idMedico + "]";
	}
	
}
