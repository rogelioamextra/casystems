package com.amextra.io.Response;

import java.io.Serializable;

public class IdDtIdentificacion implements Serializable {
    public long idDtIdentificacion;
    public String noIdentificacion;
    public String claveElector;
    public String imagen;
    public String anverso;
    public String reverso;
    public String vigencia;
    public String emision;

    public String getIdIdentificaciones() {
        return idIdentificaciones;
    }

    public void setIdIdentificaciones(String idIdentificaciones) {
        this.idIdentificaciones = idIdentificaciones;
    }

    public String idIdentificaciones;
    public  IdTipoIdentificacion idTipoIdentificacion;

    public long getIDDtIdentificacion() { return idDtIdentificacion; }
    public void setIDDtIdentificacion(long value) { this.idDtIdentificacion = value; }

    public String getNoIdentificacion() { return noIdentificacion; }
    public void setNoIdentificacion(String value) { this.noIdentificacion = value; }

    public String getClaveElector() { return claveElector; }
    public void setClaveElector(String value) { this.claveElector = value; }

    public Object getImagen() { return imagen; }
    public void setImagen(String value) { this.imagen = value; }

    public String getAnverso() { return anverso; }
    public void setAnverso(String value) { this.anverso = value; }

    public String getReverso() { return reverso; }
    public void setReverso(String value) { this.reverso = value; }

    public String getVigencia() { return vigencia; }
    public void setVigencia(String value) { this.vigencia = value; }

    public String getEmision() { return emision; }
    public void setEmision(String value) { this.emision = value; }

    public IdTipoIdentificacion getIDTipoIdentificacion() { return idTipoIdentificacion; }
    public void setIDTipoIdentificacion(IdTipoIdentificacion value) { this.idTipoIdentificacion = value; }
}
