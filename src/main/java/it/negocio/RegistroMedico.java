package it.negocio;

public class RegistroMedico implements VORegistroMedico {
	private long idMedico;
	private int numRegistro;

	public RegistroMedico() {
		this.idMedico = 0;
		this.numRegistro = 0;
	}
	
	/**
	 * @param idMedico
	 * @param numRegistro
	 */
	public RegistroMedico(long idMedico, int numRegistro) {
		this.idMedico = idMedico;
		this.numRegistro = numRegistro;
	}

	/**
	 * @return the idMedico
	 */
	public long getIdMedico() {
		return idMedico;
	}

	/**
	 * @param idMedico the idMedico to set
	 */
	public void setIdMedico(long idMedico) {
		this.idMedico = idMedico;
	}

	/**
	 * @return the numRegistro
	 */
	public int getNumRegistro() {
		return numRegistro;
	}

	/**
	 * @param numRegistro the numRegistro to set
	 */
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegistroMedico [idMedico=" + idMedico + ", numRegistro=" + numRegistro + "]";
	}
}
