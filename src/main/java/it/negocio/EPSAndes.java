package it.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
        log.info("Consultando Ips");
        List<IPS> lista = ep.darListaIps();
        log.info("Consultando las ips: " + lista.size());
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

    public Usuario registrarUsuario(String rol, String nombre, Timestamp fechaNacimiento, String tipoDocumento, long numDoc, String correo) {
        log.info("Registrando usuario: " + numDoc);
        Usuario us = ep.registrarUsuario(rol, nombre, fechaNacimiento, tipoDocumento, numDoc, correo);
        log.info("Registrando usuario: " + us);
        return us;
    }

    public Paciente registrarPaciente(long numDocumento, long idEps, String estado) {
        log.info("Registrando paciente: " + numDocumento);
        Paciente pa = ep.registrarPaciente(numDocumento, idEps, estado);
        log.info("Registrando paciente: " + pa);
        return pa;
    }

    public Medico registrarMedico(long numDoc, long numRegistro, String tipo) {
        log.info("Registrando medico: " + numRegistro);
        Medico me = ep.registrarMedico(numDoc, numRegistro, tipo);
        log.info("Registrando medico: " + me);
        return me;
    }

    public MedicoIps registrarMedicoAIps(long numDoc, long idIps) {
        log.info("Registrando medico: " + numDoc + " a Ips: " + idIps);
        MedicoIps mip = ep.registrarMedicoAIps(numDoc, idIps);
        log.info("Registrando medico: " + numDoc + " en Ips: " + idIps);
        return mip;
    }


    public IPS registrarIPS(String nombre, long idEps, int capacidad, String localizacion) {
        log.info("Registrando IPS: " + nombre);
        IPS ips = ep.registrarIPS(nombre, idEps, capacidad, localizacion);
        log.info("Registrando IPS: " + ips);
        return ips;
    }


    public Servicio registrarServicio(int capacidad, String nombre) {
        log.info("Registrando Servicio: " + nombre);
        Servicio ser = ep.registrarServicio(nombre, capacidad);
        log.info("Registrando Servicio: " + ser);
        return ser;
    }


    public Orden registrarOrden(String desc) {
        log.info("Registrando Orden con la descripcion: " + desc);
        Orden orden = ep.registrarOrden(desc);
        log.info("Registrando orden: " + orden);
        return orden;
    }

    public Campana registrarCampana(Usuario org, int participantes, ArrayList<Integer> servs, Date f_inicio, Date f_fin) {
        log.info("Adicionando campana");
        Campana campana = ep.registrarCampana(org, participantes, servs, f_inicio, f_fin);
        log.info("Saliendo de adicionar campana");
        return campana;
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

    public Object reqConsulta1(Date f_inicio, Date f_fin, int año) {
        log.info("Calculando la cantidad de servicios prestados por una IPS en el periodo: " + f_inicio.toString() + " - " +
                    f_fin.toString() + ", en el año: " + año);
        Object resp = ep.reqConsulta1(f_inicio, f_fin, año);
        return resp;
    }

    public Object reqConsulta2(Date f_inicio, Date f_fin) {
        log.info("Calculando los 20 servicios más solicitados en el periodo " + f_inicio.toString() + " - " + f_fin.toString());
        Object resp = ep.reqConsulta2(f_inicio, f_fin);
        return resp;
    }

    public Object reqConsulta5(Date f_inicio, Date f_fin, int idPac) {
        log.info("Consultando la utilización de servicios del afiliado con id " + idPac + " en el periodo: " +
                f_inicio + " - " + f_fin);
        Object resp = ep.reqConsulta5(f_inicio, f_fin, idPac);
        return resp;
    }

    public Object reqConsulta7() {
        log.info("Consultando los afiliados exigentes");
        Object resp = ep.reqConsulta7();
        return resp;
    }

    public void reabrirServicios(List<Integer> listSer) {
        log.info("Reabriendo servicios: ");
        Iterator<Integer> it = listSer.iterator();
        while (it.hasNext()) {
            Integer current = it.next();
            log.info(current + ", ");
        }
        ep.reabrirServicios(listSer);
    }
}
