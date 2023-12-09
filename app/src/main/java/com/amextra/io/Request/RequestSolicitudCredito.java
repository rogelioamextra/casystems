package com.amextra.io.Request;

import java.io.Serializable;

public class RequestSolicitudCredito implements Serializable {

    public DataRequestSolicitudCredito data;

    public DataRequestSolicitudCredito getData() { return data; }
    public void setData(DataRequestSolicitudCredito value) { this.data = value; }
}
