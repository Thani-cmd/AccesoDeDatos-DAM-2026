package org.example;

public class Producto {
    private int id;
    private String nombre;
    private Double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock=stock;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Codigo del producto: " + getId() + "\nDescripcion del producto: " + getNombre()
                + "\nPrecio: " + getPrecio() + "\nStock:" + getStock();
    }
}
