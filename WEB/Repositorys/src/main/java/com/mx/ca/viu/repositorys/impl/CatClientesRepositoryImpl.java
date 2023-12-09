/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatListaNegraAmextra;
import com.mx.ca.viu.modelos.CatPersonas;
import com.mx.ca.viu.modelos.CatTiposAvisos;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.MvSolicitudesAmextra;
import com.mx.ca.viu.modelos.dtos.response.Cliente;
import com.mx.ca.viu.repositorys.CatAvisosRepository;
import com.mx.ca.viu.repositorys.CatClientesRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "CatClientesRepository")
public class CatClientesRepositoryImpl extends SimpleRepository implements CatClientesRepository {

    private static final Logger logger = LogManager.getLogger(CatClientesRepositoryImpl.class.getName());

    @Override
    @Transactional
    public CatPersonas buscarCurp(String curp) {
        CatPersonas respuesta = new CatPersonas();
        try {

            respuesta = (CatPersonas) getSession().createQuery("select per from CatClientes cli inner join cli.idPersona per where per.curp=:curp ").setParameter("curp", curp).uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
    @Override
    @Transactional
    public CatPersonas buscarTelefono(String telefono) {
        CatPersonas respuesta = new CatPersonas();
        try {

            respuesta = (CatPersonas) getSession().createQuery("select per from CatClientes cli inner join cli.idPersona per where per.telefono=:telefono ").setParameter("telefono", telefono).uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public CatClientes buscarClienteTelefono(String telefono) {
        CatClientes respuesta = new CatClientes();
        try {

            respuesta = (CatClientes) getSession().createQuery("select cli from CatClientes cli where cli.idPersona.telefono =:telefono ").setParameter("telefono", telefono).uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<CatListaNegraAmextra> buscarListaNegraCurp(String curp) {
        List<CatListaNegraAmextra> respuesta = new ArrayList<>();
        try {

            respuesta = (List<CatListaNegraAmextra>) getSession().createQuery("select per from CatListaNegraAmextra per where per.curp=:curp ").setParameter("curp", curp).list();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<Cliente> obtenerTodosClientesStatus(boolean activo) {
        List<Cliente> respuesta = new ArrayList<>();
        try {
            if (activo) {
                respuesta = (List<Cliente>) getSession().createQuery("select new com.mx.ca.viu.modelos.dtos.response.Cliente(concat(concat(per.nombres, per.apellidoPaterno ),per.apellidoMaterno),cli.idCliente,per.curp)  from CatClientes cli inner join cli.idPersona per where cli.status=:status  ").setParameter("status", activo).list();
            } else {
                respuesta = (List<Cliente>) getSession().createQuery("select new com.mx.ca.viu.modelos.dtos.response.Cliente(concat(concat(per.nombres, per.apellidoPaterno ),per.apellidoMaterno),cli.idCliente,per.curp)   from CatClientes cli inner join cli.idPersona per  ").list();
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<Cliente> obtenerTodosClientesAsesor(Long id) {
        List<Cliente> respuesta = new ArrayList<>();
        try {

            CatUsuarios usu = (CatUsuarios) getSession().createQuery("select usu FROM CatUsuarios usu where usu.idUsuario=:id ").setParameter("id", id).uniqueResult();
            if (usu != null) {
                if (usu.getIdRol().isAdministrador()) {
                    respuesta = (List<Cliente>) getSession().createQuery("select new com.mx.ca.viu.modelos.dtos.response.Cliente(concat(concat(per.nombres,concat(' ', per.apellidoPaterno) ),concat(' ',per.apellidoMaterno)),cli.idCliente,per.curp)  from CatClientes cli inner join cli.idPersona per where cli.status=:status  ").setParameter("status", true).list();

                    HashMap<Long, Cliente> sucio = new HashMap<>();
                    for (Cliente aux : respuesta) {
                        sucio.put(aux.getId(), aux);
                    }
                    respuesta = new ArrayList<>(sucio.values());

                    return respuesta;

                }
                if (usu.getIdRol().isSupervisor()) {
                    respuesta = (List<Cliente>) getSession().createQuery("select new com.mx.ca.viu.modelos.dtos.response.Cliente(concat(concat(per.nombres, concat(' ',per.apellidoPaterno) ),concat(' ',per.apellidoMaterno)),cli.idCliente,per.curp)  from CatClientes cli inner join cli.idPersona per  where cli.idSucursal.idSucursales=:suc and  cli.status=true  ").setParameter("suc", usu.getIdConfiguracionEmpresa().getIdSucursal().getIdSucursales()).list();
                    HashMap<Long, Cliente> sucio = new HashMap<>();
                    for (Cliente aux : respuesta) {
                        sucio.put(aux.getId(), aux);
                    }
                    respuesta = new ArrayList<>(sucio.values());
                    return respuesta;

                }

            }

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public CatClientes buscarClienteCurp(String curp) {
        CatClientes respuesta = new CatClientes();
        try {

            respuesta = (CatClientes) getSession().createQuery("select cli from CatClientes cli where cli.idPersona.curp =:curp ").setParameter("curp", curp).uniqueResult();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }

    @Override
    @Transactional
    public List<MvSolicitudesAmextra> buscarSolicitudCurp(String curp) {
        List<MvSolicitudesAmextra> respuesta = new ArrayList<>();
        try {

            respuesta = (List<MvSolicitudesAmextra>) getSession().createQuery("select mv from MvSolicitudesAmextra mv inner join mv.idCliente cli where cli.idPersona.curp=:curp ").setParameter("curp", curp).list();

        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
    }
}
