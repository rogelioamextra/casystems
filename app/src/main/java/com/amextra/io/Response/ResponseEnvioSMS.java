package com.amextra.io.Response;
import java.io.Serializable;

public class ResponseEnvioSMS implements Serializable {
    public ResponseSMS response;

    public ResponseSMS getResponse() { return response; }
    public void setResponse(ResponseSMS value) { this.response = value; }
}
