/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.servicioCorreo.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author rzavaleta
 */
@Service("EmailService")
public class EmailServiceImpl implements EmailService {
    
    private static final String NOREPLY_ADDRESS = "raul.zavaletazea@gmail.com";
    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class.getName());
    
    @Autowired
    private JavaMailSender emailSender;
    
    @Override
    public void sendEmail(String to, String subject, String body) {
        
        try {
            
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessage.setContent(body, "text/html");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(NOREPLY_ADDRESS);
            emailSender.send(mimeMessage);
            
            logger.info("Correo enviado con asunto: {}", subject);
        }   
        
        catch (MessagingException | MailException e) {            
            logger.error("Error enviando: {}", e.getMessage());
        }       

    }
}
