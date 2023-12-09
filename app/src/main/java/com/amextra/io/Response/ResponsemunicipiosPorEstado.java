package com.amextra.io.Response;

import java.io.Serializable;

public class ResponsemunicipiosPorEstado implements Serializable {
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatamunicipiosPorEstado getData() {
        return data;
    }

    public void setData(DatamunicipiosPorEstado data) {
        this.data = data;
    }

    public DatamunicipiosPorEstado data;
}
