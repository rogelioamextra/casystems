package com.amextra.SolicitudCredito;


import static android.os.Build.VERSION.SDK_INT;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.RequestProyeccion;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ListaCliente;
import com.amextra.io.Response.ListaProyeccion;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponseSolicitudProyeccion;
import com.amextra.utils.ListaClienteAdapter;
import com.amextra.utils.ListaProyeccionAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import cn.pedant.SweetAlert.SweetAlertDialog ;

public class ProyeccionCuotas extends AppCompatActivity {
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    RequestProyeccion requestProyeccion = new RequestProyeccion();
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    TextView txtRegresar;
    TableLayout table;
    int STATUS_OK = 200;
    String COLOR_TEXT_TAB = "#B43C96";
    String INFO_USER = "infoLogIn";
    Button btnSiguiente;
    String N_REQ_SOL_CRED = "REQSOLCRED";
    String N_REQ_PROYEC = "REQPROYEC";
    private static final int PERMISSION_REQUEST_CODE = 200;

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();

    TextView nombre, clienteId;
    InfoUSer responseLogIn = new InfoUSer();
    ListView listProyeccion;

    private ListaProyeccionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyeccion_cuotas);

        Bundle recepcion = getIntent().getExtras();
        Bundle bHeader = new Bundle();
        txtRegresar = findViewById(R.id.txtRegresar);
        nombre = findViewById(R.id.nombre);
        clienteId = findViewById(R.id.clienteId);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        listProyeccion = findViewById(R.id.listProyeccion);
        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);
            titulo = (recepcion.getString(nombreTit));
            requestProyeccion = (RequestProyeccion) recepcion.getSerializable(N_REQ_PROYEC);
            requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
            if (getIntent().hasExtra("nombre")) {
                String name = recepcion.getString("nombre");
                nombre.setText(name.toUpperCase());
            }
            if (getIntent().hasExtra("idCliente")) {
                clienteId.setText(recepcion.getString("idCliente"));
            }
            generaProyeccion(requestProyeccion);
        }
        bHeader.putSerializable("infoLogIn", responseLogIn);
        bHeader.putString(nombreTit, titulo);
        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();

        boolean permisosn = checkPermission();
        if (!permisosn) {
            doAllow();
        }
        regreso();
        toIngresos();

    }

    private void doAllow() {

        if (SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);

            }
        }
    }

    private void toIngresos() {
        btnSiguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Intent intent = new Intent(ProyeccionCuotas.this, DatosAdicionales.class);
            Bundle receptor = getIntent().getExtras();
            boolean status = (receptor.getBoolean(nombreStatus));
            sender.putString(nombreTit, titulo);
            sender.putSerializable(INFO_USER,responseLogIn);
            sender.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
            sender.putBoolean(nombreStatus, status);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtras(sender);
            startActivity(intent);
        });
    }


    private void generaProyeccion(RequestProyeccion req) {
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Cargando información...");
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseSolicitudProyeccion> call = ApiAdapter.getApiService(responseLogIn.token).solicitudProyeccion(req);
        call.enqueue(new Callback<ResponseSolicitudProyeccion>() {
            @Override
            public void onResponse(Call<ResponseSolicitudProyeccion> call, Response<ResponseSolicitudProyeccion> response) {
                int code = response.code();
                if (response.isSuccessful() && code == STATUS_OK) {
                    ResponseSolicitudProyeccion resp = response.body();
                    if (resp.response.codigo == STATUS_OK) {
                        DecimalFormat formatter = new DecimalFormat("###,###.###");
                        savePdf(resp.data.pdf);
                        ArrayList<ListaProyeccion> infoProyeccion = resp.data.listaProyeccion;
                        pintaListaClientes(infoProyeccion);
                        dialogFragment.dismiss();

                    }else{
                        dialogFragment.dismiss();
                        new SweetAlertDialog(ProyeccionCuotas.this,SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText("" + resp.response.codigo+" - "+resp.response.mensaje)
                                .show();
                    }
                }
                dialogFragment.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseSolicitudProyeccion> call, Throwable t) {
                    dialogFragment.dismiss();
            }
        });

    }

    private void pintaListaClientes(ArrayList<ListaProyeccion> proyeccions) {
        if (proyeccions.size() > 0) {
            adapter = new ListaProyeccionAdapter(this, R.layout.custom_proyeccion_card, proyeccions);
            listProyeccion.setAdapter(adapter);
            listProyeccion.setTextFilterEnabled(true);
            listProyeccion.setVisibility(View.VISIBLE);
        }
    }


    private void savePdf(String base64) {
        try {
            String fileName = "amextra_cte" + requestSolicitudCredito.data.clienteId + "_" + System.currentTimeMillis();
            final File dwldsPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName + ".pdf");
            byte[] pdfAsBytes = Base64.decode(base64, 0);
            FileOutputStream os;
            os = new FileOutputStream(dwldsPath, false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();

            new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Advertencia")
                    .setContentText("Archivo guardado: " + fileName)
                    .show();

        } catch (IOException e) {

            new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("savePdf: " + e)
                    .show();
        }


    }

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(ProyeccionCuotas.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(ProyeccionCuotas.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void regreso() {
        txtRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}