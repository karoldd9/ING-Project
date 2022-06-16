package pl.ing.h2dbconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class H2DbConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(H2DbConnectorApplication.class, args);
    }

}
