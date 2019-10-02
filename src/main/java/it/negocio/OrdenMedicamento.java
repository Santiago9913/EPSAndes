package it.negocio;

public class OrdenMedicamento implements VOOrdenMedicamento {
	private long idOrden;
	private long idMedicamento;
	
	public OrdenMedicamento() {
		this.idOrden = 0;
		this.idMedicamento = 0;
	}
	
	/**
	 * @param idOrden
	 * @param idMedicamento
	 */
	public OrdenMedicamento(long idOrden, long idMedicamento) {
		this.idOrden = idOrden;
		this.idMedicamento = idMedicamento;
	}

	/**
	 * @return the idOrden
	 */
	public long getIdOrden() {
		return idOrden;
	}

	/**
	 * @param idOrden the idOrden to set
	 */
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}

	/**
	 * @return the idMedicamento
	 */
	public long getIdMedicamento() {
		return idMedicamento;
	}

	/**
	 * @param idMedicamento the idMedicamento to set
	 */
	public void setIdMedicamento(long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrdenMedicamento [idOrden=" + idOrden + ", idMedicamento=" + idMedicamento + "]";
	}
	
}
