package it.negocio;

import java.util.Date;

public class Usuario implements VOUsuario {

    protected String nombre;
    protected Date fechaNacimiento;
    protected long id;
    protected String correo;
    protected String tipoDocumento;
    protected String tipoUsuario;

    public Usuario(){
        this.nombre = "";
        this.fechaNacimiento = null;
        this.id = 0;
        this.correo = "";
        this.tipoDocumento = "";
        this.tipoUsuario = "";
    }


    public Usuario(String nombre, Date fechaNacimiento, long id, String correo, String tipoDocumento, String tipoUsuario){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.correo = correo;
        this.tipoDocumento = tipoDocumento;
        this.tipoUsuario = tipoUsuario;
    }


    public String getCorreo() {
        return this.correo;
    }


    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }


    public long getId() {
        return this.id;
    }


    public String getNombre() {
        return this.nombre;
    }


    public String tipoDocumento() {
        return this.tipoDocumento;
    }


    public String tipoUsuario() {
        return this.tipoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString(){
        return "Tipo Usuario = " + tipoUsuario + " [ " + "Nombre: " + nombre + ", " +  "ID: " + id + ", " + "Tipo Doc: " +  tipoDocumento + ", " + "Correo: " +  correo  + " ]";
    }
}
