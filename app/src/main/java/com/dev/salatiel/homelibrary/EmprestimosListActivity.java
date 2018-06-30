package com.dev.salatiel.homelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dev.salatiel.homelibrary.ActivityBase.BaseListActivity;

public class EmprestimosListActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimos_list);
        basicLoad();
        carregarLista();
    }

    @Override
    public void setFields() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void carregarLista() {

    }
}
