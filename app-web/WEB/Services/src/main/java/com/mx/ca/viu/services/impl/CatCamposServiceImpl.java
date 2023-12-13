/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatTipoCampo;
import com.mx.ca.viu.modelos.CatTipoDato;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.repositorys.CatCamposRepository;
import com.mx.ca.viu.services.CatCamposService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Service(value = "catCamposService")
public class CatCamposServiceImpl implements CatCamposService {

    @Autowired
    CatCamposRepository catCamposRepository;

    @Override
    public List<CatCampos> buscarTodos(boolean activos) {
        return catCamposRepository.buscarTodos(activos);
    }

    @Override
    public List<CatCampos> buscarNombre(String nombre, Long id, Long seleccion) {
        return catCamposRepository.buscarNombre(nombre, id, seleccion);
    }

    @Override
    public List<CatCampos> buscarTodosXEmpresa(boolean activos, Long id) {
        return catCamposRepository.buscarTodosXEmpresa(activos, id);
    }

    @Override
    public List<CatCampos> buscarTodosXEmpresaYCategoria(boolean activos, Long id, Long cat) {
        return catCamposRepository.buscarTodosXEmpresaYCategoria(activos, id, cat);
    }

    @Override
    public List<CatCategoriasCampos> buscarCategorias(boolean activos) {
        return catCamposRepository.buscarCategorias(activos);
    }

    @Override
    public List<CatCategoriasCampos> buscarCategoriasMasViu(boolean activos, CatEmpresas idEmpresa) {
        return catCamposRepository.buscarCategoriasMasViu(activos, idEmpresa);
    }

    @Override
    public List<CatTipoCampo> buscarTipoCampos(boolean activos) {
        return catCamposRepository.buscarTipoCampos(activos);
    }

    @Override
    public List<CatTipoDato> buscarTipoDato(boolean activos) {
        return catCamposRepository.buscarTipoDato(activos);
    }

    @Override
     public List<DatosSolicitudCampos> buscarTodosXcategoriaXProceso(Long IdCategoria,Long idProceso){
 
        return catCamposRepository.buscarTodosXcategoriaXProceso(IdCategoria, idProceso);
    }

    @Override
    public List<CatCampos> buscarTodosXcategoria(Long IdCategoria,List<Long>ids,Long idEmpresa) {
        return catCamposRepository.buscarTodosXcategoria(IdCategoria,ids,idEmpresa);
    }
}
