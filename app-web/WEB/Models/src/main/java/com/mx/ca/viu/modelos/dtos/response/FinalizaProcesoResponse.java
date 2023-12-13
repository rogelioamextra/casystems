/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.response;

import com.mx.ca.viu.modelos.dtos.generico.GenericResponse;
import com.mx.ca.viu.modelos.dtos.generico.GenericResponse.Response;
import java.io.Serializable;
/**
 *
 * @author mramirez
 */
public class FinalizaProcesoResponse extends GenericResponse implements Serializable{
    
    public FinalizaProcesoResponse() {
        setResponse(new Response());
    }
    
    
}
