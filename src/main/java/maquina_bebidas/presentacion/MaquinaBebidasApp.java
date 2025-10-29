package maquina_bebidas.presentacion;

import maquina_bebidas.datos.BebidaDAO;
import maquina_bebidas.datos.IBebidaDAO;
import maquina_bebidas.dominio.Bebida;
import java.util.Scanner;

public class MaquinaBebidasApp {
    public static void main(String[] args) {
        maquinaBebidasApp();
    }

    //crear el menú de la maquina de bebidas
    private static void maquinaBebidasApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        //se crea un objeto de la clase BebidaDAO
        IBebidaDAO bebidaDAO = new BebidaDAO();
        while(!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, bebidaDAO);
            } catch (Exception e) {
                System.out.println("Error al ejecutar las opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        //pronto, ticket y compra
        System.out.print("""
                *** Maquina Bebidas
                1. Listar bebidas
                2. Agregar bebida
                3. Modificar bebida
                4. Eliminar bebida
                5. Salir
                Elige una opción:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IBebidaDAO bebidaDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {
                //1. listar bebidas
                System.out.println("--- Listado de Bebidas ---");
                var bebidas = bebidaDAO.listarBebidas();
                bebidas.forEach(System.out::println);
            }
            case 2 -> {//2.agregar bebida
                System.out.println("--- Agregar Bebida ---");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Precio: ");
                var precio = Double.parseDouble(consola.nextLine());
                //objeto de la nueva bebida
                var bebida = new Bebida(nombre,precio);
                var agregada = bebidaDAO.agregarBebida(bebida);
                //validar si se agregó la bebida
                if(agregada){
                    System.out.println("Nueva bebida agregada: " + bebida);
                } else{
                    System.out.println("Nueva bebida no agregada: " + bebida);
                }
            }
            case 3 -> {//3. modificar bebida
                System.out.println("--- Modificar Bebida ---");
                System.out.print("Introduce el id de la bebida a modificar: ");
                var idBebida = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Precio: ");
                var precio = Double.parseDouble(consola.nextLine());
                //objeto de la nueva bebida
                var bebida = new Bebida(idBebida,nombre,precio);
                var modificada = bebidaDAO.modificarBebida(bebida);
                //validar si la bebida fué modificada
                if (modificada) {
                    System.out.println("Bebida modificada: " + bebida);
                } else {
                    System.out.println("Bebida no modificada: " + bebida);
                }
            }
            case 4 -> {//4. eliminar bebida
                System.out.println("--- Eliminar Bebida ---");
                System.out.print("Introduce el id de la bebida a eliminar: ");
                var idBebida = Integer.parseInt(consola.nextLine());
                var bebida = new Bebida(idBebida);
                var eliminada = bebidaDAO.eliminarBebida(bebida);
                //validar si la bebida fué eliminada
                if (eliminada) {
                    System.out.println("Bebida eliminada: " + bebida);
                } else {
                    System.out.println("Bebida no eliminada: " + bebida);
                }
            }
            case 5 -> {//5. salir del sistema
                System.out.println("Gracias por su compra! :)");
                salir = true;
            }
            default -> {
                try{
                    System.out.println("Ingrese un número válido por favor");
                } catch (Exception e) {
                    System.out.println("Error al seleccionar una opción: " +
                            e.getMessage() + ", digíte un número por favor");
                }
            }
        }
        return salir;
    }
}
