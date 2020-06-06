/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import static proyecto.almacen.Menus.crearConexion;
import static proyecto.almacen.Menus.menuCambio;
import static proyecto.almacen.Streams.streamBuffer;

/**
 *
 * @author Nicolás
 */
public class OpcionesAdministrador {

    static Scanner lector = new Scanner(System.in);

    /**
     * Con este metodo el administrador puede crear un objeto nuevo de los que hay y guardarlo en la base de datos.
     * @param seleccion es la seleccion del objeto que quiere crear, al usar
     * esta variable te saldran las caracteristicas del objeto en concreto
     * para que lo rellenes. 
     * @throws SQLException
     * @throws IOException 
     */
    public static void crearObjeto(String seleccion) throws SQLException, IOException {
        System.out.println("Dime la id que quieres poner:");
        int id = Integer.parseInt(lector.nextLine());
        System.out.println("Dime el nombre:");
        String nombre = lector.nextLine();
        System.out.println("Dime el stock:");
        int stock = Integer.parseInt(lector.nextLine());
        System.out.println("Dime el precio:");
        float precio = Float.parseFloat(lector.nextLine()); // Hasta aqui son los atributos comunes en los distintos objetos.
        if ("alimentos".equals(seleccion)) {
            System.out.println("Dime la fecha de caducidad con el formato yyyy-MM-dd:"); // La fecha hay que meterla en este formato aunque no entendemos porque funciona
            //convertir el string en date
            String fechaCaducidad = lector.nextLine(); //no funciona
            parseFecha(fechaCaducidad);
            try (Connection con = crearConexion()) {
                PreparedStatement prepStat = con.prepareStatement("INSERT INTO alimentos VALUES (?,?,?,?,?)");
                prepStat.setInt(1, id);
                prepStat.setString(2, nombre);
                prepStat.setString(3, fechaCaducidad);
                prepStat.setInt(4, stock);
                prepStat.setFloat(5, precio);
                prepStat.executeUpdate();
            }
        } else if ("ropa".equals(seleccion)) {
            System.out.println("Dime el tamaño de la ropa:");
            String tamaño = lector.nextLine();
            try (Connection con = crearConexion()) {
                PreparedStatement prepStat = con.prepareStatement("INSERT INTO ropa VALUES (?,?,?,?,?)");
                prepStat.setInt(1, id);
                prepStat.setString(2, nombre);
                prepStat.setString(3, tamaño);
                prepStat.setInt(4, stock);
                prepStat.setFloat(5, precio);
                prepStat.executeUpdate();
            }
        } else if ("muebles".equals(seleccion)) {
            System.out.println("Dime el material del que esta hecho:");
            String material = lector.nextLine();
            try (Connection con = crearConexion()) {
                PreparedStatement prepStat = con.prepareStatement("INSERT INTO muebles VALUES (?,?,?,?,?)");
                prepStat.setInt(1, id);
                prepStat.setString(2, nombre);
                prepStat.setString(3, material);
                prepStat.setInt(4, stock);
                prepStat.setFloat(5, precio);
                prepStat.executeUpdate();
            }
        } else {
            try (Connection con = crearConexion()) {
                PreparedStatement prepStat = con.prepareStatement("INSERT INTO juguetes VALUES (?,?,?,?)");
                prepStat.setInt(1, id);
                prepStat.setString(2, nombre);
                prepStat.setInt(3, stock);
                prepStat.setFloat(4, precio);
                prepStat.executeUpdate();
            }
        }
        streamBuffer("Has hecho un insert con la id = " + id + " y con el nombre = " + nombre); // guardamos en un log que has creado un objeto
    }

