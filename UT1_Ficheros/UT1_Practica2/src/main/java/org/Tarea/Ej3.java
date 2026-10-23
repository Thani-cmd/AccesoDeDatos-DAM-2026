package org.Tarea;

import java.io.File;
import java.io.IOException;

public class Ej3 {
    public static void main(String[] args) {

        File subcarpeta = new File("/carpeta_inexistente");
        File fichero = new File( subcarpeta, "documento.txt");

        try {
            if(fichero.createNewFile()){
                System.out.print("Archivo creado");
            }
        } catch (IOException e) {
            System.out.print("ERROR: No se puede crear el archivo ya que no existe una subcarpeta" + e.getMessage());
        }
    }
}