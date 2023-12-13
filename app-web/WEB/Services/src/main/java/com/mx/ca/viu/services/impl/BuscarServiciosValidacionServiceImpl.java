/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.repositorys.CatAvisosRepository;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.services.BuscarServiciosValidacionService;
import com.mx.ca.viu.services.CatAvisosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "buscarServiciosValidacionService")
public class BuscarServiciosValidacionServiceImpl implements BuscarServiciosValidacionService {

    @Autowired
    CatServiciosValidacionesExternosRepository repository;

    @Override
    public List<CatServiciosValidacionesExternos> buscarServiciosXTipo(Long idTipo, CatEmpresas emp, boolean admin) {
        return repository.buscarServiciosXTipo(idTipo, emp,admin);
    }

    @Override
    public boolean guardarBancoValidaciones(CatServiciosValidacionesExternos servicio, CatEmpresas emp) {
        return repository.guardarBancoValidaciones(servicio, emp);
    }

    @Override
    public List<CatServiciosValidacionesExternos> buscarTodosServicios(boolean activos, CatEmpresas emp) {
        return repository.buscarTodosServicios(activos, emp);
    }

    @Override
    public List<DtValidacionesServiciosEmpresa> buscarTodosServiciosXEmpresa(boolean admin, CatEmpresas emp) {
        return repository.buscarTodosServiciosXEmpresa(admin, emp);
    }

    @Override
    public boolean guardarBancoValidaciones(DtValidacionesServiciosEmpresa servicio, CatEmpresas emp) {
        return repository.guardarBancoValidaciones(servicio, emp);
    }

    @Override
    public boolean guardarBancoValidaciones(DtValidacionesServiciosEmpresa servicio, CatEmpresas emp, CatDocumentos doc) {
        return repository.guardarBancoValidaciones(servicio, emp, doc);
    }

    @Override
    public List<DtValidacionesServiciosEmpresa> buscarTodosValidacionesXDoc(boolean activos, CatDocumentos doc, CatEmpresas empresa) {
        return repository.buscarTodosValidacionesXDoc(activos, doc, empresa);
    }

    @Override
    public List<DtValidacionesServiciosEmpresa> buscarTodosServiciosGeneral(boolean admin, CatEmpresas emp) {
        return repository.buscarTodosServiciosGeneral(admin, emp);
    }

    @Override
    public List<DtValidacionesServiciosEmpresa> buscarConfigDocXEmpresayDoc(boolean activos, CatDocumentos doc, CatEmpresas empresa) {
        return repository.buscarConfigDocXEmpresayDoc(activos, doc, empresa);
    }

    @Override
    public List<DtConfiguracionValidaciones> buscarTodosValidacionesDocXDocyEmp(boolean activos, CatDocumentos doc, CatEmpresas emp) {
        return repository.buscarTodosValidacionesDocXDocyEmp(activos, doc, emp);
    }

}
