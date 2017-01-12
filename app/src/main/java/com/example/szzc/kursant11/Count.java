package com.example.szzc.kursant11;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by szzc on 18.08.16.
 */
public class Count extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.count);

   /**     Button clickButton = (Button) findViewById(R.id.button3);
        clickButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }});
*/


                Bundle gt = getIntent().getExtras();
        String strdata = gt.getString("data");

        String streuro = gt.getString("euro");
        String strdolar = gt.getString("dolar");

        String strsum = gt.getString("sum");
        String strbill = gt.getString("bill");

        EditText mEdit = (EditText) findViewById(R.id.editText8);
        mEdit.setKeyListener(null);
        EditText mEdit2 = (EditText) findViewById(R.id.editText9);
        mEdit2.setKeyListener(null);
        EditText mEdit3 = (EditText) findViewById(R.id.editText10);
        mEdit3.setKeyListener(null);



        try {
            Double restEuro = Double.valueOf(strbill) * Double.valueOf(streuro) - Double.valueOf(strsum);
            EditText euro = (EditText) findViewById(R.id.editText8);
            euro.setText(String.valueOf(Round.round(restEuro, 2)));

            Double restDolar = Double.valueOf(strbill) * Double.valueOf(strdolar) - Double.valueOf(strsum);
            EditText dolar = (EditText) findViewById(R.id.editText9);
            dolar.setText(String.valueOf(Round.round(restDolar, 2)));

            Double restPln = Double.valueOf(strbill) - Double.valueOf(strsum);
            EditText pln = (EditText) findViewById(R.id.editText10);
            pln.setText(String.valueOf(Round.round(restPln, 2)));
        }catch (NumberFormatException f){
            Toast.makeText(Count.this, "Błąd !! Czy pobrałaś kursy??  Używaj tylko cyfr!! Dla ułamków używaj kropki a nie przecinka!!",
                    Toast.LENGTH_LONG).show();     }

    }
    }


