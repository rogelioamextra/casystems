package com.amextra.utils;

import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.DatosLaborales;
import com.amextra.io.Request.Direccion;
import com.amextra.io.Request.Identificacion;
import com.amextra.io.Request.Persona;
import com.amextra.io.Request.Referencia;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.DtReferenciasPersonalesList;

public class ConverterReqClient {
    public RequestInsertClient converter(Cliente infoClient){

        RequestInsertClient require = new RequestInsertClient();
        require.setData(new DataReqCliente());
        DataReqCliente dataReqCliente = new DataReqCliente();

        dataReqCliente.setPersona(new Persona());
        dataReqCliente.setDireccion(new Direccion());
        dataReqCliente.setIdentificacion(new Identificacion());
        dataReqCliente.setDatosLaborales(new DatosLaborales());

        dataReqCliente.setID(String.valueOf(infoClient.idCliente));
        dataReqCliente.setStatus(infoClient.status);


        dataReqCliente.setID(String.valueOf(infoClient.idCliente));
        dataReqCliente.getPersona().setID(String.valueOf(infoClient.idPersona.idPersona));
        dataReqCliente.getPersona().setEmail(infoClient.idPersona.email);
        dataReqCliente.getPersona().setTelefono(infoClient.idPersona.telefono);
        dataReqCliente.getPersona().setCurp(infoClient.idPersona.curp);
        dataReqCliente.getPersona().setGenero(infoClient.idPersona.genero);
        dataReqCliente.getPersona().setNombres(infoClient.idPersona.nombres);
        dataReqCliente.getPersona().setApellidoPaterno(infoClient.idPersona.apellidoPaterno);
        dataReqCliente.getPersona().setApellidoMaterno(infoClient.idPersona.apellidoMaterno);
        dataReqCliente.getPersona().setRFC(infoClient.idPersona.rfc);
        dataReqCliente.getPersona().setFechaNacimiento(infoClient.idPersona.fechaNacimiento);
        dataReqCliente.getPersona().setNacionalidadID(infoClient.idPersona.idNacionalidad.idNacionalidad);
        dataReqCliente.getPersona().setLugarNacimientoID(infoClient.idPersona.lugarNacimiento.codigoEstado);
        dataReqCliente.getPersona().setGradoMaximoEstudiosID(Long.parseLong(infoClient.idPersona.idGradoMaximoEstudios.idGradoEstudios));
        dataReqCliente.getPersona().setEstadoCivilID(infoClient.idPersona.idEstadoCivil.idEstadoCivil);
        dataReqCliente.getPersona().setDependientesEconomicos(infoClient.idPersona.dependientesEconomicos);


        dataReqCliente.getDatosLaborales().setID(infoClient.idDatosLaborales.idDatosLaborales);
        dataReqCliente.getDatosLaborales().setPuesto(infoClient.idDatosLaborales.puesto);
        dataReqCliente.getDatosLaborales().setTelefono(String.valueOf(infoClient.idDatosLaborales.telefono));
        dataReqCliente.getDatosLaborales().setRecibosNomina(infoClient.idDatosLaborales.recibosNomina);
        dataReqCliente.getDatosLaborales().setIngresoMensual(String.valueOf(infoClient.idDatosLaborales.ingresoMensual));
        dataReqCliente.getDatosLaborales().setFechaIngreso(infoClient.idDatosLaborales.fechaIngreso);
        dataReqCliente.getDatosLaborales().setGiroNegocioId(Long.parseLong(infoClient.idDatosLaborales.idGiroNegocio.idGirosNegocio));
        dataReqCliente.getDatosLaborales().setCaracteristicasNegocioId(infoClient.idDatosLaborales.idCaracteristicaNegocio.idCaracteristicaNegocio);
        dataReqCliente.getDatosLaborales().setTiempoEmpleoActualId(String.valueOf(infoClient.idDatosLaborales.idTiempoEmpleoActual.idTiempoEmpleoActual));


        dataReqCliente.getDatosLaborales().setMismaDireccion(false);
        dataReqCliente.getDatosLaborales().setDireccion(new Direccion());
        dataReqCliente.getDatosLaborales().getDireccion().setID(infoClient.idDatosLaborales.idDireccion.idDireccion);
        dataReqCliente.getDatosLaborales().getDireccion().setCalle(infoClient.idDatosLaborales.idDireccion.calle);
        dataReqCliente.getDatosLaborales().getDireccion().setNumeroExterior(infoClient.idDatosLaborales.idDireccion.numeroExterior);
        dataReqCliente.getDatosLaborales().getDireccion().setNumeroInterior(infoClient.idDatosLaborales.idDireccion.numeroInterior);
        dataReqCliente.getDatosLaborales().getDireccion().setCp(infoClient.idDatosLaborales.idDireccion.cp);
        dataReqCliente.getDatosLaborales().getDireccion().setTiempoResidencia(infoClient.idDatosLaborales.idDireccion.tiempoResidencia);
        dataReqCliente.getDatosLaborales().getDireccion().setComprobante(infoClient.idDatosLaborales.idDireccion.comprobante);
        dataReqCliente.getDatosLaborales().getDireccion().setGeolocalizacionLatitud(infoClient.idDatosLaborales.idDireccion.geolocalizacionLatitud);
        dataReqCliente.getDatosLaborales().getDireccion().setGeolocalizacionLongitud(infoClient.idDatosLaborales.idDireccion.geolocalizacionLongitud);
        dataReqCliente.getDatosLaborales().getDireccion().setTipoResidenciaID(infoClient.idDatosLaborales.idDireccion.idTipoResidencia!=null?infoClient.idDatosLaborales.idDireccion.idTipoResidencia.idTipoResidencia:1 );
        dataReqCliente.getDatosLaborales().getDireccion().setTipoViviendaId(infoClient.idDatosLaborales.idDireccion.idTipoVivienda != null ? Long.parseLong(infoClient.idDatosLaborales.idDireccion.idTipoVivienda.idVivienda) : 0);
        dataReqCliente.getDatosLaborales().getDireccion().setColoniaID(infoClient.idDatosLaborales.idDireccion.idColonia.idColonia);
        dataReqCliente.getDatosLaborales().getDireccion().setEstadoID(infoClient.idDatosLaborales.idDireccion.idEstado.codigoEstado);
        dataReqCliente.getDatosLaborales().getDireccion().setMunicipioID(infoClient.idDatosLaborales.idDireccion.idMunicipio.idMunicipio);
        dataReqCliente.getDatosLaborales().getDireccion().setBanderaCambioImagen(infoClient.idDatosLaborales.idDireccion.banderaCambioImagen);



        dataReqCliente.getDireccion().setID(infoClient.idDireccion.idDireccion);
        dataReqCliente.getDireccion().setCalle(infoClient.idDireccion.calle);
        dataReqCliente.getDireccion().setComprobante(infoClient.idDireccion.comprobante);
        dataReqCliente.getDireccion().setGeolocalizacionLongitud(infoClient.idDireccion.geolocalizacionLongitud);
        dataReqCliente.getDireccion().setGeolocalizacionLatitud(infoClient.idDireccion.geolocalizacionLatitud);
        dataReqCliente.getDireccion().setTiempoResidencia(infoClient.idDireccion.tiempoResidencia);
        dataReqCliente.getDireccion().setTiempoResidenciaMes(infoClient.idDireccion.tiempoResidenciaMeses);
        dataReqCliente.getDireccion().setCp(infoClient.idDireccion.cp);
        dataReqCliente.getDireccion().setNumeroInterior(infoClient.idDireccion.numeroInterior);
        dataReqCliente.getDireccion().setNumeroExterior(infoClient.idDireccion.numeroExterior);
        dataReqCliente.getDireccion().setID(infoClient.idDireccion.idDireccion);
        dataReqCliente.getDireccion().setTipoResidenciaID(infoClient.idDireccion.idTipoResidencia.idTipoResidencia);
        dataReqCliente.getDireccion().setTipoViviendaId(Long.parseLong(infoClient.idDireccion.idTipoVivienda.idVivienda));//validar
        dataReqCliente.getDireccion().setColoniaID(infoClient.idDireccion.idColonia.idColonia);//validar
        dataReqCliente.getDireccion().setEstadoID(infoClient.idDireccion.idEstado.codigoEstado);//validar
        dataReqCliente.getDireccion().setMunicipioID(infoClient.idDireccion.idMunicipio.idMunicipio);//validar
        dataReqCliente.getDireccion().setCiudadID(infoClient.idDireccion.idMunicipio.idMunicipio);//validar

        dataReqCliente.getIdentificacion().setNoIdentificacion(infoClient.idDtIdentificacion.noIdentificacion);
        dataReqCliente.getIdentificacion().setClaveElector(infoClient.idDtIdentificacion.claveElector);
        dataReqCliente.getIdentificacion().setVigencia(infoClient.idDtIdentificacion.vigencia);
        dataReqCliente.getIdentificacion().setEmision(infoClient.idDtIdentificacion.emision);
        dataReqCliente.getIdentificacion().setTipoIdentificacionID(String.valueOf(infoClient.idDtIdentificacion.idTipoIdentificacion.idIdentificaciones));
        dataReqCliente.getIdentificacion().setImagen(String.valueOf(infoClient.idDtIdentificacion.imagen));
        dataReqCliente.getIdentificacion().setID(infoClient.idDtIdentificacion.idDtIdentificacion);

        DtReferenciasPersonalesList[] referencias = infoClient.dtReferenciasPersonalesList;
        Referencia[] referencias_end = new Referencia[2];




        for (int i = 0; i < referencias.length ; i++) {
            DtReferenciasPersonalesList item = referencias[i];

            Referencia ref = new Referencia();
            ref.setDireccion(new Direccion());
            ref.setNombre(item.nombreCompleto);
            ref.setApellidoMaterno(item.apellidoMaterno);
            ref.setApellidoPaterno(item.apellidoPaterno);
            ref.setTelefono(item.telefono);
            ref.setParentescoId(Long.parseLong(item.idParentesco.idParentesco));
            ref.getDireccion().setID(item.idDireccion.idDireccion);
            ref.getDireccion().setTiempoResidenciaMes(item.idDireccion.tiempoResidenciaMeses);
            ref.getDireccion().setTiempoResidencia(item.idDireccion.tiempoResidencia);
            ref.setID(String.valueOf(item.idReferencia));



            ref.getDireccion().setBanderaCambioImagen(false);
            ref.getDireccion().setGeolocalizacionLatitud(item.idDireccion.geolocalizacionLatitud);
            ref.getDireccion().setGeolocalizacionLongitud(item.idDireccion.geolocalizacionLongitud);
            ref.getDireccion().setTipoViviendaId(0);
            ref.getDireccion().setCiudadID(item.idDireccion.idMunicipio.idMunicipio);
            ref.getDireccion().setMunicipioID(item.idDireccion.idMunicipio.idMunicipio);
            ref.getDireccion().setEstadoID(item.idDireccion.idEstado.codigoEstado);
            ref.getDireccion().setColoniaID(item.idDireccion.idColonia.idColonia);
            ref.getDireccion().setComprobante(item.idDireccion.comprobante);
            ref.getDireccion().setCp(item.idDireccion.cp);
            ref.getDireccion().setNumeroExterior(item.idDireccion.numeroExterior);
            ref.getDireccion().setNumeroInterior(item.idDireccion.numeroInterior);
            ref.getDireccion().setCalle(item.idDireccion.calle);


            referencias_end[i] = ref;
        }
        dataReqCliente.setReferencias(referencias_end);
        require.setData(dataReqCliente);
        return require;
    }


}
