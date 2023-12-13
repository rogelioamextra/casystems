package com.amextra.io.Request;

import java.io.Serializable;

public class PatrimoniosCls implements Serializable {

    public String tipoPatrimonioId;
    public String precio;
    public String imagen;
    public boolean cambioImagen;
    public  String nombrePtr;

    public String getTipoPatrimonioId() {
        return tipoPatrimonioId;
    }

    public void setTipoPatrimonioId(String tipoPatrimonioId) {
        this.tipoPatrimonioId = tipoPatrimonioId;
    }

    public boolean isCambioImagen() {
        return cambioImagen;
    }

    public String getNombrePtr() {
        return nombrePtr;
    }

    public void setNombrePtr(String nombrePtr) {
        this.nombrePtr = nombrePtr;
    }

    public String getTipoPatrimonioID() { return tipoPatrimonioId; }
    public void setTipoPatrimonioID(String value) { this.tipoPatrimonioId = value; }

    public String getPrecio() { return precio; }
    public void setPrecio(String value) { this.precio = value; }

    public String getImagen() { return imagen; }
    public void setImagen(String value) { this.imagen = value; }

    public boolean getCambioImagen() { return cambioImagen; }
    public void setCambioImagen(boolean value) { this.cambioImagen = value; }
}
