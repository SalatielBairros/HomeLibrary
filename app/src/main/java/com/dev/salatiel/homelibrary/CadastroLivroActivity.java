package com.dev.salatiel.homelibrary;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.dev.salatiel.homelibrary.ActivityBase.BaseFormActivity;
import com.dev.salatiel.homelibrary.Controllers.LivroController;
import com.dev.salatiel.homelibrary.model.LivroModel;
import com.dev.salatiel.interfaces.ICustomDataListener;

import static android.widget.Toast.*;

public class CadastroLivroActivity extends BaseFormActivity<LivroModel> implements ICustomDataListener {

    private LivroController livroController;
    private EditText txtIsbn;
    private EditText txtTitulo;
    private EditText txtAutor;
    private EditText txtEditora;
    private EditText txtSinopse;
    private Button btnSalvar;
    private LivroModel livroModel;
    private final static String TAG = "CAD_LIVRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);
        basicLoad();
        showISBNModal();
        loadData();
    }

    private void showISBNModal() {
        if (idModel == 0) {
            DialogFragment isbnDialog = new ISBNDialogFragment();
            isbnDialog.show(getSupportFragmentManager(), ISBNDialogFragment.TAG);
        }
    }

    @Override
    public void onDataLoaded(String data) {
        txtIsbn.setText(data);
        populateFields(livroController.getByISBN(data));
    }

    public void setFields() {
        livroController = new LivroController(this);
        txtIsbn = findViewById(R.id.edtISBN);
        txtTitulo = findViewById(R.id.edtTitulo);
        txtAutor = findViewById(R.id.edtAutor);
        txtEditora = findViewById(R.id.edtEditora);
        txtSinopse = findViewById(R.id.edtSinopse);
        btnSalvar = findViewById(R.id.btnSalvarLivro);

        getParameters();
    }

    public void getParameters() {
        Bundle parameters = getIntent().getExtras();
        if (parameters != null) {
            String retCodigo = parameters.getString("codigo");
            if (retCodigo != null && retCodigo.length() > 0) {
                idModel = Integer.parseInt(retCodigo);
            }
        }
    }

    public void setEvents() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    public boolean validate() {
        String message = "";
        if (txtTitulo.getText().toString().trim().isEmpty()
                || txtAutor.getText().toString().trim().isEmpty())
            message = getString(R.string.erro_validacao_preenchimento);
        else if (idModel == 0 && livroController.verifyIfExistsByISBN(txtIsbn.getText().toString())) {
            message = getString(R.string.livro_existe_cadastrado);
        }

        if (message.isEmpty()) return true;

        makeText(CadastroLivroActivity.this, message, LENGTH_LONG).show();
        return false;
    }

    public void save() {
        if (validate()) {
            try {
                LivroModel livro = getFields();
                if (idModel > 0) {
                    livroController.alteraRegistro(livro);
                } else {
                    idModel = livroController.insereDado(livro);
                }
                makeText(this, R.string.salvo_sucesso, LENGTH_LONG).show();
                goToHomePage(this);
            } catch (Exception ex) {
                makeText(this, R.string.erro_inserir_registro, LENGTH_LONG).show();
                Log.w(TAG, ex.getMessage());
            }
        }
    }

    public void populateFields(LivroModel livro) {
        txtIsbn.setText(livro.getIsbn());
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtEditora.setText(livro.getEditora());
        txtSinopse.setText(livro.getSinopse());
    }

    public LivroModel getFields() {
        if (idModel == 0 || livroModel == null || livroModel.get_id() == 0) {
            livroModel = LivroModel.createNew((int) idModel);
        }
        livroModel.setIsbn(
                txtIsbn.getText().toString()
        );
        livroModel.setTitulo(
                txtTitulo.getText().toString()
        );
        livroModel.setAutor(
                txtAutor.getText().toString()
        );
        livroModel.setEditora(
                txtEditora.getText().toString()
        );
        livroModel.setSinopse(
                txtSinopse.getText().toString()
        );
        return livroModel;
    }

    public void loadData() {
        if(idModel > 0) {
            livroModel = livroController.carregarDadosById((int) idModel);
            populateFields(livroModel);
        }
    }
}
