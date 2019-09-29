package negocio;

public class EPSGerente implements VOEPSGerente {
	public long idEps;
	
	public long idGerente;

	/**
	 * @param idEps
	 * @param idGerente
	 */
	public EPSGerente() {
		this.idEps = 0;
		this.idGerente = 0;
	}
	
	/**
	 * @param idEps
	 * @param idGerente
	 */
	public EPSGerente(long idEps, long idGerente) {
		this.idEps = idEps;
		this.idGerente = idGerente;
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
	 * @return the idGerente
	 */
	public long getIdGerente() {
		return idGerente;
	}

	/**
	 * @param idGerente the idGerente to set
	 */
	public void setIdGerente(long idGerente) {
		this.idGerente = idGerente;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EPSGerente [idEps=" + idEps + ", idGerente=" + idGerente + "]";
	}
	
}
