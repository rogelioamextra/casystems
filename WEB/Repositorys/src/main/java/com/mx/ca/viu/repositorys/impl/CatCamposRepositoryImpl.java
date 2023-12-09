/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.commons.Constantes;
import com.mx.ca.viu.modelos.CatCampos;
import com.mx.ca.viu.modelos.CatCategoriasCampos;
import com.mx.ca.viu.modelos.CatDocumentos;
import com.mx.ca.viu.modelos.CatEmpresas;
import com.mx.ca.viu.modelos.CatModulo;
import com.mx.ca.viu.modelos.CatTipoCampo;
import com.mx.ca.viu.modelos.CatTipoDato;
import com.mx.ca.viu.modelos.DatosSolicitudAvisos;
import com.mx.ca.viu.modelos.DatosSolicitudCampos;
import com.mx.ca.viu.modelos.DatosSolicitudDocumentos;
import com.mx.ca.viu.modelos.MvConfigAvisos;
import com.mx.ca.viu.repositorys.CatCamposRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MiguelJesusRamirezMa
 */
@Repository ("catCamposRepository")
public class CatCamposRepositoryImpl extends SimpleRepository implements CatCamposRepository{
    private static final Logger logger = LogManager.getLogger(CatCamposRepositoryImpl.class.getName());
    
