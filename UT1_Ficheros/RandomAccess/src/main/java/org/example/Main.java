package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String fich = "holamundo.txt";

        try(var raf = new RandomAccessFile(fich, "rw")){
            raf.writeBytes("Hola mundo Java");
            raf.seek(0);
            byte[] buffer = new byte[5];
            raf.read(buffer);
            System.out.println("He leído la primera palabra: " + new String(buffer));

            int skipB = raf.skipBytes(5);
            System.out.println("He avanzado numero de bytes: " + skipB);
            raf.read(buffer);
            System.out.println("He leído la palabra: " + new String(buffer));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}