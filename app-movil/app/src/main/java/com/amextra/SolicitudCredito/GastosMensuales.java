package com.amextra.SolicitudCredito;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuSolicitudCredito;
import com.amextra.io.Request.DataRequestSolicitudCredito;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class GastosMensuales extends AppCompatActivity implements MenuSolicitudCredito.TransfiereDatos {
    boolean esAlta;
    Button siguiente;
    Button btnNoActionHeader;
    RadioButton radioIngresos;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    TextInputEditText gastosTransporte, gastosRenta, gastosServicios, gastosSueldos, gastosTotales;

    String N_REQ_SOL_CRED = "REQSOLCRED";
    long totalGastos;
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    int montoTransporte = 0, montoRenta = 0, montoServicios = 0, montoSueldos = 0;

    final androidx.fragment.app.FragmentManager mFragmentPat = getSupportFragmentManager();
    final MenuSolicitudCredito menuPat = new MenuSolicitudCredito();
    final androidx.fragment.app.FragmentTransaction mFragmentTransactPat = mFragmentPat.beginTransaction();

    Bundle bTransact = new Bundle();
    InfoUSer responseLogIn;
    Bundle bHeader = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_mensuales);
        Bundle recepcion = getIntent().getExtras();


        siguiente = findViewById(R.id.btnSiguiente);
        gastosTransporte = findViewById(R.id.gastosTransporte);
        gastosRenta = findViewById(R.id.gastosRenta);
        gastosServicios = findViewById(R.id.gastosServicios);
        gastosSueldos = findViewById(R.id.gastosSueldos);
        gastosTotales = findViewById(R.id.gastosTotales);

        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");

            if (getIntent().hasExtra(N_REQ_SOL_CRED)) {
                requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
                if (requestSolicitudCredito.getData().getIngresos() != null) {

                    gastosRenta.setText(requestSolicitudCredito.getData().getIngresos().getGastoRenta());
                    gastosTransporte.setText(requestSolicitudCredito.getData().getIngresos().getGastoTransporte());
                    gastosServicios.setText(requestSolicitudCredito.getData().getIngresos().getGastoServicios());
                    gastosSueldos.setText(requestSolicitudCredito.getData().getIngresos().getGastoSueldos());
                    gastosTotales.setText(requestSolicitudCredito.getData().getIngresos().getGastoTotal());
                    generaCaluloTotal();
                }
            }
        }


        bTransact.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bTransact.putInt("itm", 1);
        bTransact.putString(nombreTit, titulo);
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();

        menuPat.setArguments(bTransact);
        mFragmentTransactPat.add(R.id.frameLayout, menuPat).commit();


        toEgresos();
        listenersEditText(gastosTransporte);
        listenersEditText(gastosRenta);
        listenersEditText(gastosServicios);
        listenersEditText(gastosSueldos);
    }

    private void listenersEditText(EditText edit) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                generaCaluloTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void generaCaluloTotal() {
        DecimalFormat formatter = new DecimalFormat("###,###.###");
        montoTransporte = defineValor(gastosTransporte.getText().toString());
        montoRenta = defineValor(gastosRenta.getText().toString());
        montoServicios = defineValor(gastosServicios.getText().toString());
        montoSueldos = defineValor(gastosSueldos.getText().toString());
        totalGastos = montoServicios + montoRenta + montoTransporte + montoSueldos;
        String totalFormat = formatter.format(totalGastos);
        gastosTotales.setText(totalFormat);

    }

    private int defineValor(String s) {
        int value;
        if (s.equals("")) {
            value = 0;
        } else {
            value = Integer.parseInt(s);

        }
        return value;
    }


    private void getInformacion() {
        DataRequestSolicitudCredito dataRequestSolicitudCredito = requestSolicitudCredito.getData();
        dataRequestSolicitudCredito.getIngresos().setGastoTransporte(String.valueOf(montoTransporte));
        dataRequestSolicitudCredito.getIngresos().setGastoServicios(String.valueOf(montoServicios));
        dataRequestSolicitudCredito.getIngresos().setGastoRenta(String.valueOf(montoRenta));
        dataRequestSolicitudCredito.getIngresos().setGastoSueldos(String.valueOf(montoSueldos));
        dataRequestSolicitudCredito.getIngresos().setGastoTotal(String.valueOf(totalGastos));
    }

    private void toEgresos() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent solicitudCreditoScreenIntent = new Intent(GastosMensuales.this, Egresos.class);
            solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            boolean status = (receptor.getBoolean(nombreStatus));
            getInformacion();
            sender.putString(nombreTit, titulo);
            sender.putSerializable("infoLogIn", responseLogIn);
            sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
            sender.putBoolean(nombreStatus, status);
            solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            solicitudCreditoScreenIntent.putExtras(sender);
            startActivity(solicitudCreditoScreenIntent);

        });
    }

    @Override
    public void transfiereinfocredito() {
        getInformacion();
        bTransact.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bTransact.putString(nombreTit, titulo);
        bTransact.putSerializable("infoLogIn", responseLogIn);
        menuPat.setArguments(bTransact);

    }
}