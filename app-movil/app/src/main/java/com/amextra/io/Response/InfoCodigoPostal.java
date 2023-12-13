package com.amextra.io.Response;

import java.io.Serializable;

public class InfoCodigoPostal implements Serializable {
    public long id;
    public String nombre;
    public ID ciudadId;
    public EstadoID estadoId;
    public ID municipioId;
    public ColoniasLista[] coloniasLista;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }

    public ID getCiudadID() { return ciudadId; }
    public void setCiudadID(ID value) { this.ciudadId = value; }

    public EstadoID getEstadoID() { return estadoId; }
    public void setEstadoID(EstadoID value) { this.estadoId = value; }

    public ID getMunicipioID() { return municipioId; }
    public void setMunicipioID(ID value) { this.municipioId = value; }

    public ColoniasLista[] getColoniasLista() { return coloniasLista; }
    public void setColoniasLista(ColoniasLista[] value) { this.coloniasLista = value; }
}
