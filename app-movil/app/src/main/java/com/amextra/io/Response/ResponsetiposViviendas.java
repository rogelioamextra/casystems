package com.amextra.io.Response;

import java.io.Serializable;

public class ResponsetiposViviendas implements Serializable {
    public Response response;
    public DatatiposViviendas data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatatiposViviendas getData() {
        return data;
    }

    public void setData(DatatiposViviendas data) {
        this.data = data;
    }
}
