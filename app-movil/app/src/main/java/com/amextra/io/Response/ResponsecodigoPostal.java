package com.amextra.io.Response;

import java.io.Serializable;

public class ResponsecodigoPostal implements Serializable {

    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatacodigoPostal getData() {
        return data;
    }

    public void setData(DatacodigoPostal data) {
        this.data = data;
    }

    public  DatacodigoPostal data;
}
