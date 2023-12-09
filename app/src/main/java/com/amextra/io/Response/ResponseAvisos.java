package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseAvisos implements Serializable {
    public Response response;
    public DataAvisos data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataAvisos getData() { return data; }
    public void setData(DataAvisos value) { this.data = value; }
}
