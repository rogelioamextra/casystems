/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.dtos.request.CatClientesRequest;
import com.mx.ca.viu.modelos.dtos.request.ClienteByCurpResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ImagenesClienteByIdResponse;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface CatClientesService {

    public ClienteResponse clientesNuevoTransform(CatClientesRequest request);

    public ClienteResponse clientesUpdateTransform(CatClientesRequest request);

    public ClientesResponse obtenerTodosClientes();

    public ClienteByIdResponse obtenerClienteId(Long id);
    
    public ClienteByCurpResponse obtenerClienteCurp(String curp);
     public ClientesResponse obtenerClienteIdAsesor(Long id);
     public ClientesResponse validarTelefono(String telefonos);
      public ImagenesClienteByIdResponse obtenerImagenesClienteId(Long id);
     
}
