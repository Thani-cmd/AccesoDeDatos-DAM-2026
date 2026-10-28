package org.example;

public class Videojuego {
    private Integer id;
    private String titulo;
    private Double precio;

    public Videojuego(int id, String titulo, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
