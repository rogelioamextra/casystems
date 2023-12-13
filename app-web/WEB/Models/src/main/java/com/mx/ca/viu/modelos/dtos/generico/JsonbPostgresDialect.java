/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.ca.viu.modelos.dtos.generico;

import java.sql.Types;
import org.hibernate.dialect.PostgreSQL94Dialect;

/**
 *
 * @author jbecerril
 */
public class JsonbPostgresDialect extends PostgreSQL94Dialect {
 
    public JsonbPostgresDialect() {
        this.registerColumnType(Types.JAVA_OBJECT,"jsonb");
    }
}