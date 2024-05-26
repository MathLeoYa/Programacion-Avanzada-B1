package tareapaquetes;

import Clases.*;
import java.sql.SQLException;
import java.util.Scanner;

public class TareaPaquetes {
    static Controladora controla = new Controladora();

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int op = 0;
        String aux;
        do {
            System.out.println("Ingrese su opcion: ");
            System.out.println("Menu:");
            System.out.println("1. Crear un Cliente");
            System.out.println("2. Buscar Cliente");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Listar Cliente");
            System.out.println("5. Agregar Dirección");
            System.out.println("6. Agregar Esposa");
            System.out.println("7. Salir");
            aux = entrada.nextLine();
            op = Integer.parseInt(aux);

            switch (op) {
                case 1:
                    crearCliente();

                    break;
                case 2:

                    //buscarCliente();
                    break;
                case 3:

                    //actualizarCliente();
                    break;
                case 4:

                    //listarClientes();

                    break;

                case 5:

                    //agregarDireccion();

                    break;
                /*
                case 6:
                    try {
                        agregarEsposa();
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("Error al agregar esposa: " + e.getMessage());
                    }
                    break;*/
                default:
                    System.out.println("Opción invalida. Por favor ingrese una opcion valida.");
            }
        } while (op != 7);
        //entrada.close();
    }


    private static void crearCliente() throws SQLException, ClassNotFoundException {

        Clientes cliente = new Clientes();
        Direccion direccion = new Direccion();

        System.out.println("Ingrese la cedula");
        cliente.setCedula(entrada.nextLine());
        System.out.println("Ingrese apellidos");
        cliente.setApellido(entrada.nextLine());
        System.out.println("Ingrese nombre");
        cliente.setNombre(entrada.nextLine());
        System.out.println("Ingrese nombre");
        cliente.setNombre(entrada.nextLine());
        System.out.println("Ingrese celular");
        cliente.setNombre(entrada.nextLine());
        
        System.out.println("Direccion ======");
        System.out.println("Ingrese el codigo postal ======");
        direccion.setCodigo(entrada.nextLine());
        System.out.println("Ingrese la calle 1: ");
        direccion.setCalle1(entrada.nextLine());
        System.out.println("Ingrese Calle 2: ");
        direccion.setCalle2(entrada.nextLine());
        System.out.println("Ingrese referenda");
        direccion.setReferenda(entrada.nextLine());
        direccion.setActual(true);
        
        controla.agregarDireccion(direccion);
        controla.crearCliente(cliente);
    }
}
