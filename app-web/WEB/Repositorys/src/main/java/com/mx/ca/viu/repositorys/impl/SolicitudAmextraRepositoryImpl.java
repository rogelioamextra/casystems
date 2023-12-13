/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatEstatus;
import com.mx.ca.viu.modelos.CatProductosCredito;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.repositorys.SolicitudAmextraRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbecerril
 */
@Repository("solicitudAmextraRepository")
public class SolicitudAmextraRepositoryImpl extends SimpleRepository implements SolicitudAmextraRepository {

    @Override
    @Transactional
    public List<MvSolicitudesAmextra> buscarTodos(Date inicio, Date fin, CatEstatus estatus, CatProductosCredito producto) {
        List<MvSolicitudesAmextra> resultado = new ArrayList<>();
        try {
            
            String estatusQ="";
            String productoQ="";
            String fechaQ="";
            
            if(estatus!=null){
                estatusQ=" and mv.idEstatus.idEstatus="+estatus.getIdEstatus();
            }
            if(producto!=null){
                productoQ=" and mv.idProductoCredito.idProductosCredito="+producto.getIdProductosCredito();
            }
            
            if(inicio!=null && fin==null){
                fechaQ=" and mv.fechaSolicitud BETWEEN '"+inicio +"' and '"+UtilGenerico.obtenerHoraMexico()+"'";
            }
            if(inicio!=null && fin!=null){
                fechaQ=" and mv.fechaSolicitud BETWEEN '"+inicio +"' and '"+fin+"'";
            }

           resultado=( List<MvSolicitudesAmextra>) getSession().createQuery("select mv from MvSolicitudesAmextra mv where 1=1 "+estatusQ+productoQ+fechaQ).list();
          
        

        } catch (Exception e) {
            System.out.println(e);
        }

        return resultado;

    }

}
