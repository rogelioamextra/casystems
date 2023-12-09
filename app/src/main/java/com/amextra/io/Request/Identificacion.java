package com.amextra.io.Request;

import java.io.Serializable;

public class Identificacion implements Serializable {

    public long id;
    public String tipoIdentificacionId;
    public String noIdentificacion;
    public String claveElector;
    public String vigencia;
    public String emision;
    public String imagen;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTipoIdentificacionID() { return tipoIdentificacionId; }
    public void setTipoIdentificacionID(String value) { this.tipoIdentificacionId = value; }

    public String getNoIdentificacion() { return noIdentificacion; }
    public void setNoIdentificacion(String value) { this.noIdentificacion = value; }

    public String getClaveElector() { return claveElector; }
    public void setClaveElector(String value) { this.claveElector = value; }

    public String getVigencia() { return vigencia; }
    public void setVigencia(String value) { this.vigencia = value; }

    public String getEmision() { return emision; }
    public void setEmision(String value) { this.emision = value; }

    public String getImagen() { return imagen; }
    public void setImagen(String value) { this.imagen = value; }
}
