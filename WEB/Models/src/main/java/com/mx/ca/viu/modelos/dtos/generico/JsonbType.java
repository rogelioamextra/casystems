/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.SerializationException;
import org.postgresql.util.PGobject;
import org.springframework.util.ObjectUtils;

/**
 *
 * @author jbecerril
 */
public class JsonbType implements UserType{
 
    private final ObjectMapper mapper = new ObjectMapper();;
 
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            try{
                st.setObject(index, mapper.writeValueAsString(value), Types.OTHER);
            }catch (IOException e){
                e.printStackTrace();
            }
 
        }
    }
 
    @Override
    public Object deepCopy(Object originalValue) throws HibernateException {
        if (originalValue != null) {
            try {
                return mapper.readValue(mapper.writeValueAsString(originalValue),
                        returnedClass());
            } catch (IOException e) {
                throw new HibernateException("Failed to deep copy object", e);
            }
        }
        return null;
    }
 
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException, IOException {
        PGobject o = (PGobject) rs.getObject(names[0]);   
        if (o.getValue() != null) {
            return mapper.readValue(o.getValue(),Map.class);
        }
 
        return new HashMap<String, Object>(); 
    }
 
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object copy = deepCopy(value);
 
        if (copy instanceof Serializable) {
            return (Serializable) copy;
        }
 
        throw new SerializationException(String.format("Cannot serialize '%s', %s is not Serializable.", value, value.getClass()), null);
    }
 
    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }
 
    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
 
    @Override
    public boolean isMutable() {
        return true;
    }
 
    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x == null) {
            return 0;
        }
 
        return x.hashCode();
    }
 
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }
 
    @Override
    public Class<?> returnedClass() {
        return Map.class;
    }
 
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }
 
}