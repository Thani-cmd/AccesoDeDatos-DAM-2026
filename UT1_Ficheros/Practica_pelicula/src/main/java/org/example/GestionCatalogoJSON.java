package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GestionCatalogoJSON {

    public static List<Peliculas> cargarCatalogo(){
        File archivo = new File("catalogo_complejo.json");

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (FileReader fr = new FileReader("catalogo_complejo.json")) {
            Type tipoPelicula = new TypeToken<List<Peliculas>>(){}.getType();
            Gson gson = new Gson();
            return gson.fromJson(fr, tipoPelicula);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarCatalogo(List<Peliculas> catalogo){
        try (FileWriter fw = new FileWriter("catalogo_complejo.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(catalogo, fw);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        //ej1
        File archivo = new File("catalogo_complejo.json");
        List<Peliculas> catalogo = cargarCatalogo();

        if (!archivo.exists()) {
            Director director1 = new Director("Christopher Vega", "Reino Unido");

            List<String> reparto1 = new ArrayList<>();
            reparto1.add("Leonardo Marín");
            reparto1.add("Elena Page");

            Peliculas pelicula1 = new Peliculas(reparto1, director1, 148, "El Origen del Tiempo", 1);
            catalogo.add(pelicula1);
        }

        //ej2
        if(catalogo.size() == 1) {//hice este if para q cada vez que se ejecute el main, no cree la misma pelicula repetidamente
            Director director2 = new Director("Marta Solís", "España");

            List<String> reparto2 = new ArrayList<>();
            reparto2.add("Carlos Duarte");
            reparto2.add("Lucía Ferrer");

            Peliculas pelicula2 = new Peliculas(reparto2, director2, 122, "La Ciudad Sumergida", 2);
            catalogo.add(pelicula2);
        }

        //ej3
        guardarCatalogo(catalogo);

        //ej4
        List<Peliculas> catalogoFinal = cargarCatalogo();
        for (Peliculas p : catalogoFinal) {
            System.out.println(p);
        }

    }
}