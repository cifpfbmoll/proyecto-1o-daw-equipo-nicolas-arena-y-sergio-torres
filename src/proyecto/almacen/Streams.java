/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Nicolás
 */
public class Streams {

    /**
     * Esto es un metodo utilizado para guardar en un archivo Log.txt las acciones usadas en la base de datos, como por ejemplo un update o un delete.
     * @param mensaje Este es el mensaje enviado al metodo para que guarde.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void streamBuffer(String mensaje) throws FileNotFoundException, IOException { // probando
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Log.txt", true))) {
            Date fecha = new Date();
            escritor.write(fecha.toString() + " | " + mensaje);
            escritor.newLine();
            escritor.newLine();
        }
    }
}
