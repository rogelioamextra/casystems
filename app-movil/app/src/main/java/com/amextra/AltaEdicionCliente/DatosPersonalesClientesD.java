package com.amextra.AltaEdicionCliente;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.MainActivity;
import com.amextra.MenuHomeScreen;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.Political;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.GradosEscolare;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResValidaTelefono;
import com.amextra.io.Response.ResponseCatalogoGradosEscolares;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPersonalesClientesD extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {

    Button siguiente;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;

    String LABEL_REGISTRADO = "ya se encuentra registrado";
    String REQ_ALTA_CLI = "reqAltaCliente";
    long idDescMaxEstudios;
    String descMaxEstudios;
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    Bundle mBundle = new Bundle();
    Bundle bHeader = new Bundle();

    AutoCompleteTextView spinerTxtGradoMaxEstudios;

    TextInputEditText txtDependientesEc, txtNumCelular, txtEmail, txtjobPolitical;
    TextInputLayout layOutEmail, layOutTelefono, layOutGradoEstudios, layOutDescJobPolitic;


    CheckBox isPolitical;

    InfoUSer responseLogIn = new InfoUSer();

    Political political = new Political();

    boolean checkPolitic = false;
    String jobPolitical = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAdjustScreen();
        setContentView(R.layout.activity_datos_personales_clientes_d);
        Bundle recepcion = getIntent().getExtras();

        isPolitical = findViewById(R.id.isPolitical);
        spinerTxtGradoMaxEstudios = findViewById(R.id.spinerTxtGradoMaxEstudios);
        txtDependientesEc = findViewById(R.id.txtDependientesEc);
        txtEmail = findViewById(R.id.txtEmail);
        txtNumCelular = findViewById(R.id.txtNumCelular);
        siguiente = findViewById(R.id.btnSiguiente);
        layOutEmail = findViewById(R.id.layOutEmail);
        layOutTelefono = findViewById(R.id.layOutTelefono);
        layOutGradoEstudios = findViewById(R.id.layOutGradoEstudios);
        layOutDescJobPolitic = findViewById(R.id.layOutDescJobPolitic);
        txtjobPolitical = findViewById(R.id.txtjobPolitical);
        isPolitical.setChecked(checkPolitic);
        layOutDescJobPolitic.setEnabled(checkPolitic);


        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            geolocalizacion = (Geolocalizacion) recepcion.getSerializable("geo");
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
            if (requestInsertClient.data.persona.gradoMaximoEstudiosId != 0) {
                if (requestInsertClient.data.persona.dependientesEconomicos != 0) {
                    txtDependientesEc.setText(String.valueOf(requestInsertClient.data.persona.dependientesEconomicos));
                }
                txtNumCelular.setText(requestInsertClient.data.persona.telefono);
                txtEmail.setText(requestInsertClient.data.persona.email);
                checkPolitic = requestInsertClient.getData().getPersona().getPolitical().isPolitical();
                jobPolitical = requestInsertClient.getData().getPersona().getPolitical().getJobDescription();

                layOutDescJobPolitic.setEnabled(checkPolitic);

                txtjobPolitical.setText(jobPolitical);
                political.setPolitical(checkPolitic);
                political.setJobDescription(jobPolitical);
                isPolitical.setChecked(checkPolitic);

            }

        }
        isPolitical.setChecked(checkPolitic);
        txtChangeMovil();


        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if (!email.equals("")) {
                    if (!pattern.matcher(email).matches()) {
                        layOutEmail.setError("El texto ingresado no es un correo electronico");
                    } else {
                        layOutEmail.setErrorEnabled(false);
                    }
                } else {
                    layOutEmail.setError("El texto ingresado no es un correo electronico");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 2);
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

        getCatalogoMaximoEstudios();
        aRegistroDireccion();


        isPolitical.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkPolitic = isChecked;
            political.setPolitical(isChecked);
            layOutDescJobPolitic.setEnabled(isChecked);
            if (!isChecked) {
                txtjobPolitical.setText("");
            }
        });

        txtjobPolitical.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                jobPolitical = s.toString();
                political.setJobDescription(jobPolitical);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void txtChangeMovil() {
        txtNumCelular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String movil = s.toString();
                if (movil.length() == 10) {
                    validaTelfono(movil);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void validaTelfono(String telefono) {
        Call<ResValidaTelefono> call = ApiAdapter.getApiService(responseLogIn.token).validaNumeroTelfono(telefono);
        call.enqueue(new Callback<ResValidaTelefono>() {
            @Override
            public void onResponse(Call<ResValidaTelefono> call, Response<ResValidaTelefono> response) {
                int estado = response.code();
                boolean complete = response.isSuccessful();

                if (estado == 200 && complete) {
                    ResValidaTelefono telefono = response.body();
                    if (telefono.response.mensaje.contains(LABEL_REGISTRADO)) {
                        SweetAlertDialog dialog = new SweetAlertDialog(DatosPersonalesClientesD.this, SweetAlertDialog.ERROR_TYPE);
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });
                        dialog.setConfirmText("Continuar");
                        dialog.setTitleText("");
                        dialog.setContentText(telefono.response.mensaje.toUpperCase());
                        dialog.show();
                    } else {
                        SweetAlertDialog dialogoK = new SweetAlertDialog(DatosPersonalesClientesD.this, SweetAlertDialog.SUCCESS_TYPE);
                        dialogoK.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialogoK.dismissWithAnimation();

                            }
                        });
                        dialogoK.setConfirmText("Continuar");
                        dialogoK.setTitleText("");
                        dialogoK.setContentText(telefono.response.mensaje.toUpperCase());
                        dialogoK.show();
                    }
                } else {
                    final String alertText = (estado == 400 || estado == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(DatosPersonalesClientesD.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(DatosPersonalesClientesD.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResValidaTelefono> call, Throwable t) {

            }
        });
    }

    private void toMenu() {
        Bundle sender = new Bundle();
        Intent menuHome = new Intent(DatosPersonalesClientesD.this, MenuHomeScreen.class);
        sender.putSerializable("geo", geolocalizacion);
        sender.putBoolean(nombreStatus, esAlta);
        sender.putSerializable("infoLogIn", responseLogIn);
        menuHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        menuHome.putExtras(sender);
        startActivity(menuHome);
    }

    private void getCatalogoMaximoEstudios() {
        Call<ResponseCatalogoGradosEscolares> call = ApiAdapter.getApiService(responseLogIn.token).gradosEscolares();
        call.enqueue(new Callback<ResponseCatalogoGradosEscolares>() {
            @Override
            public void onResponse(Call<ResponseCatalogoGradosEscolares> call, Response<ResponseCatalogoGradosEscolares> response) {
                int code = response.code();
                ResponseCatalogoGradosEscolares info = response.body();
                if (response.isSuccessful()) {
                    if (code == 200) {

                        List<String> listaNueva = new ArrayList<String>();
                        List<String> listaNuevaIds = new ArrayList<String>();

                        GradosEscolare[] gradosEscolares = info.data.gradosEscolares;
                        for (GradosEscolare grado : gradosEscolares) {
                            listaNueva.add(grado.nombre.toUpperCase());
                            listaNuevaIds.add(String.valueOf(grado.idGradoEstudios));

                        }

                        if (requestInsertClient.data.persona.gradoMaximoEstudiosId != 0) {
                            Long idMaxEst = requestInsertClient.data.persona.gradoMaximoEstudiosId;
                            for (GradosEscolare grado : gradosEscolares) {
                                if (grado.idGradoEstudios == idMaxEst) {
                                    spinerTxtGradoMaxEstudios.setText(grado.nombre.toUpperCase());
                                    idDescMaxEstudios = idMaxEst;
                                    break;
                                }

                            }
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosPersonalesClientesD.this, android.R.layout.simple_spinner_dropdown_item, listaNueva);
                        spinerTxtGradoMaxEstudios.setAdapter(spinnerArrayAdapter);
                        spinerTxtGradoMaxEstudios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescMaxEstudios = Long.parseLong(listaNuevaIds.get(position));
                                descMaxEstudios = listaNueva.get(position);
                            }
                        });
                    }
                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(DatosPersonalesClientesD.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(DatosPersonalesClientesD.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponseCatalogoGradosEscolares> call, Throwable t) {

            }
        });
    }


    private void aRegistroDireccion() {

        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent screenFormsDireccion = new Intent(DatosPersonalesClientesD.this, datos_personales_address.class);

            if (validaInfo()) {

                recopilaDatos();
                boolean status = (receptor.getBoolean(nombreStatus));
                sender.putString(nombreTit, titulo);
                sender.putBoolean(nombreStatus, status);
                sender.putSerializable("geo", geolocalizacion);
                sender.putSerializable("infoLogIn", responseLogIn);
                sender.putSerializable("reqAltaCliente", requestInsertClient);
                screenFormsDireccion.putExtras(sender);
                screenFormsDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(screenFormsDireccion);

            }
        });
    }

    private void recopilaDatos() {
        DataReqCliente dataReqCliente = requestInsertClient.getData();
        if ((txtDependientesEc.getText().toString()).equals("")) {
            dataReqCliente.getPersona().setDependientesEconomicos(0);
        } else {
            dataReqCliente.getPersona().setDependientesEconomicos(Integer.parseInt(txtDependientesEc.getText().toString()));
        }

        dataReqCliente.getPersona().setGradoMaximoEstudiosID(idDescMaxEstudios);
        dataReqCliente.getPersona().setTelefono(txtNumCelular.getText().toString());
        dataReqCliente.getPersona().setEmail(txtEmail.getText().toString());
        dataReqCliente.setAsesorId(responseLogIn.usuarioId);
        dataReqCliente.getPersona().setPolitical(political);

        requestInsertClient.setData(dataReqCliente);
    }

    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if (validaInfo()) {
            recopilaDatos();
        }
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);

    }

    private boolean validaInfo() {

        String movil = txtNumCelular.getText().toString();
        String email = txtEmail.getText().toString();
        String grado = spinerTxtGradoMaxEstudios.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        boolean status = true;

        if (grado.equals("")) {
            status = false;
            layOutGradoEstudios.setError("Grado de estudios necesario");
        } else {
            layOutGradoEstudios.setErrorEnabled(false);
        }

        if (movil.equals("") || movil.length() < 10) {
            status = false;
            layOutTelefono.setError("Telefono requerido");
        } else {
            layOutTelefono.setErrorEnabled(false);
        }
        if (email.equals("") || !pattern.matcher(email).matches()) {
            layOutEmail.setError("El texto ingresado no es un correo electronico");
            status = false;
        } else {
            layOutEmail.setErrorEnabled(false);
        }


        if (checkPolitic && jobPolitical.isBlank()) {
            layOutDescJobPolitic.setError("Se debe especificar el cargo politico");
            status = false;
        } else {
            layOutDescJobPolitic.setErrorEnabled(false);
        }

        return status;
    }
}