package org.Tarea;

import java.io.File;
import java.io.IOException;

public class Ej4 {
    public static void main(String[] args) {

        File subcarpeta = new File("subcarpeta/subcarpeta/subcarpeta/fichero.txt");

        try {
            System.out.print(subcarpeta.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally{
            System.out.println("\nCerrando programa...");
        }

    }
}
