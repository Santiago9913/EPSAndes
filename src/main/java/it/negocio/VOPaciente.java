package it.negocio;

import java.sql.Timestamp;

public interface VOPaciente {
	public long getId();
	public String getNombre();
	public String getCorreo();
	public Timestamp getfNacimiento();
	public String getEstado();
	public String getTipoDocumento();
	public String toString();
}
