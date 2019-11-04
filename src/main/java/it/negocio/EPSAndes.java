package it.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
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

    public List<Servicio> darListaServicios(long idIps) {
        log.info("Consultando servicios: ");
        List<Servicio> lista = ep.darListaServicios(idIps);
        log.info("Consultando los servicios: " + lista.size());
        return lista;
    }

    public List<Servicio> darListaServiciosReservados(long idIps) {
        log.info("Consultando servicios: ");
        List<Servicio> lista = ep.darListaServiciosReservados(idIps);
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

    public List<Campana> getCampanas(){
        log.info("Consultando campanas...");
        List<Campana> list = ep.getCampanas();
        log.info("Consultando campanas..." + list.size());
        return list;
    }

    public Usuario registrarUsuario(long id, long idCampana, Date fechaNac, String nombre, String correo, String tipoDocumento, String tipoUsuario) {
        log.info("Registrando usuario: " + id);
        Usuario us = ep.registrarUsuario(id, idCampana, fechaNac, nombre, correo, tipoDocumento, tipoUsuario);
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


    public boolean deshabilitarServicio(long idServicio, long idIps, Date inicio, Date fin) {
        log.info("Actualizando Servicio: " + idServicio);
        boolean ser = ep.deshabilitarServicio(idServicio, idIps, inicio, fin);
        log.info("Actualizando Servicio: " + ser);
        return ser;
    }

    public List<Servicio> buscarServiciosPorFecha(Date inicio, Date fin) {
        log.info("Recopilando servicios...");
        List<Servicio> servicios = ep.buscarServiciosPorFechas(inicio, fin);
        log.info("Recopilando servicios...");
        return servicios;
    }

    public List<IPS> darServicioEnIps(long idServicio) {
        log.info("Buscando en Ips´s");
        List<IPS> list = ep.darServicioEnIps(idServicio);
        log.info("Buscando en Ips´s " + list.size());
        return list;
    }

    public List<Servicio> darServiciosCampana(long idCampana){
        log.info("Buscando Campanas...");
        List<Servicio> list = ep.darServiciosCampana(idCampana);
        log.info("Buscando Campanas..."+ list.size());
        return list;
    }

    public boolean cancelarServicioCampana(long idServicio, long idIps){
        log.info("Cancelando servicio: " + idServicio + "en campana: " + idIps);
        boolean can = ep.cancelarServicioCampana(idServicio, idIps);
        log.info("Cancelando servicio en campana: " + "("+idIps + " , " +idIps+")");
        return can;
    }


    public Orden registrarOrden(String desc) {
        log.info("Registrando Orden con la descripcion: " + desc);
        Orden orden = ep.registrarOrden(desc);
        log.info("Registrando orden: " + orden);
        return orden;
    }



    public Campana registrarCampana(Usuario org, int participantes, ArrayList<String> servs, Date f_inicio, Date f_fin, String eps) {
        log.info("Adicionando campana");
        Campana campana = ep.registrarCampana(org, participantes, servs, f_inicio, f_fin, eps);
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

    public Object reqConsulta1(Date f_inicio, Date f_fin) {
        log.info("Calculando la cantidad de servicios prestados por una IPS en el periodo: " + f_inicio.toString() + " - " +
                f_fin.toString());
        Object resp = ep.reqConsulta1(f_inicio, f_fin);
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

    public void reabrirServicios(Hashtable<Integer, ArrayList<Integer>> listSer) {
        log.info("Reabriendo los servicios solicitados ");
        ep.reabrirServicios(listSer);
    }

}
