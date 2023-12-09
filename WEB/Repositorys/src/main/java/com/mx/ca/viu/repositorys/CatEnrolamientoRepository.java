/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.MvDatosSolicitud;
import com.mx.ca.viu.modelos.MvDocumentosCategorias;
import java.util.List;

/**
 *
 * @author MiguelJesusRamirezMa
 */
public interface CatEnrolamientoRepository {
    public List<MvConfigSolicitudes> buscarTodos(boolean activos);
    public List<MvConfigSolicitudes> buscarTodosXEmpresa(boolean activos, Long id);
    
    public List<CatCampos> buscarCampos(boolean activos);
    public List<CatCampos> buscarCamposPorEmpresa(boolean activos, Long id);
    
    public MvConfigSolicitudes buscarConfigSol(Long id, Long idProd);
    public boolean eliminaConfig(Long id, Long idProd);
    public boolean actualizaEstatus(boolean estatus, Long idProd, Long id);
    
    public List<MvDatosSolicitud> buscarMvDatosSolicitudXCat(boolean activos, Long id, Long cat);
    public List<MvDatosSolicitud> buscarRegistrosMvDatosSolicitud(boolean activos, Long id);
    public boolean deleteConfig(Long idProd);
    public List<MvDocumentosCategorias> buscarDocCatPorEmpresaMasAdmin(boolean activos, Long id);
}
