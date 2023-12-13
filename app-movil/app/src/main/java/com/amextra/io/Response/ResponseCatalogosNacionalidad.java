package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseCatalogosNacionalidad implements Serializable {
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataNacionalidades getData() {
        return data;
    }

    public void setData(DataNacionalidades data) {
        this.data = data;
    }

    public DataNacionalidades data;
}
