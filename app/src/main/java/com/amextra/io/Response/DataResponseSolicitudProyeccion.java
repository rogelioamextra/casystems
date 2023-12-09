package com.amextra.io.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class DataResponseSolicitudProyeccion implements Serializable {

    public ArrayList<ListaProyeccion> listaProyeccion;
    public String pdf;

    public ArrayList<ListaProyeccion> getListaProyeccion() { return listaProyeccion; }
    public void setListaProyeccion(ArrayList<ListaProyeccion> value) { this.listaProyeccion = value; }

    public Object getPDF() { return pdf; }
    public void setPDF(String value) { this.pdf = value; }
}
