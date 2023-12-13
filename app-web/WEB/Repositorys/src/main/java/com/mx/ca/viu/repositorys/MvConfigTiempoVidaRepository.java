/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.MvConfigTiempoVida;
import java.util.List;

/**
 *
 * @author JoseAlfredoMarinLope
 */
public interface MvConfigTiempoVidaRepository {
    
    public List<MvConfigTiempoVida> buscarRegistro(Long idConfigTiempoVida);
    
}
