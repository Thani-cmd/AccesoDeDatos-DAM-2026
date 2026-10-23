package org.example;

import java.io.*;
import java.util.ArrayList;

public class LectorObjetos {
    public static void main(String[] args) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        boolean continuar = true;
        try (FileInputStream fos = new FileInputStream("empleados.dat");
             ObjectInputStream ois = new ObjectInputStream(fos)) {
            while(continuar){
                try {
                    Empleado empleado = (Empleado) ois.readObject();
                    empleados.add(empleado);
                }catch(EOFException e){
                    continuar = false;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            for (Empleado em : empleados) {
                System.out.println(em);
            }
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
