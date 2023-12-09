/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.response.DTOMvsolicitudesAmextra;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface MvSolicitudesAmextraRepository {
    
    
    public List<MvSolicitudesAmextra> obtenerSolicitudesCliente(Long id);
     public List<MvSolicitudesAmextra> obtenerSolicitudesAsesor(Long id);
     public DtComparacionFacial obtenerFacematch(String curp);
      public List<MvSolicitudesAmextra> obtenerSolicitudesClienteActivas(Long id);
    
}
