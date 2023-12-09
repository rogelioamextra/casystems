package com.amextra.io.Request;

import java.io.Serializable;

public class DataCitasAgente implements Serializable {
    private String asesor;
    private String fecha;

    public String getAsesor() { return asesor; }
    public void setAsesor(String value) { this.asesor = value; }

    public String getFecha() { return fecha; }
    public void setFecha(String value) { this.fecha = value; }
}
