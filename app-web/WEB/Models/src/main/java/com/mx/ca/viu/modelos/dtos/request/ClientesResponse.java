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
import com.mx.ca.viu.modelos.dtos.response.Cliente;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public class ClientesResponse extends GenericResponse implements Serializable {

    @JsonProperty("data")
    Data data;

    public ClientesResponse() {
        setResponse(new Response());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("listaClientes")
        private List<Cliente> listaClientes;

        public List<Cliente> getListaClientes() {
            return listaClientes;
        }

        public void setListaClientes(List<Cliente> listaClientes) {
            this.listaClientes = listaClientes;
        }

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
