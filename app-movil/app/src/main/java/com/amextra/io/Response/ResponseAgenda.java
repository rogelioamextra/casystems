package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseAgenda implements Serializable {

    public Response response;
    public DataAgenda data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataAgenda getData() { return data; }
    public void setData(DataAgenda value) { this.data = value; }
}
