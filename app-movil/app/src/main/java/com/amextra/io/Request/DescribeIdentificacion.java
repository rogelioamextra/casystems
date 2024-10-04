package com.amextra.io.Request;

import java.io.Serializable;

public class DescribeIdentificacion implements Serializable {
    private long idIdentificacion;
    private String describeId;

    public long getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(long idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public String getDescribeId() {
        return describeId;
    }

    public void setDescribeId(String describeId) {
        this.describeId = describeId;
    }
}
