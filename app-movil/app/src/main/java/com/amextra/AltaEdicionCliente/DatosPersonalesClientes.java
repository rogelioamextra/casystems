package com.amextra.AltaEdicionCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;


import com.amextra.MenuHomeScreen;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataCurp;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.Persona;
import com.amextra.io.Request.RequestConsultaCurp;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.Estado;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Nacionalidade;
import com.amextra.io.Response.ResponseCatalogosNacionalidad;
import com.amextra.io.Response.ResponseCurp;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.Responseestados;
import com.amextra.utils.ConverterReqClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPersonalesClientes extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Button btnSiguiente, btnCalendario;
    int dia, mes, anio;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String LABEL_REGISTRADO = "ya se encuentra registrada";
    String clienteInfo = "INFO_CLIENT";
    String REQ_ALTA_CLI = "reqAltaCliente",idDescribeEstado;
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    Calendar calendar;
    DatePickerDialog picker;
    ResponseGetCliente dataCliente;
    boolean status = true;
    int idGenero = 0;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    RequestInsertClient requestInsertClient_up = new RequestInsertClient();

    String titulo, describeNacionalidad;
    long  idDescribeNacionalidad;
    Toast toast = null;
    int duration = Toast.LENGTH_SHORT;


    ResponseGetCliente responseGetCliente = new ResponseGetCliente();

    boolean esAlta;

    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    Bundle mBundle = new Bundle();
    Bundle bHeader = new Bundle();
    AutoCompleteTextView txtTestExposed, spinnerTxtLugarNac;
    TextInputEditText editTxtCurp, editTxtNombre, editTxtApellidoP, editTxtApellidoM, editTxtFechaNacimiento;
    TextInputLayout layOutTxtCurp, layOuteditTxtNombre, layOuteditTxtApellidoP, layOuteditTxtApellidoM, tstSpinerLayout, lugarNacSpinerLayout, layOutTeditTxtFechaNacimiento;
    boolean existeInfo = false;

    String idClinte;
    boolean statusCliente = true;
    InfoUSer responseLogIn = new InfoUSer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAdjustScreen();

        setContentView(R.layout.activity_datos_personales_clientes);
        cargaInicial();


        Bundle recepcion = getIntent().getExtras();

        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            geolocalizacion = (Geolocalizacion) recepcion.getSerializable("geo");
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            if(esAlta){
                RequestInsertClient reqInsCliTmp = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                if(reqInsCliTmp!= null)
                {
                    if(reqInsCliTmp.data!=null){
                        requestInsertClient = reqInsCliTmp;
                        mapDataClient(requestInsertClient);
                        existeInfo = true;
                    }
                }
            }

            if (titulo.equals("Editar Datos del Cliente")) {
                if (getIntent().hasExtra(clienteInfo)) {
                    responseGetCliente = (ResponseGetCliente) recepcion.getSerializable(clienteInfo);
                    Cliente cliente = responseGetCliente.data.cliente;
                    ConverterReqClient converterReqClient = new ConverterReqClient();
                    requestInsertClient = converterReqClient.converter(cliente);
                    existeInfo = true;
                    mapDataClient(requestInsertClient);

                }else{
                    RequestInsertClient reqInsCliTmp = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                    if(reqInsCliTmp!= null)
                    {
                        if(reqInsCliTmp.data!=null){
                            requestInsertClient = reqInsCliTmp;
                            existeInfo = true;
                            mapDataClient(requestInsertClient);
                        }
                    }
                }


            }

        }



        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 0);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable("infoLogIn",responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);


        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn",responseLogIn);
        bHeader.putSerializable("geo", geolocalizacion);

        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();


        calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        anio = calendar.get(Calendar.YEAR);




        continuaRegistroDatosPersonales();
        muestraCalendario();
        editTxtCurp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RequestConsultaCurp requestConsultaCurp = new RequestConsultaCurp();
                requestConsultaCurp.setData(new DataCurp());
                String curp = s.toString();
                if (curp.length() == 18) {
                    requestConsultaCurp.getData().setCurp(curp);
                    if(esAlta){
                        consultaCurp(requestConsultaCurp);
                    }
                    layOutTxtCurp.setErrorEnabled(false);
                } else {
                    cleanItems();
                    layOutTxtCurp.setError("La longitud obligatoria del curp es de 18 caracteres");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void mapDataClient(RequestInsertClient requestInsertClient) {
        if(requestInsertClient.data !=null) {
            editTxtNombre.setText(requestInsertClient.data.persona.nombres);
            editTxtApellidoP.setText(requestInsertClient.data.persona.apellidoPaterno);
            editTxtApellidoM.setText(requestInsertClient.data.persona.apellidoMaterno);
            editTxtFechaNacimiento.setText(requestInsertClient.data.persona.fechaNacimiento);
            editTxtCurp.setText(requestInsertClient.data.persona.curp);
            idClinte = requestInsertClient.data.id;
            statusCliente = requestInsertClient.data.status;
            consultaNacionalidad();
            consultaLugaresNac();

        }

    }


    private void muestraCalendario() {
        editTxtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new DatePickerDialog(DatosPersonalesClientes.this, R.style.datePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String lMes = return2digits(month + 1);
                        String lDia = return2digits(dayOfMonth);
                        editTxtFechaNacimiento.setText(year + "-" + lMes + "-" + lDia);

                    }
                }, anio, mes, dia);
                picker.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                picker.show();
            }
        });
    }

    private String return2digits(int numero) {
        String finalText = "";
        int largo = String.valueOf(numero).length();
        if (largo == 1) {
            finalText = "0" + numero;
        } else {
            finalText = String.valueOf(numero);
        }

        return finalText;
    }

    private void consultaNacionalidad() {
        Call<ResponseCatalogosNacionalidad> nacionalidadCall = ApiAdapter.getApiService(responseLogIn.token).nacionalidades();
        Context context = getApplicationContext();
        nacionalidadCall.enqueue(new Callback<ResponseCatalogosNacionalidad>() {
            @Override
            public void onResponse(Call<ResponseCatalogosNacionalidad> call, Response<ResponseCatalogosNacionalidad> response) {
                ResponseCatalogosNacionalidad nacionalidades = response.body();
                ArrayList<String> descripNac = new ArrayList<>();
                ArrayList<String> idNacList = new ArrayList<>();
                int code = response.code();
                if (response.isSuccessful() && code == 200) {
                    if (nacionalidades.response.codigo == 200) {
                        Nacionalidade[] nacionalidades_ = nacionalidades.data.nacionalidades;
                        for (Nacionalidade nacionalidad : nacionalidades_) {
                            descripNac.add(nacionalidad.nombre);
                            idNacList.add(String.valueOf(nacionalidad.idNacionalidad));
                        }

                        if(requestInsertClient.data !=null) {
                            Long idNac = requestInsertClient.data.persona.nacionalidadId;
                            for (Nacionalidade nacionalidad : nacionalidades_) {
                                if(idNac.equals(nacionalidad.idNacionalidad)){
                                    txtTestExposed.setText(nacionalidad.nombre.toUpperCase());
                                    idDescribeNacionalidad = idNac;
                                    break;
                                }
                            }
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosPersonalesClientes.this, android.R.layout.simple_spinner_dropdown_item, descripNac);
                        txtTestExposed.setAdapter(spinnerArrayAdapter);
                        txtTestExposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                describeNacionalidad = descripNac.get(position);
                                idDescribeNacionalidad = Long.parseLong(idNacList.get(position));
                            }
                        });
                    } else {
                        Toast.makeText(context, nacionalidades.response.codigo + " " + nacionalidades.response.mensaje, duration);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCatalogosNacionalidad> call, Throwable t) {
                call.cancel();
                Toast.makeText(context, t.toString(), duration);


            }
        });
    }


    private void consultaLugaresNac() {
        Call<Responseestados> call = ApiAdapter.getApiService(responseLogIn.token).estados();
        call.enqueue(new Callback<Responseestados>() {
            @Override
            public void onResponse(Call<Responseestados> call, Response<Responseestados> response) {
                int code = response.code();
                boolean stat = response.isSuccessful();
                if (stat && code == 200) {
                    Responseestados data = response.body();
                    if (data.response.codigo == 200) {
                        ArrayList<String> estados = new ArrayList<>();
                        ArrayList<String> idsEstados = new ArrayList<>();
                        Estado[] listaEstado = data.data.estados;
                        for (Estado estado : listaEstado) {
                            estados.add(estado.nombre.toUpperCase());
                            idsEstados.add(estado.codigoEstado);
                        }
                        if(requestInsertClient.data !=null) {
                            String idEstado =requestInsertClient.data.persona.lugarNacimientoId;
                            for (Estado estado : listaEstado) {
                                if(idEstado.equals(estado.codigoEstado)){
                                    spinnerTxtLugarNac.setText(estado.nombre.toUpperCase());
                                    idDescribeEstado = estado.codigoEstado;
                                    break;
                                }
                            }
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosPersonalesClientes.this, android.R.layout.simple_spinner_dropdown_item, estados);
                        spinnerTxtLugarNac.setAdapter(spinnerArrayAdapter);
                        spinnerTxtLugarNac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescribeEstado = idsEstados.get(position);
                            }
                        });

                    } else {

                        Toast.makeText(DatosPersonalesClientes.this, data.response.codigo + " - " + data.response.mensaje, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<Responseestados> call, Throwable t) {
                Toast.makeText(DatosPersonalesClientes.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void consultaCurp(RequestConsultaCurp req) {
        String msg = "Validando Curp...";
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Context context = getApplicationContext();
        Call<ResponseCurp> call = ApiAdapter.getApiService(responseLogIn.token).consultaCurp(req);

        call.enqueue(new Callback<ResponseCurp>() {

            @Override
            public void onResponse(Call<ResponseCurp> call, Response<ResponseCurp> response) {

                ResponseCurp curp = response.body();
                int code = response.code();

                if (response.isSuccessful() && code == 200) {
                    if (curp.response.codigo == 200) {
                        editTxtNombre.setText(curp.data.nombre);
                        editTxtApellidoP.setText(curp.data.apellidoPaterno);
                        editTxtApellidoM.setText(curp.data.apellidoMaterno);
                        editTxtFechaNacimiento.setText(curp.data.fechaNacimiento);
                        txtTestExposed.setText(curp.data.nacionalidad.toUpperCase());
                        spinnerTxtLugarNac.setText(curp.data.nombreEstado.toUpperCase());
                        idDescribeNacionalidad = Long.parseLong(curp.data.nacionalidadId);
                        idDescribeEstado = curp.data.estadoNacimiento;
                        idGenero = Integer.parseInt(curp.data.codigoSexo);
                        blockElements();
                        call.cancel();
                        //status = true;
                        dialogFragment.dismiss();

                    } else {
                        dialogFragment.dismiss();
                        SweetAlertDialog dialog = new SweetAlertDialog(DatosPersonalesClientes.this,SweetAlertDialog.ERROR_TYPE);
                        if(curp.response.mensaje.contains(LABEL_REGISTRADO)){
                            //status = false;
                            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog.dismissWithAnimation();
                                    //toMenu();
                                }
                            });
                            dialog.setConfirmText("Continuar");
                            dialog.setTitleText("");
                            dialog.setContentText(curp.response.mensaje.toUpperCase());

                            dialog.show();
                        }
                        else{
                            dialog.setConfirmText("Continuar");
                            dialog.setContentText(curp.response.mensaje.toUpperCase());
                            dialog.show();
                        }

                        consultaNacionalidad();
                        consultaLugaresNac();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseCurp> call, Throwable t) {
                consultaNacionalidad();
                consultaLugaresNac();
                call.cancel();
                btnCalendario.setVisibility(View.VISIBLE);
                dialogFragment.dismiss();
                toast.makeText(context, t.toString(), duration).show();


            }
        });

    }



    private void blockElements() {

        layOuteditTxtNombre.setEnabled(false);
        layOuteditTxtApellidoP.setEnabled(false);
        layOuteditTxtApellidoM.setEnabled(false);
        tstSpinerLayout.setEnabled(false);
        lugarNacSpinerLayout.setEnabled(false);
        layOutTeditTxtFechaNacimiento.setEnabled(false);
    }

    private void cleanItems() {
        editTxtNombre.setText("");
        editTxtApellidoP.setText("");
        editTxtApellidoM.setText("");
        txtTestExposed.setText("");
        spinnerTxtLugarNac.setText("");
        editTxtFechaNacimiento.setText("");


        layOuteditTxtNombre.setEnabled(true);
        layOuteditTxtApellidoP.setEnabled(true);
        layOuteditTxtApellidoM.setEnabled(true);
        tstSpinerLayout.setEnabled(true);
        lugarNacSpinerLayout.setEnabled(true);
        layOutTeditTxtFechaNacimiento.setEnabled(true);


    }

    private boolean validacampos() {

        String name = editTxtNombre.getText().toString();
        String curp = editTxtCurp.getText().toString();
        String apP = editTxtApellidoP.getText().toString();
        String apM = editTxtApellidoM.getText().toString();
        String fechNac = editTxtFechaNacimiento.getText().toString();
        String nacionalidad = txtTestExposed.getText().toString();
        String lugarNac = spinnerTxtLugarNac.getText().toString();


        layOuteditTxtNombre.setEnabled(false);
        layOuteditTxtApellidoP.setEnabled(false);
        layOuteditTxtApellidoM.setEnabled(false);
        tstSpinerLayout.setEnabled(false);
        lugarNacSpinerLayout.setEnabled(false);
        layOutTeditTxtFechaNacimiento.setEnabled(false);

        if (name.equals("")) {
            status = false;
            layOuteditTxtNombre.setError("Nombre requerido");
            editTxtNombre.setSelection(name.length());
            editTxtNombre.requestFocus();

        } else {
            layOuteditTxtNombre.setErrorEnabled(false);
        }

        if (curp.equals("") || curp.length() < 18) {
            status = false;
            layOutTxtCurp.setError("Curp requerida,longitud maxima 18 caracteres.");
        } else {
            layOutTxtCurp.setErrorEnabled(false);
        }
        if (apP.equals("")) {
            status = false;
            layOuteditTxtApellidoP.setError("Apeliido paterno necesario");
        } else {
            layOuteditTxtApellidoP.setErrorEnabled(false);
        }
        if (apM.equals("")) {
            status = false;
            layOuteditTxtApellidoM.setError("Apeliido materno necesario");
        } else {
            layOuteditTxtApellidoM.setErrorEnabled(false);
        }
        if (fechNac.equals("")) {
            status = false;
            layOutTeditTxtFechaNacimiento.setError("Fecha de nacimiento necesaria");
        } else {
            layOutTeditTxtFechaNacimiento.setErrorEnabled(false);

        }
        if (nacionalidad.equals("")) {
            status = false;
            tstSpinerLayout.setError("Nacionalidad requerida");
        } else {
            tstSpinerLayout.setErrorEnabled(false);
        }

        if (lugarNac.equals("")) {
            status = false;
            lugarNacSpinerLayout.setError("Lugar de nacimiento requerido");
        } else {
            lugarNacSpinerLayout.setErrorEnabled(false);

        }


        return status;
    }

    private void toMenu(){
        Bundle sender = new Bundle();
        Intent menuHome = new Intent(DatosPersonalesClientes.this, MenuHomeScreen.class);
        sender.putSerializable("geo", geolocalizacion);
        sender.putBoolean(nombreStatus, esAlta);
        sender.putSerializable("infoLogIn",responseLogIn);
        menuHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        menuHome.putExtras(sender);
        startActivity(menuHome);
    }

    private void continuaRegistroDatosPersonales() {
        btnSiguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean continuar = validacampos();
                Bundle sender = new Bundle();


                if (continuar) {
                    setInfoDataCliente();
                    Intent screenDatosPersonalesCont = new Intent(DatosPersonalesClientes.this, DatosPersonalesClientesB.class);
                    sender.putString(nombreTit, titulo);
                    sender.putSerializable("geo", geolocalizacion);
                    sender.putInt("idGenero", idGenero);
                    sender.putSerializable("infoLogIn",responseLogIn);
                    sender.putBoolean(nombreStatus, esAlta);
                    screenDatosPersonalesCont.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    sender.putSerializable(REQ_ALTA_CLI, requestInsertClient);
                    screenDatosPersonalesCont.putExtras(sender);
                    startActivity(screenDatosPersonalesCont);
                }
            }
        });
    }


    @SuppressLint("WrongViewCast")
    private void cargaInicial() {
        btnSiguiente = findViewById(R.id.btnSiguiente);
        editTxtFechaNacimiento = findViewById(R.id.editTxtFechaNacimiento);
        editTxtCurp = findViewById(R.id.editTxtCurp);
        editTxtNombre = findViewById(R.id.editTxtNombre);
        editTxtApellidoP = findViewById(R.id.editTxtApellidoP);
        editTxtApellidoM = findViewById(R.id.editTxtApellidoM);
        spinnerTxtLugarNac = findViewById(R.id.spinnerTxtLugarNac);
        txtTestExposed = findViewById(R.id.txtTestExposed);
        layOutTxtCurp = findViewById(R.id.layOutTxtCurp);
        layOuteditTxtNombre = findViewById(R.id.layOuteditTxtNombre);
        layOuteditTxtApellidoP = findViewById(R.id.layOuteditTxtApellidoP);
        layOuteditTxtApellidoM = findViewById(R.id.layOuteditTxtApellidoM);
        tstSpinerLayout = findViewById(R.id.tstSpinerLayout);
        lugarNacSpinerLayout = findViewById(R.id.lugarNacSpinerLayout);
        layOutTeditTxtFechaNacimiento = findViewById(R.id.layOutTeditTxtFechaNacimiento);


    }


    private void setInfoDataCliente() {
        if(!existeInfo){
            requestInsertClient.setData(new DataReqCliente());
            DataReqCliente dataReqCliente = new DataReqCliente();
            dataReqCliente.setPersona(new Persona());
            dataReqCliente.setID(null);
            dataReqCliente.getPersona().setID("0");
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);
            dataReqCliente.getPersona().setNombres(editTxtNombre.getText().toString());
            dataReqCliente.getPersona().setApellidoPaterno(editTxtApellidoP.getText().toString());
            dataReqCliente.getPersona().setApellidoMaterno(editTxtApellidoM.getText().toString());
            dataReqCliente.getPersona().setCurp(editTxtCurp.getText().toString());
            dataReqCliente.getPersona().setNacionalidadID(idDescribeNacionalidad);
            dataReqCliente.getPersona().setLugarNacimientoID(idDescribeEstado);
            dataReqCliente.getPersona().setFechaNacimiento(editTxtFechaNacimiento.getText().toString());

            dataReqCliente.setStatus(true);

            requestInsertClient.setData(dataReqCliente);
        }else{
            requestInsertClient.getData().setAsesorId(responseLogIn.usuarioId);
            requestInsertClient.getData().setStatus(statusCliente);
            requestInsertClient.getData().getPersona().setID(requestInsertClient.data.persona.id);
            requestInsertClient.getData().getPersona().setNombres(editTxtNombre.getText().toString());
            requestInsertClient.getData().getPersona().setApellidoPaterno(editTxtApellidoP.getText().toString());
            requestInsertClient.getData().getPersona().setApellidoMaterno(editTxtApellidoM.getText().toString());
            requestInsertClient.getData().getPersona().setCurp(editTxtCurp.getText().toString());
            requestInsertClient.getData().getPersona().setNacionalidadID(idDescribeNacionalidad);
            requestInsertClient.getData().getPersona().setLugarNacimientoID(idDescribeEstado);
            requestInsertClient.getData().getPersona().setFechaNacimiento(editTxtFechaNacimiento.getText().toString());

        }



    }

    @Override
    public void transfiereInfo(RequestInsertClient require) {
        if(validacampos()){
            setInfoDataCliente();
        }
        mBundle.putSerializable("infoLogIn",responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);
    }


    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }


}