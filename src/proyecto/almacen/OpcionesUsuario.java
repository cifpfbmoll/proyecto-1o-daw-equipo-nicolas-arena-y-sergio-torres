/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static proyecto.almacen.Menus.crearConexion;
import static proyecto.almacen.Menus.menuPrecio;
import static proyecto.almacen.Streams.streamBuffer;

/**
 *
 * @author Nicolás
 */
public class OpcionesUsuario {

    static Scanner lector = new Scanner(System.in);

    /**
     * Con esto compramos los alimentos
     *
     * @throws SQLException
     */
    public static void comprarAlimentos() throws SQLException, IOException {
        try (Connection con = crearConexion()) {
            PreparedStatement prepStat = con.prepareStatement("SELECT * FROM alimentos;");
            ResultSet results = prepStat.executeQuery();
            while (results.next()) {
                int id = results.getInt("Id");
                String nombre = results.getString("Nombre");
                Date fechaCaducidad = results.getDate("Fecha caducidad");//esto esta mal
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
            results.next(); //revisar
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE alimentos SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
            streamBuffer("El usuario a comprado " + cantidad + " de la tabla: alimentos el objeto con la id = " + pedido); // Con esto guardamos lo que se ha comprado
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarMobiliario() throws SQLException, IOException {
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
            prepStat = con.prepareStatement("SELECT Precio FROM muebles where Id = ?;");
            prepStat.setInt(1, pedido);
            results = prepStat.executeQuery();
            while (results.next()) { //revisar, en los demas tambien hay que ponerlo
                int precio = results.getInt("Precio");
                boolean eleccion = menuPrecio(precio, cantidad);
                if (eleccion == true) {
                    prepStat = con.prepareStatement("SELECT Stock FROM muebles where Id = ?;");
                    prepStat.setInt(1, pedido);
                    results = prepStat.executeQuery();
                    int compra = 0;
                    int stock = 0;
                    while (results.next()) {
                        stock = results.getInt("Stock");
                        compra = stock - cantidad;
                        prepStat = con.prepareStatement("UPDATE muebles SET Stock = ? where id = ?");
                        prepStat.setInt(1, compra);
                        prepStat.setInt(2, pedido);
                    }
                } else {
                    System.out.println("Has salido sin comprar");
                }
            }
            streamBuffer("El usuario a comprado " + cantidad + " de la tabla: muebles el objeto con la id = " + pedido);
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarJuguetes() throws SQLException, IOException {
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
            results.next(); //esta mal
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE jugetes SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
            streamBuffer("El usuario a comprado " + cantidad + " de la tabla: juguetes el objeto con la id = " + pedido);
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }

    public static void comprarRopa() throws SQLException, IOException {
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
            results.next(); //revisar
            int stock = results.getInt("Stock");
            int compra = stock - cantidad;
            prepStat = con.prepareStatement("UPDATE ropa SET Stock = ? where id = ?");
            prepStat.setInt(1, compra);
            prepStat.setInt(2, pedido);
            streamBuffer("El usuario a comprado " + cantidad + " de la tabla: ropa el objeto con la id = " + pedido);
        } catch (NumberFormatException nfe) {
            System.out.println("--------------------------------");
        }
    }
}
