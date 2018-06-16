package com.dev.salatiel.homelibrary.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.security.keystore.UserNotAuthenticatedException;
import com.dev.salatiel.homelibrary.model.UsuarioModel;

public class UsuarioController extends BaseController {
    public static final String UserSessionPreferences = "UserSessionPpeferences";
    private SharedPreferences sharedPreferences;

    public UsuarioController(Context context) {
        super(context);
        sharedPreferences = context.getSharedPreferences(UserSessionPreferences, Context.MODE_PRIVATE);
    }

    public UsuarioModel login(String usuario, String senha) throws UserNotAuthenticatedException {
        String where = " NOME = " + usuario + "AND SENHA = " + senha;
        UsuarioModel model = UsuarioModel.getInstance();
        Cursor retCursor = carregaDados(model, where);
        int retId = Integer.parseInt(retCursor.getString(retCursor.getColumnIndexOrThrow(model.C_ID)));

        if(retId > 0) {
            model = UsuarioModel.getInstance(
                    retId,
                    retCursor.getString(retCursor.getColumnIndexOrThrow(model.C_NOME)),
                    retCursor.getString(retCursor.getColumnIndexOrThrow(model.C_SENHA)),
                    retCursor.getString(retCursor.getColumnIndexOrThrow(model.C_EMAIL))
            );

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(model.C_ID, String.valueOf(model.get_id()));
            editor.putString(model.C_NOME, String.valueOf(model.getEmail()));
            editor.putString(model.C_EMAIL, String.valueOf(model.getNome()));
            editor.commit();

            return model;
        }else{
            throw new UserNotAuthenticatedException("Login inválido.");
        }
    }
}
