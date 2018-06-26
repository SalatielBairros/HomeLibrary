package com.dev.salatiel.homelibrary.Controllers;

import android.content.Context;
import com.dev.salatiel.homelibrary.model.LivroModel;
import com.dev.salatiel.homelibrary.webservices.GoogleApiRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LivroController extends BaseController {
    public LivroController(Context context) {
        super(context);
    }

    public LivroModel getByISBN(String isbns){
        GoogleApiRequest request = new GoogleApiRequest();
        LivroModel retBook = LivroModel.getInstance(isbns);
        try {
            JSONObject ret = request.execute(isbns).get();
            if(ret != null && ret.getInt("totalItems") > 0){
                JSONObject bookJson = ret.getJSONArray("items").getJSONObject(0);
                retBook = gbooksToModel(bookJson, retBook);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return retBook;
        }
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
