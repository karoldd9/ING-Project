package pl.ing.mailings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MailingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingsApplication.class, args);
    }

}
