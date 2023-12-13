/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.modelos.dtos.request.CatClientesRequest;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionRequest;
import com.mx.ca.viu.modelos.dtos.request.ProyeccionResponse;
import com.mx.ca.viu.modelos.dtos.request.SolicitudRequest;
import com.mx.ca.viu.modelos.dtos.response.SolicitudIdClienteResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudIdResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudListaResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudResponse;
import com.mx.ca.viu.modelos.dtos.response.SolicitudTodosResponse;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface CatSolicitudService {

    public SolicitudResponse solicitudNuevoTransform(SolicitudRequest request);

    public SolicitudResponse solicitudUpdateTransform(SolicitudRequest request);

    public SolicitudTodosResponse obtenerTodosSolicitudes();

    public SolicitudIdResponse obtenerSolicitudId(Long id);

    public ProyeccionResponse generaProyeccion(ProyeccionRequest request, boolean banderaWeb);

    public SolicitudIdClienteResponse obtenerSolicitudIdCliente(Long id);

    public SolicitudIdClienteResponse obtenerSolicitudIdAsesor(Long id);

    public DtComparacionFacial obtenerFacematch(String curp);

}
