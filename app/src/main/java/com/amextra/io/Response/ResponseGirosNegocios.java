package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseGirosNegocios implements Serializable {
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataGiroNegocio getData() {
        return data;
    }

    public void setData(DataGiroNegocio data) {
        this.data = data;
    }

    public DataGiroNegocio data;
}
