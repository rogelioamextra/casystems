/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.repositorys.impl;

/**
 *
 * @author jbecerril
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

public class SimpleRepository {

    public static final Logger logger = LogManager.getLogger(SimpleRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Session getSession() {
        return (Session) getEntityManager().getDelegate();
    }

    public boolean deleteById(Class<?> type, Serializable id) {
        Object persistentInstance = getSession().load(type, id);
        if (persistentInstance != null) {
            getSession().delete(persistentInstance);
            return true;
        }
        return false;
    }

    void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    public static boolean isNotNull(String object) {
        return object != null && !"null".equals(object) && !object.isEmpty();
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    @Transactional
    public <T> T findByID(Class<T> type, Long id) {
        try {
            return (T) getSession().get(type.getName(), id);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;

    }

    @Transactional
    public <T> T findByID(Class<T> type, Integer id) {
        try {
            return (T) getSession().get(type.getName(), id);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;

    }

    @Transactional
    public <T> T findByID(Class<T> type, String id) {
        try {
            return (T) getSession().get(type.getName(), id);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;

    }

    @Transactional
    public <T> List<T> findAll(Class<T> type, boolean activos) {
        try {

            if (activos) {
                return (List<T>) getSession().createQuery("SELECT entity FROM " + type.getName() + " entity where entity.status=true").list();

            } else {

                return (List<T>) getSession().createQuery("SELECT entity FROM " + type.getName() + " entity  ").list();
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return null;

    }

    @Transactional
    public <T> List<T> findAll(Class<T> type) {
        try {
            return (List<T>) getSession().createQuery("SELECT entity FROM " + type.getName() + " entity  ").list();

        } catch (Exception e) {
            logger.error(e);
        }
        return null;

    }

}
