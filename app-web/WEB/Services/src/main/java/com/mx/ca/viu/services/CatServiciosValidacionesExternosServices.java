/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface CatServiciosValidacionesExternosServices {

   public CatServiciosValidacionesExternos buscarServicioId(Long id);
   public List<DatosSolicitudCampos> buscaSolicitudCampos(String idProducto);
}
