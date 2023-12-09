package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseParentescos implements Serializable {

    public Response response;
    public DataParentesco data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataParentesco getData() { return data; }
    public void setData(DataParentesco value) { this.data = value; }
}
