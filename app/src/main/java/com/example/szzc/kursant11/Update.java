package com.example.szzc.kursant11;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class Update extends AppCompatActivity {

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

    public void update() throws IOException, JSONException {

            JSONObject json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/USD/last/?format=json");
            JSONArray jsonarr = (JSONArray) json.get("rates");
            for (int i = 0; i < jsonarr.length(); ++i) {
                JSONObject rec = jsonarr.getJSONObject(i);
                Double id = rec.getDouble("mid");
                String idd = String.valueOf(id);
                TextView dolar = (TextView) findViewById(R.id.editText3);
                dolar.setText(idd);

            }
            JSONObject jsone = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/eur/last/?format=json");
            JSONArray jsonarre = (JSONArray) jsone.get("rates");
            for (int i = 0; i < jsonarre.length(); ++i) {
                JSONObject rec = jsonarre.getJSONObject(i);
                Double ide = rec.getDouble("mid");

                String idde = String.valueOf(ide);
                EditText euro = (EditText) findViewById(R.id.editText);
                euro.setText(idde);

                String idedate = rec.getString("effectiveDate");
                EditText date = (EditText) findViewById(R.id.editText2);
                date.setText(idedate);
            }
    }
}
