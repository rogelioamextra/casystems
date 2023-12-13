package com.amextra.ConsultaInfoCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amextra.AltaEdicionCliente.DatosClienteLaborales;
import com.amextra.amextra.R;
import com.amextra.AltaEdicionCliente.datos_personales_address;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseLogin;

public class ConsultaDireccion extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Button  btnEditarInfo;
    String nombreStatus = "esAlta";
    ResponseGetCliente infoCliente = null;
    Button btnSiguiente;

    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    TextView txtCalle,
            txtNumInt,
            txtNumExt,
            txtEstado,
            txtMunicipio,
            txtColonia,
            txtCodigoPostal,
            txtTipoResidencia,
            txtTipoVivienda,
            txtTiempoEnResidencia;
    ImageView flechaRegreso;
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    String clienteInfo ="INFO_CLIENT";
    InfoUSer responseLogIn = new InfoUSer();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_direccion);
        iniciaComponentes();
        Bundle recepcion = getIntent().getExtras();
        Bundle mBundle = new Bundle();
        Bundle bHeader = new Bundle();


        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            responseGetCliente =(ResponseGetCliente) recepcion.getSerializable(clienteInfo);
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            setInfoCliente(responseGetCliente);
        }
        mBundle.putString(nombreTit,titulo);
        mBundle.putSerializable(clienteInfo,responseGetCliente);
        mBundle.putSerializable("infoLogIn",responseLogIn);
        mBundle.putInt("itm",1);
        mBundle.putBoolean(nombreStatus,esAlta);
        mBundle.putString(nombreTit,titulo);
        bHeader.putString(nombreTit,titulo);
        bHeader.putSerializable("infoLogIn",responseLogIn);
        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader,menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout,menuInformacionCliente).commit();


        aConsultaInfLaboral();
        editaDireccion();
    }

    private void setInfoCliente(ResponseGetCliente responseGetCliente) {
        Cliente datos = responseGetCliente.data.cliente;

        if(datos.idDireccion!=null){

            String tiempoAnios = datos.idDireccion.tiempoResidencia>1 ? "años":"año";
            String tiempoMeses = datos.idDireccion.tiempoResidenciaMeses>1 ? "meses":"mes";
            txtCalle.setText(datos.idDireccion.calle.toUpperCase());
            txtNumInt.setText(datos.idDireccion.numeroInterior);
            txtNumExt.setText(datos.idDireccion.numeroExterior);
            txtEstado.setText(datos.idDireccion.idEstado.nombre.toUpperCase());
            txtTiempoEnResidencia.setText(datos.idDireccion.tiempoResidencia+" "+tiempoAnios+"  "+datos.idDireccion.tiempoResidenciaMeses+" "+tiempoMeses);
            txtCodigoPostal.setText(datos.idDireccion.cp);
            if (datos.idDireccion.idMunicipio != null) {
                txtMunicipio.setText(datos.idDireccion.idMunicipio.nombre.toUpperCase());
            }
            if (datos.idDireccion.idTipoResidencia != null) {
                txtTipoResidencia.setText(datos.idDireccion.idTipoResidencia.nombre.toUpperCase());
            }
            if (datos.idDireccion.idTipoVivienda != null) {
                txtTipoVivienda.setText(datos.idDireccion.idTipoVivienda.nombre.toUpperCase());;
            }
            if (datos.idDireccion.idColonia != null) {
                txtColonia.setText(datos.idDireccion.idColonia.nombre.toUpperCase());;
            }
        }

    }

    private void editaDireccion() {
        btnEditarInfo.setOnClickListener(v -> {
            Bundle sender = new Bundle();

            Intent consultaInfDireccion = new Intent(ConsultaDireccion.this, datos_personales_address.class);
            consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            sender.putString(nombreTit, "Editar Datos del Cliente");
            sender.putSerializable("infoLogIn",responseLogIn);

            sender.putBoolean(nombreStatus,esAlta);
            sender.putSerializable(clienteInfo,responseGetCliente);
            consultaInfDireccion.putExtras(sender);
            startActivity(consultaInfDireccion);
        });
    }

    private void aConsultaInfLaboral() {
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sender = new Bundle();
                Intent consultaInfLaboral = new Intent(ConsultaDireccion.this, ConsultaEmpleo.class);
                consultaInfLaboral.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                sender.putSerializable("infoLogIn",responseLogIn);

                sender.putString(nombreTit, titulo);
                sender.putBoolean(nombreStatus, esAlta);
                sender.putSerializable(clienteInfo,responseGetCliente);
                consultaInfLaboral.putExtras(sender);
                startActivity(consultaInfLaboral);
            }
        });
    }

    private void iniciaComponentes() {
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnEditarInfo = findViewById(R.id.btnEditarInfo);
        txtCalle = findViewById(R.id.txtCalle);
        txtNumInt = findViewById(R.id.txtNumInt);
        txtNumExt = findViewById(R.id.txtNumExt);
        txtEstado = findViewById(R.id.txtEstado);
        txtMunicipio = findViewById(R.id.txtMunicipio);
        txtColonia = findViewById(R.id.txtColonia);
        txtCodigoPostal = findViewById(R.id.txtCodigoPostal);
        txtTipoResidencia = findViewById(R.id.txtTipoResidencia);
        txtTipoVivienda = findViewById(R.id.txtTipoVivienda);
        txtTiempoEnResidencia = findViewById(R.id.txtTiempoEnResidencia);

    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {
        Log.d("consultaC", "se ejecuta en transfiere info: "+1);
    }
}