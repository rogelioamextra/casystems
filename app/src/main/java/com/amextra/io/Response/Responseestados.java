package com.amextra.io.Response;

import java.io.Serializable;

public class Responseestados implements Serializable {

    public Response response;
    public Dataestados data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Dataestados getData() {
        return data;
    }

    public void setData(Dataestados data) {
        this.data = data;
    }
}
