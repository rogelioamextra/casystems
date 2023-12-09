/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.DatosSolicitudAvisos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.modelos.DatosSolicitudCategorias;
import com.mx.ca.viu.modelos.DatosSolicitudDocumentos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.modelos.MvConfigSolicitudes;
import com.mx.ca.viu.modelos.dtos.generico.DtoCamposDocumentos;
import com.mx.ca.viu.modelos.dtos.generico.DtoCategoriaDatosProceso;
import com.mx.ca.viu.repositorys.CatCamposRepository;
import com.mx.ca.viu.repositorys.CatCategoriasCamposRepository;
import com.mx.ca.viu.services.MvDatosSolicitudService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "mvDatosSolicitudService")
public class MvDatosSolicitudServiceImpl implements MvDatosSolicitudService {

    @Autowired
    CatCamposRepository camposRepository;
    
    @Autowired
    CatCategoriasCamposRepository categoriasRepository;

    @Override
    public List<DtoCamposDocumentos> generaDtoCamposDocumentos(CatCategoriasCampos cat, Long idProductos, Long idEmpresa) {
             System.out.println("entro al service");
             

        List<DtoCamposDocumentos> listaCamposDocumentos = new ArrayList<>();
   
        List<DatosSolicitudCampos> tmpCamposGuardados = camposRepository.buscarTodosXcategoriaXProceso(cat.getIdCategoriaCampo(), idProductos);
        System.out.println("pasó buscarTodosXcategoriaXProceso");
        List<Long> ids = new ArrayList<>();
        if (tmpCamposGuardados != null && !tmpCamposGuardados.isEmpty()) {
        tmpCamposGuardados.stream().forEach(aux -> {
            ids.add(aux.getIdCampo().getIdCampos());

        });
        }

        List<CatCampos> tmpCampos = camposRepository.buscarTodosXcategoria(cat.getIdCategoriaCampo(), ids, idEmpresa);
        System.out.println("pasó buscarTodosXcategoria");
        if (tmpCampos != null && !tmpCampos.isEmpty()) {
        for (CatCampos aux : tmpCampos) {
            DtoCamposDocumentos nuevo = new DtoCamposDocumentos(aux.getNombre(), aux, 0);
            nuevo.getCampo().setBanderaStatus(false);   
            listaCamposDocumentos.add(nuevo);

        }
        }
        if (tmpCamposGuardados != null && !tmpCamposGuardados.isEmpty()) {
        for (DatosSolicitudCampos aux : tmpCamposGuardados) {
            DtoCamposDocumentos nuevo = new DtoCamposDocumentos(aux.getIdCampo().getNombre(), aux.getIdCampo(), aux.getOrden());
            nuevo.getCampo().setBanderaStatus(aux.getStatus()); 
            listaCamposDocumentos.add(nuevo);

        }
        }
        List<DatosSolicitudDocumentos> tmpDocumentosGuardados = camposRepository.buscarTodosXdocumentosXProceso(cat.getIdCategoriaCampo(), idProductos);
        System.out.println("pasó tmpDocumentosGuardados");
        List<Long> ids2 = new ArrayList<>();
        if (tmpDocumentosGuardados != null && !tmpDocumentosGuardados.isEmpty()) {
        tmpDocumentosGuardados.stream().forEach(aux -> {
            ids2.add(aux.getIdDocumento().getIdDocumentos());

        });
        }

        List<CatDocumentos> tmpDoc = camposRepository.buscarTodosDocumentosXcategoria(cat.getIdCategoriaCampo(), ids2, idEmpresa);
         System.out.println("pasó buscarTodosDocumentosXcategoria");
        if (tmpDoc != null && !tmpDoc.isEmpty()) {
        for (CatDocumentos aux : tmpDoc) {
            DtoCamposDocumentos nuevo = new DtoCamposDocumentos(aux.getNombre(), aux, 0);
            nuevo.getDocumento().setBanderaStatus(false);
            listaCamposDocumentos.add(nuevo);

        }
        }
        if (tmpDocumentosGuardados != null && !tmpDocumentosGuardados.isEmpty()) {
        for (DatosSolicitudDocumentos aux : tmpDocumentosGuardados) {
            DtoCamposDocumentos nuevo = new DtoCamposDocumentos(aux.getIdDocumento().getNombre(), aux.getIdDocumento(), aux.getOrden());
            nuevo.getDocumento().setBanderaStatus(aux.getStatus());
            listaCamposDocumentos.add(nuevo);

        }
        }
        
        //BUSCAMOS LOS AVISOS GUARDADOS
        List<DatosSolicitudAvisos> tmpAvisosGuardados = camposRepository.buscarTodosXAvisosXProceso(cat.getIdCategoriaCampo(), idProductos);
        System.out.println("pasó buscarTodosXAvisosXProceso");
        List<Long> ids3 = new ArrayList<>();
        if (tmpAvisosGuardados != null && !tmpAvisosGuardados.isEmpty()) {
            tmpAvisosGuardados.stream().forEach(aux -> {
                ids3.add(aux.getIdAviso().getIdConfigAvisos());

            });
        }
       
        List<MvConfigAvisos> tmpAvisos = camposRepository.buscarTodosAvisosXcategoria(cat.getIdCategoriaCampo(), ids3, idEmpresa);
       System.out.println("pasó buscarTodosAvisosXcategoria");
        if(tmpAvisos!=null && !tmpAvisos.isEmpty()){
        for (MvConfigAvisos aux : tmpAvisos) {
            DtoCamposDocumentos nuevo = new DtoCamposDocumentos(aux.getNombre(), aux,0);
            nuevo.getAviso().setBanderaStatus(false);
            listaCamposDocumentos.add(nuevo);
        }
        }
        if(tmpAvisosGuardados!=null && !tmpAvisosGuardados.isEmpty()){
        for (DatosSolicitudAvisos aux : tmpAvisosGuardados) {
            DtoCamposDocumentos nuevo = new DtoCamposDocumentos(aux.getIdAviso().getNombre(), aux.getIdAviso(),aux.getOrden());
            nuevo.getAviso().setBanderaStatus(aux.getStatus());
            listaCamposDocumentos.add(nuevo);

        }
        }

        return listaCamposDocumentos;
    }

