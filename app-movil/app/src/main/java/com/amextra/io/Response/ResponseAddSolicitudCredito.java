package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseAddSolicitudCredito implements Serializable {

    public Response response;
    public DataRespAddSolicitudCredito data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataRespAddSolicitudCredito getData() { return data; }
    public void setData(DataRespAddSolicitudCredito value) { this.data = value; }
}
