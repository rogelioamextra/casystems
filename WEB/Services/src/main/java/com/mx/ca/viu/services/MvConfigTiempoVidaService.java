/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.MvConfigTiempoVida;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface MvConfigTiempoVidaService {
    
       public List<MvConfigTiempoVida> buscarRegistro(Long idConfigTiempoVida); 
}
