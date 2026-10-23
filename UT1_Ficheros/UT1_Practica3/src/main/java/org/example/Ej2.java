package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Ej2 {
    public static void main(String[] args) {
            try{
                FileInputStream fis = new FileInputStream("datos.dat");
                int b;
                int bytes=0;
                int bytesNulos = 0;
                while ((b = fis.read()) != -1) {
                    bytes++;
                    System.out.print(b);
                    if(b == 0x00){
                        bytesNulos++;
                    }
                }
                fis.close();
                System.out.println("\nHay una cantidad de bytes de: " + bytes);
                System.out.println("Hay una cantidad de bytes nulos de: " + bytesNulos);
            }catch (FileNotFoundException e) {
                System.out.println("No se encontró el archivo");
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());;
            }
    }
}
