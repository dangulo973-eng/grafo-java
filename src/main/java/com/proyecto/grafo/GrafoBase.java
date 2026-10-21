package com.proyecto.grafo;

public abstract class GrafoBase {
    protected boolean[][] matriz;
    protected int numVertices;

    protected GrafoBase(int verticesIniciales) {
        if (verticesIniciales < 0) {
            throw new IllegalArgumentException("El número de vértices no puede ser negativo.");
        }
        numVertices = verticesIniciales;
        matriz = new boolean[verticesIniciales][verticesIniciales];
    }

    public int agregarVertice() {
        boolean[][] nuevaMatriz = new boolean[numVertices + 1][numVertices + 1];
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(matriz[i], 0, nuevaMatriz[i], 0, numVertices);
        }
        matriz = nuevaMatriz;
        return numVertices++;
    }

    public void eliminarVertice(int vertice) {
        validarVertice(vertice);
        boolean[][] nuevaMatriz = new boolean[numVertices - 1][numVertices - 1];

        for (int i = 0, nuevoI = 0; i < numVertices; i++) {
            if (i == vertice) {
                continue;
            }
            for (int j = 0, nuevoJ = 0; j < numVertices; j++) {
                if (j != vertice) {
                    nuevaMatriz[nuevoI][nuevoJ++] = matriz[i][j];
                }
            }
            nuevoI++;
        }

        matriz = nuevaMatriz;
        numVertices--;
    }

    public void agregarArista(int origen, int destino) {
        actualizarArista(origen, destino, true);
    }

    public void eliminarArista(int origen, int destino) {
        actualizarArista(origen, destino, false);
    }

    public void actualizarArista(int origen, int destino, boolean existe) {
        validarVertice(origen);
        validarVertice(destino);
        establecerConexion(origen, destino, existe);
    }

    public boolean existeArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);
        return matriz[origen][destino];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void imprimirMatriz() {
        System.out.print("    ");
        for (int j = 0; j < numVertices; j++) {
            System.out.printf("%3d", j);
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%3d ", i);
            for (int j = 0; j < numVertices; j++) {
                System.out.printf("%3d", matriz[i][j] ? 1 : 0);
            }
            System.out.println();
        }
    }

    public void listarConexiones() {
        System.out.println("Conexiones:");
        boolean hayConexiones = false;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matriz[i][j] && debeListarConexion(i, j)) {
                    System.out.printf("  %d -> %d%n", i, j);
                    hayConexiones = true;
                }
            }
        }
        if (!hayConexiones) {
            System.out.println("  (no hay conexiones)");
        }
    }

    protected void validarVertice(int vertice) {
        if (vertice < 0 || vertice >= numVertices) {
            throw new IllegalArgumentException(
                    "Vértice inválido: " + vertice + ". Debe estar entre 0 y " + (numVertices - 1) + ".");
        }
    }

    protected abstract void establecerConexion(int origen, int destino, boolean existe);

    protected abstract boolean debeListarConexion(int origen, int destino);
}
