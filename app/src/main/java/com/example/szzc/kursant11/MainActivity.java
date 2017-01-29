package com.example.szzc.kursant11;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            update();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // To to euro

        final EditText myTextBox1 = (EditText) findViewById(R.id.editText6);
        myTextBox1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            try {
                TextView myOutputBox = (TextView) findViewById(R.id.editText4);
                Double cyfra = Double.valueOf(s.toString());
                EditText kurs = (EditText) findViewById(R.id.editText);
                Editable kursS = kurs.getText();
                Double kursDouble = Double.valueOf(kursS.toString());
                Double wynik = cyfra / kursDouble;
                String cyfras = String.valueOf(Round.round(wynik,2));
                  myOutputBox.setText(cyfras+" €");
            }catch(ArithmeticException | NumberFormatException f)
            {
                TextView myOutputBox = (TextView) findViewById(R.id.editText4);
                myOutputBox.setText(null);
            }
            }
        });

        // do tąd

        // To to dolar

        final EditText myTextBox2 = (EditText) findViewById(R.id.editText6);
        myTextBox2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                try {
                    TextView myOutputBox = (TextView) findViewById(R.id.editText5);
                    Double cyfra = Double.valueOf(s.toString());
                    EditText kurs = (EditText) findViewById(R.id.editText3);
                    Editable kursS = kurs.getText();
                    Double kursDouble = Double.valueOf(kursS.toString());
                    Double wynik = cyfra / kursDouble;
                    String cyfras = String.valueOf(Round.round(wynik,2));
                    myOutputBox.setText(cyfras+" $");
                }catch(ArithmeticException | NumberFormatException f)
                {
                    TextView myOutputBox = (TextView) findViewById(R.id.editText5);
                    myOutputBox.setText(null);
                }
            }
        });

        // do tąd

        EditText mEdit = (EditText) findViewById(R.id.editText);
        mEdit.setKeyListener(null);
        EditText mEdit2 = (EditText) findViewById(R.id.editText2);
        mEdit2.setKeyListener(null);
        EditText mEdit3 = (EditText) findViewById(R.id.editText3);
        mEdit3.setKeyListener(null);

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Button clickButton1 = (Button) findViewById(R.id.button2);
        clickButton1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                licz();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
        try{
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

            Toast.makeText(MainActivity.this, "Kursy poprawnie pobrane z json.NBP !!",
                    Toast.LENGTH_LONG).show();


        }catch(IOException | ArithmeticException | JSONException f)
        {
            Toast.makeText(MainActivity.this, "Brak połączenia z internetem!!",
                    Toast.LENGTH_LONG).show();
        }


    }





    public void licz(){

        Intent sendStuff = new Intent(this, Count.class);

        EditText date = (EditText) findViewById(R.id.editText2);
        sendStuff.putExtra("data", date.getText().toString());

        EditText euro = (EditText) findViewById(R.id.editText);
        sendStuff.putExtra("euro", euro.getText().toString());

        EditText dolar = (EditText) findViewById(R.id.editText3);
        sendStuff.putExtra("dolar", dolar.getText().toString());

        EditText sum = (EditText) findViewById(R.id.editText6);
        sendStuff.putExtra("sum", sum.getText().toString());

        EditText bill = (EditText) findViewById(R.id.editText7);
        sendStuff.putExtra("bill", bill.getText().toString());

        startActivity(sendStuff);


    }


}
