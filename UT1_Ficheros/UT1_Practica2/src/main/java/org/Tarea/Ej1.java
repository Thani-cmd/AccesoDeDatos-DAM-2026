package org.Tarea;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ej1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Dame un número entero: ");
            int num1 = scanner.nextInt();

            System.out.println("Dame otro número entero: ");
            int num2 = scanner.nextInt();

            int resultado = num1 /num2;
            System.out.println("La división entre esos dos números es: " + resultado);

        }catch(InputMismatchException e){
            System.out.print("ERROR: Escriba un número, no un texto");
        }catch(ArithmeticException e){
            System.out.print("No se puede dividir entre 0");
        }
    }
}