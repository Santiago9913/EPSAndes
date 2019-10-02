package it.negocio;

public class OrdenServicios implements VOOrdenServicios {
	private long idOrden;
	private long idServicio;
	
	public OrdenServicios() {
		this.idOrden = 0;
		this.idServicio = 0;
	}
	
	/**
	 * @param idOrden
	 * @param idServicio
	 */
	public OrdenServicios(long idOrden, long idServicio) {
		this.idOrden = idOrden;
		this.idServicio = idServicio;
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
		return "OrdenServicios [idOrden=" + idOrden + ", idServicio=" + idServicio + "]";
	}
	
}
