package org.example;

import java.io.FileInputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Fileinput {
    public static void main(String[] args) {
    if(args.length < 1){
        System.out.print("ERROR: No hay ningún argumento.");
    } else{
        String imagen = args[0];
        try {
            FileInputStream fis = new FileInputStream(imagen);
            int b;
            while ((b = fis.read()) != -1){
                fis.read();
                System.out.print(b + " ");
            }
        } catch (IOException e) {
            System.out.print("ERROR: " + e.getMessage());
        }

    }
    }
}