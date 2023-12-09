/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.MvResultadosValidacionesDocumentos;
import com.mx.ca.viu.repositorys.MvResultadosValidacionesDocRepository;
import com.mx.ca.viu.repositorys.impl.MvResultadosValidacionesDocRepositoryImpl;
import com.mx.ca.viu.services.MvResultadosValidacionesDocService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linzunza
 */
@Service(value = "mvResultadosValidacionesDocService")
public class MvResultadosValidacionesDocServiceImpl implements MvResultadosValidacionesDocService{

    @Autowired
    MvResultadosValidacionesDocRepository mvResultadosValidacionesDocRepository;
    
    
    @Override
    public MvResultadosValidacionesDocumentos buscarPorIdResultadosValidados(Long id) {
        return mvResultadosValidacionesDocRepository.buscarPorIdResultadosValidados(id);
    }

    @Override
    public List<MvResultadosValidacionesDocumentos> buscarPorDocumentoIdResultadosValidados(Long idDocumento, Long idResultados) {
        return mvResultadosValidacionesDocRepository.buscarPorDocumentoIdResultadosValidados(idDocumento,idResultados);
    }
    
}
