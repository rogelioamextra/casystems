package com.amextra.AltaEdicionCliente;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.MainActivity;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.DatosLaborales;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.CaracteristicasNegocio;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.GirosNegocio;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseCaracteristicasNegocios;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseGirosNegocios;
import com.amextra.utils.ConverterReqClient;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosClienteLaborales extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Calendar calendar;
    DatePickerDialog picker;
    Button siguiente, btnCalendario;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    int dia, mes, anio;
    long idDescEmpresa, idDescGiroEmpresaNegocio;
    TextInputEditText txtTelefonoEmpresa, txtPuesto, txtFechaIngreso;
    AutoCompleteTextView spinGiroEmpresaNegocio, spinTxtEmpresaNegocio;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    boolean reciboNomina;
    Bundle mBundle = new Bundle();
    TextInputLayout layOutEmpresa, layOutGiro,
            layOutTeditTxtFechaIngreso, layOutPuesto, layOutTelefono;

    MaterialSwitch switchRecNomina;
    InfoUSer responseLogIn = new InfoUSer();
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    Bundle bHeader = new Bundle();
    String REQ_ALTA_CLI = "reqAltaCliente";
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    String clienteInfo = "INFO_CLIENT";

    boolean existeInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente_laborales);
        setAdjustScreen();

        siguiente = findViewById(R.id.siguiente);
        spinGiroEmpresaNegocio = findViewById(R.id.spinGiroEmpresaNegocio);
        spinTxtEmpresaNegocio = findViewById(R.id.spinTxtEmpresaNegocio);
        txtTelefonoEmpresa = findViewById(R.id.txtTelefonoEmpresa);
        txtPuesto = findViewById(R.id.txtPuesto);
        txtFechaIngreso = findViewById(R.id.txtFechaIngreso);
        layOutEmpresa = findViewById(R.id.layOutEmpresa);
        layOutGiro = findViewById(R.id.layOutGiro);
        layOutTeditTxtFechaIngreso = findViewById(R.id.layOutTeditTxtFechaIngreso);
        layOutPuesto = findViewById(R.id.layOutPuesto);
        layOutTelefono = findViewById(R.id.layOutTelefono);
        switchRecNomina = findViewById(R.id.switchRecNomina);


        Bundle recepcion = getIntent().getExtras();
        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            esAlta = recepcion.getBoolean(nombreStatus);
            titulo = (recepcion.getString(nombreTit));
            if (esAlta) {
                requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);

                if (requestInsertClient.data != null) {
                    if (requestInsertClient.data.datosLaborales != null) {
                        mapDataClient(requestInsertClient.data);
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
                    if (requestInsertClient.data != null) {
                        if (requestInsertClient.data.datosLaborales != null) {
                            existeInfo = true;
                            mapDataClient(requestInsertClient.data);
                        }
                    }
                }
                if (getIntent().hasExtra(REQ_ALTA_CLI)) {
                    requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                    if (requestInsertClient.data != null) {
                        if (requestInsertClient.data.datosLaborales != null) {
                            existeInfo = true;
                            mapDataClient(requestInsertClient.data);
                        }
                    }
                }
            }
        }


        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 5);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        mBundle.putSerializable("infoLogIn", responseLogIn);


        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        bHeader.putSerializable("geo", geolocalizacion);

        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();


        calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH) + 1;
        anio = calendar.get(Calendar.YEAR);

        switchRecNomina.setChecked(reciboNomina);
        switchRecNomina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                reciboNomina = isChecked;
            }
        });

        terminaRegistroLaboral();
        getListaGiroNegocio();
        getCaracteristicasdeNegocios();
        muestraCalendario();


    }

    private void mapDataClient(DataReqCliente data) {

        txtFechaIngreso.setText(data.datosLaborales.fechaIngreso);
        txtPuesto.setText(data.datosLaborales.puesto);
        txtTelefonoEmpresa.setText(data.datosLaborales.telefono);
        reciboNomina = data.datosLaborales.recibosNomina;
    }

    private void muestraCalendario() {
        txtFechaIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new DatePickerDialog(DatosClienteLaborales.this, R.style.datePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String lMes = return2digits(month + 1);
                        String lDia = return2digits(dayOfMonth);
                        txtFechaIngreso.setText(year + "-" + lMes + "-" + lDia);

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

    private void getCaracteristicasdeNegocios() {
        Call<ResponseCaracteristicasNegocios> call = ApiAdapter.getApiService(responseLogIn.token).caracteristicasNegocios();
        call.enqueue(new Callback<ResponseCaracteristicasNegocios>() {
            @Override
            public void onResponse(Call<ResponseCaracteristicasNegocios> call, Response<ResponseCaracteristicasNegocios> response) {
                int code = response.code();
                ResponseCaracteristicasNegocios info = response.body();
                List<String> listCarateristicas = new ArrayList<>();
                List<Long> idsGiro = new ArrayList<>();
                if (code == 200) {
                    CaracteristicasNegocio[] caratesticas = info.data.caracteristicasNegocio;
                    for (CaracteristicasNegocio caracteristica : caratesticas) {
                        listCarateristicas.add(caracteristica.nombre.toUpperCase());
                        idsGiro.add(caracteristica.idCaracteristicaNegocio);
                    }

                    if (existeInfo) {
                        Long idCaracteristica = requestInsertClient.data.datosLaborales.caracteristicasNegocioId;
                        for (CaracteristicasNegocio caracteristica : caratesticas) {
                            if (idCaracteristica == caracteristica.idCaracteristicaNegocio) {
                                idDescEmpresa = idCaracteristica;
                                spinTxtEmpresaNegocio.setText(caracteristica.nombre);
                                break;
                            }

                        }
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosClienteLaborales.this, android.R.layout.simple_spinner_dropdown_item, listCarateristicas);
                    spinTxtEmpresaNegocio.setAdapter(spinnerArrayAdapter);
                    spinTxtEmpresaNegocio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idDescEmpresa = idsGiro.get(position);

                        }
                    });
                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(DatosClienteLaborales.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(DatosClienteLaborales.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }


            }

            @Override
            public void onFailure(Call<ResponseCaracteristicasNegocios> call, Throwable t) {

            }
        });
    }

    private void getListaGiroNegocio() {
        Call<ResponseGirosNegocios> call = ApiAdapter.getApiService(responseLogIn.token).giroNegocio();
        call.enqueue(new Callback<ResponseGirosNegocios>() {
            @Override
            public void onResponse(Call<ResponseGirosNegocios> call, Response<ResponseGirosNegocios> response) {
                int code = response.code();
                ResponseGirosNegocios info = response.body();
                List<String> listaGirosNegocio = new ArrayList<>();
                List<String> IDNegocio = new ArrayList<>();
                if (code == 200) {
                    GirosNegocio[] girosNegocios = info.data.girosNegocio;
                    for (GirosNegocio giro : girosNegocios) {
                        listaGirosNegocio.add(giro.nombre.toUpperCase());
                        IDNegocio.add(String.valueOf(giro.idGirosNegocio));
                    }

                    if (existeInfo) {
                        Long idCaracteristica = requestInsertClient.data.datosLaborales.giroNegocioId;
                        for (GirosNegocio giro : girosNegocios) {
                            if (idCaracteristica == giro.idGirosNegocio) {
                                idDescGiroEmpresaNegocio = idCaracteristica;
                                spinGiroEmpresaNegocio.setText(giro.nombre);
                                break;
                            }

                        }
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosClienteLaborales.this, android.R.layout.simple_spinner_dropdown_item, listaGirosNegocio);
                    spinGiroEmpresaNegocio.setAdapter(spinnerArrayAdapter);
                    spinGiroEmpresaNegocio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idDescGiroEmpresaNegocio = Long.parseLong(IDNegocio.get(position));
                        }
                    });
                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(DatosClienteLaborales.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(DatosClienteLaborales.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponseGirosNegocios> call, Throwable t) {

            }
        });
    }

    private void terminaRegistroLaboral() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent consultaInfDireccion = new Intent(DatosClienteLaborales.this, DatosClienteLab.class);
            boolean status = (receptor.getBoolean(nombreStatus));
            if (validaInformacion()) {
                capturaInformacion();
                sender.putSerializable("infoLogIn", responseLogIn);
                sender.putString(nombreTit, titulo);
                sender.putBoolean(nombreStatus, status);
                sender.putSerializable("reqAltaCliente", requestInsertClient);
                consultaInfDireccion.putExtras(sender);
                consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(consultaInfDireccion);
            }
        });
    }

    private void capturaInformacion() {
        DataReqCliente dataReqCliente = requestInsertClient.getData();
        if (!existeInfo) {
            dataReqCliente.setDatosLaborales(new DatosLaborales());
            dataReqCliente.getDatosLaborales().setTelefono(txtTelefonoEmpresa.getText().toString());
            dataReqCliente.getDatosLaborales().setGiroNegocioID(idDescGiroEmpresaNegocio);
            dataReqCliente.getDatosLaborales().setCaracteristicasNegocioId(idDescEmpresa);
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);

            dataReqCliente.getDatosLaborales().setPuesto(txtPuesto.getText().toString());
            dataReqCliente.getDatosLaborales().setRecibosNomina(reciboNomina);
            dataReqCliente.getDatosLaborales().setFechaIngreso(txtFechaIngreso.getText().toString());
            requestInsertClient.setData(dataReqCliente);
        } else {
            dataReqCliente.getDatosLaborales().setTelefono(txtTelefonoEmpresa.getText().toString());
            dataReqCliente.getDatosLaborales().setGiroNegocioID(idDescGiroEmpresaNegocio);
            dataReqCliente.getDatosLaborales().setCaracteristicasNegocioId(idDescEmpresa);
            dataReqCliente.getDatosLaborales().setPuesto(txtPuesto.getText().toString());
            dataReqCliente.getDatosLaborales().setRecibosNomina(reciboNomina);
            dataReqCliente.getDatosLaborales().setFechaIngreso(txtFechaIngreso.getText().toString());
        }

    }

    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }


    private boolean validaInformacion() {
        boolean status = true;

        String tel = txtTelefonoEmpresa.getText().toString();
        String puesto = txtPuesto.getText().toString();
        String fecha = txtFechaIngreso.getText().toString();
        String empresa = spinTxtEmpresaNegocio.getText().toString();
        String giro = spinGiroEmpresaNegocio.getText().toString();


        if (tel.length() < 10) {
            status = false;
            layOutTelefono.setError("El numero telefonico es necesario");
        } else {
            layOutTelefono.setErrorEnabled(false);
        }
        if (puesto.equals("")) {
            status = false;
            layOutPuesto.setError("Puesto requerido");
        } else {
            layOutPuesto.setErrorEnabled(false);
        }


        if (fecha.equals("")) {
            status = false;
            layOutTeditTxtFechaIngreso.setError("Fecha de ingreso necesaria");
        } else {
            layOutTeditTxtFechaIngreso.setErrorEnabled(false);
        }


        if (empresa.equals("")) {
            status = false;
            layOutEmpresa.setError("La empresa es necesaria");
        } else {
            layOutEmpresa.setErrorEnabled(false);
        }

        if (giro.equals("")) {
            status = false;
            layOutGiro.setError("El giro de la empresa es necesario");
        } else {
            layOutGiro.setErrorEnabled(false);
        }

        return status;
    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {

        if (validaInformacion()) {

            capturaInformacion();

        }
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);


    }


}