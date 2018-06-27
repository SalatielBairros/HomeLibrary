package com.dev.salatiel.homelibrary;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dev.salatiel.homelibrary.Controllers.LivroController;
import com.dev.salatiel.homelibrary.model.LivroModel;

public class ListActivity extends AppCompatActivity {

    private ListView lista;
    FloatingActionButton btnAdd;
    LivroController livroController;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    private NavigationView nvDrawer;
    private final static String TAG = "LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.navigation);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Setup drawer view
        //setupDrawerContent(nvDrawer);

        livroController = new LivroController(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, CadastroLivroActivity.class));
            }
        });

        carregarLista();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                return super.onOptionsItemSelected(item);
            }
        };

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawer.closeDrawers();  // CLOSE DRAWER
                return true;
            }
        });

        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onRestart() {
        carregarLista();
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
