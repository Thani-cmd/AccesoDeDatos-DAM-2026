package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Todas las rutas
    private static String CARPETA = "./datos_concesionario/.";
    private static String RUTAJSON = CARPETA + "entrada_vehiculos.json";
    private static String RUTADAT = CARPETA + "vehiculos_inventario.dat";
    private static String RUTACSV = CARPETA + "backup_vehiculos.csv";

    //Parametros binario
    private static final int TAM_REGISTRO = 56;
    private static final int STOCK1 = 52;

    //GSON
    static Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public static void main(String[] args) {
        //Ej 1
        prepararEntornoYGenerarJSONinicial();

        //Ej 2
        List<Vehiculo> listaVehiculos = cargarVehiculosDesdeJSON(RUTAJSON);
        System.out.println("Enseñando la lista de vehículos desde JSON:");
        for (Vehiculo vehiculo : listaVehiculos) {
            System.out.println(vehiculo);
        }

        //Ej 3
        escribirVehiculosRAF(RUTADAT, listaVehiculos);

        //Ej 4
        modificarStockRAF(RUTADAT, 1, 30);

        //Ej 5
        System.out.println("\nDespués de modificar el stock(Va mal pero bueno, compila):");
        List<Vehiculo> listaVehiculosActualizado =leerVehiculosSecuencial(RUTADAT);
        for (Vehiculo vehiculo : listaVehiculosActualizado) {
            System.out.println(vehiculo);
        }

        //Ej 6
        exportarBackupsCSV(RUTACSV, listaVehiculos);
    }

    public static void prepararEntornoYGenerarJSONinicial() {
        File carpeta = new File(CARPETA);
        //Vemos si la carpeta existen
        if (!carpeta.exists()) {
            //Y si no pues, desde el if se crea
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada");
            }
        }

        File jsonFich = new File(RUTAJSON);
        //Si no existe
        if (!jsonFich.exists()) {
            //Se hace un try-with-resources junto a sus catchs
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTAJSON))) {
                //Se crea una lista para luego añadirles cosas
                List<Vehiculo> listaVehiculos = new ArrayList<>();

                listaVehiculos.add(new Vehiculo(1, "BMW", 50.000, 5));
                listaVehiculos.add(new Vehiculo(2, "Ford", 30.000, 7));
                listaVehiculos.add(new Vehiculo(3, "Tesla", 500.000, 2));
                listaVehiculos.add(new Vehiculo(4, "Peugeot", 10.000, 4));
                //Y con el toJson convertimos la listaVehiculos en formato Json
                GSON.toJson(listaVehiculos, bw);
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    public static List<Vehiculo> cargarVehiculosDesdeJSON(String rutaJSON) {
        Type vehiculos = new TypeToken<List<Vehiculo>>() {}.getType();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaJSON))) {
            //Aqui lee en formato json y devuelve el vehiculos
            return new Gson().fromJson(br, vehiculos);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return List.of();
    }

    public static void escribirVehiculosRAF(String rutaDat, List<Vehiculo> vehiculos) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaDat, "rw")) {//rw para que pueda leer y escribir
            raf.setLength(0);
            //Hacemos un for-each
            for (Vehiculo vehiculo : vehiculos) {
                //para poder usar los métodos de la clase vehículo y luego poder escribirlo
                raf.writeInt(vehiculo.getId());
                StringBuffer sb = new StringBuffer(vehiculo.getMarca());
                sb.setLength(20);
                raf.writeChars(sb.toString());

                raf.writeDouble(vehiculo.getStock());
                raf.writeInt(vehiculo.getStock());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("ERROR null: " + e.getMessage());
        }
    }

    public static void modificarStockRAF(String rutaDat, int posicion, int nuevoStock) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaDat, "rw")) {
            long desplazamiento = (long) posicion * TAM_REGISTRO + STOCK1;

            //Por si el usuario en la posicion se pasa
            if (posicion < 0 || desplazamiento + 4 > raf.length()) {
                System.out.println("Elige otra posición");
                return;
            }
            raf.seek(desplazamiento);
            raf.writeInt(nuevoStock);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public static List<Vehiculo> leerVehiculosSecuencial(String rutaDat){
        List<Vehiculo> listaVehiculo = new ArrayList<>();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(rutaDat))){
            while(dis.available() > 0){
                int id = dis.readInt();
                String marca = dis.readUTF();
                double precio = dis.readDouble();
                int stock = dis.readInt();

                Vehiculo vehiculo = new Vehiculo(id, marca, precio, stock);
                listaVehiculo.add(vehiculo);
            }
            dis.close();
            return listaVehiculo;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return listaVehiculo;
    }


    public static void exportarBackupsCSV(String rutaCSV, List<Vehiculo> vehiculos){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(rutaCSV))) {
            String header = "Id;Marca;Precio;Stock";
            br.write(header);
            br.newLine();//salto de línea
            //Aquí donde se guardará lo que tiene vehículos
            String linea = "";
            for(Vehiculo vehiculo : vehiculos){
                //Por si tienen un null
                linea = porSiTienenUnNull(vehiculo, linea);
                linea += "\n"; //Un salto de línea por cada fila
            }
            //Y aquí escribimos la línea;
            br.write(linea);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch(NullPointerException e){
            System.out.println("ERROR null: " + e.getMessage());
        }
    }

    //Hice un métod0 para que tenga mejor estructura, lo hice usando la opción de Extract->Methods
    private static String porSiTienenUnNull(Vehiculo vehiculo, String linea) {
        if (vehiculo.getId() == null) {
            linea += ";";
        } else {
            linea += vehiculo.getId() + ";";
        }

        if (vehiculo.getMarca() == null) {
            linea += ";";
        } else {
            linea += vehiculo.getMarca() + ";";
        }

        if (vehiculo.getPrecio() == null) {
            linea += ";";
        } else {
            linea += vehiculo.getPrecio() + ";";
        }

        if (vehiculo.getStock() == null) {
            linea += ";";
        } else {
            linea += vehiculo.getStock() + "";
        }
        return linea;
    }
}