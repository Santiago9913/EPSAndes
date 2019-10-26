package it.view;

import it.negocio.EPS;
import it.negocio.IPS;

import javax.jdo.JDODataStoreException;
import java.util.List;

public class View {

    private final static String YELLOW = "\\u001b[33m";

    public void printMenuAdmin() {
        System.out.println(YELLOW + "---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Admin) ----------------------");
        System.out.println("---------------FUNCIONES DE REGISTRO---------------");
        System.out.println("1. Agregar un usuario");
        System.out.println("2. Agregar una EPS");
        System.out.println("3. Agregar una IPS");
        System.out.print("4. Agregar Servicio");
        System.out.println();
        System.out.println("---------------FUNCIONES DE CONSULTA---------------");
        System.out.println("5. Mostrar la cantidad de servicios prestados por cada ips durante un \n"
                + "periodo de tiempo y en el a�o corrido");
        System.out.println("6. Mostrar los 20 servicios mas solicitados");
        System.out.println("7. Mostrar el indice de uso de cada uno de los servicios provistos");
        System.out.println("8. Mostrar los servicios que cumplen con cierta caracteristica");
        System.out.println("9. Mostrar la utilizacion de servicios de EPSAndes por un afiliado dado, \n"
                + "en un rango de fechas indicado");
        System.out.println("10. Cerrar Conexion");

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
                + "d) RECEPCIONISTA "
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
        for (EPS eps : lista) {
            int i = 1;
            System.out.println(i + ". " + eps.getNombre());
            i++;
        }
    }

    public void printIpsLista(List<IPS> list) {
        for (IPS ips : list) {
            int i = 1;
            System.out.println(i + ". " + ips.getNombre());
            i++;
        }
    }
}
