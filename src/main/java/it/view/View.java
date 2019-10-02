package it.view;

import javax.jdo.JDODataStoreException;

public class View {

	public void printMenu() {
		System.out.println("---------ISIS 2304 - Sistemas Transaccionales----------");
		System.out.println("---------------------Iteracion 1----------------------");
		System.out.println("1. Agregar un rol");
		System.out.println("2. Agregar un usuario");

		System.out.println("10. Cerrar Conexion");

		System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}

	public void printMessage(String msj) {
		System.out.println(msj);
	}

	public void printErrMessage(Exception e) {
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e); 
		resultado += "\n\nRevise datanucleus.log y project.log para m�s detalles";
		System.out.println(resultado);
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
