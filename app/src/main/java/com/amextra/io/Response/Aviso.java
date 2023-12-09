package com.amextra.io.Response;

import java.io.Serializable;

public class Aviso implements Serializable {

    public String tituloAviso;
    public String contenidoAviso;

    public String getTituloAviso() { return tituloAviso; }
    public void setTituloAviso(String value) { this.tituloAviso = value; }

    public String getContenidoAviso() { return contenidoAviso; }
    public void setContenidoAviso(String value) { this.contenidoAviso = value; }
}
