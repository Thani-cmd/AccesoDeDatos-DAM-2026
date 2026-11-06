package org.example;

public class Vehiculo {
    private Integer id;
    private String marca;
    private Double precio;
    private Integer stock;

    public Vehiculo(Integer id, String marca, Double precio, Integer stock) {
        this.id = id;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }



}
