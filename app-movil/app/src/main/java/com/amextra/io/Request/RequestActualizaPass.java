package com.amextra.io.Request;

import java.io.Serializable;

public class RequestActualizaPass implements Serializable {

    private DataActualizaPass data;

    public DataActualizaPass getData() {
        return data;
    }

    public void setData(DataActualizaPass data) {
        this.data = data;
    }

}
