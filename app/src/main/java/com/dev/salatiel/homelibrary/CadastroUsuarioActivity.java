package com.dev.salatiel.homelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.dev.salatiel.homelibrary.Controllers.UsuarioController;
import com.dev.salatiel.homelibrary.model.UsuarioModel;

public class CadastroUsuarioActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtEmail;
    EditText txtSenha;
    EditText txtConfirmacaoSenha;
    Button btnCadastrar;
    UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        txtNome = findViewById(R.id.edtNome);
        txtEmail = findViewById(R.id.edtEmail);
        txtSenha = findViewById(R.id.edtSenha);
        txtConfirmacaoSenha = findViewById(R.id.edtConfirmacaoSenha);
        btnCadastrar = findViewById(R.id.btnGravarUsuario);

        usuarioController = new UsuarioController(this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validate()){
                    usuarioController.insereDado(getData());
                    Toast.makeText(CadastroUsuarioActivity.this, R.string.salvo_sucesso, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private boolean Validate(){
        String message = "";
        if(txtNome.getText().toString().trim().isEmpty()
                || txtEmail.getText().toString().trim().isEmpty()
                || txtSenha.getText().toString().trim().isEmpty()
                || txtConfirmacaoSenha.getText().toString().trim().isEmpty()){
            message = getString(R.string.erro_validacao_preenchimento);
        }else if(!txtSenha.getText().toString().trim()
                .equals(txtConfirmacaoSenha.getText().toString().trim())){
            message = getString(R.string.erro_confirmacao_senha_validacao);
        }

        if(message.isEmpty()) return true;

        Toast.makeText(CadastroUsuarioActivity.this, message, Toast.LENGTH_LONG);
        return false;
    }

    private UsuarioModel getData(){
        return UsuarioModel.createNew(
                txtNome.getText().toString().trim(),
                txtSenha.getText().toString().trim(),
                txtEmail.getText().toString().trim()
        );
    }
}
