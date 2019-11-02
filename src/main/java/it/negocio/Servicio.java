package it.negocio;

import java.sql.Date;

public class Servicio implements VOServicio {
    private long id;
    private int capacidad;
    private String nombre;
    private String inhabilitado;
    private Date inicioInhabilitacion;
    private Date finInhabilitacion;
    private String reservado;

    public Servicio() {
        this.id = 0;
        this.capacidad = 0;
        this.nombre = "";
        this.inhabilitado = "";
        this.inicioInhabilitacion = null;
        this.finInhabilitacion = null;
        this.reservado = "";
    }

    /**
     * @param id
     * @param capacidad
     */
    public Servicio(long id, int capacidad, String nombre, String inhabilitado, Date inicioInhabilitacion, Date finInhabilitacion, String reservado) {
        this.id = id;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.inhabilitado = inhabilitado;
        this.inicioInhabilitacion = inicioInhabilitacion;
        this.finInhabilitacion = finInhabilitacion;
        this.reservado = reservado;
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

    public Date getInicioInhabilitacion() {
        return inicioInhabilitacion;
    }

    public Date getFinInhabilitacion() {
        return finInhabilitacion;
    }

    public String getReservado() {
        return reservado;
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

    public void setInicioInhabilitacion(Date inicioInhabilitacion) {
        this.inicioInhabilitacion = inicioInhabilitacion;
    }

    public void setFinInhabilitacion(Date finInhabilitacion) {
        this.finInhabilitacion = finInhabilitacion;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Servicio [id=" + id + ", capacidad=" + capacidad + ", nombre=" + nombre + "]";
    }

}
