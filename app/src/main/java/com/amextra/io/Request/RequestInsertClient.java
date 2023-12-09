package com.amextra.io.Request;

import java.io.Serializable;

public class RequestInsertClient implements Serializable {

    public DataReqCliente data;

    public DataReqCliente getData() { return data; }
    public void setData(DataReqCliente value) { this.data = value; }
}
