/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.services;

import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface SolicitudAmextraService {
        public List<MvSolicitudesAmextra> buscarTodos(Date inicio,Date fin,CatEstatus estatus,CatProductosCredito producto);

}
