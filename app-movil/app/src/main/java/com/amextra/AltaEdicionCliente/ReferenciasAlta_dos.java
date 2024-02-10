package com.amextra.AltaEdicionCliente;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.amextra.MainActivity;
import com.amextra.SMS.EnviaSMS;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.Direccion;
import com.amextra.io.Request.Referencia;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.ColoniasLista;
import com.amextra.io.Response.EstadoID;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.ID;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.Parentesco;
import com.amextra.io.Response.ResponseGetClientes;
import com.amextra.io.Response.ResponseParentescos;
import com.amextra.io.Response.ResponsecodigoPostal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import cn.pedant.SweetAlert.SweetAlertDialog ;


public class ReferenciasAlta_dos extends AppCompatActivity implements MenuInformacionCliente.TransfiereDatos {

    Button siguiente;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    boolean esAlta;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    TextInputEditText txtNombre, txtCalle, txtNumExt,
            txtCelular, editCP, txtNumInt, txtApMat, txtApPat;
    long idDescColonia, idDescMunicipio, idDescCiudad, idParentesco;
    String descColonia,idDescEstado;
    private AutoCompleteTextView spinTxtColonia, txtEstado, spintTxtMunicipio, txtParentesco;

    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();

    InfoUSer responseLogIn = new InfoUSer();
    Bundle mBundle = new Bundle();
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    Referencia referencia = new Referencia();
    TextInputLayout layOutNombre, layOutParentesco, layOutApellidoP, layOutApellidoM, layOutTelefono,
            layOutCodigoPostal, layOutEstado, layOutMunicipio, layOutColonia, layOutCalle, layoutNoExt;

    Bundle bHeader = new Bundle();
    String REQ_ALTA_CLI = "reqAltaCliente";
    boolean existeInfo = false;
    String curp = "", telfono ="";

