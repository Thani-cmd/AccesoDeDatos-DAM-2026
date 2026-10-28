package org.example;

public class Producto {

    private Integer cod;
    private String desc;
    private Double prUnit;

    public Producto(Integer cod, String desc, Double prUnit) {
        this.cod = cod;
        this.desc = desc;
        this.prUnit = prUnit;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public Double getPrUnit() {
        return prUnit;
    }

    @Override
    public String toString() {
        return "Codigo del producto: " + getCod() + "\nDescripcion del producto: " + getDesc()
                + "\nPrecio: " + getPrUnit();
    }
}
