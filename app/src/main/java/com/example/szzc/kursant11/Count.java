package com.example.szzc.kursant11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by szzc on 25.08.17.
 */

public class Count extends AppCompatActivity {

    public void doCount(){

        Intent sendStuff = new Intent(this, ResultActivity.class);

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
