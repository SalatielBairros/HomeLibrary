package com.dev.salatiel.homelibrary.DataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dev.salatiel.homelibrary.model.*;

public class HomeLibraryContext extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "library.db";
    private static final int VERSION = 2;

    public HomeLibraryContext(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EmprestimoModel.getInstance().CreateTable());
        sqLiteDatabase.execSQL(LivroModel.getInstance().CreateTable());
        sqLiteDatabase.execSQL(NotaLeituraModel.getInstance().CreateTable());
        sqLiteDatabase.execSQL(UsuarioModel.getInstance().CreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(EmprestimoModel.getInstance().DropTable());
        sqLiteDatabase.execSQL(LivroModel.getInstance().DropTable());
        sqLiteDatabase.execSQL(NotaLeituraModel.getInstance().DropTable());
        sqLiteDatabase.execSQL(UsuarioModel.getInstance().DropTable());
        onCreate(sqLiteDatabase);
    }
}
