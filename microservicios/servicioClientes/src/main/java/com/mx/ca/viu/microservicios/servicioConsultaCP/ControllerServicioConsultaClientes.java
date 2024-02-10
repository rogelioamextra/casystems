/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.microservicios.servicioConsultaCP;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mx.ca.viu.commons.Transform;
import com.mx.ca.viu.modelos.dtos.request.CatClientesRequest;
import com.mx.ca.viu.modelos.dtos.request.ClienteByCurpResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ImagenesClienteByIdResponse;
import com.mx.ca.viu.services.CatClientesService;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
