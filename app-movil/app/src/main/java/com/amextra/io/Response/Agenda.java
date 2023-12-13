package com.amextra.io.Response;

import java.io.Serializable;

public class Agenda implements Serializable {
    public long idAgenda;
    public String codEmpresa;
    public String codAgencia;
    public String codAsesor;
    public String codCliente;
    public String codGrupo;
    public String fecha;
    public long estatus;
    public String comentario;

    public long getIDAgenda() { return idAgenda; }
    public void setIDAgenda(long value) { this.idAgenda = value; }

    public String getCodEmpresa() { return codEmpresa; }
    public void setCodEmpresa(String value) { this.codEmpresa = value; }

    public String getCodAgencia() { return codAgencia; }
    public void setCodAgencia(String value) { this.codAgencia = value; }

    public String getCodAsesor() { return codAsesor; }
    public void setCodAsesor(String value) { this.codAsesor = value; }

    public String getCodCliente() { return codCliente; }
    public void setCodCliente(String value) { this.codCliente = value; }

    public String getCodGrupo() { return codGrupo; }
    public void setCodGrupo(String value) { this.codGrupo = value; }

    public String getFecha() { return fecha; }
    public void setFecha(String value) { this.fecha = value; }

    public long getEstatus() { return estatus; }
    public void setEstatus(long value) { this.estatus = value; }

    public String getComentario() { return comentario; }
    public void setComentario(String value) { this.comentario = value; }
}
