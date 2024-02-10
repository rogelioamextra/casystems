package com.amextra.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amextra.amextra.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuCredito#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MenuCredito extends Fragment {
    Activity actividad;
    LinearLayout btnRegresar;
    public static MenuCredito newInstance(String param1, String param2) {
        MenuCredito fragment = new MenuCredito();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public MenuCredito() {
        // Required empty public constructor
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        }


    }

            @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_menu_credito, container, false);
        btnRegresar = vista.findViewById(R.id.btnRegresar);
        atras();

        return vista;

    }

    private void atras() {
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}