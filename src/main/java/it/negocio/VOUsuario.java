package it.negocio;

import java.util.Date;

public interface VOUsuario {

    public String getCorreo();
    public Date getFechaNacimiento();
    public long getId();
    public String getNombre();
    public String tipoDocumento();
    public String tipoUsuario();

}
