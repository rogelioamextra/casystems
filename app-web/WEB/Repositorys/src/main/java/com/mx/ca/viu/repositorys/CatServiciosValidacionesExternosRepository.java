/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatServiciosValidacionesExternosRepository {
    public List<CatCampos> buscarTodos(boolean activos);
    
    public CatServiciosValidacionesExternos buscarConfigFTP();
    public List<CatServiciosValidacionesExternos> buscarServiciosXTipo(Long idTipo, CatEmpresas emp, boolean admin);
    public List<CatServiciosValidacionesExternos> buscarTodosServicios(boolean admin, CatEmpresas emp);
    public List<DtValidacionesServiciosEmpresa> buscarTodosServiciosXEmpresa(boolean admin, CatEmpresas emp);
    
    public List<DtValidacionesServiciosEmpresa> buscarTodosServiciosGeneral(boolean admin, CatEmpresas emp);
    
    public List<DtValidacionesServiciosEmpresa> buscarConfigDocXEmpresayDoc(boolean activos, CatDocumentos doc, CatEmpresas empresa);
    public List<DtValidacionesServiciosEmpresa> buscarTodosValidacionesXDoc(boolean activos, CatDocumentos doc, CatEmpresas empresa);
    public List<DtConfiguracionValidaciones> buscarTodosValidacionesDocXDocyEmp(boolean activos, CatDocumentos doc, CatEmpresas emp);
    
    public boolean guardarBancoValidaciones(CatServiciosValidacionesExternos servicio, CatEmpresas emp);
    
    public boolean guardarBancoValidaciones(DtValidacionesServiciosEmpresa servicio, CatEmpresas emp);
    
    public boolean guardarBancoValidaciones(DtValidacionesServiciosEmpresa servicio, CatEmpresas emp, CatDocumentos doc);
    
    public CatServiciosValidacionesExternos buscarServicioId(Long id);
   
    public List<DatosSolicitudCampos> buscaSolicitudCampos(String idProducto);
}
