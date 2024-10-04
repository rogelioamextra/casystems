package com.amextra.ConsultaInfoCliente;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.amextra.AltaEdicionCliente.DatosPersonalesClientes;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseGetCliente;

public class ConsultaPersonal extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Button btnEditarInfo, nxtBtn;
    TextView txtIdClient, txtNombreClient, idGenero, idNacionalidad, idLugarNacimiento, txtCodigoIne, tipoId, txtEstadoCivil;
    TextView idFechaNacimiento, idRfc, txtIdIne, txtIdClaveIne, idCurp, idMaxEstudios, numTelefono, txtEmision, txtVigencia, txtMail;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    String clienteInfo = "INFO_CLIENT";
    FrameLayout frameMenu, frameHeader;
    InfoUSer responseLogIn = new InfoUSer();
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();

    MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    MenuHeader menuHeader = new MenuHeader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_personal);

        FragmentManager fragManagerHead = this.getSupportFragmentManager();
        FragmentManager fragManagerMenu = this.getSupportFragmentManager();

        FragmentTransaction transactionHead = fragManagerHead.beginTransaction();
        FragmentTransaction transactionMenu = fragManagerMenu.beginTransaction();

        Bundle recepcion = getIntent().getExtras();
        Bundle clientBundle = new Bundle();
        Bundle headerBundle = new Bundle();
        iniciaComponentes();


        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            responseGetCliente = (ResponseGetCliente) recepcion.getSerializable(clienteInfo);
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            setInfoClient(responseGetCliente);
        }


        clientBundle.putSerializable(clienteInfo, responseGetCliente);
        clientBundle.putSerializable("infoLogIn", responseLogIn);
        clientBundle.putInt("itm", 0);
        clientBundle.putBoolean(nombreStatus, esAlta);
        clientBundle.putString(nombreTit, titulo);
        headerBundle.putString(nombreTit, titulo);
        headerBundle.putSerializable("infoLogIn", responseLogIn);
        menuInformacionCliente.setArguments(clientBundle);
        menuHeader.setArguments(headerBundle);

        transactionHead.add(R.id.frameHeader, menuHeader);
        transactionHead.commit();
        transactionMenu.add(R.id.frameMenu, menuInformacionCliente);
        transactionMenu.commit();


        editaInfoPersonal();
        consultaDireccion();
    }


    private void editaInfoPersonal() {
        btnEditarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sender = new Bundle();
                Intent consultaInfDireccion = new Intent(ConsultaPersonal.this, DatosPersonalesClientes.class);

                consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                sender.putString(nombreTit, "Editar Datos del Cliente");
                sender.putSerializable("infoLogIn", responseLogIn);
                sender.putBoolean(nombreStatus, esAlta);
                sender.putSerializable(clienteInfo, responseGetCliente);
                consultaInfDireccion.putExtras(sender);
                startActivity(consultaInfDireccion);
            }
        });
    }

    private void consultaDireccion() {
        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaInfDireccion = new Intent(ConsultaPersonal.this, ConsultaDireccion.class);
                Bundle bundle = new Bundle();
                consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                bundle.putString(nombreTit, titulo);
                bundle.putBoolean(nombreStatus, esAlta);
                bundle.putSerializable(clienteInfo, responseGetCliente);
                bundle.putSerializable("infoLogIn", responseLogIn);
                consultaInfDireccion.putExtras(bundle);
                startActivity(consultaInfDireccion);
            }
        });
    }


    private void iniciaComponentes() {
        nxtBtn = findViewById(R.id.nxtBtn);


        btnEditarInfo = findViewById(R.id.btnEditarInfo);
        idFechaNacimiento = findViewById(R.id.idFechaNacimiento);
        idRfc = findViewById(R.id.idRfc);
        txtIdIne = findViewById(R.id.txtIdIne);
        txtIdClaveIne = findViewById(R.id.txtIdClaveIne);
        idCurp = findViewById(R.id.idCurp);
        idMaxEstudios = findViewById(R.id.idMaxEstudios);
        txtIdClient = findViewById(R.id.txtIdClient);
        txtNombreClient = findViewById(R.id.txtNombreClient);
        idGenero = findViewById(R.id.idGenero);
        idNacionalidad = findViewById(R.id.idNacionalidad);
        numTelefono = findViewById(R.id.numTelefono);
        idLugarNacimiento = findViewById(R.id.idLugarNacimiento);
        txtCodigoIne = findViewById(R.id.txtCodigoIne);
        txtEmision = findViewById(R.id.txtEmision);
        txtVigencia = findViewById(R.id.txtVigencia);
        tipoId = findViewById(R.id.tipoId);
        txtMail = findViewById(R.id.txtMail);
        txtEstadoCivil = findViewById(R.id.txtEstadoCivil);
        frameMenu = findViewById(R.id.frameMenu);
        frameHeader = findViewById(R.id.frameHeader);

    }

    @SuppressLint("SetTextI18n")
    private void setInfoClient(ResponseGetCliente responseGetCliente) {
        Cliente datos = responseGetCliente.data.cliente;
        txtIdClient.setText(String.valueOf(datos.idCliente));
        txtNombreClient.setText(datos.idPersona.nombres + " " + datos.idPersona.apellidoPaterno + " " + datos.idPersona.apellidoMaterno);
        if ((datos.idPersona.genero == 1)) {
            idGenero.setText("Hombre");
        } else {
            idGenero.setText("Mujer");
        }
        idNacionalidad.setText(datos.idPersona.idNacionalidad.nombre);
        idLugarNacimiento.setText(datos.idPersona.lugarNacimiento.nombre);
        idFechaNacimiento.setText(datos.idPersona.fechaNacimiento);
        idRfc.setText(datos.idPersona.rfc);
        numTelefono.setText(datos.idPersona.telefono);
        idCurp.setText(datos.idPersona.curp);
        idMaxEstudios.setText(datos.idPersona.idGradoMaximoEstudios.nombre);
        txtIdIne.setText(String.valueOf(datos.idDtIdentificacion.idDtIdentificacion));
        txtIdClaveIne.setText(String.valueOf(datos.idDtIdentificacion.claveElector));
        txtCodigoIne.setText(String.valueOf(datos.idDtIdentificacion.noIdentificacion));
        tipoId.setText(String.valueOf(datos.idDtIdentificacion.idTipoIdentificacion.nombre));

        txtEmision.setText(String.valueOf(datos.idDtIdentificacion.emision));
        txtVigencia.setText(String.valueOf(datos.idDtIdentificacion.vigencia));

        txtMail.setText(String.valueOf(datos.idPersona.email));
        txtEstadoCivil.setText(String.valueOf(datos.idPersona.idEstadoCivil.nombre));
    }


    @Override
    public void transfiereInfo(RequestInsertClient req) {
        Log.d("consultaC", "se ejecuta en transfiere info: " + 3);
    }
}