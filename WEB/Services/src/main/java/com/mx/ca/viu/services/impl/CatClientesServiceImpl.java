package com.mx.ca.viu.services.impl;

import com.mx.ca.viu.commons.Transform;
import com.mx.ca.viu.commons.UtilGenerico;
import com.mx.ca.viu.modelos.CatCaracteristicasNegocios;
import com.mx.ca.viu.modelos.CatClientes;
import com.mx.ca.viu.modelos.CatColonias;
import com.mx.ca.viu.modelos.CatDirecciones;
import com.mx.ca.viu.modelos.CatEstados;
import com.mx.ca.viu.modelos.CatEstadosCiviles;
import com.mx.ca.viu.modelos.CatGirosNegociosEmpresas;
import com.mx.ca.viu.modelos.CatGradoMaximoEstudios;
import com.mx.ca.viu.modelos.CatIdentificaciones;
import com.mx.ca.viu.modelos.CatListaNegraAmextra;
import com.mx.ca.viu.modelos.CatMunicipios;
import com.mx.ca.viu.modelos.CatNacionalidades;
import com.mx.ca.viu.modelos.CatParentescos;
import com.mx.ca.viu.modelos.CatPersonas;
import com.mx.ca.viu.modelos.CatServiciosValidacionesExternos;
import com.mx.ca.viu.modelos.CatTiemposEmpleoActual;
import com.mx.ca.viu.modelos.CatTiposResidencias;
import com.mx.ca.viu.modelos.CatTiposViviendas;
import com.mx.ca.viu.modelos.CatUsuarios;
import com.mx.ca.viu.modelos.Ciudad;
import com.mx.ca.viu.modelos.Colonia;
import com.mx.ca.viu.modelos.DtDatosLaborales;
import com.mx.ca.viu.modelos.DtIdentificaciones;
import com.mx.ca.viu.modelos.DtReferenciasPersonales;
import com.mx.ca.viu.modelos.Estado;
import com.mx.ca.viu.modelos.Municipio;
import com.mx.ca.viu.modelos.dtos.request.CatClientesRequest;
import com.mx.ca.viu.modelos.dtos.request.ClienteByCurpResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.ClienteResponse;
import com.mx.ca.viu.modelos.dtos.request.ClientesResponse;
import com.mx.ca.viu.modelos.dtos.request.ImagenesClienteByIdResponse;
import com.mx.ca.viu.modelos.dtos.request.Referencias;
import com.mx.ca.viu.modelos.dtos.response.Cliente;
import com.mx.ca.viu.repositorys.CatClientesRepository;
import com.mx.ca.viu.repositorys.CatEstadoRepository;
import com.mx.ca.viu.repositorys.CatServiciosValidacionesExternosRepository;
import com.mx.ca.viu.repositorys.GenericoRepository;
import com.mx.ca.viu.services.CatClientesService;
import com.mx.ca.viu.services.CatEstadoService;
import com.mx.ca.viu.services.FTPService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("catClientesService")
public class CatClientesServiceImpl implements CatClientesService {

    @Autowired
    GenericoRepository genericoRepository;
    @Autowired
    CatClientesRepository catClientesRepository;
    @Autowired
    CatEstadoRepository estadosrepositiory;

    @Autowired
    CatServiciosValidacionesExternosRepository servicios;
    @Autowired
    FTPService ftp;

