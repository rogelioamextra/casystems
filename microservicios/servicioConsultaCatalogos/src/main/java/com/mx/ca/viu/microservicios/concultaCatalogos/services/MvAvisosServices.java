/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.microservicios.concultaCatalogos.services;

import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.dtos.response.AvisosDTO;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface MvAvisosServices {
    
     public List<AvisosDTO> getAvisos();
    
}
