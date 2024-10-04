package com.amextra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amextra.AltaEdicionCliente.DatosPersonalesClientes;
import com.amextra.ConsultaInfoCliente.ConsultaClientes;
import com.amextra.SolicitudCredito.SolicitudDeCredito;
import com.amextra.agenda.Agenda_clientes;
import com.amextra.amextra.R;
import com.amextra.cartera.ClientesAsesor;
import com.amextra.io.Request.MenuOption;
import com.amextra.io.Response.Geolocalizacion;
import com.amextra.io.Response.InfoUSer;
import com.amextra.utils.ListaMenuOpcionesAdapter;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MenuHomeScreen extends AppCompatActivity implements ListaMenuOpcionesAdapter.Listener {


    TextView labelSaludoName;

    Geolocalizacion geolocalizacion = new Geolocalizacion();
    String nombreStatus = "esAlta";
    String nombreTit = "Titulo";
    InfoUSer responseLogIn = new InfoUSer();
    String INFO_USER = "infoLogIn";

    ArrayList<MenuOption> options = new ArrayList<>();


    ListView listViewOpciones;

    LinearLayout exit;
    private ListaMenuOpcionesAdapter mennu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loadOptionsMenu();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home_screen);
        String nombre = "";
        Bundle receptor = getIntent().getExtras();

        labelSaludoName = findViewById(R.id.labelSaludoName);
        exit = findViewById(R.id.exit);
        listViewOpciones = findViewById(R.id.listViewOpciones);

        if (receptor != null) {
            geolocalizacion = (Geolocalizacion) receptor.getSerializable("geo");
            responseLogIn = (InfoUSer) receptor.getSerializable("infoLogIn");
            nombre = responseLogIn.nombreUsuario;
        }


        cerrarSesion();
        labelSaludoName.setText(nombre);
        mennu = new ListaMenuOpcionesAdapter(this, R.layout.custum_option_menu_row, options);
        listViewOpciones.setAdapter(mennu);

    }


    private void loadOptionsMenu() {

        String[] labels = new String[]{"Mis clientes", "Solicitud de crédito", "Avisos", "Cliente Nuevo", "Cartera", "Agenda"};
        String[] main = new String[]{"client", "credit", "advise", "new", "wallet", "calendar"};
        String[] icons = new String[]{"ic_person_pin_24", "ic_credit_score_24", "ic_security_update_warning_24", "ic_person_add_alt_24", "ic_wallet_24", "ic_calendar_today_24"};
        int index = 0;
        for (String label : labels) {
            MenuOption me = new MenuOption();
            me.setLabel(label);
            me.setOptionId(main[index]);
            me.setIcon(icons[index]);
            index++;
            options.add(me);

        }

    }

    private void cerrarSesion() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog = new SweetAlertDialog(MenuHomeScreen.this, SweetAlertDialog.WARNING_TYPE);
                dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        finish();
                        Intent login = new Intent(MenuHomeScreen.this, MainActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(login);
                    }
                });
                dialog.setTitleText("Abandonara la sesión");
                dialog.setContentText("¿Desea cerrar la sesión");
                dialog.setConfirmText("Continuar");
                dialog.setCancelText("Cancelar");
                dialog.show();

            }
        });
    }


    @Override
    public void generaRegistro(long id, String label) {
        //        String[] main = new String[]{"client", "credit", "advise", "new", "wallet", "calendar"};
        Intent intent = null;
        Bundle sender = new Bundle();
        switch (label) {

            case "client":// consulta de clientes
                intent = new Intent(MenuHomeScreen.this, ConsultaClientes.class);
                sender.putBoolean(nombreStatus, false);
                sender.putString(nombreTit, "Mis Clientes");
                break;
            case "credit":// solicitud de credito
                intent = new Intent(MenuHomeScreen.this, SolicitudDeCredito.class);
                sender.putBoolean(nombreStatus, true);
                sender.putString(nombreTit, "Solicitud de Crédito");
                break;
            case "advise": //avisos
                intent = new Intent(MenuHomeScreen.this, AvisosAsesor.class);
                sender.putBoolean(nombreStatus, false);
                sender.putString(nombreTit, "Avisos");
                break;
            case "new"://alta de cliente
                intent = new Intent(MenuHomeScreen.this, DatosPersonalesClientes.class);
                sender.putBoolean(nombreStatus, true);
                sender.putString(nombreTit, "Alta de nuevo cliente");
                sender.putSerializable("geo", geolocalizacion);
                break;
            case "wallet": //cartera
                intent = new Intent(MenuHomeScreen.this, ClientesAsesor.class);
                sender.putBoolean(nombreStatus, false);
                sender.putString(nombreTit, "Clientes por asesor");
                break;
            case "calendar"://agenda
                intent = new Intent(MenuHomeScreen.this, Agenda_clientes.class);
                sender.putBoolean(nombreStatus, true);
                sender.putString(nombreTit, "Agenda Clientes");
                sender.putSerializable("geo", geolocalizacion);
                break;
            default:
                break;
        }

        sender.putSerializable(INFO_USER, responseLogIn);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtras(sender);
        startActivity(intent);

    }
}