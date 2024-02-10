package com.amextra.dialogs;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amextra.Beans.ImagesIdentificacion;
import com.amextra.amextra.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class IneScanner extends DialogFragment {
    ImageButton closeDialog;
    Activity actividad;
    TransfiereImagenes transfiereDatos;
    ImagesIdentificacion imagesIdentificacion = new ImagesIdentificacion();
    RadioButton radio1, radio2,radio3;
    ImageView imgAnverso, imgSelphie,imgReverso, btnGuardar;
    ImageButton btnCamaraF, btnCamaraT,btnSelphie;
    TextView txtFrente, txtReverso,txtSelphie;

    long idIdentificacion = 0;
    int pos = 1;

    int quality = 0;
    String currentPath;
    Uri imageUri;

    String curp;
    public static IneScanner ineScanner(ImagesIdentificacion imagesIdentificacion, long tipoId,String curp) {

        IneScanner ineScanner = new IneScanner();
        Bundle bundle = new Bundle();
        bundle.putSerializable("id", imagesIdentificacion);
        bundle.putLong("tipoId", tipoId);
        bundle.putString("curp", curp);
        ineScanner.setArguments(bundle);
        return ineScanner;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearTomarFoto();
    }

    private AlertDialog crearTomarFoto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        imagesIdentificacion = (ImagesIdentificacion) getArguments().getSerializable("id");
        idIdentificacion = getArguments().getLong("tipoId");
        curp = getArguments().getString("curp");
        View v = layoutInflater.inflate(R.layout.fragment_ine_scanner, null);
        radio1 = v.findViewById(R.id.radio1);
        radio2 = v.findViewById(R.id.radio2);
        radio3 = v.findViewById(R.id.radio3);
        imgAnverso = v.findViewById(R.id.imgAnverso);
        imgReverso = v.findViewById(R.id.imgReverso);
        imgSelphie = v.findViewById(R.id.imgSelphie);
        closeDialog = v.findViewById(R.id.closeDialog);
        btnGuardar = v.findViewById(R.id.btnGuardar);
        btnCamaraF = v.findViewById(R.id.btnCamaraF);
        btnCamaraT = v.findViewById(R.id.btnCamaraT);
        txtFrente = v.findViewById(R.id.txtFrente);
        txtReverso = v.findViewById(R.id.txtReverso);
        txtSelphie = v.findViewById(R.id.txtSelphie);
        btnSelphie = v.findViewById(R.id.btnSelphie);

        imgAnverso.setVisibility(View.GONE);
        imgReverso.setVisibility(View.GONE);
        imgSelphie.setVisibility(View.GONE);

        txtFrente.setVisibility(View.GONE);
        txtReverso.setVisibility(View.GONE);
        txtSelphie.setVisibility(View.GONE);

        btnCamaraF.setVisibility(View.GONE);
        btnCamaraT.setVisibility(View.GONE);
        btnSelphie.setVisibility(View.GONE);
        imagesIdentificacion.setRostro("");
        imagesIdentificacion.setAnverso("");
        imagesIdentificacion.setReverso("");
        if(idIdentificacion==1){
            radio2.setVisibility(View.VISIBLE);
            radio3.setVisibility(View.VISIBLE);
            radio1.setChecked(true);
            btnCamaraF.setVisibility(View.VISIBLE);
            imgReverso.setVisibility(View.VISIBLE);
            txtFrente.setVisibility(View.VISIBLE);

        }else{
            radio2.setVisibility(View.GONE);
            radio3.setVisibility(View.GONE);
            radio1.setChecked(true);
            btnCamaraF.setVisibility(View.VISIBLE);
            imgReverso.setVisibility(View.VISIBLE);
            txtFrente.setText("Imagen de la identificacion");
            txtFrente.setVisibility(View.VISIBLE);
        }


        btnCamaraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = 0;
                imagesIdentificacion.setAnverso("");
                quality = 20;
                abreCamara();
            }
        });
        btnCamaraT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = 1;
                imagesIdentificacion.setReverso("");
                quality = 20;
                abreCamara();
            }
        });

        btnSelphie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = 2;
                imagesIdentificacion.setRostro("");
                quality = 40;
                abreCamara();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(idIdentificacion ==1){
                        if(!imagesIdentificacion.anverso.equals("") &&
                                !imagesIdentificacion.reverso.equals("") &&
                                !imagesIdentificacion.rostro.equals("")){
                            transfiereDatos.transfiereBase64(imagesIdentificacion);
                            dismiss();
                        }else{
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setContentText("Se deben de capturar todas las fotos")
                                    .show();
                        }
                    }else{
                        if(!imagesIdentificacion.anverso.equals("")){
                            transfiereDatos.transfiereBase64(imagesIdentificacion);
                            dismiss();
                        }else{
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setContentText("Se deben de capturar la imagen del anverso de la identificacion")
                                    .show();
                        }
                    }

                }catch (Exception e){
                    Log.d("error", "onClick: err"+e.getMessage());
                }

            }
        });
        eventBotones();
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setView(v);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }




    private void clickRadio1() {

        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    txtFrente.setVisibility(View.VISIBLE);
                    imgAnverso.setVisibility(View.VISIBLE);
                    btnCamaraF.setVisibility(View.VISIBLE);
                    txtSelphie.setVisibility(View.GONE);
                    imgSelphie.setVisibility(View.GONE);
                    btnSelphie.setVisibility(View.GONE);
                    btnCamaraT.setVisibility(View.GONE);
                    imgReverso.setVisibility(View.GONE);
                    txtReverso.setVisibility(View.GONE);
                }
            }
        });
    }

    private Bitmap base64toBm(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
    private void clickRadio2() {
        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnCamaraT.setVisibility(View.VISIBLE);
                    imgReverso.setVisibility(View.VISIBLE);
                    txtReverso.setVisibility(View.VISIBLE);


                    txtFrente.setVisibility(View.GONE);
                    imgAnverso.setVisibility(View.GONE);
                    btnCamaraF.setVisibility(View.GONE);

                    txtSelphie.setVisibility(View.GONE);
                    imgSelphie.setVisibility(View.GONE);
                    btnSelphie.setVisibility(View.GONE);


                    btnGuardar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void clickRadio3() {
        radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtSelphie.setVisibility(View.VISIBLE);
                    imgSelphie.setVisibility(View.VISIBLE);
                    btnSelphie.setVisibility(View.VISIBLE);


                    txtFrente.setVisibility(View.GONE);
                    imgAnverso.setVisibility(View.GONE);
                    btnCamaraF.setVisibility(View.GONE);

                    btnCamaraT.setVisibility(View.GONE);
                    imgReverso.setVisibility(View.GONE);
                    txtReverso.setVisibility(View.GONE);

                    btnGuardar.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void eventBotones() {
        closeDialog.setOnClickListener(v -> dismiss());
        clickRadio1();
        clickRadio2();
        clickRadio2();
        clickRadio3();

    }


    private void abreCamara() {

        String fileName = "img_amx_";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        File storageDir = actividad.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {


            File image = File.createTempFile(fileName + timeStamp, ".jpg", storageDir);
            currentPath = image.getAbsolutePath();
            imageUri  = FileProvider.getUriForFile(this.actividad, "com.amextra.fileprovider", image);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(currentPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            Bitmap img = uriToBitmap(imageUri);
            if (idIdentificacion == 1) {
                if (pos == 0) {
                    imgAnverso.setImageBitmap(img);
                    imgSelphie.setVisibility(View.GONE);
                    imgReverso.setVisibility(View.GONE);
                    imgAnverso.setVisibility(View.VISIBLE);
                    imagesIdentificacion.setAnverso(currentPath);
                } else if(pos == 1) {
                    imgReverso.setImageBitmap(img);
                    imgSelphie.setVisibility(View.GONE);
                    imgReverso.setVisibility(View.VISIBLE);
                    imgAnverso.setVisibility(View.GONE);
                    imagesIdentificacion.setReverso(currentPath);

                }else{
                    imgSelphie.setImageBitmap(img);
                    imgSelphie.setVisibility(View.VISIBLE);
                    imgReverso.setVisibility(View.GONE);
                    imgAnverso.setVisibility(View.GONE);
                    imagesIdentificacion.setRostro(currentPath);
                }
            }else{
                imgAnverso.setImageBitmap(img);
                imgSelphie.setVisibility(View.GONE);
                imgReverso.setVisibility(View.GONE);
                imgAnverso.setVisibility(View.VISIBLE);
                imagesIdentificacion.setAnverso(currentPath);
                imagesIdentificacion.setReverso("");
                imagesIdentificacion.setRostro("");
            }

        }

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
        return  null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;

        }

        if (context instanceof TransfiereImagenes) {
            this.transfiereDatos = (TransfiereImagenes) context;

        }
    }


    public interface TransfiereImagenes {
        public void transfiereBase64(ImagesIdentificacion imgInfo);
    }
}