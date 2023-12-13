package com.amextra.io.Response;

import java.io.Serializable;
import java.util.ArrayList;

public class DataAgenda implements Serializable {

    public ArrayList<Agenda> agenda;

    public ArrayList<Agenda> getAgenda() { return agenda; }
    public void setAgenda(ArrayList<Agenda> value) { this.agenda = value; }
}
