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
import static proyecto.almacen.OpcionesAdministrador.seleccionObjeto;
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

    public static void menuAdministrador() {
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
                        crearObjeto(seleccionObjeto());
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

}
