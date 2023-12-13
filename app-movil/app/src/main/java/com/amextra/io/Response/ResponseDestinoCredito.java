package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseDestinoCredito implements Serializable {

    public Response response;
    public DataDestinoCredito data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataDestinoCredito getData() { return data; }
    public void setData(DataDestinoCredito value) { this.data = value; }
}
