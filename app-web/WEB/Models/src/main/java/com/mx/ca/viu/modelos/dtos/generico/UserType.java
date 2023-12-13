/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author jbecerril
 */
public interface UserType {

    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException;

    public Object deepCopy(Object originalValue) throws HibernateException;

    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException, IOException;

    public Serializable disassemble(Object value) throws HibernateException;

    public Object assemble(Serializable cached, Object owner) throws HibernateException;

    public Object replace(Object original, Object target, Object owner) throws HibernateException;

    public boolean isMutable();

    public int hashCode(Object x) throws HibernateException;

    public boolean equals(Object x, Object y) throws HibernateException;

    public Class<?> returnedClass();

    public int[] sqlTypes();

}
