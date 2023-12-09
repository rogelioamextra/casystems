package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseTiposResidencia implements Serializable {
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatatiposResidencias getTiposResidencias() {
        return data;
    }

    public void setTiposResidencias(DatatiposResidencias tiposResidencias) {
        this.data = tiposResidencias;
    }

    public DatatiposResidencias data;
}
