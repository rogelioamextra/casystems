package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseCurpClienteSolicitud implements Serializable {

    public Response response;
    public DataResponseCurpClienteSolicitud data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataResponseCurpClienteSolicitud getData() { return data; }
    public void setData(DataResponseCurpClienteSolicitud value) { this.data = value; }
}