    Referencia[] referencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referencias_alta_dos);
        setAdjustScreen();
        cargaReferencia1();

        Bundle recepcion = getIntent().getExtras();
        if (recepcion != null) {
            geolocalizacion = (Geolocalizacion) recepcion.getSerializable("geo");
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            titulo = (recepcion.getString(nombreTit));
            esAlta = recepcion.getBoolean(nombreStatus);


                RequestInsertClient reqInsCliTmp = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
                recepcion.clear();
                if (reqInsCliTmp != null) {
                    requestInsertClient = reqInsCliTmp;
                    referencias = requestInsertClient.data.referencias;
                    curp = requestInsertClient.data.persona.curp;
                    telfono = requestInsertClient.data.persona.telefono;
                    if (requestInsertClient.data.referencias != null) {
                        if (requestInsertClient.data.referencias[1] != null) {
                            mapDataClient(requestInsertClient.data);
                            existeInfo=true;
                        }
                    }
                }




        }

        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 7);
        mBundle.putBoolean(nombreStatus, esAlta);
        mBundle.putSerializable("infoLogIn",responseLogIn);


        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        bHeader.putString(nombreTit, titulo);
        bHeader.putSerializable("infoLogIn",responseLogIn);
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
                if(esAlta){
                    generaAltaCliente(requestInsertClient);
                }else{
                    updateClient(requestInsertClient);
                }


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
                        long idParent = requestInsertClient.data.referencias[1].parentescoId;
                        for (Parentesco parentesco : parentescos) {
                            if(idParent == parentesco.idParentesco){
                                idParentesco = idParent;
                                txtParentesco.setText(parentesco.nombre.toUpperCase());
                                break;
                            }
                        }
                    }
                    ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(ReferenciasAlta_dos.this, android.R.layout.simple_spinner_dropdown_item, parentescosDesc);
                    txtParentesco.setAdapter(spAdaptEstado);
                    txtParentesco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            idParentesco = idsParentescos.get(position);
                        }
                    });

                }

                else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(ReferenciasAlta_dos.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(ReferenciasAlta_dos.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseParentescos> call, Throwable t) {

            }
        });
    }

    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if(validaReferencia()){
            capturaInfoRf1();
        }
        mBundle.putSerializable("infoLogIn",responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);
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
        if (cp.equals("")) {
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

    private void updateClient(RequestInsertClient body){
        DialogFragment dialogUpdate = LoaderTransparent.loaderTransparent("Actualizando datos de cliente..");
        dialogUpdate.show(getSupportFragmentManager(), "LoaderTransparent");
        try {
            if (validaReferencia()) {
                DataReqCliente dataReqCliente = requestInsertClient.getData();
                capturaInfoRf1();

                if(esAlta){
                    if(!dataReqCliente.direccion.comprobante.equals("") && !dataReqCliente.direccion.comprobante.equals("null")) {
                        String comprobante = requestInsertClient.data.direccion.comprobante;
                        String comp64 = pathToBase64(comprobante,100);
                        dataReqCliente.getDireccion().setComprobante(comp64);
                        dataReqCliente.getDireccion().setBanderaCambioImagen(true);
                    }else{
                        dataReqCliente.getDireccion().setBanderaCambioImagen(false);
                    }

                    if(!dataReqCliente.identificacion.imagen.equals("") && !dataReqCliente.identificacion.imagen.equals("null") ){
                        String ident = requestInsertClient.data.identificacion.imagen;
                        String comp64 = pathToBase64(ident,100);
                        dataReqCliente.getIdentificacion().setImagen(comp64);

                    }

                }
                else{
                    if(!dataReqCliente.direccion.comprobante.equals("") && !dataReqCliente.direccion.comprobante.equals("null")){
                        String comprobante = requestInsertClient.data.direccion.comprobante;
                        String comp64 = pathToBase64(comprobante,100);
                        dataReqCliente.getDireccion().setComprobante(comp64);
                        dataReqCliente.getDireccion().setBanderaCambioImagen(true);
                    }else{
                        dataReqCliente.getDireccion().setBanderaCambioImagen(false);

                    }

                    if(!dataReqCliente.identificacion.imagen.equals("") && !dataReqCliente.identificacion.imagen.equals("null") ){
                        String ident = requestInsertClient.data.identificacion.imagen;
                        String comp64 = pathToBase64(ident,100);
                        dataReqCliente.getIdentificacion().setImagen(comp64);

                    }
                }



                dataReqCliente.setReferencias(referencias);
                Call<ResponseGetClientes> call = ApiAdapter.getApiService(responseLogIn.token).actualizaCliente(body);
                call.enqueue(new Callback<ResponseGetClientes>() {
                    @Override
                    public void onResponse(Call<ResponseGetClientes> call, Response<ResponseGetClientes> response) {
                        int code = response.code();
                        boolean status = response.isSuccessful();
                        if (code == 200 && status) {
                            ResponseGetClientes datos = response.body();
                            if (datos.response.codigo == 200) {
                                Intent enviaSms = new Intent(ReferenciasAlta_dos.this, EnviaSMS.class);
                                Bundle sender = new Bundle();
                                sender.putSerializable("infoLogIn",responseLogIn);
                                sender.putSerializable("telefono", telfono);
                                sender.putSerializable("curp", curp);
                                requestInsertClient = new RequestInsertClient();
                                enviaSms.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                enviaSms.putExtras(sender);
                                startActivity(enviaSms);
                                dialogUpdate.dismiss();
                                Toast.makeText(ReferenciasAlta_dos.this, "Actualización exitosa" + datos.response.codigo + " - " + datos.response.mensaje, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                dialogUpdate.dismiss();
                                new SweetAlertDialog(ReferenciasAlta_dos.this,SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Advertencia")
                                        .setContentText(datos.response.codigo + " - " + datos.response.mensaje)
                                        .show();

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseGetClientes> call, Throwable t) {
                        dialogUpdate  .dismiss();
                        Toast.makeText(ReferenciasAlta_dos.this, "ERROR: " + t, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }catch (Exception e){
            dialogUpdate.dismiss();
            Toast.makeText(ReferenciasAlta_dos.this, "Error de actualizacion " +e, Toast.LENGTH_SHORT).show();
        }

    }

    private void cleanCp(){
        spinTxtColonia.setText("");
        spintTxtMunicipio.setText("");
        txtEstado.setText("");
    }
    private void generaAltaCliente(RequestInsertClient body) {

        if (validaReferencia()) {
            DataReqCliente dataReqCliente = requestInsertClient.getData();
            Referencia[]  referencias = dataReqCliente.getReferencias();
            capturaInfoRf1();


            if(esAlta){
                if(!dataReqCliente.direccion.comprobante.equals("")){
                    String comprobante = requestInsertClient.data.direccion.comprobante;
                    String comp64 = pathToBase64(comprobante,100);
                    dataReqCliente.getDireccion().setComprobante(comp64);
                    dataReqCliente.getDireccion().setBanderaCambioImagen(true);
                }else{
                    dataReqCliente.getDireccion().setBanderaCambioImagen(false);
                }

                if(!dataReqCliente.identificacion.imagen.equals("")){
                    String ident = requestInsertClient.data.identificacion.imagen;
                    String comp64 = pathToBase64(ident,100);
                    dataReqCliente.getIdentificacion().setImagen(comp64);

                }

            }
            else{
                if(!dataReqCliente.direccion.comprobante.equals("")){
                    String comprobante = requestInsertClient.data.direccion.comprobante;
                    String comp64 = pathToBase64(comprobante,100);
                    dataReqCliente.getDireccion().setComprobante(comp64);
                    dataReqCliente.getDireccion().setBanderaCambioImagen(true);
                }else{
                    dataReqCliente.getDireccion().setBanderaCambioImagen(false);

                }

                if(!dataReqCliente.identificacion.imagen.equals("")){
                    String ident = requestInsertClient.data.identificacion.imagen;
                    String comp64 = pathToBase64(ident,100);
                    dataReqCliente.getIdentificacion().setImagen(comp64);

                }
            }

            dataReqCliente.setReferencias(referencias);
            DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Generando alta de cliente..");
            dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");
            Call<ResponseGetClientes> call = ApiAdapter.getApiService(responseLogIn.token).generaAltaCliente(body);
            call.enqueue(new Callback<ResponseGetClientes>() {
                @Override
                public void onResponse(Call<ResponseGetClientes> call, Response<ResponseGetClientes> response) {
                    int code = response.code();
                    boolean status = response.isSuccessful();
                    if (code == 200 && status) {
                        ResponseGetClientes datos = response.body();
                        if (datos.response.codigo == 200) {
                            requestInsertClient = new RequestInsertClient();
                            Intent enviaSms = new Intent(ReferenciasAlta_dos.this, EnviaSMS.class);
                            Bundle sender = new Bundle();
                            sender.clear();
                            sender.putSerializable("infoLogIn",responseLogIn);
                            sender.putSerializable("telefono", telfono);
                            sender.putSerializable("curp", curp);
                            enviaSms.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            enviaSms.putExtras(sender);
                            startActivity(enviaSms);
                            dialogFragment.dismiss();
                            Toast.makeText(ReferenciasAlta_dos.this, "Alta EXITOSA" + datos.response.codigo + " - " + datos.response.mensaje, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            dialogFragment.dismiss();
                            new SweetAlertDialog(ReferenciasAlta_dos.this,SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Advertencia")
                                    .setContentText(datos.response.codigo + " - " + datos.response.mensaje)
                                    .show();

                        }
                    }

                    else {
                        final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                        new SweetAlertDialog(ReferenciasAlta_dos.this,SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText(alertText)
                                .setConfirmText("Continuar")
                                .setConfirmClickListener(sweetAlertDialog -> {
                                    finish();
                                    Intent login = new Intent(ReferenciasAlta_dos.this, MainActivity.class);
                                    login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(login);
                                })
                                .show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseGetClientes> call, Throwable t) {
                    dialogFragment.dismiss();
                    Toast.makeText(ReferenciasAlta_dos.this, "ERROR: " + t, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


    private void capturaInfoRf1() {

        String name = txtNombre.getText().toString();
        String apPat = txtApPat.getText().toString();
        String apMat = txtApMat.getText().toString();
        String numInt = "0";
        String numExt = "0";
        if(!txtNumExt.getText().toString().equals("")){
            numExt = txtNumExt.getText().toString();
        }

        if(!txtNumInt.getText().toString().equals("")){
            numInt = txtNumInt.getText().toString();
        }


        if(!existeInfo){
            Direccion dir = new Direccion();
            Referencia referencia = new Referencia();
            referencia.setDireccion(dir);
            referencia.setNombre(name);
            referencia.setApellidoPaterno(apPat);
            referencia.setID("1");
            referencia.setApellidoMaterno(apMat);
            referencia.setParentescoID(idParentesco);
            referencia.setTelefono(txtCelular.getText().toString());
            referencia.getDireccion().setBanderaCambioImagen(false);
            referencia.getDireccion().setID(1);
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
            referencias[1] = referencia;
            requestInsertClient.getData().setAsesorId(responseLogIn.usuarioId);

        }else{
            Referencia referencia = requestInsertClient.data.referencias[1];
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

            referencias[1] = referencia;
        }
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

    private void mapDataClient(DataReqCliente data) {

        Referencia cliente = data.referencias[1];
        txtNombre.setText(cliente.nombre);
        txtApPat.setText(cliente.apellidoPaterno);
        txtApMat.setText(cliente.apellidoMaterno);
        txtCelular.setText(cliente.telefono);
        txtCalle.setText(cliente.direccion.calle);
        editCP.setText(cliente.direccion.cp);
        txtNumExt.setText(cliente.direccion.numeroExterior);
        txtNumInt.setText(cliente.direccion.numeroInterior);
        consultaCP(cliente.direccion.cp);

    }


    private String pathToBase64(String path, int quality) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.NO_WRAP);

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
                        listIdsEstados.add(String.valueOf(estado.codigoEstado));


                        listMunicipios.add(String.valueOf(municipio.nombre));
                        listIdsMunicipios.add(String.valueOf(municipio.idMunicipio));


                        for (ColoniasLista colonia : coloniasListas) {
                            listColonias.add(colonia.nombre);
                            listIdsColonias.add(String.valueOf(colonia.idColonia));
                        }
                        if (existeInfo){
                            long idCol = requestInsertClient.data.referencias[1].direccion.coloniaId;
                            for (ColoniasLista colonia : coloniasListas) {
                                if(idCol == colonia.idColonia){
                                    spinTxtColonia.setText(colonia.nombre.toUpperCase());
                                    idDescColonia = idCol;
                                    break;
                                }
                            }
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ReferenciasAlta_dos.this, android.R.layout.simple_spinner_dropdown_item, listColonias);
                        spinTxtColonia.setAdapter(spinnerArrayAdapter);


                        ArrayAdapter<String> spAdaptMunici = new ArrayAdapter<String>(ReferenciasAlta_dos.this, android.R.layout.simple_spinner_dropdown_item, listMunicipios);
                        spintTxtMunicipio.setAdapter(spAdaptMunici);
                        spintTxtMunicipio.setText(listMunicipios.get(0));
                        idDescMunicipio = Long.parseLong(listIdsMunicipios.get(0));

                        ArrayAdapter<String> spAdaptEstado = new ArrayAdapter<String>(ReferenciasAlta_dos.this, android.R.layout.simple_spinner_dropdown_item, listEstados);
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
                        Toast.makeText(ReferenciasAlta_dos.this, "Error al consultar el codigo postal: " + responsecodigoPostal.response.codigo + " - " + responsecodigoPostal.response.mensaje, Toast.LENGTH_SHORT).show();

                    }
                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(ReferenciasAlta_dos.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(ReferenciasAlta_dos.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponsecodigoPostal> call, Throwable t) {
                dialogFragment.dismiss();
            }
        });
    }

}