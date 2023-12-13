package com.mx.ca.viu.microservicios.servicioOcrIdentificacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@EntityScan("com.mx.ca.viu.modelos")
@SpringBootApplication(scanBasePackages = "com.mx.ca.viu")
//@EnableFeignClients
@EnableJpaRepositories("com.mx.ca.viu")

public class ServicioExtDatosOcrIdentifiacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioExtDatosOcrIdentifiacionApplication.class, args);
    }
    
}
