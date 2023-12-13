package com.amextra.io.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class DataAvisos implements Serializable {

    public ArrayList<Aviso> avisos;

    public ArrayList<Aviso> getAvisos() { return avisos; }
    public void setAvisos(ArrayList<Aviso> value) { this.avisos = value; }
}
