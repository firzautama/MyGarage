package com.example.firzasmacbookpro.mygarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OdometerActivity extends AppCompatActivity {

    EditText etOdometer;
    Button btnGoMaintenance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometer);

        etOdometer=(EditText)findViewById(R.id.etOdometer);
        btnGoMaintenance=(Button)findViewById(R.id.btnGoMaintenance);


        String odo = etOdometer.getText().toString();

        btnGoMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OdometerActivity.this,MainActivity.class);
                intent.putExtra("textOdo",etOdometer.getText().toString());
                startActivity(intent);
            }
        });


    }
}
