package com.amextra.io.Response;

import java.io.Serializable;

public class ResponsecoloniaPorMunicipio implements Serializable {

    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatacoloniaPorMunicipio getData() {
        return data;
    }

    public void setData(DatacoloniaPorMunicipio data) {
        this.data = data;
    }

    public DatacoloniaPorMunicipio data;

}
