/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import static proyecto.almacen.Menus.crearConexion;
import static proyecto.almacen.Menus.lector;

/**
 *
 * @author Nicolás
 */
public class OpcionesAdministrador {

    public static String seleccionObjeto() { //Hay que hacer un menu
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        String seleccion = "";

        while (!salir) {

            System.out.println("Que objeto quieres:");
            System.out.println("    1. alimento");
            System.out.println("    2. muebles");
            System.out.println("    3. juguetes");
            System.out.println("    4. ropa");
            System.out.println(" ");
            System.out.println("    5. Volver atras");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("-------------------------");
                        seleccion = "alimentos";
                        salir = true;
                        break;
                    case 2:
                        System.out.println("-------------------------");
                        seleccion = "muebles";
                        salir = true;
                        break;
                    case 3:
                        System.out.println("-------------------------");
                        seleccion = "juguetes";
                        salir = true;
                        break;
                    case 4:
                        System.out.println("-------------------------");
                        seleccion = "ropa";
                        salir = true;
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                lector.next();
            }
        }
        return seleccion;
    }

    public static void crearObjeto(String seleccion) throws SQLException {
        System.out.println("Dime la id que quieres poner:");
        int id = Integer.parseInt(lector.nextLine());
        System.out.println("Dime el nombre");
        String nombre = lector.nextLine();
        System.out.println("Dime el stock:");
        int stock = Integer.parseInt(lector.nextLine());
        System.out.println("Dime el precio:");
        float precio = Float.parseFloat(lector.nextLine()); // Hasta aqui son los atributos comunes en los distintos objetos.
        if ("alimentos".equals(seleccion)) {
            System.out.println("Dime el fecha de caducidad");
            Date fechaCaducidad = new Date(); //Probar
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
    }

    public static void modificarObjeto(String seleccion) {
    }

    public static void rellenarObjeto(String seleccion) {
    }

    public static void quitarObjeto(String seleccion) {
    }
}
