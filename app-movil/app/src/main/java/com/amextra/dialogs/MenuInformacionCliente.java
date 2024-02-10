package com.amextra.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amextra.ConsultaInfoCliente.ConsultaDireccion;
import com.amextra.ConsultaInfoCliente.ConsultaEmpleo;
import com.amextra.ConsultaInfoCliente.ConsultaPersonal;
import com.amextra.ConsultaInfoCliente.ConsultaReferencia;
import com.amextra.AltaEdicionCliente.DatosClienteLaborales;
import com.amextra.AltaEdicionCliente.DatosPersonalesClientes;
import com.amextra.AltaEdicionCliente.ReferenciasAlta;
import com.amextra.amextra.R;
import com.amextra.AltaEdicionCliente.datos_personales_address;
import com.amextra.io.Request.RequestInsertClient;
import com.amextra.io.Response.InfoUSer;
import com.amextra.io.Response.ResponseGetCliente;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuInformacionCliente} factory method to
 * create an instance of this fragment.
 */
public class MenuInformacionCliente extends Fragment {
    Activity actividad;
    RadioButton radioRefencias, radioDatosLaborales, radioDireccion, radioDatosPersonales;
    boolean esAltaG;
    String nombreStatus = "esAlta";
    RadioGroup grupoOpciones;
    MenuInformacionCliente.TransfiereDatos transfiereDatos;
    int numero;
    String nombreTit = "Titulo";
    String titulo = "";
    String clienteInfo = "INFO_CLIENT";
    ResponseGetCliente responseGetCliente = new ResponseGetCliente();
    RequestInsertClient requestInsertClient = new RequestInsertClient();
    String REQ_ALTA_CLI = "reqAltaCliente";

    ImageView flechaRegreso;
    InfoUSer responseLogIn = new InfoUSer();

    public static MenuInformacionCliente menuInformacionCliente(boolean esAlta) {
        MenuInformacionCliente menu = new MenuInformacionCliente();
        Bundle bundle = new Bundle();
        bundle.putBoolean("alta", esAlta);
        menu.setArguments(bundle);
        return menu;
    }


    private void cambiarActividad(RadioButton radio, int pos) {

        radio.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                radio.setChecked(true);
                boolean isChecked = radio.isChecked();
                if (!titulo.equals("Información del Cliente")) {
                    if (isChecked) {

                        switch (pos) {
                            case 0:
                            case 1:
                            case 2:
                                Intent intent = new Intent(getActivity(), DatosPersonalesClientes.class);
                                enviaData(intent);
                                break;
                            case 3:
                            case 4:

                                Intent inten = new Intent(getActivity(), datos_personales_address.class);
                                enviaData(inten);
                                break;
                            case 5:
                            case 6:

                                Intent inte = new Intent(getActivity(), DatosClienteLaborales.class);
                                enviaData(inte);
                                break;
                            case 7:
                                Intent in = new Intent(getActivity(), ReferenciasAlta.class);
                                enviaData(in);
                            default:
                                break;
                        }
                    }
                } else {
                    if (isChecked) {

                        switch (pos) {
                            case 0:

                                Intent intent = new Intent(getActivity(), ConsultaPersonal.class);
                                enviaData(intent);
                                break;
                            case 1:
                                Intent inten = new Intent(getActivity(), ConsultaDireccion.class);
                                enviaData(inten);
                                break;
                            case 2:
                                Intent inte = new Intent(getActivity(), ConsultaEmpleo.class);
                                enviaData(inte);
                                break;
                            case 3:
                                Intent in = new Intent(getActivity(), ConsultaReferencia.class);
                                enviaData(in);
                            default:

                        }
                    }
                }


                return false;
            }
        });

    }


    private void intgetNum() {
    }

    private int apartado(int itm) {
        int res = 0;
        if (esAltaG || titulo.equals("Editar Datos del Cliente")) {
            switch (itm) {
                case 0:
                case 1:
                case 2:
                    res = 0;
                    radioDatosPersonales.setChecked(true);
                    break;
                case 3:
                case 4:
                    res = 1;
                    radioDireccion.setChecked(true);
                    break;
                case 5:
                case 6:
                    res = 2;
                    radioDatosLaborales.setChecked(true);
                    break;
                case 7:
                    res = 3;
                    radioRefencias.setChecked(true);
                    break;
                default:
                    break;
            }
        }else{

                switch (itm) {
                    case 0:
                        radioDatosPersonales.setChecked(true);
                        break;
                    case 1:
                        radioDireccion.setChecked(true);
                        break;
                    case 2:
                        radioDatosLaborales.setChecked(true);
                        break;
                    case 3:
                        radioRefencias.setChecked(true);
                        break;
                    default:
                        break;
                }
                res = itm;

        }
        return res;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        }
        if (context instanceof MenuInformacionCliente.TransfiereDatos) {
            this.transfiereDatos = (MenuInformacionCliente.TransfiereDatos) context;
        }


    }

    public void enviaData(Intent intent) {
        Bundle bund = new Bundle();
        transfiereDatos.transfiereInfo(requestInsertClient);

        bund.putBoolean(nombreStatus, esAltaG);
        if (responseGetCliente != null) {
            bund.putSerializable(clienteInfo, responseGetCliente);
        }

        if(requestInsertClient != null){
            bund.putSerializable(REQ_ALTA_CLI,requestInsertClient);
        }

        bund.putSerializable("infoLogIn",responseLogIn);
        bund.putString(nombreTit, titulo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtras(bund);
        startActivity(intent);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_informacion_cliente, container, false);
        int iRef = R.id.radioRefencias;
        int iDper = R.id.radioDatosPersonales;
        int iDir = R.id.radioDireccion;
        int iDlab = R.id.radioDatosLaborales;

        radioRefencias = v.findViewById(iRef);
        radioDatosLaborales = v.findViewById(iDlab);
        radioDireccion = v.findViewById(iDir);
        radioDatosPersonales = v.findViewById(iDper);


        grupoOpciones = v.findViewById(R.id.grupoOpciones);

        Bundle bund = getArguments();
        esAltaG = bund.getBoolean(nombreStatus);
        titulo = bund.getString(nombreTit);
        numero = bund.getInt("itm");
        responseGetCliente = (ResponseGetCliente) bund.getSerializable(clienteInfo);
        responseLogIn = (InfoUSer) bund.getSerializable("infoLogIn");
        requestInsertClient = (RequestInsertClient) bund.getSerializable(REQ_ALTA_CLI);

        if (esAltaG || titulo.equals("Editar Datos del Cliente")) {
            cambiarActividad(radioDatosPersonales, 0);
            cambiarActividad(radioDireccion, 3);
            cambiarActividad(radioDatosLaborales, 5);
            cambiarActividad(radioRefencias, 7);

        } else {
            cambiarActividad(radioDatosPersonales, 0);
            cambiarActividad(radioDireccion, 1);
            cambiarActividad(radioDatosLaborales, 2);
            cambiarActividad(radioRefencias, 3);
        }
        apartado(numero);
        return v;
    }


    public interface TransfiereDatos {
        public void transfiereInfo(RequestInsertClient req);
    }
}