    @Override
    public ClienteResponse clientesNuevoTransform(CatClientesRequest request) {
        CatClientes nuevo = new CatClientes();
        ClienteResponse response = new ClienteResponse();

        try {
            boolean banderaIdentificacion = false;
            nuevo.setJson(Transform.toJSON(request));

            CatPersonas p = catClientesRepository.buscarCurp(request.getData().getPersona().getCurp());
            if (p != null && p.getIdPersona() != null) {
                throw new Exception("La curp:" + request.getData().getPersona().getCurp() + " ya se encuentra registrada");
            }
            CatClientes cli = catClientesRepository.buscarClienteTelefono(request.getData().getPersona().getTelefono());
            if (cli != null) {
                throw new Exception("El telefono:" + request.getData().getPersona().getTelefono() + " ya se encuentra registrado");
            }
            List<CatListaNegraAmextra> negra = catClientesRepository.buscarListaNegraCurp(request.getData().getPersona().getCurp());

            if (negra != null && !negra.isEmpty()) {
                throw new Exception("La curp:" + request.getData().getPersona().getCurp() + " ya se encuentra en la lista negra");
            }
            if (request.getData().getDireccion() == null) {
                throw new Exception("El campo direccion es requerido ");
            }

            CatDirecciones direccion = new CatDirecciones();
            direccion = Transform.toObject(request.getData().getDireccion(), CatDirecciones.class);
            if (request.getData().getDireccion().getColoniaId() == null) {
                throw new Exception("El campo colonia es requerido ");
            }
            direccion.setIdColonia(genericoRepository.findByID(CatColonias.class, request.getData().getDireccion().getColoniaId()));
            if (request.getData().getDireccion().getEstadoId() == null) {
                throw new Exception("El campo estado es requerido ");
            }
            String idestado = String.valueOf(request.getData().getDireccion().getEstadoId());
            if (idestado.length() == 1) {
                idestado = "0" + idestado;
            }
            direccion.setIdEstado(estadosrepositiory.buscarEstadoCodigo(idestado));
            if (request.getData().getDireccion().getMunicipioId() == null) {
                throw new Exception("El campo municipio es requerido ");
            }
            direccion.setIdMunicipio(genericoRepository.findByID(CatMunicipios.class, request.getData().getDireccion().getMunicipioId()));
            if (request.getData().getDireccion().getTipoResidenciaId() == null) {
                throw new Exception("El campo tipo de residencia es requerido ");
            }
            direccion.setIdTipoResidencia(genericoRepository.findByID(CatTiposResidencias.class, request.getData().getDireccion().getTipoResidenciaId()));
            if (request.getData().getDireccion().getTipoViviendaId() == null) {
                throw new Exception("El campo tipo de vivienda es requerido ");
            }
            direccion.setIdTipoVivienda(genericoRepository.findByID(CatTiposViviendas.class, request.getData().getDireccion().getTipoViviendaId()));
            nuevo.setIdDireccion(direccion);
            //  genericoRepository.guardar(direccion);

            CatPersonas persona = new CatPersonas();
            if (request.getData().getPersona() == null) {
                throw new Exception("El campo persona es requerido ");
            }
            persona = Transform.toObject(request.getData().getPersona(), CatPersonas.class);
            persona.setFechaNacimiento(UtilGenerico.parseStringToDate(request.getData().getPersona().getFechaNacimiento(), "yyyy-MM-dd"));

            if (request.getData().getPersona().getEstadoCivilId() == null) {
                throw new Exception("El campo estado civil es requerido ");
            }
            persona.setIdEstadoCivil(genericoRepository.findByID(CatEstadosCiviles.class, request.getData().getPersona().getEstadoCivilId()));
            if (request.getData().getPersona().getGradoMaximoEstudiosId() == null) {
                throw new Exception("El campo  grado maximo de estudios es requerido ");
            }
            persona.setIdGradoMaximoEstudios(genericoRepository.findByID(CatGradoMaximoEstudios.class, request.getData().getPersona().getGradoMaximoEstudiosId()));
            if (request.getData().getPersona().getNacionalidadId() == null) {
                throw new Exception("El campo nacionalidad de estudios es requerido ");
            }
            persona.setIdNacionalidad(genericoRepository.findByID(CatNacionalidades.class, request.getData().getPersona().getNacionalidadId()));
            if (request.getData().getPersona().getLugarNacimientoId() == null) {
                throw new Exception("El campo Lugar de nacimiento es requerido ");
            }
            CatEstados estado = estadosrepositiory.buscarEstadoCodigo(String.valueOf(request.getData().getPersona().getLugarNacimientoId()));

            persona.setLugarNacimiento(estado);
            nuevo.setIdPersona(persona);
            //   genericoRepository.guardar(persona);

            DtDatosLaborales datos = new DtDatosLaborales();
            if (request.getData().getDatosLaborales() == null) {
                throw new Exception("El campo datos laborales es requerido ");
            }
            datos = Transform.toObject(request.getData().getDatosLaborales(), DtDatosLaborales.class);
            datos.setFechaIngreso(UtilGenerico.parseStringToDate(request.getData().getDatosLaborales().getFechaIngreso(), "yyyy-MM-dd"));

            CatDirecciones direccionLaboral = new CatDirecciones();
            direccionLaboral = Transform.toObject(request.getData().getDatosLaborales().getDireccion(), CatDirecciones.class);
            if (request.getData().getDatosLaborales().getDireccion().getColoniaId() == null) {
                throw new Exception("El campo colonia-datos laborales es requerido ");
            }
            direccionLaboral.setIdColonia(genericoRepository.findByID(CatColonias.class, request.getData().getDatosLaborales().getDireccion().getColoniaId()));
            if (request.getData().getDatosLaborales().getDireccion().getEstadoId() == null) {
                throw new Exception("El campo estado-datos laborales es requerido ");
            }
            idestado = String.valueOf(request.getData().getDatosLaborales().getDireccion().getEstadoId());
            if (idestado.length() == 1) {
                idestado = "0" + idestado;
            }
            direccionLaboral.setIdEstado(estadosrepositiory.buscarEstadoCodigo(idestado));
//            direccionLaboral.setIdEstado(genericoRepository.findByID(CatEstados.class, request.getData().getDatosLaborales().getDireccion().getEstadoId()));
            if (request.getData().getDatosLaborales().getDireccion().getMunicipioId() == null) {
                throw new Exception("El campo municipio-datos laborales es requerido ");
            }
            direccionLaboral.setIdMunicipio(genericoRepository.findByID(CatMunicipios.class, request.getData().getDatosLaborales().getDireccion().getMunicipioId()));

            direccionLaboral.setIdTipoResidencia(genericoRepository.findByID(CatTiposResidencias.class, request.getData().getDatosLaborales().getDireccion().getTipoResidenciaId()));
            direccionLaboral.setIdTipoVivienda(genericoRepository.findByID(CatTiposViviendas.class, request.getData().getDatosLaborales().getDireccion().getTipoViviendaId()));
            //  genericoRepository.guardar(direccionLaboral);
            datos.setIdDireccion(direccionLaboral);
            if (request.getData().getDatosLaborales().getGiroNegocioId() == null) {
                throw new Exception("El campo giro de negocio es requerido ");
            }
            datos.setIdGiroNegocio(genericoRepository.findByID(CatGirosNegociosEmpresas.class, request.getData().getDatosLaborales().getGiroNegocioId()));
            datos.setIdCaracteristicaNegocio(genericoRepository.findByID(CatCaracteristicasNegocios.class, request.getData().getDatosLaborales().getCaracteristicasNegocioId()));
            datos.setIdTiempoEmpleoActual(genericoRepository.findByID(CatTiemposEmpleoActual.class, request.getData().getDatosLaborales().getTiempoEmpleoActualId()));
            // genericoRepository.guardar(datos);
            nuevo.setIdDatosLaborales(datos);
            /*
            referencias personales
             */

            if (!request.getData().getReferencias().isEmpty()) {

                if (request.getData().getReferencias().size() > 2) {
                    throw new Exception("Solo se permite un maximo de 2 referencias , favor de validar ");
                }

                List<DtReferenciasPersonales> lista = new ArrayList<>();
                for (Referencias aux : request.getData().getReferencias()) {
                    DtReferenciasPersonales ref = new DtReferenciasPersonales();

                    ref.setNombreCompleto(aux.getNombre());
                    ref.setApellidoPaterno(aux.getApellidoPaterno());
                    ref.setApellidoMaterno(aux.getApellidoMaterno());
                    ref.setTelefono(aux.getTelefono());
                    CatDirecciones direccionRef = new CatDirecciones();
                    direccionRef = Transform.toObject(aux.getDireccion(), CatDirecciones.class);
                    direccionRef.setIdColonia(genericoRepository.findByID(CatColonias.class, aux.getDireccion().getColoniaId()));
                    if (aux.getDireccion().getEstadoId() != null) {
                        idestado = String.valueOf(aux.getDireccion().getEstadoId());
                        if (idestado.length() == 1) {
                            idestado = "0" + idestado;
                        }
                        direccionRef.setIdEstado(estadosrepositiory.buscarEstadoCodigo(idestado));
                    }
//                    direccionRef.setIdEstado(genericoRepository.findByID(CatEstados.class, aux.getDireccion().getEstadoId()));
                    direccionRef.setIdMunicipio(genericoRepository.findByID(CatMunicipios.class, aux.getDireccion().getMunicipioId()));
                    direccionRef.setIdTipoResidencia(null);
                    direccionRef.setIdTipoVivienda(null);
                    ref.setIdDireccion(direccionRef);
                    ref.setIdParentesco(genericoRepository.findByID(CatParentescos.class, aux.getParentescoId()));
                    ref.setStatus(Boolean.TRUE);
                    ref.setIdCliente(nuevo);
                    lista.add(ref);

                }
                nuevo.setDtReferenciasPersonalesList(lista);

            }

            DtIdentificaciones identificacion = new DtIdentificaciones();
            identificacion.setIdTipoIdentificacion(genericoRepository.findByID(CatIdentificaciones.class, Long.valueOf(request.getData().getIdentificacion().getTipoIdentificacionId())));
            if (identificacion.getIdTipoIdentificacion().getIdIdentificaciones() == 1) {
                identificacion.setClaveElector(request.getData().getIdentificacion().getClaveElector());
                identificacion.setNoIdentificacion(request.getData().getIdentificacion().getNoIdentificacion());
                identificacion.setVigencia(request.getData().getIdentificacion().getVigencia());
                identificacion.setEmision(request.getData().getIdentificacion().getEmision());

            } else {
                identificacion.setClaveElector(request.getData().getIdentificacion().getClaveElector());
                identificacion.setNoIdentificacion(request.getData().getIdentificacion().getNoIdentificacion());
                identificacion.setVigencia(request.getData().getIdentificacion().getVigencia());
                identificacion.setEmision(request.getData().getIdentificacion().getEmision());
                banderaIdentificacion = true;
            }
            nuevo.setIdDtIdentificacion(identificacion);
            /*
            se guarda el id de la sucursal del asesor
             */
            CatUsuarios asesor = genericoRepository.findByID(CatUsuarios.class, Long.valueOf(request.getData().getAsesorId()));
            nuevo.setIdSucursal(asesor.getIdConfiguracionEmpresa().getIdSucursal());
            nuevo.setIdAsesor(asesor);

            nuevo.setStatus(true);
            if (genericoRepository.guardar(nuevo)) {
                if (!direccion.getComprobante().isEmpty() && direccion.isBanderaCambioImagen()) {
                    byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(direccion.getComprobante());
                    img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
//                    ftp.connect();
//                    InputStream is = new ByteArrayInputStream(img1);
//                    direccion.setComprobante("");
//                    ftp.uploadFiles(nuevo.getIdPersona().getCurp() + "/comDir", nuevo.getIdCliente().toString() + "-" + nuevo.getIdDireccion().getIdDireccion().toString(), is);
                    if (ftp.sendFileSFTP(img1, nuevo.getIdCliente().toString() + "-" + nuevo.getIdDireccion().getIdDireccion().toString(),
                            obtenpropiedades(nuevo.getIdPersona().getCurp() + "/comDir"))) {
                        nuevo.getIdDireccion().setNombreArchivo(nuevo.getIdCliente().toString() + "-" + nuevo.getIdDireccion().getIdDireccion().toString());
                        nuevo.getIdDireccion().setComprobante("");
                        genericoRepository.update(nuevo.getIdDireccion());
                    }

                }
                if (banderaIdentificacion && !request.getData().getIdentificacion().getImagen().isEmpty()) {
                    byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(request.getData().getIdentificacion().getImagen());
                    img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
//                    ftp.connect();
//                    InputStream is = new ByteArrayInputStream(img1);
//
//                    ftp.uploadFiles(nuevo.getIdPersona().getCurp() + "/iden", nuevo.getIdDtIdentificacion().getIdDtIdentificacion() + "-" + nuevo.getIdDtIdentificacion().getIdDtIdentificacion(), is);
                    if (ftp.sendFileSFTP(img1,
                            nuevo.getIdDtIdentificacion().getIdDtIdentificacion() + "-" + nuevo.getIdDtIdentificacion().getIdDtIdentificacion(),
                            obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"))) {
                        nuevo.getIdDtIdentificacion().setNombreImagen(nuevo.getIdDtIdentificacion().getIdDtIdentificacion() + "-" + nuevo.getIdDtIdentificacion().getIdDtIdentificacion());
                        genericoRepository.update(nuevo.getIdDtIdentificacion());
                    }

                }

            }
            response.getData().setClienteId(nuevo.getIdCliente());
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.fillInStackTrace().getMessage() + " " + e.getMessage());
            System.out.println(e);
            //nuevo.setMsjError(e.getMessage());
        }

        return response;

    }

