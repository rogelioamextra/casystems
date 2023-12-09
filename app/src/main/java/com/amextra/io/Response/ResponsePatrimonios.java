package com.amextra.io.Response;

import java.io.Serializable;

public class ResponsePatrimonios implements Serializable {

    public Response response;
    public DataPatrimonio data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataPatrimonio getData() { return data; }
    public void setData(DataPatrimonio value) { this.data = value; }
}
