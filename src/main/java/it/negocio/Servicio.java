package it.negocio;

import java.sql.Date;

public class Servicio implements VOServicio {
    public long id;
    public long id_ips;
    public int capacidad;
    public String nombre;
    public String inhabilitado;
    public Date inicio_Inhabilitacion;
    public Date fin_Inhabilitacion;
    public String reservado;
    public double porcentajeUso;

    public Servicio() {
        this.id = 0;
        this.capacidad = 0;
        this.nombre = "";
        this.inhabilitado = "";
        this.inicio_Inhabilitacion = null;
        this.fin_Inhabilitacion = null;
        this.reservado = "";
        this.id_ips = 0;
        this.porcentajeUso = 0;
    }

    /**
     * @param id
     * @param capacidad
     */
    public Servicio(long id, int capacidad, String nombre, String inhabilitado, Date inicio_Inhabilitacion, Date fin_Inhabilitacion, String reservado, long id_ips) {
        this.id = id;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.inhabilitado = inhabilitado;
        this.inicio_Inhabilitacion = inicio_Inhabilitacion;
        this.fin_Inhabilitacion = fin_Inhabilitacion;
        this.reservado = reservado;
        this.id_ips = id_ips;
    }

    public Servicio(long id, String nombre, double porcentajeUso){
        this.id = id;
        this.nombre = nombre;
        this.porcentajeUso = porcentajeUso;
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
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    public String getInhabilitado() {
        return inhabilitado;
    }

    public Date getinicio_Inhabilitacion() {
        return inicio_Inhabilitacion;
    }

    public Date getfin_Inhabilitacion() {
        return fin_Inhabilitacion;
    }

    public String getReservado() {
        return reservado;
    }

    public long getId_ips() {
        return id_ips;
    }

    public double getPorcentajeUso() {
        return porcentajeUso;
    }

    public void setPorcentajeUso(double porcentajeUso) {
        this.porcentajeUso = porcentajeUso;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInhabilitado(String inhabilitado) {
        this.inhabilitado = inhabilitado;
    }

    public void setinicio_Inhabilitacion(Date inicio_Inhabilitacion) {
        this.inicio_Inhabilitacion = inicio_Inhabilitacion;
    }

    public void setfin_Inhabilitacion(Date fin_Inhabilitacion) {
        this.fin_Inhabilitacion = fin_Inhabilitacion;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public void setId_ips(long id_ips) {
        this.id_ips = id_ips;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Servicio [id=" + id + ", capacidad=" + capacidad + ", nombre=" + nombre + ", inicio inhabilitacion= " + inicio_Inhabilitacion + ", fin inhabilitacion= "
                + fin_Inhabilitacion + ", reservado= " + reservado + ", inhabilitado= " + inhabilitado + "]";
    }

}
