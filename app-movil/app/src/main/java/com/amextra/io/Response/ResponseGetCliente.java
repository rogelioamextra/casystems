package com.amextra.io.Response;

import java.io.Serializable;

public class ResponseGetCliente implements Serializable {
    public Response response;
    public DataGetCliente data;

    //apublic DataGetCliente getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }

    public DataGetCliente getData() { return data; }
    public void setData(DataGetCliente value) { this.data = value; }
}
