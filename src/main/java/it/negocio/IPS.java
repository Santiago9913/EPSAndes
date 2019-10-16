package it.negocio;

public class IPS implements VOIPS {
    private long id;
    private String localizacion;
    private int capacidad;
    private String nombre;

    /**
     * @param id
     * @param localizacion
     * @param capacidad
     */
    public IPS() {
        this.id = 0;
        this.localizacion = "";
        this.capacidad = 0;
        this.nombre = "";
    }

    /**
     * @param id
     * @param localizacion
     * @param capacidad
     */
    public IPS(long id, String nombre, String localizacion, int capacidad) {
        this.id = id;
        this.localizacion = localizacion;
        this.capacidad = capacidad;
        this.nombre = nombre;
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
     * @return
     */
    public java.lang.String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the localizacion
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion the localizacion to set
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
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


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IPS [id=" + id + ", Nombre=" + nombre + ", localizacion=" + localizacion + ", capacidad=" + capacidad + "]";
    }

}
