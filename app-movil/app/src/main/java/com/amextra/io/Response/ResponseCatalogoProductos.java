package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseCatalogoProductos implements Serializable {

    public Response response;
    public DataRespProductos data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataRespProductos getData() { return data; }
    public void setData(DataRespProductos value) { this.data = value; }
}
