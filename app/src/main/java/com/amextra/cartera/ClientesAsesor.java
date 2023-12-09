package com.amextra.cartera;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
import android.widget.Toast;

import com.amextra.MainActivity;
import com.amextra.MenuHomeScreen;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.io.ApiAdapter;
import com.amextra.io.ApiService;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ListaCliente;
import com.amextra.io.Response.ResponseGetClientes;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.utils.ListaClienteAdapter;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientesAsesor extends AppCompatActivity {
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
    InfoUSer responseLogIn = new InfoUSer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_asesor);
        Bundle bHeader = new Bundle();
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

        getClientesAsesor(responseLogIn.usuarioId);

    }



    private void getClientesAsesor (String idAsesor){
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseGetClientes> call = ApiAdapter.getApiService(responseLogIn.token).getClientesAsesor(idAsesor);
        call.enqueue(new Callback<ResponseGetClientes>() {
            @Override
            public void onResponse(Call<ResponseGetClientes> call, Response<ResponseGetClientes> response) {
                int code = response.code();
                final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                boolean estatus = response.isSuccessful();
                if(code == 200 && estatus){
                    ResponseGetClientes clientes = response.body();
                    if(clientes.response.codigo==200){
                        pintaListaClientes(clientes.data.listaClientes);
                        dialogFragment.dismiss();
                    }else{
                        dialogFragment.dismiss();
                    }
                }

                else {
                    new SweetAlertDialog(ClientesAsesor.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(ClientesAsesor.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetClientes> call, Throwable t) {

            }
        });
    }


    private void pintaListaClientes(ArrayList<ListaCliente> clientes) {
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
                    String nombreCliente = info.nombre;
                    toDetalleSolicitudesCliente(noCliente,nombreCliente);

                }
            });
            listViewClientes.setVisibility(View.VISIBLE);
            noContent.setVisibility(View.GONE);
        }else{
            listViewClientes.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
        }
    }

    private void toDetalleSolicitudesCliente(String idCliente,String nombre){
        Bundle enviaDatos = new Bundle();
        Intent clientScreenIntent = new Intent(ClientesAsesor.this, SolicitudesCreditoCliente.class);
        enviaDatos.putBoolean(nombreStatus,false);
        enviaDatos.putString(nombreTit,"Detalle solicitudes de credito");
        enviaDatos.putString("idCliente",idCliente);
        enviaDatos.putString("nombreCliente",nombre);
        enviaDatos.putSerializable("infoLogIn",responseLogIn);
        clientScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        clientScreenIntent.putExtras(enviaDatos);
        startActivity(clientScreenIntent);
    }

}