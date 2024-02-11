package com.amextra.Security;

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

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResetPass extends AppCompatActivity {

    DialogFragment dialogFragment = LoaderTransparent.loaderTransparent("Procesando\nPor favor espere...");
    Button btnEnviaResetPassword;
    EditText textInputEmail;
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

                dialogFragment.show(getSupportFragmentManager(), "LoaderTransparent");

                /*
                Intent resetPassScreenIntent = new Intent(ResetPass.this, NewPass.class);
                startActivity(resetPassScreenIntent);
                */

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

}