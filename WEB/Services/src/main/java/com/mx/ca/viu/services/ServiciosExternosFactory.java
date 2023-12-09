/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

/**
 *
 * @author jbecerril
 */
public interface ServiciosExternosFactory {
    
     public  <T> T procesarServicio(Object input, Class<T> definitionRef);
    
}
