package com.mx.ca.viu.microservicios.servicioConsultaCP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.mx.ca.viu.modelos")
@SpringBootApplication(scanBasePackages = "com.mx.ca.viu")
@ComponentScan("com.mx.ca.viu")
@EnableJpaRepositories("com.mx.ca.viu")

public class ServicioConsultaCPApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioConsultaCPApplication.class, args);
    }

}
