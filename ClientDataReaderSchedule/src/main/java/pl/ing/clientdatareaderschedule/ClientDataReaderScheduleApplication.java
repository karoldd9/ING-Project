package pl.ing.clientdatareaderschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.ing.clientdatareaderschedule.reader.DataReader;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableFeignClients
public class ClientDataReaderScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientDataReaderScheduleApplication.class, args);
    }

}
