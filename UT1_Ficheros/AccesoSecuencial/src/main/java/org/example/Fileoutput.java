package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fileoutput {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.print("ERROR: No hay ningún argumento.");
        } else{
            String imagen = args[0];
            try {
                FileInputStream fis = new FileInputStream(imagen);
                FileOutputStream fos = new FileOutputStream("fenix2.png");
                int b;
                while ((b = fis.read()) != -1){
                    fos.write(b);
                }
            } catch (IOException e) {
                System.out.print("ERROR: " + e.getMessage());
            }

        }
    }
}
