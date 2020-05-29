/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Nicolás
 */
public class Streams {

    static String rutaDestino = "Log.txt";

    public static void streamBuffer(String mensaje) throws FileNotFoundException, IOException { // probando
        File salida = new File(rutaDestino);
        BufferedWriter escribir = new BufferedWriter(new FileWriter(salida));
        escribir.write(mensaje);
    }
}
