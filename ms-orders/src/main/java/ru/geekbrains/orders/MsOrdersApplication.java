package ru.geekbrains.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsOrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsOrdersApplication.class, args);
    }
}
