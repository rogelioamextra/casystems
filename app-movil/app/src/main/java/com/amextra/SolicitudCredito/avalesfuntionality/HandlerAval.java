package com.amextra.SolicitudCredito.avalesfuntionality;

import static android.app.Activity.RESULT_OK;
import static com.amextra.utils.Constants.MISSING_TOKEN_TEXT;
import static com.amextra.utils.Constants.SERVER_ERROR_TEXT;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.Aval;
import com.amextra.io.Request.DataCurp;
import com.amextra.io.Request.DataReqSms;
import com.amextra.io.Request.DataResValidaSMS;
import com.amextra.io.Request.DescribeIdentificacion;
import com.amextra.io.Request.RequestConsultaCurp;
import com.amextra.io.Request.RequestEnvioSMS;
import com.amextra.io.Request.RequestValidaSMS;
import com.amextra.io.Response.ResponseCurp;
import com.amextra.io.Response.ResponseEnvioSMS;
import com.amextra.io.Response.ResponseValidaSMS;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HandlerAval extends DialogFragment {

    Activity actividad;
    SendData sendData;

    Button btnCancela, btnSiguiente, btnSaveAval;
    int dia, mes, anio;

    Aval objSend = new Aval();
    ArrayList<Long> idsIdent = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();


    TextInputEditText editTxtCurp, editTxtNombre, editTxtApellidoP, editTxtApellidoM, editTxtFechaNacimiento, txtTelefonoEmpresa;
    TextInputLayout layOutTxtCurp, layOuteditTxtNombre, layOuteditTxtApellidoP, layOuteditTxtApellidoM, layOutTeditTxtFechaNacimiento, layOutTelefono;

    CardView expandFront, expandComprobante, expandBack, cardData, cardSmsAval;
    ImageView imgFront, imgBack, imgComprobante;
    ImageButton btnCamaraFront, viewFrontBtn, viewBacktBtn, btnCamaraBack, btnCamComprobante, viewBtnComprobante, backIcon;
    LinearLayout layOutFront, layOutBakc, layOutComprobante;
    AutoCompleteTextView spinerTxtTipoID;
    TextView txtFrente, txtTitle;

    String currentPath = "", newToken = "";
    String tipoImgs = "";
    Uri imageUri;
    int quality = 100;
    boolean existInfo = false;
    DatePickerDialog picker;
    Calendar calendar;
    String curpCliente = "";
    final String CURP_CLI = "CURP_CLI";

    EditText txtTkn;
    private String token = "";
    final int STATUS_OK = 200;


    public static HandlerAval handlerAval(ArrayList<DescribeIdentificacion> ids, Aval aval, String token, String curp) {
        HandlerAval handler = new HandlerAval();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ids", ids);
        bundle.putSerializable("aval", aval);
        bundle.putSerializable("CURP_CLI", curp);
        bundle.putString("tkn", token);


        handler.setArguments(bundle);
        return handler;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return showDialogAlert();
    }


    private AlertDialog showDialogAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.fragment_handle_aval, null);
        txtFrente = v.findViewById(R.id.txtFrente);
        expandFront = v.findViewById(R.id.expandFront);
        expandBack = v.findViewById(R.id.expandBack);
        expandComprobante = v.findViewById(R.id.expandComprobante);
        spinerTxtTipoID = v.findViewById(R.id.spinerTxtTipoID);
        btnCancela = v.findViewById(R.id.btnCancela);
        btnSiguiente = v.findViewById(R.id.btnSiguiente);


        btnSaveAval = v.findViewById(R.id.btnSaveAval);
        txtTkn = v.findViewById(R.id.txtTkn);
        backIcon = v.findViewById(R.id.backIcon);


        imgFront = v.findViewById(R.id.imgFront);
        imgBack = v.findViewById(R.id.imgBack);
        imgComprobante = v.findViewById(R.id.imgComprobante);
        txtTelefonoEmpresa = v.findViewById(R.id.txtTelefonoEmpresa);
        layOutTelefono = v.findViewById(R.id.layOutTelefono);


        editTxtNombre = v.findViewById(R.id.editTxtNombre);
        editTxtCurp = v.findViewById(R.id.editTxtCurp);
        editTxtApellidoP = v.findViewById(R.id.editTxtApellidoP);
        editTxtApellidoM = v.findViewById(R.id.editTxtApellidoM);
        editTxtFechaNacimiento = v.findViewById(R.id.editTxtFechaNacimiento);


        layOutTxtCurp = v.findViewById(R.id.layOutTxtCurp);
        layOuteditTxtNombre = v.findViewById(R.id.layOuteditTxtNombre);
        layOuteditTxtApellidoP = v.findViewById(R.id.layOuteditTxtApellidoP);
        layOuteditTxtApellidoM = v.findViewById(R.id.layOuteditTxtApellidoM);
        layOutTeditTxtFechaNacimiento = v.findViewById(R.id.layOutTeditTxtFechaNacimiento);


        viewFrontBtn = v.findViewById(R.id.viewFrontBtn);
        viewBacktBtn = v.findViewById(R.id.viewBacktBtn);
        viewBtnComprobante = v.findViewById(R.id.viewBtnComprobante);

        btnCamaraBack = v.findViewById(R.id.btnCamaraBack);
        btnCamComprobante = v.findViewById(R.id.btnCamComprobante);
        btnCamaraFront = v.findViewById(R.id.btnCamaraFront);

        layOutFront = v.findViewById(R.id.layOutFront);
        layOutBakc = v.findViewById(R.id.layOutBakc);
        layOutComprobante = v.findViewById(R.id.layOutComprobante);
        cardSmsAval = v.findViewById(R.id.cardSmsAval);
        cardData = v.findViewById(R.id.cardData);
        txtTitle = v.findViewById(R.id.txtTitle);


        calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH) + 1;
        anio = calendar.get(Calendar.YEAR);
        cardData.setVisibility(View.VISIBLE);
        cardSmsAval.setVisibility(View.GONE);
        txtTitle.setText("Nuevo aval");
        if (getArguments() != null) {
            newToken = getArguments().getString("tkn");
            curpCliente = getArguments().getString(CURP_CLI);
            ArrayList<DescribeIdentificacion> infoIds = (ArrayList<DescribeIdentificacion>) getArguments().getSerializable("ids");
            if (!infoIds.isEmpty()) {
                for (DescribeIdentificacion info : infoIds) {
                    idsIdent.add(info.getIdIdentificacion());
                    descriptions.add(info.getDescribeId());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this.actividad, R.layout.dropdown_item, descriptions);
                spinerTxtTipoID.setText(descriptions.get(0));
                objSend.setTipoIdentificacion(idsIdent.get(0));
                showHideBackId(idsIdent.get(0));
                spinerTxtTipoID.setAdapter(adapter);
                spinerTxtTipoID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        long tipoId = idsIdent.get(position);
                        showHideBackId(tipoId);
                    }
                });

            }
            Aval tmp = (Aval) getArguments().getSerializable("aval");
            if (tmp != null) {
                objSend = tmp;
                existInfo = true;
                drawDataAval(objSend);
            }
        }

        editTxtCurp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RequestConsultaCurp requestConsultaCurp = new RequestConsultaCurp();
                requestConsultaCurp.setData(new DataCurp());
                String curp = s.toString();
                if (curp.length() == 18) {
                    if (curpCliente.equals(curp)) {
                        SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.WARNING_TYPE);
                        sw.setTitleText("Atención");
                        sw.setContentText("El cliente no puede ser registrado como aval");
                        sw.setConfirmText("Ok");
                        sw.setConfirmClickListener(sweetAlertDialog -> {
                            sw.dismiss();

                        });
                        sw.show();
                    } else {
                        requestConsultaCurp.getData().setCurp(curp);
                        requestConsultaCurp.getData().setPosibleCliente(false);
                        consultaCurp(requestConsultaCurp);
                        layOutTxtCurp.setErrorEnabled(false);
                    }

                } else {
                    cleanItems();
                    layOutTxtCurp.setError("La longitud obligatoria del curp es de 18 caracteres");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaInfo()) {
                    RequestEnvioSMS req = new RequestEnvioSMS();
                    req.setData(new DataReqSms());
                    req.getData().setAval(true);
                    req.getData().setNumeroEnvio(txtTelefonoEmpresa.getText().toString());
                    req.getData().setCurp(editTxtCurp.getText().toString());
                    sendSmsToken(req);
                }
            }
        });

        txtTkn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                token = s.toString();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardData.setVisibility(View.VISIBLE);
                cardSmsAval.setVisibility(View.GONE);
            }
        });

        btnSaveAval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestValidaSMS require = new RequestValidaSMS();
                require.setData(new DataResValidaSMS());
                require.getData().setCodigo(token);
                require.getData().setAval(true);
                require.getData().setCurp(objSend.getCurp());
                require.getData().setNumeroEnvio(objSend.getTelefono());

                validaSms(require);
            }
        });

        doExpand(layOutFront, viewFrontBtn, imgFront);
        doExpand(layOutBakc, viewBacktBtn, imgBack);
        doExpand(layOutComprobante, viewBtnComprobante, imgComprobante);
        doPhoto(btnCamComprobante, "c");
        doPhoto(btnCamaraBack, "b");
        doPhoto(btnCamaraFront, "f");
        muestraCalendario();
        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.setView(v);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;

    }

    private void validaSms(RequestValidaSMS body) {

        String pin = txtTkn.getText().toString();
        boolean doReq = pin.isEmpty() || pin.isBlank() || pin.length() < 5;

        if (!doReq) {
            DialogFragment dfr = LoaderTransparent.loaderTransparent("Validando token...");
            dfr.show(getActivity().getSupportFragmentManager(), "LoaderTransparent");

            Call<ResponseValidaSMS> call = ApiAdapter.getApiService(newToken).validaSMS(body);
            call.enqueue(new Callback<ResponseValidaSMS>() {
                @Override
                public void onResponse(Call<ResponseValidaSMS> call, Response<ResponseValidaSMS> response) {
                    if (response.isSuccessful() && response.code() == STATUS_OK) {
                        ResponseValidaSMS rs = response.body();
                        assert rs != null;
                        if (rs.getResponse().getCodigo() == STATUS_OK) {
                            objSend.setConfirmSms(true);
                            dfr.dismiss();
                            saveAval();
                        } else {
                            objSend.setConfirmSms(false);
                            saveAval();
                            call.cancel();

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseValidaSMS> call, Throwable t) {
                    objSend.setConfirmSms(false);
                    saveAval();
                    dfr.dismiss();
                    SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.ERROR_TYPE);
                    sw.setTitleText("Error");
                    sw.setContentText(t.toString());
                    sw.setConfirmText("Continuar");
                    sw.setConfirmClickListener(sweetAlertDialog -> {
                        sw.dismiss();

                    });
                    sw.show();
                    call.cancel();
                }
            });
        } else {


            SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.WARNING_TYPE);
            sw.setTitleText("Atención");
            sw.setContentText("Para continuar con tu solicitud de crédito, es necesario validar tu PIN. Si no recibes el PIN en unos minutos, por favor haz clic en \"Continuar\". Si has recibido el SMS, selecciona \"Validar Token\". Recuerda que la longitud del token es de 5 caracteres.\n" +
                    "\n\n\n" +
                    "Agradecemos tu colaboración y paciencia.");
            sw.setConfirmText("Continuar");
            sw.setCancelText("Validar Token");
            sw.setCancelClickListener(sweetAlertDialog -> {
                sw.dismiss();
            });
            sw.setConfirmClickListener(sweetAlertDialog -> {
                objSend.setConfirmSms(false);
                saveAval();
                sw.dismiss();

            });
            sw.show();


        }


    }


    private void doPhoto(ImageButton imgBtn, String tipo) {
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoImgs = tipo;
                abreCamara();
            }
        });

    }


    private void cleanItems() {
        editTxtNombre.setText("");
        editTxtApellidoP.setText("");
        editTxtApellidoM.setText("");
        editTxtFechaNacimiento.setText("");
    }

    private void consultaCurp(RequestConsultaCurp req) {
        DialogFragment dfr = LoaderTransparent.loaderTransparent("Validando curp...");
        dfr.show(getActivity().getSupportFragmentManager(), "LoaderTransparent");

        Call<ResponseCurp> call = ApiAdapter.getApiService(newToken).consultaCurp(req);

        call.enqueue(new Callback<ResponseCurp>() {

            @Override
            public void onResponse(Call<ResponseCurp> call, Response<ResponseCurp> response) {

                ResponseCurp curp = response.body();
                int code = response.code();

                if (response.isSuccessful() && code == 200) {
                    if (curp.response.codigo == 200) {
                        editTxtNombre.setText(curp.data.nombre);
                        editTxtApellidoP.setText(curp.data.apellidoPaterno);
                        editTxtApellidoM.setText(curp.data.apellidoMaterno);
                        editTxtFechaNacimiento.setText(curp.data.fechaNacimiento);
                        dfr.dismiss();
                        call.cancel();
                        //status = true;

                    } else {
                        dfr.dismiss();
                        SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.ERROR_TYPE);
                        sw.setTitleText("Error");
                        sw.setContentText(curp.response.mensaje);
                        sw.setConfirmText("Continuar");
                        sw.setConfirmClickListener(sweetAlertDialog -> {
                            sw.dismiss();

                        });
                        sw.show();
                        call.cancel();
                    }

                } else {
                    final String alertText = (code == 400 || code == 401) ? MISSING_TOKEN_TEXT : SERVER_ERROR_TEXT;
                    dfr.dismiss();
                    SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.ERROR_TYPE);
                    sw.setTitleText("Error");
                    sw.setContentText(alertText);
                    sw.setConfirmText("Continuar");
                    sw.setConfirmClickListener(sweetAlertDialog -> {
                        sw.dismiss();

                    });
                    sw.show();
                    call.cancel();
                }
            }

            @Override
            public void onFailure(Call<ResponseCurp> call, Throwable t) {

                call.cancel();
                //btnCalendario.setVisibility(View.VISIBLE);
                //toast.makeText(context, t.toString(), duration).show();


            }
        });

    }


    private void sendSmsToken(RequestEnvioSMS body) {
        DialogFragment dfr = LoaderTransparent.loaderTransparent("Enviando sms...");
        dfr.show(getActivity().getSupportFragmentManager(), "LoaderTransparent");
        Call<ResponseEnvioSMS> call = ApiAdapter.getApiService(newToken).enviaSMS(body);
        call.enqueue(new Callback<ResponseEnvioSMS>() {
            @Override
            public void onResponse(Call<ResponseEnvioSMS> call, Response<ResponseEnvioSMS> response) {

                if (response.code() == STATUS_OK && response.isSuccessful()) {

                    ResponseEnvioSMS rs = response.body();

                    if (rs.getResponse().codigo == STATUS_OK) {

                        cardData.setVisibility(View.GONE);
                        cardSmsAval.setVisibility(View.VISIBLE);
                        dfr.dismiss();
                    } else {
                        dfr.dismiss();
                        SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.WARNING_TYPE);
                        sw.setTitleText("Atención");
                        sw.setContentText(rs.getResponse().mensaje);
                        sw.setConfirmText("Continuar");
                        sw.setConfirmClickListener(sweetAlertDialog -> {
                            sw.dismiss();
                        });
                        sw.show();

                    }
                    call.cancel();
                }

            }

            @Override
            public void onFailure(Call<ResponseEnvioSMS> call, Throwable t) {
                dfr.dismiss();
                SweetAlertDialog sw = new SweetAlertDialog(actividad, SweetAlertDialog.WARNING_TYPE);
                sw.setTitleText("Error");
                sw.setContentText(t.toString());
                sw.setConfirmText("Ok");
                sw.setConfirmClickListener(sweetAlertDialog -> {
                    sw.dismiss();
                });
                sw.show();
                call.cancel();
            }
        });


    }

    private void muestraCalendario() {
        editTxtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new DatePickerDialog(actividad, R.style.datePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String lMes = return2digits(month + 1);
                        String lDia = return2digits(dayOfMonth);
                        editTxtFechaNacimiento.setText(year + "-" + lMes + "-" + lDia);


                    }
                }, anio, mes, dia);
                picker.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                picker.show();
            }
        });
    }

    private void abreCamara() {

        String fileName = "img_amx_";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        File storageDir = actividad.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {


            File image = File.createTempFile(fileName + timeStamp, ".jpg", storageDir);
            currentPath = image.getAbsolutePath();
            imageUri = FileProvider.getUriForFile(this.actividad, "com.amextra.fileprovider", image);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(currentPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            Bitmap img = uriToBitmap(imageUri);
            switch (tipoImgs) {
                case "f":
                    layOutFront.setVisibility(View.VISIBLE);
                    objSend.setFrontal(currentPath);
                    viewFrontBtn.setVisibility(View.VISIBLE);
                    imgFront.setVisibility(View.VISIBLE);
                    imgFront.setImageBitmap(uriToBitmap(imageUri));

                    break;
                case "b":
                    objSend.setReverso(currentPath);
                    viewBacktBtn.setVisibility(View.VISIBLE);
                    imgBack.setVisibility(View.VISIBLE);
                    imgBack.setImageBitmap(uriToBitmap(imageUri));
                    break;
                case "c":

                    objSend.setComprobanteDomicilio(currentPath);
                    imgComprobante.setVisibility(View.VISIBLE);
                    viewBtnComprobante.setVisibility(View.VISIBLE);
                    imgComprobante.setImageBitmap(uriToBitmap(imageUri));
                    break;
                default:
                    break;
            }

        }

    }

    private void saveAval() {
        if (validaInfo()) {
            sendData.sendAvalInfo(objSend);
            dismiss();
        }
    }

    private boolean validaInfo() {
        if (editTxtCurp.getText().equals("") || editTxtCurp.getText().length() != 18) {
            layOutTxtCurp.setErrorEnabled(true);
            return false;
        } else {
            layOutTxtCurp.setErrorEnabled(false);
            objSend.setCurp(editTxtCurp.getText().toString());
        }
        if (objSend.getTipoIdentificacion() != 1) {
            if (objSend.getFrontal().isEmpty() || objSend.getComprobanteDomicilio().isEmpty()) {
                SweetAlertDialog toast = new SweetAlertDialog(actividad, SweetAlertDialog.ERROR_TYPE);
                toast.setTitleText("Error");
                toast.setContentText("Por favor valide que las imagenes de la identificación y comprobante de domicilio se hayan capturado correctamente.");
                toast.setConfirmText("Ok");
                toast.setConfirmClickListener(sweetAlertDialog -> {
                    //dismiss();
                    toast.dismiss();

                });
                toast.show();
                return false;
            }
        } else {
            if (objSend.getFrontal().isEmpty() || objSend.getReverso().isEmpty() || objSend.getComprobanteDomicilio().isEmpty()) {
                new SweetAlertDialog(actividad, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Por favor valide que las imagenes de la identificación y comprobante de domicilio se hayan capturado correctamente.")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(sweetAlertDialog -> {
                            dismiss();

                        })
                        .show();
                return false;


            }
        }

        if (editTxtApellidoM.getText().equals("")) {
            layOuteditTxtApellidoM.setErrorEnabled(true);
            return false;
        } else {
            layOuteditTxtApellidoM.setErrorEnabled(false);
            objSend.setApellidoMaterno(editTxtApellidoM.getText().toString());
        }


        if (editTxtApellidoP.getText().equals("")) {
            layOuteditTxtApellidoP.setErrorEnabled(true);
            return false;
        } else {
            layOuteditTxtApellidoP.setErrorEnabled(false);
            objSend.setApellidoPaterno(editTxtApellidoP.getText().toString());
        }


        if (editTxtNombre.getText().equals("")) {
            layOuteditTxtNombre.setErrorEnabled(true);
            return false;
        } else {
            layOuteditTxtNombre.setErrorEnabled(false);
            objSend.setNombre(editTxtNombre.getText().toString());
        }


        if (editTxtFechaNacimiento.getText().equals("")) {
            layOutTeditTxtFechaNacimiento.setErrorEnabled(true);
            return false;
        } else {
            layOutTeditTxtFechaNacimiento.setErrorEnabled(false);
            objSend.setFechaNacimiento(editTxtFechaNacimiento.getText().toString());
        }

        if (txtTelefonoEmpresa.getText().equals("") || txtTelefonoEmpresa.getText().length() != 10) {
            layOutTelefono.setErrorEnabled(true);
            return false;
        } else {
            layOutTelefono
                    .setErrorEnabled(false);
            objSend.setTelefono(txtTelefonoEmpresa.getText().toString());
        }


        return true;
    }

    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    actividad.getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void showHideBackId(long id) {

        if (id != 1) {
            layOutBakc.setVisibility(View.GONE);
            txtFrente.setText("Identificación");


        } else {
            txtFrente.setText("Frente");
            layOutBakc.setVisibility(View.VISIBLE);
        }
    }


    private void doExpand(LinearLayout layOut, ImageButton btn, ImageView imgV) {
        layOut.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int view = imgV.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
                imgV.setVisibility(view);

            }
        });

    }


    private void drawDataAval(Aval aval) {
        editTxtCurp.setText(aval.getCurp());
        txtTelefonoEmpresa.setText(aval.getTelefono());
        txtTitle.setText("Edición de aval");

        showHideBackId(aval.getTipoIdentificacion());
        RequestConsultaCurp requestConsultaCurp = new RequestConsultaCurp();
        requestConsultaCurp.setData(new DataCurp());
        requestConsultaCurp.getData().setCurp(aval.getCurp());
        requestConsultaCurp.getData().setPosibleCliente(false);
        consultaCurp(requestConsultaCurp);
        if (aval.getTipoIdentificacion() != 1) {

            imgFront.setImageBitmap(pathToBitmap(aval.getFrontal()));
            imgComprobante.setImageBitmap(pathToBitmap(aval.getComprobanteDomicilio()));

            viewFrontBtn.setVisibility(View.VISIBLE);
            imgFront.setVisibility(View.VISIBLE);
            imgComprobante.setVisibility(View.VISIBLE);
            viewBtnComprobante.setVisibility(View.VISIBLE);


        } else {
            imgFront.setImageBitmap(pathToBitmap(aval.getFrontal()));
            imgComprobante.setImageBitmap(pathToBitmap(aval.getComprobanteDomicilio()));
            imgBack.setImageBitmap(pathToBitmap(aval.getReverso()));


            viewFrontBtn.setVisibility(View.VISIBLE);
            imgFront.setVisibility(View.VISIBLE);
            imgComprobante.setVisibility(View.VISIBLE);
            viewBtnComprobante.setVisibility(View.VISIBLE);
            imgComprobante.setVisibility(View.VISIBLE);
            viewBtnComprobante.setVisibility(View.VISIBLE);
        }

    }


    private Bitmap pathToBitmap(String path) {
        File imgFile = new File(path);
        Bitmap bitmap = null;
        if (imgFile.exists()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap = BitmapFactory.decodeFile(path);

        }
        return bitmap;


    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        }

        if (context instanceof SendData) {
            this.sendData = (SendData) context;

        }
    }

    public interface SendData {

        void sendAvalInfo(Aval aval);

    }
}
