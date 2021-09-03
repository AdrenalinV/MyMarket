package ru.geekbrains.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "ru.geekbrains")
@EnableFeignClients(basePackages = "ru.geekbrains")
public class MsOrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsOrdersApplication.class, args);
    }
}
