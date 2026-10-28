package org.example;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GestorBibliotecaXML {

    public static XStream configurarXStream(){
        XStream xstream = new XStream();

        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypes(new Class[]{Libro.class, Biblioteca.class});
        xstream.alias("biblioteca", Biblioteca.class);
        xstream.alias("libro", Libro.class);
        xstream.addImplicitCollection(Biblioteca.class, "coleccion");
        return xstream;
    }

    public static void guardarBiblioteca(String ruta, Biblioteca biblioteca){
        XStream xstream = configurarXStream();
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write(xstream.toXML(biblioteca));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Biblioteca cargarBibliotecaXML(String ruta){
        XStream xstream = configurarXStream();

        File fichero = new File(ruta);
        if(!fichero.exists()){
            return new Biblioteca();
        }
        try (FileReader fr = new FileReader(ruta)) {
            return (Biblioteca) xstream.fromXML(fr);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new Biblioteca();
        }
    }

    public static List<Libro> generarDatosIniciales(){
        List<Libro> coleccionLibro = new ArrayList<>();
        Libro libro1 = new Libro("111", "Libro feo", "Pepito feo", 14.99);
        Libro libro2 = new Libro("112", "Libro intermedio", "Pepito intermedio", 14.99);
        Libro libro3 = new Libro("113", "Libro bonito", "Pepito bonito", 14.99);
        coleccionLibro.add(libro1);
        coleccionLibro.add(libro2);
        coleccionLibro.add(libro3);

        /*otra forma de hacerlo
        coleccionLibro.add(new Libro("111", "Libro feo", "Pepito feo", 14.99));
         */
        return coleccionLibro;
    }

    public static void main(String[] args) {
        //Ej 1
        String ruta = "biblioteca_contenedor.xml";
        File fichero = new File(ruta);
        Biblioteca biblioteca;

        if (fichero.exists()) {
            biblioteca = cargarBibliotecaXML(ruta);
        } else {
            biblioteca = new Biblioteca();
            for(Libro libro : generarDatosIniciales()){
                biblioteca.addLibro(libro);
            }
            guardarBiblioteca(ruta, biblioteca);
        }

        //Ej 2
        System.out.println(biblioteca);

        //Ej 3
        biblioteca.addLibro(new Libro("114", "Elden ring", "Pepita", 14.99));

        //Ej 4
        guardarBiblioteca(ruta, biblioteca);

        //Ej 5
        Biblioteca biblioteca2 = cargarBibliotecaXML(ruta);
        System.out.println(biblioteca2);

    }
}