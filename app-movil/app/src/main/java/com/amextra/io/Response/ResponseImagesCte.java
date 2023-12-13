package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseImagesCte implements Serializable {
    public Response response;
    public DataImagesCte data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataImagesCte getData() { return data; }
    public void setData(DataImagesCte value) { this.data = value; }
}
