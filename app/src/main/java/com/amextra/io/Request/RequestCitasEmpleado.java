package com.amextra.io.Request;

import java.io.Serializable;

public class RequestCitasEmpleado implements Serializable {

    private DataCitasAgente data;

    public DataCitasAgente getData() { return data; }
    public void setData(DataCitasAgente value) { this.data = value; }
}
