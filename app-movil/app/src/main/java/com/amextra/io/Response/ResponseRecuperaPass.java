package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseRecuperaPass implements Serializable {

    private Response response;

    private DataRecuperaPass data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DataRecuperaPass getData() {
        return data;
    }

    public void setData(DataRecuperaPass data) {
        this.data = data;
    }

}
