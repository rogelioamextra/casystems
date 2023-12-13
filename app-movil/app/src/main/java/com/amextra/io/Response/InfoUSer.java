package com.amextra.io.Response;

import java.io.Serializable;

public class InfoUSer implements Serializable {
    public String nombreUsuario;
    public long infoUSerIdempresa;
    public long idempresa;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String usuarioId;
    public String logoEmpresa;
    public String color;
    public String username;

    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public long getInfoUSerIdempresa() {
        return infoUSerIdempresa;
    }

    public void setInfoUSerIdempresa(long infoUSerIdempresa) {
        this.infoUSerIdempresa = infoUSerIdempresa;
    }

    public long getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(long idempresa) {
        this.idempresa = idempresa;
    }

    public String getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(String logoEmpresa) {
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

    public String preferenciaIdioma;
    public String nombreEmpresa;
}
