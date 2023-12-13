package com.amextra.SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amextra.AltaEdicionCliente.DatosClienteLab;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqSms;
import com.amextra.io.Request.RequestEnvioSMS;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseEnvioSMS;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseLogin;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnviaSMS extends AppCompatActivity {
    LinearProgressIndicator loader;
    Button btnCancela,btnContinua;

    InfoUSer responseLogIn = new InfoUSer();
    String numeroTelefonoCliente ="", curp ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envia_sms);
        loader = findViewById(R.id.loader);
        btnContinua = findViewById(R.id.btnContinua);
        btnCancela = findViewById(R.id.btnCancela);
        loader.hide();

        Bundle recepcion = getIntent().getExtras();
        if(recepcion!= null){
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            numeroTelefonoCliente =  recepcion.getString("telefono");
            curp =  recepcion.getString("curp");

        }

        btnContinua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaSMS();

            }
        });

        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(0,0);
            }
        });

    }

    private void enviaSMS(){
        DialogFragment dialogFragment =  LoaderTransparent.loaderTransparent("Enviando SMS");
        dialogFragment.show(getSupportFragmentManager(),"LoaderTransparent");
        Intent confirmaSMS = new Intent(EnviaSMS.this, ConfirmaSMS.class);

        Bundle sender = new Bundle();
        sender.putSerializable("infoLogIn",responseLogIn);
        sender.putSerializable("telefono",numeroTelefonoCliente);
        sender.putSerializable("curp",curp);
        confirmaSMS.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        confirmaSMS.putExtras(sender);

        RequestEnvioSMS requestEnvioSMS = new RequestEnvioSMS();
        requestEnvioSMS.setData(new DataReqSms());
        requestEnvioSMS.getData().setNumeroEnvio("+52"+numeroTelefonoCliente);
        requestEnvioSMS.getData().setCurp(curp);
        Call<ResponseEnvioSMS> call = ApiAdapter.getApiService(responseLogIn.token).enviaSMS(requestEnvioSMS);
        call.enqueue(new Callback<ResponseEnvioSMS>() {
            @Override
            public void onResponse(Call<ResponseEnvioSMS> call, Response<ResponseEnvioSMS> response) {
                ResponseEnvioSMS envioToken = response.body();
                int code = response.code();
                if (response.isSuccessful() && code==200 && envioToken.response.codigo ==200){
                    dialogFragment.dismiss();
                    startActivity(confirmaSMS);
                }else{
                    dialogFragment.dismiss();
                    Toast.makeText(EnviaSMS.this, "Error al enviar sms: " + envioToken.response.codigo + " - " + envioToken.response.mensaje, Toast.LENGTH_SHORT).show();
                    startActivity(confirmaSMS);
                }
            }

            @Override
            public void onFailure(Call<ResponseEnvioSMS> call, Throwable t) {
                Toast.makeText(EnviaSMS.this, "Error al enviar sms: " + t, Toast.LENGTH_SHORT).show();
                dialogFragment.dismiss();
                startActivity(confirmaSMS);
            }
        });
    }
}