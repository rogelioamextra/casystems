package com.amextra.io.Request;

import java.io.Serializable;

public class RequestExisteCorreo implements Serializable {

    private DataExisteCorreo data;

    public DataExisteCorreo getData() {
        return data;
    }

    public void setData(DataExisteCorreo data) {
        this.data = data;
    }
}
