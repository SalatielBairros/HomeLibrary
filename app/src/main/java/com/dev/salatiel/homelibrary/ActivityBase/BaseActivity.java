package com.dev.salatiel.homelibrary.ActivityBase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.dev.salatiel.homelibrary.*;
import com.dev.salatiel.homelibrary.Controllers.UsuarioController;
import com.dev.salatiel.homelibrary.model.UsuarioModel;
import com.dev.salatiel.interfaces.IBaseActivity;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView nvDrawer;
    private UsuarioController usuarioController;
    public UsuarioModel usuarioLogado;

    public void setToolbar(){
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setSideBar(){
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.navigation);
        setHeaderUserName();
        setSidebarEvents();
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void getLoggedUser(){
        usuarioLogado = getUsuarioController().getLoggedUser();
        if(usuarioLogado == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public UsuarioController getUsuarioController(){
        if(usuarioController == null){
            usuarioController = UsuarioController.getInstance(this);
        }
        return usuarioController;
    }

    public void basicLoad(){
        setToolbar();
        setFields();
        setEvents();
        getLoggedUser();
        setSideBar();
    }

    public void goToHomePage(Context context){
        Intent intent = new Intent(context, ListActivity.class);
        startActivity(intent);
    }

    public void goToEmprestimosList(Context context){
        Intent intent = new Intent(context, EmprestimosListActivity.class);
        startActivity(intent);
    }

    public void logout(Context context){
        getUsuarioController().logout();
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setHeaderUserName(){
        View header = nvDrawer.getHeaderView(0);
        TextView txtNomeUsuario = header.findViewById(R.id.txtNomeUsuarioLogado);
        txtNomeUsuario.setText(usuarioLogado.getNome());
    }

    private void setSidebarEvents(){
        final Activity thisActivity = this;

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                return super.onOptionsItemSelected(item);
            }
        };

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.btnMeusLivros){
                    goToHomePage(thisActivity);
                }else if(id == R.id.btnEmprestimos){
                    goToEmprestimosList(thisActivity);
                }else if(id == R.id.btnSair){
                    logout(thisActivity);
                }

                mDrawer.closeDrawers();  // CLOSE DRAWER
                return true;
            }
        });
    }
}
