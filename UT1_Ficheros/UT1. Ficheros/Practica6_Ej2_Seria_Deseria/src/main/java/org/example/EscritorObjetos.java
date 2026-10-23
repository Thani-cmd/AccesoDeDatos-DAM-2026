package org.example;

import java.io.*;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EscritorObjetos {
    public static void main(String[] args) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (FileOutputStream fos = new FileOutputStream("empleados.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            empleados.add(new Empleado(1, 24000.0, false, "Ana Pérez", "12345678A"));
            empleados.add(new Empleado(2, 18500.50, true, "Luis García", "87654321B"));

            for(Empleado empleado : empleados){
                oos.writeObject(empleado);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}