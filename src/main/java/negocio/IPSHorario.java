package negocio;

public class IPSHorario implements VOIPSHorario {
	private long idIps;
	private long idHorario;
	
	/**
	 * @param idIps
	 * @param idHorario
	 */
	public IPSHorario() {
		this.idIps = 0;
		this.idHorario = 0;
	}
	
	/**
	 * @param idIps
	 * @param idHorario
	 */
	public IPSHorario(long idIps, long idHorario) {
		this.idIps = idIps;
		this.idHorario = idHorario;
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
	 * @return the idHorario
	 */
	public long getIdHorario() {
		return idHorario;
	}

	/**
	 * @param idHorario the idHorario to set
	 */
	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IPSHorario [idIps=" + idIps + ", idHorario=" + idHorario + "]";
	}
	
}
