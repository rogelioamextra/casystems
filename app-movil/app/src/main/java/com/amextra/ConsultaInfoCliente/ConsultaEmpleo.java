package com.amextra.ConsultaInfoCliente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.AltaEdicionCliente.DatosClienteLaborales;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseGetCliente;

public class ConsultaEmpleo extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {

    Button btnEditarInfo;
    Button btnSiguiente;
    ResponseGetCliente infoCliente = null;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    TextView txtEmpresa,
            txtCalleNo,
            txtGiro,
            txtCp,
            txtMunicipio,
            txtReciboNomina,
            txtIngresoMensual,
            txtAntiguedad, txtColonia, txtPuesto;

    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    String clienteInfo = "INFO_CLIENT";
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();

    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

    InfoUSer responseLogIn = new InfoUSer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_empleo);

        Bundle recepcion = getIntent().getExtras();
        Bundle mBundle = new Bundle();
        Bundle bHeader = new Bundle();

        cargaInicial();

        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            if (getIntent().hasExtra(clienteInfo)) {
                responseGetCliente = (ResponseGetCliente) recepcion.getSerializable(clienteInfo);
                getInfoCliente(responseGetCliente);
                responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            }


        }

        mBundle.putString(nombreTit, titulo);
        mBundle.putSerializable(clienteInfo, responseGetCliente);
        mBundle.putString(nombreTit, titulo);
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putInt("itm", 2);
        mBundle.putBoolean(nombreStatus, esAlta);
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();
        aConsultaInfReferencias();
        editaInfoLaboral();
    }

    private void getInfoCliente(ResponseGetCliente responseGetCliente) {
        Cliente datos = responseGetCliente.data.cliente;

        if (datos.idDatosLaborales != null) {
            txtEmpresa.setText(datos.idDatosLaborales.idCaracteristicaNegocio.nombre);
            txtGiro.setText(datos.idDatosLaborales.idGiroNegocio.nombre);
            txtCalleNo.setText(datos.idDatosLaborales.idDireccion.calle + "- No." + datos.idDatosLaborales.idDireccion.numeroExterior);
            txtMunicipio.setText(datos.idDatosLaborales.idDireccion.idMunicipio.nombre);
            txtColonia.setText(datos.idDatosLaborales.idDireccion.idColonia.nombre);
            txtCp.setText(datos.idDatosLaborales.idDireccion.cp);
            if (datos.idDatosLaborales.recibosNomina) {
                txtReciboNomina.setText("Si");
            } else {
                txtReciboNomina.setText("No");
            }
            txtIngresoMensual.setText(String.valueOf(datos.idDatosLaborales.ingresoMensual));
            txtAntiguedad.setText(String.valueOf(datos.idDatosLaborales.fechaIngreso));
            txtPuesto.setText(String.valueOf(datos.idDatosLaborales.puesto));
        }


    }

    private void editaInfoLaboral() {
        btnEditarInfo.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent consultaInfDireccion = new Intent(ConsultaEmpleo.this, DatosClienteLaborales.class);
            consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            boolean status = (receptor.getBoolean(nombreStatus));
            sender.putSerializable("infoLogIn", responseLogIn);
            sender.putString(nombreTit, "Editar Datos del Cliente");
            sender.putSerializable(clienteInfo, responseGetCliente);
            sender.putBoolean(nombreStatus, status);
            consultaInfDireccion.putExtras(sender);
            startActivity(consultaInfDireccion);
        });
    }

    private void aConsultaInfReferencias() {
        btnSiguiente.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Intent consultaInfDireccion = new Intent(ConsultaEmpleo.this, ConsultaReferencia.class);
            consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            bundle.putSerializable("infoLogIn", responseLogIn);
            bundle.putString(nombreTit, titulo);
            bundle.putBoolean(nombreStatus, esAlta);
            bundle.putSerializable(clienteInfo, responseGetCliente);
            consultaInfDireccion.putExtras(bundle);
            startActivity(consultaInfDireccion);
        });
    }

    private void cargaInicial() {
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnEditarInfo = findViewById(R.id.btnEditarInfo);
        txtEmpresa = findViewById(R.id.txtEmpresa);
        txtCalleNo = findViewById(R.id.txtCalleNo);
        txtIngresoMensual = findViewById(R.id.txtIngresoMensual);
        txtCp = findViewById(R.id.txtCp);
        txtMunicipio = findViewById(R.id.txtMunicipio);
        txtReciboNomina = findViewById(R.id.txtReciboNomina);
        txtGiro = findViewById(R.id.txtGiro);
        txtAntiguedad = findViewById(R.id.txtAntiguedad);
        txtColonia = findViewById(R.id.txtColonia);
        txtPuesto = findViewById(R.id.txtPuesto);


    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {
        Log.d("consultaC", "se ejecuta en transfiere info: " + 2);
    }
}