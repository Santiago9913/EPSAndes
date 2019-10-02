package it.negocio;

import java.sql.Timestamp;

public class Horario implements VOHorario {
	private long id;
	private Timestamp hInicio;
	private Timestamp hFin;
	
	public Horario() {
		this.id = 0;
		this.hInicio = null;
		this.hFin = null;
	}
	
	/**
	 * @param id
	 * @param hInicio
	 * @param hFin
	 */
	public Horario(long id, Timestamp hInicio, Timestamp hFin) {
		this.id = id;
		this.hInicio = hInicio;
		this.hFin = hFin;
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
	 * @return the hInicio
	 */
	public Timestamp gethInicio() {
		return hInicio;
	}

	/**
	 * @param hInicio the hInicio to set
	 */
	public void sethInicio(Timestamp hInicio) {
		this.hInicio = hInicio;
	}

	/**
	 * @return the hFin
	 */
	public Timestamp gethFin() {
		return hFin;
	}

	/**
	 * @param hFin the hFin to set
	 */
	public void sethFin(Timestamp hFin) {
		this.hFin = hFin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Horario [id=" + id + ", hInicio=" + hInicio + ", hFin=" + hFin + "]";
	}
	
}
