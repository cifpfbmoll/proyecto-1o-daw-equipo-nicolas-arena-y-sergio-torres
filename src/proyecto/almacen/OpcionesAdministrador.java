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
import static proyecto.almacen.Menus.menuCambio;

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
            Date fechaCaducidad = new Date(); //falla
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
                //meter en un archivo la informacion que metemos en la base de datos
            }
        }
    }

    public static void modificarObjeto(String seleccion) throws SQLException { //Esto hay que probarlo, estoy probando formas de hacerlo
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            PreparedStatement prepStat1 = con.prepareStatement("SELECT * FROM alimentos");
            PreparedStatement prepStat2 = con.prepareStatement("SELECT * FROM ropa");
            PreparedStatement prepStat3 = con.prepareStatement("SELECT * FROM muebles");
            PreparedStatement prepStat4 = con.prepareStatement("SELECT * FROM juguetes");
            ResultSet results1 = prepStat1.executeQuery();
            ResultSet results2 = prepStat2.executeQuery();
            ResultSet results3 = prepStat3.executeQuery();
            ResultSet results4 = prepStat4.executeQuery();
            System.out.println("-----------------------------");
            while (results1.next()) {
            }
            System.out.println("-----------------------------");
            while (results2.next()) {
            }
            System.out.println("-----------------------------");
            while (results3.next()) {
            }
            System.out.println("-----------------------------");
            while (results4.next()) {
            }
            System.out.println("-----------------------------");

            //pedir la id para saber que objeto quiere modificar
            System.out.println("Dime la id del objeto que quieres modificar:");
            int idElegida = Integer.parseInt(lector.nextLine());
            System.out.println("Dime que quieres cambiar");
            int eleccion = menuCambio();
            if (eleccion == 1) {
                System.out.println("Dime el nombre nuevo que le quieres poner:");
                String nombreCambiado = lector.nextLine();
                PreparedStatement prepStat = con.prepareStatement("UPDATE muebles SET Nombre = ? where id = ?");
                //prepStat.setInt(1, nombreTabla); --> este no se como hacerlo bien asi que lo quito por ahora y pondre otra cosa provisional
                prepStat.setString(1, nombreCambiado);
                prepStat.setInt(2, idElegida);
                prepStat.executeUpdate();
            } else if (eleccion == 2) {
                //seria lo mismo que el anterior pero cambiando el campo
            }

        }
    }

    public static void rellenarObjeto(String seleccion) throws SQLException {
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            PreparedStatement prepStat1 = con.prepareStatement("SELECT * FROM alimentos");
            PreparedStatement prepStat2 = con.prepareStatement("SELECT * FROM ropa");
            PreparedStatement prepStat3 = con.prepareStatement("SELECT * FROM muebles");
            PreparedStatement prepStat4 = con.prepareStatement("SELECT * FROM juguetes");
            ResultSet results1 = prepStat1.executeQuery();
            ResultSet results2 = prepStat2.executeQuery();
            ResultSet results3 = prepStat3.executeQuery();
            ResultSet results4 = prepStat4.executeQuery();
            System.out.println("-----------------------------");
            while (results1.next()) {
            }
            System.out.println("-----------------------------");
            while (results2.next()) {
            }
            System.out.println("-----------------------------");
            while (results3.next()) {
            }
            System.out.println("-----------------------------");
            while (results4.next()) {
            }
            System.out.println("-----------------------------");

            //pedir la id para saber que objeto quiere modificar
            System.out.println("Dime la id del objeto que quieres aumentar el stock:");
            int idElegida = Integer.parseInt(lector.nextLine());
            //pedir stock para sumar
            System.out.println("Dime cuanto quieres aumentar el stock:");
            int aumentoStock = Integer.parseInt(lector.nextLine());
            //hay que sacar el stock original. Creo que con un select guardando el valor deberia de servir.
            int stockOriginal = 0;
            //Hay que sacar el stock original y sumarle lo nuevo.
            int stockTotal = stockOriginal + aumentoStock;
            PreparedStatement prepStat = con.prepareStatement("UPDATE muebles SET Stock = ? where id = ?"); //todavia tengo que descubrir como hacer el interrogante de la tabla
            prepStat.setInt(1, stockTotal);
            prepStat.setInt(2, idElegida);
            prepStat.executeUpdate();
        }
    }

    public static void quitarObjeto(String seleccion) throws SQLException {
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            PreparedStatement prepStat1 = con.prepareStatement("SELECT * FROM alimentos");
            PreparedStatement prepStat2 = con.prepareStatement("SELECT * FROM ropa");
            PreparedStatement prepStat3 = con.prepareStatement("SELECT * FROM muebles");
            PreparedStatement prepStat4 = con.prepareStatement("SELECT * FROM juguetes");
            ResultSet results1 = prepStat1.executeQuery();
            ResultSet results2 = prepStat2.executeQuery();
            ResultSet results3 = prepStat3.executeQuery();
            ResultSet results4 = prepStat4.executeQuery();
            System.out.println("-----------------------------");
            while (results1.next()) {
            }
            System.out.println("-----------------------------");
            while (results2.next()) {
            }
            System.out.println("-----------------------------");
            while (results3.next()) {
            }
            System.out.println("-----------------------------");
            while (results4.next()) {
            }
            System.out.println("-----------------------------");

            //pedir la id para saber que objeto quiere modificar
            System.out.println("Dime la id del objeto que quieres eliminar:");
            int idElegida = Integer.parseInt(lector.nextLine());
            //en teoria esto lo borra.
            PreparedStatement prepStat = con.prepareStatement("DELETE FROM muebles where ?");
            prepStat.setInt(1, idElegida);
            prepStat.executeUpdate();
        }
    }
}
