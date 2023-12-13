package com.amextra.SolicitudCredito;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amextra.Beans.RequestLogin;
import com.amextra.MainActivity;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataRequestProyeccion;
import com.amextra.io.Request.DataRequestSolicitudCredito;
import com.amextra.io.Request.RequestProyeccion;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.DestinosCredito;
import com.amextra.io.Response.FrecuenciaPago;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Producto;
import com.amextra.io.Response.ResponseCatalogoProductos;
import com.amextra.io.Response.ResponseCurpClienteSolicitud;
import com.amextra.io.Response.ResponseDestinoCredito;
import com.amextra.io.Response.ResponseFrecuenciaPago;
import com.amextra.io.Response.ResponseLogin;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class SolicitudDeCredito extends AppCompatActivity {
    Button siguiente, btnCalendario;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    EditText montoCredito;
    SeekBar seekBar;
    RequestProyeccion requestProyeccion = new RequestProyeccion();
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    CheckBox esRecompraCheck;
    String labelCurp = "CURP_CLIENTE";
    String esSmsVerificado = "VERIFICA_SMS";
    Calendar calendar;
    DatePickerDialog picker;
    AutoCompleteTextView destinoCredito, spinnerFrecuenciaPago, productosCredito;
    TextInputEditText txtFechaSolicitud, plazo;
    long idFrecuenciaPago, idPrdouctoCredito, idDestinoCredito;
    int dia, mes, anio;
    int montoC;
    boolean esRecompra;
    String curpCliente;

    boolean smsValidado = true;
    int MAX_SOLCITUD = 200000;
    String N_REQ_SOL_CRED = "REQSOLCRED";
    String N_REQ_PROYEC = "REQPROYEC";
    Button validaSmsBtn;
    TextView noCliente, nombreCliente, txtFrecPago;
    TextInputEditText inputCurp;
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    InfoUSer responseLogIn = new InfoUSer();

    String INFO_USER = "infoLogIn";
    private EditText et;


    TextInputLayout layOutTeditTxtFechaSolicitud, layoutPlazo, layoutFrecuencia, layOutDestinoCredito, layOutProductosCredito, layOutTxtCurp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_credito);
        cargaComponentes();
        Bundle bHeader = new Bundle();
        Bundle recepcion = getIntent().getExtras();
        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            curpCliente = (recepcion.getString(labelCurp));
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);

            if (getIntent().hasExtra(esSmsVerificado)) {
                smsValidado = recepcion.getBoolean(esSmsVerificado);
            } else {
                smsValidado = true;
            }
        }
        bHeader.putSerializable("infoLogIn", responseLogIn);
        bHeader.putString(nombreTit, titulo);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();

        if (!smsValidado) {
            validaSmsBtn.setVisibility(View.VISIBLE);
            validaSmsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                    overridePendingTransition(0, 0);
                }
            });
        } else {
            validaSmsBtn.setVisibility(View.GONE);
        }

        if (curpCliente != null && !curpCliente.equals("")) {
            inputCurp.setFocusable(false);
            inputCurp.setClickable(false);
            inputCurp.setText(curpCliente);
            consultaInformacionCliente(curpCliente);

        }


        txtFechaSolicitud.setFocusable(false);
        calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH)+1;
        anio = calendar.get(Calendar.YEAR);
        txtFechaSolicitud.setText(anio + "-" + return2digits(mes) + "-" + return2digits(dia));
        seekBar.setMax(MAX_SOLCITUD);
        listenerRecompra();
        realizaProyeccion();
        obtenFrecuenciasPago();
        obtenCatalogoProductos();
        obtenDestinoCredito();
        seekBarToChangeval();
        montoCreditoCambia();
        muestraCalendario();


        inputCurp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String txt = s.toString();

                int largo = txt.length();
                if (largo == 18) {
                    curpCliente = txt;
                    consultaInformacionCliente(curpCliente);
                } else {
                    noCliente.setText("");
                    nombreCliente.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


    }

    private void consultaInformacionCliente(String curp) {
        String msg = "Validando Curp...!";
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");

        Call<ResponseCurpClienteSolicitud> call = ApiAdapter.getApiService(responseLogIn.token).getCurpClienteSolicitud(curp);
        call.enqueue(new Callback<ResponseCurpClienteSolicitud>() {
            @Override
            public void onResponse(Call<ResponseCurpClienteSolicitud> call, Response<ResponseCurpClienteSolicitud> response) {
                int code = response.code();
                final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                boolean status = response.isSuccessful();
                if (code == 200 && status) {
                    ResponseCurpClienteSolicitud datos = response.body();
                    if (datos.response.codigo == 200) {
                        nombreCliente.setText(datos.data.nombres.toUpperCase() + " " + datos.data.apellidoPaterno.toUpperCase() + " " + datos.data.apellidoMaterno.toUpperCase());
                        noCliente.setText(String.valueOf(datos.data.idCliente));
                        dialogFragment.dismiss();
                    } else {
                        noCliente.setText("Cliente no encontrado");
                        nombreCliente.setText("Cliente no encontrado");
                        dialogFragment.dismiss();
                        new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.WARNING_TYPE)
                                .setContentText(datos.response.codigo + " " + datos.response.mensaje)
                                .setTitleText("Advertencia")
                                .show();
                    }
                }

                else {
                    new SweetAlertDialog(SolicitudDeCredito.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(SolicitudDeCredito.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCurpClienteSolicitud> call, Throwable t) {
                new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.ERROR_TYPE)
                        .setContentText(t.toString())
                        .setTitleText("Error")
                        .show();
            }
        });
    }

    private void listenerRecompra() {
        esRecompraCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                esRecompra = isChecked;
            }
        });
    }

    private void muestraCalendario() {
        txtFechaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new DatePickerDialog(SolicitudDeCredito.this, R.style.datePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String lMes = return2digits(month + 1);
                        String lDia = return2digits(dayOfMonth);
                        txtFechaSolicitud.setText(year + "-" + lMes + "-" + lDia);

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

    private void montoCreditoCambia() {


        montoCredito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = s.toString();
                montoC = defineValor(text);
                int largo = text.length();
                montoCredito.setSelection(largo);
                seekBar.setProgress(montoC);


            }
        });
    }

    private void seekBarToChangeval() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                    montoC = progress;
                    montoCredito.setText(String.valueOf(progress));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void obtenFrecuenciasPago() {
        Call<ResponseFrecuenciaPago> call = ApiAdapter.getApiService(responseLogIn.token).frecuenciaPago();
        call.enqueue(new Callback<ResponseFrecuenciaPago>() {
            @Override
            public void onResponse(@NonNull Call<ResponseFrecuenciaPago> call, Response<ResponseFrecuenciaPago> response) {
                int code = response.code();
                if (code == 200 && response.isSuccessful()) {
                    ResponseFrecuenciaPago resp = response.body();
                    if (resp.response.codigo == 200) {
                        ArrayList<String> frecuencias = new ArrayList<>();
                        ArrayList<Long> idsFrecuencias = new ArrayList<>();
                        FrecuenciaPago[] frecuenciaPagos = resp.data.frecuenciasPago;
                        for (FrecuenciaPago frecuencia : frecuenciaPagos) {
                            frecuencias.add(frecuencia.nombre);
                            idsFrecuencias.add(frecuencia.idFrecuenciaPago);
                        }

                        ArrayAdapter<String> spinner = new ArrayAdapter<String>(SolicitudDeCredito.this, android.R.layout.simple_spinner_dropdown_item, frecuencias);
                        spinnerFrecuenciaPago.setAdapter(spinner);
                        spinnerFrecuenciaPago.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idFrecuenciaPago = idsFrecuencias.get(position);
                                txtFrecPago.setText(defineFrecuenciaString((int) idFrecuenciaPago));
                            }
                        });


                    }
                    else {
                        new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.WARNING_TYPE)
                                .setContentText(resp.response.codigo + " " + resp.response.mensaje)
                                .setTitleText("Advertencia")
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFrecuenciaPago> call, Throwable t) {
                new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.ERROR_TYPE)
                        .setContentText(t.toString())
                        .setTitleText("Error")
                        .show();
            }
        });
    }

    private void obtenCatalogoProductos() {

        Call<ResponseCatalogoProductos> call = ApiAdapter.getApiService(responseLogIn.token).catalogoProductos();
        call.enqueue(new Callback<ResponseCatalogoProductos>() {
            @Override
            public void onResponse(Call<ResponseCatalogoProductos> call, Response<ResponseCatalogoProductos> response) {
                int code = response.code();
                if (code == 200 && response.isSuccessful()) {
                    ResponseCatalogoProductos resp = response.body();
                    if (resp.response.codigo == 200) {
                        ArrayList<String> productos = new ArrayList<>();
                        ArrayList<Long> idsProductos = new ArrayList<>();
                        ArrayList<Producto> catalogo = resp.data.productos;

                        for (Producto producto : catalogo) {
                            productos.add(producto.nombre);
                            idsProductos.add(producto.idProductosCredito);
                        }
                        ArrayAdapter<String> spinner = new ArrayAdapter<String>(SolicitudDeCredito.this, android.R.layout.simple_spinner_dropdown_item, productos);
                        productosCredito.setAdapter(spinner);
                        productosCredito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idPrdouctoCredito = idsProductos.get(position);
                            }
                        });


                    } else {
                        new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.WARNING_TYPE)
                                .setContentText(resp.response.codigo + " " + resp.response.mensaje)
                                .setTitleText("Advertencia")
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCatalogoProductos> call, Throwable t) {
                new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.ERROR_TYPE)
                        .setContentText(t.toString())
                        .setTitleText("Error")
                        .show();
            }
        });


    }

    private void obtenDestinoCredito() {
        Call<ResponseDestinoCredito> call = ApiAdapter.getApiService(responseLogIn.token).destinoCreditos();
        call.enqueue(new Callback<ResponseDestinoCredito>() {
            @Override
            public void onResponse(Call<ResponseDestinoCredito> call, Response<ResponseDestinoCredito> response) {
                int code = response.code();
                if (code == 200 && response.isSuccessful()) {
                    ResponseDestinoCredito resp = response.body();

                    if (resp.response.codigo == 200) {
                        ArrayList<String> destino = new ArrayList<>();
                        ArrayList<Long> idsDestino = new ArrayList<>();
                        DestinosCredito[] destinosCreditos = resp.data.destinosCredito;
                        for (DestinosCredito destinoC : destinosCreditos) {
                            destino.add(destinoC.nombre);
                            idsDestino.add(destinoC.idDestinoCredito);
                        }

                        ArrayAdapter<String> spinner = new ArrayAdapter<String>(SolicitudDeCredito.this, android.R.layout.simple_spinner_dropdown_item, destino);
                        destinoCredito.setAdapter(spinner);
                        destinoCredito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDestinoCredito = idsDestino.get(position);
                            }
                        });
                    }else{
                        new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.WARNING_TYPE)
                                .setContentText(resp.response.codigo + " " + resp.response.mensaje)
                                .setTitleText("Advertencia")
                                .show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDestinoCredito> call, Throwable t) {
                new SweetAlertDialog(SolicitudDeCredito.this, SweetAlertDialog.ERROR_TYPE)
                        .setContentText(t.toString())
                        .setTitleText("Error")
                        .show();
            }
        });
    }

    private String defineFrecuenciaString(int idFrec) {
        String frec = "";
        switch (idFrec) {
            case 1:
                frec = "Catorcenas";
                break;
            case 2:
                frec = "Meses";
                break;
            case 3:
                frec = "Semanas";
                break;


        }

        return frec;
    }

    private int defineValor(String s) {
        String v = s.replaceAll("[\\D]", "");
        int value;
        if (v.equals("")) {
            value = 0;
        } else {
            value = Integer.parseInt(v);

        }
        return value;
    }

    private void capturaDatos() {
        requestSolicitudCredito.setData(new DataRequestSolicitudCredito());
        DataRequestSolicitudCredito dataRequestSolicitudCredito = requestSolicitudCredito.getData();

        dataRequestSolicitudCredito.setClienteID(noCliente.getText().toString());
        dataRequestSolicitudCredito.setRevolvente(esRecompra);
        dataRequestSolicitudCredito.setPlazo(plazo.getText().toString());
        dataRequestSolicitudCredito.setFrecuenciaPagoID(String.valueOf(idFrecuenciaPago));
        dataRequestSolicitudCredito.setProductoCreditoID(String.valueOf(idPrdouctoCredito));
        dataRequestSolicitudCredito.setDestinoCreditoID(String.valueOf(idDestinoCredito));
        dataRequestSolicitudCredito.setFechaSolicitud(txtFechaSolicitud.getText().toString());
        dataRequestSolicitudCredito.setMonto(String.valueOf(montoC));
        dataRequestSolicitudCredito.setAsesorId(String.valueOf(responseLogIn.usuarioId));


        requestProyeccion.setData(new DataRequestProyeccion());
        requestProyeccion.getData().setNumeroCuotas(plazo.getText().toString());
        requestProyeccion.getData().setFrecuenciaID(String.valueOf(idFrecuenciaPago));
        requestProyeccion.getData().setMontoCredito(String.valueOf(montoC));
        requestProyeccion.getData().setProductoId(String.valueOf(idPrdouctoCredito));
        requestProyeccion.getData().setClienteId(noCliente.getText().toString());


    }


    private boolean validaInfo() {

        String curp = inputCurp.getText().toString();
        String destino = destinoCredito.getText().toString();
        String frecuencia = spinnerFrecuenciaPago.getText().toString();
        String producto = inputCurp.getText().toString();

        Log.d("valiadciones", "curp: " + curp);
        Log.d("valiadciones", "destino: " + destino);
        Log.d("valiadciones", "frecuencia: " + frecuencia);
        Log.d("valiadciones", "producto: " + producto);


        boolean status = true;
        if (montoC == 0) {
            status = false;
            montoCredito.setError("El monto es necesario");
        }

        if (curp.equals("")) {
            status = false;
            layOutTxtCurp.setError("Curp necesaria");
        } else {
            layOutTxtCurp.setErrorEnabled(false);
        }
        if (destino.equals("")) {
            status = false;
            layOutDestinoCredito.setError("Curp necesaria");
        } else {
            layOutDestinoCredito.setErrorEnabled(false);
        }
        if (frecuencia.equals("")) {
            status = false;
            layoutFrecuencia.setError("Frecuencia necesaria");
        } else {
            layoutFrecuencia.setErrorEnabled(false);
        }
        if ((producto).equals("")) {
            status = false;
            layOutProductosCredito.setError("Curp necesaria");
        } else {
            layOutProductosCredito.setErrorEnabled(false);
        }

        if ((plazo.getText().toString()).equals("")) {
            status = false;
            layoutPlazo.setError("Plazo necesario");
        } else {
            layoutPlazo.setErrorEnabled(false);
        }


        return status;

    }

    private void realizaProyeccion() {

        siguiente.setOnClickListener(v -> {
            String n = nombreCliente.getText().toString();
            String c = noCliente.getText().toString();
            Intent solicitudCreditoScreenIntent = new Intent(SolicitudDeCredito.this, ProyeccionCuotas.class);
            Bundle sender = new Bundle();

            if (validaInfo()) {
                capturaDatos();
                sender.putSerializable(N_REQ_PROYEC, requestProyeccion);
                sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
                sender.putString("nombre", n.toUpperCase());
                sender.putSerializable(INFO_USER,responseLogIn);

                sender.putString("idCliente", c);
                sender.putString(nombreTit, titulo);
                solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                solicitudCreditoScreenIntent.putExtras(sender);
                startActivity(solicitudCreditoScreenIntent);
            }


        });
    }


    private void cargaComponentes() {
        siguiente = findViewById(R.id.btnSiguiente);
        nombreCliente = findViewById(R.id.nombreCliente);
        noCliente = findViewById(R.id.noCliente);
        destinoCredito = findViewById(R.id.destinoCredito);
        spinnerFrecuenciaPago = findViewById(R.id.spinnerFrecuenciaPago);
        productosCredito = findViewById(R.id.productosCredito);
        seekBar = findViewById(R.id.seekBar);
        montoCredito = findViewById(R.id.montoCredito);
        txtFechaSolicitud = findViewById(R.id.txtFechaSolicitud);
        txtFrecPago = findViewById(R.id.txtFrecPago);
        esRecompraCheck = findViewById(R.id.esRecompraCheck);
        validaSmsBtn = findViewById(R.id.validaSmsBtn);
        plazo = findViewById(R.id.plazo);
        inputCurp = findViewById(R.id.inputCurp);


        layOutTeditTxtFechaSolicitud = findViewById(R.id.layOutTeditTxtFechaSolicitud);
        layoutPlazo = findViewById(R.id.layoutPlazo);
        layoutFrecuencia = findViewById(R.id.layoutFrecuencia);
        layOutDestinoCredito = findViewById(R.id.layOutDestinoCredito);
        layOutProductosCredito = findViewById(R.id.layOutProductosCredito);
        layOutTxtCurp = findViewById(R.id.layOutTxtCurp);

    }

}