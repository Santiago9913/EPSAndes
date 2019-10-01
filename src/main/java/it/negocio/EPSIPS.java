package it.negocio;

public class EPSIPS implements VOEPSIPS {
	private long idEps;
	
	private long idIps;

	/**
	 * @param idEps
	 * @param idIps
	 */
	public EPSIPS() {
		this.idEps = 0;
		this.idIps = 0;
	}
	
	/**
	 * @param idEps
	 * @param idIps
	 */
	public EPSIPS(long idEps, long idIps) {
		this.idEps = idEps;
		this.idIps = idIps;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EPSIPS [idEps=" + idEps + ", idIps=" + idIps + "]";
	}
	
}
