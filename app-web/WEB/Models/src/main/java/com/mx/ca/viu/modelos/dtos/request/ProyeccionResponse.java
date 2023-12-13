/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.response.Proyeccion;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ProyeccionResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public ProyeccionResponse() {
        data=new Data();
        setResponse(new Response());
    }

    public static class Data implements Serializable {

        private List<Proyeccion> listaProyeccion;
        private String pdf;

        public String getPdf() {
            return pdf;
        }

        public void setPdf(String pdf) {
            this.pdf = pdf;
        }
        
        

        public List<Proyeccion> getListaProyeccion() {
            return listaProyeccion;
        }

        public void setListaProyeccion(List<Proyeccion> listaProyeccion) {
            this.listaProyeccion = listaProyeccion;
        }

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
