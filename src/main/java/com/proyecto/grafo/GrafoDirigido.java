package com.proyecto.grafo;

public class GrafoDirigido extends GrafoBase {
    public GrafoDirigido(int verticesIniciales) {
        super(verticesIniciales);
    }

    @Override
    protected void establecerConexion(int origen, int destino, boolean existe) {
        matriz[origen][destino] = existe;
    }

    @Override
    protected boolean debeListarConexion(int origen, int destino) {
        return true;
    }
}
