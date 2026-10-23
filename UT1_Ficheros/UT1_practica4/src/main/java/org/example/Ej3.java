package org.example;

import java.io.*;

public class Ej3 {
    public static void main(String[] args) {
        try (BufferedReader leer = new BufferedReader(new FileReader("borrador.txt"));

             BufferedWriter escribir = new BufferedWriter(new FileWriter("resultado_limpio.txt"))) {
            String linea;

            while ((linea = leer.readLine()) != null) {
                String mayusLineas = linea.toUpperCase();
                if(mayusLineas.trim().isEmpty()){
                    continue; //Es como un break
                }
                escribir.write(mayusLineas);
                escribir.newLine();
            }
            leer.close();
            escribir.close();


        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
