/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioConsultaCP;

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
import com.mx.ca.viu.modelos.dtos.request.ClienteByCurpResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ConsultaCPRequest;
import com.mx.ca.viu.modelos.dtos.request.ImagenesClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.response.ConsultaCPResponse;
import com.mx.ca.viu.modelos.dtos.response.LoginEntrarResponse;
import com.mx.ca.viu.repositorys.impl.CatAvisosRepositoryImpl;
import com.mx.ca.viu.services.CatAvisosService;
import com.mx.ca.viu.services.CatClientesService;
import com.mx.ca.viu.services.CatEstadoService;
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
@RequestMapping("clientes")

public class ControllerServicioConsultaClientes {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ControllerServicioConsultaClientes.class.getName());

    @Autowired
    GenericoService genericoService;
    @Autowired
    CatClientesService catClientesService;

    @GetMapping()
    public ResponseEntity<ClientesResponse> getAllClientes(Principal principal) {

        return ResponseEntity.ok(catClientesService.obtenerTodosClientes());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteByIdResponse> getCliente(@PathVariable(value = "id") Long id, Principal principal) {

        return ResponseEntity.ok(catClientesService.obtenerClienteId(id));
    }

    @GetMapping("asesor/{id}")
    public ResponseEntity<ClientesResponse> getClienteAsesor(@PathVariable(value = "id") Long id, Principal principal) {

        return ResponseEntity.ok(catClientesService.obtenerClienteIdAsesor(id));
    }

    @PostMapping()
    public ResponseEntity<ClienteResponse> post(@RequestBody CatClientesRequest entity, Principal principal) {
        try {
            logger.error("peticion entrante cliente:" + Transform.toJSON(entity));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ControllerServicioConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok(catClientesService.clientesNuevoTransform(entity));
    }

    @PutMapping
    public ResponseEntity<ClienteResponse> put(@RequestBody CatClientesRequest entity, Principal principal) {
//        try {
//            logger.error("peticion entrante cliente put:" + Transform.toJSON(entity));
//            
//
//        } catch (JsonProcessingException ex) {
//            Logger.getLogger(ControllerServicioConsultaClientes.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ClienteResponse nuevo = catClientesService.clientesUpdateTransform(entity);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/curp/{curp}")
    public ResponseEntity<ClienteByCurpResponse> getClienteCurp(@PathVariable(value = "curp") String curp, Principal principal) {

        return ResponseEntity.ok(catClientesService.obtenerClienteCurp(curp));
    }

    @GetMapping("/validaciontelefono/{telefono}")
    public ResponseEntity<ClientesResponse> getClienteTelefono(@PathVariable(value = "telefono") String telefono, Principal principal) {

        return ResponseEntity.ok(catClientesService.validarTelefono(telefono));
    }
    @GetMapping("/imagenescliente/{idcliente}")
    public ResponseEntity<ImagenesClienteByIdResponse> getImagenesCliente(@PathVariable(value = "idcliente") Long idcliente, Principal principal) {

        return ResponseEntity.ok(catClientesService.obtenerImagenesClienteId(idcliente));
    }

}
