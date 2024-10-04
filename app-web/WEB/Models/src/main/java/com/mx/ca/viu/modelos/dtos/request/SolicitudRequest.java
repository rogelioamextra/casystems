/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.ca.viu.modelos.dtos.generico.GenericAuth;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jbecerril
 */
public class SolicitudRequest extends GenericAuth implements Serializable {

    @JsonProperty("data")
    Data data;

    public SolicitudRequest() {
        setAuth(new Auth());
        this.data = new Data();
    }

    public static class Data implements Serializable {

        @JsonProperty("clienteId")
        private String clienteId;
        
        @JsonProperty("asesorId")
        private String asesorId;
        
        @JsonProperty("frecuenciaPagoId")
        private String frecuenciaPagoId;
        
        @JsonProperty("productoCreditoId")
        private String productoCreditoId;
        
        @JsonProperty("destinoCreditoId")
        private String destinoCreditoId;
        
        @JsonProperty("plazo")
        private String plazo;
        
        @JsonProperty("revolvente")
        private String revolvente;
        
        @JsonProperty("fechaSolicitud")
        private String fechaSolicitud;
        
        @JsonProperty("monto")
        private String monto;
        
        @JsonProperty("latitud")
        private String latitud;
        
        @JsonProperty("longitud")
        private String longitud;
        
        @JsonProperty("egresos")
        private EgresosSolicitud egresos;
        
        @JsonProperty("disseaseDescription")
        private String disseaseDescription;
        
        
        @JsonProperty("confirmaIngresos")
        private Boolean confirmaIngresos;
        
        @JsonProperty("sick")
        private boolean sick;

        @JsonProperty("patrimonios")
        private List<PatrimoniosSolicitud> patrimonios;
        
        
        
        private Set<Aval> avales = new HashSet<>();

        @JsonProperty("ingresos")
        private IngresosSolicitud ingresos;

        public Data() {
        }

        public String getAsesorId() {
            return asesorId;
        }

        public void setAsesorId(String asesorId) {
            this.asesorId = asesorId;
        }
        
        

        public String getClienteId() {
            return clienteId;
        }

        public void setClienteId(String clienteId) {
            this.clienteId = clienteId;
        }

      

        public String getFrecuenciaPagoId() {
            return frecuenciaPagoId;
        }

        public void setFrecuenciaPagoId(String frecuenciaPagoId) {
            this.frecuenciaPagoId = frecuenciaPagoId;
        }

        public String getProductoCreditoId() {
            return productoCreditoId;
        }

        public void setProductoCreditoId(String productoCreditoId) {
            this.productoCreditoId = productoCreditoId;
        }

        public String getDestinoCreditoId() {
            return destinoCreditoId;
        }

        public void setDestinoCreditoId(String destinoCreditoId) {
            this.destinoCreditoId = destinoCreditoId;
        }

        public String getPlazo() {
            return plazo;
        }

        public void setPlazo(String plazo) {
            this.plazo = plazo;
        }

        public String getRevolvente() {
            return revolvente;
        }

        public void setRevolvente(String revolvente) {
            this.revolvente = revolvente;
        }

        public String getFechaSolicitud() {
            return fechaSolicitud;
        }

        public void setFechaSolicitud(String fechaSolicitud) {
            this.fechaSolicitud = fechaSolicitud;
        }

        public String getMonto() {
            return monto;
        }

        public void setMonto(String monto) {
            this.monto = monto;
        }

        public EgresosSolicitud getEgresos() {
            return egresos;
        }

        public void setEgresos(EgresosSolicitud egresos) {
            this.egresos = egresos;
        }

        public List<PatrimoniosSolicitud> getPatrimonios() {
            return patrimonios;
        }

        public void setPatrimonios(List<PatrimoniosSolicitud> patrimonios) {
            this.patrimonios = patrimonios;
        }

        public IngresosSolicitud getIngresos() {
            return ingresos;
        }

        public void setIngresos(IngresosSolicitud ingresos) {
            this.ingresos = ingresos;
        }

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

        public String getDisseaseDescription() {
            return disseaseDescription;
        }

        public void setDisseaseDescription(String disseaseDescription) {
            this.disseaseDescription = disseaseDescription;
        }

        public Boolean getConfirmaIngresos() {
            return confirmaIngresos;
        }

        public void setConfirmaIngresos(Boolean confirmaIngresos) {
            this.confirmaIngresos = confirmaIngresos;
        }

        public boolean isSick() {
            return sick;
        }

        public void setSick(boolean sick) {
            this.sick = sick;
        }

        public Set<Aval> getAvales() {
            return avales;
        }

        public void setAvales(Set<Aval> avales) {
            this.avales = avales;
        }
      

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    

    
    
}
