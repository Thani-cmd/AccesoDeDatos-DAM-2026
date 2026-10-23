package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Ej3 {
    public static void main(String[] args) {

        try {
            FileWriter a = new FileWriter("notas.txt");
            String linea1 = "Hola buenas tardes";
            String linea2 = "\nAdios";
            a.write(linea1);
            a.append(linea2);
            a.close();

            FileWriter b = new FileWriter("notas.txt", true);
            b.append("\nNueva linea");
            b.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
