package it.negocio;

import java.sql.Date;
import java.util.ArrayList;

public class Campana {

    private long id;
    private int cantInscritos;
    private int idOrg;
    private ArrayList<String> servs;
    private Date fInicio;
    private Date fFin;

    public Campana() {
        id = 0;
        cantInscritos = 0;
        fInicio = null;
        fFin = null;
    }

    public Campana(long id, int cantInscritos, int idOrg, ArrayList<String> servs, Date fInicio, Date fFin) {
        this.id = id;
        this.cantInscritos = cantInscritos;
        this.idOrg = idOrg;
        this.servs = servs;
        this.fInicio = fInicio;
        this.fFin = fFin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantInscritos() {
        return cantInscritos;
    }

    public void setCantInscritos(int cantInscritos) {
        this.cantInscritos = cantInscritos;
    }

    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }

    @Override
    public String toString() {
        return "Campana{" +
                "id=" + id +
                ", cantInscritos=" + cantInscritos +
                ", fInicio=" + fInicio +
                ", fFin=" + fFin +
                '}';
    }
}
