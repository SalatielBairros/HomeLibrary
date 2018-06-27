package com.dev.salatiel.homelibrary;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dev.salatiel.homelibrary.Controllers.LivroController;
import com.dev.salatiel.homelibrary.model.LivroModel;

public class ListActivity extends AppCompatActivity {

    private ListView lista;
    FloatingActionButton btnAdd;
    LivroController livroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        livroController = new LivroController(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, CadastroLivroActivity.class));
            }
        });

        carregarLista();
    }

    @Override
    public void onRestart() {
        carregarLista();
        super.onRestart();
    }

    private void carregarLista(){
        LivroModel model = LivroModel.getInstance();
        final Cursor cursor = livroController.carregaDados(model);
        String[] nomeCampos = model.getViewColumns().toArray(new String[0]);
        int[] idViews = new int[] {R.id.tituloLivro, R.id.tvAutor, R.id.tvEditora};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(ListActivity.this,
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
    }
}