    /**
     * Este es un metodo con el que transformamos la fecha que es un string en una
     * fecha que es posible insertar en la base de datos.
     * @param texto es la fecha de caducidad que es un string que le pasas al metodo.
     * @return devuelve la fehca transformada en un Date
     */
    public static Date parseFecha(String texto) { //Funciona pero sale un error extraño
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = (Date) formato.parse(texto);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    /**
     * Con este metodo el administrador puede modificar lo que quiera de los objetos en la base de datos.
     * @param seleccion Con esto seleccionas el tipo de objeto de la base de datos.
     * @throws SQLException
     * @throws IOException 
     */
    public static void modificarObjeto(String seleccion) throws SQLException, IOException { //Funciona menos alimentos por la fecha
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM " + seleccion);
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                String alomejor = "";
                if ("muebles".equals(seleccion)) {
                    alomejor = results.getString("material");
                } else if ("ropa".equals(seleccion)) {
                    alomejor = results.getString("Tamaño");
                } else if ("Fecha caducidad".equals(seleccion)) {
                    alomejor = results.getString("Fecha caducidad");
                }
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + alomejor + " | " + stock + " | " + precio);
            }
            System.out.println("-----------------------------");
            //pedir la id para saber que objeto quiere modificar
            System.out.println("Dime la id del objeto que quieres modificar "
                    + "o dime una letra para volver atras:");
            int idElegida = Integer.parseInt(lector.nextLine());
            System.out.println("Dime que quieres cambiar");
            String eleccion = menuCambio(seleccion);
            System.out.println("Dime el " + eleccion + " nuevo que le quieres poner:");
            String opcionCambiada = lector.nextLine();
            prepStat = con.prepareStatement("UPDATE " + seleccion + " SET " + eleccion + " = ? where id = ?");
            prepStat.setString(1, opcionCambiada);
            prepStat.setInt(2, idElegida);
            prepStat.executeUpdate();
            streamBuffer("Has hecho un update con la id = " + idElegida + " y el nuevo " + eleccion + " = " + opcionCambiada); //guardamos un log para saber que has modificado
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    /**
     * Con este metodo el administrador puede rellenar/aumentar el stock de algun objeto de la base de datos.
     * @param seleccion este es el tipo de objeto de la base de datos
     * @throws SQLException
     * @throws IOException 
     */
    public static void rellenarObjeto(String seleccion) throws SQLException, IOException { //Funciona menos alimentos por la fecha
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM " + seleccion);
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                String alomejor = "";
                if ("muebles".equals(seleccion)) {
                    alomejor = results.getString("material");
                } else if ("ropa".equals(seleccion)) {
                    alomejor = results.getString("Tamaño");
                } else if ("Fecha caducidad".equals(seleccion)) {
                    alomejor = results.getString("Fecha caducidad");
                }
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + alomejor + " | " + stock + " | " + precio);
            }
            System.out.println("-----------------------------");
            //pedir la id para saber que objeto quiere modificar
            System.out.println("Dime la id del objeto que quieres aumentar "
                    + "o dime una letra para volver atras:");
            int idElegida = Integer.parseInt(lector.nextLine());
            //pedir stock para sumar
            System.out.println("Dime cuanto quieres aumentar el stock:");
            int aumentoStock = Integer.parseInt(lector.nextLine());
            prepStat = con.prepareStatement("SELECT Stock FROM " + seleccion + " where Id = ?;");
            prepStat.setInt(1, idElegida);
            results = prepStat.executeQuery();
            results.next(); //No funciona el update y el while de aqui no esta bien
            int stock = results.getInt("Stock");
            int stockTotal = stock + aumentoStock;
            prepStat = con.prepareStatement("UPDATE " + seleccion + " SET Stock = ? where id = ?");
            prepStat.setInt(1, stockTotal);
            prepStat.setInt(2, idElegida);
            prepStat.executeUpdate();
            streamBuffer("Has hecho un update con la id = " + idElegida + " y has aumentado el stock a " + stockTotal); // Con esto guardamos el total despues de rellenar
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    /**
     * Este es un metodo con el que el administrador puede quitar un objeto de la base de datos.
     * @param seleccion Con esto seleccionas el tipo de objeto.
     * @throws SQLException
     * @throws IOException 
     */
    public static void quitarObjeto(String seleccion) throws SQLException, IOException { //Este deberia funcionaros
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM " + seleccion);
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                String alomejor = "";
                if ("muebles".equals(seleccion)) {
                    alomejor = results.getString("material");
                } else if ("ropa".equals(seleccion)) {
                    alomejor = results.getString("Tamaño");
                } else if ("Fecha caducidad".equals(seleccion)) {
                    alomejor = results.getString("Fecha caducidad");
                }
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + alomejor + " | " + stock + " | " + precio);
            }
            System.out.println("-----------------------------");
            //pedir la id para saber que objeto quiere modificar
            System.out.println("Dime la id del objeto que quieres modificar "
                    + "o dime una letra para volver atras:");
            int idElegida = Integer.parseInt(lector.nextLine());
            //en teoria esto lo borra.
            prepStat = con.prepareStatement("DELETE FROM " + seleccion + " where id = ?");
            prepStat.setInt(1, idElegida);
            prepStat.executeUpdate();
            streamBuffer("Has hecho un delete de este objeto: " + idElegida + " | tabla: " + seleccion); // guardamos lo que se borra
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }
}
