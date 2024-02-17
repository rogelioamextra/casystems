package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseActualizaPass implements Serializable {

    private Response response;

    private DataActualizaPass data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataActualizaPass getData() {
        return data;
    }

    public void setData(DataActualizaPass data) {
        this.data = data;
    }
}
