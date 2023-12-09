package com.amextra.io.Request;

import java.io.Serializable;

public class RequestRegistraAsistencia implements Serializable {

    public DataRegistroAsistencia data;

    public DataRegistroAsistencia getData() { return data; }
    public void setData(DataRegistroAsistencia value) { this.data = value; }
}
