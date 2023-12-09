package com.amextra.AltaEdicionCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amextra.Beans.ImagesIdentificacion;
import com.amextra.SolicitudCredito.ProyeccionCuotas;
import com.amextra.SolicitudCredito.SolicitudDeCredito;
import com.amextra.amextra.R;
import com.amextra.dialogs.IneScanner;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataPruebaVida;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.DataValidaIne;
import com.amextra.io.Request.Identificacion;
import com.amextra.io.Request.ReqReconocimiento;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Request.RequestvalidaIne;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.DataResValidaIne;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.GradosEscolare;
import com.amextra.io.Response.Identificacione;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Nacionalidade;
import com.amextra.io.Response.ResponseCatalogosEstadoCivil;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseIdentificaicon;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponseValidaIne;
import com.amextra.utils.ConverterReqClient;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPersonalesClientesB extends AppCompatActivity implements IneScanner.TransfiereImagenes, MenuInformacionCliente.TransfiereDatos {
    Geolocalizacion geolocalizacion = new Geolocalizacion();

    ResponseGetCliente responseGetCliente = new ResponseGetCliente();

    ImagesIdentificacion imagesIdentificacion = new ImagesIdentificacion();

    RadioButton radioDatosPersonales;
    Button siguiente;

    String nombreStatus = "esAlta";
    ResponseGetCliente dataCliente = null;
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;

    MaterialSwitch switchGenero;
    String tipoIdentificacion, descripcionEstadoCivil;
    long idTipoIdentificacion, idEstadoCivil;
    String clienteInfo = "INFO_CLIENT";
    long idGenero = 0;
    ImageButton btnCamara;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    Bundle mBundle = new Bundle();
    String curp;
    String REQ_ALTA_CLI = "reqAltaCliente";
    RequestInsertClient requestInsertClient_up = new RequestInsertClient();

    AutoCompleteTextView spinerTxtTipoID, spinerTxtEstadoCivil;
    TextInputEditText spinAnioVigencia, spinAnioEmision, txtIdIdentificacion, editTxtClveId, editTxtRfc;
    TextInputLayout layOutTxtIdIdentificacion, layOutTxtCliaveId,
            layoutVencimiento, layOutEmision, identificacionSpinerLayout, layOutTxtRfc, estadoCivilLayout;
    Bundle bHeader = new Bundle();

    boolean existeInfo = false;
    InfoUSer responseLogIn = new InfoUSer();
    boolean ineValidado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales_clientes_b);
        setAdjustScreen();
        iniciaComponentes();
        Bundle recepcion = getIntent().getExtras();

        imagesIdentificacion.setEsAltaEdicion(true);
        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            geolocalizacion = (Geolocalizacion) recepcion.getSerializable("geo");
            idGenero = recepcion.getInt("idGenero");
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            if (!esAlta) {
                if (getIntent().hasExtra(REQ_ALTA_CLI)) {
                    requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                    curp = requestInsertClient.getData().getPersona().getCurp();
                    mapDataClient(requestInsertClient);
                }
            } else {
                if (getIntent().hasExtra(REQ_ALTA_CLI)) {
                    requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                    mapDataClient(requestInsertClient);
                    curp = requestInsertClient.getData().getPersona().getCurp();


                }
            }
        }


        if (idGenero == 1) {
            switchGenero.setChecked(true);
        } else {
            switchGenero.setChecked(false);
        }

        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 1);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);

        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        bHeader.putSerializable("geo", geolocalizacion);

        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();


        switchGenero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    idGenero = 1;
                } else {
                    idGenero = 0;
                }

            }
        });

        //capturaImgs();
        continuaRegistroDatosPersonales();
        cargaTipoID();
        cargaEstadoCivil();
    }


    private void mapDataClient(RequestInsertClient reequire) {
        if (reequire.data.identificacion != null) {
            existeInfo = true;
            txtIdIdentificacion.setText(reequire.data.identificacion.noIdentificacion);
            editTxtClveId.setText(reequire.data.identificacion.claveElector);
            spinAnioEmision.setText(reequire.data.identificacion.emision);
            spinAnioVigencia.setText(reequire.data.identificacion.vigencia);
            editTxtRfc.setText(reequire.data.persona.rfc);
            editTxtRfc.setText(reequire.data.persona.rfc);
            idGenero = reequire.data.persona.genero;
            if (idTipoIdentificacion != 1) {
                imagesIdentificacion.setAnverso(reequire.data.identificacion.imagen);
            }
        }

    }

