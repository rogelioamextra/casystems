/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.viu.repositorys;

import com.mx.ca.viu.modelos.MvResultadosValidacionesDocumentos;
import java.util.List;

/**
 *
 * @author linzunza
 */
public interface MvResultadosValidacionesDocRepository {

    public MvResultadosValidacionesDocumentos buscarPorIdResultadosValidados(Long id);

    public List<MvResultadosValidacionesDocumentos> buscarPorDocumentoIdResultadosValidados(Long idDocumento, Long idResultados);
    
}
