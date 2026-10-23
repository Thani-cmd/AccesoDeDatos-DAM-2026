package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LeeFicheroCSV {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("productos.csv"))) {
            String linea;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                String[] linea2 = linea.split(";", -1);
                Object[] lineaBuena = new Object[linea2.length];
                if (linea2.length < 3) {
                    System.out.println("Error en la línea " + numLinea + ": debe contener al menos 3 campos.");
                    continue;
                }
                try {
                    for (int i = 0; i < 3; i++) {
                        try {
                            if (linea2[i].isBlank()) {
                                System.out.println("Advertencia: Uno de los 3 campos no está completo en la linea: " + numLinea);
                                lineaBuena[i] = null;
                                continue;
                            }

                            if (i == 1) {
                                lineaBuena[i] = linea2[i];
                            } else if (linea2[i].contains(".")) {
                                lineaBuena[i] = Double.parseDouble(linea2[i]);
                            } else {
                                lineaBuena[i] = Integer.parseInt(linea2[i]);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error en la línea " + numLinea + ", campo " + (i + 1) + ": " + e.getMessage());
                        }
                    }
                    Producto p = new Producto((Integer) lineaBuena[0], (String) lineaBuena[1], (Double) lineaBuena[2]);
                    productos.add(p);
                    System.out.println("Línea " + numLinea + ": " + p + "\n");
                } catch (Exception e) {
                    System.out.println("Error al crear el producto de la línea " + numLinea + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}