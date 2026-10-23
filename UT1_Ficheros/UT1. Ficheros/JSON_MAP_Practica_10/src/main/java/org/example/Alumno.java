package org.example;

import java.util.List;

public class Alumno {
    private String nombre;
    private List<Asignatura> asignaturas;

    public Alumno(String nombre, List<Asignatura> asignaturas) {
        this.nombre = nombre;
        this.asignaturas = asignaturas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public double calcularNotaMedia(){
        double notaMedia;
        double notas = 0;
        int total = 0;
        for(int i = 0; i < asignaturas.size();i++){
            notas += asignaturas.get(i).getCalificacion();
            total++;
        }

        notaMedia = notas/total;

        return notaMedia;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", asignaturas=" + asignaturas +
                '}';
    }
}
