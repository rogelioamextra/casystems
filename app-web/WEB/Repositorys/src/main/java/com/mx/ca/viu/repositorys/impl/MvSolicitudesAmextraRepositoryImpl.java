/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.DtComparacionFacial;
import com.mx.ca.viu.modelos.MvSolicitudProducto;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.response.DTOMvsolicitudesAmextra;
import com.mx.ca.viu.repositorys.MvSolicitudProductoRepository;
import com.mx.ca.viu.repositorys.MvSolicitudesAmextraRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbecerril
 */
@Repository("mvSolicitudesAmextraRepository")
public class MvSolicitudesAmextraRepositoryImpl extends SimpleRepository implements MvSolicitudesAmextraRepository {

    @Override
    @Transactional
    public List<MvSolicitudesAmextra> obtenerSolicitudesCliente(Long id) {
        List<MvSolicitudesAmextra> resultado=null;

        try {
            resultado=(List<MvSolicitudesAmextra>)getSession().createQuery("SELECT sol  FROM MvSolicitudesAmextra sol inner join sol.idCliente cli where cli.idCliente = :id  ").setParameter("id", id).list();
        } catch (Exception e) {
                logger.error(e);
        }
        
        return resultado;

    }
    @Override
    @Transactional
    public List<MvSolicitudesAmextra> obtenerSolicitudesClienteActivas(Long id) {
        List<MvSolicitudesAmextra> resultado=null;

        try {
            resultado=(List<MvSolicitudesAmextra>)getSession().createQuery("SELECT sol  FROM MvSolicitudesAmextra sol inner join sol.idCliente cli where cli.idCliente = :id and sol.idEstatus.idEstatus in (3)  ").setParameter("id", id).list();
        } catch (Exception e) {
                logger.error(e);
        }
        
        return resultado;

    }
    @Override
    @Transactional
    public List<MvSolicitudesAmextra> obtenerSolicitudesAsesor(Long id) {
        List<MvSolicitudesAmextra> resultado=null;

        try {
            resultado=(List<MvSolicitudesAmextra>)getSession().createQuery("SELECT sol  FROM MvSolicitudesAmextra sol inner join sol.idAsesor ase where ase.idUsuario=:id and sol.idEstatus.idEstatus=1   ").setParameter("id", id).list();
        } catch (Exception e) {
                logger.error(e);
        }
        
        return resultado;

    }
    @Override
    @Transactional
    public DtComparacionFacial obtenerFacematch(String curp) {
        DtComparacionFacial resultado=null;

        try {
            resultado=(DtComparacionFacial)getSession().createQuery("SELECT sol  FROM DtComparacionFacial sol where sol.curp=:curp ").setParameter("curp", curp).uniqueResult();
        } catch (Exception e) {
                logger.error(e);
        }
        
        return resultado;

    }

}
