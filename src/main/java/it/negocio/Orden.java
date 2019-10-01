package it.negocio;

public class Orden implements VOOrden {
	private long id;
	private long idUrgencia;
	
	public Orden() {
		this.id = 0;
		this.idUrgencia = 0;
	}
	
	/**
	 * @param id
	 * @param idUrgencia
	 */
	public Orden(long id, long idUrgencia) {
		this.id = id;
		this.idUrgencia = idUrgencia;
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
	 * @return the idUrgencia
	 */
	public long getIdUrgencia() {
		return idUrgencia;
	}

	/**
	 * @param idUrgencia the idUrgencia to set
	 */
	public void setIdUrgencia(long idUrgencia) {
		this.idUrgencia = idUrgencia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orden [id=" + id + ", idUrgencia=" + idUrgencia + "]";
	}
	
}
