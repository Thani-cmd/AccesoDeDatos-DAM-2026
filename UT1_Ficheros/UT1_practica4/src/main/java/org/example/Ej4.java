package org.example;

import java.io.*;

public class Ej4 {
    public static void main(String[] args) {

        try (
                BufferedReader leer1 = new BufferedReader(new FileReader("parte1.txt"));
                BufferedReader leer2 = new BufferedReader(new FileReader("parte2.txt"));
                BufferedWriter combinado = new BufferedWriter(new FileWriter("documento_completo.txt"))) {

            String linea;
            String linea2;

            while ((linea = leer1.readLine()) != null) {

                combinado.write(linea);
                combinado.newLine();
            }

            combinado.write("----------------------------------------");
            combinado.newLine();


            while ((linea2 = leer2.readLine()) != null) {
                combinado.append(linea2);
                combinado.newLine();
            }
            leer1.close();
            leer2.close();
            combinado.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
