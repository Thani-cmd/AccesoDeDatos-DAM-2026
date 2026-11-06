package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MiPrimerJDBC {
    private static final String URL = "jdbc:mysql://100.87.254.101:3306/practica1";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "thani";

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            creacionTablas(c);
            insertarDatos(c);
            consultaTabla(c);

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    private static void creacionTablas(Connection c) {
        try (Statement s = c.createStatement()) {
            s.execute("USE practica1;");

            s.execute("CREATE TABLE IF NOT EXISTS departamentos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nombreDep VARCHAR(50))");

            s.execute("CREATE TABLE IF NOT EXISTS empleados (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " + //Lo puse para no poner los id a mano
                    "nombre VARCHAR(50), " +
                    "id_departamento INT, " +
                    "FOREIGN KEY (id_departamento) REFERENCES departamentos(id))");
            System.out.println("Tablas creadas correctamente");

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void insertarDatos(Connection c) throws SQLException {
        try(Statement s = c.createStatement()){
            s.executeUpdate("INSERT INTO departamentos(nombreDep) VALUES " +
                    "('Tecnologia'), " +
                    "('Atención al cliente') ");

            s.executeUpdate("INSERT INTO empleados(nombre, id_departamento) VALUES " +
                    "('Pepito', 1), " +
                    "('Pepita', 1), " +
                    "('Pepite', 2)");
            System.out.println("Datos insertados\n");
        }
    }

    private static void consultaTabla(Connection c){
        try(Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(
                    "SELECT E.id, E.nombre, D.nombreDep " +
                            "FROM departamentos AS D " +
                            "INNER JOIN empleados AS E ON E.id_departamento = D.id "
            );
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String nombreDep = rs.getString("nombreDep");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Nombre: " + nombreDep);
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}