/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import java.io.Serializable;

/**
 *
 * @author jbecerril
 */
public class ProyeccionRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;

    public ProyeccionRequest() {
        setAuth(new Auth());
        this.data = new Data();
    }

    public static class Data implements Serializable {
        private String montoCredito;
        private String numeroCuotas;
        private String frecuenciaId;
        private String clienteId;
        private String productoId;

        public String getMontoCredito() {
            return montoCredito;
        }

        public void setMontoCredito(String montoCredito) {
            this.montoCredito = montoCredito;
        }

        public String getNumeroCuotas() {
            return numeroCuotas;
        }

        public void setNumeroCuotas(String numeroCuotas) {
            this.numeroCuotas = numeroCuotas;
        }

        public String getFrecuenciaId() {
            return frecuenciaId;
        }

        public void setFrecuenciaId(String frecuenciaId) {
            this.frecuenciaId = frecuenciaId;
        }

        public String getClienteId() {
            return clienteId;
        }

        public void setClienteId(String clienteId) {
            this.clienteId = clienteId;
        }

        public String getProductoId() {
            return productoId;
        }

        public void setProductoId(String productoId) {
            this.productoId = productoId;
        }
        
        
        

        
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
