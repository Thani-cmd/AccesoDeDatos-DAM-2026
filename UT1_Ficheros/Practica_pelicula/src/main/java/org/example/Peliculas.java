package org.example;

import java.util.List;

public class Peliculas {
    private int id;
    private String nombre;
    private int duracionMinutos;
    private Director director;
    private List<String> reparto;

    public Peliculas(List<String> reparto, Director director, int duracionMinutos, String nombre, int id) {
        this.reparto = reparto;
        this.director = director;
        this.duracionMinutos = duracionMinutos;
        this.nombre = nombre;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<String> getReparto() {
        return reparto;
    }

    public Director getDirector() {
        return director;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Peliculas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracionMinutos=" + duracionMinutos +
                ", director=" + director +
                ", reparto=" + reparto +
                '}';
    }
}
