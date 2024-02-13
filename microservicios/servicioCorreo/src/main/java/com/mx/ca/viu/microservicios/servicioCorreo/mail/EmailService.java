/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioCorreo.mail;

/**
 *
 * @author rzavaleta
 */
public interface EmailService {
    
    void sendEmail(String to, String subject, String body);
    
}
