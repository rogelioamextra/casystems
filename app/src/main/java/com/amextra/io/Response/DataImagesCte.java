package com.amextra.io.Response;

import java.io.Serializable;

public class DataImagesCte implements Serializable {

    public String imgAnverso;
    public String imgReverso;
    public String selfie;
    public String otraIdentificacion;
    public String comprobanteDomicilio;

    public String getImgAnverso() { return imgAnverso; }
    public void setImgAnverso(String value) { this.imgAnverso = value; }

    public String getImgReverso() { return imgReverso; }
    public void setImgReverso(String value) { this.imgReverso = value; }

    public String getSelfie() { return selfie; }
    public void setSelfie(String value) { this.selfie = value; }

    public String getOtraIdentificacion() { return otraIdentificacion; }
    public void setOtraIdentificacion(String value) { this.otraIdentificacion = value; }

    public String getComprobanteDomicilio() { return comprobanteDomicilio; }
    public void setComprobanteDomicilio(String value) { this.comprobanteDomicilio = value; }
}
