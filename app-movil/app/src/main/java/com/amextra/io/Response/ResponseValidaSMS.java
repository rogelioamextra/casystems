package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseValidaSMS implements Serializable {

    public Response response;
    public DataValidaTokenSMS data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataValidaTokenSMS getData() { return data; }
    public void setData(DataValidaTokenSMS value) { this.data = value; }
}
