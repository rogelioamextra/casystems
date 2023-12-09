package com.amextra.AltaEdicionCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.Direccion;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Colonia;
import com.amextra.io.Response.ColoniasLista;
import com.amextra.io.Response.Estado;
import com.amextra.io.Response.EstadoID;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.ID;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Municipio;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponseTiemposActualesEmpleo;
import com.amextra.io.Response.ResponsecodigoPostal;
import com.amextra.io.Response.ResponsecoloniaPorMunicipio;
import com.amextra.io.Response.Responseestados;
import com.amextra.io.Response.ResponsemunicipiosPorEstado;
import com.amextra.io.Response.TieposActuale;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosClienteLab extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {

    Button siguiente;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String REQ_ALTA_CLI = "reqAltaCliente",idDescEstado;
    String titulo;
    boolean esAlta;
    AutoCompleteTextView spinnTxtEstado, spinTxtMunicipio, spinnTxtTiempoEmpleo, spinnTxtColonia;
    TextInputEditText txtCalle, txtNumExt, txtNumInt, ingresoMnsual, editCP;
    long idDescColonia, idDescMunicipio,  idDescCiudad, idDesTiempoAc, idTiempoEmpleo;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    InfoUSer responseLogIn = new InfoUSer();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    Bundle mBundle = new Bundle();
    Bundle bHeader = new Bundle();
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    TextInputLayout layOutCodigoPostal, layOutEstado,
            layOutMunicipio, layOutColonia, layOutCalle, layoutNoExt,
            layOutIngresos, layOutTiempoEmpleo;
    boolean existeInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente_lab);
        setAdjustScreen();

        siguiente = findViewById(R.id.siguiente);
        spinnTxtEstado = findViewById(R.id.spinnTxtEstado);
        spinTxtMunicipio = findViewById(R.id.spinTxtMunicipio);
        spinnTxtColonia = findViewById(R.id.spinnTxtColonia);
        spinnTxtTiempoEmpleo = findViewById(R.id.spinnTxtTiempoEmpleo);

        editCP = findViewById(R.id.editCP);
        txtCalle = findViewById(R.id.txtCalle);
        txtNumExt = findViewById(R.id.txtNumExt);
        txtNumInt = findViewById(R.id.txtNumInt);
        ingresoMnsual = findViewById(R.id.ingresoMnsual);


        layOutCodigoPostal = findViewById(R.id.layOutCodigoPostal);
        layOutEstado = findViewById(R.id.layOutEstado);
        layOutMunicipio = findViewById(R.id.layOutMunicipio);
        layOutColonia = findViewById(R.id.layOutColonia);
        layOutCalle = findViewById(R.id.layOutCalle);
        layoutNoExt = findViewById(R.id.layoutNoExt);
        layOutIngresos = findViewById(R.id.layOutIngresos);
        layOutTiempoEmpleo = findViewById(R.id.layOutTiempoEmpleo);


        Bundle recepcion = getIntent().getExtras();

        if (recepcion != null) {
            esAlta = recepcion.getBoolean(nombreStatus);
            titulo = (recepcion.getString(nombreTit));
            geolocalizacion = (Geolocalizacion) recepcion.getSerializable("geo");
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);

            if(requestInsertClient.data != null){ 
                if(requestInsertClient.data.datosLaborales != null && requestInsertClient.data.datosLaborales.direccion!=null){ 
                    mapDataClient(requestInsertClient.data);
                    existeInfo = true;
                }
            }
        }

        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 6);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        mBundle.putSerializable("infoLogIn",responseLogIn);


        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn",responseLogIn);
        bHeader.putSerializable("geo", geolocalizacion);

        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);

        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();


        registroReferencias();
        getListaTiempoEmpleo();
        validaCP();


    }

    private void mapDataClient(DataReqCliente data) {
        txtNumExt.setText(data.datosLaborales.direccion.numeroExterior);
        txtNumInt.setText(data.datosLaborales.direccion.numeroInterior);
        ingresoMnsual.setText(String.valueOf(data.datosLaborales.ingresoMensual));
        editCP.setText(data.datosLaborales.direccion.cp);
        txtCalle.setText(data.datosLaborales.direccion.calle);
        consultaCP(data.datosLaborales.direccion.cp);

    }

    private void validaCP() {
        editCP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cp = s.toString();
                if (cp.length() == 5) {
                    consultaCP(cp);
                    layOutCodigoPostal.setErrorEnabled(false);

                } else {
                    cleanCp();
                    layOutCodigoPostal.setError("Codigo Postal requerido");

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void cleanCp(){
        spinnTxtColonia.setText("");
        spinTxtMunicipio.setText("");
        spinnTxtEstado.setText("");
    }
    private void getListaTiempoEmpleo() {
        Call<ResponseTiemposActualesEmpleo> call = ApiAdapter.getApiService(responseLogIn.token).tiemposActualesEmpleo();
        call.enqueue(new Callback<ResponseTiemposActualesEmpleo>() {
            @Override
            public void onResponse(Call<ResponseTiemposActualesEmpleo> call, Response<ResponseTiemposActualesEmpleo> response) {
                int code = response.code();
                ResponseTiemposActualesEmpleo info = response.body();
                List<String> listaTiempos = new ArrayList<>();
                List<String> listaIdsTiempos = new ArrayList<>();
                if (code == 200) {
                    TieposActuale[] tiempos = info.data.tieposActuales;
                    for (TieposActuale tiempo : tiempos) {
                        listaTiempos.add(tiempo.nombre.toUpperCase());
                        listaIdsTiempos.add(String.valueOf(tiempo.idTiempoEmpleoActual));
                    }
                    if(existeInfo){
                        Long idTiempo = Long.parseLong(requestInsertClient.data.datosLaborales.tiempoEmpleoActualId);
                        for (TieposActuale tiempo : tiempos) {
                            if(idTiempo == tiempo.idTiempoEmpleoActual){
                                spinnTxtTiempoEmpleo.setText(tiempo.nombre.toUpperCase());
                                idTiempoEmpleo = idTiempo;
                                break;
                            }
                        }
                    }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, listaTiempos);
                    spinnTxtTiempoEmpleo.setAdapter(spinnerArrayAdapter);
                    spinnTxtTiempoEmpleo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idTiempoEmpleo = Long.parseLong(listaIdsTiempos.get(position));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseTiemposActualesEmpleo> call, Throwable t) {

            }
        });
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
                if (response.isSuccessful() && code == 200 &&
                        responsecodigoPostal.response.codigo == 200) {

                    ColoniasLista[] coloniasListas = responsecodigoPostal.data.infoCodigoPostal.coloniasLista;
                    EstadoID estado = responsecodigoPostal.data.infoCodigoPostal.estadoId;
                    ID municipio = responsecodigoPostal.data.infoCodigoPostal.municipioId;


                    ArrayList<String> listColonias = new ArrayList<>();
                    ArrayList<String> listIdsColonias = new ArrayList<>();
                    ArrayList<String> listIdsEstados = new ArrayList<>();
                    ArrayList<String> listEstados = new ArrayList<>();
                    ArrayList<String> listMunicipios = new ArrayList<>();
                    ArrayList<String> listIdsMunicipios = new ArrayList<>();

                    listEstados.add(estado.nombre.toUpperCase());
                    listIdsEstados.add(String.valueOf(estado.codigoEstado));


                    listMunicipios.add(municipio.nombre.toUpperCase());
                    listIdsMunicipios.add(String.valueOf(municipio.idMunicipio));
                    idDescCiudad = municipio.idMunicipio;


                    for (ColoniasLista colonia : coloniasListas) {
                        listColonias.add(colonia.nombre.toUpperCase());
                        listIdsColonias.add(String.valueOf(colonia.idColonia));
                    }

                    if(existeInfo){
                        Long idColonia = requestInsertClient.data.datosLaborales.direccion.coloniaId;
                        for (ColoniasLista colonia : coloniasListas) {
                            if(idColonia == colonia.idColonia){
                                spinnTxtColonia.setText(colonia.nombre.toUpperCase());
                                idDescColonia = idColonia;
                                break;
                            }
                        }
                    }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, listColonias);
                    spinnTxtColonia.setAdapter(spinnerArrayAdapter);
                    spinnTxtColonia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idDescColonia = Long.parseLong(listIdsColonias.get(position));
                        }
                    });
                    ArrayAdapter<String> spAdaptMunici = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, listMunicipios);
                    spinTxtMunicipio.setAdapter(spAdaptMunici);
                    spinTxtMunicipio.setText(listMunicipios.get(0));
                    idDescMunicipio = Long.parseLong(listIdsMunicipios.get(0));
                    spinTxtMunicipio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idDescMunicipio = Long.parseLong(listIdsMunicipios.get(position));
                        }
                    });

                    ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, listEstados);
                    spinnTxtEstado.setAdapter(spAdaptEstado);
                    spinnTxtEstado.setText(listEstados.get(0));
                    idDescEstado = listIdsEstados.get(0);
                    spinnTxtEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idDescEstado = listIdsEstados.get(position);
                        }
                    });
                    dialogFragment.dismiss();
                } else {
                    dialogFragment.dismiss();
                    Toast.makeText(DatosClienteLab.this, "Error al consultar el codigo postal: " + responsecodigoPostal.response.codigo + " - " + responsecodigoPostal.response.mensaje, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponsecodigoPostal> call, Throwable t) {
                consultaEstados();
                dialogFragment.dismiss();
            }
        });
    }


    private void recopilaInfo() {

        String numInt = "0";
        String numExt = "0";
        String ingreso = "0";
        if (!((txtNumInt.getText().toString())).equals("")) {
            numInt = txtNumInt.getText().toString();
        }
        if (!((txtNumExt.getText().toString())).equals("")) {
            numExt = txtNumExt.getText().toString();
        }
        if (!((ingresoMnsual.getText().toString())).equals("")) {
            ingreso = ingresoMnsual.getText().toString();
        }

        DataReqCliente dataReqCliente = requestInsertClient.getData();
        if(!existeInfo){
            dataReqCliente.getDatosLaborales().setDireccion(new Direccion());
            dataReqCliente.getDatosLaborales().getDireccion().setColoniaID(idDescColonia);
            dataReqCliente.getDatosLaborales().getDireccion().setID(0);
            dataReqCliente.getDatosLaborales().getDireccion().setTiempoResidencia(idDesTiempoAc);
            dataReqCliente.getDatosLaborales().setTiempoEmpleoActualId(String.valueOf(idTiempoEmpleo));
            dataReqCliente.getDatosLaborales().getDireccion().setMunicipioID(idDescMunicipio);
            dataReqCliente.getDatosLaborales().getDireccion().setEstadoID(idDescEstado);
            dataReqCliente.getDatosLaborales().getDireccion().setCiudadID(idDescCiudad);
            dataReqCliente.getDatosLaborales().getDireccion().setCp(editCP.getText().toString());
            dataReqCliente.getDatosLaborales().getDireccion().setNumeroExterior(numExt);
            dataReqCliente.getDatosLaborales().getDireccion().setNumeroInterior(numInt);
            dataReqCliente.getDatosLaborales().getDireccion().setCalle(txtCalle.getText().toString());
            dataReqCliente.getDatosLaborales().setIngresoMensual(ingreso);
            dataReqCliente.getDatosLaborales().getDireccion().setGeolocalizacionLongitud("");
            dataReqCliente.getDatosLaborales().getDireccion().setGeolocalizacionLatitud("");
            dataReqCliente.getDatosLaborales().getDireccion().setTiempoResidencia(1);
            dataReqCliente.getDatosLaborales().getDireccion().setTipoViviendaId(1);
            dataReqCliente.getDatosLaborales().getDireccion().setBanderaCambioImagen(false);
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);
            requestInsertClient.setData(dataReqCliente);
        }else{

            dataReqCliente.getDatosLaborales().getDireccion().setColoniaID(idDescColonia);
            dataReqCliente.getDatosLaborales().getDireccion().setTiempoResidencia(idDesTiempoAc);
            dataReqCliente.getDatosLaborales().setTiempoEmpleoActualId(String.valueOf(idTiempoEmpleo));
            dataReqCliente.getDatosLaborales().getDireccion().setMunicipioID(idDescMunicipio);
            dataReqCliente.getDatosLaborales().getDireccion().setEstadoID(idDescEstado);
            dataReqCliente.getDatosLaborales().getDireccion().setCiudadID(idDescCiudad);
            dataReqCliente.getDatosLaborales().getDireccion().setCp(editCP.getText().toString());
            dataReqCliente.getDatosLaborales().getDireccion().setNumeroExterior(numExt);
            dataReqCliente.getDatosLaborales().getDireccion().setNumeroInterior(numInt);
            dataReqCliente.getDatosLaborales().getDireccion().setCalle(txtCalle.getText().toString());
            dataReqCliente.getDatosLaborales().setIngresoMensual(ingreso);
            dataReqCliente.getDatosLaborales().getDireccion().setGeolocalizacionLongitud("");
            dataReqCliente.getDatosLaborales().getDireccion().setGeolocalizacionLatitud("");
            dataReqCliente.getDatosLaborales().getDireccion().setTiempoResidencia(1);
            dataReqCliente.getDatosLaborales().getDireccion().setTipoViviendaId(1);
            dataReqCliente.setAsesorId(responseLogIn.usuarioId);
            dataReqCliente.getDatosLaborales().getDireccion().setBanderaCambioImagen(false);
        }


    }

    private void registroReferencias() {
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sender = new Bundle();
                Intent consultaInfDireccion = new Intent(DatosClienteLab.this, ReferenciasAlta.class);
                if (validaCampos()) {
                    recopilaInfo();

                    sender.putSerializable("reqAltaCliente", requestInsertClient);
                    sender.putString(nombreTit, titulo);
                    sender.putBoolean(nombreStatus, esAlta);
                    sender.putSerializable("infoLogIn",responseLogIn);

                    consultaInfDireccion.putExtras(sender);
                    consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    startActivity(consultaInfDireccion);
                }
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
                            nombrEstados.add(estado.nombre);
                            idsEstados.add(String.valueOf(estado.codigoEstado));
                            codigos.add(String.valueOf(estado.idEstado));
                        }

                        ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, nombrEstados);
                        spinnTxtEstado.setAdapter(spAdaptEstado);
                        spinnTxtEstado.setText(nombrEstados.get(0));
                        idDescEstado = idsEstados.get(0);
                        consultaMunicipiosEstado(codigos.get(0));
                        spinnTxtEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescEstado = idsEstados.get(position);
                                consultaMunicipiosEstado(idsEstados.get(position));
                            }
                        });
                    } else {

                    }
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
                            nombresMunicipios.add(municipio.nombre);
                            idsMunicipios.add(String.valueOf(municipio.idMunicipio));
                        }
                        ArrayAdapter<String> spAdaptMunici = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, nombresMunicipios);
                        spinTxtMunicipio.setAdapter(spAdaptMunici);
                        spinTxtMunicipio.setText(nombresMunicipios.get(0));
                        idDescMunicipio = Long.parseLong(idsMunicipios.get(0));
                        consultaColoniaMunicipio(idsMunicipios.get(0));
                        spinTxtMunicipio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescMunicipio = Long.parseLong(idsMunicipios.get(position));
                                consultaColoniaMunicipio(idsMunicipios.get(position));
                            }
                        });

                    } else {

                    }
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
                            nombreColinias.add(colonia.nombre);
                            idsColinias.add(String.valueOf(colonia.idColonia));
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosClienteLab.this, android.R.layout.simple_spinner_dropdown_item, nombreColinias);
                        spinnTxtColonia.setAdapter(spinnerArrayAdapter);
                        spinnTxtColonia.setText(nombreColinias.get(0));
                        idDescColonia = Long.parseLong(idsColinias.get(0));
                        spinnTxtColonia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescColonia = Long.parseLong(idsColinias.get(position));
                            }
                        });

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsecoloniaPorMunicipio> call, Throwable t) {

            }
        });
    }


    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    private boolean validaCampos() {




        boolean status = true;
        String exte = txtNumExt.getText().toString();
        String monto = ingresoMnsual.getText().toString();
        String cp = editCP.getText().toString();
        String calle = txtCalle.getText().toString();
        String municipio = spinTxtMunicipio.getText().toString();
        String colonia = spinnTxtColonia.getText().toString();
        String estado = spinnTxtEstado.getText().toString();
        String tiempo = spinnTxtTiempoEmpleo.getText().toString();

        if (exte.equals("")) {
            status = false;
            layoutNoExt.setError("Numero exterior requerido");
            txtNumExt.setSelected(true);
        } else {
            layoutNoExt.setErrorEnabled(false);
        }
        if (monto.equals("")) {
            status = false;
            layOutIngresos.setError("Monto requerido");
            ingresoMnsual.setSelected(true);
        } else {
            layOutIngresos.setErrorEnabled(false);
        }
        if (cp.length() < 5) {
            status = false;
            layOutCodigoPostal.setError("Codigo postal requerido");
            editCP.setSelected(true);

        } else {
            layOutCodigoPostal.setErrorEnabled(false);
        }
        if (calle.equals("")) {
            status = false;
            layOutCalle.setError("Calle requerida");
            txtCalle.setSelected(true);

        } else {
            layOutCalle.setErrorEnabled(false);
        }

        if (municipio.equals("")) {
            status = false;
            layOutMunicipio.setError("Municipio requerido");
            spinTxtMunicipio.setSelected(true);

        } else {
            layOutMunicipio.setErrorEnabled(false);
        }
        if (colonia.equals("")) {
            status = false;
            layOutColonia.setError("Colonia requerida");
            spinnTxtColonia.setSelected(true);

        } else {
            layOutColonia.setErrorEnabled(false);
        }

        if (estado.equals("")) {
            status = false;
            layOutEstado.setError("Estado requerida");
            spinnTxtEstado.setSelected(true);

        } else {
            layOutEstado.setErrorEnabled(false);
        }
        if (tiempo.equals("")) {
            status = false;
            layOutTiempoEmpleo.setError("Antiguedad requerida");
            spinnTxtTiempoEmpleo.setSelected(true);

        } else {
            layOutTiempoEmpleo.setErrorEnabled(false);
        }

        return status;
    }


    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if(validaCampos()){
            recopilaInfo();
        }
        mBundle.putSerializable("infoLogIn",responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);



    }
}

