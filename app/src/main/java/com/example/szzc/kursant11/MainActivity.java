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

    final Update update = new Update();
    Dolar dolar = new Dolar();
    Euro euro = new Euro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        doUpdate();
        try {
            dolar.setRate((Double) update.updateItem(dolar.getUrl(),dolar.getSection()));
            euro.setRate((Double) update.updateItem(euro.getUrl(),euro.getSection()));
            euro.setDate((String) update.updateItem(euro.getUrl(),euro.getDateSection()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdate();
            }
        });

        final EditText inputTotalForEuro = (EditText) findViewById(R.id.editText6);
        inputTotalForEuro.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            try {
                TextView output = (TextView) findViewById(R.id.editText4);
                String resultAsString = String.valueOf(Round.round(Float.valueOf(s.toString()) / Float.valueOf(String.valueOf(euro.getRate())),2));
                output.setText(resultAsString+" €");

            }catch(ArithmeticException | NumberFormatException f)
            {
                TextView myOutputBox = (TextView) findViewById(R.id.editText4);
                myOutputBox.setText(null);
            }
            }
        });

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
                    TextView output = (TextView) findViewById(R.id.editText5);
                    String resultAsString = String.valueOf(Round.round(Float.valueOf(s.toString()) / Float.valueOf(String.valueOf(dolar.getRate())),2));
                    output.setText(resultAsString+" $");
                }catch(ArithmeticException | NumberFormatException f)
                {
                    TextView myOutputBox = (TextView) findViewById(R.id.editText5);
                    myOutputBox.setText(null);
                }
            }
        });

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
                Intent sendStuff = new Intent(MainActivity.this,ResultActivity.class);
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
        });
    }

    public void doUpdate(){
        try {
            TextView dolarTextView = (TextView) findViewById(R.id.editText3);
            dolarTextView.setText(update.updateItem(dolar.getUrl(),dolar.getSection()).toString());
            EditText euroTextView = (EditText) findViewById(R.id.editText);
            euroTextView.setText(update.updateItem(euro.getUrl(),euro.getSection()).toString());
            EditText dateTextView = (EditText) findViewById(R.id.editText2);
            dateTextView.setText(update.updateItem(euro.getUrl(),euro.getDateSection()).toString());

            Toast.makeText(MainActivity.this, "Kursy poprawnie pobrane z json.NBP !!",
                    Toast.LENGTH_LONG).show();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Brak połączenia z internetem!!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                finish();
                System.exit(0);
                break;
            default:

        }

        return true;
    }
}
