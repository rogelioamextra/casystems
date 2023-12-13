/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatValidaciones;
import com.mx.ca.viu.modelos.DtConfiguracionValidaciones;
import com.mx.ca.viu.modelos.ValidacionesValores;
import com.mx.ca.viu.repositorys.DtConfiguracionValidacionesRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JoseAlfredoMarinLope
 */
@Repository("dtConfiguracionValidacionesRepository")
public class DtConfiguracionValidacionesRepositoryImpl extends SimpleRepository implements DtConfiguracionValidacionesRepository{

    private static final Logger logger = LogManager.getLogger(DtConfiguracionValidacionesRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<DtConfiguracionValidaciones> buscarTodos(boolean activos, Long idDoc) {
        
        List<DtConfiguracionValidaciones> respuesta = new ArrayList<>();
        
        try{
            if(activos){
                respuesta = (List<DtConfiguracionValidaciones>) getSession().createQuery("select r from DtConfiguracionValidaciones r WHERE r.status = true and r.idDocumentos.idDocumentos =: doc").setParameter("doc", idDoc).list();
            }else{
                respuesta = (List<DtConfiguracionValidaciones>) getSession().createQuery("select r from DtConfiguracionValidaciones r WHERE r.idDocumentos.idDocumentos =: documento").setParameter("documento", idDoc).list();
            }
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatValidaciones> buscarTodosValidaciones(boolean activos, CatDocumentos doc, CatEmpresas emp) {
        
        List<CatValidaciones> respuesta = new ArrayList<>();
        
        try{
            if(activos){
                respuesta = (List<CatValidaciones>) getSession().
                        createQuery("select r.idConfigValidacion.idValidaciones from DtValidacionesServiciosEmpresa r WHERE r.status = true and r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =: emp and r.status= true")
                        .setParameter("doc", doc.getIdDocumentos()).setParameter("emp", emp.getIdEmpresas()).list();
            }else{
                respuesta = (List<CatValidaciones>) getSession().
                        createQuery("select r.idConfigValidacion.idValidaciones from DtValidacionesServiciosEmpresa r WHERE r.idConfigValidacion.idDocumentos.idDocumentos =: doc AND r.idEmpresa.idEmpresas =: emp")
                        .setParameter("doc", doc.getIdDocumentos()).setParameter("emp", emp.getIdEmpresas()).list();
            }
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<DtConfiguracionValidaciones> buscarValidacionesXEmpresaYDocumento(boolean activos, Long idEmpresa, Long idDocumento) {

        List<DtConfiguracionValidaciones> respuesta = new ArrayList<>();
        try{
            if(activos){
                respuesta = (List<DtConfiguracionValidaciones>)getSession().
                createQuery("select r from DtConfiguracionValidaciones r WHERE r.status = true and r.idEmpresas.idEmpresas =: empresa and r.idDocumentos.idDocumentos =: documento")
                        .setParameter("empresa", idEmpresa).setParameter("documento", idDocumento).list();
            }else{
                respuesta = (List<DtConfiguracionValidaciones>)getSession().
                createQuery("select r from DtConfiguracionValidaciones r WHERE r.idEmpresas.idEmpresas =: empresa and r.idDocumentos.idDocumentos =: documento")
                        .setParameter("empresa", idEmpresa).setParameter("documento", idDocumento).list();
            //select * from dt_configuracion_validaciones dcv 
            //inner join validaciones_valores vv on dcv.id_validaciones = vv.id_validacion 

            //where id_empresas =1 and id_documentos =11 

            
            
            }
            
        }catch (Exception e){
            logger.error(e);
        }
        return respuesta;
    }
    
}
