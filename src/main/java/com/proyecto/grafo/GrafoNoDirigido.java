package com.proyecto.grafo;

public class GrafoNoDirigido extends GrafoBase {
    public GrafoNoDirigido(int verticesIniciales) {
        super(verticesIniciales);
    }

    @Override
    protected void establecerConexion(int origen, int destino, boolean existe) {
        matriz[origen][destino] = existe;
        matriz[destino][origen] = existe;
    }

    @Override
    protected boolean debeListarConexion(int origen, int destino) {
        return origen <= destino;
    }
}
