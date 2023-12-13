/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service
public class MensajesProceso implements Serializable {

    @JsonProperty("aprobado")
    Aprobado aprobado;

    @JsonProperty("pendiente")
    Pendiente pendiente;

    @JsonProperty("rechazada")
    Rechazado rechazado;

    public MensajesProceso() {
        this.aprobado = new Aprobado();
        this.pendiente = new Pendiente();
        this.rechazado = new Rechazado();
    }

    public static class Aprobado {
        @JsonProperty("activo")
        private Boolean activo;
        @JsonProperty("mensaje")
        private String mensaje;

        @JsonProperty("validaciones")
        @OneToMany(mappedBy = "validaciones")
        private List<ValidacionesMensajeProceso> validaciones;

        public Boolean getActivo() {
            return activo;
        }

        public void setActivo(Boolean activo) {
            this.activo = activo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public List<ValidacionesMensajeProceso> getValidaciones() {
            return validaciones;
        }

        public void setValidaciones(List<ValidacionesMensajeProceso> validaciones) {
            this.validaciones = validaciones;
        }
    }

    public static class Pendiente {
        @JsonProperty("activo")
        private Boolean activo;
        @JsonProperty("mensaje")
        private String mensaje;

        @JsonProperty("validaciones")
        @OneToMany(mappedBy = "validaciones")
        private List<ValidacionesMensajeProceso> validaciones;

        public Boolean getActivo() {
            return activo;
        }

        public void setActivo(Boolean activo) {
            this.activo = activo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public List<ValidacionesMensajeProceso> getValidaciones() {
            return validaciones;
        }

        public void setValidaciones(List<ValidacionesMensajeProceso> validaciones) {
            this.validaciones = validaciones;
        }
    }

    public static class Rechazado {
        @JsonProperty("activo")
        private Boolean activo;
        @JsonProperty("mensaje")
        private String mensaje;

        @JsonProperty("validaciones")
        @OneToMany(mappedBy = "validaciones")
        private List<ValidacionesMensajeProceso> validaciones;

        public Boolean getActivo() {
            return activo;
        }

        public void setActivo(Boolean activo) {
            this.activo = activo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public List<ValidacionesMensajeProceso> getValidaciones() {
            return validaciones;
        }

        public void setValidaciones(List<ValidacionesMensajeProceso> validaciones) {
            this.validaciones = validaciones;
        }
    }

    public Aprobado getAprobado() {
        return aprobado;
    }

    public void setAprobado(Aprobado aprobado) {
        this.aprobado = aprobado;
    }

    public Pendiente getPendiente() {
        return pendiente;
    }

    public void setPendiente(Pendiente pendiente) {
        this.pendiente = pendiente;
    }

    public Rechazado getRechazado() {
        return rechazado;
    }

    public void setRechazado(Rechazado rechazado) {
        this.rechazado = rechazado;
    }

}
