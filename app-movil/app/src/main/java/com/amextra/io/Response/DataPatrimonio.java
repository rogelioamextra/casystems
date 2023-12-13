package com.amextra.io.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class DataPatrimonio implements Serializable {
    public ArrayList<Patrimonio> patrimonios;

    public ArrayList<Patrimonio> getPatrimonios() { return patrimonios; }
    public void setPatrimonios(ArrayList<Patrimonio> value) { this.patrimonios = value; }
}
