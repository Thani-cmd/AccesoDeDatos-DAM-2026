package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int a, b, resultado;
        a=6;
        b=2;
        resultado= a/b;
        System.out.printf("El resultado de %d/%d es: %d ", a,b,resultado);

        b=0;
        try {
            resultado = a / b;
            System.out.printf("El resultado de %d/%d es %d es: ", a, b, resultado);
        }catch(ArithmeticException e){
            System.out.print("\nERROR: Ha ocurrido una excepción: " + e.getMessage() );
        }

        b=20;
            resultado = a / b;
            System.out.printf("El resultado de %d/%d es %d es: ", a, b, resultado);
    }
}