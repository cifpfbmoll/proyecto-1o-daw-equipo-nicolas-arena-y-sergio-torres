/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.almacen;

import java.util.InputMismatchException;
import java.util.Scanner;
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
                        System.out.println("Has seleccionado la opcion 1");
                        menuUsuario();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        if (pedirContraseña() == true) {
                            menuAdministrador();
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
            }
        }
    }

    public static boolean pedirContraseña() {
        boolean confirmacion = false;
        System.out.println("Dime la contraseña:");
        String respuesta = lector.nextLine();
        if (respuesta == "probando") {
            confirmacion = true;
        }
        return confirmacion;
    }

    public static void menuUsuario() {
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("Que desea comprar:");
            System.out.println("    1. Alimentos");
            System.out.println("    2. Mobiliario");
            System.out.println("    3. Juguetes");
            System.out.println("    4. Ropa");
            System.out.println(" ");
            System.out.println("    5. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        comprarAlimentos();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        comprarMobiliario();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 1");
                        comprarJuguetes();
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 1");
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
            System.out.println("    5. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = Integer.parseInt(lector.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        crearObjeto(seleccionObjeto());
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        modificarObjeto(seleccionObjeto());
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        rellenarObjeto(seleccionObjeto());
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4");
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
        System.out.println("Dime el objeto");
        String resultado = "";
        return resultado;
    }
}
