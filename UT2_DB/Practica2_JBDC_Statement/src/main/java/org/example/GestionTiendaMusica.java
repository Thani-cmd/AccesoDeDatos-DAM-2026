package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GestionTiendaMusica {
    private static final String URL = "jdbc:mysql://100.87.254.101:3306/practica2";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "thani";

    public static void main(String[] args) {
        try (Connection c = obtenerConexion()) {
            crearTablas(c);
            insertarDatos(c);
            modificarDatos(c);
            consultarDatos(c);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public static void crearTablas(Connection conn) {
        try (Statement s = conn.createStatement()) {
            s.execute("CREATE TABLE IF NOT EXISTS artistas (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nombre VARCHAR(60) NOT NULL, " +
                    "pais VARCHAR(40))");

            s.execute("CREATE TABLE IF NOT EXISTS albumes(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "titulo VARCHAR(80) NOT NULL, " +
                    "precio DOUBLE NOT NULL, " +
                    "id_artista INT, " +
                    "FOREIGN KEY(id_artista) REFERENCES artistas(id))");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void insertarDatos(Connection conn) {
        try (Statement s = conn.createStatement()) {
            int numArtistas = s.executeUpdate("INSERT INTO artistas(nombre, pais)" +
                    "VALUES ('Queen', 'Reino Unido'), " +
                    "('Rosalia', 'España'), " +
                    "('Bad Bunny', 'Puerto Rico')");
            System.out.println("Artistas insertados: " + numArtistas);


            int numAlbum = s.executeUpdate("INSERT INTO albumes(titulo, precio, id_artista)" +
                    "VALUES('Lux', 15.90, 2), " +
                    "('Oasis', 20.00, 3), " +
                    "('Un verano sin ti', 20.00, 3), " +
                    "('Queen II', 15.99, 1)");
            System.out.println("Albumes insertados: " + numAlbum);

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void modificarDatos(Connection conn) {
        try (Statement s = conn.createStatement()) {
            int num_registos1 = s.executeUpdate("UPDATE albumes " +
                    "SET precio = 5.99 " +
                    "WHERE titulo = 'Lux'");
            System.out.println("Numero de registros afectados en albumes al editar: " + num_registos1);

            int num_registros2 = s.executeUpdate("DELETE FROM albumes " +
                    "WHERE id = 2");
            System.out.println("Numero de registros afectados en albumes al eliminar: " + num_registros2);


        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void consultarDatos(Connection conn) {
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(
                     "SELECT A.id, A.titulo, A.precio, AR.nombre FROM albumes AS A " +
                             "INNER JOIN artistas as AR ON A.id_artista = AR.id;"
             )) {
            while (rs.next()) {
                int album_id = rs.getInt("id");
                String album_titulo = rs.getString("titulo");
                double album_precio = rs.getDouble("precio");
                String artista_nombre = rs.getString("nombre");
                System.out.println("ID: " + album_id);
                System.out.println("Titulo: " + album_titulo);
                System.out.println("Precio: " + album_precio);
                System.out.println("Artista: " + artista_nombre);
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}