    @Override
    public List<DtoCategoriaDatosProceso> generaDtoCategorias(Long idProducto, Long idEmpresa) {

        List<DtoCategoriaDatosProceso> listaDto = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        List<DatosSolicitudCategorias> tmpsolcat = categoriasRepository.buscarCategoriasXproducto(idProducto);

        tmpsolcat.stream().forEach(aux -> {
            ids.add(aux.getIdCategoria().getIdCategoriaCampo());
        });

        for (DatosSolicitudCategorias aux : tmpsolcat) {
            DtoCategoriaDatosProceso nuevo = new DtoCategoriaDatosProceso(aux.getIdCategoria().getNombre(), aux.getIdCategoria());
            nuevo.getCategoria().setBanderaStatus(aux.getStatus());
            listaDto.add(nuevo);
        }
        List<CatCategoriasCampos> listCat = categoriasRepository.buscarRestantes(ids, idEmpresa);

        for (CatCategoriasCampos aux : listCat) {
            DtoCategoriaDatosProceso nuevo = new DtoCategoriaDatosProceso(aux.getNombre(), aux);
            nuevo.getCategoria().setBanderaStatus(false);
            listaDto.add(nuevo);
        }

        return listaDto;

    }

    @Override
    public boolean deleteCategoriasDatosSolicitud(Long idDatosSolicitud) {
        return categoriasRepository.deleteCategoriasDatosSolicitud(idDatosSolicitud);
    }

    @Override
    public boolean deleteCamposDocumentosDatosSolicitud(Long idDatosSolicitud, Long idCategoria) {
        return categoriasRepository.deleteCamposDocumentosDatosSolicitud(idDatosSolicitud, idCategoria);
    }

    @Override
    public List<MvConfigSolicitudes> consultaCategoriasActivas(Long idProducto, Long idEmpresa) {
        return categoriasRepository.consultaCategoriasActivas(idProducto, idEmpresa);
    }
}
