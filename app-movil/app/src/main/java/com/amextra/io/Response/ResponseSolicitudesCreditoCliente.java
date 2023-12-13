package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseSolicitudesCreditoCliente implements Serializable {

    public Response response;
    public DataSolicitudesCred data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataSolicitudesCred getData() { return data; }
    public void setData(DataSolicitudesCred value) { this.data = value; }
}
