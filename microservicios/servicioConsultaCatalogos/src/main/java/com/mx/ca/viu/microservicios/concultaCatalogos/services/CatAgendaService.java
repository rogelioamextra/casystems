/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.microservicios.concultaCatalogos.services;

import com.mx.ca.viu.modelos.CatAgenda;
import com.mx.ca.viu.modelos.dtos.request.CatAgendaCambioEstatusRequest;
import com.mx.ca.viu.modelos.dtos.response.CatAgendaResponse;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jbecerril
 */
public interface CatAgendaService {

    public boolean cambioEstatusAssitio(CatAgendaCambioEstatusRequest request);

    public CatAgendaResponse obtenerFecha(String fecha);

 public CatAgendaResponse getAgendaAsesor(String asesor);
  public CatAgendaResponse getAgendaAsesorFecha(String asesor, String fecha);
}
