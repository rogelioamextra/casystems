package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseTiemposActualesEmpleo implements Serializable {

    public Response response;
    public DatatiemposActualesEmpleo data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DatatiemposActualesEmpleo getData() {
        return data;
    }

    public void setData(DatatiemposActualesEmpleo data) {
        this.data = data;
    }
}