    @Override
    public ClienteResponse clientesUpdateTransform(CatClientesRequest request) {
        CatClientes nuevo = new CatClientes();
        ClienteResponse response = new ClienteResponse();

        try {
            nuevo.setIdCliente(request.getData().getId());
            nuevo.setJson(Transform.toJSON(request));
            boolean banderaIdentificacion = false;

//            CatPersonas p = catClientesRepository.buscarCurp(request.getData().getPersona().getCurp());
//            if (p != null && p.getIdPersona() != null) {
//                throw new Exception("La curp:" + request.getData().getPersona().getCurp() + " ya se encuentra registrada");
//            }
//            CatClientes cli = catClientesRepository.buscarClienteTelefono(request.getData().getPersona().getTelefono());
//            if (cli != null) {
//                throw new Exception("El telefono:" + request.getData().getPersona().getTelefono() + " ya se encuentra registrado");
//            }
            List<CatListaNegraAmextra> negra = catClientesRepository.buscarListaNegraCurp(request.getData().getPersona().getCurp());

            if (negra != null && !negra.isEmpty()) {
                throw new Exception("La curp:" + request.getData().getPersona().getCurp() + " ya se encuentra en la lista negra");
            }
            if (request.getData().getDireccion() == null) {
                throw new Exception("El campo direccion es requerido ");
            }

            CatDirecciones direccion = new CatDirecciones();
            direccion = Transform.toObject(request.getData().getDireccion(), CatDirecciones.class);
            direccion.setIdDireccion(request.getData().getDireccion().getId());
            if (request.getData().getDireccion().getColoniaId() == null) {
                throw new Exception("El campo colonia es requerido ");
            }
            direccion.setIdColonia(genericoRepository.findByID(CatColonias.class, request.getData().getDireccion().getColoniaId()));
            if (request.getData().getDireccion().getEstadoId() == null) {
                throw new Exception("El campo estado es requerido ");
            }
            String idestado = String.valueOf(request.getData().getDireccion().getEstadoId());
            if (idestado.length() == 1) {
                idestado = "0" + idestado;
            }
            direccion.setIdEstado(estadosrepositiory.buscarEstadoCodigo(idestado));

            //direccion.setIdEstado(genericoRepository.findByID(CatEstados.class, request.getData().getDireccion().getEstadoId()));
            if (request.getData().getDireccion().getMunicipioId() == null) {
                throw new Exception("El campo municipio es requerido ");
            }
            direccion.setIdMunicipio(genericoRepository.findByID(CatMunicipios.class, request.getData().getDireccion().getMunicipioId()));
            if (request.getData().getDireccion().getTipoResidenciaId() == null) {
                throw new Exception("El campo tipo de residencia es requerido ");
            }
            direccion.setIdTipoResidencia(genericoRepository.findByID(CatTiposResidencias.class, request.getData().getDireccion().getTipoResidenciaId()));
            if (request.getData().getDireccion().getTipoViviendaId() == null) {
                throw new Exception("El campo tipo de vivienda es requerido ");
            }
            direccion.setIdTipoVivienda(genericoRepository.findByID(CatTiposViviendas.class, request.getData().getDireccion().getTipoViviendaId()));
            nuevo.setIdDireccion(direccion);
            //  genericoRepository.guardar(direccion);

            CatPersonas persona = new CatPersonas();
            if (request.getData().getPersona() == null) {
                throw new Exception("El campo persona es requerido ");
            }
            persona = Transform.toObject(request.getData().getPersona(), CatPersonas.class);
            persona.setIdPersona(request.getData().getPersona().getId());
            if (request.getData().getPersona().getEstadoCivilId() == null) {
                throw new Exception("El campo estado civil es requerido ");
            }
            persona.setIdEstadoCivil(genericoRepository.findByID(CatEstadosCiviles.class, request.getData().getPersona().getEstadoCivilId()));
            if (request.getData().getPersona().getGradoMaximoEstudiosId() == null) {
                throw new Exception("El campo  grado maximo de estudios es requerido ");
            }
            persona.setIdGradoMaximoEstudios(genericoRepository.findByID(CatGradoMaximoEstudios.class, request.getData().getPersona().getGradoMaximoEstudiosId()));
            if (request.getData().getPersona().getNacionalidadId() == null) {
                throw new Exception("El campo nacionalidad de estudios es requerido ");
            }
            persona.setIdNacionalidad(genericoRepository.findByID(CatNacionalidades.class, request.getData().getPersona().getNacionalidadId()));
            if (request.getData().getPersona().getLugarNacimientoId() == null) {
                throw new Exception("El campo Lugar de nacimiento es requerido ");
            }
            CatEstados estado = estadosrepositiory.buscarEstadoCodigo(String.valueOf(request.getData().getPersona().getLugarNacimientoId()));

            persona.setLugarNacimiento(estado);
            persona.setFechaNacimiento(UtilGenerico.parseStringToDate(request.getData().getPersona().getFechaNacimiento(), "yyyy-MM-dd"));

            nuevo.setIdPersona(persona);
            //   genericoRepository.guardar(persona);

            DtDatosLaborales datos = new DtDatosLaborales();
            if (request.getData().getDatosLaborales() == null) {
                throw new Exception("El campo datos laborales es requerido ");
            }
            datos = Transform.toObject(request.getData().getDatosLaborales(), DtDatosLaborales.class);
            datos.setIdDatosLaborales(request.getData().getDatosLaborales().getId());
            datos.setFechaIngreso(UtilGenerico.parseStringToDate(request.getData().getDatosLaborales().getFechaIngreso(), "yyyy-MM-dd"));

            CatDirecciones direccionLaboral = new CatDirecciones();
            direccionLaboral = Transform.toObject(request.getData().getDatosLaborales().getDireccion(), CatDirecciones.class);
            direccionLaboral.setIdDireccion(request.getData().getDatosLaborales().getDireccion().getId());
            if (request.getData().getDatosLaborales().getDireccion().getColoniaId() == null) {
                throw new Exception("El campo colonia-datos laborales es requerido ");
            }
            direccionLaboral.setIdColonia(genericoRepository.findByID(CatColonias.class, request.getData().getDatosLaborales().getDireccion().getColoniaId()));
            if (request.getData().getDatosLaborales().getDireccion().getEstadoId() == null) {
                throw new Exception("El campo estado-datos laborales es requerido ");
            }
            idestado = String.valueOf(request.getData().getDatosLaborales().getDireccion().getEstadoId());
            if (idestado.length() == 1) {
                idestado = "0" + idestado;
            }
            direccionLaboral.setIdEstado(estadosrepositiory.buscarEstadoCodigo(idestado));
            //direccionLaboral.setIdEstado(genericoRepository.findByID(CatEstados.class, request.getData().getDatosLaborales().getDireccion().getEstadoId()));
            if (request.getData().getDatosLaborales().getDireccion().getMunicipioId() == null) {
                throw new Exception("El campo municipio-datos laborales es requerido ");
            }
            direccionLaboral.setIdMunicipio(genericoRepository.findByID(CatMunicipios.class, request.getData().getDatosLaborales().getDireccion().getMunicipioId()));

            direccionLaboral.setIdTipoResidencia(genericoRepository.findByID(CatTiposResidencias.class, request.getData().getDatosLaborales().getDireccion().getTipoResidenciaId()));
            direccionLaboral.setIdTipoVivienda(genericoRepository.findByID(CatTiposViviendas.class, request.getData().getDatosLaborales().getDireccion().getTipoViviendaId()));
            //  genericoRepository.guardar(direccionLaboral);
            datos.setIdDireccion(direccionLaboral);
            if (request.getData().getDatosLaborales().getGiroNegocioId() == null) {
                throw new Exception("El campo giro de negocio es requerido ");
            }
            datos.setIdGiroNegocio(genericoRepository.findByID(CatGirosNegociosEmpresas.class, request.getData().getDatosLaborales().getGiroNegocioId()));
            datos.setIdCaracteristicaNegocio(genericoRepository.findByID(CatCaracteristicasNegocios.class, request.getData().getDatosLaborales().getCaracteristicasNegocioId()));
            datos.setIdTiempoEmpleoActual(genericoRepository.findByID(CatTiemposEmpleoActual.class, request.getData().getDatosLaborales().getTiempoEmpleoActualId()));
            // genericoRepository.guardar(datos);
            nuevo.setIdDatosLaborales(datos);
            /*
            referencias personales
             */

            if (!request.getData().getReferencias().isEmpty()) {

                if (request.getData().getReferencias().size() > 2) {
                    throw new Exception("Solo se permite un maximo de 2 referencias , favor de validar ");
                }

                List<DtReferenciasPersonales> lista = new ArrayList<>();
                for (Referencias aux : request.getData().getReferencias()) {
                    DtReferenciasPersonales ref = new DtReferenciasPersonales();
                    ref.setIdReferencia(Long.valueOf(aux.getId()));
                    ref.setNombreCompleto(aux.getNombre());
                    ref.setApellidoPaterno(aux.getApellidoPaterno());
                    ref.setApellidoMaterno(aux.getApellidoMaterno());
                    ref.setTelefono(aux.getTelefono());
                    CatDirecciones direccionRef = new CatDirecciones();
                    direccionRef = Transform.toObject(aux.getDireccion(), CatDirecciones.class);
                    direccionRef.setIdDireccion(aux.getDireccion().getId());
                    direccionRef.setIdColonia(genericoRepository.findByID(CatColonias.class, aux.getDireccion().getColoniaId()));
                    idestado = String.valueOf(aux.getDireccion().getEstadoId());
                    if (idestado.length() == 1) {
                        idestado = "0" + idestado;
                    }
                    direccionRef.setIdEstado(estadosrepositiory.buscarEstadoCodigo(idestado));
                    //direccionRef.setIdEstado(genericoRepository.findByID(CatEstados.class, aux.getDireccion().getEstadoId()));
                    direccionRef.setIdMunicipio(genericoRepository.findByID(CatMunicipios.class, aux.getDireccion().getMunicipioId()));
                    direccionRef.setIdTipoResidencia(null);
                    direccionRef.setIdTipoVivienda(null);
                    ref.setIdDireccion(direccionRef);
                    ref.setIdParentesco(genericoRepository.findByID(CatParentescos.class, aux.getParentescoId()));
                    ref.setStatus(Boolean.TRUE);
                    ref.setIdCliente(nuevo);
                    lista.add(ref);

                }
                nuevo.setDtReferenciasPersonalesList(lista);

            }

            DtIdentificaciones identificacion = new DtIdentificaciones();
            identificacion.setIdTipoIdentificacion(genericoRepository.findByID(CatIdentificaciones.class, Long.valueOf(request.getData().getIdentificacion().getTipoIdentificacionId())));
            if (identificacion.getIdTipoIdentificacion().getIdIdentificaciones() == 1) {
                identificacion.setClaveElector(request.getData().getIdentificacion().getClaveElector());
                identificacion.setNoIdentificacion(request.getData().getIdentificacion().getNoIdentificacion());
                identificacion.setVigencia(request.getData().getIdentificacion().getVigencia());
                identificacion.setEmision(request.getData().getIdentificacion().getEmision());

            } else {
                identificacion.setClaveElector(request.getData().getIdentificacion().getClaveElector());
                identificacion.setNoIdentificacion(request.getData().getIdentificacion().getNoIdentificacion());
                identificacion.setVigencia(request.getData().getIdentificacion().getVigencia());
                identificacion.setEmision(request.getData().getIdentificacion().getEmision());
                banderaIdentificacion = true;
            }
            nuevo.setIdDtIdentificacion(identificacion);
            nuevo.getIdDtIdentificacion().setIdDtIdentificacion(Long.valueOf(request.getData().getIdentificacion().getId()));

            /*
            se guarda el id de la sucursal del asesor
             */
            CatUsuarios asesor = genericoRepository.findByID(CatUsuarios.class, Long.valueOf(request.getData().getAsesorId()));
            nuevo.setIdSucursal(asesor.getIdConfiguracionEmpresa().getIdSucursal());
            nuevo.setIdAsesor(asesor);

            nuevo.setStatus(true);
            
           
            nuevo.getIdDireccion().setNombreArchivo(nuevo.getIdCliente().toString() + "-"
                                + nuevo.getIdDireccion().getIdDireccion().toString());
            
            if (genericoRepository.update(nuevo)) {
                if (!direccion.getComprobante().isEmpty() && direccion.isBanderaCambioImagen()) {
                    byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(direccion.getComprobante());
                    img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
                    ftp.deleteFileSFTP(nuevo.getIdCliente().toString() + "-" + nuevo.getIdDireccion().getIdDireccion().toString(),
                            obtenpropiedades(nuevo.getIdPersona().getCurp() + "/comDir"));
                    if (ftp.sendFileSFTP(img1, nuevo.getIdCliente().toString() + "-" + nuevo.getIdDireccion().getIdDireccion().toString(),
                            obtenpropiedades(nuevo.getIdPersona().getCurp() + "/comDir"))) {
                        nuevo.getIdDireccion().setNombreArchivo(nuevo.getIdCliente().toString() + "-"
                                + nuevo.getIdDireccion().getIdDireccion().toString());
                        genericoRepository.update(nuevo.getIdDireccion());
                    }

                }
                if (banderaIdentificacion && !request.getData().getIdentificacion().getImagen().isEmpty()) {
                    byte[] img1 = org.apache.commons.codec.binary.Base64.decodeBase64(request.getData().getIdentificacion().getImagen());
                    img1 = UtilGenerico.encriptarDesencriptarBytes(img1);
                    if (ftp.sendFileSFTP(img1,
                            nuevo.getIdDtIdentificacion().getIdDtIdentificacion() + "-" + nuevo.getIdDtIdentificacion().getIdDtIdentificacion(),
                            obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"))) {
                        ftp.deleteFileSFTP("rev", obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"));
                        ftp.deleteFileSFTP("anv", obtenpropiedades(nuevo.getIdPersona().getCurp() + "/iden"));
                        ftp.deleteFileSFTP("selfie", obtenpropiedades(nuevo.getIdPersona().getCurp() + "/sel"));
                        nuevo.getIdDtIdentificacion().setNombreImagen(nuevo.getIdDtIdentificacion().getIdDtIdentificacion() + "-" + nuevo.getIdDtIdentificacion().getIdDtIdentificacion());
                        genericoRepository.update(nuevo.getIdDtIdentificacion());
                        genericoRepository.deleteFaceMatch(nuevo.getIdPersona().getCurp());
                    }

                }

            }
            response.getData().setClienteId(nuevo.getIdCliente());
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {

            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.fillInStackTrace().getMessage() + " " + e.getMessage());
            System.out.println(e);
            //nuevo.setMsjError(e.getMessage());
        }

        return response;

    }

    @Override
    public ClientesResponse obtenerTodosClientes() {
        ClientesResponse response = new ClientesResponse();
        try {
            List<Cliente> lista = catClientesRepository.obtenerTodosClientesStatus(true);

            response.getData().setListaClientes(lista);

            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.toString());
        }

        return response;
    }

    @Override
    public ClienteByIdResponse obtenerClienteId(Long id) {
        ClienteByIdResponse response = new ClienteByIdResponse();

        try {
            CatClientes cli = genericoRepository.findByID(CatClientes.class, id);
            cli.setNombreAsesor(cli.getIdAsesor().getIdPersona().getNombres() + " " + cli.getIdAsesor().getIdPersona().getApellidoPaterno() + " " + cli.getIdAsesor().getIdPersona().getApellidoMaterno());
            if (cli != null) {
                response.getData().setCliente(cli);
                response.getData().getCliente().getIdDireccion().setComprobante("");
//                response.getData().getCliente().getIdDireccion().setComprobante(
//                        obtenerBase64(response.getData().getCliente().getIdDireccion().getNombreArchivo(), response.getData().getCliente().getIdPersona().getCurp() + "/comDir"));
                if (response.getData().getCliente().getIdDtIdentificacion().getIdTipoIdentificacion().getIdIdentificaciones() == 1L) {
//                    response.getData().getCliente().getIdDtIdentificacion().setAnverso(obtenerBase64("anv",response.getData().getCliente().getIdPersona().getCurp() + "/iden"));
//                    response.getData().getCliente().getIdDtIdentificacion().setReverso(obtenerBase64("rev",response.getData().getCliente().getIdPersona().getCurp() + "/iden"));
                    response.getData().getCliente().getIdDtIdentificacion().setAnverso("");
                    response.getData().getCliente().getIdDtIdentificacion().setReverso("");
                } else {
//                    response.getData().getCliente().getIdDtIdentificacion().setImagen(obtenerBase64(response.getData().getCliente().getIdDtIdentificacion().getNombreImagen(), response.getData().getCliente().getIdPersona().getCurp() + "/iden"));
                    response.getData().getCliente().getIdDtIdentificacion().setImagen("");
                }
                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");
            } else {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("El cliente no existe, favor de verificar");
            }

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.toString());
        }
        return response;
    }

