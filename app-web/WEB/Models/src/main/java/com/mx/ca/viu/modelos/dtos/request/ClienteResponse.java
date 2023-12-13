/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ClienteResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public ClienteResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("clienteId")
        private Long clienteId;

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }

       

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
