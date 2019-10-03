package it.view;

import javax.jdo.JDODataStoreException;

public class View {

	public void printMenuAdmin() {
		System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
		System.out.println("---------------------EPSAndes (Admin) ----------------------");
		System.out.println("1. Agregar un rol");
		System.out.println("2. Agregar un usuario");
		System.out.println("3. Agregar una EPS");
		System.out.println("4. Agregar una IPS");

		System.out.println("10. Cerrar Conexion");

		System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}

	public void printInicioSesion() {
		System.out.println("---------Inicio Sesion----------");
		System.out.println("Ingrese el usuario: ");
	}

	public void printMessage(String msj) {
		System.out.println(msj);
	}

	public void printErrMessage(Exception e) {
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e); 
		resultado += "\n\nRevise datanucleus.log y project.log para más detalles";
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
		if(e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp; 
	}
}
