package com.amextra;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Response.Aviso;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseAvisos;
import com.amextra.utils.ListAvisosAdapter;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvisosAsesor extends AppCompatActivity {
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    String msg = "Consultando Clientes....";
    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);

    private ListView listViewClientes;
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    LinearLayout noContent;

    InfoUSer responseLogIn = new InfoUSer();

    private ListAvisosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos_asesor);


        listViewClientes = findViewById(R.id.listViewClientes);
        noContent = findViewById(R.id.noContent);


        Bundle bHeader = new Bundle();
        Bundle receptor = getIntent().getExtras();
        if (receptor != null) {
            titulo = (receptor.getString(nombreTit));
            esAlta = (receptor.getBoolean(nombreStatus));
            responseLogIn = (InfoUSer) receptor.getSerializable("infoLogIn");

        }
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        getListaAvisos();
    }


    private void getListaAvisos() {
        Call<ResponseAvisos> call = ApiAdapter.getApiService(responseLogIn.token).avisos();
        call.enqueue(new Callback<ResponseAvisos>() {
            @Override
            public void onResponse(Call<ResponseAvisos> call, Response<ResponseAvisos> response) {
                int code = response.code();
                final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                boolean status = response.isSuccessful();
                if (code == 200 && status) {
                    ResponseAvisos avisos = response.body();
                    if (avisos.response.codigo == 200) {
                        muestraAvisos(avisos.data.avisos);
                    } else {

                    }
                } else {
                    new SweetAlertDialog(AvisosAsesor.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(AvisosAsesor.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAvisos> call, Throwable t) {

            }
        });


    }

    private void muestraAvisos(ArrayList<Aviso> avisos) {


        if (!avisos.isEmpty()) {

            ArrayList<Aviso> ad = new ArrayList<>();
            for (Aviso aviso : avisos) {
                if (aviso.getContenidoAviso() != null && aviso.getTituloAviso() != null) {
                    Aviso tmp = new Aviso();
                    tmp.setContenidoAviso(aviso.getContenidoAviso());
                    tmp.setTituloAviso(aviso.getTituloAviso());
                    ad.add(tmp);
                }
            }

            adapter = new ListAvisosAdapter(this, R.layout.custom_card_avisos, ad);
            listViewClientes.setAdapter(adapter);
            listViewClientes.setVisibility(View.VISIBLE);
            listViewClientes.setClickable(false);
            noContent.setVisibility(View.GONE);

        } else {
            noContent.setVisibility(View.VISIBLE);
            listViewClientes.setVisibility(View.GONE);
        }


    }

}