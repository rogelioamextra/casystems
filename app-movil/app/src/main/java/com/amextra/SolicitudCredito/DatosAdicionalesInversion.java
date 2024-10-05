package com.amextra.SolicitudCredito;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuSolicitudCredito;
import com.amextra.io.Request.DataRequestSolicitudCredito;
import com.amextra.io.Request.Ingresos_;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;
import com.google.android.material.textfield.TextInputEditText;

public class DatosAdicionalesInversion extends AppCompatActivity implements MenuSolicitudCredito.TransfiereDatos {
    Button siguiente;

    String nombreStatus = "esAlta";
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    String N_REQ_SOL_CRED = "REQSOLCRED";
    String INFO_USER = "infoLogIn";
    double tasa = 4.34;
    TextInputEditText montoSemanal, montoQuincenal, montoMensual, montoTransporte, montoRenta, montoServicios, montoSueldo;

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();

    final androidx.fragment.app.FragmentManager mFragmentPat = getSupportFragmentManager();
    final MenuSolicitudCredito menuPat = new MenuSolicitudCredito();
    final androidx.fragment.app.FragmentTransaction mFragmentTransactPat = mFragmentPat.beginTransaction();

    Bundle bTransact = new Bundle();

    int transporte = 0, renta = 0, servicios = 0, sueldos = 0, totalGastosNegocio = 0;
    int montoMensualr = 0, montoQuincenalNum = 0, montoSemanalNum = 0, totalQincenaMes = 0;
    InfoUSer responseLogIn = new InfoUSer();

    double montoMensualResult = 0, totalSemanaMes = 0;
    Bundle bHeader = new Bundle();

    String curpCliente = "";
    final String CURP_CLI = "CURP_CLI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_adicionales_inversion);
        Bundle recepcion = getIntent().getExtras();


        siguiente = findViewById(R.id.btnSiguiente);
        montoSemanal = findViewById(R.id.montoSemanal);
        montoQuincenal = findViewById(R.id.montoQuincenal);
        montoMensual = findViewById(R.id.montoMensual);

        montoTransporte = findViewById(R.id.montoTransporte);
        montoRenta = findViewById(R.id.montoRenta);
        montoServicios = findViewById(R.id.montoServicios);
        montoSueldo = findViewById(R.id.montoSueldo);


        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);
            curpCliente = recepcion.getString(CURP_CLI);
            if (getIntent().hasExtra(N_REQ_SOL_CRED)) {
                requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
                if (requestSolicitudCredito.getData().getIngresos() != null && requestSolicitudCredito.getData().getIngresos().getGastoTotal() != null) {

                    montoSueldo.setText(requestSolicitudCredito.getData().getIngresos().getGastoSueldos());
                    montoTransporte.setText(requestSolicitudCredito.getData().getIngresos().getGastoTransporte());
                    montoRenta.setText(requestSolicitudCredito.getData().getIngresos().getGastoRenta());
                    montoServicios.setText(requestSolicitudCredito.getData().getIngresos().getGastoServicios());
                    montoSemanal.setText(requestSolicitudCredito.getData().getIngresos().getInversionSemanalMonto());
                    montoQuincenal.setText(requestSolicitudCredito.getData().getIngresos().getInversionQuincenalMonto());
                    montoMensual.setText(requestSolicitudCredito.getData().getIngresos().getInversionMensualMonto());
                    realizaCalculo();
                }
            }
        }
        bTransact.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bTransact.putInt("itm", 3);
        bTransact.putString(nombreTit, titulo);
        bTransact.putSerializable(INFO_USER, responseLogIn);
        bHeader.putString(CURP_CLI, curpCliente);
        bTransact.putString(CURP_CLI, curpCliente);

        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable(INFO_USER, responseLogIn);

        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();


        menuPat.setArguments(bTransact);
        mFragmentTransactPat.add(R.id.frameLayout, menuPat).commit();

        listenerTxt(montoMensual);
        listenerTxt(montoQuincenal);
        listenerTxt(montoSemanal);

        listenerGstos(montoTransporte);
        listenerGstos(montoRenta);
        listenerGstos(montoServicios);
        listenerGstos(montoSueldo);


        toGastosMensuales();
    }

    private void toGastosMensuales() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            capturaInfo();
            Intent solicitudCreditoScreenIntent = new Intent(DatosAdicionalesInversion.this, Egresos.class);
            boolean status = (receptor.getBoolean(nombreStatus));
            sender.putString(nombreTit, titulo);
            sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
            sender.putSerializable(INFO_USER, responseLogIn);
            sender.putString(CURP_CLI, curpCliente);
            sender.putBoolean(nombreStatus, status);
            solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            solicitudCreditoScreenIntent.putExtras(sender);
            startActivity(solicitudCreditoScreenIntent);
        });
    }

    private void listenerTxt(EditText txt) {
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                realizaCalculo();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void listenerGstos(EditText txt) {
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculaTotalGasto();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    private void realizaCalculo() {

        montoQuincenalNum = defineValor(montoQuincenal.getText().toString());
        totalQincenaMes = montoQuincenalNum * 2;

        montoSemanalNum = defineValor(montoSemanal.getText().toString());
        totalSemanaMes = montoSemanalNum * tasa;


        montoMensualr = defineValor(montoMensual.getText().toString());


        montoMensualResult = montoMensualr + totalQincenaMes + totalSemanaMes;
    }


    private void calculaTotalGasto() {


        transporte = defineValor(montoTransporte.getText().toString());
        renta = defineValor(montoRenta.getText().toString());
        servicios = defineValor(montoServicios.getText().toString());
        sueldos = defineValor(montoSueldo.getText().toString());

        totalGastosNegocio = transporte + renta + servicios + sueldos;

    }

    private void capturaInfo() {


        DataRequestSolicitudCredito dataRequestSolicitudCredito = requestSolicitudCredito.getData();
        Ingresos_ ingresos = dataRequestSolicitudCredito.getIngresos();

        ingresos.setInversionSemanalMonto(String.valueOf(montoSemanalNum));
        ingresos.setInversionQuincenalMonto(String.valueOf(montoQuincenalNum));
        ingresos.setInversionMensualMonto(String.valueOf(montoMensualr));

        ingresos.setInversionSemanalMontoMensual(String.valueOf(totalSemanaMes));
        ingresos.setInversionQuincenalMontoMensual(String.valueOf(totalQincenaMes));
        ingresos.setInversionMensualMontoMensual(String.valueOf(montoMensualr));

        ingresos.setInversionTotal(String.valueOf(montoMensualResult));


        ingresos.setGastoTransporte(String.valueOf(transporte));
        ingresos.setGastoRenta(String.valueOf(renta));
        ingresos.setGastoServicios(String.valueOf(servicios));
        ingresos.setGastoSueldos(String.valueOf(sueldos));
        ingresos.setGastoTotal(String.valueOf(totalGastosNegocio));


        requestSolicitudCredito.setData(dataRequestSolicitudCredito);

    }

    @Override
    public void transfiereinfocredito() {
        capturaInfo();
        bTransact.clear();
        bHeader.clear();
        bTransact.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bTransact.putString(nombreTit, titulo);
        bTransact.putSerializable(INFO_USER, responseLogIn);


        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable(INFO_USER, responseLogIn);
        menuHeader.setArguments(bHeader);
        menuPat.setArguments(bTransact);
    }
}