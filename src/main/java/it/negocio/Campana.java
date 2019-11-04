package it.negocio;

import java.sql.Date;
import java.util.ArrayList;

public class Campana {

    private long id;
    private int cantidad_inscritos;
    private int idOrg;
    private ArrayList<String> servs;
    private Date fecha_Inicio;
    private Date fecha_Fin;

    public Campana() {
        id = 0;
        cantidad_inscritos = 0;
        fecha_Inicio = null;
        fecha_Fin = null;
    }

    public Campana(long id, int cantidad_inscritos, int idOrg, ArrayList<String> servs, Date fecha_Inicio, Date fecha_Fin) {
        this.id = id;
        this.cantidad_inscritos = cantidad_inscritos;
        this.idOrg = idOrg;
        this.servs = servs;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
    }

    public long getId() {
        return id;
    }

    public int getCantidad_inscritos() {
        return cantidad_inscritos;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public ArrayList<String> getServs() {
        return servs;
    }

    public Date getFecha_Inicio() {
        return fecha_Inicio;
    }

    public Date getFecha_Fin() {
        return fecha_Fin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCantidad_inscritos(int cantidad_inscritos) {
        this.cantidad_inscritos = cantidad_inscritos;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
    }

    public void setServs(ArrayList<String> servs) {
        this.servs = servs;
    }

    public void setFecha_Inicio(Date fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public void setFecha_Fin(Date fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    @Override
    public String toString() {
        return "Campana{" +
                "id=" + id +
                ", cantidad_inscritos=" + cantidad_inscritos +
                ", fecha_Inicio=" + fecha_Inicio +
                ", fecha_Fin=" + fecha_Fin +
                '}';
    }
}
