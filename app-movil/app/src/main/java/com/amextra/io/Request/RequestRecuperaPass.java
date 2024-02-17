package com.amextra.io.Request;

import java.io.Serializable;

public class RequestRecuperaPass implements Serializable {

    private DataRecuperaPass data;

    public DataRecuperaPass getData() {
        return data;
    }

    public void setData(DataRecuperaPass data) {
        this.data = data;
    }

}
