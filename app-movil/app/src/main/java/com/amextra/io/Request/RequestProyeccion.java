package com.amextra.io.Request;

import java.io.Serializable;

public class RequestProyeccion implements Serializable {
    public DataRequestProyeccion data;

    public DataRequestProyeccion getData() { return data; }
    public void setData(DataRequestProyeccion value) { this.data = value; }
}
