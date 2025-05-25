package co.edu.uco.onlinetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class PruebaConexion implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conexion = dataSource.getConnection()) {
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (Exception exception) {
            System.out.println("❌ Error al conectar:");
            exception.printStackTrace();
        }
    }
}
