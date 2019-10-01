package it.negocio;

import java.sql.Timestamp;

public interface VOHorario {
	public long getId();
	public Timestamp gethInicio();
	public Timestamp gethFin();
	public String toString();
}
