package com.amextra.SolicitudCredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuSolicitudCredito;
import com.amextra.io.Request.DataRequestSolicitudCredito;
import com.amextra.io.Request.Ingresos_;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DatosAdicionales extends AppCompatActivity implements MenuSolicitudCredito.TransfiereDatos{
    Button siguiente;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    String INFO_USER = "infoLogIn";
    DecimalFormat formatter = new DecimalFormat("###,###.###");
    TextInputEditText txtDiasVA, txtMontoVA, txtDiasVB, txtMontoVB;
    TextInputEditText txtTotalVA, txtTotalVB, totalDias,
            totalMontoDia, totalMontoSemana, totalGeneral;

    TextView txtTasa;

    double tasa = 4.34;
    String N_REQ_SOL_CRED = "REQSOLCRED";

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();


    final androidx.fragment.app.FragmentManager mFragmentPat= getSupportFragmentManager();
    final MenuSolicitudCredito menuPat = new MenuSolicitudCredito();
    final androidx.fragment.app.FragmentTransaction mFragmentTransactPat = mFragmentPat.beginTransaction();

    int diasVa =0,diasVb=0,montoVa=0,montoVb=0;
    long totalVa=0,totalVb=0,totalSemanal=0,totalDiasSemana=0;
    double montoDiario=0;
    double totalGeneralM =0;
    Bundle bTransact = new Bundle();
    InfoUSer responseLogIn ;
    Bundle bHeader = new Bundle();

    boolean existeInfo = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_adicionales);
        Bundle recepcion = getIntent().getExtras();


        siguiente = findViewById(R.id.btnSiguiente);
        txtDiasVA = findViewById(R.id.txtDiasVA);
        txtMontoVA = findViewById(R.id.txtMontoVA);
        txtDiasVB = findViewById(R.id.txtDiasVB);
        txtMontoVB = findViewById(R.id.txtMontoVB);
        txtTotalVA = findViewById(R.id.txtTotalVA);
        txtTotalVB = findViewById(R.id.txtTotalVB);
        totalDias = findViewById(R.id.totalDias);
        totalMontoDia = findViewById(R.id.totalMontoDia);
        totalMontoSemana = findViewById(R.id.totalMontoSemana);
        totalGeneral = findViewById(R.id.totalGeneral);
        txtTasa = findViewById(R.id.txtTasa);






        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);
            if(getIntent().hasExtra(N_REQ_SOL_CRED)){
                requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
                if(requestSolicitudCredito.data.ingresos!=null){
                    existeInfo = true;
                    txtDiasVA.setText(requestSolicitudCredito.data.ingresos.diasVentaAlta);
                    txtDiasVB.setText(requestSolicitudCredito.data.ingresos.diasVentaBaja);
                    txtMontoVA.setText(requestSolicitudCredito.data.ingresos.diasVentaAltaMontoDia);
                    txtMontoVB.setText(requestSolicitudCredito.data.ingresos.diasVentaBajaMontoDia);
                    realizaCalculo();
                }
            }

            txtTasa.setText("Monto por mes\n(x"+tasa+")");
        }



        bTransact.putInt("itm",3);
        bTransact.putSerializable(N_REQ_SOL_CRED,requestSolicitudCredito);
        bTransact.putString(nombreTit,titulo);
        bTransact.putSerializable("infoLogIn",responseLogIn);

        bHeader.putString(nombreTit,titulo);
        bHeader.putSerializable("infoLogIn",responseLogIn);
        menuHeader.setArguments(bHeader);
        menuPat.setArguments(bTransact);


        mFragmentHeaderTransac.add(R.id.frameHeader,menuHeader).commit();



        mFragmentTransactPat.add(R.id.frameLayout, menuPat).commit();

        toInversion();
        listenerDiasVentaAlta();
        listenerMontoVentaAlta();
        listenerDiasVentaBaja();
        listenerMontoVentaBaja();


    }

    private void listenerMontoVentaBaja() {
        txtMontoVB.addTextChangedListener(new TextWatcher() {
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

    private void listenerDiasVentaBaja() {
        txtDiasVB.addTextChangedListener(new TextWatcher() {
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

    private void listenerMontoVentaAlta() {
        txtMontoVA.addTextChangedListener(new TextWatcher() {
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

    private void listenerDiasVentaAlta() {
        txtDiasVA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {realizaCalculo();}
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }


    private void realizaCalculo(){


        DecimalFormat formatter = new DecimalFormat("###,###.###");

        diasVa = defineValor(txtDiasVA.getText().toString());
        diasVb = defineValor(txtDiasVB.getText().toString());

        montoVa = defineValor(txtMontoVA.getText().toString());
         montoVb = defineValor(txtMontoVB.getText().toString());

         totalVa = calculaTotalDias(diasVa,montoVa);
         totalVb = calculaTotalDias(diasVb,montoVb);
         totalSemanal = totalVb+totalVa;
         totalDiasSemana = diasVa+diasVb;


        if(totalSemanal >0 || totalDiasSemana >0){
            montoDiario = totalSemanal/totalDiasSemana;
        }
        totalGeneralM = tasa * totalSemanal;



        String numFormVa = formatter.format(totalVa);
        String numFormVb = formatter.format(totalVb);
        String numFormTotalSemanal = formatter.format(totalSemanal);
        String numFormMontoDiario = formatter.format(montoDiario);
        String numFormMontoGeneral = formatter.format(totalGeneralM);

        txtTotalVA.setText("$"+numFormVa);
        txtTotalVB.setText("$"+numFormVb);
        totalMontoSemana.setText("$"+numFormTotalSemanal);
        totalDias.setText(String.valueOf(totalDiasSemana));
        totalMontoDia.setText("$"+numFormMontoDiario);
        totalGeneral.setText("$"+numFormMontoGeneral);


    }



    private int defineValor(String s) {
        int value ;
        if (s.equals("")) {
            value = 0;
        }else{
            value = Integer.parseInt(s);

        }
        return value;
    }


    private long calculaTotalDias(int dias, int monto) {

        int total = dias * monto;
        return (long) total;
    }


    private void toInversion() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            if(validaDiasSemana()){
                capturaInfo();
                Intent solicitudCreditoScreenIntent = new Intent(DatosAdicionales.this, DatosAdicionalesInversion.class);
                boolean status = (receptor.getBoolean(nombreStatus));
                sender.putString(nombreTit, titulo);
                sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
                sender.putSerializable(INFO_USER,responseLogIn);
                sender.putBoolean(nombreStatus, status);
                solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                solicitudCreditoScreenIntent.putExtras(sender);
                startActivity(solicitudCreditoScreenIntent);
            }else{
                new SweetAlertDialog(DatosAdicionales.this,SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Advertencia")
                        .setContentText("El total de dias de la semana debe estar entre 1 y 7.")
                        .show();
            }

        });
    }

    public void capturaInfo(){
        DataRequestSolicitudCredito data = requestSolicitudCredito.getData();

        if(!existeInfo){
            data.setIngresos(new Ingresos_());
            data.getIngresos().setTotalDiasTrabajados(String.valueOf(totalDiasSemana));
            data.getIngresos().setDiasVentaAlta(String.valueOf(diasVa));
            data.getIngresos().setDiasVentaAltaMontoDia(String.valueOf(montoVa));
            data.getIngresos().setDiasVentaAltaMontoSemanal(String.valueOf(totalVa));
            data.getIngresos().setDiasVentaBajaMontoSemanal(String.valueOf(totalVb));
            data.getIngresos().setDiasVentaBaja(String.valueOf(diasVb));
            data.getIngresos().setDiasVentaBajaMontoDia(String.valueOf(montoVb));
            data.getIngresos().setTotalMontoDia(String.valueOf(montoDiario));
            data.getIngresos().setTotalMontoSemana(String.valueOf(totalSemanal));
            data.getIngresos().setTotalMontoMes(String.valueOf(totalGeneralM));
        }else {
            data.getIngresos().setTotalDiasTrabajados(String.valueOf(totalDiasSemana));
            data.getIngresos().setDiasVentaAlta(String.valueOf(diasVa));
            data.getIngresos().setDiasVentaAltaMontoDia(String.valueOf(montoVa));
            data.getIngresos().setDiasVentaAltaMontoSemanal(String.valueOf(totalVa));
            data.getIngresos().setDiasVentaBajaMontoSemanal(String.valueOf(totalVb));
            data.getIngresos().setDiasVentaBaja(String.valueOf(diasVb));
            data.getIngresos().setDiasVentaBajaMontoDia(String.valueOf(montoVb));
            data.getIngresos().setTotalMontoDia(String.valueOf(montoDiario));
            data.getIngresos().setTotalMontoSemana(String.valueOf(totalSemanal));
            data.getIngresos().setTotalMontoMes(String.valueOf(totalGeneralM));
        }



    }

    private boolean validaDiasSemana(){
        /*
        TODO: Se requiere que la validación de la venta alta/baja se retire, es decir si los campos se encuentran vacíos desde atrás se coloque automáticamente 0, ya que en ocasiones esto no es necesario.
        TODO: Por otro lado, si se coloca algún dato en venta alta/baja la suma de estos si debe ser igual o menor a 7 días.
         */
        return totalDiasSemana <= 7 && totalDiasSemana >= 0;
    }


    @Override
    public void transfiereinfocredito() {
        if(validaDiasSemana()){
            capturaInfo();
        }

        bTransact.putSerializable(N_REQ_SOL_CRED,requestSolicitudCredito);
        bTransact.putString(nombreTit,titulo);
        bTransact.putSerializable(INFO_USER,responseLogIn);
        menuPat.setArguments(bTransact);


    }



}