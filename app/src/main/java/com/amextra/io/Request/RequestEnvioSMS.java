package com.amextra.io.Request;

import java.io.Serializable;

public class RequestEnvioSMS implements Serializable {

    public DataReqSms data;

    public DataReqSms getData() { return data; }
    public void setData(DataReqSms value) { this.data = value; }
    
}
