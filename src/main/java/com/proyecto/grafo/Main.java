package com.proyecto.grafo;

import java.util.Scanner;

public class Main {
    private static final int VERTICES_INICIALES = 4;

    private Main() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n=== GESTIÓN DE GRAFOS ===");
                System.out.println("1. Probar grafo dirigido");
                System.out.println("2. Probar grafo no dirigido");
                System.out.println("0. Salir");
                Integer opcion = leerEntero(scanner, "Seleccione una opción: ");
                if (opcion == null) {
                    break;
                }

                switch (opcion) {
                    case 1 -> continuar = ejecutarMenuGrafo(scanner,
                            new GrafoDirigido(VERTICES_INICIALES), "dirigido");
                    case 2 -> continuar = ejecutarMenuGrafo(scanner,
                            new GrafoNoDirigido(VERTICES_INICIALES), "no dirigido");
                    case 0 -> continuar = false;
                    default -> System.out.println("Opción no válida.");
                }
            }
            System.out.println("Programa finalizado.");
        }
    }

    private static boolean ejecutarMenuGrafo(Scanner scanner, GrafoBase grafo, String tipo) {
        while (true) {
            System.out.printf("%n=== GRAFO %s (%d vértices) ===%n",
                    tipo.toUpperCase(), grafo.getNumVertices());
            System.out.println("1. Agregar vértice");
            System.out.println("2. Eliminar vértice");
            System.out.println("3. Agregar arista");
            System.out.println("4. Eliminar arista");
            System.out.println("5. Modificar conexión");
            System.out.println("6. Mostrar matriz de adyacencia");
            System.out.println("7. Listar conexiones");
            System.out.println("8. Consultar arista");
            System.out.println("0. Volver al menú principal");

            Integer opcion = leerEntero(scanner, "Seleccione una opción: ");
            if (opcion == null) {
                return false;
            }

            try {
                switch (opcion) {
                    case 1 -> {
                        int vertice = grafo.agregarVertice();
                        System.out.println("Vértice " + vertice + " agregado.");
                    }
                    case 2 -> {
                        int vertice = leerEnteroRequerido(scanner, "Índice del vértice a eliminar: ");
                        grafo.eliminarVertice(vertice);
                        System.out.println("Vértice eliminado; los índices posteriores se renumeraron.");
                    }
                    case 3 -> {
                        int origen = leerEnteroRequerido(scanner, "Vértice de origen: ");
                        int destino = leerEnteroRequerido(scanner, "Vértice de destino: ");
                        grafo.agregarArista(origen, destino);
                        System.out.println("Arista agregada.");
                    }
                    case 4 -> {
                        int origen = leerEnteroRequerido(scanner, "Vértice de origen: ");
                        int destino = leerEnteroRequerido(scanner, "Vértice de destino: ");
                        grafo.eliminarArista(origen, destino);
                        System.out.println("Arista eliminada (si existía).");
                    }
                    case 5 -> modificarConexion(scanner, grafo);
                    case 6 -> grafo.imprimirMatriz();
                    case 7 -> grafo.listarConexiones();
                    case 8 -> consultarArista(scanner, grafo);
                    case 0 -> {
                        return true;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException excepcion) {
                System.out.println("Error: " + excepcion.getMessage());
            } catch (IllegalStateException excepcion) {
                return false;
            }
        }
    }

    private static void modificarConexion(Scanner scanner, GrafoBase grafo) {
        int origen = leerEnteroRequerido(scanner, "Vértice de origen: ");
        int destino = leerEnteroRequerido(scanner, "Vértice de destino: ");
        int estado = leerOpcion(scanner, "Nueva conexión (1 = existe, 0 = no existe): ", 0, 1);
        grafo.actualizarArista(origen, destino, estado == 1);
        System.out.println("Conexión actualizada.");
    }

    private static void consultarArista(Scanner scanner, GrafoBase grafo) {
        int origen = leerEnteroRequerido(scanner, "Vértice de origen: ");
        int destino = leerEnteroRequerido(scanner, "Vértice de destino: ");
        System.out.printf("La arista %d -> %d %s.%n", origen, destino,
                grafo.existeArista(origen, destino) ? "existe" : "no existe");
    }

    private static int leerOpcion(Scanner scanner, String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEnteroRequerido(scanner, mensaje);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.printf("Ingrese un número entre %d y %d.%n", minimo, maximo);
        }
    }

    private static int leerEnteroRequerido(Scanner scanner, String mensaje) {
        Integer valor = leerEntero(scanner, mensaje);
        if (valor == null) {
            throw new IllegalStateException("La entrada se cerró.");
        }
        return valor;
    }

    private static Integer leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!scanner.hasNextLine()) {
                return null;
            }
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.valueOf(entrada);
            } catch (NumberFormatException excepcion) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }
    }
}
