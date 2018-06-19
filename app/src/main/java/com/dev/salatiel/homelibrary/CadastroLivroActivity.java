package com.dev.salatiel.homelibrary;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.dev.salatiel.homelibrary.Controllers.LivroController;
import com.dev.salatiel.homelibrary.model.LivroModel;
import com.dev.salatiel.interfaces.ICustomDataListener;

//TODO: Interface base
//TODO: Interface crud
public class CadastroLivroActivity extends AppCompatActivity implements ICustomDataListener {

    LivroController livroController;

    int idLivro;
    EditText txtIsbn;
    EditText txtTitulo;
    EditText txtAutor;
    EditText txtEditora;
    EditText txtSinopse;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);
        setFields();
        setEvents();
        showISBNModal();
    }

    public void showISBNModal(){
        DialogFragment isbnDialog = new ISBNDialogFragment();
        isbnDialog.show(getSupportFragmentManager(), ISBNDialogFragment.TAG);
    }

    @Override
    public void onDataLoaded(String data) {
        txtIsbn.setText(data);
    }

    private void setFields(){
        livroController = new LivroController(this);
        txtIsbn = findViewById(R.id.edtISBN);
        txtTitulo = findViewById(R.id.edtTitulo);
        txtAutor = findViewById(R.id.edtAutor);
        txtEditora = findViewById(R.id.edtEditora);
        txtSinopse = findViewById(R.id.edtSinopse);
        btnSalvar = findViewById(R.id.btnSalvarLivro);
    }

    private void setEvents(){
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private boolean validate(){
        String message = "";
        if(txtTitulo.getText().toString().trim().isEmpty()
                || txtAutor.getText().toString().trim().isEmpty())
            message = getString(R.string.erro_validacao_preenchimento);

        if(message.isEmpty()) return true;

        Toast.makeText(CadastroLivroActivity.this, message, Toast.LENGTH_LONG);
        return false;
    }

    private void salvar(){
        if(validate()){
            LivroModel livro = getData();
            if(idLivro > 0){
                livroController.alteraRegistro(livro);
            }else{
                livroController.insereDado(livro);
            }
        }
    }

    private LivroModel getData(){
        return LivroModel.getInstance();
    }
}
