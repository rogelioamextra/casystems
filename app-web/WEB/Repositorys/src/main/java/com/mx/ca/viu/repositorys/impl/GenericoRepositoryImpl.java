/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatProperties;
import com.mx.ca.viu.repositorys.GenericoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Repository("genericoRepository")
public class GenericoRepositoryImpl extends SimpleRepository implements GenericoRepository {

    private static final Logger logger = LogManager.getLogger(GenericoRepositoryImpl.class.getName());

    @Override
    @Transactional
    public boolean guardar(Object cliente) {
        boolean respuesta = false;
        try {
            getSession().save(cliente);
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean update(Object cliente) {
        boolean respuesta = false;
        try {
            getSession().saveOrUpdate(cliente);

            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean deleteFaceMatch(String curp) {
        boolean respuesta = false;
        try {
            getSession().createSQLQuery("delete from dt_comparacion_facial where curp=:curp").setParameter("curp", curp).executeUpdate();

            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }
    @Override
    @Transactional
    public boolean deleteTiposIdentificacion(String curp) {
        boolean respuesta = false;
        try {
            getSession().createSQLQuery("delete from dt_identificaciones where id_dt_identificacion=(select id_dt_identificacion from cat_clientes cli inner join cat_personas per on per.id_persona =cli.id_persona where per.curp=:curp )").setParameter("curp", curp).executeUpdate();

            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean delete(Object cliente) {
        boolean respuesta = false;
        try {
            getSession().delete(cliente);

            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public CatProperties getPropertie(Long contante) {
        CatProperties resultado = new CatProperties();
        try {
            resultado = (CatProperties) getSession().createQuery("select p  from CatProperties p where p.constante=:con").setParameter("con", contante).uniqueResult();

        } catch (Exception e) {
            logger.error(e);

        }
        return resultado;
    }

}
