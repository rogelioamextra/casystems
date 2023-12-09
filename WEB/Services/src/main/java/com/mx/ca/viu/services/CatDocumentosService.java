/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatTiposDocumentos;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import com.mx.ca.viu.modelos.MvDocumentosTipos;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatDocumentosService {
    public List<CatDocumentos> buscarTodos(boolean activos);
    public List<CatDocumentos> buscarNombre(String nombre, Long id, Long seleccion);
    public List<CatDocumentos> buscarTodosXidEmpres(Long id);
    public List<CatDocumentos> buscarTodosXidEmpresa(Long id);
    public List<CatDocumentos> buscarTodosXidEmpresaYGenerales(Long id);
    public List<CatValidaciones> buscarTodosValidacionesDisponibles();
    public List<CatValidaciones> buscarTodosValidacionesDisponibles(List<Long> ids);
    public List<CatValidaciones> buscarValidacionDocCargado();
    public boolean eliminarValidacionesXDocumento(Long id);
    public boolean actualizaEstatus(Long id, boolean estatus);
    public DtConfiguracionValidaciones validacionesPorDocumentos(Long id, Long val);
    public MvDocumentosCategorias buscarDocumentoCategoria(Long documento, Long empresa);
    public MvDocumentosTipos buscarTipoDocumento(Long documento, Long empresa);
    public List<CatTiposDocumentos> buscarTiposDocumentos(boolean activos, Long id);
}
