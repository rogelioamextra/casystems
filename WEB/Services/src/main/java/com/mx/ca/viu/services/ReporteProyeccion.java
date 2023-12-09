/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatFrecuenciaPago;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.MvSolicitudProducto;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.response.Proyeccion;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface ReporteProyeccion {
    
    public byte[] generaReporte(MvSolicitudesAmextra solicitud,boolean banderaFinal);
    public byte[] generaReporte(List<Proyeccion> proyeccion, CatClientes cliente, CatProductosCredito tasa, CatFrecuenciaPago frec);
}
