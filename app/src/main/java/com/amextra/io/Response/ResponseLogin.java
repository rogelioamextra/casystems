package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseLogin implements Serializable {
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataLogIn getData() {
        return data;
    }

    public void setData(DataLogIn data) {
        this.data = data;
    }

    public DataLogIn data;

}
