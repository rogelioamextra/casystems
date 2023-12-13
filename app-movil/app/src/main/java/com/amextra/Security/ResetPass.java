package com.amextra.Security;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amextra.amextra.R;

public class ResetPass extends AppCompatActivity {

    Button btnEnviaResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        btnEnviaResetPassword = findViewById(R.id.btnEnviaResetPassword);
        goToNewPass();

    }

    private void goToNewPass() {
        btnEnviaResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetPassScreenIntent = new Intent(ResetPass.this, NewPass.class);
                startActivity(resetPassScreenIntent);
            }
        });
    }
}