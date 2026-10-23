package org.example;


import java.io.*;

public class Ej2 {
    public static void main(String[] args) {

        try (BufferedReader leer = new BufferedReader(new FileReader("origen.txt"));

             BufferedWriter escribir = new BufferedWriter(new FileWriter("destino_numerado.txt"))) {
            String linea;
            int numero = 1;

            while ((linea = leer.readLine()) != null) {
                escribir.write(numero + ": " + linea);
                escribir.newLine();
                numero++;
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
