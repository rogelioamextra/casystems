/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.dtos.generico.DtoCamposDocumentos;
import com.mx.ca.viu.modelos.dtos.generico.DtoCategoriaDatosProceso;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface MvDatosSolicitudService {

    public List<DtoCamposDocumentos> generaDtoCamposDocumentos(CatCategoriasCampos cat, Long idProceso, Long idEmpresa);

    public List<DtoCategoriaDatosProceso> generaDtoCategorias(Long idProducto, Long idEmpresa);

    public boolean deleteCategoriasDatosSolicitud(Long idDatosSolicitud);

    public boolean deleteCamposDocumentosDatosSolicitud(Long idDatosSolicitud, Long idCategoria);
    
    public List<MvConfigSolicitudes> consultaCategoriasActivas(Long idProducto, Long idEmpresa);
}
