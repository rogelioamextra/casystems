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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amextra.amextra.R;

import com.amextra.io.Request.PatrimoniosCls;
import com.amextra.io.Response.Patrimonio;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManejaPatrimonio#} factory method to
 * create an instance of this fragment.
 */
public class ManejaPatrimonio extends DialogFragment {


    Activity actividad;
    private AutoCompleteTextView productosCredito;
    Button btnCancelar,saveBtn;
    ImageButton btnCamara;
    TextInputEditText txtPrecio;
    ImageView imgPatrimonio;

    EnviaInformacion enviaInformacion;

    PatrimoniosCls patrimonio = new PatrimoniosCls();
    String textoImagen ="";
    TextInputLayout layoutPartrimonio,layoutPrecio;

    String currentPath, descPat;
    Uri imageUri;

    int quality = 100;
    boolean existInfo = false;
    public static ManejaPatrimonio manejaPatrimonio(ArrayList<Patrimonio> lista, PatrimoniosCls patrimonio) {
        ManejaPatrimonio manejaPatrimonio= new ManejaPatrimonio();
        Bundle bundle = new Bundle();
        bundle.putSerializable("patrimonios",lista);
        if(patrimonio!= null){
            bundle.putSerializable("item",patrimonio);
        }
        manejaPatrimonio.setArguments(bundle);
        return manejaPatrimonio;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return muestraPatrimonios();
    }

    private AlertDialog muestraPatrimonios() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.fragment_maneja_patrimonio, null);

        productosCredito = v.findViewById(R.id.productosCredito);
        btnCancelar = v.findViewById(R.id.btnCancelar);
        saveBtn = v.findViewById(R.id.saveBtn);
        btnCamara = v.findViewById(R.id.btnCamara);
        txtPrecio = v.findViewById(R.id.txtPrecio);
        imgPatrimonio = v.findViewById(R.id.imgPatrimonio);
        layoutPartrimonio = v.findViewById(R.id.layoutPartrimonio);
        layoutPrecio = v.findViewById(R.id.layoutPrecio);


        PatrimoniosCls p = (PatrimoniosCls) getArguments().getSerializable("item");
        if(p!=null){
            mapData(p);
        }


        ArrayList<Patrimonio> listaPatrimonios = (ArrayList<Patrimonio>) getArguments().getSerializable("patrimonios");
        if(listaPatrimonios != null){
            llenaDropPatrimonios(listaPatrimonios);
        }




        AlertDialog dialog = builder.create();
        dialog.setView(v);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        cancelar();

        enviaPatrimonio();
        abreCamara();
        listenerPrecio();
        return dialog;
    }

    private void mapData(PatrimoniosCls p) {
        existInfo = true;
        txtPrecio.setText(p.precio);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        currentPath = p.imagen;
        textoImagen = currentPath;
        File imgFile = new  File(currentPath);

        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgPatrimonio.setImageBitmap(myBitmap);

        }

        patrimonio = p;

    }

    private void listenerPrecio() {
        txtPrecio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                patrimonio.setPrecio(text);
            }
        });
    }

    private void abreCamara() {
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "img_amx_"+descPat+"_";
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                File storageDir = actividad.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                try {


                    File image = File.createTempFile(fileName + timeStamp, ".jpg", storageDir);
                    currentPath = image.getAbsolutePath();
                    imageUri  = FileProvider.getUriForFile(actividad, "com.amextra.fileprovider", image);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void enviaPatrimonio() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validaMonto() && validaImagen()){
                    enviaInformacion.transfierePatrimonio(patrimonio);
                    dismiss();
                }

            }
        });
    }


    private void cancelar() {
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void llenaDropPatrimonios(ArrayList<Patrimonio> listaPatrimonios) {

        ArrayList<String> nombresPatrimonios = new ArrayList<>();
        ArrayList<String> idsPatrimonios = new ArrayList<>();
        for (Patrimonio patrimonio:listaPatrimonios) {
            nombresPatrimonios.add(patrimonio.nombre);
            idsPatrimonios.add(String.valueOf(patrimonio.idPatrimonio));
        }

        if(existInfo){
            for (Patrimonio patrimonioLis:listaPatrimonios) {
                if(patrimonio.tipoPatrimonioId.equals(patrimonioLis.idPatrimonio)){
                    productosCredito.setText(patrimonioLis.nombre);
                    break;
                }
            }

        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, nombresPatrimonios);
        productosCredito.setAdapter(spinnerArrayAdapter);
        productosCredito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                patrimonio.setTipoPatrimonioID(idsPatrimonios.get(position));
                descPat = nombresPatrimonios.get(position);
            }
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(currentPath);
            textoImagen = currentPath;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            Bitmap img = uriToBitmap(imageUri);
            imgPatrimonio.setImageBitmap(img);
            patrimonio.setImagen(currentPath);
            patrimonio.setCambioImagen(true);
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



    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        }

        if (context instanceof ManejaPatrimonio.EnviaInformacion) {
            this.enviaInformacion = (ManejaPatrimonio.EnviaInformacion) context;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maneja_patrimonio, container, false);
    }


    private boolean validaMonto(){
        boolean continua= true;

        String precio = txtPrecio.getText().toString();
        if (precio.equals("") ||  precio.equals("0")){
            continua = false;
            layoutPrecio.setError("Monto requerido");
        }else{
            layoutPrecio.setErrorEnabled(false);
        }
        if ((productosCredito.getText()).equals("")){
            continua = false;
            layoutPartrimonio.setError("Seleccione un patrimonio");
        }else{
            layoutPartrimonio.setErrorEnabled(false);
        }

        if ((textoImagen).equals("")){
            continua = false;

        }else{
        }


        return continua;
    }

    private boolean validaImagen(){
        boolean continua= true;
        if (textoImagen.equals("")){
            continua = false;

        }
        return continua;
    }

    public interface EnviaInformacion{

        public  void transfierePatrimonio( PatrimoniosCls pat);

    }

}