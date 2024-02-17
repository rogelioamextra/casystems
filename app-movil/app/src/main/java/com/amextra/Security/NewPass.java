package com.amextra.Security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amextra.MainActivity;
import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataActualizaPass;
import com.amextra.io.Request.RequestActualizaPass;
import com.amextra.io.Response.ResponseActualizaPass;
import com.amextra.utils.UtilGenerico;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPass extends AppCompatActivity {
    Button btnEnviaConfirmacion;
    EditText etCodigo;
    EditText etPassword;
    EditText etPasswordConfirm;
    TextView btnGoToInicio;
    private String strPass1;

    private final String MSG_VAL_CODIGO = "Por favor ingresa el código de 8 dígitos " +
            "enviado por correo electrónico";
    private final String MSG_VAL_PASS = "Por favor ingresa una contraséña que contenga:" +
            "\n- Al menos una letra Mayúscula " +
            "\n- Al menos una letra Minúscula " +
            "\n- Al menos un número" +
            "\n- Al menos un simbolo" +
            "\n- Al menos 8 caracteres en total";
    private final String MSG_VAL_PASS_EQ = "Las contraseñas no coinciden";
    private final String MSG_VAL_CODIGO_EQ = "El código de confirmación es incorrecto, " +
            "verifica en tu correo electrónico, si el problema persiste, vuelve a iniciar " +
            "el proceso de recuperación de contraseña";
    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Procesando\nPor favor espere...");
    private String idUsuario;
    private String email;
    private String codigoRecuperacion;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        btnEnviaConfirmacion = findViewById(R.id.btnEnviaConfirmacion);
        etCodigo = findViewById(R.id.etCodigo);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnGoToInicio = findViewById(R.id.btnGoToInicio);

        Bundle receptor = getIntent().getExtras();

        if (Objects.nonNull(receptor)) {
            idUsuario = receptor.getString("idUsuario", "");
            email = receptor.getString("email", "");
            token = receptor.getString("token", token);
            codigoRecuperacion = receptor.getString("codigoRecuperaPass", "");
        }

        goToUpdatePassword();

        goToInicio();
    }

    private void goToInicio() {
        btnGoToInicio.setOnClickListener(v -> {
            Intent inicioIntent = new Intent(NewPass.this, MainActivity.class);
            finish();
            startActivity(inicioIntent);
        });
    }

    private void goToUpdatePassword() {
        btnEnviaConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(esInfoValida()) {
                    dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");

                    RequestActualizaPass request = new RequestActualizaPass();
                    request.setData(new DataActualizaPass());
                    request.getData().setIdUsuario(idUsuario);
                    request.getData().setEmail(email);
                    request.getData().setNewPass(strPass1);

                    Call<ResponseActualizaPass> call = ApiAdapter.getApiService(token).postActualizaPass(request);
                    call.enqueue(new Callback<ResponseActualizaPass>() {
                        @Override
                        public void onResponse(Call<ResponseActualizaPass> call, Response<ResponseActualizaPass> response) {

                            int code = response.code();

                            if (response.isSuccessful() && code == 200) {

                                ResponseActualizaPass responseRp = response.body();

                                if (responseRp.getResponse().codigo == 200) {

                                    dialogFragment.dismiss();

                                    new SweetAlertDialog(NewPass.this,SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Recuperación de contraseña")
                                            .setContentText(responseRp.getResponse().mensaje)
                                            .setConfirmText("Continuar")
                                            .setConfirmClickListener(sweetAlertDialog -> {
                                                finish();
                                                Intent goToLoginIntent = new Intent(NewPass.this, MainActivity.class);
                                                goToLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(goToLoginIntent);
                                            })
                                            .show();
                                }

                                else {
                                    dialogFragment.dismiss();
                                    new SweetAlertDialog(NewPass.this,SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Ocurrió un error")
                                            .setContentText("Por favor vuelve a intentarlo")
                                            .show();
                                }

                            }

                            else {
                                dialogFragment.dismiss();
                                new SweetAlertDialog(NewPass.this,SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Ocurrió un error")
                                        .setContentText("Por favor vuelve a intentarlo")
                                        .show();
                            }



                        }

                        @Override
                        public void onFailure(Call<ResponseActualizaPass> call, Throwable t) {
                            dialogFragment.dismiss();
                            new SweetAlertDialog(NewPass.this,SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error de comunicación")
                                    .setContentText("Favor de contactar a su administrador de sistemas")
                                    .show();
                        }
                    });

                }

            }
        });
    }

    private boolean esInfoValida() {

        String strCodigo = etCodigo.getText().toString().trim();
        strPass1 = etPassword.getText().toString().trim();
        String strPass2 = etPasswordConfirm.getText().toString().trim();

        if (strCodigo.length() != 8) {
            new SweetAlertDialog(NewPass.this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText(MSG_VAL_CODIGO)
                    .show();
            etCodigo.setError(MSG_VAL_CODIGO);

            return false;
        }

        else if (!UtilGenerico.validatePassword(strPass1)) {
            new SweetAlertDialog(NewPass.this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText(MSG_VAL_PASS)
                    .show();
            etPassword.setError(MSG_VAL_PASS);

            return false;
        }

        else if (!strPass1.equals(strPass2)) {
            new SweetAlertDialog(NewPass.this,SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Error")
                    .setContentText(MSG_VAL_PASS_EQ)
                    .show();
            etPassword.setError(MSG_VAL_PASS_EQ);

            return false;
        }

        else if (!codigoRecuperacion.equals(strCodigo)) {
            new SweetAlertDialog(NewPass.this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText(MSG_VAL_CODIGO_EQ)
                    .show();
            etPassword.setError(MSG_VAL_CODIGO_EQ);

            return false;
        }
        
        return true;
    }
}