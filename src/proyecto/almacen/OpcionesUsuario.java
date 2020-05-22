/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static proyecto.almacen.Menus.crearConexion;

/**
 *
 * @author Nicolás
 */
public class OpcionesUsuario {

    static Scanner lector = new Scanner(System.in);

    public static void comprarAlimentos() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM alimentos;");
            ResultSet results = prepStat.executeQuery();
            while(results.next()){}
            System.out.println("----------------------------");
            System.out.println("Dime que una id de comida para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
            System.out.println("----------------------------");
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarMobiliario() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM muebles;");
            ResultSet results = prepStat.executeQuery();
            //poner el next
            System.out.println("---------------------------");
            System.out.println("Dime que una id de comida para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarJuguetes() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM juguetes;");
            ResultSet results = prepStat.executeQuery();
            //next
            System.out.println("---------------------------");
            System.out.println("Dime que una id de comida para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarRopa() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM ropa;");
            ResultSet results = prepStat.executeQuery();
            //next
            System.out.println("---------------------------");
            System.out.println("Dime que una id de comida para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }
}
