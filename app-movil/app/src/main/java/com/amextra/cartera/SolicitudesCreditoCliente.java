package com.amextra.cartera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponseSolicitudesCreditoCliente;
import com.amextra.io.Response.SolicitudesCredito;
import com.amextra.utils.ListAdapterSolicitudesCredito;
import com.amextra.utils.ListaClienteAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitudesCreditoCliente extends AppCompatActivity {
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;

    EditText texInputSearch;
    String msg = "Consultando Clientes....";

    String noCliente;
    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);
    private ListAdapterSolicitudesCredito adapter;
    private ListView listViewClientes;
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    LinearLayout noContent;

    ImageView back;
    InfoUSer responseLogIn = new InfoUSer();
    TextView txtNombre;
    String nombreTitular ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes_credito_cliente);
        back = findViewById(R.id.back);
        txtNombre = findViewById(R.id.txtNombre);
        listViewClientes = findViewById(R.id.listViewClientes);
        noContent = findViewById(R.id.noContent);

        Bundle bHeader = new Bundle();
        Bundle receptor = getIntent().getExtras();
        if (receptor != null) {
            titulo = (receptor.getString(nombreTit));
            esAlta = (receptor.getBoolean(nombreStatus));
            responseLogIn = (InfoUSer) receptor.getSerializable("infoLogIn");
            noCliente = receptor.getString("idCliente");
            nombreTitular = receptor.getString("nombreCliente");

        }
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(0, 0);

            }
        });
        txtNombre.setText(nombreTitular);
        getSolicitudesCliente(noCliente);
    }


    private void getSolicitudesCliente(String idCliente) {

        Call<ResponseSolicitudesCreditoCliente> call = ApiAdapter.getApiService(responseLogIn.token).solicitudesCreditoCliente(idCliente);
        call.enqueue(new Callback<ResponseSolicitudesCreditoCliente>() {
            @Override
            public void onResponse(Call<ResponseSolicitudesCreditoCliente> call, Response<ResponseSolicitudesCreditoCliente> response) {
                int code = response.code();
                boolean status = response.isSuccessful();
                if (code == 200 && status) {
                    ResponseSolicitudesCreditoCliente infoSolicitudes = response.body();
                    if (infoSolicitudes.response.codigo == 200) {
                        //txtNombre.setText(infoSolicitudes.data.solicitudes.get(0).cliente);
                            pintaSolicitudes(infoSolicitudes.data.solicitudes);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseSolicitudesCreditoCliente> call, Throwable t) {

            }
        });

    }

    private void pintaSolicitudes(ArrayList<SolicitudesCredito> solicitudes) {
        if (solicitudes.size() > 0) {
            adapter = new ListAdapterSolicitudesCredito(this, R.layout.custom_layout_solicitudes_credito_cliente, solicitudes);
            listViewClientes.setAdapter(adapter);
            noContent.setVisibility(View.GONE);
            listViewClientes.setVisibility(View.VISIBLE);

        } else {
            listViewClientes.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
        }

    }
}