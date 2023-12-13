/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public class ModDatosProceso {
    
    private Long idDatosSolicitud;
    private CatCategoriasCampos categoriaCampos;
    private boolean status;
    private CatCampos idCampo;
    private MvDocumentosCategorias idDocumentoCategoria; 
    private MvConfigSolicitudes idConfigSolicitud;
    
    public CatCategoriasCampos getCategoriaCampos() {
        return categoriaCampos;
    }

    public void setCategoriaCampos(CatCategoriasCampos categoriaCampos) {
        this.categoriaCampos = categoriaCampos;
    }

    public Long getIdDatosSolicitud() {
        return idDatosSolicitud;
    }

    public void setIdDatosSolicitud(Long idDatosSolicitud) {
        this.idDatosSolicitud = idDatosSolicitud;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CatCampos getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(CatCampos idCampo) {
        this.idCampo = idCampo;
    }

    public MvDocumentosCategorias getIdDocumentoCategoria() {
        return idDocumentoCategoria;
    }

    public void setIdDocumentoCategoria(MvDocumentosCategorias idDocumentoCategoria) {
        this.idDocumentoCategoria = idDocumentoCategoria;
    }

    public MvConfigSolicitudes getIdConfigSolicitud() {
        return idConfigSolicitud;
    }

    public void setIdConfigSolicitud(MvConfigSolicitudes idConfigSolicitud) {
        this.idConfigSolicitud = idConfigSolicitud;
    }

    
}
