package com.amextra.Beans;

import java.io.Serializable;

public class ImagesIdentificacion implements Serializable {

   public String reverso;
   public String anverso;

    public boolean isEsAltaEdicion() {
        return esAltaEdicion;
    }

    public void setEsAltaEdicion(boolean esAltaEdicion) {
        this.esAltaEdicion = esAltaEdicion;
    }

    public String rostro;

   public boolean esAltaEdicion;

    public String getReverso() {
        return reverso;
    }

    public String getRostro() {
        return rostro;
    }

    public void setRostro(String rostro) {
        this.rostro = rostro;
    }

    public void setReverso(String reverso) {
        this.reverso = reverso;
    }

    public String getAnverso() {
        return anverso;
    }

    public void setAnverso(String anverso) {
        this.anverso = anverso;
    }

}
