package it.negocio;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import it.persistencia.PersistenciaEPSAndes;;

public class EPSAndes {
    private static Logger log = Logger.getLogger(EPSAndes.class.getName());

    private PersistenciaEPSAndes ep;

    public EPSAndes() {
        ep = PersistenciaEPSAndes.getInstance();
    }

    public EPSAndes(JsonObject tableConfig) {
        ep = PersistenciaEPSAndes.getInstance(tableConfig);
    }

    public void cerrarUP() {
        ep.cerrarUnidadPersistencia();
    }

    // M�todos



    public Paciente registrarPaciente(long id, String nombre, String correo, Timestamp fNac, String estado, String tipoDoc) {
        log.info("Registrando paciente: " + nombre);
        Paciente pac = ep.registrarPaciente(id, nombre, correo, fNac, estado, tipoDoc);
        log.info("Registrando paciente: " + pac);
        return pac;
    }

    public Medico registrarMedico(long id, String nombre, String correo, String especialidad) {
        log.info("Registrando m�dico: " + nombre);
        Medico med = ep.registrarMedico(id, nombre, correo, especialidad);
        log.info("Registrando m�dico: " + med);
        return med;
    }


    public MedicoIps registrarMedicoAIps(long idIps, long idMedico) {
        log.info("Registrando medico: " + idMedico + " en IPS: " + idIps);
        MedicoIps im = ep.registrarMedicoAIps(idIps, idMedico);
        log.info("Registrando: " + im);
        return im;
    }





    public EPS registrarEPS(String nombre) {
        log.info("Registrando EPS: " + nombre);
        EPS eps = ep.registrarEPS(nombre);
        log.info("Registrando EPS: " + eps);
        return eps;
    }

    public List<EPS> darListaEps() {
        log.info("Consultando Eps�s");
        List<EPS> lista = ep.darListaEps();
        log.info("Consultando las eps�s: " + lista.size() + " eps�s agregadas");
        return lista;
    }

    public List<IPS> darListaIps() {
        log.info("Consultando Ips´s");
        List<IPS> lista = ep.darListaIps();
        log.info("Consultando las eps�s: " + lista.size() + " eps�s agregadas");
        return lista;
    }

    public List<Servicio> darListaServicios() {
        log.info("Consultando servicios: ");
        List<Servicio> lista = ep.darListaServicios();
        log.info("Consultando los servicios: " + lista.size());
        return lista;
    }

    public List<Medicamento> darListaMedicamentos() {
        log.info("Consultando medicamentos: ");
        List<Medicamento> lista = ep.darListaMedicamentos();
        log.info("Consultando los medicamentos: " + lista.size());
        return lista;
    }

    public List<Paciente> darListaPacientes() {
        log.info("Consultando pacientes");
        List<Paciente> lista = ep.darListaPacientes();
        log.info("Consultando los pacientes: " + lista.size());
        return lista;
    }

    public IPS registrarIPS(String nombre, String localizacion, int cantidad) {
        log.info("Registrando IPS: " + nombre);
        IPS ips = ep.registrarIPS(nombre, cantidad, localizacion);
        log.info("Registrando IPS: " + ips);
        return ips;
    }



    public Servicio registrarServicio(int capacidad, String nombre) {
        log.info("Registrando Servicio: " + nombre);
        Servicio ser = ep.registrarServicio(nombre, capacidad);
        log.info("Registrando Servicio: " + ser);
        return ser;
    }

    public ServicioIpsIps registrarServicioIps(long idIps, long idServicio) {
        log.info("Registrar Servicio: " + idServicio + " en: " + idIps);
        ServicioIpsIps ips_Ser = ep.registrarServicioIps(idIps, idServicio);
        log.info("Registrando Servicio: " + idServicio + " en: " + idIps);
        return ips_Ser;
    }

    public Orden registrarOrden(String desc) {
        log.info("Registrando Orden con la descripcion: " + desc);
        Orden orden = ep.registrarOrden(desc);
        log.info("Registrando orden: " + orden);
        return orden;
    }


    public OrdenMedicamento registrarOrdenConMedicamento(long idOrden, long idMed) {
        log.info("Registrando Orden: " + idOrden + " con Medicamento: " + idMed);
        OrdenMedicamento om = ep.registrarOrdenConMedicamento(idOrden, idMed);
        log.info("Registrar orden: " + om);
        return om;
    }

    public void registrarConsulta() {
        // TODO Auto-generated method stub

    }

    public void consultarHorarios() {
        // TODO Auto-generated method stub

    }

    public void reservarConsulta(Timestamp horario) {
        // TODO Auto-generated method stub

    }

    public int rfc1(Timestamp fecha1, Timestamp fecha2) {
        // TODO Auto-generated method stub
        log.info("Consultando cantidad de servicios prestados desde " + fecha1.toString() + " hasta " + fecha2.toString());
        int ser = ep.rfc1(fecha1, fecha2);
        log.info("Saliendo de consultar cantidad de servicios prestados");
        return ser;
    }
}
