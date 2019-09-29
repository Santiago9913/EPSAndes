package negocio;

public class Urgencia implements VOUrgencia {
	private long id;
	private int triage;
	private long idOrden;
	
	public Urgencia() {
		this.id = 0;
		this.triage = 0;
		this.idOrden = 0;
	}
	
	/**
	 * @param id
	 * @param triage
	 * @param idOrden
	 */
	public Urgencia(long id, int triage, long idOrden) {
		this.id = id;
		this.triage = triage;
		this.idOrden = idOrden;
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
	 * @return the triage
	 */
	public int getTriage() {
		return triage;
	}

	/**
	 * @param triage the triage to set
	 */
	public void setTriage(int triage) {
		this.triage = triage;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Urgencia [id=" + id + ", triage=" + triage + ", idOrden=" + idOrden + "]";
	}
	
}
