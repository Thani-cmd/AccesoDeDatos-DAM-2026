package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GestorAlmacenMultiformato {
    public static String RUTACSV="importacion_productos.csv";
    public static String RUTADAT="inventario.dat";
    public static String RUTAJSON="backup_inventario.json";

    public static final int LONG_NOMBRE = 30;
    public static final int TAM_REGISTRO = 4 + (LONG_NOMBRE * 2) + 8 + 4;
    public static final int COMIENZO_STOCK = 4 + (LONG_NOMBRE * 2) + 8;

    public static void main(String[] args) {
        prepararEntorno();
        List<Producto> productos = leerCSV(RUTACSV);
        escribirRandomAccess(RUTADAT, productos);
        exportarJSON(RUTAJSON, productos);

    }

    public static void prepararEntorno(){
        File carpeta = new File("./datos_almacen/.");
        if(!carpeta.exists()){
            boolean creado = carpeta.mkdirs();
            if(creado){
                System.out.println("Fichero creado");
            }else{
                System.out.println("No se pudo crear");
            }
        }

        File fichero = new File(RUTACSV);
        if(!fichero.exists()){
            ArrayList<Producto> productos = new ArrayList<>();
            productos.add(new Producto(1, "Almeja", 5.0, 10));
            productos.add(new Producto(2, "Mesa", 15.0, 5));
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTACSV))) {
                for(Producto p : productos){
                    String linea = p.getId() + ";"
                            + p.getNombre() + ";"
                            + p.getPrecio() + ";"
                            + p.getStock();
                    bw.write(linea);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static List<Producto> leerCSV(String rutaCSV) {
        ArrayList<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] lineaSplit = linea.split(";", -1);
                try {
                    int id = Integer.parseInt(lineaSplit[0].trim());
                    String nombre = lineaSplit[1].trim();
                    double precio = Double.parseDouble(lineaSplit[2].trim());
                    int stock = Integer.parseInt(lineaSplit[3].trim());
                    productos.add(new Producto(id, nombre, precio, stock));

                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        return productos;
    }

    public static void escribirRandomAccess(String rutaDat, List<Producto> productos){
        try(var raf = new RandomAccessFile(rutaDat, "rw")){
            raf.setLength(0);
            for(Producto p : productos){
                raf.writeInt(p.getId());

                StringBuilder sb = new StringBuilder(p.getNombre());
                sb.setLength(LONG_NOMBRE);
                raf.writeChars(sb.toString());

                raf.writeDouble(p.getPrecio());
                raf.writeInt(p.getStock());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void actualizarStockRandomAccess(String rutaDat, int posicion, int nuevoStock){
        try (RandomAccessFile raf = new RandomAccessFile(RUTADAT, "rw")) {
            long byteDondeEscribir  = (long) posicion * TAM_REGISTRO + COMIENZO_STOCK;
            raf.seek(byteDondeEscribir );
            raf.writeInt(nuevoStock);
        } catch (IOException e) {
            System.out.println("Error al actualizar el stock: " + e.getMessage());
        }
   }

    public static void exportarJSON(String rutaJSON, List<Producto> productos){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaJSON))) {
            gson.toJson(productos, bw);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}