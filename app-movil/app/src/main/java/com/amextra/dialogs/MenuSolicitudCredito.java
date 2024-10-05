package com.amextra.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.amextra.SolicitudCredito.AvalesPrincipal;
import com.amextra.SolicitudCredito.DatosAdicionales;
import com.amextra.SolicitudCredito.Egresos;
import com.amextra.SolicitudCredito.Patrimonios;
import com.amextra.amextra.R;
import com.amextra.io.Request.RequestSolicitudCredito;
import com.amextra.io.Response.InfoUSer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuSolicitudCredito#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuSolicitudCredito extends Fragment {
    MenuSolicitudCredito.TransfiereDatos transfiereDatos;
    RadioButton radioIngresos, radioEgresos, radioPatrimonios, radioAvales;

    String nombreStatus = "esAlta";
    boolean esAltaG;
    Activity actividad;
    String nombreTit = "Titulo";
    String titulo = "";
    int numero;
    RequestSolicitudCredito requestSolicitudCredito = new RequestSolicitudCredito();
    String N_REQ_SOL_CRED = "REQSOLCRED";
    String INFO_USER = "infoLogIn";
    InfoUSer responseLogIn = new InfoUSer();
    final String CURP_CLI = "CURP_CLI";
    String curpCliente = "";

    public MenuSolicitudCredito() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MenuSolicitudCredito newInstance(String param1, String param2) {
        MenuSolicitudCredito fragment = new MenuSolicitudCredito();
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


    @SuppressLint("ClickableViewAccessibility")
    private void cambiarActividad(RadioButton radio, int pos) {

        radio.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                radio.setChecked(true);
                boolean isChecked = radio.isChecked();
                if (isChecked) {
                    switch (pos) {
                        case 0:
                        case 3:

                            Intent in = new Intent(getActivity(), DatosAdicionales.class);
                            enviaData(in);
                            break;
                        case 1:
                            Intent inten = new Intent(getActivity(), Egresos.class);
                            enviaData(inten);
                            break;
                        case 2:
                            Intent inte = new Intent(getActivity(), Patrimonios.class);
                            enviaData(inte);
                            break;
                        case 5:
                            Intent avales = new Intent(getActivity(), AvalesPrincipal.class);
                            enviaData(avales);
                            break;
                        default:
                            break;
                    }
                }


                return false;
            }
        });

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        }
        if (context instanceof MenuSolicitudCredito.TransfiereDatos) {
            this.transfiereDatos = (MenuSolicitudCredito.TransfiereDatos) context;

        }
    }

    private void apartado(int itm) {
        switch (itm) {
            case 0:
            case 1:
                radioEgresos.setChecked(true);
                break;
            case 2:
                radioPatrimonios.setChecked(true);
                break;
            case 3:
                radioIngresos.setChecked(true);
                break;
            case 5:
                radioAvales.setChecked(true);
                break;
            default:
                break;
        }
    }

    public void enviaData(Intent intent) {
        transfiereDatos.transfiereinfocredito();
        Bundle bund = new Bundle();
        bund.putBoolean(nombreStatus, esAltaG);
        bund.putSerializable(N_REQ_SOL_CRED, requestSolicitudCredito);
        bund.putString(nombreTit, titulo);
        bund.putSerializable(INFO_USER, responseLogIn);
        bund.putString(CURP_CLI, curpCliente);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtras(bund);
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_solicitud_credito, container, false);
        Bundle bund = getArguments();
        radioIngresos = v.findViewById(R.id.radioIngresos);
        radioEgresos = v.findViewById(R.id.radioEgresos);
        radioPatrimonios = v.findViewById(R.id.radioPatrimonios);
        radioAvales = v.findViewById(R.id.radioAvales);

        numero = bund.getInt("itm");
        titulo = bund.getString(nombreTit);
        responseLogIn = (InfoUSer) bund.getSerializable(INFO_USER);
        curpCliente = bund.getString(CURP_CLI);
        requestSolicitudCredito = (RequestSolicitudCredito) bund.getSerializable(N_REQ_SOL_CRED);


        cambiarActividad(radioIngresos, 0);
        cambiarActividad(radioEgresos, 1);
        cambiarActividad(radioPatrimonios, 2);
        cambiarActividad(radioAvales, 5);
        apartado(numero);
        return v;

    }


    public interface TransfiereDatos {
        void transfiereinfocredito();
    }
}