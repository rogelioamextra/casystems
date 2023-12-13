/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CodigoPostal;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class SolicitudIdResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public SolicitudIdResponse() {
        data = new Data();
        setResponse(new Response());
    }

    public static class Data implements Serializable {

        @JsonProperty("solicitud")
        private MvSolicitudesAmextra solicitud;

        public MvSolicitudesAmextra getSolicitud() {
            return solicitud;
        }

        public void setSolicitud(MvSolicitudesAmextra solicitud) {
            this.solicitud = solicitud;
        }

      

         
        

       
        
        

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    

}
