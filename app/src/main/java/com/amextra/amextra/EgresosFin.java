package com.amextra.amextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.amextra.SolicitudCredito.Patrimonios;

public class EgresosFin extends AppCompatActivity {
    Button siguiente;
    Button btnNoActionHeader;
    RadioButton radioEgresos;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egresos_fin);
        Bundle recepcion = getIntent().getExtras();
        titulo =(recepcion.getString(nombreTit));
        siguiente = findViewById(R.id.btnSiguiente);
        radioEgresos = findViewById(R.id.radioEgresos);
        btnNoActionHeader= findViewById(R.id.btnNoActionHeader);
        radioEgresos.setChecked(true);
        btnNoActionHeader.setText(titulo);
        toEgresosFin();
    }

    private void toEgresosFin() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent solicitudCreditoScreenIntent = new Intent(EgresosFin.this, Patrimonios.class);
            boolean status = (receptor.getBoolean(nombreStatus));
            sender.putString(nombreTit,titulo);
            sender.putBoolean(nombreStatus,status);
            solicitudCreditoScreenIntent.putExtras(sender);
            startActivity(solicitudCreditoScreenIntent);
        });
    }
}