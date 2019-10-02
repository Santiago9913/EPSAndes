package it.negocio;

public class Hospitalizacion implements VOHospitalizacion {
	private long idServicio;
	private long idVisitas;
	
	public Hospitalizacion() {
		this.idServicio = 0;
		this.idVisitas = 0;
	}
	
	/**
	 * @param idServicio
	 * @param idVisitas
	 */
	public Hospitalizacion(long idServicio, long idVisitas) {
		this.idServicio = idServicio;
		this.idVisitas = idVisitas;
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

	/**
	 * @return the idVisitas
	 */
	public long getIdVisitas() {
		return idVisitas;
	}

	/**
	 * @param idVisitas the idVisitas to set
	 */
	public void setIdVisitas(long idVisitas) {
		this.idVisitas = idVisitas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hospitalizacion [idServicio=" + idServicio + ", idVisitas=" + idVisitas + "]";
	}
	
}
