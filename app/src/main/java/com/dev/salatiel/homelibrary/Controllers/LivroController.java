package com.dev.salatiel.homelibrary.Controllers;

import android.content.Context;
import android.database.Cursor;
import com.dev.salatiel.enums.StatusBase;
import com.dev.salatiel.homelibrary.enums.StatusLeituraLivro;
import com.dev.salatiel.homelibrary.enums.StatusLivro;
import com.dev.salatiel.homelibrary.model.LivroModel;
import com.dev.salatiel.homelibrary.model.UsuarioModel;
import com.dev.salatiel.homelibrary.webservices.GoogleApiRequest;
import com.dev.salatiel.util.DateTimeUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class LivroController extends BaseController {

    public LivroController(Context context) {
        super(context);
    }

    public LivroModel getByISBN(String isbns) {
        GoogleApiRequest request = new GoogleApiRequest();
        LivroModel retBook = LivroModel.getInstance(isbns);
        try {
            JSONObject ret = request.execute(isbns).get();
            if (ret != null && ret.getInt("totalItems") > 0) {
                JSONObject bookJson = ret.getJSONArray("items").getJSONObject(0);
                retBook = gbooksToModel(bookJson, retBook);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return retBook;
        }
    }

    public boolean verifyIfExistsByISBN(String isbn) {
        String whereQuery = LivroModel.C_ISBN + " = " + isbn;
        Cursor ret = carregaDados(LivroModel.getInstance(), whereQuery);
        return (ret.getCount() > 0);
    }

    public LivroModel carregarDadosById(int id) {
        LivroModel model = LivroModel.getInstance(id);
        Cursor cursor = carregaDadoById(model);
        model.setIsbn(cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_ISBN)));
        model.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_TITULO)));
        model.setEditora(cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_EDITORA)));
        model.setSinopse(cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_SINOPSE)));
        model.setAutor(cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_AUTOR)));
        model.setStatus(StatusBase.values()[(cursor.getInt(cursor.getColumnIndexOrThrow(LivroModel.C_STATUS)))]);
        model.setStatusLeitura(StatusLeituraLivro.values()[(cursor.getInt(cursor.getColumnIndexOrThrow(LivroModel.C_STATUS_LEITURA)))]);
        model.setStatusLivro(StatusLivro.values()[(cursor.getInt(cursor.getColumnIndexOrThrow(LivroModel.C_STATUS_LIVRO)))]);

        String dataCadastro = cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_DATA_CADASTRO));
        model.setDataCadastro(DateTimeUtil.toDateTime(dataCadastro, "yyyy-MM-dd HH:mm:ss"));

        String dataModificacao = cursor.getString(cursor.getColumnIndexOrThrow(LivroModel.C_DATA_MODIFICACAO));
        model.setDataAlteracao(DateTimeUtil.toDateTime(dataModificacao, "yyyy-MM-dd HH:mm:ss"));

        return model;

    }

    private LivroModel gbooksToModel(JSONObject bookJson, LivroModel livroModel) throws JSONException {
        JSONObject volumeInfo = bookJson.getJSONObject("volumeInfo");
        livroModel.setTitulo(
                volumeInfo.getString("title")
        );
        livroModel.setAutor(
                volumeInfo.getJSONArray("authors").getString(0)
        );
        livroModel.setEditora(
                volumeInfo.getString("publisher")
        );
        livroModel.setSinopse(
                volumeInfo.getString("description")
        );
        return livroModel;
    }
}
