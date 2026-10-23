package org.example;

public class Director {
    private String nombre;
    private String nacionalidad;

    public Director(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }



    public String getNacionalidad() {
        return nacionalidad;
    }


    @Override
    public String toString() {
        return "Director{" +
                "nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }

}
