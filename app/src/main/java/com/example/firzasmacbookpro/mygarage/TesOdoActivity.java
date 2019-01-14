package com.example.firzasmacbookpro.mygarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class TesOdoActivity extends AppCompatActivity {

    EditText etDariOdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_odo);

        etDariOdo = (EditText)findViewById(R.id.etDariOdo);

        Intent i = getIntent();
        String text = i.getStringExtra("textOdo");
        etDariOdo.setText(text);
        etDariOdo.setEnabled(false);


    }
}
