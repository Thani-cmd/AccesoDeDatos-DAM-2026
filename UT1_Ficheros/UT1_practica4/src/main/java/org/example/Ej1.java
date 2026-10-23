package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ej1 {
    public static void main(String[] args) {
        try {
            FileReader leer = new FileReader("log.txt");
            BufferedReader leerLinea = new BufferedReader(leer);
            int numError = 0;
            String linea;
            while ((linea = leerLinea.readLine()) != null){
                if(linea.toLowerCase().contains("error")) {
                    numError++;
                }
            }
            System.out.println("Hay " + numError);
            leer.close();
            leerLinea.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }

}