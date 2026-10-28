package org.example;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> coleccion = new ArrayList<Libro>();

    public Biblioteca(List<Libro> coleccion) {
        this.coleccion = new ArrayList<Libro>(coleccion);
    }

    public Biblioteca() {
    }

    public List<Libro> getColeccion() {
        return coleccion;
    }

    public void addLibro(Libro libro){
        coleccion.add(libro);
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "coleccion=" + coleccion +
                "}";
    }
}
