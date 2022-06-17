package pl.ing.clientdatareaderschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableFeignClients
public class ClientDataReaderScheduleApplication {

    public static String localhostAddress = "http://localhost:8080/api/customer";
    public static void main(String[] args) {
        SpringApplication.run(ClientDataReaderScheduleApplication.class, args);
    }

}
