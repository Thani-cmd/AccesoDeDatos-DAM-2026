
package org.example;

import java.sql.*;

public class Main {

    private static final String URL =
            "jdbc:mysql://100.87.254.101:3306/basededatos";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "thani";

    public static void main(String[] args) {

        try (Connection c = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {

            creacionTablas(c);
            insertarDatos(c);
            consultaTabla(c);

            System.out.println("Tablas creadas correctamente.");

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void creacionTablas(Connection c) {
        try (Statement s = c.createStatement()) {
            s.execute("DROP TABLE IF EXISTS productos");
            s.execute("DROP TABLE IF EXISTS categoria");

            s.execute("CREATE TABLE categoria (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nombreCat VARCHAR(100))");



            s.execute("CREATE TABLE productos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nombre VARCHAR(100), " +
                    "precio DECIMAL(10,2), " +
                    "id_categoria INT, " +
                    "FOREIGN KEY (id_categoria) REFERENCES categoria(id))");

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void insertarDatos(Connection c) throws SQLException {
        try(Statement s = c.createStatement()){
            s.executeUpdate("INSERT INTO categoria(nombreCat) VALUES " +
                    "('Primeros platos'), " +
                    "('Segundos platos'), " +
                    "('Quesos'), " +
                    "('Menús')");

            s.executeUpdate("INSERT INTO productos(nombre, precio, id_categoria) VALUES " +
                    "('Gazpacho', 5.30, 1), " +
                    "('Tortilla de papa', 4.50, 2), " +
                    "('Queso de cabra', 7.00, 3), " +
                    "('Menu burrata Macdonald', 11.80, 4)");
        }
    }

    private static void consultaTabla(Connection c){
        try(Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(
                    "SELECT p.id, p.nombre, p.precio, c.nombreCat " +
                            "FROM productos AS p " +
                            "INNER JOIN categoria AS c ON p.id_categoria = c.id " +
                            "WHERE p.precio > 5.00"
            );
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String categoria = rs.getString("nombreCat");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("Categoría: " + categoria);
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
