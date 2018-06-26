package com.dev.salatiel.homelibrary.webservices;

import org.json.JSONObject;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

public class GoogleBooksTest {
    @Test
    public void getByISBN(){
        GoogleApiRequest request = new GoogleApiRequest();
        try {
            JSONObject ret = request.execute("0891074686").get();
            assertNotNull(ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
