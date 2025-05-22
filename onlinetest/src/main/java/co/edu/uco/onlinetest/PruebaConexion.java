package co.edu.uco.onlinetest;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebaConexion {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/onlinetest_db";
        String usuario = "postgres";
        String clave = "admin";

        try (Connection conexion = DriverManager.getConnection(url, usuario, clave)) {
            System.out.println(" Conexión exitosa a la base de datos");
        } catch (Exception exception) {
            System.out.println(" Error al conectar:");
            exception.printStackTrace();
        }
    }
}
