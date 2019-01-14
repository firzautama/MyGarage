package com.example.firzasmacbookpro.mygarage;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.firzasmacbookpro.mygarage.Util.AppControler;
import com.example.firzasmacbookpro.mygarage.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminVehicleInsertActivity extends AppCompatActivity {

    EditText etModel, etMake, etCategory, etDateMake;
    Button btnCancel, btnAdd;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vehicle_insert);

        //untuk update data disini kita pakai getIntent untuk mengambil parameter dari intent yang ada
        //supaya tertampilkan di edit text utk nanti diedit
        //get data from intent
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_id = data.getStringExtra("id");
        String intent_model = data.getStringExtra("model");
        String intent_make = data.getStringExtra("make");
        String intent_category = data.getStringExtra("category");
        String intent_dateMake = data.getStringExtra("dateMake");
        //end get data from intent


        etModel = (EditText)findViewById(R.id.etModel);
        etMake = (EditText)findViewById(R.id.etMake);
        etCategory = (EditText)findViewById(R.id.etCategory);
        etDateMake = (EditText)findViewById(R.id.etDateMake);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        pd = new ProgressDialog(AdminVehicleInsertActivity.this);

        //kondisi update / intent
        //gak perlu bikin layout baru tapi pake yg insert jadi Adaptif Dynamic gitu cui...cool
        if (update == 1)
        {
            btnAdd.setText("Update Data");

            etModel.setText(intent_model);
            etMake.setText(intent_make);
            etCategory.setText(intent_category);
            etDateMake.setText(intent_dateMake);

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update == 1)
                {
                    UpdateData();
                }else {
                    InsertData();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVehicleInsertActivity.this,AdminVehicleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void UpdateData ()
    {
        pd.setMessage("Updating Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(AdminVehicleInsertActivity.this,"pesan : "+ res.getString("message"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(AdminVehicleInsertActivity.this,AdminVehicleActivity.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Toast.makeText(AdminVehicleInsertActivity.this,"pesan : Insert Data Failed",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                map.put("model",etModel.getText().toString());
                map.put("make",etMake.getText().toString());
                map.put("category",etCategory.getText().toString());
                map.put("dateMake",etDateMake.getText().toString());

                return map;
            }
        };

        AppControler.getInstance().addToRequestQueue(updateReq);
    }

    private void InsertData ()
    {
        pd.setMessage("Inserting Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(AdminVehicleInsertActivity.this,"pesan : "+ res.getString("message"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(AdminVehicleInsertActivity.this,AdminVehicleActivity.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(AdminVehicleInsertActivity.this,"pesan : Insert Data Fail",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("model",etModel.getText().toString());
                map.put("make",etMake.getText().toString());
                map.put("category",etCategory.getText().toString());
                map.put("dateMake",etDateMake.getText().toString());

                return map;
            }
        };

        AppControler.getInstance().addToRequestQueue(sendData);
    }
}
