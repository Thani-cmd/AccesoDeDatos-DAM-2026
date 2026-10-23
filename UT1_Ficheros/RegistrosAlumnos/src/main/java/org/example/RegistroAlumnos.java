package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RegistroAlumnos {
    private static final int TAMAÑO_REGISTRO = 12;

    public static void main (String[] args){

        String fich = "alumnos.dat";
        try(var raf = new RandomAccessFile(fich, "rw")){

            EscribirRegistros(raf, 0, 1,  5.0);
            EscribirRegistros(raf, 1, 2,  7.6);
            EscribirRegistros(raf, 2, 3,  8.4);
            EscribirRegistros(raf, 3, 4,  9.4);

            LeerRegistro(raf, 0);
            LeerRegistro(raf, 1);
            LeerRegistro(raf, 2);
            LeerRegistro(raf, 3);



        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void EscribirRegistros(RandomAccessFile raf, int numReg, int id, double nota) throws IOException {
        long posicion = (long)numReg * TAMAÑO_REGISTRO;
        raf.seek(posicion);
        raf.writeInt(id);
        raf.writeDouble(nota);


    }

    public static void LeerRegistro(RandomAccessFile raf, int numReg) throws IOException {
        long posicion = (long)numReg * TAMAÑO_REGISTRO;
        raf.seek(posicion);
        int identificador = raf.readInt();
        double nota = raf.readDouble();
        System.out.println("El registro numero " + numReg + " tiene un id de " +  identificador + " y tiene una nota de: " + nota);

    }
}