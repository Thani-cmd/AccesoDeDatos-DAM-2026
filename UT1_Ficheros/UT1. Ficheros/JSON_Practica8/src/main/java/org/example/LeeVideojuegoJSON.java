package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeeVideojuegoJSON {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (BufferedReader br = new BufferedReader(new FileReader("videojuego.json"))) {
            Videojuego juego1 = gson.fromJson(br, Videojuego.class);
            System.out.println("Archivo: " + juego1);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String json = "{\"id\": 2, \"titulo\": \"Hollow Knight\", \"precio\": 20.00}";
        Videojuego cadena_texto = gson.fromJson(json, Videojuego.class);
        System.out.println("Cadena texto: " + cadena_texto);

    }
}
