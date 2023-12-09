/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.commons.Transform;
import com.mx.ca.viu.services.ServiciosExternosFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "serviciosExternosFactory")

public class ServiciosExternosFactoryImpl implements ServiciosExternosFactory {

    final Transform transform = new Transform();

    @Override
    public <T> T procesarServicio(Object input, Class<T> definitionRef) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}
