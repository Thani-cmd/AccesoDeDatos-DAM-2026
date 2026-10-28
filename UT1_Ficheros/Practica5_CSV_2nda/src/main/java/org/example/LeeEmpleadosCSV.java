package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LeeEmpleadosCSV {
    public static void main(String[] args) {

        ArrayList<Empleado> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("empleados.csv"))) {
            String linea;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                String[] linea2 = linea.split(";", -1);

                if (linea2.length < 5) {
                    System.err.println("Error: línea " + numLinea + " con formato incorrecto: " + linea);
                    continue;
                }
                String dni = linea2[3];
                String nombre = linea2[4];


                if (nombre.isBlank() || nombre.equals("null") || dni.isBlank() || dni.equals("null")) {
                    System.err.println("Error: DNI o nombre vacío en la línea " + numLinea + "\n" + Arrays.toString(linea2) + "\n" );
                    continue;
                }
                boolean tf = linea2[2].equals("1");
                int numEmpleado = Integer.parseInt(linea2[0]);
                double salario = Double.parseDouble(linea2[1]);

                Empleado em = new Empleado(numEmpleado, salario, tf, nombre, dni);
                empleados.add(em);
                System.out.println("Línea " + numLinea + ": " + em + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}