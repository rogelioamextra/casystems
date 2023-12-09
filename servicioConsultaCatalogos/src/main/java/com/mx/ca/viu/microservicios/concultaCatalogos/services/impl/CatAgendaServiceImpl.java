/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.microservicios.concultaCatalogos.services.impl;

import com.mx.ca.viu.microservicios.concultaCatalogos.repository.CatAgendaRepository;
import com.mx.ca.viu.microservicios.concultaCatalogos.services.CatAgendaService;
import com.mx.ca.viu.modelos.CatAgenda;
import com.mx.ca.viu.modelos.dtos.request.CatAgendaCambioEstatusRequest;
import com.mx.ca.viu.modelos.dtos.response.CatAgendaResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Service("catAgendaService")
public class CatAgendaServiceImpl implements CatAgendaService {

    @Autowired
    private CatAgendaRepository agenda;

    @Override

    public boolean cambioEstatusAssitio(CatAgendaCambioEstatusRequest request) {
        Optional<CatAgenda> age = agenda.findById(request.getData().getIdAgenda());
        CatAgenda save = null;
        if (age.isPresent()) {
            age.get().setEstatus(Integer.valueOf(request.getData().getEstatus()));
            age.get().setLatitud(request.getData().getLatitud());
              age.get().setLongitud(request.getData().getLongitud());
            save = agenda.save(age.get());
        }

        return save != null;

    }

    @Override
    public CatAgendaResponse obtenerFecha(String fecha) {
        CatAgendaResponse resultado = new CatAgendaResponse();

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha2 = format.parse(fecha);

            resultado.getData().setLista(agenda.getAgendaFecha(fecha2));
            resultado.getResponse().setCodigo(200);
            resultado.getResponse().setMensaje("OK");
        } catch (ParseException ex) {
            resultado.getResponse().setCodigo(500);
            resultado.getResponse().setMensaje(ex.getMessage());
            Logger.getLogger(CatAgendaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;

    }

    @Override
    public CatAgendaResponse getAgendaAsesor(String asesor) {
        CatAgendaResponse resultado = new CatAgendaResponse();

        resultado.getData().setLista(agenda.getAgendaAsesor(asesor));
        resultado.getResponse().setCodigo(200);
        resultado.getResponse().setMensaje("OK");
        return resultado;

    }

    @Override
    public CatAgendaResponse getAgendaAsesorFecha(String asesor, String fecha) {
        CatAgendaResponse resultado = new CatAgendaResponse();

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha2 = format.parse(fecha);
            resultado.getData().setLista(agenda.getAgendaAsesorFecha(asesor, fecha2));
           
            resultado.getResponse().setCodigo(200);
            resultado.getResponse().setMensaje("OK");
            return resultado;
        } catch (ParseException ex) {
            resultado.getResponse().setCodigo(500);
            resultado.getResponse().setMensaje(ex.getMessage());
            Logger.getLogger(CatAgendaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}
