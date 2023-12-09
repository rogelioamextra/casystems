package com.amextra.Beans;

import java.io.Serializable;

public class Data implements Serializable {
    private String usuario;
    private String password;

    public String getUsuario() { return usuario; }
    public void setUsuario(String value) { this.usuario = value; }

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }
}
