package com.amextra.Security;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amextra.MainActivity;
import com.amextra.amextra.R;

public class NewPass extends AppCompatActivity {
    Button btnEnviaConfirmacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        btnEnviaConfirmacion = findViewById(R.id.btnEnviaConfirmacion);
        btnEnviaConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginScreenIntent = new Intent(NewPass.this, MainActivity.class);
                startActivity(loginScreenIntent);
            }
        });
    }
}