
import Clases.Cliente;
import Clases.Direccion;
import Clases.Estado;
import Clases.Paquete;
import Clases.Entrega;
import Clases.Repartidor;
import javax.persistence.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");
    private static final EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Cliente: Revisar estados de sus paquetes");
                System.out.println("2. Cliente: Agregar nueva dirección y cambiar el estado actual de las que ya tenga");
                System.out.println("3. Repartidor: Registrar la entrega de un paquete");
                System.out.println("4. Bodeguero: Registrar un nuevo cliente");
                System.out.println("5. Bodeguero: Registrar un nuevo cliente y sus direcciones");
                System.out.println("6. Bodeguero: Registrar un nuevo paquete");
                System.out.println("7. Bodeguero: Despachar un paquete");
                System.out.println("8. Registrar un nuevo repartidor");
                System.out.println("9. Salir");

                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        revisarEstadosPaquetes(scanner);
                        break;
                    case 2:
                        agregarDireccionYActualizarEstado(scanner);
                        break;
                    case 3:
                        registrarEntregaPaquete(scanner);
                        break;
                    case 4:
                        registrarNuevoCliente(scanner);
                        break;
                    case 5:
                        registrarNuevoClienteYDirecciones(scanner);
                        break;
                    case 6:
                        registrarNuevoPaquete(scanner);
                        break;
                    case 7:
                        despacharPaquete(scanner);
                        break;
                    case 8:
                        registrarNuevoRepartidor(scanner);
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
            em.close();
            emf.close();
        }
    }

    private static void registrarNuevoCliente(Scanner scanner) {
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese los apellidos del cliente: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese los nombres del cliente: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese el correo del cliente: ");
        String mail = scanner.nextLine();
        System.out.print("Ingrese el número de celular del cliente: ");
        String celular = scanner.nextLine();

        em.getTransaction().begin();
        Cliente cliente = new Cliente(celular, cedula, apellidos, nombres, mail);
        em.persist(cliente);
        em.getTransaction().commit();

        System.out.println("Cliente registrado exitosamente");
    }

    private static void registrarNuevoRepartidor(Scanner scanner) {
        System.out.print("Ingrese la cédula del repartidor: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese los apellidos del repartidor: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese los nombres del repartidor: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese el correo del repartidor: ");
        String mail = scanner.nextLine();
        System.out.print("Ingrese la ciudad del repartidor: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese la zona del repartidor: ");
        int zona = scanner.nextInt();
        scanner.nextLine();

        em.getTransaction().begin();
        Repartidor repartidor = new Repartidor(zona, ciudad, cedula, apellidos, nombres, mail);
        em.persist(repartidor);
        em.getTransaction().commit();

        System.out.println("Repartidor registrado exitosamente");
    }

    private static void registrarNuevoPaquete(Scanner scanner) {
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = scanner.nextLine();
        Cliente cliente = em.find(Cliente.class, cedula);

        if (cliente != null) {
            System.out.print("Ingrese el código del paquete: ");
            String codigo = scanner.nextLine();
            System.out.print("Ingrese el peso del paquete: ");
            int peso = scanner.nextInt();
            System.out.print("Ingrese la altura del paquete: ");
            int altura = scanner.nextInt();
            scanner.nextLine();

            em.getTransaction().begin();
            Paquete paquete = new Paquete();
            paquete.setCodigo(codigo);
            paquete.setPeso(peso);
            paquete.setAlto(altura);
            paquete.setCliente(cliente);
            em.persist(paquete);
            em.getTransaction().commit();
            System.out.println("Paquete registrado exitosamente");
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    private static void despacharPaquete(Scanner scanner) {
        System.out.print("Ingrese el ID del paquete: ");
        int idPaquete = scanner.nextInt();
        scanner.nextLine();
        Paquete paquete = em.find(Paquete.class, idPaquete);

        if (paquete != null) {
            System.out.print("Ingrese la cédula del repartidor: ");
            String cedulaRepartidor = scanner.nextLine();
            Repartidor repartidor = em.find(Repartidor.class, cedulaRepartidor);

            if (repartidor != null) {
                em.getTransaction().begin();
                Estado estado = new Estado();
                estado.setTipo(2);
                estado.setEstado("Despachado");
                estado.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                estado.setObservacion("Despachado para entrega por repartidor " + repartidor.getNombres() + " " + repartidor.getApellidos());
                estado.setPaquete(paquete);
                paquete.getEstados().add(estado);
                em.merge(paquete);
                em.getTransaction().commit();

                System.out.println("Paquete despachado exitosamente");
            } else {
                System.out.println("Repartidor no encontrado");
            }
        } else {
            System.out.println("Paquete no encontrado");
        }
    }

    private static void agregarDireccionYActualizarEstado(Scanner scanner) {
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = scanner.nextLine();
        Cliente cliente = em.find(Cliente.class, cedula);

        if (cliente != null) {
            System.out.println("1. Agregar nueva dirección");
            System.out.println("2. Cambiar estado de dirección actual");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Ingrese el código de la dirección: ");
                String codigo = scanner.nextLine();
                System.out.print("Ingrese calle 1: ");
                String calle1 = scanner.nextLine();
                System.out.print("Ingrese calle 2: ");
                String calle2 = scanner.nextLine();
                System.out.print("Ingrese referencia: ");
                String referencia = scanner.nextLine();

                Direccion nuevaDireccion = new Direccion(null, codigo, calle1, calle2, referencia, false, cliente);

                em.getTransaction().begin();
                em.persist(nuevaDireccion);
                em.getTransaction().commit();
                System.out.println("Dirección agregada exitosamente");

            } else if (opcion == 2) {
                System.out.print("Ingrese el código de la dirección a actualizar: ");
                String codigo = scanner.nextLine();

                Direccion direccionAActualizar = null;
                for (Direccion direccion : cliente.getDirecciones()) {
                    if (direccion.getCodigo().equals(codigo)) {
                        direccionAActualizar = direccion;
                        break;
                    }
                }

                if (direccionAActualizar != null) {
                    em.getTransaction().begin();
                    for (Direccion direccion : cliente.getDirecciones()) {
                        if (direccion.isActual() && !direccion.equals(direccionAActualizar)) {
                            direccion.setActual(false);
                            em.merge(direccion);
                        }
                    }
                    direccionAActualizar.setActual(!direccionAActualizar.isActual());
                    em.merge(direccionAActualizar);
                    em.getTransaction().commit();
                    System.out.println("Estado de la dirección actualizado");
                } else {
                    System.out.println("Dirección no encontrada");
                }
            } else {
                System.out.println("Opción no válida");
            }
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    private static void revisarEstadosPaquetes(Scanner scanner) {
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = scanner.nextLine();
        Cliente cliente = em.find(Cliente.class, cedula);

        if (cliente != null) {
            for (Paquete paquete : cliente.getPaquetes()) {
                System.out.println("Paquete ID: " + paquete.getIdpaq());
                for (Estado estado : paquete.getEstados()) {
                    System.out.println("Estado: " + estado.getEstado() + ", Fecha: " + estado.getFecha());
                }
            }
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    private static void registrarEntregaPaquete(Scanner scanner) {
        System.out.print("Ingrese el ID del paquete: ");
        int idPaquete = scanner.nextInt();
        scanner.nextLine();
        Paquete paquete = em.find(Paquete.class, idPaquete);

        if (paquete != null) {
            System.out.print("Ingrese la cédula del repartidor: ");
            String cedulaRepartidor = scanner.nextLine();
            Repartidor repartidor = em.find(Repartidor.class, cedulaRepartidor);

            if (repartidor != null) {
                em.getTransaction().begin();

                Estado estado = new Estado();
                estado.setTipo(3);
                estado.setEstado("Entregado");
                estado.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                estado.setObservacion("Entregado por repartidor " + repartidor.getNombres() + " " + repartidor.getApellidos());
                estado.setPaquete(paquete);
                paquete.getEstados().add(estado);

                Entrega entrega = new Entrega();
                entrega.setCodigo("ENT" + idPaquete);
                entrega.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                entrega.setObservacion("Entrega realizada exitosamente");
                entrega.setCliente(paquete.getCliente());
                entrega.setRepartidor(repartidor);
                entrega.setPaquete(paquete);

                em.persist(entrega);
                em.merge(paquete);
                em.getTransaction().commit();

                System.out.println("Entrega registrada exitosamente");
            } else {
                System.out.println("Repartidor no encontrado");
            }
        } else {
            System.out.println("Paquete no encontrado");
        }
    }

    private static void registrarNuevoClienteYDirecciones(Scanner scanner) {
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese los apellidos del cliente: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese los nombres del cliente: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese el correo del cliente: ");
        String mail = scanner.nextLine();
        System.out.print("Ingrese el número de celular del cliente: ");
        String celular = scanner.nextLine();

        em.getTransaction().begin();
        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        cliente.setApellidos(apellidos);
        cliente.setNombres(nombres);
        cliente.setMail(mail);
        cliente.setCelular(celular);
        em.persist(cliente);

        boolean addMore = true;
        while (addMore) {
            System.out.print("Ingrese el código de la dirección: ");
            String codigo = scanner.nextLine();
            System.out.print("Ingrese calle 1: ");
            String calle1 = scanner.nextLine();
            System.out.print("Ingrese calle 2: ");
            String calle2 = scanner.nextLine();
            System.out.print("Ingrese referencia: ");
            String referencia = scanner.nextLine();

            Direccion direccion = new Direccion();
            direccion.setCodigo(codigo);
            direccion.setCalle1(calle1);
            direccion.setCalle2(calle2);
            direccion.setReferenda(referencia);
            direccion.setActual(false);
            direccion.setCliente(cliente);
            em.persist(direccion);

            System.out.print("¿Desea agregar otra dirección? (s/n): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("s")) {
                addMore = false;
            }
        }
        em.getTransaction().commit();

        System.out.println("Cliente y direcciones registradas exitosamente");
    }
}
