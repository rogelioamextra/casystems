/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatTiposDocumentos;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.repositorys.CatDocumentosRepository;
import com.mx.ca.viu.services.CatDocumentosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import com.mx.ca.viu.modelos.MvDocumentosTipos;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catDocumentosService")
public class CatDocumentosServiceImpl implements CatDocumentosService {
    @Autowired
    CatDocumentosRepository catDocumentosRepository;
    
    @Override
    public List<CatDocumentos> buscarTodos(boolean activos) {
        return catDocumentosRepository.buscarTodos(activos);
    }
    
    @Override
    public List<CatDocumentos> buscarNombre(String nombre, Long id, Long seleccion) {
        return catDocumentosRepository.buscarNombre(nombre, id, seleccion);
    }
    
    @Override
    public List<CatDocumentos> buscarTodosXidEmpres(Long id) {
        return catDocumentosRepository.buscarTodosXidEmpres(id);
    }
    
    @Override
    public List<CatDocumentos> buscarTodosXidEmpresa(Long id) {
        return catDocumentosRepository.buscarTodosXidEmpresa(id);
    }

    @Override
    public List<CatDocumentos> buscarTodosXidEmpresaYGenerales(Long id) {
        return catDocumentosRepository.buscarTodosXidEmpresaYGenerales(id);
    }
    
    @Override
    public List<CatValidaciones> buscarTodosValidacionesDisponibles() {
        return catDocumentosRepository.buscarTodosValidacionesDisponibles();
    }
    
    @Override
    public List<CatValidaciones> buscarTodosValidacionesDisponibles(List<Long> ids) {
        return catDocumentosRepository.buscarTodosValidacionesDisponibles(ids);
    }
    
    @Override
    public boolean eliminarValidacionesXDocumento(Long id) {
        return catDocumentosRepository.eliminarValidacionesXDocumento(id);
    }

    @Override
    public List<CatValidaciones> buscarValidacionDocCargado() {
        return catDocumentosRepository.buscarValidacionDocCargado();
    }
    
    @Override
    public boolean actualizaEstatus(Long id, boolean estatus) {
        return catDocumentosRepository.actualizaEstatus(id, estatus);
    }
    
    @Override
    public DtConfiguracionValidaciones validacionesPorDocumentos(Long id, Long val) {
        return catDocumentosRepository.validacionesPorDocumentos(id, val);
    }
    
    @Override
    public MvDocumentosCategorias buscarDocumentoCategoria(Long documento, Long empresa) {
        return catDocumentosRepository.buscarDocumentoCategoria(documento, empresa);
    }
    
    @Override
    public MvDocumentosTipos buscarTipoDocumento(Long documento, Long empresa) {
        return catDocumentosRepository.buscarTipoDocumento(documento, empresa);
    }
    
    @Override
    public List<CatTiposDocumentos> buscarTiposDocumentos(boolean activos, Long id) {
        return catDocumentosRepository.buscarTiposDocumentos(activos, id);
    }
}
