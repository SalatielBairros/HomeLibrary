package com.dev.salatiel.homelibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.dev.salatiel.homelibrary.ActivityBase.BaseListActivity;
import com.dev.salatiel.homelibrary.Controllers.LivroController;
import com.dev.salatiel.homelibrary.model.LivroModel;

public class ListActivity extends BaseListActivity {

    private ListView lista;
    FloatingActionButton btnAdd;
    LivroController livroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        basicLoad();
        carregarLista();
    }

    @Override
    public void onRestart() {
        carregarLista();
        super.onRestart();
    }

    public void carregarLista(){
        LivroModel model = LivroModel.getInstance();
        final Cursor cursor = livroController.carregaDados(model);
        String[] nomeCampos = model.getViewColumns().toArray(new String[0]);
        int[] idViews = new int[] {R.id.tituloLivro, R.id.tvAutor, R.id.tvEditora};

        final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(ListActivity.this,
                R.layout.item_lista_livros,
                cursor,
                nomeCampos,
                idViews, 0);

        lista = findViewById(R.id.listLivros);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                cursor.moveToPosition(position);

                String codigo =
                        cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_ID));

                Intent intent = new Intent(ListActivity.this, CadastroLivroActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                cursor.moveToPosition(position);

                final int idLivroSelecionado =
                        cursor.getInt(cursor.getColumnIndexOrThrow(LivroModel.C_ID));

                new AlertDialog.Builder(ListActivity.this)
                        .setTitle(R.string.excluindo_livro)
                        .setMessage(R.string.confirma_exclusao_livro)
                        .setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                livroController.deletaRegistro(LivroModel.getInstance(idLivroSelecionado));
                                Toast.makeText(ListActivity.this, R.string.registro_removido_sucesso, Toast.LENGTH_LONG).show();
                                carregarLista();
                            }
                        })
                        .setNegativeButton(R.string.nao, null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public void setFields() {
        livroController = new LivroController(this);
        btnAdd = findViewById(R.id.btnAdd);
    }

    @Override
    public void setEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, CadastroLivroActivity.class));
            }
        });
    }
}