/*    private void capturaImgs() {
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }*/


    private String pathToBase64(String path, int quality) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.NO_WRAP);


    }

    private void llenaInfoCliente() {

        DataReqCliente dataReqCliente = requestInsertClient.getData();


        if (!existeInfo) {

            dataReqCliente.setIdentificacion(new Identificacion());

            if (idTipoIdentificacion == 1) {
                dataReqCliente.getIdentificacion().setImagen("");
            } else {
                dataReqCliente.getIdentificacion().setImagen(imagesIdentificacion.anverso);
            }

            dataReqCliente.getIdentificacion().setNoIdentificacion(txtIdIdentificacion.getText().toString());
            dataReqCliente.getIdentificacion().setID(0);
            dataReqCliente.getIdentificacion().setTipoIdentificacionID(String.valueOf(idTipoIdentificacion));
            dataReqCliente.getIdentificacion().setClaveElector(editTxtClveId.getText().toString());
            dataReqCliente.getIdentificacion().setEmision(spinAnioEmision.getText().toString());
            dataReqCliente.getIdentificacion().setVigencia(spinAnioVigencia.getText().toString());
            dataReqCliente.getPersona().setRFC(editTxtRfc.getText().toString());
            dataReqCliente.getPersona().setGenero(idGenero);
            dataReqCliente.getPersona().setEstadoCivilID(idEstadoCivil);
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);
            requestInsertClient.setData(dataReqCliente);
        } else {
            if (idTipoIdentificacion == 1) {
                dataReqCliente.getIdentificacion().setImagen("");
            } else {
                String img = dataReqCliente.identificacion.imagen;
                if (!imagesIdentificacion.anverso.equals("")) {
                    dataReqCliente.getIdentificacion().setImagen(imagesIdentificacion.anverso);
                } else {
                    dataReqCliente.getIdentificacion().setImagen(img);
                }
            }

            dataReqCliente.getIdentificacion().setNoIdentificacion(txtIdIdentificacion.getText().toString());
            dataReqCliente.getIdentificacion().setTipoIdentificacionID(String.valueOf(idTipoIdentificacion));
            dataReqCliente.getIdentificacion().setClaveElector(editTxtClveId.getText().toString());
            dataReqCliente.getIdentificacion().setEmision(spinAnioEmision.getText().toString());
            dataReqCliente.getIdentificacion().setVigencia(spinAnioVigencia.getText().toString());
            dataReqCliente.getPersona().setRFC(editTxtRfc.getText().toString());
            dataReqCliente.getPersona().setGenero(idGenero);
            dataReqCliente.getPersona().setEstadoCivilID(idEstadoCivil);
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);

        }

    }


    private void continuaRegistroDatosPersonales() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Intent screenRegDatosPersonalesD = new Intent(DatosPersonalesClientesB.this, DatosPersonalesClientesD.class);

            if (validacampos()) {
                llenaInfoCliente();

                sender.putString(nombreTit, titulo);
                sender.putBoolean(nombreStatus, esAlta);
                sender.putSerializable("geo", geolocalizacion);
                sender.putSerializable("reqAltaCliente", requestInsertClient);
                sender.putSerializable("infoLogIn", responseLogIn);

                screenRegDatosPersonalesD.putExtras(sender);
                screenRegDatosPersonalesD.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(screenRegDatosPersonalesD);
            }
        });
    }

    private void cargaTipoID() {
        Call<ResponseIdentificaicon> call = ApiAdapter.getApiService(responseLogIn.token).identificaciones();
        call.enqueue(new Callback<ResponseIdentificaicon>() {
            @Override
            public void onResponse(Call<ResponseIdentificaicon> call, Response<ResponseIdentificaicon> response) {
                int code = response.code();
                ResponseIdentificaicon responseIdentificaicon = response.body();
                if (response.isSuccessful() && code == 200) {
                    Identificacione[] identificaciones = responseIdentificaicon.data.identificaciones;
                    ArrayList<String> tipoId = new ArrayList<>();
                    ArrayList<String> idTipoId = new ArrayList<>();
                    for (Identificacione identificacion : identificaciones) {
                        tipoId.add(identificacion.nombre.toUpperCase());
                        idTipoId.add(String.valueOf(identificacion.idIdentificaciones));
                    }


                    if (requestInsertClient.data.identificacion != null) {
                        Long idIdentifiacion = Long.parseLong(requestInsertClient.data.identificacion.tipoIdentificacionId);
                        for (Identificacione identificacion : identificaciones) {
                            if (idIdentifiacion.equals(identificacion.idIdentificaciones)) {
                                spinerTxtTipoID.setText(identificacion.nombre.toUpperCase());
                                idTipoIdentificacion = idIdentifiacion;
                                break;
                            }
                        }
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosPersonalesClientesB.this, R.layout.dropdown_item, tipoId);
                    spinerTxtTipoID.setAdapter(spinnerArrayAdapter);
                    spinerTxtTipoID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tipoIdentificacion = tipoId.get(position);
                            idTipoIdentificacion = Long.parseLong(idTipoId.get(position));
                            DialogFragment dialogFragment = IneScanner.ineScanner(imagesIdentificacion, idTipoIdentificacion, curp);
                            dialogFragment.show(getSupportFragmentManager(), "IneScanner");
                        }
                    });


                }

            }

            @Override
            public void onFailure(Call<ResponseIdentificaicon> call, Throwable t) {

            }
        });
    }


    private void cargaEstadoCivil() {
        Call<ResponseCatalogosEstadoCivil> call = ApiAdapter.getApiService(responseLogIn.token).estadosCivil();
        call.enqueue(new Callback<ResponseCatalogosEstadoCivil>() {
            @Override
            public void onResponse(Call<ResponseCatalogosEstadoCivil> call, Response<ResponseCatalogosEstadoCivil> response) {
                int code = response.code();
                ResponseCatalogosEstadoCivil responseCatalogosEstadoCivil = response.body();
                if (response.isSuccessful() && code == 200) {
                    GradosEscolare[] estadosCiviles = responseCatalogosEstadoCivil.data.estadosCiviles;
                    ArrayList<String> estadosC = new ArrayList<>();
                    ArrayList<String> idEstadosC = new ArrayList<>();
                    for (GradosEscolare estado : estadosCiviles) {
                        estadosC.add(estado.nombre.toUpperCase());
                        idEstadosC.add(String.valueOf(estado.idEstadoCivil));
                    }

                    if (requestInsertClient.data.persona.estadoCivilId != 0) {
                        Long idCivil = requestInsertClient.data.persona.estadoCivilId;
                        for (GradosEscolare estado : estadosCiviles) {
                            if (String.valueOf(idCivil).equals(estado.idEstadoCivil)) {
                                spinerTxtEstadoCivil.setText(estado.nombre.toUpperCase());
                                idEstadoCivil = idCivil;
                                break;
                            }
                        }
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosPersonalesClientesB.this, R.layout.dropdown_item, estadosC);
                    spinerTxtEstadoCivil.setAdapter(spinnerArrayAdapter);
                    spinerTxtEstadoCivil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            descripcionEstadoCivil = estadosC.get(position);
                            idEstadoCivil = Long.parseLong(idEstadosC.get(position));
                        }
                    });


                }

            }

            @Override
            public void onFailure(Call<ResponseCatalogosEstadoCivil> call, Throwable t) {

            }
        });
    }

    private void iniciaComponentes() {
        siguiente = findViewById(R.id.siguiente);
        spinerTxtTipoID = findViewById(R.id.spinerTxtTipoID);
        spinerTxtEstadoCivil = findViewById(R.id.spinerTxtEstadoCivil);
        switchGenero = findViewById(R.id.switchGenero);

        editTxtRfc = findViewById(R.id.editTxtRfc);
        btnCamara = findViewById(R.id.btnCamara);
        txtIdIdentificacion = findViewById(R.id.txtIdIdentificacion);
        editTxtClveId = findViewById(R.id.editTxtClveId);
        spinAnioVigencia = findViewById(R.id.spinAnioVigencia);
        spinAnioEmision = findViewById(R.id.spinAnioEmision);


        layOutTxtIdIdentificacion = findViewById(R.id.layOutTxtIdIdentificacion);
        layOutTxtCliaveId = findViewById(R.id.layOutTxtCliaveId);
        layoutVencimiento = findViewById(R.id.layoutVencimiento);
        layOutEmision = findViewById(R.id.layOutEmision);
        identificacionSpinerLayout = findViewById(R.id.identificacionSpinerLayout);
        layOutTxtRfc = findViewById(R.id.layOutTxtRfc);
        estadoCivilLayout = findViewById(R.id.estadoCivilLayout);


    }

    @Override
    public void transfiereBase64(ImagesIdentificacion base64) {

        if (idTipoIdentificacion == 1) {
            String base_rostro = pathToBase64(base64.rostro, 100);
            String base_frente = pathToBase64(base64.anverso, 100);
            String base_back = pathToBase64(base64.reverso, 100);
            RequestvalidaIne reqIne = new RequestvalidaIne();
            ReqReconocimiento reqPruebaVida = new ReqReconocimiento();
            reqPruebaVida.setData(new DataPruebaVida());
            reqIne.setData(new DataValidaIne());
            reqIne.getData().setCurp(curp);
            reqIne.getData().setReverso(base_back);
            reqIne.getData().setAnverso(base_frente);
            reqPruebaVida.getData().setCurp(curp);
            reqPruebaVida.getData().setIne(base_frente);
            reqPruebaVida.getData().setSelfie(base_rostro);
            validaIne(reqIne);
            generaPruebaVida(reqPruebaVida);

        } else {

        }

    }

    private void generaPruebaVida(ReqReconocimiento body) {
        Call<ResponseValidaIne> call = ApiAdapter.getApiService(responseLogIn.token).pruebaVida(body);
        call.enqueue(new Callback<ResponseValidaIne>() {
            @Override
            public void onResponse(Call<ResponseValidaIne> call, Response<ResponseValidaIne> response) {
                ResponseValidaIne info = response.body();
                int code = response.code();
                boolean status = response.isSuccessful();

                if(code==200 && status ){
                    if(info.response.codigo == 200){
                        new SweetAlertDialog(DatosPersonalesClientesB.this,SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Prueba de vida")
                                .setContentText("" + info.response.codigo+" - "+info.response.mensaje)
                                .show();
                        call.cancel();
                    }else{
                        new SweetAlertDialog(DatosPersonalesClientesB.this,SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Prueba de vida")
                                .setContentText("" + info.response.codigo+" - "+info.response.mensaje)
                                .show();
                    }



                }

            }

            @Override
            public void onFailure(Call<ResponseValidaIne> call, Throwable t) {
                Toast.makeText(DatosPersonalesClientesB.this, "Prueba de vida " + t, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }


    private void validaIne(RequestvalidaIne requestvalidaIne) {
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Validando ine");
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseValidaIne> call = ApiAdapter.getApiService(responseLogIn.token).validaIne(requestvalidaIne);
        call.enqueue(new Callback<ResponseValidaIne>() {
            @Override
            public void onResponse(Call<ResponseValidaIne> call, Response<ResponseValidaIne> response) {
                int code = response.code();
                boolean status = response.isSuccessful();
                ResponseValidaIne datos = response.body();
                if (code == 200 && status) {

                    if (datos.response.codigo == 200) {
                        DataResValidaIne data_final = datos.data;
                        ineValidado = true;
                        call.cancel();
                        txtIdIdentificacion.setText(data_final.noIdentificacion);
                        editTxtClveId.setText(data_final.claveElector);
                        spinAnioVigencia.setText(data_final.vigencia);
                        spinAnioEmision.setText(String.valueOf(data_final.emision));

                        dialogFragment.dismiss();

                    } else {

                        call.cancel();
                        dialogFragment.dismiss();
                        new SweetAlertDialog(DatosPersonalesClientesB.this, SweetAlertDialog.WARNING_TYPE)
                                .setContentText("Por favor capture los datos de la identificacion de manera manual.")
                                .setTitleText(datos.response.codigo + " " + datos.response.mensaje)
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseValidaIne> call, Throwable t) {

                call.cancel();
                dialogFragment.dismiss();
                new SweetAlertDialog(DatosPersonalesClientesB.this, SweetAlertDialog.WARNING_TYPE)
                        .setContentText(t.toString())
                        .setTitleText("Error")
                        .show();
            }
        });
    }


    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private boolean validacampos() {
        boolean status = true;
        String numId = txtIdIdentificacion.getText().toString();
        String claveId = editTxtClveId.getText().toString();
        String emision = spinAnioEmision.getText().toString();
        String vigente = spinAnioVigencia.getText().toString();
        String rfc = editTxtRfc.getText().toString();
        String tipoid = spinerTxtTipoID.getText().toString();
        String estadoCivil = spinerTxtEstadoCivil.getText().toString();


        if (estadoCivil.equals("")) {
            status = false;
            estadoCivilLayout.setError("Estado civil requerido");
        } else {
            estadoCivilLayout.setErrorEnabled(false);
        }


        if (tipoid.equals("")) {
            status = false;
            identificacionSpinerLayout.setError("Tipo de identificación requerido");
        } else {
            identificacionSpinerLayout.setErrorEnabled(false);
        }

        if (numId.equals("")) {
            status = false;
            layOutTxtIdIdentificacion.setError("El numero de identificacion es necesario");
        } else {
            layOutTxtIdIdentificacion.setErrorEnabled(false);
        }

        if (claveId.equals("")) {
            status = false;
            layOutTxtCliaveId.setError("La clave de identificacion es necesario");

        } else {
            layOutTxtCliaveId.setErrorEnabled(false);
        }
        if (rfc.equals("")) {
            status = false;
            layOutTxtRfc.setError("El RFC es necesario");

        } else {
            layOutTxtRfc.setErrorEnabled(false);
        }
        if (emision.equals("")) {
            status = false;
            layOutEmision.setError("El año de emisión es necesario");
        } else {
            if (Integer.parseInt(emision) > Integer.parseInt(vigente)) {
                status = false;
                layOutEmision.setError("Vigencia no valida, el año de vigencia no puede ser menor al año de emisión");
            } else {
                layOutEmision.setErrorEnabled(false);
            }
        }
        if (vigente.equals("")) {
            status = false;
            layoutVencimiento.setError("El año de vencimiento es necesario");
        } else {

            layoutVencimiento.setErrorEnabled(false);
        }

        if (esAlta) {
            if (idTipoIdentificacion != 1) {

                if (imagesIdentificacion.anverso == null || imagesIdentificacion.anverso.equals("")) {
                    status = false;
                    new SweetAlertDialog(DatosPersonalesClientesB.this, SweetAlertDialog.WARNING_TYPE)
                            .setContentText("Imagen de identificacion necesaria, por favor capture este dato.")
                            .setTitleText("Advertencia")
                            .show();

                } else {
                    status = true;
                }

            } else {
                if (ineValidado) {
                    status = true;
                }
            }
        }


        return status;


    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if (validacampos()) {
            llenaInfoCliente();
        }
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);


    }
}