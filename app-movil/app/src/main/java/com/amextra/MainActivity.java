package com.amextra;



import static com.amextra.utils.Constants.NO_TOKEN_VALUE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


import com.amextra.Beans.Data;
import com.amextra.Beans.RequestLogin;
import com.amextra.Security.ResetPass;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.ResponseLogin;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import cn.pedant.SweetAlert.SweetAlertDialog ;

public class MainActivity extends AppCompatActivity implements Callback<ResponseLogin> {
    Button btnIniciaSesion;
    ResponseLogin responseLogIn = new ResponseLogin();

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    TextView forgotPassword;
    private final Geolocalizacion geolocalizacion = new Geolocalizacion();

    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;

    EditText texInputEmail;

    EditText texInputPassword;
    int duration = Toast.LENGTH_SHORT;
    CharSequence text = "";
    String nombreTit = "Titulo";
    String titulo = "Consulta de Clientes";
    Toast toast = null;

    String msg = "Iniciando Sesion....";
    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);
    String INFO_USER = "infoLogIn";
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        forgotPassword = findViewById(R.id.forgotPassword);
        btnIniciaSesion = findViewById(R.id.btnIniciaSesion);
        texInputEmail = findViewById(R.id.texInputEmail);
        texInputPassword = findViewById(R.id.texInputPassword);
        goToForgotPassword();
        iniciaSesion();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_CAMERA_REQUEST_CODE);

        }




        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    geolocalizacion.setLatitude(String.valueOf(location.getLatitude()));
                    geolocalizacion.setLongitud(String.valueOf(location.getLongitude()));
                    currentLocation=location;
                    Log.d("loc", "onLocationResult: "+currentLocation.getLatitude());

                }else{
                    buildLocationCallback();
                }
            }
        });

    }


    private void buildLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    currentLocation=location;
                    geolocalizacion.setLatitude(String.valueOf(currentLocation.getLatitude()));
                    geolocalizacion.setLongitud(String.valueOf(currentLocation.getLongitude()));
                    Log.d("loc", "onLocationResult: "+currentLocation.getLatitude());
                }
            };
        };
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {

                new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Permisos no proporcionados, por favor proporcione los permisos solicitados para poder continuar")
                        .show();

            }

        }


    }




    private void iniciaSesion() {
        btnIniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");


                String usr = texInputEmail.getText().toString().trim();
                String pwd = texInputPassword.getText().toString().trim();
                generaSesion(usr, pwd);
            }
        });
    }

    private void goToForgotPassword() {
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuScreenIntent = new Intent(MainActivity.this, ResetPass.class);
                startActivity(menuScreenIntent);
            }
        });


    }


    private void generaSesion(String usuario, String password) {

        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setData(new Data());
        Context context = getApplicationContext();

        texInputEmail.setActivated(false);
        texInputPassword.setActivated(false);

        if (usuario.equals("")) {
            texInputEmail.setError("Usuario requerido");
            dialogFragment.dismiss();
            texInputEmail.setActivated(true);


        } else if(password.equals("")){
            dialogFragment.dismiss();
            texInputPassword.setError("Contraseña requerida");

        }
        else {

            requestLogin.getData().setUsuario(usuario);
            requestLogin.getData().setPassword(password);
            Call<ResponseLogin> callLogin = ApiAdapter.getApiService(NO_TOKEN_VALUE).postIniciaSesion(requestLogin);
            callLogin.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

        if (response.isSuccessful()) {
            Bundle transfiereDatos = new Bundle();
            Intent menuScreenIntent = new Intent(MainActivity.this, MenuHomeScreen.class);
            responseLogIn = response.body();
            int code = response.code();

            if (response.isSuccessful() && code == 200) {
                if (responseLogIn.response.codigo == 200) {
                    transfiereDatos.putString(nombreTit, titulo);
                    transfiereDatos.putSerializable("geo", geolocalizacion);
                    transfiereDatos.putSerializable("infoLogIn",responseLogIn.data.infoUSer);

                    menuScreenIntent.putExtras(transfiereDatos);
                    menuScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(menuScreenIntent);
                    dialogFragment.dismiss();

                }else{

                    text = responseLogIn.response.codigo + "  " + responseLogIn.response.mensaje;
                    dialogFragment.dismiss();
                    texInputEmail.setActivated(true);
                    texInputPassword.setActivated(true);
                    new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Advertencia")
                            .setContentText(text.toString())
                            .show();

                }
            }

        }

    }

    @Override
    public void onFailure(Call<ResponseLogin> call, Throwable t) {

        text = (t.toString());
        dialogFragment.dismiss();
        texInputEmail.setActivated(true);
        texInputPassword.setActivated(true);
        Toast.makeText(MainActivity.this,text.toString(),Toast.LENGTH_LONG).show();
        new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error de comunicacion")
                .setContentText("Favor de contactar a su administrador de sistemas")
                .show();


    }


}