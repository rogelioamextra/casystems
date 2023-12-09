java -jar -Dspring.profiles.active=qa catalogos/servicioConsultaCatalogos-0.0.1-SNAPSHOT.jar  & 
java -jar  gataway/gateway-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=qa login/Login-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=qa curp/servicioCurp-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=qa ocrine/servicioOcrIdentificacion-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=qa sms/servicioSms-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=qa solicitud/servicioSolicitud-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=qa clientes/servicioClientes-0.0.1-SNAPSHOT.jar &
