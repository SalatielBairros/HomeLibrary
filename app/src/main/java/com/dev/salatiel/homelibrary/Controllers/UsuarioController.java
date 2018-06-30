package com.dev.salatiel.homelibrary.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.security.keystore.UserNotAuthenticatedException;
import com.dev.salatiel.homelibrary.model.UsuarioModel;

public class UsuarioController extends BaseController {
    public static final String UserSessionPreferences = "UserSessionPpeferences";
    private SharedPreferences sharedPreferences;

    public static UsuarioController getInstance(Context context){
        return new UsuarioController(context);
    }

    public UsuarioController(Context context) {
        super(context);
        sharedPreferences = context.getSharedPreferences(UserSessionPreferences, Context.MODE_PRIVATE);
    }

    public UsuarioModel login(String usuario, String senha) throws UserNotAuthenticatedException {
        String where = UsuarioModel.C_EMAIL + " = '" + usuario + "' AND " + UsuarioModel.C_SENHA +"     = '" + senha + "'";
        UsuarioModel model = UsuarioModel.getInstance();
        Cursor retCursor = carregaDados(model, where);
        if (retCursor.getCount() > 0) {
            int retId = Integer.parseInt(retCursor.getString(retCursor.getColumnIndexOrThrow(model.C_ID)));
            if (retId > 0) {
                model = getUserFromCursor(retCursor);
                setUserSessionPreferences(model);
                return model;
            }
        }
        throw new UserNotAuthenticatedException("Login inválido.");
    }

    public UsuarioModel getLoggedUser(){
        try {
            int idLoggedUser = sharedPreferences.getInt(UsuarioModel.C_ID, 0);
            if (idLoggedUser == 0) return null;

            Cursor userCursor = carregaDadoById(UsuarioModel.getInstance(idLoggedUser));
            if(userCursor.getCount() == 0) return null;

            return getUserFromCursor(userCursor);
        }catch(Exception ex) {
            logout();
            return null;
        }
    }

    public boolean verificaLogin(){
        UsuarioModel user = getLoggedUser();
        return (user != null && user.get_id() > 0);
    }

    private void setUserSessionPreferences(UsuarioModel model){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(model.C_ID, model.get_id());
        editor.putString(model.C_NOME, String.valueOf(model.getEmail()));
        editor.putString(model.C_EMAIL, String.valueOf(model.getNome()));
        editor.commit();
    }

    private UsuarioModel getUserFromCursor(Cursor cursor){
        return UsuarioModel.getInstance(
                Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioModel.C_ID))),
                cursor.getString(cursor.getColumnIndexOrThrow(UsuarioModel.C_NOME)),
                cursor.getString(cursor.getColumnIndexOrThrow(UsuarioModel.C_SENHA)),
                cursor.getString(cursor.getColumnIndexOrThrow(UsuarioModel.C_EMAIL))
        );
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
