/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service
public class InfoUsuarioDTO implements Serializable {

    @JsonProperty("Idempresa")
    private Long Idempresa;
    @JsonProperty("logoEmpresa")
    private byte[] logoEmpresa;
    @JsonProperty("color")
    private String color;
    @JsonProperty("preferenciaIdioma")
    private String preferenciaIdioma;
    @JsonProperty("nombreEmpresa")
    private String nombreEmpresa;
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;
    @JsonProperty("username")
    private String username;
    @JsonProperty("usuarioId")
    private String usuarioId;
    @JsonProperty("token")
    private String token;
    
    public InfoUsuarioDTO() {
    }

    public InfoUsuarioDTO(Long Idempresa, byte[] logoEmpresa, String color, String preferenciaIdioma, String nombreEmpresa) {
        this.Idempresa = Idempresa;
        this.logoEmpresa = logoEmpresa;
        this.color = color;
        this.preferenciaIdioma = preferenciaIdioma;
        this.nombreEmpresa = nombreEmpresa;
    }

    public Long getIdempresa() {
        return Idempresa;
    }

    public void setIdempresa(Long Idempresa) {
        this.Idempresa = Idempresa;
    }

    public byte[] getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(byte[] logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPreferenciaIdioma() {
        return preferenciaIdioma;
    }

    public void setPreferenciaIdioma(String preferenciaIdioma) {
        this.preferenciaIdioma = preferenciaIdioma;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
    
    
    
    

}
