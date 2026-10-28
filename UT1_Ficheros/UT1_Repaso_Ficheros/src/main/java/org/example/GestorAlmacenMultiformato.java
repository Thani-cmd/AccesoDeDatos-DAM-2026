package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GestorAlmacenMultiformato {
    public static String RUTACSV="importacion_productos.csv";

    public static void main(String[] args) {
        prepararEntorno();

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
        try (BufferedReader br = new BufferedReader(new FileReader(RUTACSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] lineaSplit = linea.split(";", -1);
                /*
                 if (i == 1) {
                                lineaBuena[i] = linea2[i];
                            } else if (linea2[i].contains(".")) {
                                lineaBuena[i] = Double.parseDouble(linea2[i]);
                            } else {
                                lineaBuena[i] = Integer.parseInt(linea2[i]);
                            }
                 */
                try {
                    if(lineaSplit[0].equals("1")){
                        lineaSplit[0] = String.valueOf(Integer.parseInt(lineaSplit[0]));
                    } else if(){

                    }


                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

}