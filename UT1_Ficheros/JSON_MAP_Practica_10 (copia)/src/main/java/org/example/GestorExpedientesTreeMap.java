
package org.example;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GestorExpedientesTreeMap {
    public static final String RUTA = "expedientes_avanzados.json";

    public static TreeMap<String, Alumno> cargarExpedientes(String ruta) {
        File archivo = new File("expedientes_avanzados.json");
        if (!archivo.exists()) {
            return new TreeMap<>();
        }

        try (FileReader fr = new FileReader(ruta)) {
            Type tipoAlumnos = new TypeToken<TreeMap<String, Alumno>>() {}.getType();
            Gson gson = new Gson();
            return gson.fromJson(fr, tipoAlumnos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarExpedientes(String ruta, TreeMap<String, Alumno> map){
        try (FileWriter fw = new FileWriter(ruta)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(map, fw);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void imprimirMap(Map<String, Alumno> map){
        for (Map.Entry<String, Alumno> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static TreeMap<String, Alumno> generarDatosIniciales(){
        TreeMap<String, Alumno> alumnos_expedientes = new TreeMap<>();

        List<Asignatura> asignaturas1 = new ArrayList<>();
        asignaturas1.add(new Asignatura("Lengua", 7.8));
        asignaturas1.add(new Asignatura("Mates", 5.0));
        alumnos_expedientes.put("EXP-104", new Alumno("Pepito", asignaturas1));

        List<Asignatura> asignaturas2 = new ArrayList<>();
        asignaturas2.add(new Asignatura("Lengua", 5.0));
        asignaturas2.add(new Asignatura("Mates", 6.0));
        alumnos_expedientes.put("EXP-102", new Alumno("Jaimito", asignaturas2));

        List<Asignatura> asignaturas3 = new ArrayList<>();
        asignaturas3.add(new Asignatura("Lengua", 10.0));
        asignaturas3.add(new Asignatura("Mates", 10.0));
        alumnos_expedientes.put("EXP-101", new Alumno("Thani", asignaturas3));

        List<Asignatura> asignaturas4 = new ArrayList<>();
        asignaturas4.add(new Asignatura("Lengua", 7.0));
        asignaturas4.add(new Asignatura("Mates", 7.0));
        alumnos_expedientes.put("EXP-103", new Alumno("Alice", asignaturas4));

        return alumnos_expedientes;
    }

    public static void main(String[] args) {

        //ej1
        TreeMap<String, Alumno> mapAlumno = cargarExpedientes(RUTA);
        if (mapAlumno.isEmpty()) {
            mapAlumno = generarDatosIniciales();
            guardarExpedientes(RUTA, mapAlumno);
        }

        //ej2
        imprimirMap(mapAlumno);

        //ej3
        System.out.println("\nPrimera expediente: " + mapAlumno.firstEntry());
        System.out.println("Último expediente: " + mapAlumno.lastEntry());

        //ej4
        Alumno alumno103 = mapAlumno.get("EXP-103");
        if(alumno103 != null){
            System.out.println("\nEl alumno " + alumno103.getNombre() + " tiene una media de: " + alumno103.calcularNotaMedia());
        }

        //ej5
        Alumno alumno101 = mapAlumno.get("EXP-101");
        if (alumno101 != null) {
            Asignatura asignatura1 = new Asignatura("Acceso a Datos", 9.50);
            alumno101.getAsignaturas().add(asignatura1);
        }

        //ej6
        guardarExpedientes(RUTA, mapAlumno);

        //ej7
        TreeMap<String, Alumno> mapFinal = cargarExpedientes(RUTA);
        imprimirMap(mapFinal);
    }


}

