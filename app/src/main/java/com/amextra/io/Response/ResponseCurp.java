package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseCurp implements Serializable {

    public Response response;
    public DataRespCurp data;

    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataRespCurp getData() { return data; }
    public void setData(DataRespCurp value) { this.data = value; }
}
