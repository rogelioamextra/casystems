package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseciudadPorEstado implements Serializable {

    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataciudadPorEstado getData() {
        return data;
    }

    public void setData(DataciudadPorEstado data) {
        this.data = data;
    }

    public DataciudadPorEstado data;


}
