package com.amextra.dialogs;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.amextra.MenuHomeScreen;
import com.amextra.amextra.R;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.InfoUSer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuHeader#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuHeader extends Fragment {

    String nombreTit = "Titulo";
    Button btnNoActionHeader;
    private ImageView cierraActividad;
    public MenuHeader() {
        // Required empty public constructor
    }

    InfoUSer responseLogIn = new InfoUSer();
    Geolocalizacion geolocalizacion = new Geolocalizacion();

    public static MenuHeader newInstance(String param1, String param2) {
        MenuHeader fragment = new MenuHeader();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_menu_header, container, false);
        btnNoActionHeader = vista.findViewById(R.id.btnNoActionHeader);
        cierraActividad = vista.findViewById(R.id.cierraActividad);
        Bundle bund = getArguments();
        String titulo = bund.getString(nombreTit);
        responseLogIn = (InfoUSer) bund.getSerializable("infoLogIn");
        geolocalizacion = (Geolocalizacion) bund.getSerializable("geo");
        btnNoActionHeader.setText(titulo);
        cierraActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sender = new Bundle();
                sender.putSerializable("infoLogIn",responseLogIn);
                sender.putSerializable("geo",geolocalizacion);
                Intent intent = new Intent(getActivity(),MenuHomeScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtras(sender);
                startActivity(intent);

            }
        });


        return vista;
    }
}