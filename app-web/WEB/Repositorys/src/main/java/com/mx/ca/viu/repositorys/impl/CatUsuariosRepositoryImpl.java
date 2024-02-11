/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.repositorys.CatUsuariosRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Repository("catUsuariosRepository")
public class CatUsuariosRepositoryImpl extends SimpleRepository implements CatUsuariosRepository {

    @Override
    @Transactional
    public CatUsuarios login(String usu, String pass) {
        CatUsuarios usuario = null;
        try {
            usuario = (CatUsuarios) getSession()
                    .createQuery(" from CatUsuarios au where au.username = :user and au.password = :password ")
                    .setParameter("user", usu).setParameter("password", pass).uniqueResult();

        } catch (Exception e) {

            logger.fatal("Error en persistencia(findByUserPassword): ", e);
        }
        
        return usuario;
    }
    
    @Override
    @Transactional
    public boolean actualizarContrasena(String usuario, String pass) {
        boolean respuesta = false;
        try {
            Query query = getSession().createQuery(" update CatUsuarios au set au.password = :password, au.primerInicio = false where au.username = :user ")
                    .setParameter("password", pass).setParameter("user", usuario);
            query.executeUpdate();
            respuesta = true;
        } catch (Exception e) {
            logger.fatal("Error en persistencia(actualizarContrasena): ", e);
        }
        return respuesta;
    }  

    @Override
    @Transactional
    public List<CatUsuarios> buscarTodos(boolean activos) {
        List<CatUsuarios> respuesta = new ArrayList<>();
        
        try {
            if (activos) {
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r where r.status=true ORDER BY r.username").list();

            } else {
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r ORDER BY r.username").list();

            }
        } catch (Exception e) {
            logger.error(e);
        }

        return respuesta;
                
    }
    @Override
    @Transactional
    public List<CatUsuarios> buscarTodosEmpresa(boolean activos,Long idempresa) {
        List<CatUsuarios> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r inner join r.idConfiguracionEmpresa conf where r.status=true and conf.idEmpresa.idEmpresas=:empresa ORDER BY r.username").setParameter("empresa", idempresa).list();

            } else {
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r inner join r.idConfiguracionEmpresa conf where conf.idEmpresa.idEmpresas=:empresa ORDER BY r.username").setParameter("empresa", idempresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;     
    }
    
    @Override
    @Transactional
    public List<CatUsuarios> buscarNombre(String nombre, Long id) {
        List<CatUsuarios> respuesta = new ArrayList<>();
        try {
            if(id == null){
            respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r where r.username = :nombre ")
                    .setParameter("nombre", nombre).list();
            }else{
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r where r.username = :nombre and r.idUsuario !=: id ")
                    .setParameter("nombre", nombre).setParameter("id", id).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatUsuarios> buscarUsuario(String usuario, String pass) {
        List<CatUsuarios> respuesta = null;
        try {
            respuesta = (List<CatUsuarios>) getSession().createQuery("select cu from CatUsuarios cu where cu.username = :usuario and cu.password = :pass")
                    .setParameter("usuario", usuario).setParameter("pass", pass).list();
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;
    }
    
    @Override
    @Transactional
    public List<CatUsuarios> consultaUsuariosXEmpresa(boolean activos,Long idempresa) {
        List<CatUsuarios> respuesta = new ArrayList<>();
        try {
            if (activos) {
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r where r.status=true and r.idConfiguracionEmpresa.idEmpresa.idEmpresas=:empresa ").setParameter("empresa", idempresa).list();

            } else {
                respuesta = (List<CatUsuarios>) getSession().createQuery("select r from CatUsuarios r where r.idConfiguracionEmpresa.idEmpresa.idEmpresas =:empresa").setParameter("empresa", idempresa).list();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return respuesta;     
    }
    
    @Override
    @Transactional
    public CatUsuarios searchByEmail(String email) {
        CatUsuarios usuario = null;
        
        try {
            usuario = (CatUsuarios) getSession().createQuery("SELECT r from CatUsuarios r WHERE r.idPersona.email = :email").setParameter("email", email).uniqueResult();
        } 
        
        catch (Exception e) {
            logger.info("email: {} not found", email);
        }
        
        return usuario;
        
    }
    
}
