package com.amextra.SolicitudCredito;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.amextra.MainActivity;
import com.amextra.SolicitudCredito.avalesfuntionality.HandlerAval;
import com.amextra.SolicitudCredito.avalesfuntionality.ListaAvalesAdapter;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuSolicitudCredito;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.Aval;
import com.amextra.io.Request.DescribeIdentificacion;
import com.amextra.io.Request.InformacionAval;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.Identificacione;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseIdentificaicon;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvalesPrincipal extends AppCompatActivity
        implements MenuSolicitudCredito.TransfiereDatos,
        ListaAvalesAdapter.Listener,
        HandlerAval.SendData {


    ImageButton btnAgregaAval;
    Button toPatrimonios;

    String nombreTit = "Titulo";
    String titulo;
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    String N_REQ_SOL_CRED = "REQSOLCRED";

    String INFO_USER = "infoLogIn";
    String nombreStatus = "esAlta";
    LinearLayout noContent, layOutAgregaAval;
    int position;
    boolean isEditing;
    ListaAvalesAdapter adapter;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    String curpCliente = "";
    final String CURP_CLI = "CURP_CLI";

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentPat = getSupportFragmentManager();
    final MenuSolicitudCredito menuPat = new MenuSolicitudCredito();
    final androidx.fragment.app.FragmentTransaction mFragmentTransactPat = mFragmentPat.beginTransaction();


    InfoUSer responseLogIn = new InfoUSer();
    private FusedLocationProviderClient fusedLocationClient;
    Bundle bTransact = new Bundle();
    Bundle bHeader = new Bundle();

    ListView listAvales;

    ArrayList<Aval> avales = new ArrayList<Aval>();

    ArrayList<DescribeIdentificacion> identificacions = new ArrayList<>();

    String token = "";


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avales_principal);

        loadComponents();
        Bundle recepcion = getIntent().getExtras();
        noContent.setVisibility(View.GONE);
        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);
            curpCliente = recepcion.getString(CURP_CLI);
            Log.d("login", responseLogIn.token);
            token = responseLogIn.token;

            if (getIntent().hasExtra(N_REQ_SOL_CRED)) {
                requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
                if (!requestSolicitudCredito.getData().getAvales().isEmpty()) {
                    avales = requestSolicitudCredito.getData().getAvales();

                }
            }

        }
        drawAvales(avales);
        loadCatalogoIdentificacion();

        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable(INFO_USER, responseLogIn);
        bTransact.putSerializable(INFO_USER, responseLogIn);

        bTransact.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bTransact.putInt("itm", 5);
        bTransact.putString(nombreTit, titulo);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.MANAGE_MEDIA}, MY_CAMERA_REQUEST_CODE);

        }

        toPatrimonios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToPatrimonios();
            }
        });
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        menuPat.setArguments(bTransact);
        mFragmentTransactPat.add(R.id.frameLayout, menuPat).commit();
        layOutAgregaAval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("toFragment", identificacions.get(0).getDescribeId());
                HandlerAval dialog = HandlerAval.handlerAval(identificacions, null, token, curpCliente);
                dialog.show(getSupportFragmentManager(), "HandlerAval");

            }
        });

    }

    private void loadCatalogoIdentificacion() {
        Call<ResponseIdentificaicon> call = ApiAdapter.getApiService(token).identificaciones();
        call.enqueue(new Callback<ResponseIdentificaicon>() {
            @Override
            public void onResponse(Call<ResponseIdentificaicon> call, Response<ResponseIdentificaicon> response) {
                int code = response.code();
                ResponseIdentificaicon responseIdentificaicon = response.body();
                if (response.isSuccessful() && code == 200) {
                    Identificacione[] identificaciones = responseIdentificaicon.data.identificaciones;

                    for (Identificacione identificacion : identificaciones) {
                        Log.d("idsdesc", identificacion.nombre.toUpperCase());
                        DescribeIdentificacion tmp = new DescribeIdentificacion();
                        tmp.setDescribeId(identificacion.nombre.toUpperCase());
                        tmp.setIdIdentificacion(identificacion.idIdentificaciones);
                        identificacions.add(tmp);
                    }


                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(AvalesPrincipal.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(AvalesPrincipal.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponseIdentificaicon> call, Throwable t) {
                Log.d("fallo", t.toString());
            }
        });


    }

    @Override
    public void transfiereinfocredito() {
        if (avales.size() > 0) {
            requestSolicitudCredito.getData().setAvales(avales);

        }
        bTransact.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bTransact.putString(nombreTit, titulo);
        bTransact.putSerializable(INFO_USER, responseLogIn);
        menuPat.setArguments(bTransact);

    }


    private void setToPatrimonios() {
        if (avales.size() > 0) {
            requestSolicitudCredito.getData().setAvales(avales);
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent patrimonios = new Intent(AvalesPrincipal.this, Patrimonios.class);
            boolean status = (receptor.getBoolean(nombreStatus));
            sender.putString(nombreTit, titulo);
            sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
            sender.putSerializable(INFO_USER, responseLogIn);
            sender.putBoolean(nombreStatus, status);
            sender.putString(CURP_CLI, curpCliente);
            patrimonios.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            patrimonios.putExtras(sender);
            startActivity(patrimonios);

        } else {
            SweetAlertDialog sw = new SweetAlertDialog(AvalesPrincipal.this, SweetAlertDialog.WARNING_TYPE);
            sw.setTitleText("Alerta");
            sw.setContentText("Se debe contar con almenos una referencia para poder generar una solicitud.");
            sw.setConfirmText("Continuar");
            sw.setConfirmClickListener(sweetAlertDialog -> {
                sw.dismiss();
            });
            sw.show();
        }
    }

    private void loadComponents() {
        btnAgregaAval = findViewById(R.id.btnAgregaAval);
        toPatrimonios = findViewById(R.id.toPatrimonios);
        layOutAgregaAval = findViewById(R.id.layOutAgregaAval);
        noContent = findViewById(R.id.noContent);
        listAvales = findViewById(R.id.listAvales);
    }


    private void drawAvales(ArrayList<Aval> avales) {
        if (avales.isEmpty()) {
            listAvales.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
        } else {
            ArrayList<InformacionAval> avals = new ArrayList<InformacionAval>();

            for (Aval aval : avales) {
                InformacionAval tmp = new InformacionAval();
                tmp.setNombreCompleto(aval.getNombre() + " " + aval.getApellidoPaterno() + " " + aval.getApellidoMaterno());
                tmp.setTelefono(aval.getTelefono());
                avals.add(tmp);
            }

            adapter = new ListaAvalesAdapter(this, R.layout.custum_aval_row, avals);
            listAvales.setAdapter(adapter);
            listAvales.setVisibility(View.VISIBLE);
            noContent.setVisibility(View.GONE);

        }


    }

    @Override
    public void deleteItem(int id) {
        avales.remove(id);
        drawAvales(avales);
    }


    @Override
    public void editItem(int id) {

        position = id;
        isEditing = true;

        Log.d("editAval", avales.get(id).toString());
        HandlerAval dialog = HandlerAval.handlerAval(identificacions, avales.get(id), token, curpCliente);
        dialog.show(getSupportFragmentManager(), "HandlerAval");
    }

    @Override
    public void sendAvalInfo(Aval aval) {
        Log.d("addAval", aval.toString());
        if (isEditing) {
            avales.set(position, aval);
            isEditing = false;
        } else {
            avales.add(aval);
        }

        drawAvales(avales);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permiso granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "permiso denied", Toast.LENGTH_LONG).show();
            }

        }


    }
}