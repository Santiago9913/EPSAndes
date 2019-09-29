package negocio;

public class EPSAdmin implements VOEPSAdmin {
	private long idEps;
	
	private long idAdmin;
	
	public EPSAdmin() {
		this.idEps = 0;
		this.idAdmin = 0;
	}
	
	/**
	 * @param idEps
	 * @param idAdmin
	 */
	public EPSAdmin(long idEps, long idAdmin) {
		this.idEps = idEps;
		this.idAdmin = idAdmin;
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
	 * @return the idAdmin
	 */
	public long getIdAdmin() {
		return idAdmin;
	}

	/**
	 * @param idAdmin the idAdmin to set
	 */
	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EPSAdmin [idEps=" + idEps + ", idAdmin=" + idAdmin + "]";
	}

}
