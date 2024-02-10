package com.amextra.SMS;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.amextra.MainActivity;
import com.amextra.SolicitudCredito.SolicitudDeCredito;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqSms;
import com.amextra.io.Request.DataResValidaSMS;
import com.amextra.io.Request.RequestEnvioSMS;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Request.RequestValidaSMS;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseEnvioSMS;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseValidaSMS;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmaSMS extends AppCompatActivity {
    String clienteInfo = "INFO_CLIENT";
    String labelCurp = "CURP_CLIENTE";
    String esSmsVerificado = "VERIFICA_SMS";
    boolean verificaSms = false;
    Button validaCodigo;
    TextView reenviaCodigo;
    EditText txtTkn1,nip;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    String nombreTit = "Titulo";
    InfoUSer responseLogIn = new InfoUSer();

    CheckBox checkConfirm;

    boolean confirm;
    String numeroTelefonoCliente = "";
    String curpCliente = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_sms);
        validaCodigo = findViewById(R.id.validaCodigo);
        reenviaCodigo = findViewById(R.id.reenviaCodigo);
        txtTkn1 = findViewById(R.id.txtTkn1);
        nip = findViewById(R.id.nip);
        checkConfirm = findViewById(R.id.checkConfirm);


        Bundle recepcion = getIntent().getExtras();
        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            numeroTelefonoCliente =  recepcion.getString("telefono");
            curpCliente =  recepcion.getString("curp");

        }
        generaValidacionToken();
        checkConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                confirm = isChecked;
            }
        });

        reenviaCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviaSMS(numeroTelefonoCliente);
            }
        });
    }


    private void generaValidacionToken() {
        validaCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tkn1 = txtTkn1.getText().toString();
                validaToken(tkn1, numeroTelefonoCliente, curpCliente);


            }
        });
    }


    private void validaToken(String token, String numero, String curp) {
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Validando SMS");
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Intent toSolicitudCredito = new Intent(ConfirmaSMS.this, SolicitudDeCredito.class);

        boolean codeToken = ((txtTkn1.getText().toString()).length()) == 5 ;
        boolean codeNips = ((nip.getText().toString()).length()) == 4;
        if( !codeToken || !codeNips || !confirm ||
                (txtTkn1.getText().toString()).equals("") ||
                (nip.getText().toString()).equals("") )



        {
            dialogFragment.dismiss();
            new SweetAlertDialog(ConfirmaSMS.this, SweetAlertDialog.WARNING_TYPE)
                    .setContentText("Los campos: Codigo y NIP deben ser llenados para poder continuar, tambien se debe autorizar la consulta de buro de crèdito.")
                    .setTitleText("Advertencia")
                    .show();
        }else{
            RequestValidaSMS requestValidaSMS = new RequestValidaSMS();
            requestValidaSMS.setData(new DataResValidaSMS());
            requestValidaSMS.getData().setCodigo(token);
            requestValidaSMS.getData().setNumeroEnvio("+52" + numero);
            requestValidaSMS.getData().setCurp(curp);
            requestValidaSMS.getData().setNip(nip.getText().toString());


            Call<ResponseValidaSMS> call = ApiAdapter.getApiService(responseLogIn.token).validaSMS(requestValidaSMS);
            call.enqueue(new Callback<ResponseValidaSMS>() {
                @Override
                public void onResponse(Call<ResponseValidaSMS> call, Response<ResponseValidaSMS> response) {
                    int code = response.code();
                    boolean status = response.isSuccessful();
                    if(code == 200 && status){
                        ResponseValidaSMS info = response.body();
                        if(info.response.codigo == 200){
                            dialogFragment.dismiss();
                            cargaSolicitud(toSolicitudCredito, curp, true);
                        }else if(!info.response.mensaje.equals("ERROR, el pin ingresado no es valido")){

                            dialogFragment.dismiss();
                            cargaSolicitud(toSolicitudCredito, curp, false);
                        }
                        else{
                            dialogFragment.dismiss();
                            new SweetAlertDialog(ConfirmaSMS.this, SweetAlertDialog.WARNING_TYPE)
                                    .setContentText("Verificaciòn de SMS : "+info.response.codigo+" - "+info.response.mensaje)
                                    .setTitleText("Advertencia")
                                    .show();
                        }
                    }

                    else {
                        final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                        new SweetAlertDialog(ConfirmaSMS.this,SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText(alertText)
                                .setConfirmText("Continuar")
                                .setConfirmClickListener(sweetAlertDialog -> {
                                    finish();
                                    Intent login = new Intent(ConfirmaSMS.this, MainActivity.class);
                                    login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(login);
                                })
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseValidaSMS> call, Throwable t) {
                    dialogFragment.dismiss();
                    new SweetAlertDialog(ConfirmaSMS.this, SweetAlertDialog.ERROR_TYPE)
                            .setContentText("Verificaciòn de SMS : "+t)
                            .setTitleText("Error")
                            .show();
                }
            });
        }

    }


    private void cargaSolicitud(Intent intent, String curp, boolean sms) {
        Bundle bundle = new Bundle();
        bundle.clear();
        bundle.putString(labelCurp, curp);
        bundle.putBoolean(esSmsVerificado, sms);
        bundle.putString(nombreTit, "Solicitud de Crédito");
        bundle.putSerializable("infoLogIn", responseLogIn);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void enviaSMS(String numero) {
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Enviando SMS");
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        RequestEnvioSMS requestEnvioSMS = new RequestEnvioSMS();
        requestEnvioSMS.setData(new DataReqSms());
        requestEnvioSMS.getData().setNumeroEnvio("+52" + numero);
        requestEnvioSMS.getData().setCurp(curpCliente);
        Call<ResponseEnvioSMS> call = ApiAdapter.getApiService(responseLogIn.token).enviaSMS(requestEnvioSMS);
        call.enqueue(new Callback<ResponseEnvioSMS>() {
            @Override
            public void onResponse(Call<ResponseEnvioSMS> call, Response<ResponseEnvioSMS> response) {
                ResponseEnvioSMS envioToken = response.body();
                int code = response.code();
                if (response.isSuccessful() && code == 200 && envioToken.response.codigo == 200) {
                    dialogFragment.dismiss();
                } else {
                    dialogFragment.dismiss();
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(ConfirmaSMS.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(ConfirmaSMS.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEnvioSMS> call, Throwable t) {
                dialogFragment.dismiss();
            }
        });
    }
}