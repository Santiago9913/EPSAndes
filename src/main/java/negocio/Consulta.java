package negocio;

public class Consulta implements VOConsulta {
	private long id;
	private char cumplida;
	private long idOrden;
	private long idOrdenPrevia;
	
	public Consulta() {
		this.id = 0;
		this.cumplida = 0;
		this.idOrden = 0;
		this.idOrdenPrevia = 0;
	}
	
	/**
	 * @param id
	 * @param cumplida
	 * @param idOrden
	 * @param idOrdenPrevia
	 */
	public Consulta(long id, char cumplida, long idOrden, long idOrdenPrevia) {
		this.id = id;
		this.cumplida = cumplida;
		this.idOrden = idOrden;
		this.idOrdenPrevia = idOrdenPrevia;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the cumplida
	 */
	public char getCumplida() {
		return cumplida;
	}

	/**
	 * @param cumplida the cumplida to set
	 */
	public void setCumplida(char cumplida) {
		this.cumplida = cumplida;
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
	 * @return the idOrdenPrevia
	 */
	public long getIdOrdenPrevia() {
		return idOrdenPrevia;
	}

	/**
	 * @param idOrdenPrevia the idOrdenPrevia to set
	 */
	public void setIdOrdenPrevia(long idOrdenPrevia) {
		this.idOrdenPrevia = idOrdenPrevia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Consulta [id=" + id + ", cumplida=" + cumplida + ", idOrden=" + idOrden + ", idOrdenPrevia="
				+ idOrdenPrevia + "]";
	}
	
}
