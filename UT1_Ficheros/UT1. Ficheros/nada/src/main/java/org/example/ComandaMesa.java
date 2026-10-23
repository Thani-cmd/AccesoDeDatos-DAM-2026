package org.example;

public class ComandaMesa {
    private int numMesa;
    private int asiento_ocupado;
    private boolean estado_activo;


    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public boolean isEstado_activo() {
        return estado_activo;
    }

    public void setEstado_activo(boolean estado_activo) {
        this.estado_activo = estado_activo;
    }

    public int getAsiento_ocupado() {
        return asiento_ocupado;
    }

    public void setAsiento_ocupado(int asiento_ocupado) {
        this.asiento_ocupado = asiento_ocupado;
    }
}
