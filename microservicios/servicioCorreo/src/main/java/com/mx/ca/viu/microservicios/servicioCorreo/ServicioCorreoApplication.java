package com.mx.ca.viu.microservicios.servicioCorreo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.mx.ca.viu.modelos")
@SpringBootApplication(scanBasePackages = "com.mx.ca.viu")
public class ServicioCorreoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioCorreoApplication.class, args);
	}

}
