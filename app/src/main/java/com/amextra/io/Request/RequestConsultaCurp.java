package com.amextra.io.Request;

import java.io.Serializable;

public class RequestConsultaCurp implements Serializable {
    public DataCurp data;

    public DataCurp getData() { return data; }
    public void setData(DataCurp value) { this.data = value; }
}
