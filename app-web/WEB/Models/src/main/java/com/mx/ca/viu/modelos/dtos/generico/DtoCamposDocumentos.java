/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.MvConfigAvisos;

/**
 *
 * @author jbecerril
 */
 public class DtoCamposDocumentos {

        private String nombre;
        private String tipo;
        private Integer orden;
        private CatCampos campo;
        private CatDocumentos documento;
        private MvConfigAvisos aviso;

        public DtoCamposDocumentos() {
        }

        public DtoCamposDocumentos(String nombre, CatCampos campo, int orden) {
            this.nombre = nombre;
            this.campo = campo;
            this.orden = orden;
            this.tipo="viu.menu.campos";
            
        }

        public DtoCamposDocumentos(String nombre, CatDocumentos documento, int orden) {
            this.nombre = nombre;
            this.documento = documento;
            this.orden = orden;
            this.tipo="viu.menu.documentos";
        }
        public DtoCamposDocumentos(String nombre, MvConfigAvisos aviso, int orden) {
            this.nombre = nombre;
            this.aviso = aviso;
            this.orden = orden;
            this.tipo="viu.menu.avisos";
        }
        
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public CatCampos getCampo() {
            return campo;
        }

        public void setCampo(CatCampos campo) {
            this.campo = campo;
        }

        public CatDocumentos getDocumento() {
            return documento;
        }

        public void setDocumento(CatDocumentos documento) {
            this.documento = documento;
        }

    public MvConfigAvisos getAviso() {
        return aviso;
    }

    public void setAviso(MvConfigAvisos aviso) {
        this.aviso = aviso;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

  
    }