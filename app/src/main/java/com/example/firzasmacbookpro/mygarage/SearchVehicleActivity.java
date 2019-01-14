package com.example.firzasmacbookpro.mygarage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchVehicleActivity extends AppCompatActivity {

    EditText etMakeSearch;
    Button btnSearch;

    public static TextView txtDataFetched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicle);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        etMakeSearch = (EditText)findViewById(R.id.etMakeSearch);
        txtDataFetched = (TextView)findViewById(R.id.etMakeSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchMake process = new SearchMake();
                process.execute();
            }
        });

    }
}