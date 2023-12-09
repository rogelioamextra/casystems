package com.amextra.SolicitudCredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuSolicitudCredito;
import com.amextra.io.Request.DataRequestSolicitudCredito;
import com.amextra.io.Request.Egresos_;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Patrimonio;
import com.amextra.io.Response.ResponseLogin;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Egresos extends AppCompatActivity implements MenuSolicitudCredito.TransfiereDatos{
    Button siguiente;
    RadioButton radioEgresos;
    String nombreStatus = "esAlta";
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    String N_REQ_SOL_CRED = "REQSOLCRED";
    String INFO_USER = "infoLogIn";
    TextInputEditText montoAlimentos, montoRenta, montoGastosEscuela, montoTelefono, montoLuz, montoAgua,
            montoGas, montoTransporte, montoVestido, montoEsparcimiento, montoReparaciones;
    double montoDoAlimentos =0,montoDoRenta=0,montoDoGastosEscuela=0,montoDoTelefono=0,montoDoLuz=0;
    double montoDoAgua =0,montoDoGas=0,montoDoTransporte=0,montoDoVestido=0,montoDoEsparcimiento=0,montoDoReparaciones=0,totalGeneral=0;
    TextView montoTotal;
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();

    final androidx.fragment.app.FragmentManager mFragmentPat= getSupportFragmentManager();
    final MenuSolicitudCredito menuPat = new MenuSolicitudCredito();
    final androidx.fragment.app.FragmentTransaction mFragmentTransactPat = mFragmentPat.beginTransaction();

    Bundle bTransact = new Bundle();
    InfoUSer responseLogIn ;
    Bundle bHeader = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egresos);
        Bundle recepcion = getIntent().getExtras();

        generaCargaInicial();
        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);
            titulo = (recepcion.getString(nombreTit));
            if(getIntent().hasExtra(N_REQ_SOL_CRED)){
                requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
                if(requestSolicitudCredito.data.egresos!=null){
                    montoAlimentos.setText(requestSolicitudCredito.data.egresos.alimentos);
                    montoRenta.setText(requestSolicitudCredito.data.egresos.renta);
                    montoGastosEscuela.setText(requestSolicitudCredito.data.egresos.gastosEscolares);
                    montoTelefono.setText(requestSolicitudCredito.data.egresos.telefono);
                    montoLuz.setText(requestSolicitudCredito.data.egresos.luz);
                    montoAgua.setText(requestSolicitudCredito.data.egresos.agua);
                    montoGas.setText(requestSolicitudCredito.data.egresos.gas);
                    montoTransporte.setText(requestSolicitudCredito.data.egresos.transporteGasolina);
                    montoVestido.setText(requestSolicitudCredito.data.egresos.vestido);
                    montoEsparcimiento.setText(requestSolicitudCredito.data.egresos.esparcimiento);
                    montoReparaciones.setText(requestSolicitudCredito.data.egresos.mantenimientoReparaciones);
                    generaCalculo();
                }
            }
        }


        bTransact.putSerializable(N_REQ_SOL_CRED,requestSolicitudCredito);
        bTransact.putInt("itm",1);
        bTransact.putString(nombreTit,titulo);

        bHeader.putString(nombreTit,titulo);
        bHeader.putSerializable(INFO_USER,responseLogIn);
        bTransact.putSerializable(INFO_USER,responseLogIn);

        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader,menuHeader).commit();

        menuPat.setArguments(bTransact);
        mFragmentTransactPat.add(R.id.frameLayout, menuPat).commit();


        setListenerTextEdit(montoAlimentos);
        setListenerTextEdit(montoRenta);
        setListenerTextEdit(montoGastosEscuela);
        setListenerTextEdit(montoTelefono);
        setListenerTextEdit(montoLuz);
        setListenerTextEdit(montoAgua);
        setListenerTextEdit(montoGas);
        setListenerTextEdit(montoTransporte);
        setListenerTextEdit(montoVestido);
        setListenerTextEdit(montoEsparcimiento);
        setListenerTextEdit(montoReparaciones);
        toFinEgresos();
    }


    private void setListenerTextEdit(EditText edit) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                generaCalculo();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void generaCalculo() {

        DecimalFormat formatter = new DecimalFormat("###,###.###");

        montoDoAlimentos = defineValor(montoAlimentos.getText().toString());
        montoDoRenta = defineValor(montoRenta.getText().toString());
        montoDoGastosEscuela = defineValor(montoGastosEscuela.getText().toString());
        montoDoTelefono = defineValor(montoTelefono.getText().toString());
        montoDoLuz = defineValor(montoLuz.getText().toString());
        montoDoAgua = defineValor(montoAgua.getText().toString());
        montoDoGas = defineValor(montoGas.getText().toString());
        montoDoTransporte = defineValor(montoTransporte.getText().toString());
        montoDoVestido = defineValor(montoVestido.getText().toString());
        montoDoEsparcimiento = defineValor(montoEsparcimiento.getText().toString());
        montoDoReparaciones = defineValor(montoReparaciones.getText().toString());
        totalGeneral = montoDoAlimentos + montoDoRenta + montoDoGastosEscuela + montoDoTelefono + montoDoLuz + montoDoAgua + montoDoGas
                + montoDoTransporte + montoDoVestido + montoDoEsparcimiento + montoDoReparaciones;

        String formateado = formatter.format(totalGeneral);

        montoTotal.setText(formateado);


    }

    private void capturaInfo() {
        DataRequestSolicitudCredito data = requestSolicitudCredito.getData();
        data.setEgresos(new Egresos_());

        data.getEgresos().setAgua(String.valueOf(montoDoAgua));
        data.getEgresos().setTelefono(String.valueOf(montoDoTelefono));
        data.getEgresos().setAlimentos(String.valueOf(montoDoAlimentos));
        data.getEgresos().setGas(String.valueOf(montoDoGas));
        data.getEgresos().setGastosEscolares(String.valueOf(montoDoGastosEscuela));
        data.getEgresos().setRenta(String.valueOf(montoDoRenta));
        data.getEgresos().setLuz(String.valueOf(montoDoLuz));
        data.getEgresos().setTransporteGasolina(String.valueOf(montoDoTransporte));
        data.getEgresos().setVestido(String.valueOf(montoDoVestido));
        data.getEgresos().setEsparcimiento(String.valueOf(montoDoEsparcimiento));
        data.getEgresos().setMantenimientoReparaciones(String.valueOf(montoDoReparaciones));

        data.getEgresos().setTotalMensual(String.valueOf(totalGeneral));
        //requestSolicitudCredito.setData(data);
    }

    private double defineValor(String s) {
        double value;
        if (s.equals("")) {
            value = 0;
        } else {
            value = Double.parseDouble(s);

        }
        return value;
    }

    private void generaCargaInicial() {
        siguiente = findViewById(R.id.btnSiguiente);
        radioEgresos = findViewById(R.id.radioEgresos);
        montoAlimentos = findViewById(R.id.montoAlimentos);
        montoRenta = findViewById(R.id.montoRenta);
        montoGastosEscuela = findViewById(R.id.montoGastosEscuela);
        montoTelefono = findViewById(R.id.montoTelefono);
        montoLuz = findViewById(R.id.montoLuz);
        montoAgua = findViewById(R.id.montoAgua);
        montoGas = findViewById(R.id.montoGas);
        montoTransporte = findViewById(R.id.montoTransporte);
        montoVestido = findViewById(R.id.montoVestido);
        montoEsparcimiento = findViewById(R.id.montoEsparcimiento);
        montoReparaciones = findViewById(R.id.montoReparaciones);
        montoTotal = findViewById(R.id.montoTotal);


    }

    private void toFinEgresos() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent solicitudCreditoScreenIntent = new Intent(Egresos.this, Patrimonios.class);
            solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            boolean status = (receptor.getBoolean(nombreStatus));
            capturaInfo();
            sender.putString(nombreTit, titulo);
            sender.putSerializable(INFO_USER,responseLogIn);
            sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
            sender.putBoolean(nombreStatus, status);
            solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            solicitudCreditoScreenIntent.putExtras(sender);
            startActivity(solicitudCreditoScreenIntent);
        });
    }

    @Override
    public void transfiereinfocredito() {
        capturaInfo();
        bTransact.putSerializable(N_REQ_SOL_CRED,requestSolicitudCredito);
        bTransact.putString(nombreTit,titulo);
        bTransact.putSerializable(INFO_USER,responseLogIn);
        menuPat.setArguments(bTransact);
    }
}