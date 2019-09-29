package negocio;

public class IPSSecretaria implements VOIPSSecretaria {
	public long idIps;
	public long idSecretaria;
	
	/**
	 * @param idIps
	 * @param idSecretaria
	 */
	public IPSSecretaria() {
		this.idIps = 0;
		this.idSecretaria = 0;
	}
	
	/**
	 * @param idIps
	 * @param idSecretaria
	 */
	public IPSSecretaria(long idIps, long idSecretaria) {
		this.idIps = idIps;
		this.idSecretaria = idSecretaria;
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
	 * @return the idSecretaria
	 */
	public long getIdSecretaria() {
		return idSecretaria;
	}

	/**
	 * @param idSecretaria the idSecretaria to set
	 */
	public void setIdSecretaria(long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IPSSecretaria [idIps=" + idIps + ", idSecretaria=" + idSecretaria + "]";
	}
	
}
