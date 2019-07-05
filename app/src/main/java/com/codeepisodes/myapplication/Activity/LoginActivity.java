package com.codeepisodes.myapplication.Activity;

import android.Manifest;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.codeepisodes.myapplication.Helper.UserPreferences;
import com.codeepisodes.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btn_login, btn_register, btn_forgot_password, btn_privacy_policy;
    private TextInputEditText edt_email, edt_password;
    private String email, password;
    private AlertDialog alertDialog;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        initComponents ( );
        alertDialogHelper ( );
        validateForm ( );

        userPreferences = new UserPreferences ( getApplicationContext () );

        email = userPreferences.restoreEmailLogin ();
        if ( !email.equals ( "" ) ){
            edt_email.setText ( email );
        }

        btn_login.setOnClickListener ( v -> {

            email = edt_email.getText ().toString ();

            if ( !email.equals ( "" ) ){
                userPreferences.saveEmailLogin ( email );
            }

            Intent intent = new Intent ( this , MainActivity.class );
            startActivity ( intent );
        } );
    }

    private void initComponents() {
        btn_login = findViewById ( R.id.btn_login );
        btn_register = findViewById ( R.id.btn_register );
        btn_forgot_password = findViewById ( R.id.btn_forgot_password );
        btn_privacy_policy = findViewById ( R.id.btn_privacy_policy );
        edt_email = findViewById ( R.id.edt_email );
        edt_password = findViewById ( R.id.edt_password );
    }

    private void validateForm() {
        edt_email.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence , int i , int i1 , int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence , int i , int i1 , int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ( !Patterns.EMAIL_ADDRESS.matcher ( editable ).matches ( ) )
                    edt_email.setError ( "Forneça um e-mail válido." );
            }
        } );
    }

    private void alertDialogHelper() {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this  );
        builder.setMessage ( "Este é um template para ajudar no desenvolvimento. Por favor, simplemente clique em LOGIN para acessar." );
        builder.setPositiveButton ( "OK" , (dialogInterface , i) -> {
            Toast.makeText ( LoginActivity.this , "Você clicou em OK" , Toast.LENGTH_SHORT ).show ( );
        } );
        builder.setNegativeButton ( "CANCELAR" , (dialogInterface , i) -> {
            Toast.makeText ( LoginActivity.this , "Você clicou em CANCELAR" , Toast.LENGTH_SHORT ).show ( );
        } );

        alertDialog = builder.create ( );
        alertDialog.show ();
    }
}
