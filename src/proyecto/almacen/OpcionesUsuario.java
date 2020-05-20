/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.sql.Connection;
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
            Statement stat = con.createStatement();
            ResultSet rset = stat.executeQuery("SELECT * FROM comida;");
            System.out.println(rset);
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
            Statement stat = con.createStatement();
            ResultSet rset = stat.executeQuery("SELECT * FROM muebles;");
            System.out.println(rset);
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
            Statement stat = con.createStatement();
            ResultSet rset = stat.executeQuery("SELECT * FROM juguetes;");
            System.out.println(rset);
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
            Statement stat = con.createStatement();
            ResultSet rset = stat.executeQuery("SELECT * FROM ropa;");
            System.out.println(rset);
            System.out.println("---------------------------");
            System.out.println("Dime que una id de comida para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }
}
