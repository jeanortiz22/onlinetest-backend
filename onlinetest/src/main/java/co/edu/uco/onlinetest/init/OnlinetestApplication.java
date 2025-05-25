package co.edu.uco.onlinetest.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.onlinetest"})
public class OnlinetestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinetestApplication.class, args);
    }

}
