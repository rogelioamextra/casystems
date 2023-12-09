/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioConsultaCP;

import com.mx.ca.viu.microservicios.concultaCatalogos.services.CatAgendaService;
import com.mx.ca.viu.modelos.CatAgenda;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatDestinoCreditos;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatIdentificaciones;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatParentescos;
import com.mx.ca.viu.modelos.CatPatrimonios;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.CatTiemposEmpleoActual;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposViviendas;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.CodigoPostal;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.InfoAvisos;
import com.mx.ca.viu.modelos.dtos.generico.InfoColonia;
import com.mx.ca.viu.modelos.dtos.response.AvisosResponse;
import com.mx.ca.viu.modelos.dtos.request.AvisosRequest;
import com.mx.ca.viu.modelos.dtos.request.CatAgendaCambioEstatusRequest;
import com.mx.ca.viu.modelos.dtos.request.CatAgendaFiltroRequest;
import com.mx.ca.viu.modelos.dtos.request.ConsultaCPRequest;
import com.mx.ca.viu.modelos.dtos.response.CatAgendaCambioEstatusResponse;
import com.mx.ca.viu.modelos.dtos.response.CatAgendaResponse;
import com.mx.ca.viu.modelos.dtos.response.CatCaracteristicasNegocioResponse;
import com.mx.ca.viu.modelos.dtos.response.CatColoniasResponse;
import com.mx.ca.viu.modelos.dtos.response.CatCuidadResponse;
import com.mx.ca.viu.modelos.dtos.response.CatDestinosCreditosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatEstadosCivilesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatEstadosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatFrecuenciasPagoResponse;
import com.mx.ca.viu.modelos.dtos.response.CatGirosNegocioResponse;
import com.mx.ca.viu.modelos.dtos.response.CatGradoEscolaresResponse;
import com.mx.ca.viu.modelos.dtos.response.CatMunicipiosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatNacionalidadesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatParentescosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatPatrimoniosResponse;
import com.mx.ca.viu.modelos.dtos.response.CatProductosCreditoResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiemposActualesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiposIdentificacionesResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiposResidenciasResponse;
import com.mx.ca.viu.modelos.dtos.response.CatTiposViviendasResponse;
import com.mx.ca.viu.modelos.dtos.response.CodigoPostal2;
import com.mx.ca.viu.modelos.dtos.response.CodigoPostalResponse;
import com.mx.ca.viu.modelos.dtos.response.CodigoPostalSepomexResponse;
import com.mx.ca.viu.modelos.dtos.response.ConsultaCPResponse;
import com.mx.ca.viu.services.CatAvisosService;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.DtConsultaCPService;
import com.mx.ca.viu.services.GenericoService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jbecerril
 */
@RestController
@RequestMapping("agenda")

public class ControllerServicioConsultaAgenda {

    @Autowired
    private GenericoService genericoService;
    @Autowired
    private CatAgendaService agendaService;

    @GetMapping
    public ResponseEntity<CatAgendaResponse> consultarAgenda(Principal principal) {
        CatAgendaResponse response = new CatAgendaResponse();
        try {
            response.getData().setLista(genericoService.findAll(CatAgenda.class));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("pendientes")
    public ResponseEntity<CatAgendaResponse> consultarPendientes(Principal principal) {
        CatAgendaResponse response = new CatAgendaResponse();
        try {
            List<CatAgenda> lista = genericoService.findAll(CatAgenda.class);
            response.getData().setLista(lista.stream().filter(a -> a.getEstatus() == 0).collect(Collectors.toList()));
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("pendientes/dia/{fecha}")
    public ResponseEntity<CatAgendaResponse> consultarPendientesFecha(@PathVariable("fecha") String fecha, Principal principal) {
        CatAgendaResponse response = new CatAgendaResponse();
        try {
            response = agendaService.obtenerFecha(fecha);

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("pendientes/asesor/{asesor}")
    public ResponseEntity<CatAgendaResponse> consultarPendientesAsesor(@PathVariable("asesor") String asesor, Principal principal) {
        CatAgendaResponse response = new CatAgendaResponse();
        try {
            response = agendaService.getAgendaAsesor(asesor);

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("pendientes/asesorfecha/")
    public ResponseEntity<CatAgendaResponse> consultarPendientesAsesor(@RequestBody CatAgendaFiltroRequest request, Principal principal) {
        CatAgendaResponse response = new CatAgendaResponse();
        try {
            response = agendaService.getAgendaAsesorFecha(request.getData().getAsesor(), request.getData().getFecha());

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registrarAsitencia/")
    public ResponseEntity<CatAgendaCambioEstatusResponse> consultarAgenda(@RequestBody CatAgendaCambioEstatusRequest request, Principal principal) {
        CatAgendaCambioEstatusResponse response = new CatAgendaCambioEstatusResponse();
        try {
            if (agendaService.cambioEstatusAssitio(request)) {
                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");
            } else {
                throw new Exception("no se actualizo el estatus");
            }

        } catch (Exception e) {
            System.out.println(e);
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

}
