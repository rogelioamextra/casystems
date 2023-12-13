package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseCatalogoGradosEscolares implements Serializable {
    public Response response;
    public DatagradosEscolares data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatagradosEscolares getData() {
        return data;
    }

    public void setData(DatagradosEscolares data) {
        this.data = data;
    }
}
