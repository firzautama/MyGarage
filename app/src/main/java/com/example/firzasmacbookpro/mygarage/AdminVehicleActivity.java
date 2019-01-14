package com.example.firzasmacbookpro.mygarage;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.firzasmacbookpro.mygarage.Adapter.AdapterData;
import com.example.firzasmacbookpro.mygarage.Model.ModelData;
import com.example.firzasmacbookpro.mygarage.Util.AppControler;
import com.example.firzasmacbookpro.mygarage.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminVehicleActivity extends AppCompatActivity {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsert,btnDelete,btnSearchMake;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vehicle);

        mRecyclerview = (RecyclerView)findViewById(R.id.recyclerviewTemp);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnSearchMake = (Button)findViewById(R.id.btnSearchMake);

        pd = new ProgressDialog(AdminVehicleActivity.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(AdminVehicleActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData(AdminVehicleActivity.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVehicleActivity.this,AdminVehicleInsertActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVehicleActivity.this,AdminVehicleDeleteActivity.class);
                startActivity(intent);

            }
        });

        btnSearchMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVehicleActivity.this,SearchVehicleActivity.class);
                startActivity(intent);
            }
        });


    }

    private void loadJson()
    {
        pd.setMessage("Fetching Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("Volley","Response : " + response.toString());
                        for (int i = 0; i < response.length() ; i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setId(data.getString("id"));
                                md.setModel(data.getString("model"));
                                md.setMake(data.getString("make"));
                                md.setCategory(data.getString("category"));
                                md.setDatemake(data.getString("dateMake"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("volley","error : " + error.getMessage());
            }
        });

        AppControler.getInstance().addToRequestQueue(reqData);

    }
}