    @Override
    @Transactional
    public List<CatCampos> buscarTodos(boolean activos) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.status = true ORDER BY r.idEmpresa.idEmpresas DESC").list();
            } else {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r ORDER BY r.idEmpresa.idEmpresas DESC").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCampos> buscarNombre(String nombre, Long id, Long seleccion) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if(id == null){
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.nombre = :nombre and r.idEmpresa.idEmpresas =: seleccion ")
                    .setParameter("nombre", nombre).setParameter("seleccion", seleccion).list();
            }else{
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.nombre = :nombre and r.idCampos !=: id and r.idEmpresa.idEmpresas =: seleccion")
                    .setParameter("nombre", nombre).setParameter("id", id).setParameter("seleccion", seleccion).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCampos> buscarTodosXEmpresa(boolean activos, Long IdEmpresa) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.status=true and r.idEmpresa.idEmpresas in (1,:empresa) ORDER BY r.idEmpresa.idEmpresas DESC").setParameter("empresa", IdEmpresa).list();
            } else {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.idEmpresa.idEmpresas in (1,:empresa) ORDER BY r.idEmpresa.idEmpresas DESC").setParameter("empresa", IdEmpresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }

    @Override
    @Transactional
    public List<CatCampos> buscarTodosXcategoria(Long IdCategoria,List<Long>ids,Long idEmpresa) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            
            if(ids.isEmpty()){
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.status=true and r.idCategoria.idCategoriaCampo=:cat and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu) ").setParameter("cat", IdCategoria).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
            }else{
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.status=true and r.idCategoria.idCategoriaCampo=:cat and r.idCampos not in (:ids) and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu)").setParameter("cat", IdCategoria).setParameterList("ids", ids).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
            }
                
           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    @Override
    @Transactional
    public List<CatDocumentos> buscarTodosDocumentosXcategoria(Long IdCategoria,List<Long>ids,Long idEmpresa) {
        List<CatDocumentos> respuesta = new ArrayList<>();
        try {
            
            if(ids.isEmpty()){
                respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r inner join r.mvDocumentosCategoriasList catDoc  where r.status=true and catDoc.idCategoriaCampo.idCategoriaCampo=:cat and (catDoc.idEmpresa.idEmpresas=:emp or catDoc.idEmpresa.idEmpresas=:viu) ").setParameter("cat", IdCategoria).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
            }else{
                respuesta = (List<CatDocumentos>) getSession().createQuery("select r from CatDocumentos r inner join r.mvDocumentosCategoriasList catDoc where r.status=true  and r.idDocumentos not in (:ids) and catDoc.idCategoriaCampo.idCategoriaCampo=:cat and (catDoc.idEmpresa.idEmpresas=:emp or catDoc.idEmpresa.idEmpresas=:viu )").setParameter("cat", IdCategoria).setParameterList("ids", ids).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
            }
                
           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    @Override
    @Transactional
    public List<DatosSolicitudCampos> buscarTodosXcategoriaXProceso(Long IdCategoria,Long idProceso) {
        List<DatosSolicitudCampos> respuesta = new ArrayList<>();
        try {
                respuesta = (List<DatosSolicitudCampos>) getSession().createQuery("select c from MvDatosSolicitud mv inner join mv.datosSolicitudCamposList c inner join mv.idConfigSolicitud conf where conf.idProducto.idProductos=:prod and c.idCampo.idCategoria.idCategoriaCampo=:cat ").setParameter("cat", IdCategoria).setParameter("prod", idProceso).list();
           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    @Override
    @Transactional
    public List<DatosSolicitudDocumentos> buscarTodosXdocumentosXProceso(Long IdCategoria,Long idProceso) {
        List<DatosSolicitudDocumentos> respuesta = new ArrayList<>();
        System.out.println("pasó a buscarTodosXdocumentosXProceso, IdCategoria: "+IdCategoria+", idProceso: "+idProceso);
        
        try {
                respuesta = (List<DatosSolicitudDocumentos>) getSession().createQuery("select c from MvDatosSolicitud mv inner join mv.datosSolicitudDocumentosList c inner join mv.idConfigSolicitud conf inner join c.idDocumento.mvDocumentosCategoriasList docCat where conf.idProducto.idProductos=:prod and c.idCategoria.idCategoriaCampo = :cat ").setParameter("cat", IdCategoria).setParameter("prod", idProceso).list();
           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarCategorias(boolean activos) {
        List<CatCategoriasCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.status = true ").list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatCategoriasCampos> buscarCategoriasMasViu(boolean activos, CatEmpresas idEmpresa) {
        List<CatCategoriasCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.status = true and r.idEmpresa.idEmpresas = 1 OR r.idEmpresa.idEmpresas =: idEmpresa and r.status = true")
                        .setParameter("idEmpresa", idEmpresa.getIdEmpresas()).list();
            } else {
                respuesta = (List<CatCategoriasCampos>) getSession().createQuery("select r from CatCategoriasCampos r where r.idEmpresa.idEmpresas = 1 OR r.idEmpresa.idEmpresas =: idEmpresa")
                        .setParameter("idEmpresa", idEmpresa.getIdEmpresas()).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatTipoCampo> buscarTipoCampos(boolean activos) {
        List<CatTipoCampo> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatTipoCampo>) getSession().createQuery("select r from CatTipoCampo r where r.status = true ").list();
            } else {
                respuesta = (List<CatTipoCampo>) getSession().createQuery("select r from CatTipoCampo r").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatTipoDato> buscarTipoDato(boolean activos) {
        List<CatTipoDato> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatTipoDato>) getSession().createQuery("select r from CatTipoDato r where r.status = true ").list();
            } else {
                respuesta = (List<CatTipoDato>) getSession().createQuery("select r from CatTipoDato r").list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatCampos> buscarTodosXEmpresaYCategoria(boolean activos, Long IdEmpresa, Long cat) {
        List<CatCampos> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.status=true and r.idEmpresa.idEmpresas in (1,:empresa) and r.idCategoria.idCategoriaCampo =:cat ORDER BY r.nombre")
                        .setParameter("empresa", IdEmpresa).setParameter("cat", cat).list();
            } else {
                respuesta = (List<CatCampos>) getSession().createQuery("select r from CatCampos r where r.idEmpresa.idEmpresas in (1,:empresa) and r.idCategoria.idCategoriaCampo =:cat ORDER BY r.nombre")
                        .setParameter("empresa", IdEmpresa).setParameter("cat", cat).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<MvConfigAvisos> buscarTodosAvisosXcategoria(Long IdCategoria,List<Long>ids,Long idEmpresa) {
        List<MvConfigAvisos> respuesta = new ArrayList<>();
        try {
            
            if(ids.isEmpty()){
                 respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.status=true and r.idCategoria.idCategoriaCampo=:cat and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu) ").setParameter("cat", IdCategoria).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
        
               // respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from CatCampos r where r.status=true and r.idCategoria.idCategoriaCampo=:cat and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu) ").setParameter("cat", IdCategoria).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
            }else{
                respuesta = (List<MvConfigAvisos>) getSession().createQuery("select r from MvConfigAvisos r where r.status=true and r.idCategoria.idCategoriaCampo=:cat and r.idConfigAvisos not in (:ids) and (r.idEmpresa.idEmpresas=:emp or r.idEmpresa.idEmpresas=:viu)").setParameter("cat", IdCategoria).setParameterList("ids", ids).setParameter("viu", Constantes.ID_EMPRESA_VIU).setParameter("emp", idEmpresa).list();
            }
                
           
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<DatosSolicitudAvisos> buscarTodosXAvisosXProceso(Long IdCategoria,Long idProceso) {
        List<DatosSolicitudAvisos> respuesta = new ArrayList<>();
        try {
               respuesta = (List<DatosSolicitudAvisos>) getSession().createQuery("select c from MvDatosSolicitud mv inner join mv.datosSolicitudAvisosList c inner join mv.idConfigSolicitud conf where conf.idProducto.idProductos=:prod and c.idAviso.idCategoria.idCategoriaCampo=:cat ").setParameter("cat", IdCategoria).setParameter("prod", idProceso).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
}
