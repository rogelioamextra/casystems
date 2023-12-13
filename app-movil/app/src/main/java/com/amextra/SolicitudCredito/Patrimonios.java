package com.amextra.SolicitudCredito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.amextra.MenuHomeScreen;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.ManejaPatrimonio;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuSolicitudCredito;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.PatrimoniosCls;
import com.amextra.io.Request.Referencia;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ListaPatrimonio;
import com.amextra.io.Response.Patrimonio;
import com.amextra.io.Response.ResponseAddSolicitudCredito;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponsePatrimonios;
import com.amextra.utils.ListaPatrimoniosAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Patrimonios extends AppCompatActivity
        implements ManejaPatrimonio.EnviaInformacion ,
        MenuSolicitudCredito.TransfiereDatos,
        ListaPatrimoniosAdapter.Listener{

    Button  generaSolicitud;

    String nombreTit = "Titulo";
    String titulo;
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    String N_REQ_SOL_CRED = "REQSOLCRED";
    ImageButton btnAgregaPatrimonio;
    ArrayList<Patrimonio> listaPatrimonios = new ArrayList<>();
   ArrayList<PatrimoniosCls> newPatrimonios = new ArrayList<>();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentPat= getSupportFragmentManager();
    final MenuSolicitudCredito menuPat = new MenuSolicitudCredito();
    final androidx.fragment.app.FragmentTransaction mFragmentTransactPat = mFragmentPat.beginTransaction();
    InfoUSer responseLogIn = new InfoUSer();
    private FusedLocationProviderClient fusedLocationClient;
    Bundle bTransact = new Bundle();
    Bundle bHeader = new Bundle();

    LinearLayout noContent;

    ListView listPatrimonios;

     ListaPatrimoniosAdapter adapter;
     LinearLayout totalPrecios;
     TextView txtTotal;

     boolean isEditing = false;
     int itemIsEditing;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    boolean existeInfo =  false;
    String INFO_USER = "infoLogIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonios);

        Bundle recepcion = getIntent().getExtras();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btnAgregaPatrimonio = findViewById(R.id.btnAgregaPatrimonio);
        noContent = findViewById(R.id.noContent);
        generaSolicitud = findViewById(R.id.generaSolicitud);
        listPatrimonios = findViewById(R.id.listPatrimonios);
        totalPrecios = findViewById(R.id.totalPrecios);
        txtTotal = findViewById(R.id.txtTotal);
        totalPrecios.setVisibility(View.GONE);




        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable(INFO_USER);




            if(getIntent().hasExtra(N_REQ_SOL_CRED)){
                requestSolicitudCredito = (RequestSolicitudCredito) recepcion.getSerializable(N_REQ_SOL_CRED);
                if(requestSolicitudCredito.data.patrimonios!=null){
                    newPatrimonios  = requestSolicitudCredito.data.patrimonios;
                    existeInfo= true;

                }
            }

        }





        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_CAMERA_REQUEST_CODE);

        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    requestSolicitudCredito.getData().setLatitud(String.valueOf(location.getLatitude()));
                    requestSolicitudCredito.getData().setLongitud(String.valueOf(location.getLongitude()));
                }
            }
        });



        bHeader.putString(nombreTit,titulo);
        bHeader.putSerializable(INFO_USER,responseLogIn);
        bTransact.putSerializable(INFO_USER,responseLogIn);

        bTransact.putSerializable(N_REQ_SOL_CRED,requestSolicitudCredito);
        bTransact.putInt("itm",2);
        bTransact.putString(nombreTit,titulo);

        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader,menuHeader).commit();
        menuPat.setArguments(bTransact);
        mFragmentTransactPat.add(R.id.frameLayout, menuPat).commit();

        traePatrimonios();
        altaSolicitudCredito();
        clickbtnAgregaPatrimonio();


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

    private void altaSolicitudCredito() {
        generaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment =  LoaderTransparent.loaderTransparent("Generando Solicitud de Credito");
                if( newPatrimonios.size()>0 && requestSolicitudCredito.data != null){
                    dialogFragment.show(getSupportFragmentManager(),"LoaderTransparent");
                    replacePathToBase64();
                    requestSolicitudCredito.getData().setPatrimonios(newPatrimonios);
                    Call<ResponseAddSolicitudCredito> call = ApiAdapter.getApiService(responseLogIn.token).addSolicitudCredito(requestSolicitudCredito);
                    call.enqueue(new Callback<ResponseAddSolicitudCredito>() {
                        @Override
                        public void onResponse(Call<ResponseAddSolicitudCredito> call, Response<ResponseAddSolicitudCredito> response) {
                            int code = response.code();
                            boolean status = response.isSuccessful();
                            if(code == 200 && status){
                                ResponseAddSolicitudCredito respuesta = response.body();
                                long finalCode =respuesta.response.codigo;
                                if(finalCode == 200){
                                    Bundle sender = new Bundle();
                                    Toast.makeText(Patrimonios.this, "Solicitud de crédito generada: "+respuesta.data.solicitudId, Toast.LENGTH_SHORT).show();
                                    sender.putSerializable(INFO_USER,responseLogIn);

                                    requestSolicitudCredito = new RequestSolicitudCredito();
                                    newPatrimonios = new ArrayList<>();
                                    requestSolicitudCredito = new RequestSolicitudCredito();
                                    Intent home = new Intent(Patrimonios.this, MenuHomeScreen.class);
                                    home.putExtras(sender);
                                    home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    new SweetAlertDialog(Patrimonios.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    startActivity(home);
                                                    finish();
                                                }
                                            })
                                            .setTitleText("Solicitud de crédito")
                                            .setContentText("Solicitud generada,"+respuesta.response.codigo+" - "+respuesta.response.mensaje+" No Solicitud : "+respuesta.data.solicitudId )
                                            .show();


                                    dialogFragment.dismiss();
                                }else{
                                    dialogFragment.dismiss();
                                    new SweetAlertDialog(Patrimonios.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Solicitud de crédito")
                                            .setContentText("Error al generar la solicitud"+respuesta.response.codigo+" - "+respuesta.response.mensaje)
                                            .show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseAddSolicitudCredito> call, Throwable t) {

                        }
                    });
                }else{
                    dialogFragment.dismiss();
                    new SweetAlertDialog(Patrimonios.this, SweetAlertDialog.WARNING_TYPE)
                            .setContentText("Se debe capturar almenos un patrimonio, por favor realice esta captura")
                            .setTitleText("Advertencia")
                            .show();
                }



            }
        });
    }

    private void clickbtnAgregaPatrimonio() {
        btnAgregaPatrimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManejaPatrimonio dialogFragment = ManejaPatrimonio.manejaPatrimonio(listaPatrimonios, null);
                dialogFragment.show(getSupportFragmentManager(), "ManejaPatrimonio");
                isEditing = false;
            }
        });
    }

    private void replacePathToBase64(){
        if(newPatrimonios.size()> 0){
            for (int i = 0; i <newPatrimonios.size() ; i++) {
                PatrimoniosCls tmpPat = newPatrimonios.get(i);
                String path = tmpPat.imagen;
                String base64 = pathToBase64(path,100);
                tmpPat.setImagen(base64);
            }
        }
    }

    private void traePatrimonios() {
        Call<ResponsePatrimonios> call = ApiAdapter.getApiService(responseLogIn.token).patrimonios();
        call.enqueue(new Callback<ResponsePatrimonios>() {
            @Override
            public void onResponse(Call<ResponsePatrimonios> call, Response<ResponsePatrimonios> response) {
                int code = response.code();
                boolean ok = response.isSuccessful();
                if (code == 200 && ok) {
                    ResponsePatrimonios responsePatrimonios = response.body();
                    if (responsePatrimonios.response.codigo == 200) {
                        listaPatrimonios = responsePatrimonios.data.patrimonios;
                        if(existeInfo){
                            llenaTablaPats();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePatrimonios> call, Throwable t) {

            }
        });
    }


    @Override
    public void transfierePatrimonio(PatrimoniosCls pat) {
        if (pat != null) {
            if(isEditing){
                PatrimoniosCls tmp =  newPatrimonios.get(itemIsEditing);

                tmp.setTipoPatrimonioID(pat.tipoPatrimonioId);
                tmp.setPrecio(pat.precio);
                tmp.setCambioImagen(pat.cambioImagen);
                tmp.setImagen(pat.imagen);
                llenaTablaPats();

            }else{
                newPatrimonios.add(pat);
                llenaTablaPats();
            }
        }

    }

    private String obtenNombrePatrimonio(String idPatrimonio) {
        String patr = "";

        if(listaPatrimonios.size()>0){
            for (Patrimonio item : listaPatrimonios) {
                String id = String.valueOf(item.idPatrimonio);
                String name = item.nombre;
                if (idPatrimonio.equals(id)) {
                    patr = name;
                    break;
                }

            }
        }

        return patr;
    }

    private String pathToBase64(String path, int quality) {
        File imgFile = new  File(path);
        String endBse = "";
        if(imgFile.exists()){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            endBse = Base64.encodeToString(byteArray, Base64.NO_WRAP);;
        }else{
            endBse = path;
        }

        return endBse;


    }

    @SuppressLint("ResourceAsColor")
    private void llenaTablaPats() {
        if (newPatrimonios.size() > 0) {
            long totalPatrimonios = 0;
            ArrayList<ListaPatrimonio> patAdapter = new ArrayList<>();
            DecimalFormat formatter = new DecimalFormat("###,###.###");

            for (PatrimoniosCls patrim: newPatrimonios) {
                ListaPatrimonio tmpPat = new ListaPatrimonio();
                long monto =Long.parseLong(patrim.precio);
                String nombreP = obtenNombrePatrimonio(patrim.tipoPatrimonioId);

                tmpPat.setNombre(nombreP);
                tmpPat.setMonto("$" + formatter.format(monto));
                totalPatrimonios = totalPatrimonios +monto;
                patAdapter.add(tmpPat);
            }
            txtTotal.setText("$" + formatter.format(totalPatrimonios));
            adapter = new ListaPatrimoniosAdapter(this, R.layout.custom_patrimonio_row, patAdapter);

            listPatrimonios.setAdapter(adapter);
            noContent.setVisibility(View.GONE);
            listPatrimonios.setVisibility(View.VISIBLE);
            totalPrecios.setVisibility(View.VISIBLE);



        } else {
            listPatrimonios.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
            totalPrecios.setVisibility(View.GONE);
        }


    }


    @Override
    public void transfiereinfocredito() {

        if(newPatrimonios.size() >0){
            requestSolicitudCredito.getData().setPatrimonios(newPatrimonios);
        }
        bTransact.putSerializable(N_REQ_SOL_CRED,requestSolicitudCredito);
        bTransact.putString(nombreTit,titulo);
        bTransact.putSerializable(INFO_USER,responseLogIn);
        menuPat.setArguments(bTransact);

    }


    @Override
    public void deleteItem(int id) {
        newPatrimonios.remove(id);
        if(newPatrimonios.size()>0){

            for (PatrimoniosCls pat:newPatrimonios) {
                Log.d("borraado", "deleteItem: "+pat.tipoPatrimonioId);
                Log.d("borraado", "deleteItem: "+pat.precio);
                Log.d("borraado", "deleteItem: "+pat.imagen);

            }
        }
        llenaTablaPats();

    }

    @Override
    public void editItem(int id) {
        PatrimoniosCls item = newPatrimonios.get(id);
        itemIsEditing = id;
        ManejaPatrimonio dialogFragment = ManejaPatrimonio.manejaPatrimonio(listaPatrimonios,item);
        dialogFragment.show(getSupportFragmentManager(), "ManejaPatrimonio");
        isEditing = true;
    }
}