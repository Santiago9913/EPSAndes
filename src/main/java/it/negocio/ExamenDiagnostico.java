package it.negocio;

public class ExamenDiagnostico implements VOExamenDiagnostico {
	private long idServicio;
	private String resultados;
	
	public ExamenDiagnostico() {
		this.idServicio = 0;
		this.resultados = "";
	}
	
	/**
	 * @param idServicio
	 * @param resultados
	 */
	public ExamenDiagnostico(long idServicio, String resultados) {
		this.idServicio = idServicio;
		this.resultados = resultados;
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
	 * @return the resultados
	 */
	public String getResultados() {
		return resultados;
	}

	/**
	 * @param resultados the resultados to set
	 */
	public void setResultados(String resultados) {
		this.resultados = resultados;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamenDiagnostico [idServicio=" + idServicio + ", resultados=" + resultados + "]";
	}
	
}
