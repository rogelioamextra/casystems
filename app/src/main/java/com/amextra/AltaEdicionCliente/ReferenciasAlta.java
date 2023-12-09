package com.amextra.AltaEdicionCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.amextra.SMS.EnviaSMS;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.ClientDefine;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.Direccion;
import com.amextra.io.Request.Referencia;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Cliente;
import com.amextra.io.Response.ColoniasLista;
import com.amextra.io.Response.EstadoID;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.ID;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Parentesco;
import com.amextra.io.Response.ResponseGetCliente;
import com.amextra.io.Response.ResponseGetClientes;
import com.amextra.io.Response.ResponseLogin;
import com.amextra.io.Response.ResponseParentescos;
import com.amextra.io.Response.ResponsecodigoPostal;
import com.amextra.utils.ConverterReqClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferenciasAlta extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {
    Button siguiente;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    TextInputEditText txtNombre, txtCalle, txtNumExt,
            txtCelular, editCP, txtNumInt, txtApMat, txtApPat;
    long idDescColonia, idDescMunicipio,  idDescCiudad, idParentesco;
    String descColonia,idDescEstado;
    private AutoCompleteTextView spinTxtColonia, txtEstado, spintTxtMunicipio, txtParentesco;
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    Referencia[] referencias = new Referencia[2];
    Bundle mBundle = new Bundle();
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    TextInputLayout layOutNombre, layOutParentesco, layOutApellidoP, layOutApellidoM, layOutTelefono,
            layOutCodigoPostal, layOutEstado, layOutMunicipio, layOutColonia, layOutCalle, layoutNoExt;

    InfoUSer responseLogIn = new InfoUSer();
    Bundle bHeader = new Bundle();
    String REQ_ALTA_CLI = "reqAltaCliente";
    String INFOC = "i_c";

    boolean existeInfo = false;
    String clienteInfo = "INFO_CLIENT";

    ResponseGetCliente responseGetCliente = new ResponseGetCliente();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referencias_alta);
        setAdjustScreen();
        cargaComponentes();

        Bundle recepcion = getIntent().getExtras();
        if (recepcion != null) {
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            esAlta = recepcion.getBoolean(nombreStatus);
            titulo = (recepcion.getString(nombreTit));
            if (esAlta) {
                requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);

                if(requestInsertClient.data!= null){
                    if (requestInsertClient.data.referencias != null) {
                        referencias = requestInsertClient.data.referencias;
                        if (referencias[0] != null) {
                            mapDataClient(requestInsertClient.data);
                            existeInfo=true;
                        }
                    }
                }
            }

            if (titulo.equals("Editar Datos del Cliente")) {
                if (getIntent().hasExtra(clienteInfo)) {
                    responseGetCliente = (ResponseGetCliente) recepcion.getSerializable(clienteInfo);
                    Cliente cliente = responseGetCliente.data.cliente;
                    ConverterReqClient converterReqClient = new ConverterReqClient();
                    requestInsertClient = converterReqClient.converter(cliente);
                    if(requestInsertClient.data!= null){
                        if (requestInsertClient.data.referencias != null) {
                            referencias = requestInsertClient.data.referencias;
                            if (referencias[0] != null) {
                                mapDataClient(requestInsertClient.data);
                                existeInfo=true;
                            }
                        }
                    }
                }
                if (getIntent().hasExtra(REQ_ALTA_CLI)) {
                    requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                    if(requestInsertClient.data!= null){
                        if (requestInsertClient.data.referencias != null) {
                            referencias = requestInsertClient.data.referencias;
                            if (referencias[0] != null) {
                                mapDataClient(requestInsertClient.data);
                                existeInfo=true;
                            }
                        }
                    }
                }
            }
        }

        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 7);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        mBundle.putSerializable("infoLogIn", responseLogIn);

        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn", responseLogIn);
        bHeader.putSerializable("geo", geolocalizacion);

        menuInformacionCliente.setArguments(mBundle);
        menuHeader.setArguments(bHeader);

        mFragmentHeaderTransac.add(R.id.frameHeader, menuHeader).commit();
        mFragmentTransaction.add(R.id.frameLayout, menuInformacionCliente).commit();


        obtenListaParentesco();

        editCP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) {
                    consultaCP(s.toString());
                    layOutCodigoPostal.setErrorEnabled(false);
                } else {
                    cleanCp();
                    layOutCodigoPostal.setError("Longitud requerida : 5 caracteres");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aRegistroDireccion();
                //generaAltaCliente(requestInsertClient);

            }
        });


    }

    private void mapDataClient(DataReqCliente data) {

        Referencia cliente = data.referencias[0];
        txtNombre.setText(cliente.nombre);
        txtCelular.setText(cliente.telefono);
        txtCalle.setText(cliente.direccion.calle);
        editCP.setText(cliente.direccion.cp);
        txtNumExt.setText(cliente.direccion.numeroExterior);
        txtNumInt.setText(cliente.direccion.numeroInterior);
        txtApPat.setText(cliente.apellidoPaterno);
        txtApMat.setText(cliente.apellidoMaterno);
        consultaCP(cliente.direccion.cp);

    }


    private int defineValor(String s) {
        int value;
        if (s.equals("")) {
            value = 0;
        } else {
            value = Integer.parseInt(s);

        }
        return value;
    }

    private void capturaInfoRf1() {

        String name = txtNombre.getText().toString();
        String apPat = txtApPat.getText().toString();
        String apMat = txtApMat.getText().toString();
        String numInt = "0";
        String numExt = "0";
        if (!txtNumExt.getText().toString().equals("")) {
            numExt = txtNumExt.getText().toString();
        }

        if (!txtNumInt.getText().toString().equals("")) {
            numInt = txtNumInt.getText().toString();
        }

        if(!existeInfo){
            Direccion dir = new Direccion();
            Referencia referencia = new Referencia();
            referencia.setDireccion(dir);
            referencia.setID("0");
            referencia.setNombre(name);
            referencia.setApellidoPaterno(apPat);
            referencia.setApellidoMaterno(apMat);
            referencia.setParentescoID(idParentesco);
            referencia.getDireccion().setID(0);
            referencia.setTelefono(txtCelular.getText().toString());
            referencia.getDireccion().setBanderaCambioImagen(false);
            referencia.getDireccion().setGeolocalizacionLatitud("");
            referencia.getDireccion().setGeolocalizacionLongitud("");
            referencia.getDireccion().setTiempoResidencia(1);
            referencia.getDireccion().setTipoViviendaId(1);
            referencia.getDireccion().setTiempoResidencia(1);
            referencia.getDireccion().setCiudadID(idDescCiudad);
            referencia.getDireccion().setMunicipioID(idDescMunicipio);
            referencia.getDireccion().setEstadoID(idDescEstado);
            referencia.getDireccion().setColoniaID(idDescColonia);
            referencia.getDireccion().setComprobante("");
            referencia.getDireccion().setCp(editCP.getText().toString());
            referencia.getDireccion().setNumeroExterior(numExt);
            referencia.getDireccion().setNumeroInterior(numInt);
            referencia.getDireccion().setCalle(txtCalle.getText().toString());
            requestInsertClient.getData().setAsesorId(responseLogIn.usuarioId);

            referencias[0] = referencia;
        }else{
            Referencia referencia = requestInsertClient.data.referencias[0];
            referencia.setNombre(name);
            referencia.setApellidoPaterno(apPat);
            referencia.setApellidoMaterno(apMat);
            referencia.setParentescoID(idParentesco);
            referencia.setTelefono(txtCelular.getText().toString());
            referencia.getDireccion().setBanderaCambioImagen(false);
            referencia.getDireccion().setGeolocalizacionLatitud("");
            referencia.getDireccion().setGeolocalizacionLongitud("");
            referencia.getDireccion().setTiempoResidencia(1);
            referencia.getDireccion().setTipoViviendaId(1);
            referencia.getDireccion().setTiempoResidencia(1);
            referencia.getDireccion().setCiudadID(idDescCiudad);
            referencia.getDireccion().setMunicipioID(idDescMunicipio);
            referencia.getDireccion().setEstadoID(idDescEstado);
            referencia.getDireccion().setColoniaID(idDescColonia);
            referencia.getDireccion().setComprobante("");
            referencia.getDireccion().setCp(editCP.getText().toString());
            referencia.getDireccion().setNumeroExterior(numExt);
            referencia.getDireccion().setNumeroInterior(numInt);
            referencia.getDireccion().setCalle(txtCalle.getText().toString());
            requestInsertClient.getData().setAsesorId(responseLogIn.usuarioId);

        }




    }


    private boolean validaReferencia() {

        boolean status = true;
        String nombre = txtNombre.getText().toString();
        String apPat = txtApPat.getText().toString();
        String apMat = txtApMat.getText().toString();
        String calle = txtCalle.getText().toString();
        String numExt = txtNumExt.getText().toString();
        String movil = txtCelular.getText().toString();
        String cp = editCP.getText().toString();
        String estado = txtEstado.getText().toString();
        String municipio = spintTxtMunicipio.getText().toString();
        String colonia = spinTxtColonia.getText().toString();
        String parentesco = txtParentesco.getText().toString();

        if (nombre.equals("")) {
            status = false;
            layOutNombre.setError("Ingrese un nombre");
        } else {
            layOutNombre.setErrorEnabled(false);
        }

        if (apPat.equals("")) {
            status = false;
            layOutApellidoP.setError("Ingrese el apellido paterno");

        } else {
            layOutApellidoP.setErrorEnabled(false);
        }

        if (apMat.equals("")) {
            status = false;
            layOutApellidoM.setError("Ingrese el apellido materno");

        } else {
            layOutApellidoM.setErrorEnabled(false);
        }

        if (calle.equals("")) {
            status = false;
            layOutCalle.setError("Ingrese la calle");

        } else {
            layOutCalle.setErrorEnabled(false);
        }

        if (numExt.equals("")) {
            status = false;
            layoutNoExt.setError("Ingrese almenos el numero exterior");

        } else {
            layoutNoExt.setErrorEnabled(false);
        }

        if (movil.equals("")) {
            status = false;
            layOutTelefono.setError("Ingrese el numero telefonico");

        } else {
            layOutTelefono.setErrorEnabled(false);
        }

        if (cp.equals("") || cp.length() < 5) {
            status = false;
            layOutCodigoPostal.setError("Codigo postal no valid");

        } else {
            layOutCodigoPostal.setErrorEnabled(false);
        }

        if (estado.equals("")) {
            status = false;
            layOutEstado.setError("Seleccione un estado");

        } else {
            layOutEstado.setErrorEnabled(false);
        }

        if (municipio.equals("")) {
            status = false;
            layOutMunicipio.setError("Seleccione un municipio");

        } else {
            layOutMunicipio.setErrorEnabled(false);
        }

        if (colonia.equals("")) {
            status = false;
            layOutColonia.setError("Seleccione una colonia");

        } else {
            layOutColonia.setErrorEnabled(false);
        }

        if (parentesco.equals("")) {
            status = false;
            layOutParentesco.setError("Seleccione un parentesco");

        } else {
            layOutParentesco.setErrorEnabled(false);
        }

        return status;
    }


    private void obtenListaParentesco() {
        Call<ResponseParentescos> call = ApiAdapter.getApiService(responseLogIn.token).parentescos();
        call.enqueue(new Callback<ResponseParentescos>() {
            @Override
            public void onResponse(Call<ResponseParentescos> call, Response<ResponseParentescos> response) {
                ArrayList<String> parentescosDesc = new ArrayList<>();
                ArrayList<Long> idsParentescos = new ArrayList<>();

                int code = response.code();
                ResponseParentescos resp = response.body();
                if (response.isSuccessful() && code == 200) {
                    Parentesco[] parentescos = resp.data.parentescos;

                    for (Parentesco parentesco : parentescos) {
                        parentescosDesc.add(parentesco.nombre);
                        idsParentescos.add(parentesco.idParentesco);
                    }

                    if(existeInfo){
                        long idParent = requestInsertClient.data.referencias[0].parentescoId;
                        for (Parentesco parentesco : parentescos) {
                            if(idParent == parentesco.idParentesco){
                                idParentesco = idParent;
                                txtParentesco.setText(parentesco.nombre.toUpperCase());
                                break;
                            }
                        }
                    }
                    ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(ReferenciasAlta.this, android.R.layout.simple_spinner_dropdown_item, parentescosDesc);
                    txtParentesco.setAdapter(spAdaptEstado);
                    txtParentesco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idParentesco = idsParentescos.get(position);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ResponseParentescos> call, Throwable t) {

            }
        });
    }

    private void cleanCp(){
        spinTxtColonia.setText("");
        spintTxtMunicipio.setText("");
        txtEstado.setText("");
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
                        idDescCiudad = municipio.idMunicipio;

                        ArrayList<String> listColonias = new ArrayList<>();
                        ArrayList<String> listIdsColonias = new ArrayList<>();
                        ArrayList<String> listIdsEstados = new ArrayList<>();
                        ArrayList<String> listEstados = new ArrayList<>();
                        ArrayList<String> listMunicipios = new ArrayList<>();
                        ArrayList<String> listIdsMunicipios = new ArrayList<>();

                        listEstados.add(estado.nombre);
                        listIdsEstados.add(estado.codigoEstado);


                        listMunicipios.add(String.valueOf(municipio.nombre));
                        listIdsMunicipios.add(String.valueOf(municipio.idMunicipio));


                        for (ColoniasLista colonia : coloniasListas) {
                            listColonias.add(colonia.nombre);
                            listIdsColonias.add(String.valueOf(colonia.idColonia));
                        }
                        if (existeInfo){
                            long idCol = requestInsertClient.data.referencias[0].direccion.coloniaId;
                            for (ColoniasLista colonia : coloniasListas) {
                                if(idCol == colonia.idColonia){
                                    spinTxtColonia.setText(colonia.nombre.toUpperCase());
                                    idDescColonia = idCol;
                                    break;
                                }
                            }
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ReferenciasAlta.this, android.R.layout.simple_spinner_dropdown_item, listColonias);
                        spinTxtColonia.setAdapter(spinnerArrayAdapter);


                        ArrayAdapter<String> spAdaptMunici = new ArrayAdapter<String>(ReferenciasAlta.this, android.R.layout.simple_spinner_dropdown_item, listMunicipios);
                        spintTxtMunicipio.setAdapter(spAdaptMunici);
                        spintTxtMunicipio.setText(listMunicipios.get(0));
                        idDescMunicipio = Long.parseLong(listIdsMunicipios.get(0));

                        ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(ReferenciasAlta.this, android.R.layout.simple_spinner_dropdown_item, listEstados);
                        txtEstado.setAdapter(spAdaptEstado);
                        txtEstado.setText(listEstados.get(0));
                        idDescEstado = listIdsEstados.get(0);


                        spinTxtColonia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescColonia = Long.parseLong(listIdsColonias.get(position));
                                descColonia = listColonias.get(position);
                            }
                        });
                        txtEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescEstado = listIdsEstados.get(position);
                            }
                        });
                        spintTxtMunicipio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDescMunicipio = Long.parseLong(listIdsMunicipios.get(position));
                            }
                        });

                        dialogFragment.dismiss();
                    } else {
                        dialogFragment.dismiss();
                        Toast.makeText(ReferenciasAlta.this, "Error al consultar el codigo postal: " + responsecodigoPostal.response.codigo + " - " + responsecodigoPostal.response.mensaje, Toast.LENGTH_SHORT).show();

                    }
                } else {
                    dialogFragment.dismiss();
                    Toast.makeText(ReferenciasAlta.this, "Error al consultar el codigo postal: " + responsecodigoPostal.response.codigo + " - " + responsecodigoPostal.response.mensaje, Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponsecodigoPostal> call, Throwable t) {
                dialogFragment.dismiss();
            }
        });
    }

    private void aRegistroDireccion() {

        siguiente.setOnClickListener(v -> {
            Bundle sender = new Bundle();
            Bundle receptor = getIntent().getExtras();
            Intent screenFormsDireccion = new Intent(ReferenciasAlta.this, ReferenciasAlta_dos.class);

            if (validaReferencia()) {

                DataReqCliente dataReqCliente = requestInsertClient.getData();
                capturaInfoRf1();
                dataReqCliente.setReferencias(referencias);

                boolean status = (receptor.getBoolean(nombreStatus));
                sender.putString(nombreTit, titulo);
                sender.putBoolean(nombreStatus, status);
                sender.putSerializable("geo", geolocalizacion);
                sender.putSerializable("reqAltaCliente", requestInsertClient);
                sender.putSerializable("infoLogIn", responseLogIn);

                screenFormsDireccion.putExtras(sender);
                screenFormsDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(screenFormsDireccion);
            }
        });
    }


    private void cargaReferencia1() {


        siguiente = findViewById(R.id.siguiente);
        txtNumInt = findViewById(R.id.txtNumInt);

        txtNombre = findViewById(R.id.txtNombre);
        txtParentesco = findViewById(R.id.txtParentesco);


        txtEstado = findViewById(R.id.txtEstado);
        spintTxtMunicipio = findViewById(R.id.spintTxtMunicipio);
        spinTxtColonia = findViewById(R.id.spinTxtColonia);

        txtCalle = findViewById(R.id.txtCalle);

        txtNumExt = findViewById(R.id.txtNumExt);
        txtCelular = findViewById(R.id.txtCelular);
        editCP = findViewById(R.id.editCP);
        txtApMat = findViewById(R.id.txtApMat);
        txtApPat = findViewById(R.id.txtApPat);

        layOutNombre = findViewById(R.id.layOutNombre);
        layOutParentesco = findViewById(R.id.layOutParentesco);
        layOutApellidoP = findViewById(R.id.layOutApellidoP);
        layOutTelefono = findViewById(R.id.layOutTelefono);
        layOutCodigoPostal = findViewById(R.id.layOutCodigoPostal);
        layOutEstado = findViewById(R.id.layOutEstado);
        layOutMunicipio = findViewById(R.id.layOutMunicipio);
        layOutColonia = findViewById(R.id.layOutColonia);
        layOutCalle = findViewById(R.id.layOutCalle);
        layoutNoExt = findViewById(R.id.layoutNoExt);
        layOutApellidoM = findViewById(R.id.layOutApellidoM);


    }


    private void cargaComponentes() {
        cargaReferencia1();
    }

    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if (validaReferencia()) {
            capturaInfoRf1();
        }
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);

    }
}