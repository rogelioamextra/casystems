/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DtValidacionesServiciosEmpresa;
import com.mx.ca.viu.modelos.MvAdminNivelRiesgo;
import com.mx.ca.viu.modelos.MvConfigRiesgo;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface MvConfigRiesgoService {
  
    public List<MvConfigRiesgo> buscarConfiguraciones(Long idConfigSolicitud);
    public List<MvConfigRiesgo> buscarConfiguracionesXDocumento(Long idConfigSolicitud, Long doc);
    public boolean eliminarRegistro(Long id);
    public boolean eliminaConfiguracion(Long id);
    public List<CatServiciosValidacionesExternos> buscarValidaciones();
    public List<MvAdminNivelRiesgo> buscarConfiguracion(Long idConfigSolicitud, boolean activos);

    public List<DtValidacionesServiciosEmpresa> buscarValidacionesEmpresa(Long idEmpresas);
}
