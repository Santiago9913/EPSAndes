package it.negocio;

public class Procedimiento implements VOProcedimiento{
	private long idServicio;
	private String tipo;
	
	public Procedimiento() {
		this.idServicio = 0;
		this.tipo = "";
	}
	
	/**
	 * @param idServicio
	 * @param tipo
	 */
	public Procedimiento(long idServicio, String tipo) {
		this.idServicio = idServicio;
		this.tipo = tipo;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Procedimiento [idServicio=" + idServicio + ", tipo=" + tipo + "]";
	}
	
}
