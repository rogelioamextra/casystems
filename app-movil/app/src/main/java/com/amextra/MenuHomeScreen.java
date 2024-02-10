package com.amextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amextra.AltaEdicionCliente.DatosPersonalesClientes;
import com.amextra.ConsultaInfoCliente.ConsultaClientes;
import com.amextra.SolicitudCredito.SolicitudDeCredito;
import com.amextra.agenda.Agenda_clientes;
import com.amextra.amextra.R;
import com.amextra.cartera.ClientesAsesor;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.InfoUSer;
import cn.pedant.SweetAlert.SweetAlertDialog ;

public class MenuHomeScreen extends AppCompatActivity {

    ImageButton btnSolicitudCredito,btnAvisos,btnClienteNuevo,btnCartera,btnAgenda,btnConsultaClientes;

    TextView labelSaludoName,cerrarSesion;

    Geolocalizacion geolocalizacion = new Geolocalizacion();
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    InfoUSer responseLogIn = new InfoUSer();
    String INFO_USER = "infoLogIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home_screen);
        String nombre="";
        Bundle receptor = getIntent().getExtras();


        if(receptor!=null){

            geolocalizacion = (Geolocalizacion) receptor.getSerializable("geo");
            responseLogIn = (InfoUSer) receptor.getSerializable("infoLogIn");
            nombre = responseLogIn.nombreUsuario;

        }

        btnConsultaClientes = findViewById(R.id.btnConsultaClientes);
        btnCartera = findViewById(R.id.btnCartera);
        btnAvisos = findViewById(R.id.btnAvisos);
        btnClienteNuevo = findViewById(R.id.btnClienteNuevo);
        btnSolicitudCredito = findViewById(R.id.btnSolicitudCredito);
        btnAgenda = findViewById(R.id.btnAgenda);
        labelSaludoName = findViewById(R.id.labelSaludoName);
        cerrarSesion = findViewById(R.id.cerrarSesion);

        labelSaludoName.setText(nombre);
        toConsultaClientes();
        aNuevaSolicitudCredito();
        toAltaCliente();
        toCartera();
        toAvisos();
        cerrarSesion();
        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle enviaDatos = new Bundle();
                Intent datosClienteScreenIntent = new Intent(MenuHomeScreen.this, Agenda_clientes.class);
                enviaDatos.putBoolean(nombreStatus,true);
                enviaDatos.putString(nombreTit,"Agenda Clientes");
                enviaDatos.putSerializable("geo",geolocalizacion);
                enviaDatos.putSerializable("infoLogIn",responseLogIn);
                datosClienteScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                datosClienteScreenIntent.putExtras(enviaDatos);
                startActivity(datosClienteScreenIntent);
            }
        });


    }

    private void cerrarSesion() {
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog = new SweetAlertDialog(MenuHomeScreen.this,SweetAlertDialog.WARNING_TYPE);
                dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        finish();
                        Intent login = new Intent(MenuHomeScreen.this, MainActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(login);
                    }
                });
                dialog.setTitleText("Abandonara la sesión");
                dialog.setContentText("¿Desea cerrar la sesión");
                dialog.setConfirmText("Continuar");
                dialog.setCancelText("Cancelar");
                dialog.show();

            }
        });
    }

    private void toAltaCliente() {
        btnClienteNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle enviaDatos = new Bundle();
                Intent datosClienteScreenIntent = new Intent(MenuHomeScreen.this, DatosPersonalesClientes.class);
                enviaDatos.putBoolean(nombreStatus,true);
                enviaDatos.putString(nombreTit,"Alta de nuevo cliente");
                enviaDatos.putSerializable("geo",geolocalizacion);
                enviaDatos.putSerializable(INFO_USER,responseLogIn);
                datosClienteScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                datosClienteScreenIntent.putExtras(enviaDatos);
                startActivity(datosClienteScreenIntent);
            }
        });
    }

    private void aNuevaSolicitudCredito() {
        btnSolicitudCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle enviaDatos = new Bundle();
                Intent solicitudCreditoScreenIntent = new Intent(MenuHomeScreen.this, SolicitudDeCredito.class);
                solicitudCreditoScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                enviaDatos.putBoolean(nombreStatus,true);
                enviaDatos.putSerializable(INFO_USER,responseLogIn);
                enviaDatos.putString(nombreTit,"Solicitud de Crédito");
                solicitudCreditoScreenIntent.putExtras(enviaDatos);
                startActivity(solicitudCreditoScreenIntent);

            }
        });
    }

    private void toConsultaClientes() {
        btnConsultaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle enviaDatos = new Bundle();
                Intent clientScreenIntent = new Intent(MenuHomeScreen.this, ConsultaClientes.class);
                enviaDatos.putBoolean(nombreStatus,false);
                enviaDatos.putString(nombreTit,"Mis Clientes");
                enviaDatos.putSerializable(INFO_USER,responseLogIn);
                clientScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                clientScreenIntent.putExtras(enviaDatos);
                startActivity(clientScreenIntent);
            }
        });
    }

    private void toCartera(){
        btnCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle enviaDatos = new Bundle();
                Intent clientScreenIntent = new Intent(MenuHomeScreen.this, ClientesAsesor.class);
                enviaDatos.putBoolean(nombreStatus,false);
                enviaDatos.putString(nombreTit,"Clientes por asesor");
                enviaDatos.putSerializable(INFO_USER,responseLogIn);
                clientScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                clientScreenIntent.putExtras(enviaDatos);
                startActivity(clientScreenIntent);
            }
        });
    }

    private void toAvisos(){
        btnAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle enviaDatos = new Bundle();
                Intent clientScreenIntent = new Intent(MenuHomeScreen.this, AvisosAsesor.class);
                enviaDatos.putBoolean(nombreStatus,false);
                enviaDatos.putString(nombreTit,"Avisos");
                enviaDatos.putSerializable(INFO_USER,responseLogIn);
                clientScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                clientScreenIntent.putExtras(enviaDatos);
                startActivity(clientScreenIntent);
            }
        });
    }



}