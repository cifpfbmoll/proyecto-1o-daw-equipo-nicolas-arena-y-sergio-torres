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
import java.util.Scanner;
import static proyecto.almacen.Menus.crearConexion;
import static proyecto.almacen.Menus.menuCambio;

/**
 *
 * @author Nicolás
 */
public class OpcionesAdministrador {

    static Scanner lector = new Scanner(System.in);

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
            }
        }
    }

    public static void modificarObjeto(String seleccion) throws SQLException { //Funciona menos alimentos por la fecha
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            System.out.println("-----------------------------");
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
            System.out.println("Dime la id del objeto que quieres modificar:");
            int idElegida = Integer.parseInt(lector.nextLine());
            System.out.println("Dime que quieres cambiar");
            String eleccion = menuCambio(seleccion);
            System.out.println("Dime el " + eleccion + " nuevo que le quieres poner:");
            String stockCambiado = lector.nextLine();
            prepStat = con.prepareStatement("UPDATE " + seleccion + " SET " + eleccion + " = ? where id = ?");
            prepStat.setString(1, stockCambiado);
            prepStat.setInt(2, idElegida);
            prepStat.executeUpdate();
        }
    }

    public static void rellenarObjeto(String seleccion) throws SQLException { //Funciona menos alimentos por la fecha
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            System.out.println("-----------------------------");
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
            System.out.println("Dime la id del objeto que quieres modificar:");
            int idElegida = Integer.parseInt(lector.nextLine());
            //pedir stock para sumar
            System.out.println("Dime cuanto quieres aumentar el stock:");
            int aumentoStock = Integer.parseInt(lector.nextLine());
            prepStat = con.prepareStatement("SELECT Stock FROM alimentos where Id = ?;");
            prepStat.setInt(1, idElegida);
            results = prepStat.executeQuery();
            results.next();
            int stock = results.getInt("Stock");
            int stockTotal = stock + aumentoStock;
            prepStat = con.prepareStatement("UPDATE " + seleccion + " SET Stock = ? where id = ?");
            prepStat.setInt(1, stockTotal);
            prepStat.setInt(2, idElegida);
            prepStat.executeUpdate();
        }
    }

    public static void quitarObjeto(String seleccion) throws SQLException { //Este deberia funcionaros
        try (Connection con = crearConexion()) {
            //mostrar la informacion de todos los objetos
            System.out.println("-----------------------------");
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
            System.out.println("Dime la id del objeto que quieres modificar:");
            int idElegida = Integer.parseInt(lector.nextLine());
            //en teoria esto lo borra.
            prepStat = con.prepareStatement("DELETE FROM " + seleccion + " where ?");
            prepStat.setInt(1, idElegida);
            prepStat.executeUpdate();
        }
    }
}
