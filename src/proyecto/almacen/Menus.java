/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static proyecto.almacen.OpcionesAdministrador.crearObjeto;
import static proyecto.almacen.OpcionesAdministrador.modificarObjeto;
import static proyecto.almacen.OpcionesAdministrador.quitarObjeto;
import static proyecto.almacen.OpcionesAdministrador.rellenarObjeto;
import static proyecto.almacen.OpcionesUsuario.comprarAlimentos;
import static proyecto.almacen.OpcionesUsuario.comprarJuguetes;
import static proyecto.almacen.OpcionesUsuario.comprarMobiliario;
import static proyecto.almacen.OpcionesUsuario.comprarRopa;

/**
 *
 * @author Nicolás
 */
public class Menus {

    static Scanner lector = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("No usar nada de alimentos porque no funciona por un error en la fecha");
            System.out.println("Como desea iniciar sesion:");
            System.out.println("    1. Usuario");
            System.out.println("    2. Administrador");
            System.out.println(" ");
            System.out.println("    3. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("-------------------------");
                        menuUsuario();
                        break;
                    case 2:
                        System.out.println("-------------------------");
                        boolean contraseña = pedirContraseña();
                        if (contraseña == true) {
                            menuAdministrador();
                        } else {
                            System.out.println("--------------------------------");
                            System.out.println("Te has equivocado de contraseña.");
                            System.out.println("--------------------------------");
                        }
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                lector.next();
            } catch (SQLException ex) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Connection crearConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/granalmacen";
        return DriverManager.getConnection(url, "root", "");//Hay que probarlo
    }

    public static boolean pedirContraseña() {
        boolean confirmacion = false;
        System.out.println("Dime la contraseña:");
        String respuesta = lector.nextLine();
        if ("probando".equals(respuesta)) {
            confirmacion = true;
        }
        return confirmacion;
    }

    public static void menuUsuario() throws SQLException {
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Que desea comprar:");
            System.out.println("    1. Alimentos");
            System.out.println("    2. Mobiliario");
            System.out.println("    3. Juguetes");
            System.out.println("    4. Ropa");
            System.out.println(" ");
            System.out.println("    5. Volver atras");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("-------------------------");
                        comprarAlimentos();
                        break;
                    case 2:
                        System.out.println("-------------------------");
                        comprarMobiliario();
                        break;
                    case 3:
                        System.out.println("-------------------------");
                        comprarJuguetes();
                        break;
                    case 4:
                        System.out.println("-------------------------");
                        comprarRopa();
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
    }

    public static void menuAdministrador() throws SQLException {
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Que desea hacer:");
            System.out.println("    1. Crear Objeto");
            System.out.println("    2. Modificar Objeto");
            System.out.println("    3. Rellenar Stock");
            System.out.println("    4. Quitar Objeto");
            System.out.println(" ");
            System.out.println("    5. Volver atras");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("-------------------------");
                        String seleccionado = seleccionObjeto();
                        crearObjeto(seleccionado);
                        break;
                    case 2:
                        System.out.println("-------------------------");
                        modificarObjeto(seleccionObjeto());
                        break;
                    case 3:
                        System.out.println("-------------------------");
                        rellenarObjeto(seleccionObjeto());
                        break;
                    case 4:
                        System.out.println("-------------------------");
                        quitarObjeto(seleccionObjeto());
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
    }

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

    public static String menuCambio(String seleccion) {
        String eleccion = "";
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Que quieres cambiar:");
            System.out.println("    1. Cambiar nombre");
            System.out.println("    2. Cambiar Stock");
            System.out.println("    3. Cambiar precio");
            if (null != seleccion) {
                switch (seleccion) {
                    case "muebles":
                        System.out.println("    4. Cambiar material");
                        break;
                    case "alimentos":
                        System.out.println("4. Fecha de caducidad");
                        break;
                    case "ropa":
                        System.out.println("4. Tamaño");
                        break;
                    default:
                        break;
                }
            }
            System.out.println(" ");
            System.out.println("    0. Volver atras");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("-------------------------");
                        eleccion = "Nombre";
                        break;
                    case 2:
                        System.out.println("-------------------------");
                        eleccion = "Stock";
                        break;
                    case 3:
                        System.out.println("-------------------------");
                        eleccion = "Precio";
                        break;
                    case 4:
                        System.out.println("-------------------------");
                        if (null != seleccion) {
                            switch (seleccion) {
                                case "muebles":
                                    eleccion = "Material";
                                    break;
                                case "alimentos":
                                    eleccion = "Fecha caducidad";
                                    break;
                                case "ropa":
                                    eleccion = "Tamaño";
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                lector.next();
            }
            salir = true;
        }
        return eleccion;
    }

    public static boolean menuPrecio(int precio, int cantidad) { //Hay que hacer un menu
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        int total = precio * cantidad;

        boolean eleccion = false;

        while (!salir) {

            System.out.println("El precio es " + total + ", estas seguro de que quieres comprar?");
            System.out.println("    1. Sí");
            System.out.println("    2. No");

            try {

                System.out.println("Escribe uno de los numeros");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        eleccion = true;
                        salir = true;
                        break;
                    case 2:
                        eleccion = false;
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                lector.next();
            }
        }
        return eleccion;
    }
}
