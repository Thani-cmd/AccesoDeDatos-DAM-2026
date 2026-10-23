package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[][] matriz = new int[2][3];
        try {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    matriz[i][j] = i + j;
                    System.out.print(matriz[i][j]);
                }
                System.out.println(" ");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("ERROR: Ocurrió una excepción" + e.getMessage());
        }

        //int[][] matriz2 = new int[3][3];
        try {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j < 3; j++) {
                    try {
                        matriz[i][j] = i / j;
                        System.out.print(matriz[i][j]);
                    } catch (ArithmeticException e) {
                        System.out.print("\nERROR: " + e.getMessage() + " ");
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("\nERROR: Ocurrió una excepción: " + e.getMessage());
        }
    }
}