package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseExisteCorreo implements Serializable {

    private Response response;

    private DataExisteCorreo data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataExisteCorreo getData() {
        return data;
    }

    public void setData(DataExisteCorreo data) {
        this.data = data;
    }
}
