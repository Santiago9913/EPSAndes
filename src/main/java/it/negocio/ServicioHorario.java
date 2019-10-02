package it.negocio;

public class ServicioHorario implements VOServicioHorario {
	private long idServicio;
	private long idHorario;
	
	public ServicioHorario() {
		this.idServicio = 0;
		this.idHorario = 0;
	}
	
	/**
	 * @param idServicio
	 * @param idHorario
	 */
	public ServicioHorario(long idServicio, long idHorario) {
		this.idServicio = idServicio;
		this.idHorario = idHorario;
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
		return "ServicioHorario [idServicio=" + idServicio + ", idHorario=" + idHorario + "]";
	}
	
}
