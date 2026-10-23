package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ej4 {
    public static void main(String[] args) {
        try {
            FileReader leer = new FileReader("notas.txt");
            int i;
            int cantidadCaracter = 0;
            int vocales = 0;
            while((i = leer.read()) != -1){
                cantidadCaracter++;
                char caracter = Character.toLowerCase((char) i); //si no pongo esto no detectara las vocales mayus
                if(caracter == 'a' || caracter == 'e'|| caracter == 'i'|| caracter == 'o'|| caracter == 'u'){
                    ++vocales;
                }
            }
            System.out.println("Cantidad de caracteres: " + cantidadCaracter);
            System.out.println("Cantidad de vocales: " + vocales);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se encontró el archivo" + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());;
        }
    }
}
