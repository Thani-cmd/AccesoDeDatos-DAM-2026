package org.example;

public class Empleado {
    private Integer numEmpleado;
    private String dni;
    private String nombre;
    private Double salarioBrutoAnual;
    private Boolean tiempoParcial;

    public Empleado(Integer numEmpleado, Double salarioBrutoAnual, Boolean tiempoParcial, String nombre, String dni) {
        this.numEmpleado = numEmpleado;
        this.salarioBrutoAnual = salarioBrutoAnual;
        this.tiempoParcial = tiempoParcial;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Integer getNumEmpleado() {
        return numEmpleado;
    }

    public Boolean getTiempoParcial() {
        return tiempoParcial;
    }

    public Double getSalarioBrutoAnual() {
        return salarioBrutoAnual;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "numEmpleado=" + getNumEmpleado() +
                ", dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", salarioBrutoAnual=" + getSalarioBrutoAnual() +
                ", tiempoParcial=" + getTiempoParcial() +
                '}';
    }
}
