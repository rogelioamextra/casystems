package com.amextra.Security;

import static com.amextra.utils.Constants.NO_TOKEN_VALUE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amextra.amextra.R;
import com.amextra.dialogs.LoaderTransparent;
import com.amextra.io.ApiAdapter;
import com.amextra.io.Request.DataExisteCorreo;
import com.amextra.io.Request.DataRecuperaPass;
import com.amextra.io.Request.RequestExisteCorreo;
import com.amextra.io.Request.RequestRecuperaPass;
import com.amextra.io.Response.ResponseExisteCorreo;
import com.amextra.io.Response.ResponseRecuperaPass;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPass extends AppCompatActivity implements Callback<ResponseExisteCorreo> {

    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Procesando\nPor favor espere...");
    Button btnEnviaResetPassword;
    EditText textInputEmail;
    private String idUsuario;
    private String email;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        btnEnviaResetPassword = findViewById(R.id.btnEnviaResetPassword);
        textInputEmail = findViewById(R.id.texInputEmail);

        goToNewPass();

    }

    private void goToNewPass() {
        btnEnviaResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidEmail()) {
                    new SweetAlertDialog(ResetPass.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Por favor ingresa un correo electrónico válido")
                            .show();

                    textInputEmail.setText("");

                    return;
                }

                existeCorreo(textInputEmail.getText().toString().trim());

            }
        });
    }

    private boolean isValidEmail() {
        String email = textInputEmail.getText().toString().trim();
        return (Objects.nonNull(email)
                && Patterns
                        .EMAIL_ADDRESS
                        .matcher(email)
                        .matches()
                );
    }

    private void existeCorreo(String email) {
        RequestExisteCorreo requestExisteCorreo = new RequestExisteCorreo();
        requestExisteCorreo.setData(new DataExisteCorreo());

        dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");

        requestExisteCorreo.getData().setEmail(email);

        Call<ResponseExisteCorreo> callExisteCorreo = ApiAdapter.getApiService(NO_TOKEN_VALUE).postExisteCorreon(requestExisteCorreo);
        callExisteCorreo.enqueue(this);

    }

    @Override
    public void onResponse(Call<ResponseExisteCorreo> call, Response<ResponseExisteCorreo> response) {

        int code = response.code();

        if (response.isSuccessful() && code == 200) {

            ResponseExisteCorreo responseEc = response.body();

            if (responseEc.getResponse().codigo == 404) {
                dialogFragment.dismiss();

                new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Advertencia")
                        .setContentText(responseEc.getResponse().mensaje)
                        .show();
            }

            else if (responseEc.getResponse().codigo == 200) {

                idUsuario = responseEc.getData().getIdUsuario();
                email = responseEc.getData().getEmail();
                token = responseEc.getData().getToken();

                recuperaPass(
                        responseEc.getData().getEmail(),
                        responseEc.getData().getNombreUsuario()
                );

            }

        }

        else {
            dialogFragment.dismiss();
            new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error de comunicación")
                    .setContentText("Favor de contactar a su administrador de sistemas")
                    .show();
        }

    }

    @Override
    public void onFailure(Call<ResponseExisteCorreo> call, Throwable t) {

        dialogFragment.dismiss();
        new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error de comunicación")
                .setContentText("Favor de contactar a su administrador de sistemas")
                .show();

    }

    private void recuperaPass(String email, String nombreUsuario) {
        RequestRecuperaPass requestRecuperaPass = new RequestRecuperaPass();
        requestRecuperaPass.setData(new DataRecuperaPass());
        requestRecuperaPass.getData().setEmail(email);
        requestRecuperaPass.getData().setNombreUsuario(nombreUsuario);

        Call<ResponseRecuperaPass> call = ApiAdapter.getApiService(token).postRecuperaPass(requestRecuperaPass);
        call.enqueue(new Callback<ResponseRecuperaPass>() {

            @Override
            public void onResponse(Call<ResponseRecuperaPass> call, Response<ResponseRecuperaPass> response) {

                int code = response.code();

                if (response.isSuccessful() && code == 200) {

                    ResponseRecuperaPass responseRp = response.body();

                    if (responseRp.getResponse().codigo == 200) {

                        dialogFragment.dismiss();

                        new SweetAlertDialog(ResetPass.this,SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Recuperación de contraseña")
                                .setContentText(responseRp.getResponse().mensaje)
                                .setConfirmText("Continuar")
                                .setConfirmClickListener(sweetAlertDialog -> {
                                    finish();
                                    Intent resetPassScreenIntent = new Intent(ResetPass.this, NewPass.class);
                                    resetPassScreenIntent.putExtra("idUsuario", idUsuario);
                                    resetPassScreenIntent.putExtra("email", email);
                                    resetPassScreenIntent.putExtra("token", token);
                                    resetPassScreenIntent.putExtra("codigoRecuperaPass", responseRp.getData().getCodigoRecuperaPass());
                                    resetPassScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(resetPassScreenIntent);
                                })
                                .show();

                    }

                }

                else {
                    dialogFragment.dismiss();
                    new SweetAlertDialog(ResetPass.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error de comunicación")
                            .setContentText("Favor de contactar a su administrador de sistemas")
                            .show();
                }

            }

            @Override
            public void onFailure(Call<ResponseRecuperaPass> call, Throwable t) {
                dialogFragment.dismiss();
                new SweetAlertDialog(ResetPass.this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error de comunicación")
                        .setContentText("Favor de contactar a su administrador de sistemas")
                        .show();
            }
        });
    }
}