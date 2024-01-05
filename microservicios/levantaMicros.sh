java -jar  gateway-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa servicioConsultaCatalogos-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa Login-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa servicioCurp-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa servicioOcrIdentificacion-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa servicioSms-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa servicioSolicitud-0.0.1-SNAPSHOT.jar & 
java -jar -Dspring.profiles.active=qa servicioClientes-0.0.1-SNAPSHOT.jar & 