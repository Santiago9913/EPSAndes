package it.negocio;

public class IPS implements VOIPS {

    public long id;
    public String nombre;
    public long id_Eps;
    public int capacidad;
    public String localizacion;

    public IPS(){
        this.id = 0;
        this.nombre = "";
        this.id_Eps = 0;
        this.capacidad = 0;
        this.localizacion = "";
    }

    public IPS(long id, String nombre, long id_Eps, int capacidad, String localizacion){
        this.id = id;
        this.nombre = nombre;
        this.id_Eps = id_Eps;
        this.capacidad = capacidad;
        this.localizacion = localizacion;
    }



    public long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public long getId_Eps() {
        return this.id_Eps;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public String getLocalizacion() {
        return this.localizacion;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_Eps(long id_Eps) {
        this.id_Eps = id_Eps;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    @Override
    public String toString(){
        return "IPS[ " + "ID: " + id + ", NOMBRE: " + nombre + ", ID_EPS: " + id_Eps + ", CAPACIDAD: " + capacidad + ", LOCALIZACION: " + localizacion + " ]";
    }
}
