package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EscribeEmpleadosCSV {
    public static void main(String[] args) {

        ArrayList<Empleado> empleados = new ArrayList<>();

        empleados.add(new Empleado(1, 24000.0, false, "Ana Pérez", "12345678A"));
        empleados.add(new Empleado(2, 18500.50, true, "Luis García", "87654321B"));

        empleados.add(new Empleado(4, 21000.0, false, "Carlos Díaz", "null"));
        empleados.add(new Empleado(5, 19500.0, true, null, "55667788D"));
        empleados.add(new Empleado(6, 22000.0, false, "", "99887766E"));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("empleados.csv"))) {

            for(Empleado em : empleados){
                String linea = "";
                if (em.getTiempoParcial() == true){
                    linea = em.getNumEmpleado() + ";" +em.getSalarioBrutoAnual() + ";1;" +em.getNombre() + ";" +em.getDni();
                } else{
                    linea = em.getNumEmpleado() + ";" +em.getSalarioBrutoAnual() + ";0;" +em.getNombre() + ";" +em.getDni() ;
                }
                bw.write(linea);
                bw.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}