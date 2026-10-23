package org.example;

import java.io.*;
import java.util.ArrayList;

public class EscribeFicheroCSV {
    public static void main(String[] args) {
            ArrayList<Producto> productos = new ArrayList<>();
            productos.add(new Producto(1, "Almeja", 12.0));
            productos.add(new Producto(2, "Mesa", 3.0));
            productos.add(new Producto(3, "Potaje", 5.4));

            //algunos null
            productos.add(new Producto(4, null, 3.2));
            productos.add(new Producto(null, "Potaje", 2.3));
            productos.add(new Producto(5, "Arroz", null));


        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.csv"))) {
            for(Producto p : productos){
                String linea = "";
                if(p.getPrUnit() == null){
                    linea = p.getCod()+";"+ p.getDesc()+"; ";
                }else if (p.getDesc() == null){
                    linea = p.getCod()+"; "+";"+p.getPrUnit();
                }else if (p.getCod() == null){
                    linea = " ;"+p.getDesc()+";"+p.getPrUnit();
                } else{
                    linea = p.getCod() + ";"+p.getDesc()+";"+p.getPrUnit();
                }
                bw.write(linea);
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
