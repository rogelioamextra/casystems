package com.amextra.AltaEdicionCliente;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.amextra.MainActivity;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.Direccion;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.Colonia;
import com.amextra.io.Response.ColoniasLista;
import com.amextra.io.Response.Estado;
import com.amextra.io.Response.EstadoID;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.ID;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Municipio;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseTiposResidencia;
import com.amextra.io.Response.ResponsecodigoPostal;
import com.amextra.io.Response.ResponsecoloniaPorMunicipio;
import com.amextra.io.Response.Responseestados;
import com.amextra.io.Response.ResponsemunicipiosPorEstado;
import com.amextra.io.Response.TiposResidencia;
import com.amextra.utils.ConverterReqClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class datos_personales_address extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Button siguiente;

    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    String REQ_ALTA_CLI = "reqAltaCliente";
    long idDescResidencia, idDescColonia, idDescMunicipio, idDescCiudad;
    String descResidencia, descColonia, idDescEstado;
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();

    Bundle mBundle = new Bundle();
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    TextInputEditText editCP, txtCalle, txtNumInt, txtNumExt;
    TextInputLayout layOutCodigoPostal, layOutEstado, layOutMunicipio, layOutColonia, layOutCalle,
            layoutNoExt, layOutNoInt, layOutTipoRes;

    AutoCompleteTextView spinListEstado, spintTxtMunicipio, spintxtTipoResidencia,
            spinTxtColonia;
    Bundle bHeader = new Bundle();
    InfoUSer responseLogIn = new InfoUSer();
    private Location currentLocation;

    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationClient;
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    String clienteInfo = "INFO_CLIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales_address);
        setAdjustScreen();
        cargaInicial();

        Bundle recepcion = getIntent().getExtras();


        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            esAlta = recepcion.getBoolean(nombreStatus);
            titulo = (recepcion.getString(nombreTit));
            if (esAlta) {
                requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);

                if (requestInsertClient.data != null) {
                    if (requestInsertClient.data.direccion != null) {
                        mapData(requestInsertClient);
                    }
                }
            }

            if (titulo.equals("Editar Datos del Cliente")) {
                if (getIntent().hasExtra(clienteInfo)) {
                    responseGetCliente = (ResponseGetCliente) recepcion.getSerializable(clienteInfo);
                    Cliente cliente = responseGetCliente.data.cliente;
                    ConverterReqClient converterReqClient = new ConverterReqClient();
                    requestInsertClient = converterReqClient.converter(cliente);
                    if (requestInsertClient.data.direccion != null) {
                        mapData(requestInsertClient);
                    }
                }
                if (getIntent().hasExtra(REQ_ALTA_CLI)) {
                    requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                    if (requestInsertClient.data.direccion != null) {
                        mapData(requestInsertClient);
                    }
                }
            }
        }

        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 3);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        mBundle.putSerializable("infoLogIn",responseLogIn);

        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        bHeader.putSerializable("geo", geolocalizacion);


        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);

        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();


        editCP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cp = s.toString();
                if (cp.length() < 5) {
                    cleanCp();
                    layOutCodigoPostal.setError("La longitud rquerida del codigo postal es de 5 caracteres");
                } else {
                    layOutCodigoPostal.setErrorEnabled(false);
                    consultaCP(cp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                    currentLocation = location;
                    Log.d("loc", "onLocationResult: " + currentLocation.getLatitude());

                } else {
                    buildLocationCallback();
                }
            }
        });


        terminaRegistroDireccion();
        getListaTipodeResidencias();

    }


    private void cleanCp(){
        spinListEstado.setText("");
        spintTxtMunicipio.setText("");
        spinTxtColonia.setText("");
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
                    currentLocation = location;
                    geolocalizacion.setLatitude(String.valueOf(currentLocation.getLatitude()));
                    geolocalizacion.setLongitud(String.valueOf(currentLocation.getLongitude()));
                    Log.d("loc", "onLocationResult: " + currentLocation.getLatitude());
                }
            }

            ;
        };
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {

                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Permisos no proporcionados, por favor proporcione los permisos solicitados para poder continuar")
                        .show();

            }

        }


    }


    private void mapData(RequestInsertClient requestInsertClient) {

        editCP.setText(requestInsertClient.data.direccion.cp);
        txtCalle.setText(requestInsertClient.data.direccion.calle);
        txtNumExt.setText(requestInsertClient.data.direccion.numeroExterior);
        if (!(requestInsertClient.data.direccion.numeroInterior.equals(""))) {
            txtNumInt.setText(requestInsertClient.data.direccion.numeroInterior);
        }
        requestInsertClient.getData().getDireccion().setGeolocalizacionLatitud(geolocalizacion.getLatitude());
        requestInsertClient.getData().getDireccion().setGeolocalizacionLongitud(geolocalizacion.getLongitud());

        consultaCP(editCP.getText().toString());
    }

    private void consultaCP(String cp) {

        String msg = "Obteniendo informacion...";
        DialogFragment dialogFragment = LoaderTransparent.loaderTransparent(msg);
        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponsecodigoPostal> call = ApiAdapter.getApiService(responseLogIn.token).codigoPostal(cp);
        call.enqueue(new Callback<ResponsecodigoPostal>() {
            @Override
            public void onResponse(Call<ResponsecodigoPostal> call, Response<ResponsecodigoPostal> response) {
                int code = response.code();
                ResponsecodigoPostal responsecodigoPostal = response.body();
                if (response.isSuccessful() && code == 200) {
                    if (responsecodigoPostal.response.codigo == 200) {
                        ColoniasLista[] coloniasListas = responsecodigoPostal.data.infoCodigoPostal.coloniasLista;
                        EstadoID estado = responsecodigoPostal.data.infoCodigoPostal.estadoId;
                        ID municipio = responsecodigoPostal.data.infoCodigoPostal.municipioId;
                        ID ciudad = responsecodigoPostal.data.infoCodigoPostal.ciudadId;

                        idDescCiudad = municipio.idMunicipio;
                        ArrayList<String> listColonias = new ArrayList<>();
                        ArrayList<String> listIdsColonias = new ArrayList<>();
                        ArrayList<String> listIdsEstados = new ArrayList<>();
                        ArrayList<String> listEstados = new ArrayList<>();
                        ArrayList<String> listMunicipios = new ArrayList<>();
                        ArrayList<String> listIdsMunicipios = new ArrayList<>();

                        listEstados.add((estado.nombre.toUpperCase()).toUpperCase());
                        listIdsEstados.add(estado.codigoEstado);


                        listMunicipios.add((municipio.nombre.toUpperCase()).toUpperCase());
                        listIdsMunicipios.add(String.valueOf(municipio.idMunicipio));


                        for (ColoniasLista colonia : coloniasListas) {
                            listColonias.add((colonia.nombre.toUpperCase()).toUpperCase());
                            listIdsColonias.add(String.valueOf(colonia.idColonia));
                        }


                        ArrayAdapter<String> spAdaptMunici = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, listMunicipios);
                        spintTxtMunicipio.setAdapter(spAdaptMunici);
                        spintTxtMunicipio.setText(listMunicipios.get(0));
                        idDescMunicipio = Long.parseLong(listIdsMunicipios.get(0));


                        ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, listEstados);
                        spinListEstado.setAdapter(spAdaptEstado);
                        spinListEstado.setText(listEstados.get(0));
                        idDescEstado = listIdsEstados.get(0);


                        if (requestInsertClient.data.direccion != null) {
                            Long idColo = requestInsertClient.data.direccion.coloniaId;
                            for (ColoniasLista colonia : coloniasListas) {
                                if (idColo == colonia.idColonia) {
                                    spinTxtColonia.setText(colonia.nombre.toUpperCase());
                                    idDescColonia = idColo;
                                    break;
                                }
                            }
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, listColonias);
                        spinTxtColonia.setAdapter(spinnerArrayAdapter);
                        spinTxtColonia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescColonia = Long.parseLong(listIdsColonias.get(position));
                                descColonia = listColonias.get(position);
                            }
                        });

                        spintTxtMunicipio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescMunicipio = Long.parseLong(listIdsMunicipios.get(position));
                            }
                        });
                        spinListEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescEstado = listIdsEstados.get(position);
                            }
                        });
                        dialogFragment.dismiss();
                    } else {

                        dialogFragment.dismiss();
                        Toast.makeText(datos_personales_address.this, "Error al consultar el codigo postal: " + responsecodigoPostal.response.codigo + " - " + responsecodigoPostal.response.mensaje, Toast.LENGTH_SHORT).show();
                    }

                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;

                    new SweetAlertDialog(datos_personales_address.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(datos_personales_address.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponsecodigoPostal> call, Throwable t) {
                consultaEstados();
                dialogFragment.dismiss();
            }
        });
    }


    private void consultaEstados() {
        Call<Responseestados> call = ApiAdapter.getApiService(responseLogIn.token).estados();
        call.enqueue(new Callback<Responseestados>() {
            @Override
            public void onResponse(Call<Responseestados> call, Response<Responseestados> response) {
                int code = response.code();
                boolean status = response.isSuccessful();
                if (status && code == 200) {
                    Responseestados data = response.body();
                    if (data.response.codigo == 200) {
                        Estado[] listaEstados = data.data.estados;
                        ArrayList<String> nombrEstados = new ArrayList<>();
                        ArrayList<String> idsEstados = new ArrayList<>();
                        ArrayList<String> codigos = new ArrayList<>();
                        for (Estado estado : listaEstados) {
                            nombrEstados.add(estado.nombre.toUpperCase());
                            idsEstados.add(String.valueOf(estado.codigoEstado));
                            codigos.add(String.valueOf(estado.idEstado));
                        }

                        ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, nombrEstados);
                        spinListEstado.setAdapter(spAdaptEstado);
                        spinListEstado.setText(nombrEstados.get(0));
                        idDescEstado = idsEstados.get(0);
                        consultaMunicipiosEstado(codigos.get(0));
                        spinListEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescEstado = idsEstados.get(position);
                                consultaMunicipiosEstado(idsEstados.get(position));
                            }
                        });

                    }
                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;

                    new SweetAlertDialog(datos_personales_address.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(datos_personales_address.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Responseestados> call, Throwable t) {

            }
        });
    }

    private void consultaMunicipiosEstado(String idEstado) {
        Call<ResponsemunicipiosPorEstado> call = ApiAdapter.getApiService(responseLogIn.token).municipiosPorEstado(idEstado);
        call.enqueue(new Callback<ResponsemunicipiosPorEstado>() {
            @Override
            public void onResponse(Call<ResponsemunicipiosPorEstado> call, Response<ResponsemunicipiosPorEstado> response) {
                int code = response.code();
                boolean stat = response.isSuccessful();
                if (stat && code == 200) {
                    ResponsemunicipiosPorEstado data = response.body();
                    if (data.response.codigo == 200) {
                        ArrayList<String> nombresMunicipios = new ArrayList<>();
                        ArrayList<String> idsMunicipios = new ArrayList<>();
                        Municipio[] municipios = data.data.municipios;
                        for (Municipio municipio : municipios) {
                            nombresMunicipios.add(municipio.nombre.toUpperCase());
                            idsMunicipios.add(String.valueOf(municipio.idMunicipio));
                        }
                        ArrayAdapter<String> spAdaptMunici = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, nombresMunicipios);
                        spintTxtMunicipio.setAdapter(spAdaptMunici);
                        spintTxtMunicipio.setText(nombresMunicipios.get(0));
                        idDescMunicipio = Long.parseLong(idsMunicipios.get(0));
                        consultaColoniaMunicipio(idsMunicipios.get(0));
                        spintTxtMunicipio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescMunicipio = Long.parseLong(idsMunicipios.get(position));
                                consultaColoniaMunicipio(idsMunicipios.get(position));
                            }
                        });

                    }
                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;

                    new SweetAlertDialog(datos_personales_address.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(datos_personales_address.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponsemunicipiosPorEstado> call, Throwable t) {

            }
        });
    }

    private void consultaColoniaMunicipio(String idMunicipio) {
        Call<ResponsecoloniaPorMunicipio> call = ApiAdapter.getApiService(responseLogIn.token).coloniaPorMunicipio(idMunicipio);
        call.enqueue(new Callback<ResponsecoloniaPorMunicipio>() {
            @Override
            public void onResponse(Call<ResponsecoloniaPorMunicipio> call, Response<ResponsecoloniaPorMunicipio> response) {
                int code = response.code();
                boolean stat = response.isSuccessful();
                if (stat && code == 200) {
                    ResponsecoloniaPorMunicipio datosr = response.body();
                    if (datosr.response.codigo == 200) {
                        Colonia[] listaColonias = datosr.data.colonias;
                        ArrayList<String> nombreColinias = new ArrayList<>();
                        ArrayList<String> idsColinias = new ArrayList<>();
                        for (Colonia colonia : listaColonias) {
                            nombreColinias.add(colonia.nombre.toUpperCase());
                            idsColinias.add(String.valueOf(colonia.idColonia));
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, nombreColinias);
                        spinTxtColonia.setAdapter(spinnerArrayAdapter);
                        spinTxtColonia.setText(nombreColinias.get(0));
                        idDescColonia = Long.parseLong(idsColinias.get(0));

                        spinTxtColonia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescColonia = Long.parseLong(idsColinias.get(position));
                                descColonia = idsColinias.get(position);
                            }
                        });

                    }
                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;

                    new SweetAlertDialog(datos_personales_address.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(datos_personales_address.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponsecoloniaPorMunicipio> call, Throwable t) {

            }
        });
    }


    private void getListaTipodeResidencias() {
        Call<ResponseTiposResidencia> call = ApiAdapter.getApiService(responseLogIn.token).tipoResidencia();
        call.enqueue(new Callback<ResponseTiposResidencia>() {
            @Override
            public void onResponse(Call<ResponseTiposResidencia> call, Response<ResponseTiposResidencia> response) {
                int code = response.code();
                ResponseTiposResidencia info = response.body();
                List<String> listaResidencias = new ArrayList<String>();
                List<String> listaIdResidencias = new ArrayList<>();
                if (code == 200) {
                    TiposResidencia[] residencias = info.data.tiposResidencias;
                    for (TiposResidencia residencia : residencias) {
                        listaResidencias.add(residencia.nombre.toUpperCase());
                        listaIdResidencias.add(String.valueOf(residencia.idTipoResidencia));
                    }

                    if(requestInsertClient.data!= null){
                        if (requestInsertClient.data.direccion != null) {
                            Long idRec = requestInsertClient.data.direccion.tipoResidenciaId;
                            for (TiposResidencia residencia : residencias) {
                                if (idRec == residencia.idTipoResidencia) {
                                    spintxtTipoResidencia.setText(residencia.nombre.toUpperCase());
                                    idDescResidencia = idRec;
                                    break;
                                }
                            }
                        }
                    }


                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(datos_personales_address.this, android.R.layout.simple_spinner_dropdown_item, listaResidencias);
                    spintxtTipoResidencia.setAdapter(spinnerArrayAdapter);
                    spintxtTipoResidencia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idDescResidencia = Long.parseLong(listaIdResidencias.get(position));
                            descResidencia = listaIdResidencias.get(position);
                        }
                    });
                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;

                    new SweetAlertDialog(datos_personales_address.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(datos_personales_address.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponseTiposResidencia> call, Throwable t) {

            }
        });
    }


    private void terminaRegistroDireccion() {
        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent screenRegFinDireccion = new Intent(datos_personales_address.this, DatosPersonalesAddresB.class);
            boolean status = (receptor.getBoolean(nombreStatus));
            if (validaDatos()) {

                recopilaDatos();
                sender.putString(nombreTit, titulo);
                sender.putBoolean(nombreStatus, status);
                sender.putSerializable("reqAltaCliente", requestInsertClient);
                sender.putSerializable("infoLogIn", responseLogIn);
                screenRegFinDireccion.putExtras(sender);
                screenRegFinDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(screenRegFinDireccion);
            }
        });
    }

    private void recopilaDatos() {
        DataReqCliente dataReqCliente = requestInsertClient.getData();

        String numInt = "0";
        String numExt = "0";
        if (!(txtNumInt.getText().toString().equals(""))) {
            numInt = txtNumInt.getText().toString();
        }
        if (!(txtNumExt.getText().toString().equals(""))) {
            numExt = txtNumExt.getText().toString();
        }

        if (requestInsertClient.data.direccion == null) {
            dataReqCliente.setDireccion(new Direccion());
            dataReqCliente.getDireccion().setID(0);
            dataReqCliente.getDireccion().setCp(editCP.getText().toString());
            dataReqCliente.getDireccion().setMunicipioID(idDescMunicipio);
            dataReqCliente.getDireccion().setEstadoID(idDescEstado);
            dataReqCliente.getDireccion().setColoniaID(idDescColonia);
            dataReqCliente.getDireccion().setCiudadID(idDescCiudad);
            dataReqCliente.getDireccion().setTipoResidenciaID(idDescResidencia);
            dataReqCliente.getDireccion().setNumeroInterior(numInt);
            dataReqCliente.getDireccion().setNumeroExterior(numExt);
            dataReqCliente.getDireccion().setGeolocalizacionLongitud(geolocalizacion.getLongitud());
            dataReqCliente.getDireccion().setGeolocalizacionLatitud(geolocalizacion.getLatitude());
            dataReqCliente.getDireccion().setCalle(txtCalle.getText().toString());
            dataReqCliente.getDireccion().setBanderaCambioImagen(true);
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);

            requestInsertClient.setData(dataReqCliente);
        } else {
            dataReqCliente.getDireccion().setCp(editCP.getText().toString());
            dataReqCliente.getDireccion().setMunicipioID(idDescMunicipio);
            dataReqCliente.getDireccion().setEstadoID(idDescEstado);
            dataReqCliente.getDireccion().setColoniaID(idDescColonia);
            dataReqCliente.getDireccion().setCiudadID(idDescCiudad);
            dataReqCliente.getDireccion().setTipoResidenciaID(idDescResidencia);
            dataReqCliente.getDireccion().setNumeroInterior(numInt);
            dataReqCliente.getDireccion().setNumeroExterior(numExt);
            dataReqCliente.getDireccion().setGeolocalizacionLongitud(geolocalizacion.getLongitud());
            dataReqCliente.getDireccion().setGeolocalizacionLatitud(geolocalizacion.getLatitude());
            dataReqCliente.getDireccion().setCalle(txtCalle.getText().toString());
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);

            dataReqCliente.getDireccion().setBanderaCambioImagen(true);
        }


    }


    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    private boolean validaDatos() {
        boolean status = true;
        String numExt = txtNumExt.getText().toString();
        String cp = editCP.getText().toString();
        String calle = txtCalle.getText().toString();
        String residencia = spintxtTipoResidencia.getText().toString();
        String municipio = spintTxtMunicipio.getText().toString();
        String colonia = spinTxtColonia.getText().toString();
        String estado = spinListEstado.getText().toString();


        if (calle.equals("")) {
            status = false;
            layOutCalle.setError("Calle requerida");

        } else {
            layOutCalle.setErrorEnabled(false);
        }
        if (cp.equals("")) {
            status = false;
            layOutCodigoPostal.setError("Codigo postal requerido");
        } else {
            layOutCodigoPostal.setErrorEnabled(false);
        }
        if (numExt.equals("")) {
            status = false;
            layoutNoExt.setError("Numero exterior requerido");

        } else {
            layoutNoExt.setErrorEnabled(false);
        }
        if (residencia.equals("")) {
            status = false;
            layOutTipoRes.setError("Tipo de residencia requerida");

        } else {
            layOutTipoRes.setErrorEnabled(false);
        }

        if (municipio.equals("")) {
            status = false;
            layOutMunicipio.setError("Municipio requerido");

        } else {
            layOutMunicipio.setErrorEnabled(false);
        }
        if (estado.equals("")) {
            status = false;
            layOutEstado.setError("Estado requerido");

        } else {
            layOutEstado.setErrorEnabled(false);
        }


        if (colonia.equals("")) {
            status = false;
            layOutColonia.setError("Colonia requerida");

        } else {
            layOutColonia.setErrorEnabled(false);
        }

        return status;
    }

    private void cargaInicial() {
        siguiente = findViewById(R.id.siguiente);
        spintTxtMunicipio = findViewById(R.id.spintTxtMunicipio);
        spinListEstado = findViewById(R.id.spinListEstado);
        spinTxtColonia = findViewById(R.id.spinTxtColonia);
        editCP = findViewById(R.id.editCP);
        spintxtTipoResidencia = findViewById(R.id.spintxtTipoResidencia);
        txtCalle = findViewById(R.id.txtCalle);
        txtNumInt = findViewById(R.id.txtNumInt);
        txtNumExt = findViewById(R.id.txtNumExt);

        layOutCodigoPostal = findViewById(R.id.layOutCodigoPostal);
        layOutEstado = findViewById(R.id.layOutEstado);
        layOutMunicipio = findViewById(R.id.layOutMunicipio);
        layOutColonia = findViewById(R.id.layOutColonia);
        layOutCalle = findViewById(R.id.layOutCalle);
        layoutNoExt = findViewById(R.id.layoutNoExt);
        layOutNoInt = findViewById(R.id.layOutNoInt);
        layOutTipoRes = findViewById(R.id.layOutTipoRes);


    }


    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if(validaDatos()){
            recopilaDatos();
        }

        mBundle.putSerializable("infoLogIn",responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);


    }
}