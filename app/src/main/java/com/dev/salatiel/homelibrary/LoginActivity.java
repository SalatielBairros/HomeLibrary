package com.dev.salatiel.homelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.UserNotAuthenticatedException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.dev.salatiel.homelibrary.Controllers.UsuarioController;
import com.dev.salatiel.interfaces.IAppActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements IAppActivity {

    public static final String TAG = "LoginActivity";
    UsuarioController usuarioController;

    EditText txtEmail;
    EditText txtSenha;
    Button btnCadastrar;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioController = new UsuarioController(this);
        verifyUserSession();

        txtEmail = findViewById(R.id.edtEmail);
        txtSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                try {
                    usuarioController.login(email, senha);
                    startActivity(new Intent(LoginActivity.this, ListActivity.class));
                } catch (UserNotAuthenticatedException e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    Log.e(TAG, getString(R.string.erro_realizar_login), ex);
                    Toast.makeText(LoginActivity.this, R.string.erro_realizar_login, Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroUsuarioActivity.class));
            }
        });
    }

    @Override
    public void verifyUserSession() {
        if(usuarioController.verificaLogin()){
            startActivity(new Intent(LoginActivity.this, ListActivity.class));
        }
    }
}

