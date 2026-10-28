package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EscribeVideojuegoJSON {
    public static void main(String[] args) {

    Videojuego juego1 = new Videojuego(1, "Elden ring", 69.99);
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("videojuego.json"))) {
            gson.toJson(juego1, bw);
            System.out.println("Creado");

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }


    }
}