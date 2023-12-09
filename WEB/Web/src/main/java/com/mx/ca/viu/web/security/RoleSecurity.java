/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.web.security;

/**
 *
 * @author jbecerril
 */
import org.springframework.security.core.GrantedAuthority;

public class RoleSecurity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String nombre;

    @Override
    public String getAuthority() {
        return this.nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}