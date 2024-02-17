package com.amextra.io;

import com.amextra.Beans.RequestLogin;
import com.amextra.io.Request.ReqReconocimiento;
import com.amextra.io.Request.RequestActualizaPass;
import com.amextra.io.Request.RequestCitasEmpleado;
import com.amextra.io.Request.RequestConsultaCurp;
import com.amextra.io.Request.RequestEnvioSMS;
import com.amextra.io.Request.RequestExisteCorreo;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Request.RequestProyeccion;
import com.amextra.io.Request.RequestRecuperaPass;
import com.amextra.io.Request.RequestRegistraAsistencia;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Request.RequestValidaSMS;
import com.amextra.io.Request.RequestvalidaIne;
import com.amextra.io.Response.ResValidaTelefono;
import com.amextra.io.Response.ResponseActualizaPass;
import com.amextra.io.Response.ResponseAddSolicitudCredito;
import com.amextra.io.Response.ResponseAgenda;
import com.amextra.io.Response.ResponseAvisos;
import com.amextra.io.Response.ResponseCaracteristicasNegocios;
import com.amextra.io.Response.ResponseCatalogoGradosEscolares;
import com.amextra.io.Response.ResponseCatalogoProductos;
import com.amextra.io.Response.ResponseCatalogosEstadoCivil;
import com.amextra.io.Response.ResponseCatalogosNacionalidad;
import com.amextra.io.Response.ResponseCurp;
import com.amextra.io.Response.ResponseCurpClienteSolicitud;
import com.amextra.io.Response.ResponseDestinoCredito;
import com.amextra.io.Response.ResponseEnvioSMS;
import com.amextra.io.Response.ResponseExisteCorreo;
import com.amextra.io.Response.ResponseFrecuenciaPago;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseGetClientes;
import com.amextra.io.Response.ResponseGirosNegocios;
import com.amextra.io.Response.ResponseIdentificaicon;
import com.amextra.io.Response.ResponseImagesCte;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponseParentescos;
import com.amextra.io.Response.ResponsePatrimonios;
import com.amextra.io.Response.ResponseRecuperaPass;
import com.amextra.io.Response.ResponseSolicitudProyeccion;
import com.amextra.io.Response.ResponseSolicitudesCreditoCliente;
import com.amextra.io.Response.ResponseTiemposActualesEmpleo;
import com.amextra.io.Response.ResponseTiposResidencia;
import com.amextra.io.Response.ResponseValidaIne;
import com.amextra.io.Response.ResponseValidaSMS;
import com.amextra.io.Response.ResponseciudadPorEstado;
import com.amextra.io.Response.ResponsecodigoPostal;
import com.amextra.io.Response.ResponsecoloniaPorMunicipio;
import com.amextra.io.Response.Responseestados;
import com.amextra.io.Response.ResponsemunicipiosPorEstado;
import com.amextra.io.Response.ResponsetiposViviendas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/clientes")
    Call<ResponseGetClientes> getListClientes();
    @GET("/catalogos/avisos")
    Call<ResponseAvisos> avisos();
    @POST("/login/entrar")
    Call<ResponseLogin> postIniciaSesion(@Body RequestLogin requestLogin);
    @POST("/login/existecorreo")
    Call<ResponseExisteCorreo> postExisteCorreon(@Body RequestExisteCorreo requestExisteCorreo);
    @POST("/correo/recuperapass")
    Call<ResponseRecuperaPass> postRecuperaPass(@Body RequestRecuperaPass requestRecuperaPass);
    @POST("/login/actualizapass")
    Call<ResponseActualizaPass> postActualizaPass(@Body RequestActualizaPass requestActualizaPass);
    @GET("/clientes/{id}")
    Call<ResponseGetCliente> getCliente(@Path("id") String id);
    @GET("/clientes/imagenescliente/{id}")
    Call<ResponseImagesCte> getImagesClient(@Path("id") String id);
    @GET("/clientes/curp/{curp}")
    Call<ResponseCurpClienteSolicitud> getCurpClienteSolicitud(@Path("curp") String curp);
    @POST("/clientes")
    Call<ResponseGetClientes> generaAltaCliente(@Body RequestInsertClient resquestAltaCliente);
    @PUT("/clientes")
    Call<ResponseGetClientes> actualizaCliente(@Body RequestInsertClient resquestAltaCliente);
    @GET("/solicitud/cliente/{id}")
    Call<ResponseSolicitudesCreditoCliente> solicitudesCreditoCliente(@Path("id") String id);
    @GET("/clientes/asesor/{id}")
    Call<ResponseGetClientes> getClientesAsesor(@Path("id") String id);
    @GET("/clientes/validaciontelefono/{phone}")
    Call<ResValidaTelefono> validaNumeroTelfono(@Path("phone")String telefono);

    //--------- CATALOGOS --------->
    @GET("/catalogos/gradosEscolares")
    Call<ResponseCatalogoGradosEscolares> gradosEscolares();
    @GET("/catalogos/estadosCiviles")
    Call<ResponseCatalogosEstadoCivil> estadosCivil();
    @GET("/catalogos/nacionalidades")
    Call<ResponseCatalogosNacionalidad> nacionalidades();
    @GET("/catalogos/tiposResidencias")
    Call<ResponseTiposResidencia> tipoResidencia();
    @GET("/catalogos/girosNegocioEmpresas")
    Call<ResponseGirosNegocios> giroNegocio();
    @GET("/catalogos/caracteristicasNegocios")
    Call<ResponseCaracteristicasNegocios> caracteristicasNegocios();
    @GET("/catalogos/tiemposActualesEmpleo")
    Call<ResponseTiemposActualesEmpleo> tiemposActualesEmpleo();
    @GET("/catalogos/tiposViviendas")
    Call<ResponsetiposViviendas> tiposViviendas();
    @POST("/valida/curp")
    Call<ResponseCurp> consultaCurp(@Body RequestConsultaCurp requestConsultaCurp);
    @POST("/envio/sms/")
    Call<ResponseEnvioSMS> enviaSMS(@Body RequestEnvioSMS requestEnvioSMS);
    @POST("/envio/validar")
    Call<ResponseValidaSMS> validaSMS(@Body RequestValidaSMS requestValidaSMS);
    @POST("/ocr/ine/")
    Call<ResponseValidaIne> validaIne(@Body RequestvalidaIne requestvalidaIne);
    @POST("/ocr/reconocimientofacial")
    Call<ResponseValidaIne> pruebaVida(@Body ReqReconocimiento reqReconocimiento);
    @GET("/catalogos/estados") Call<Responseestados> estados();
    @GET("/catalogos/municipiosPorEstado/{id}") Call<ResponsemunicipiosPorEstado> municipiosPorEstado(@Path("id") String id);
    @GET("/catalogos/ciudadPorEstado/{id}") Call<ResponseciudadPorEstado> ciudadPorEstado(@Path("id") String id);
    @GET("/catalogos/coloniaPorMunicipio/{id}") Call<ResponsecoloniaPorMunicipio> coloniaPorMunicipio(@Path("id") String id);
    @GET("/catalogos/codigoPostal/{id}")
    Call<ResponsecodigoPostal> codigoPostal(@Path("id") String id);
    @GET("/catalogos/parentescos")
    Call<ResponseParentescos> parentescos();
    @GET("/catalogos/tiposIdentificaciones")
    Call<ResponseIdentificaicon> identificaciones();
    @GET("/catalogos/frecuenciasPago")
    Call<ResponseFrecuenciaPago> frecuenciaPago();
    @GET("/catalogos/productos")
    Call<ResponseCatalogoProductos> catalogoProductos();
    @GET("/catalogos/destinoCreditos")
    Call<ResponseDestinoCredito> destinoCreditos();
    @POST("/solicitud/proyeccion")
    Call<ResponseSolicitudProyeccion> solicitudProyeccion(@Body RequestProyeccion requestProyeccion);
    @POST("/solicitud/")
    Call<ResponseAddSolicitudCredito> addSolicitudCredito(@Body RequestSolicitudCredito requestSolicitudCredito);
    @GET("/catalogos/patrimonios")
    Call<ResponsePatrimonios> patrimonios();

    //--------- AGENDA --------->
    @POST("/agenda/pendientes/asesorfecha/")
    Call<ResponseAgenda> agendaPendientes(@Body RequestCitasEmpleado requestCitasEmpleado);
    @POST("/agenda/registrarAsitencia/")
    Call<ResponseAgenda> confirmaAsistencia(@Body RequestRegistraAsistencia registraAsistencia);

}
