package org.Tarea;

import java.awt.font.NumericShaper;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ej2 {
    public static void main(String[] args) {

    String[] array = {"100", "250", "30A", "400", "XYZ"};

        for (int i = 0; i < array.length; i++) {
            try {
                array[i] = String.valueOf(Integer.parseInt(array[i]));
                System.out.println("Convertido a numero: " + array[i]);
            }catch(NumberFormatException e){
                System.out.println("El índice número " + i + " está corrupto (" + array[i] + ")");
            }
        }
    }
}
