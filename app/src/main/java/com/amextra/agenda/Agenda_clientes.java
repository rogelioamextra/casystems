package com.amextra.agenda;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.amextra.MainActivity;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataCitasAgente;
import com.amextra.io.Request.DataRegistroAsistencia;
import com.amextra.io.Request.RequestCitasEmpleado;
import com.amextra.io.Request.RequestRegistraAsistencia;
import com.amextra.io.Response.Agenda;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseAgenda;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.utils.ListaCitasAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Calendar;


public class Agenda_clientes extends AppCompatActivity implements ListaCitasAdapter.Listener {

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();

    String titulo;
    String nombreTit = "Titulo";
    InfoUSer responseLogIn = new InfoUSer();
    Calendar calendar;
    int dia, mes, anio;
    CalendarView calendarView;
    private ListaCitasAdapter adapter;
    Geolocalizacion geolocalizacion = new Geolocalizacion();

    ListView listView;

    LinearLayout noContent;
    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Consultando agenda");

    RequestCitasEmpleado requestCitasEmpleado = new RequestCitasEmpleado();

    private FusedLocationProviderClient fusedLocationClient;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private Location currentLocation;
    private LocationCallback locationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bHeader = new Bundle();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_clientes);
        Bundle recepcion = getIntent().getExtras();
        calendar = Calendar.getInstance();


        calendarView = findViewById(R.id.calendarView);
        listView = findViewById(R.id.listView);
        noContent = findViewById(R.id.noContent);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH) + 1;
        anio = calendar.get(Calendar.YEAR);

        String diaf = return2digits(dia);
        String mesf = return2digits(mes);
        String aniof = return2digits(anio);
        requestCitasEmpleado.setData(new DataCitasAgente());

        if (recepcion != null) {
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");


        }
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);

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



        menuHeader.setArguments(bHeader);
        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        calendarView.setMaxDate(calendar.getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String mes = return2digits(month + 1);
                String dia = return2digits(dayOfMonth);
                requestCitasEmpleado.getData().setFecha(year + "-" + mes + "-" + dia);
                getCitasPendientes(requestCitasEmpleado);
            }
        });
        requestCitasEmpleado.setData(new DataCitasAgente());
        requestCitasEmpleado.getData().setAsesor(responseLogIn.username.toUpperCase());
        requestCitasEmpleado.getData().setFecha(aniof + "-" + mesf + "-" + diaf);
        getCitasPendientes(requestCitasEmpleado);
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
    private void getCitasPendientes(RequestCitasEmpleado body) {
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseAgenda> call = ApiAdapter.getApiService(responseLogIn.token).agendaPendientes(body);
        call.enqueue(new Callback<ResponseAgenda>() {
            @Override
            public void onResponse(Call<ResponseAgenda> call, Response<ResponseAgenda> response) {
                int code = response.code();
                final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                boolean status = response.isSuccessful();
                if (code == 200 && status) {
                    ResponseAgenda responseAgenda = response.body();
                    if (responseAgenda.response.codigo == 200) {
                        ArrayList<Agenda> citas = responseAgenda.data.agenda;
                        muestraCitasPendientes(citas);
                        dialogFragment.dismiss();
                        call.cancel();
                    } else {
                        new SweetAlertDialog(Agenda_clientes.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("ALERTA")
                                .setContentText(responseAgenda.response.codigo + " - " + responseAgenda.response.mensaje)
                                .show();
                        call.cancel();
                    }
                }

                else {
                    new SweetAlertDialog(Agenda_clientes.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(Agenda_clientes.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAgenda> call, Throwable t) {
                call.cancel();
                new SweetAlertDialog(Agenda_clientes.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("ERROR")
                        .setContentText(t.toString())
                        .show();
            }
        });
    }


    private void muestraCitasPendientes(ArrayList<Agenda> citas) {
        if (citas.size() > 0) {
            ArrayList<Long> idsCitas = new ArrayList<>();
            for (Agenda cita : citas) {
                idsCitas.add(cita.idAgenda);
            }
            adapter = new ListaCitasAdapter(this, R.layout.custom_layout_agenda, citas);
            listView.setAdapter(adapter);


            noContent.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

        } else {
            noContent.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }


    private void confirmaAsistencia(RequestRegistraAsistencia req, String operacion) {
        DialogFragment dialog = LoaderTransparent.loaderTransparent(  operacion);
        dialog.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseAgenda> call = ApiAdapter.getApiService(responseLogIn.token).confirmaAsistencia(req);
        call.enqueue(new Callback<ResponseAgenda>() {
            @Override
            public void onResponse(Call<ResponseAgenda> call, Response<ResponseAgenda> response) {
                int code = response.code();
                boolean status = response.isSuccessful();
                if (code == 200 && status) {
                    ResponseAgenda info = response.body();
                    if (info.response.codigo == 200) {
                        dialog.dismiss();
                        getCitasPendientes(requestCitasEmpleado);


                    } else {
                        new SweetAlertDialog(Agenda_clientes.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Alerta")
                                .setContentText(info.response.codigo + " - " + info.response.mensaje)
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAgenda> call, Throwable t) {
                dialog.dismiss();
                new SweetAlertDialog(Agenda_clientes.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText(t.toString())
                        .show();
            }
        });

    }

    private String return2digits(int numero) {
        String finalText = "";
        int largo = String.valueOf(numero).length();
        if (largo == 1) {
            finalText = "0" + numero;
        } else {
            finalText = String.valueOf(numero);
        }

        return finalText;
    }

    @Override
    public void generaRegistro(long id, String tipoMovimiento) {
        RequestRegistraAsistencia require = new RequestRegistraAsistencia();
        require.setData(new DataRegistroAsistencia());
        require.getData().setLatitud(geolocalizacion.getLatitude());
        require.getData().setLongitud(geolocalizacion.getLongitud());
        require.getData().setIdAgenda(id);
        require.getData().setEstatus(tipoMovimiento);
        String titulo = "";
        String oper = "";

        if (tipoMovimiento.equals("1")) {
            titulo = "¿Desea registrar su asistencia?";
            oper = "Generando registro";
        } else {
            titulo = "¿Desea posponer la cita?";
            oper = "Posponiendo cita";
        }

        SweetAlertDialog alert = new SweetAlertDialog(Agenda_clientes.this, SweetAlertDialog.WARNING_TYPE);
        String finalOper = oper;
        alert.setTitleText("Cuidado")
                .setContentText(titulo)
                .setConfirmText("Continuar")
                .setCancelText("Cancelar")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        confirmaAsistencia(require, finalOper);

                    }
                })
                .show();


    }
}