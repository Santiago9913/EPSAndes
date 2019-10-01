package it.negocio;

public class IPSServicio implements VOIPSServicio {
	private long idIps;
	private long idServicio;
	
	/**
	 * @param idIps
	 * @param idServicio
	 */
	public IPSServicio() {
		this.idIps = 0;
		this.idServicio = 0;
	}
	
	/**
	 * @param idIps
	 * @param idServicio
	 */
	public IPSServicio(long idIps, long idServicio) {
		this.idIps = idIps;
		this.idServicio = idServicio;
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
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IPSServicio [idIps=" + idIps + ", idServicio=" + idServicio + "]";
	}
	
}