    @Override
    public ImagenesClienteByIdResponse obtenerImagenesClienteId(Long id) {
        ImagenesClienteByIdResponse response = new ImagenesClienteByIdResponse();

        try {
            CatClientes cli = genericoRepository.findByID(CatClientes.class, id);
            if (cli != null) {

                response.getData().setComprobanteDomicilio(obtenerBase64(cli.getIdDireccion().getNombreArchivo(), cli.getIdPersona().getCurp() + "/comDir"));
                if (cli.getIdDtIdentificacion().getIdTipoIdentificacion().getIdIdentificaciones() == 1L) { // sie s ine
                    response.getData().setImgAnverso(obtenerBase64("anv", cli.getIdPersona().getCurp() + "/iden"));
                    response.getData().setImgReverso(obtenerBase64("rev", cli.getIdPersona().getCurp() + "/iden"));
                    response.getData().setSelfie(obtenerBase64(cli.getIdDireccion().getNombreArchivo(), cli.getIdPersona().getCurp() + "/comDir"));
                    response.getData().setOtraIdentificacion("");

                } else {
                    response.getData().setImgAnverso("");
                    response.getData().setImgReverso("");
                    response.getData().setSelfie("");
                    response.getData().setOtraIdentificacion(obtenerBase64(cli.getIdDtIdentificacion().getNombreImagen(), cli.getIdPersona().getCurp() + "/iden"));
                }

                response.getResponse().setCodigo(200);
                response.getResponse().setMensaje("OK");
            } else {
                response.getResponse().setCodigo(500);
                response.getResponse().setMensaje("El cliente no existe, favor de verificar");
            }

        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.toString());
        }
        return response;
    }

    @Override
    public ClientesResponse obtenerClienteIdAsesor(Long id) {
        ClientesResponse response = new ClientesResponse();

        try {
            response.getData().setListaClientes(catClientesRepository.obtenerTodosClientesAsesor(id));

            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.toString());
        }
        return response;
    }

    @Override
    public ClienteByCurpResponse obtenerClienteCurp(String curp) {
        ClienteByCurpResponse response = new ClienteByCurpResponse();
        try {
            CatClientes p = catClientesRepository.buscarClienteCurp(curp);
            if (p == null) {
                throw new Exception("La curp:" + curp + " no se encuentra");
            }
            response.getData().setIdCliente(p.getIdCliente());
            response.getData().setNombres(p.getIdPersona().getNombres());
            response.getData().setApellidoPaterno(p.getIdPersona().getApellidoPaterno());
            response.getData().setApellidoMaterno(p.getIdPersona().getApellidoMaterno());
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK");
        } catch (Exception e) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje(e.fillInStackTrace().getMessage());
            System.out.println(e);
            //nuevo.setMsjError(e.getMessage());
        }
        return response;
    }

    public String obtenerBase64(String nombreImagen, String path) {
//        ftp.connect();
        String resultado = "";

//        InputStream ip = ftp.dowloadFiles(path, nombreImagen);
        byte[] ip = ftp.dowloadFileSFTP(nombreImagen, obtenpropiedades(path));
        if (ip != null) {
            ip = UtilGenerico.encriptarDesencriptarBytes(ip);
            //                            byte[] img1 = IOUtils.toByteArray(ip);
            ip = org.apache.commons.codec.binary.Base64.encodeBase64(ip);
            resultado = new String(ip);

        }
        return resultado;

    }

    private Map<String, String> obtenpropiedades(String path) {
        CatServiciosValidacionesExternos credenciales = servicios.buscarConfigFTP();
        Map<String, String> propiedades = new HashMap<>();
        propiedades.put("ftp.sender.user", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUser());
        propiedades.put("ftp.sender.password", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getPass());
        propiedades.put("ftp.sender.host", credenciales.getCatConfiguracionesServiciosExternosList().get(0).getUrl());
        propiedades.put("ftp.sender.port", "22");
        propiedades.put("ftp.sender.path", path);

        return propiedades;

    }

    @Override
    public ClientesResponse validarTelefono(String telefonos) {

        ClientesResponse response = new ClientesResponse();
        CatPersonas p = catClientesRepository.buscarTelefono(telefonos);
        if (p != null) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje("Error, el telefono ingresado ya se encuentra registrado");
        } else if (telefonos.length() > 10 || telefonos.length() < 10) {
            response.getResponse().setCodigo(500);
            response.getResponse().setMensaje("Error, el telefono ingresado no es un telefono celular valido");

        } else {
            response.getResponse().setCodigo(200);
            response.getResponse().setMensaje("OK, el telefono ingresado  se encuentra disponible");
        }

        return response;
    }

}
