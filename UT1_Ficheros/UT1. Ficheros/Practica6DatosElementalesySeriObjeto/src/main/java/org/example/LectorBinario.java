package org.example;

import java.io.*;
import java.util.ArrayList;

public class LectorBinario {
    public static void main(String[] args){
        ArrayList<String> productos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("productos.dat");
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0){
                int codigo = dis.readInt();
                String nombre = dis.readUTF();
                double precio = dis.readDouble();
                productos.add(codigo + " - " + nombre + " - " + precio + " €");
            }
            for (String p : productos) {
                System.out.println(p);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }

