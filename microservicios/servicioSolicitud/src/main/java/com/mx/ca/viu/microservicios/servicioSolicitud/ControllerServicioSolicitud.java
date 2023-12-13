/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioSolicitud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.ca.viu.commons.Transform;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatDirecciones;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatNacionalidades;
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
import com.mx.ca.viu.modelos.dtos.request.CatClientesRequest;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ConsultaCPRequest;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionRequest;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionResponse;
import com.mx.ca.viu.modelos.dtos.request.SolicitudRequest;
import com.mx.ca.viu.modelos.dtos.response.ConsultaCPResponse;
import com.mx.ca.viu.modelos.dtos.response.LoginEntrarResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudIdClienteResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudIdResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudListaResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudTodosResponse;
import com.mx.ca.viu.services.CatAvisosService;
import com.mx.ca.viu.services.CatClientesService;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.CatSolicitudService;
import com.mx.ca.viu.services.DtConsultaCPService;
import com.mx.ca.viu.services.GenericoService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jbecerril
 */
@RestController
@RequestMapping("solicitud")

public class ControllerServicioSolicitud {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ControllerServicioSolicitud.class.getName());

    @Autowired
    GenericoService genericoService;
    @Autowired
    CatSolicitudService catSolicitudService;

    @GetMapping()
    public ResponseEntity<SolicitudTodosResponse> getAllSolicitudes(Principal principal) {

        return ResponseEntity.ok(catSolicitudService.obtenerTodosSolicitudes());
    }

    @GetMapping("{id}")
    public ResponseEntity<SolicitudIdResponse> getSolicitud(@PathVariable(value = "id") Long id, Principal principal) {

        return ResponseEntity.ok(catSolicitudService.obtenerSolicitudId(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<SolicitudIdClienteResponse> getSolicitudCliente(@PathVariable(value = "id") Long id, Principal principal) {

        return ResponseEntity.ok(catSolicitudService.obtenerSolicitudIdCliente(id));
    }

    @GetMapping("/asesor/{id}")
    public ResponseEntity<SolicitudIdClienteResponse> getSolicitudAsesor(@PathVariable(value = "id") Long id, Principal principal) {

        return ResponseEntity.ok(catSolicitudService.obtenerSolicitudIdAsesor(id));
    }

    @PostMapping()
    public ResponseEntity<SolicitudResponse> post(@RequestBody SolicitudRequest entity, Principal principal) {
        try {
            logger.info("peticion entrante cliente:" + Transform.toJSON(entity));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ControllerServicioSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok(catSolicitudService.solicitudNuevoTransform(entity));

    }

    @PostMapping("proyeccion")
    public ResponseEntity<ProyeccionResponse> postProyeccion(@RequestBody ProyeccionRequest entity, Principal principal) {

        return ResponseEntity.ok(catSolicitudService.generaProyeccion(entity, false));

    }

    @PutMapping
    public ResponseEntity<SolicitudResponse> put(@RequestBody SolicitudRequest entity, Principal principal) {
        SolicitudResponse nuevo = catSolicitudService.solicitudUpdateTransform(entity);
        return ResponseEntity.ok(nuevo);
    }

}
