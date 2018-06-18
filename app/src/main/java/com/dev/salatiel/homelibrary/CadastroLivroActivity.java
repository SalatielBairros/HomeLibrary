package com.dev.salatiel.homelibrary;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

public class CadastroLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        showISBNModal();
    }

    public void showISBNModal(){
        DialogFragment isbnDialog = new ISBNDialogFragment();
        isbnDialog.show(getSupportFragmentManager(), ISBNDialogFragment.TAG);
    }
}
