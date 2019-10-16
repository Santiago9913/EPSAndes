package it.view;

import javax.jdo.JDODataStoreException;

public class View {

    public void printMenuAdmin() {
        System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Admin) ----------------------");
        System.out.println("---------------FUNCIONES DE REGISTRO---------------");
        System.out.println("1. Agregar un rol");
        System.out.println("2. Agregar un usuario");
        System.out.println("3. Agregar una EPS");
        System.out.println("4. Agregar una IPS");
        System.out.print("5. Agregar Servicio");
        System.out.println();
        System.out.println("---------------FUNCIONES DE CONSULTA---------------");
        System.out.println("6. Mostrar la cantidad de servicios prestados por cada ips durante un \n"
                + "periodo de tiempo y en el a�o corrido");
        System.out.println("7. Mostrar los 20 servicios mas solicitados");
        System.out.println("8. Mostrar el indice de uso de cada uno de los servicios provistos");
        System.out.println("9. Mostrar los servicios que cumplen con cierta caracteristica");
        System.out.println("10. Mostrar la utilizacion de servicios de EPSAndes por un afiliado dado, \n"
                + "en un rango de fechas indicado");
        System.out.println("11. Cerrar Conexion");

        System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");

    }

    public void printMenuPaciente() {
        System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Paciente) ----------------------");
        System.out.println("0. Salir");
        System.out.println("1. Reservar una cita");

    }

    public void printMenuMedico() {
        System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
        System.out.println("---------------------EPSAndes (Medico) ----------------------");
        System.out.println("0. Salir");
        System.out.println("1. Registrar Orden");
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
}
