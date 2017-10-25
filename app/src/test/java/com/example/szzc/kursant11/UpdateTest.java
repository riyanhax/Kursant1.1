package com.example.szzc.kursant11;

import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by szzc on 15.10.17.
 */

public class UpdateTest {

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
@Test
    public void update() throws IOException, JSONException {

        JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/USD/last/?format=json");
        JSONArray jsonarr = (JSONArray) json.get("rates");
        assertNotNull(json);


        JSONObject jsone = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/eur/last/?format=json");
        JSONArray jsonarre = (JSONArray) jsone.get("rates");
        assertNotNull(jsone);
        }
    }



