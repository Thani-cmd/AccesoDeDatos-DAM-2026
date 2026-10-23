package org.example;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Filewriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            FileWriter a = new FileWriter("hola2.txt");
            System.out.println("Escribe tu nombre:");
            String nombre = scanner.nextLine();
            a.write(nombre);
            a.close();

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
