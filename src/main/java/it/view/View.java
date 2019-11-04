package it.view;

import it.negocio.Campana;
import it.negocio.EPS;
import it.negocio.IPS;
import it.negocio.Servicio;

import javax.jdo.JDODataStoreException;
import java.util.List;

public class View {

    public void printMenuAdmin() {
        System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Admin) ----------------------");
        System.out.println("---------------FUNCIONES DE REGISTRO---------------");
        System.out.println("1. Agregar un usuario");
        System.out.println("2. Agregar una EPS");
        System.out.println("3. Agregar una IPS");
        System.out.println("4. Agregar Servicio");
        System.out.println("5. Agregar Campana");
        System.out.println("6. Cancelar servicios de la campana");
        System.out.println("7. Deshabilitar servicios de salud");
        System.out.println("8. Registrar la reapertura de servicios de salud");
        System.out.println();
        System.out.println("---------------FUNCIONES DE CONSULTA---------------");
        System.out.println("9. Mostrar la cantidad de servicios prestados por cada ips durante un \n"
                + "periodo de tiempo y en el ano corrido");
        System.out.println("10. Mostrar los 20 servicios mas solicitados");
        System.out.println("11. Mostrar el indice de uso de cada uno de los servicios provistos");
        System.out.println("12. Mostrar los servicios que cumplen con cierta caracteristica");
        System.out.println("13. Mostrar la utilizacion de servicios de EPSAndes por un afiliado dado, \n"
                + "en un rango de fechas indicado");
        System.out.println("14. Analizar la operación de EP SAndes");
        System.out.println("15. Encontrar los afiliados exigentes");
        System.out.println("16. Encontrar los servicios que no tienen mucha demanda");
        System.out.println("0. Cerrar Conexion");

        System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");

    }

    public void printMenuPaciente() {
        System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Paciente) ----------------------");
        System.out.println("1. Reservar una cita");
        System.out.println("2. Salir");

    }

    public void printMenuMedico() {
        System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Medico) ----------------------");
        System.out.println("1. Registrar Orden");
        System.out.println("2. Salir");
    }

    public void printInicioSesion() {
        System.out.println("---------Inicio Sesion----------");
        System.out.println("Ingrese el usuario: ");
    }

    public void printMessage(String msj) {
        System.out.println(msj);
    }

    public void printErrMessage(Exception e) {
        String resultado = "************ Error en la ejecucion\n";
        resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
        resultado += "\n\nRevise datanucleus.log y project.log para mas detalles";
        System.out.println(resultado);
    }

    public void printServiciosMenu() {
        System.out.println("A) Procedimiento Medico");
        System.out.println("B) Hospitalizacion ");
        System.out.println("C) Examen Diagnostico");
        System.out.println("D) Terapia");
    }

    public void printRoles() {
        System.out.println("a) PACIENTE" + "\n"
                + "b) MEDICO" + "\n"
                + "c) GERENTE" + "\n"
                + "d) RECEPCIONISTA " + "\n"
                + "e) ORGANIZADOR CAMPAnA"
        );
    }

    private String darDetalleException(Exception e) {
        String resp = "";
        if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
            JDODataStoreException je = (JDODataStoreException) e;
            return je.getNestedExceptions()[0].getMessage();
        }
        return resp;
    }

    public void printEpsList(List<EPS> lista) {
        int i = 1;
        for (EPS eps : lista) {
            System.out.println(i + ". " + eps.getNombre());
            i++;
        }
    }

    public void printCampanas(List<Campana> lista){
        System.out.println("Id por campaña: ");
        int i = 1;
        for(Campana c : lista){
            System.out.println(i+". " + c.getId());
            i++;
        }
    }

    public void printIpsLista(List<IPS> list) {
        int i = 1;
        for (IPS ips : list) {
            System.out.println(i + ". " + ips.getNombre());
            i++;
        }
    }

    public void printListaServicios(List<Servicio> list) {
        int i = 1;
        for (Servicio ser : list) {
            System.out.println(i+". " + ser.toString());
            i++;
        }
    }

    public void printListaServiciosTotal(List<Servicio> list) {
        int i = 1;
        for (Servicio ser : list) {
            System.out.println(i+". ID =" + ser.getId()+ ", NOMBRE = " + ser.getNombre());
            i++;
        }
    }
    public void printListaOpcionesConsulta() {
        System.out.println("a) Rango fechas");
        System.out.println("b) Prestan a cierta Ips");
        System.out.println("c) Ha sido solicitado n veces en cierta fecha");
    }
}
