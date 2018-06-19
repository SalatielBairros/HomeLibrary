package com.dev.salatiel.homelibrary;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import com.dev.salatiel.interfaces.ICustomDataListener;

public class CadastroLivroActivity extends AppCompatActivity implements ICustomDataListener {

    EditText txtIsbn;
    EditText txtTitulo;
    EditText txtAutor;
    EditText txtEditora;
    EditText txtSinopse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);
        setFields();
        showISBNModal();
    }

    public void showISBNModal(){
        DialogFragment isbnDialog = new ISBNDialogFragment();
        isbnDialog.show(getSupportFragmentManager(), ISBNDialogFragment.TAG);
    }

    @Override
    public void onDataLoaded(String data) {

    }

    private void setFields(){
        txtIsbn = findViewById(R.id.edtISBN);
        txtTitulo = findViewById(R.id.edtTitulo);
        txtAutor = findViewById(R.id.edtAutor);
        txtEditora = findViewById(R.id.edtEditora);
        txtSinopse = findViewById(R.id.edtSinopse);
    }
}
