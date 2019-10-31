package it.negocio;

public class Consulta {

    private long id;
    private long id_paciente;
    private long id_medico;
    private long id_orden;
    private String cumplida;
    private int capacidad;

    public Consulta() {
        this.id = 0;
        this.id_paciente = 0;
        this.id_medico = 0;
        this.id_orden = 0;
        this.cumplida = "";
        this.capacidad = 0;
    }

    public Consulta(long id, long id_paciente, long id_medico, long id_orden, String cumplida, int capacidad) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.id_orden = id_orden;
        this.cumplida = cumplida;
        this.capacidad = capacidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(long id_paciente) {
        this.id_paciente = id_paciente;
    }

    public long getId_medico() {
        return id_medico;
    }

    public void setId_medico(long id_medico) {
        this.id_medico = id_medico;
    }

    public long getId_orden() {
        return id_orden;
    }

    public void setId_orden(long id_orden) {
        this.id_orden = id_orden;
    }

    public String getCumplida() {
        return cumplida;
    }

    public void setCumplida(String cumplida) {
        this.cumplida = cumplida;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Orden: [ " + "ID: " + id + ", ID_PACIENTE: " + id_paciente + ", ID_MEDICO: " + id_medico + ", ID_ORDEN: " + id_orden + ", CUMPLIDA: " + cumplida + ", CAPACIDAD: " + capacidad + " ]";
    }
}
