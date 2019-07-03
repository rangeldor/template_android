package com.codeepisodes.myapplication.Activity;

import android.content.Intent;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codeepisodes.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btn_login, btn_register, btn_forgot_password, btn_privacy_policy;
    private TextInputEditText edt_email, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        btn_login = findViewById ( R.id.btn_login );
        btn_register = findViewById ( R.id.btn_register );
        btn_forgot_password = findViewById ( R.id.btn_forgot_password );
        btn_privacy_policy = findViewById ( R.id.btn_privacy_policy );

        btn_login.setOnClickListener ( v -> {
            Intent intent = new Intent ( this, MainActivity.class );
            startActivity ( intent );
        } );
    }
}
