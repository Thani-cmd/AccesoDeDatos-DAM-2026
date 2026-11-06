package org.example;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;

public class UT1Ficheros {

    // ================= PREPARAR FICHEROS DE PRUEBA =================
    static void prepararFicheros() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("a.bin")) {
            fos.write(new byte[]{1, 2, 3, 4, 5});
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"))) {
            bw.write("Primera línea");
            bw.newLine();
            bw.write("Segunda línea");
            bw.newLine();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("alumnos.csv"))) {
            bw.write("Ana;20;8.5");
            bw.newLine();
            bw.write("Luis;22;7.0");
            bw.newLine();
            bw.write("Marta;abc;6.0");   // línea corrupta a propósito, para probar NumberFormatException
            bw.newLine();
        }
    }

    // Modelo: Serializable + serialVersionUID, wrappers para poder usar null
    static class Persona implements Serializable {
        private static final long serialVersionUID = 1L;
        String nombre;
        Integer edad;
        Double nota;

        Persona(String nombre, Integer edad, Double nota) {
            this.nombre = nombre; this.edad = edad; this.nota = nota;
        }
        @Override public String toString() { return nombre + ";" + edad + ";" + nota; }
    }

    public static void main(String[] args) {
        try {
            prepararFicheros();
            clase_File();
            texto_y_binario();
            csv();
            dataStreams();
            objetos();
            randomAccess();
            gson();
            xstream();
        } catch (IOException e) {
            System.err.println("Error E/S: " + e.getMessage());
        }
    }

    // ================= 4. CLASE FILE =================
    static void clase_File() throws IOException {
        File f = new File("datos" + File.separator + "notas.txt");   // separador multiplataforma
        File dir = new File("datos");

        dir.mkdir();                         // crea directorio
        f.createNewFile();                   // crea fichero

        System.out.println(f.exists() + " " + f.isFile() + " " + f.isDirectory());
        System.out.println(f.canRead() + " " + f.canWrite() + " " + f.canExecute());
        System.out.println(f.length() + " " + f.getName() + " " + f.getParent());

        String[] nombres = dir.list();       // nombres
        File[] ficheros = dir.listFiles();   // objetos File

        f.renameTo(new File("datos" + File.separator + "notas2.txt"));  // renombrar/mover
        new File("datos/notas2.txt").delete();   // borrar (directorio solo si está vacío)
        File temp = File.createTempFile("tmp", ".txt");
        Path_interop(f);
    }
    static void Path_interop(File f) { f.toPath(); }   // interoperabilidad con java.nio

    // ================= 5 y 7. EXCEPCIONES + FLUJOS =================
    static void texto_y_binario() {
        // Binario: read() devuelve -1 al final
        try (FileInputStream in = new FileInputStream("a.bin");
             FileOutputStream out = new FileOutputStream("b.bin")) {
            int b;
            while ((b = in.read()) != -1) out.write(b);
        } catch (FileNotFoundException e) {      // checked, más específica primero
            System.err.println("No existe");
        } catch (IOException e) {                // checked
            System.err.println("Error E/S");
        } finally {
            System.out.println("finally: se ejecuta siempre");
        }

        // Texto con buffer (try-with-resources cierra solo)
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();                    // salto de línea multiplataforma
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================= 8. CSV =================
    static void csv() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                try {
                    String nombre = c[0];
                    Integer edad = Integer.parseInt(c[1]);     // wrapper admite null
                    Double nota = Double.parseDouble(c[2]);
                    System.out.println(new Persona(nombre, edad, nota));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Dato corrupto: " + linea);   // unchecked
                }
            }
        }
    }

    // ================= 9. DATA STREAMS (primitivos, sin null) =================
    static void dataStreams() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("d.dat"))) {
            dos.writeUTF("Ana");
            dos.writeInt(20);
            dos.writeDouble(8.5);
        }
        try (DataInputStream dis = new DataInputStream(new FileInputStream("d.dat"))) {
            while (dis.available() > 0) {        // o capturar EOFException
                String n = dis.readUTF();
                int e = dis.readInt();
                double nt = dis.readDouble();
                System.out.println(n + " " + e + " " + nt);
            }
        }
    }

    // ================= 9. SERIALIZACIÓN DE OBJETOS =================
    static void objetos() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("p.obj"))) {
            oos.writeObject(new Persona("Ana", 20, 8.5));
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("p.obj"))) {
            Persona p = (Persona) ois.readObject();
            System.out.println(p);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ================= 10. RANDOMACCESSFILE =================
    // Registro fijo: id (int = 4 bytes) + nombre (20 chars = 40 bytes) = 44 bytes
    static final int LONG_NOMBRE = 20;
    static final int LONG_REG = 4 + LONG_NOMBRE * 2;

    static void randomAccess() throws IOException {
        // modos: "r", "rw", "rwd", "rws"
        try (RandomAccessFile raf = new RandomAccessFile("reg.dat", "rw")) {
            // Escribir registro número 2
            int numReg = 2;
            raf.seek((long) numReg * LONG_REG);          // posición = nº * longitud
            raf.writeInt(100);
            StringBuilder sb = new StringBuilder("Ana");
            sb.setLength(LONG_NOMBRE);                   // rellena a longitud fija
            raf.writeChars(sb.toString());

            // Leer registro número 2
            raf.seek((long) numReg * LONG_REG);
            int id = raf.readInt();
            char[] cs = new char[LONG_NOMBRE];
            for (int i = 0; i < LONG_NOMBRE; i++) cs[i] = raf.readChar();
            System.out.println(id + " " + new String(cs).trim());

            raf.seek(0);
            raf.skipBytes(LONG_REG);                     // avance relativo
            System.out.println("Registros: " + raf.length() / LONG_REG);
        }
    }

    // ================= 12. GSON =================
    static void gson() throws IOException {
        Gson gson = new Gson();   // omite nulls, JSON compacto
        Gson bonito = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .setDateFormat("dd/MM/yyyy")
                .create();

        // Objeto individual
        Persona p = new Persona("Ana", 20, null);
        String json = bonito.toJson(p);
        try (FileWriter fw = new FileWriter("p.json")) { bonito.toJson(p, fw); }
        try (FileReader fr = new FileReader("p.json")) {
            Persona leida = gson.fromJson(fr, Persona.class);
        }
        Persona desdeString = gson.fromJson(json, Persona.class);

        // Colección: TypeToken por el type erasure
        List<Persona> lista = new ArrayList<>(List.of(new Persona("Ana", 20, 8.5),
                new Persona("Luis", 22, 7.0)));
        Type tipoLista = new TypeToken<ArrayList<Persona>>() {}.getType();
        try (FileWriter fw = new FileWriter("lista.json")) { bonito.toJson(lista, fw); }
        try (FileReader fr = new FileReader("lista.json")) {
            List<Persona> leidas = gson.fromJson(fr, tipoLista);
        }

        // Arrays se pueden directamente
        Persona[] arr = gson.fromJson(gson.toJson(lista), Persona[].class);

        // Map: clave -> propiedad, valor -> valor
        Map<String, Persona> mapa = new HashMap<>();
        mapa.put("a1", new Persona("Ana", 20, 8.5));
        Type tipoMapa = new TypeToken<HashMap<String, Persona>>() {}.getType();
        String jsonMapa = gson.toJson(mapa);
        Map<String, Persona> mapaLeido = gson.fromJson(jsonMapa, tipoMapa);
    }

    // ================= 13. XSTREAM =================
    static void xstream() throws IOException {
        XStream xs = new XStream();
        xs.allowTypes(new Class[]{Persona.class, ArrayList.class});   // permisos de seguridad
        // xs.allowTypesByWildcard(new String[]{"mipaquete.**"});
        xs.alias("persona", Persona.class);                           // alias de clase
        xs.alias("lista", ArrayList.class);
        xs.aliasField("years", Persona.class, "edad");                // alias de atributo

        // Objeto individual
        Persona p = new Persona("Ana", 20, 8.5);
        String xml = xs.toXML(p);
        try (FileWriter fw = new FileWriter("p.xml")) { xs.toXML(p, fw); }
        try (FileReader fr = new FileReader("p.xml")) {
            Persona leida = (Persona) xs.fromXML(fr);
        }

        // Colección
        List<Persona> lista = new ArrayList<>(List.of(new Persona("Ana", 20, 8.5),
                new Persona("Luis", 22, 7.0)));
        try (FileWriter fw = new FileWriter("lista.xml")) { xs.toXML(lista, fw); }
        try (FileReader fr = new FileReader("lista.xml")) {
            List<Persona> leidas = (List<Persona>) xs.fromXML(fr);
        }

        // Colección implícita: sin etiqueta contenedora de la lista
        // (requiere una clase contenedora con un campo List<Persona>)
        // xs.addImplicitCollection(Contenedor.class, "personas");
    }
}