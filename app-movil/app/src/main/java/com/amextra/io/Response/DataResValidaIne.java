package com.amextra.io.Response;

import java.io.Serializable;

public class DataResValidaIne implements Serializable {
    public String noIdentificacion;
    public String claveElector;
    public String vigencia;
    public Object emision;

    public Object getNoIdentificacion() { return noIdentificacion; }
    public void setNoIdentificacion(String value) { this.noIdentificacion = value; }

    public String getClaveElector() { return claveElector; }
    public void setClaveElector(String value) { this.claveElector = value; }

    public String getVigencia() { return vigencia; }
    public void setVigencia(String value) { this.vigencia = value; }

    public Object getEmision() { return emision; }
    public void setEmision(Object value) { this.emision = value; }
}
