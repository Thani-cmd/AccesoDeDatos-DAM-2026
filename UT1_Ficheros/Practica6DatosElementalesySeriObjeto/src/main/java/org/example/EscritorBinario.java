package org.example;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EscritorBinario {
    public static void main(String[] args) {
        String archivo_nombre = "productos.dat";
        Object[][] productos = {
                {1, "Atun", 2.5},
                {2, "Cafe", 0.85}
        };

        try (FileOutputStream fos = new FileOutputStream(archivo_nombre);
             DataOutputStream dos = new DataOutputStream(fos)) {

            for(Object[] producto: productos){
                dos.writeInt((Integer) producto[0]);
                dos.writeUTF((String) producto[1]);
                dos.writeDouble((Double) producto[2]);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException("El archivo no pudo ser creado o encontrado", e);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir datos en el flujo", e);
        }
    }
}