package com.codeepisodes.myapplication.Activity;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codeepisodes.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        btn_login = findViewById ( R.id.btn_login );
        btn_login.setOnClickListener ( v -> {
            Intent intent = new Intent ( this, MainActivity.class );
            startActivity ( intent );
        } );
    }
}
