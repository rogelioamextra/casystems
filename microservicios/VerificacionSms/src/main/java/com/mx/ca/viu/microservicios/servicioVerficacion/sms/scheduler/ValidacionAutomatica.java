/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioVerficacion.sms.scheduler;

import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.repositorys.ValidacionSmsRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author jbecerril
 */
@Component

public class ValidacionAutomatica {

    @Autowired
    ValidacionSmsRepository validacionSmsRepository;
    
    @Scheduled(cron = "0 */5 * * * *")
    public void updateAutomate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(UtilGenerico.obtenerHoraMexico());
        cal.set(Calendar.MINUTE, -5);
        validacionSmsRepository.desabilitaTodos5Minutos(cal.getTime());
        
    }
    
}
