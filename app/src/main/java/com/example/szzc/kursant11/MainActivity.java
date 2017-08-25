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

import org.json.JSONException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    DoCount doCount = new DoCount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Update update = new Update();
        try {
            update.update();
            Toast.makeText(MainActivity.this, "Kursy poprawnie pobrane z json.NBP !!",
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Brak połączenia z internetem!!",
                    Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Brak połączenia z internetem!!",
                    Toast.LENGTH_LONG).show();
        }

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    update.update();
                    Toast.makeText(MainActivity.this, "Kursy poprawnie pobrane z json.NBP !!",
                            Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Brak połączenia z internetem!!",
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Brak połączenia z internetem!!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });






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

        Button clickButton1 = (Button) findViewById(R.id.button2);
        clickButton1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doCount.doCount();
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
