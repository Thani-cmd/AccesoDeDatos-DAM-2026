package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Filereader {
    public static void main(String[] args) {
        try {
            FileReader leer = new FileReader("hola.txt");
            int i ;
            while((i= leer.read()) != -1){
                System.out.print((char)i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se encontró el archivo" + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());;
        }
    }
}
