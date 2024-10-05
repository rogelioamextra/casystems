/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

/**
 *
 * @author rogel
 */
public class RequestBodySms {
    
    private final String lada = "52";

    private String message ="";

    private String phoneNumber ;

    public String getLada() {
        return lada;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "RequestBodySms{" + "lada=" + lada + ", message=" + message + ", phoneNumber=" + phoneNumber + '}';
    }


    
    
}
