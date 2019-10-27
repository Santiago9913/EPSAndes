package it.negocio;

import java.sql.Date;

public class Campa�a {

    private long id;
    private int cantInscritos;
    private Date fInicio;
    private Date fFin;

    public Campa�a() {
        id = 0;
        cantInscritos = 0;
        fInicio = null;
        fFin = null;
    }

    public Campa�a(long id, int cantInscritos, Date fInicio, Date fFin) {
        this.id = id;
        this.cantInscritos = cantInscritos;
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
        return "Campa�a{" +
                "id=" + id +
                ", cantInscritos=" + cantInscritos +
                ", fInicio=" + fInicio +
                ", fFin=" + fFin +
                '}';
    }
}
