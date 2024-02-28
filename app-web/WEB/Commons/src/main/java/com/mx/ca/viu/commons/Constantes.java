/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.commons;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author jbecerril
 */
public class Constantes {

    public static final Long ID_EMPRESA_VIU = 1L;
    public static ObjectMapper mapper = new ObjectMapper();
    public static final int KEYBYTES = 654658744;
    public static final String KEYENCRIPTION = "4M3XTRA2022";
    public static final String CODIGO_EMPRESA_CORE_AMEXTRA = "00024";
    public static final Long CONSTANTE_PROPERTIES_TASA = 1L;
    public static final Long CONSTANTE_PROPERTIES_IVA = 2L;
    public static final Long TOKEN_VALIDITY = 1800000L; // Valor de configuración del tiempo de expiración del token móvil

    public static final String JWTSECRET = "4m3xtr42023";

}
