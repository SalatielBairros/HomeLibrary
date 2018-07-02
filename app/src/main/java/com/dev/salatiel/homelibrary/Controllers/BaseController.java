package com.dev.salatiel.homelibrary.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.dev.salatiel.homelibrary.DataAccess.HomeLibraryContext;
import com.dev.salatiel.interfaces.IEntityModel;

public class BaseController {

    private SQLiteDatabase db;
    private HomeLibraryContext banco;

    public BaseController(Context context){
        banco = new HomeLibraryContext(context);
    }

    public long insereDado(IEntityModel model){
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        for (int i = 1; i < model.getColumns().size(); i++) {
            valores.put(model.getColumns().get(i), model.getValues().get(i));
        }

        long resultado = db.insert(model.getTableName(), null, valores);
        db.close();
        if (resultado == -1) {
            throw new SQLiteException("Sistema não retornou identificador válido.");
        }
        return resultado;
    }

    public Cursor carregaDados(IEntityModel model){
        Cursor cursor;
        String[] campos = model.getColumns().toArray(new String[0]);
        db = banco.getReadableDatabase();
        cursor = db.query(model.getTableName(), campos, null, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(IEntityModel model){
        return carregaDados(model, "_id=" + model.get_id());
    }

    public Cursor carregaDados(IEntityModel model, String whereQuery){
        Cursor cursor;
        String[] campos = model.getColumns().toArray(new String[0]);
        db = banco.getReadableDatabase();
        cursor = db.query(model.getTableName() ,campos, whereQuery, null, null, null, null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(IEntityModel model){
        ContentValues valores;
        String where = "_id=" + model.get_id();
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        for (int i = 0; i < model.getColumns().size(); i++) {
            valores.put(model.getColumns().get(i), model.getValues().get(i));
        }
        db.update(model.getTableName(),valores,where,null);
        db.close();
    }

    public void deletaRegistro(IEntityModel model){
        String where = "_id = " + model.get_id();
        db = banco.getReadableDatabase();
        db.delete(model.getTableName(),where,null);
        db.close();
    }
}
