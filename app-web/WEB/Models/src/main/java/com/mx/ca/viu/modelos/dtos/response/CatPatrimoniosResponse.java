/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatDestinoCreditos;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatPatrimonios;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class CatPatrimoniosResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public CatPatrimoniosResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("patrimonios")
        private List<CatPatrimonios> lista;

        public List<CatPatrimonios> getLista() {
            return lista;
        }

        public void setLista(List<CatPatrimonios> lista) {
            this.lista = lista;
        }

     
      
      

     
        

    

      
   

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
