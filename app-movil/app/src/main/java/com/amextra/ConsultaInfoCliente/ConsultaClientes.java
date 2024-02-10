package com.amextra.ConsultaInfoCliente;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.amextra.AltaEdicionCliente.ReferenciasAlta_dos;
import com.amextra.MainActivity;
import com.amextra.MenuHomeScreen;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Response.DataGetClientes;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ListaCliente;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseGetClientes;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.utils.ListaClienteAdapter;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConsultaClientes extends AppCompatActivity implements Callback<ResponseGetClientes> {


    TableLayout table;
    LinearProgressIndicator loader;
    String cliente = "idCliente";
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    Toast toast = null;
    CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    String clienteInfo = "INFO_CLIENT";

    EditText texInputSearch;
    String msg = "Consultando Clientes....";
    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);
    private ListaClienteAdapter adapter;
    private ListView listViewClientes;
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    LinearLayout noContent;
    ArrayList<ListaCliente> clientes;
    InfoUSer responseLogIn = new InfoUSer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bHeader = new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_clientes);
        texInputSearch = findViewById(R.id.texInputSearch);
        listViewClientes = findViewById(R.id.listViewClientes);
        noContent = findViewById(R.id.noContent);
        Bundle receptor = getIntent().getExtras();
        if (receptor != null) {
            titulo = (receptor.getString(nombreTit));
            esAlta = (receptor.getBoolean(nombreStatus));
            responseLogIn = (InfoUSer) receptor.getSerializable("infoLogIn");


        }

        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn",responseLogIn);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        getListaClientes();
        texInputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("TAG", "onTextChanged: "+s.toString().toUpperCase());
                adapter.getFilter().filter(s.toString().toUpperCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void getListaClientes() {
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseGetClientes> call = ApiAdapter.getApiService(responseLogIn.token).getClientesAsesor(responseLogIn.usuarioId);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ResponseGetClientes> call, Response<ResponseGetClientes> response) {
        int code = response.code();
        final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;

        Context context = getApplicationContext();
        ResponseGetClientes responseClientes = response.body();
        if (code == 200) {
            if (responseClientes.response.codigo == 200) {
                DataGetClientes datos = responseClientes.data;
                clientes = datos.listaClientes;
                pintaListaClientes(clientes);
                // pintaTabla(clientes);
                dialogFragment.dismiss();
            } else {
                text = responseClientes.response.codigo + "  " + responseClientes.response.mensaje;
                toast = Toast.makeText(context, text, duration);
                dialogFragment.dismiss();
                toast.show();
            }
        }

        else {
            new SweetAlertDialog(ConsultaClientes.this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText(alertText)
                    .setConfirmText("Continuar")
                    .setConfirmClickListener(sweetAlertDialog -> {
                        finish();
                        Intent login = new Intent(ConsultaClientes.this, MainActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(login);
                    })
                    .show();
        }
}


    public void getInfoCliente(String id) {
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseGetCliente> call = ApiAdapter.getApiService(responseLogIn.token).getCliente(id);
        call.enqueue(new Callback<ResponseGetCliente>() {
            @Override
            public void onResponse(Call<ResponseGetCliente> call, Response<ResponseGetCliente> response) {
                int code = response.code();
                boolean stat = response.isSuccessful();
                if (code == 200 && stat) {
                    ResponseGetCliente datos = response.body();
                    if (datos.response.codigo == 200) {
                        Bundle sender = new Bundle();
                        Intent activity = new Intent(ConsultaClientes.this, ConsultaPersonal.class);

                        activity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        sender.putSerializable("infoLogIn",responseLogIn);
                        sender.putSerializable(clienteInfo, datos);
                        sender.putBoolean(nombreStatus, esAlta);
                        sender.putString(nombreTit, "Información del Cliente");
                        activity.putExtras(sender);
                        dialogFragment.dismiss();
                        startActivity(activity);

                    } else {
                        dialogFragment.dismiss();
                        Toast.makeText(ConsultaClientes.this, datos.response.codigo+" - "+datos.response.mensaje, Toast.LENGTH_SHORT).show();

                    }
                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(ConsultaClientes.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(ConsultaClientes.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetCliente> call, Throwable t) {
                dialogFragment.dismiss();
                Toast.makeText(ConsultaClientes.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void pintaListaClientes(ArrayList<ListaCliente> clientes) {
        Log.d("clientes.size()", "pintaListaClientes: "+clientes.size());
        ArrayList<String> idClientes = new ArrayList<>();
        if (clientes.size() > 0) {
            for (ListaCliente cliente : clientes) {
                idClientes.add(cliente.id);
            }
            adapter = new ListaClienteAdapter(this, R.layout.custom_list_card, clientes);
            listViewClientes.setAdapter(adapter);
            listViewClientes.setTextFilterEnabled(true);
            listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListaCliente info = (ListaCliente) parent.getAdapter().getItem(position);
                    String noCliente = info.id;
                    getInfoCliente(noCliente);

                }
            });
            listViewClientes.setVisibility(View.VISIBLE);
            noContent.setVisibility(View.GONE);
        }else{
             listViewClientes.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onFailure(Call<ResponseGetClientes> call, Throwable t) {
        Context context = getApplicationContext();
        text = (t.toString());
        toast = Toast.makeText(context, text, duration);
        loader.hide();
        toast.show();
    }
}