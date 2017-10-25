package com.example.szzc.kursant11;

import android.support.v7.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.example.szzc.kursant11.NbpJsonGet.readJsonFromUrl;

public class Update extends AppCompatActivity {

    public <T> T updateItem(String url,String item) throws IOException, JSONException{

        JSONObject json = readJsonFromUrl(url);
        JSONArray jsonArray = (JSONArray) json.get("rates");
        T itemStr = null;

        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject rec = jsonArray.getJSONObject(i);
            T id = (T) rec.get(item);
            itemStr = (T) id;
        }
        return itemStr;
    }
}
