package com.amextra.io.Request;

import java.io.Serializable;

public class Persona implements Serializable {

    public String id;
    public String nombres;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String email;
    public String telefono;
    public String curp;
    public long genero;
    public String fechaNacimiento;
    public long dependientesEconomicos;
    public String rfc;
    public long estadoCivilId;
    public long gradoMaximoEstudiosId;
    public long nacionalidadId;
    public String lugarNacimientoId;
    private Political political;



    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public Political getPolitical() {
        return political;
    }

    public void setPolitical(Political political) {
        this.political = political;
    }

    public String getNombres() { return nombres; }
    public void setNombres(String value) { this.nombres = value; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String value) { this.apellidoPaterno = value; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String value) { this.apellidoMaterno = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String value) { this.telefono = value; }

    public String getCurp() { return curp; }
    public void setCurp(String value) { this.curp = value; }

    public long getGenero() { return genero; }
    public void setGenero(long value) { this.genero = value; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String value) { this.fechaNacimiento = value; }

    public long getDependientesEconomicos() { return dependientesEconomicos; }
    public void setDependientesEconomicos(long value) { this.dependientesEconomicos = value; }

    public String getRFC() { return rfc; }
    public void setRFC(String value) { this.rfc = value; }

    public long getEstadoCivilID() { return estadoCivilId; }
    public void setEstadoCivilID(long value) { this.estadoCivilId = value; }

    public long getGradoMaximoEstudiosID() { return gradoMaximoEstudiosId; }
    public void setGradoMaximoEstudiosID(long value) { this.gradoMaximoEstudiosId = value; }

    public long getNacionalidadID() { return nacionalidadId; }
    public void setNacionalidadID(long value) { this.nacionalidadId = value; }

    public String getLugarNacimientoID() { return lugarNacimientoId; }
    public void setLugarNacimientoID(String value) { this.lugarNacimientoId = value; }


}
