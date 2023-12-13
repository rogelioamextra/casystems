

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan("com.mx.ca.viu.modelos")
@SpringBootApplication(scanBasePackages = "com.mx.ca.viu")
@EnableScheduling
public class ServicioVerificacionSms {

    public static void main(String[] args) {
            SpringApplication.run(ServicioVerificacionSms.class, args);
    }

}
