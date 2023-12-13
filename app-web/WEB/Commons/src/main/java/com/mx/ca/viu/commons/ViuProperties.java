/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.commons;

import java.util.ResourceBundle;

/**
 *
 * @author jbecerril
 */
public class ViuProperties {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("com.mx.ca.viu.commons.properties.config");

    public static final String BASE_DATOS_URL;
    public static final String BASE_DATOS_USER;
    public static final String BASE_DATOS_PASS;
    public static final String COMPILACION;
    public static final String AMBIENTE;

    static {
        BASE_DATOS_URL = BUNDLE.getString("base.datos.url");
        BASE_DATOS_USER = BUNDLE.getString("base.datos.username");
        BASE_DATOS_PASS = BUNDLE.getString("base.datos.password");
        COMPILACION = BUNDLE.getString("numero.compilacion");
        AMBIENTE = BUNDLE.getString("ambiente");

    }

}
