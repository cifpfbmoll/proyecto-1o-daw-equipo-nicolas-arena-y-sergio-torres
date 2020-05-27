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
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                String fechaCaducidad = results.getString("Fecha caducidad");//esto esta mal porque deberia ser un date pero no va
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + fechaCaducidad + " | " + stock + " | " + precio);
            }
            System.out.println("----------------------------");
            System.out.println("Dime una id de comida para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
            System.out.println("----------------------------");
            System.out.println("Dime la cantidad que quieres comprar:");
            int cantidad = Integer.parseInt(lector.nextLine());
            prepStat = con.prepareStatement("SELECT Stock FROM alimentos where Id = ?;");
            prepStat.setInt(1, pedido);
            results = prepStat.executeQuery();
            results.next();
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE alimentos SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarMobiliario() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM muebles;");
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                String material = results.getString("material");
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + material + " | " + stock + " | " + precio);
            }
            System.out.println("---------------------------");
            System.out.println("Dime que una id de muebles para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
            System.out.println("----------------------------");
            System.out.println("Dime la cantidad que quieres comprar:");
            int cantidad = Integer.parseInt(lector.nextLine());
            prepStat = con.prepareStatement("SELECT Stock FROM muebles where Id = ?;");
            prepStat.setInt(1, pedido);
            results = prepStat.executeQuery();
            while(results.next()){
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE muebles SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarJuguetes() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM juguetes;");
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + stock + " | " + precio);
            }
            System.out.println("---------------------------");
            System.out.println("Dime que una id de juguetes para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
            System.out.println("----------------------------");
            System.out.println("Dime la cantidad que quieres comprar:");
            int cantidad = Integer.parseInt(lector.nextLine());
            prepStat = con.prepareStatement("SELECT Stock FROM juguetes where Id = ?;");
            prepStat.setInt(1, pedido);
            results = prepStat.executeQuery();
            results.next();
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE jugetes SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarRopa() throws SQLException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM ropa;");
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                String Tamaño = results.getString("Tamaño");
                int stock = results.getInt("Stock");
                float precio = results.getFloat("Precio");
                System.out.println(id + " | " + nombre + " | " + Tamaño + " | " + stock + " | " + precio);
            }
            System.out.println("---------------------------");
            System.out.println("Dime que una id de ropa para comprar "
                    + "o dime una letra para volver atras:");
            int pedido = Integer.parseInt(lector.nextLine());
            System.out.println("----------------------------");
            System.out.println("Dime la cantidad que quieres comprar:");
            int cantidad = Integer.parseInt(lector.nextLine());
            prepStat = con.prepareStatement("SELECT Stock FROM ropa where Id = ?;");
            prepStat.setInt(1, pedido);
            results = prepStat.executeQuery();
            results.next();
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE ropa SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }
}
