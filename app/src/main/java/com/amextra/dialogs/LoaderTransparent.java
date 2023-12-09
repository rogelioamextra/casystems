package com.amextra.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amextra.amextra.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaderTransparent#} factory method to
 * create an instance of this fragment.
 */
public class LoaderTransparent extends DialogFragment {


    Activity actividad;
    TextView labelLoader;

    CircularProgressIndicator loaderCircular;

    public static LoaderTransparent loaderTransparent(String mensajeLoader) {

        LoaderTransparent loaderTransparent = new LoaderTransparent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("msg", mensajeLoader);
        loaderTransparent.setArguments(bundle);
        return loaderTransparent;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearLoader();
    }

    private AlertDialog crearLoader() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.fragment_loader_transparent, null);
        String titulo = getArguments().getString("msg");

        loaderCircular = v.findViewById(R.id.loaderCircular);
        labelLoader = v.findViewById(R.id.labelLoader);


        labelLoader.setText(titulo);


        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.dialog_animation_fade);
        dialog.setView(v);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        return dialog;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loader_transparent, container, false);
    }
}