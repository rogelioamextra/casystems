package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseValidaIne implements Serializable {

    public Response response;
    public DataResValidaIne data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataResValidaIne getData() { return data; }
    public void setData(DataResValidaIne value) { this.data = value; }
}
