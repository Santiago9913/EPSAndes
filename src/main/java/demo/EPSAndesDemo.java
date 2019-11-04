package demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.sun.xml.internal.ws.assembler.jaxws.MustUnderstandTubeFactory;
import it.negocio.Campana;
import it.negocio.EPSAndes;
import it.negocio.Usuario;
import org.apache.log4j.Logger;

import javax.jdo.JDODataStoreException;
import javax.swing.*;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EPSAndesDemo {

    private static Logger log = Logger.getLogger(EPSAndesDemo.class.getName());
    private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json";
    private JsonObject tableConfig;
    private EPSAndes epsAndes;
    private JsonObject guiConfig;

    public EPSAndesDemo () {
        tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
        epsAndes = new EPSAndes();
    }

    private JsonObject openConfig (String tipo, String archConfig)
    {
        JsonObject config = null;
        try
        {
            Gson gson = new Gson( );
            FileReader file = new FileReader (archConfig);
            JsonReader reader = new JsonReader ( file );
            config = gson.fromJson(reader, JsonObject.class);
            log.info ("Se encontró un archivo de configuración válido: " + tipo);
        }
        catch (Exception e)
        {
//			e.printStackTrace ();
            log.info ("NO se encontró un archivo de configuración válido");
            JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "EPSAndes App", JOptionPane.ERROR_MESSAGE);
        }
        return config;
    }

    /**
     * Demostracion de creacion de Campana
     */
    public void demoCampana() {
        try {
            Usuario org = new Usuario(1001169257, "Cristiano Ronaldo", "cr7@gmail.com", "CEDULA", "ORGANIZADOR CAMPANA");
            ArrayList<String> listSer = new ArrayList<>();
            listSer.add("SERVICIO222");
            listSer.add("SERVICIO123");
            Date fIni = Date.valueOf(LocalDate.of(2019, 01, 10));
            Date fFin = Date.valueOf(LocalDate.of(2019, 10, 20));
            String eps = "Colsegura";
            Campana c = epsAndes.registrarCampana(org, 200, listSer, fIni, fFin, eps);
            boolean error = false;
            if (c == null) {
                error = true;
            }

            String resultado = "Demo de creacion y listado de Campana\n\n";
            resultado += "\n\n************ Generando datos de prueba ************ \n";
            if (error)
            {
                resultado += "*** Exception creando tipo de bebida !!\n";
                resultado += "*** Es probable que esta campana ya existiera y hay restricción de UNICIDAD sobre la campana\n";
                resultado += "*** Revise el log de EPSAndes para más detalles\n";
            }
            resultado += "Adicionado la campana con id: " + 200 + "\n";
            resultado += "\n\n************ Ejecutando la demo ************ \n";
            resultado += "\n\n************ Limpiando la base de datos ************ \n";
            resultado += "\n Demo terminada";
        } catch (Exception e) {
            String resultado = generarMensajeError(e);
        }
    }

    private String generarMensajeError(Exception e) {
        String resultado = "************ Error en la ejecución\n";
        resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
        resultado += "\n\nRevise datanucleus.log y epsAndes.log para más detalles";
        return resultado;
    }

    private String darDetalleException(Exception e) {
        String resp = "";
        if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
        {
            JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
            return je.getNestedExceptions() [0].getMessage();
        }
        return resp;
    }
}
