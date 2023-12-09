package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseCatalogosEstadoCivil implements Serializable {
    public Response response;
    public DataEstadosCiviles data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataEstadosCiviles getData() {
        return data;
    }

    public void setData(DataEstadosCiviles data) {
        this.data = data;
    }
}
