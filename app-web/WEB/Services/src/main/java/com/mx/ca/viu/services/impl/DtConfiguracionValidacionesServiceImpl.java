/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.repositorys.DtConfiguracionValidacionesRepository;
import com.mx.ca.viu.services.DtConfiguracionValidacionesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Service(value = "dtConfiguracionValidacionesService")
public class DtConfiguracionValidacionesServiceImpl implements DtConfiguracionValidacionesService{
    
    @Autowired
    DtConfiguracionValidacionesRepository dtConfiguracionValidacionesRepository;

    @Override
    public List<DtConfiguracionValidaciones> buscarTodos(boolean activos, Long idDoc) {
        return dtConfiguracionValidacionesRepository.buscarTodos(activos, idDoc);
    }
    
    @Override
    public List<CatValidaciones> buscarTodosValidaciones(boolean activos, CatDocumentos doc, CatEmpresas emp) {
        return dtConfiguracionValidacionesRepository.buscarTodosValidaciones(activos, doc, emp);
    }

    @Override
    public List<DtConfiguracionValidaciones> buscarValidacionesXEmpresaYDocumento(boolean activos, Long idEmpresa, Long idDocumento) {
        return dtConfiguracionValidacionesRepository.buscarValidacionesXEmpresaYDocumento(activos, idEmpresa, idDocumento);
    }
}
