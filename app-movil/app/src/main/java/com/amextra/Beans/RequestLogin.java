package com.amextra.Beans;

import java.io.Serializable;

public class RequestLogin implements Serializable {
    private Data data;
    public Data getData() { return data; }
    public void setData(Data value) { this.data = value; }
}

