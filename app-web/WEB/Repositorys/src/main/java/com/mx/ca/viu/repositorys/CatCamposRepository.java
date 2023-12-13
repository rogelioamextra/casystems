/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatTipoCampo;
import com.mx.ca.viu.modelos.CatTipoDato;
import com.mx.ca.viu.modelos.DatosSolicitudAvisos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.modelos.DatosSolicitudDocumentos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatCamposRepository {

    public List<CatCampos> buscarTodos(boolean activos);

    public List<CatCampos> buscarNombre(String nombre, Long id, Long seleccion);

    public List<CatCampos> buscarTodosXEmpresa(boolean activos, Long id);

    public List<CatCampos> buscarTodosXEmpresaYCategoria(boolean activos, Long id, Long cat);

    public List<CatCategoriasCampos> buscarCategorias(boolean activos);

    public List<CatCategoriasCampos> buscarCategoriasMasViu(boolean activos, CatEmpresas idEmpresa);

    public List<CatTipoCampo> buscarTipoCampos(boolean activos);

    public List<CatTipoDato> buscarTipoDato(boolean activos);

    public List<DatosSolicitudCampos> buscarTodosXcategoriaXProceso(Long IdCategoria, Long idProceso);

    public List<CatCampos> buscarTodosXcategoria(Long IdCategoria, List<Long> ids, Long IdEmpresa);

    public List<CatDocumentos> buscarTodosDocumentosXcategoria(Long IdCategoria, List<Long> ids, Long idEmpresa);

    public List<DatosSolicitudDocumentos> buscarTodosXdocumentosXProceso(Long IdCategoria, Long idProceso);
    
    public List<MvConfigAvisos> buscarTodosAvisosXcategoria(Long IdCategoria,List<Long>ids,Long idEmpresa);
    
    public List<DatosSolicitudAvisos> buscarTodosXAvisosXProceso(Long IdCategoria,Long idProceso);
}
