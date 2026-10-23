package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actcions.Execute"/> icon in the gutter.
public class Ej1 {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.print("ERROR: No hay ningún argumento.");
        }
            String imagen = args[0];
            try {
                FileInputStream fis = new FileInputStream(imagen);
                FileOutputStream fos = new FileOutputStream("copia_imagen.png");

                byte[] bytesImagen = new byte[1024];
                int bytes;

                while ((bytes = fis.read(bytesImagen)) != -1){
                    fos.write(bytesImagen, 0, bytes);
                }
            } catch (IOException e) {
                System.out.print("ERROR: " + e.getMessage());
            }



    }
}