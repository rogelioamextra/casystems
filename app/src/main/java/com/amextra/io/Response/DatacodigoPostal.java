package com.amextra.io.Response;

import java.io.Serializable;

public class DatacodigoPostal implements Serializable {

    public InfoCodigoPostal getInfoCodigoPostal() {
        return infoCodigoPostal;
    }

    public void setInfoCodigoPostal(InfoCodigoPostal infoCodigoPostal) {
        this.infoCodigoPostal = infoCodigoPostal;
    }

    public  InfoCodigoPostal infoCodigoPostal;
}
