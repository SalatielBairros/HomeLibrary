package com.dev.salatiel.homelibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.UserNotAuthenticatedException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.dev.salatiel.homelibrary.Controllers.UsuarioController;
import com.dev.salatiel.homelibrary.model.UsuarioModel;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    UsuarioController usuarioController;

    EditText txtEmail;
    EditText txtSenha;
    Button btnCadastrar;
    Button btnEntrar;

    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.edtEmail);
        txtSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnEntrar = findViewById(R.id.btnEntrar);

        usuarioController = new UsuarioController(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                try {
                    usuarioController.login(email, senha);
                    in = new Intent(LoginActivity.this, ListActivity.class);
                    startActivity(in);
                } catch (UserNotAuthenticatedException e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG);
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

