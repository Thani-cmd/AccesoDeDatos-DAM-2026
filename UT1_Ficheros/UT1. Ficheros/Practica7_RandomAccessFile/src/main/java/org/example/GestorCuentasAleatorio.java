package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GestorCuentasAleatorio {
    private static final int TAMAÑO_REGISTRO = 32;

    public static void escribirCuenta(RandomAccessFile raf, int numReg, int id,  String titular, double saldo) throws IOException {
        long posicion = (long)numReg * TAMAÑO_REGISTRO;
        raf.seek(posicion);
        raf.writeInt(id);
        raf.writeUTF(titular);
        raf.writeDouble(saldo);
    }

    public static void leerCuenta(RandomAccessFile raf, int numReg) throws IOException {
        long posicion = (long)numReg * TAMAÑO_REGISTRO;
        raf.seek(posicion);
        int identificador = raf.readInt();
        String titular = raf.readUTF();
        double saldo = raf.readDouble();

        System.out.println("El registro numero " + numReg + " tiene un id de " +  identificador + " " +
                "\nEl " + titular + " tiene un saldo de: " + saldo);

    }

    public static void actualizarSaldo(RandomAccessFile raf, int numReg, double incremento) throws IOException {
        long posicion = (long)numReg * TAMAÑO_REGISTRO;
        raf.seek(posicion);
        raf.readInt();//para saltarlos
        raf.readUTF();

        double saldoActual = raf.readDouble();
        double saldoNuevo = saldoActual + incremento;

        raf.seek(raf.getFilePointer() - 8); //el getfilepointer le pregunté a la ia, pa que te voy a mentir
        raf.writeDouble(saldoNuevo);
        System.out.println("El saldo se actualizó a " + saldoNuevo);
    }

    public static void main(String[] args) {

        String fich = "cuentas.dat";
        try(var raf = new RandomAccessFile(fich, "rw")){

            escribirCuenta(raf, 0, 1, "Pepe", 100.0);

            leerCuenta(raf,0);

            actualizarSaldo(raf, 0, 500.0);
            leerCuenta(raf,0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}