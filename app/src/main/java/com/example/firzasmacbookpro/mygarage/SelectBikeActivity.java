package com.example.firzasmacbookpro.mygarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectBikeActivity extends AppCompatActivity {

    ImageView imgCar, imgBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bike);

        imgCar = (ImageView)findViewById(R.id.imgCar);
        imgBike = (ImageView)findViewById(R.id.imgBike);

        imgCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectBikeActivity.this,SelectModelActivity.class);
                startActivity(intent);
            }
        });

        imgBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectBikeActivity.this,SelectModelActivity.class);
                startActivity(intent);
            }
        });
    }
}
