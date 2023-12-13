/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesDocumentos;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesDocRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author linzunza
 */
@Repository("mvResultadosValidacionesDocRepository")
public class MvResultadosValidacionesDocRepositoryImpl extends SimpleRepository implements MvResultadosValidacionesDocRepository{

    private static final Logger logger = LogManager.getLogger(MvResultadosValidacionesDocRepositoryImpl.class.getName());
    

    @Override
    @Transactional
    public MvResultadosValidacionesDocumentos buscarPorIdResultadosValidados(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<MvResultadosValidacionesDocumentos> buscarPorDocumentoIdResultadosValidados(Long idDocumento, Long idResultados) {
               List<MvResultadosValidacionesDocumentos> respuesta = new ArrayList<>();
        try {

            respuesta = (List<MvResultadosValidacionesDocumentos>) getSession().createQuery("select r from MvResultadosValidacionesDocumentos r WHERE r.idDocumento.idDocumentos =:idDocumento and r.idResultadosDatosValidados.idDatosValidados=:idResultados")
                    .setParameter("idDocumento", idDocumento).setParameter("idResultados", idResultados).list();

        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
}
