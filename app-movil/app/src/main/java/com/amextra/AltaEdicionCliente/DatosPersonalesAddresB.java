package com.amextra.AltaEdicionCliente;

import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.amextra.MainActivity;
import com.amextra.amextra.R;
import com.amextra.dialogs.MenuHeader;
import com.amextra.dialogs.MenuInformacionCliente;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataReqCliente;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseImagesCte;
import com.amextra.io.Response.ResponsetiposViviendas;
import com.amextra.io.Response.TiposVivienda;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPersonalesAddresB extends AppCompatActivity implements OnMapReadyCallback, MenuInformacionCliente.TransfiereDatos {
    Geolocalizacion geolocalizacion = new Geolocalizacion();
    GoogleMap mMap;
    Button siguiente;
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    String titulo;
    ImageButton btnCamaraComprobante;
    ImageView imgComprobanteDom;
    boolean esAlta;
    TextView labelImgComprobante, alertaTiempo;
    AutoCompleteTextView spinTxtTipoVivienda;
    String basee64ComprobanteDomicilio;
    long idDesctipoVivienda;
    TextInputEditText tiempoResAnios, tiempoResMeses;
    double lt;
    double ln;
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    final androidx.fragment.app.FragmentManager mFragmentManager = getSupportFragmentManager();
    final MenuInformacionCliente menuInformacionCliente = new MenuInformacionCliente();
    final androidx.fragment.app.FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

    final androidx.fragment.app.FragmentManager mFragmentManH = getSupportFragmentManager();
    final MenuHeader menuHeader = new MenuHeader();
    final androidx.fragment.app.FragmentTransaction mFragmentHeaderTransac = mFragmentManH.beginTransaction();
    Bundle mBundle = new Bundle();
    Bundle bHeader = new Bundle();

    String REQ_ALTA_CLI = "reqAltaCliente";

    TextInputLayout layoutAnios, layOutTipoVivienda;
    InfoUSer responseLogIn = new InfoUSer();
    int quality = 0;
    String currentPath;
    Uri imageUri;

    CircularProgressIndicator loaderCircular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales_addres_b);
        setAdjustScreen();
        siguiente = findViewById(R.id.btnSiguiente);
        spinTxtTipoVivienda = findViewById(R.id.spinTxtTipoVivienda);
        btnCamaraComprobante = findViewById(R.id.btnCamaraComprobante);
        imgComprobanteDom = findViewById(R.id.imgComprobanteDom);
        labelImgComprobante = findViewById(R.id.labelImgComprobante);
        tiempoResAnios = findViewById(R.id.tiempoResAnios);
        tiempoResMeses = findViewById(R.id.tiempoResMeses);
        layoutAnios = findViewById(R.id.layoutAnios);
        layOutTipoVivienda = findViewById(R.id.layOutTipoVivienda);
        loaderCircular = findViewById(R.id.loaderCircular);

        loaderCircular.setVisibility(View.GONE);
        Bundle recepcion = getIntent().getExtras();

        if (recepcion != null) {
            esAlta = recepcion.getBoolean(nombreStatus);
            titulo = (recepcion.getString(nombreTit));
            responseLogIn = (InfoUSer) recepcion.getSerializable("infoLogIn");
            requestInsertClient = (RequestInsertClient) recepcion.getSerializable(REQ_ALTA_CLI);
            if (requestInsertClient.data.direccion.tipoViviendaId != 0) {
                mapDataCliente(requestInsertClient.data);

            }

        }


        mBundle.putString(nombreTit, titulo);
        mBundle.putInt("itm", 4);
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


        btnCamaraComprobante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCamara();
            }
        });
        cargaTiposVivienda();
        iniciaCargaDatosLaborales();
    }

    private void mapDataCliente(DataReqCliente data) {

        tiempoResAnios.setText(String.valueOf(data.direccion.tiempoResidencia));
        tiempoResMeses.setText(String.valueOf(data.direccion.tiempoResidenciaMeses));

        basee64ComprobanteDomicilio = data.direccion.comprobante;
        currentPath = data.direccion.comprobante;
        File imgFile = new File(currentPath);

        if (imgFile.exists()) {
            labelImgComprobante.setVisibility(View.GONE);
            loaderCircular.setVisibility(View.VISIBLE);

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgComprobanteDom.setImageBitmap(myBitmap);
            imgComprobanteDom.setVisibility(View.VISIBLE);
            loaderCircular.setVisibility(View.GONE);

        }

        if (!esAlta && !imgFile.exists()) {
            labelImgComprobante.setVisibility(View.GONE);
            loaderCircular.setVisibility(View.VISIBLE);
            Call<ResponseImagesCte> call = ApiAdapter.getApiService(responseLogIn.token).getImagesClient(requestInsertClient.data.id);
            call.enqueue(new Callback<ResponseImagesCte>() {
                @Override
                public void onResponse(Call<ResponseImagesCte> call, Response<ResponseImagesCte> response) {
                    int code = response.code();
                    boolean status = response.isSuccessful();
                    if (code == 200 && status) {
                        ResponseImagesCte info = response.body();
                        if (info.response.codigo == 200) {
                            String b64 = info.data.comprobanteDomicilio;
                            Bitmap bm = base64toBm(b64);
                            imgComprobanteDom.setImageBitmap(bm);
                            loaderCircular.setVisibility(View.GONE);
                            imgComprobanteDom.setVisibility(View.VISIBLE);
                        } else {
                            loaderCircular.setVisibility(View.GONE);
                            new SweetAlertDialog(DatosPersonalesAddresB.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Alerta")
                                    .setContentText(info.response.codigo + " " + info.response.mensaje)
                                    .show();
                        }
                    } else {

                        final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                        new SweetAlertDialog(DatosPersonalesAddresB.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText(alertText)
                                .setConfirmText("Continuar")
                                .setConfirmClickListener(sweetAlertDialog -> {
                                    finish();
                                    Intent login = new Intent(DatosPersonalesAddresB.this, MainActivity.class);
                                    login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(login);
                                })
                                .show();

                    }
                }

                @Override
                public void onFailure(Call<ResponseImagesCte> call, Throwable t) {
                    new SweetAlertDialog(DatosPersonalesAddresB.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ERROR")
                            .setContentText(t.toString())
                            .show();
                }
            });
        }

    }


    private void abreCamara() {
        String fileName = "img_amx_";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {


            File image = File.createTempFile(fileName + timeStamp, ".jpg", storageDir);
            currentPath = image.getAbsolutePath();
            imageUri = FileProvider.getUriForFile(this, "com.amextra.fileprovider", image);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(currentPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            Bitmap img = uriToBitmap(imageUri);
            imgComprobanteDom.setImageBitmap(img);
            imgComprobanteDom.setVisibility(View.VISIBLE);
            labelImgComprobante.setVisibility(View.GONE);
            basee64ComprobanteDomicilio = currentPath;

        }
    }


    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    this.getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap base64toBm(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }

    private void iniciaCargaDatosLaborales() {
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sender = new Bundle();
                Bundle receptor = getIntent().getExtras();
                Intent consultaInfDireccion = new Intent(DatosPersonalesAddresB.this, DatosClienteLaborales.class);

                if (validaInfo()) {
                    recopilaDatos();
                    boolean status = (receptor.getBoolean(nombreStatus));
                    sender.putString(nombreTit, titulo);
                    sender.putBoolean(nombreStatus, status);
                    sender.putSerializable("infoLogIn", responseLogIn);
                    sender.putSerializable("reqAltaCliente", requestInsertClient);
                    consultaInfDireccion.putExtras(sender);
                    consultaInfDireccion.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    startActivity(consultaInfDireccion);
                }
            }
        });
    }

    private void cargaTiposVivienda() {
        Call<ResponsetiposViviendas> call = ApiAdapter.getApiService(responseLogIn.token).tiposViviendas();
        call.enqueue(new Callback<ResponsetiposViviendas>() {
            @Override
            public void onResponse(Call<ResponsetiposViviendas> call, Response<ResponsetiposViviendas> response) {
                int code = response.code();
                if (code == 200 && response.isSuccessful()) {
                    ResponsetiposViviendas info = response.body();
                    if (info.response.codigo == 200) {
                        List<String> listaViviendas = new ArrayList<>();
                        List<String> listaIdViviendas = new ArrayList<>();


                        TiposVivienda[] tiposViviendas = info.data.tiposViviendas;
                        for (TiposVivienda tiposVivienda : tiposViviendas) {
                            listaViviendas.add(tiposVivienda.nombre.toUpperCase());
                            listaIdViviendas.add(String.valueOf(tiposVivienda.idVivienda));
                        }

                        if (requestInsertClient.data.direccion.tipoViviendaId != 0) {
                            Long idTipoViv = requestInsertClient.data.direccion.tipoViviendaId;
                            for (TiposVivienda tiposVivienda : tiposViviendas) {
                                if (idTipoViv == tiposVivienda.idVivienda) {
                                    spinTxtTipoVivienda.setText(tiposVivienda.nombre);
                                    idDesctipoVivienda = idTipoViv;
                                    break;

                                }
                            }

                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DatosPersonalesAddresB.this, android.R.layout.simple_spinner_dropdown_item, listaViviendas);
                        spinTxtTipoVivienda.setAdapter(spinnerArrayAdapter);
                        spinTxtTipoVivienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                idDesctipoVivienda = Long.parseLong(listaIdViviendas.get(position));

                            }
                        });
                        call.cancel();

                    } else {
                        call.cancel();
                        Toast.makeText(DatosPersonalesAddresB.this, info.response.codigo + " " + info.response.mensaje, Toast.LENGTH_LONG).show();
                    }
                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    new SweetAlertDialog(DatosPersonalesAddresB.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(alertText)
                            .setConfirmText("Continuar")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                finish();
                                Intent login = new Intent(DatosPersonalesAddresB.this, MainActivity.class);
                                login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(login);
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponsetiposViviendas> call, Throwable t) {
                Toast.makeText(DatosPersonalesAddresB.this, t.toString(), Toast.LENGTH_LONG).show();

                call.cancel();
            }
        });
    }

    protected void setAdjustScreen() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void recopilaDatos() {
        DataReqCliente dataReqCliente = requestInsertClient.getData();
        int anios = defineValor(tiempoResAnios.getText().toString());
        int mesesIni = defineValor(tiempoResMeses.getText().toString());
        long mese = 0;
        if (mesesIni > 0) {
            mese = mesesIni / 12;
        }


        long tiempoRec = anios + mese;
        dataReqCliente.getDireccion().setTiempoResidenciaMes(mesesIni);
        dataReqCliente.getDireccion().setTiempoResidencia(tiempoRec);
        dataReqCliente.getDireccion().setTipoViviendaId(idDesctipoVivienda);
        dataReqCliente.getDireccion().setComprobante(basee64ComprobanteDomicilio);
        dataReqCliente.setAsesorId(responseLogIn.usuarioId);
        requestInsertClient.setData(dataReqCliente);
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


    private boolean validaInfo() {
        boolean status = true;

        String txtAnios = tiempoResAnios.getText().toString();
        String meses = tiempoResMeses.getText().toString();
        String vivienda = spinTxtTipoVivienda.getText().toString();

        // layoutAnios, layMeses,layOutTipoVivienda
        if (txtAnios.equals("") && meses.equals("")) {
            status = false;
            layoutAnios.setError("Almenos de debe llenar un campo del tiempo de residencia");
        } else {
            layoutAnios.setErrorEnabled(false);
        }


        if (vivienda.equals("")) {
            layOutTipoVivienda.setError("Tipo de vivienda requerido");
            status = false;
        } else {
            layOutTipoVivienda.setErrorEnabled(false);
        }
        return status;

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ubi = new LatLng(lt, ln);
        mMap.addMarker(new MarkerOptions().position(ubi).title("Ubicación Actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubi));
    }

    @Override
    public void transfiereInfo(RequestInsertClient req) {
        if (validaInfo()) {
            recopilaDatos();
        }
        mBundle.putSerializable("infoLogIn", responseLogIn);
        mBundle.putSerializable(REQ_ALTA_CLI, requestInsertClient);
        menuInformacionCliente.setArguments(mBundle);


    }
}