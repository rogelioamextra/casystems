package com.amextra.ConsultaInfoCliente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.AltaEdicionCliente.ReferenciasAlta;
import com.amextra.SMS.EnviaSMS;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.DtReferenciasPersonalesList;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.utils.ListaReferenciasAdapter;

public class ConsultaReferencia extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Button btnSiguiente, btnEditarInfo;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    TableLayout tableRef;
    String titulo;
    boolean esAlta;
    String clienteInfo = "INFO_CLIENT";
    boolean esEdicionOalta = false;
    String esAltaOedicion = "ALTA-EDICION";
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    ListView listaReferencias;
    private ListaReferenciasAdapter adapter;
    LinearLayout noContent;

    InfoUSer responseLogIn = new InfoUSer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_referencia);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnEditarInfo = findViewById(R.id.btnEditarInfo);
        listaReferencias = findViewById(R.id.listaReferencias);
        noContent = findViewById(R.id.noContent);


        Bundle recepcion = getIntent().getExtras();
        Bundle mBundle = new Bundle();
        Bundle bHeader = new Bundle();

        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            Log.d("respLog", "onCreate: " + responseLogIn.usuarioId);

            if (getIntent().hasExtra(clienteInfo)) {
                responseGetCliente = (ResponseGetCliente) recepcion.getSerializable(clienteInfo);
                pintaReferencias(responseGetCliente.data.cliente.dtReferenciasPersonalesList);

            }

        }
        mBundle.putSerializable("infoLogIn", responseLogIn);

        mBundle.putSerializable(clienteInfo, responseGetCliente);
        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 3);
        mBundle.putBoolean(nombreStatus, esAlta);
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        mBundle.putString(nombreTit, titulo);

        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsEnvia = new Intent(ConsultaReferencia.this, EnviaSMS.class);
                Bundle sender = new Bundle();
                sender.putSerializable("infoLogIn", responseLogIn);
                sender.putString("telefono", responseGetCliente.data.cliente.idPersona.telefono);
                sender.putString("curp", responseGetCliente.data.cliente.idPersona.curp);
                smsEnvia.putExtras(sender);
                smsEnvia.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(smsEnvia);
            }
        });
        editaReferencia();


    }

    private void pintaReferencias(DtReferenciasPersonalesList[] dtReferenciasPersonalesLists) {
        if (dtReferenciasPersonalesLists.length > 0) {

            adapter = new ListaReferenciasAdapter(this, R.layout.custom_referencia_card, dtReferenciasPersonalesLists);
            listaReferencias.setAdapter(adapter);
            listaReferencias.setVisibility(View.VISIBLE);
            noContent.setVisibility(View.GONE);
        } else {
            noContent.setVisibility(View.VISIBLE);
            listaReferencias.setVisibility(View.GONE);
        }


    }

    private void editaReferencia() {
        btnEditarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esEdicionOalta = true;
                Bundle sender = new Bundle();
                Intent consultaInfDireccion = new Intent(ConsultaReferencia.this, ReferenciasAlta.class);
                consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                sender.putString(nombreTit, "Editar Datos del Cliente");
                sender.putBoolean(nombreStatus, esAlta);
                sender.putSerializable(clienteInfo, responseGetCliente);
                sender.putSerializable("infoLogIn", responseLogIn);

                consultaInfDireccion.putExtras(sender);
                startActivity(consultaInfDireccion);
            }
        });
    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {
        Log.d("consultaC", "se ejecuta en transfiere info: " + 4);
    }
}