package com.amextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amextra.amextra.R;


public class MenuScreen extends AppCompatActivity {
    ImageView closeWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        closeWindow = findViewById(R.id.closeWindow);
        goMenuHomeScreen();
    }

    private void goMenuHomeScreen() {
        closeWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent screenMenuHome = new Intent(MenuScreen.this, MenuHomeScreen.class);
                startActivity(screenMenuHome);
            }
        });
    }
}