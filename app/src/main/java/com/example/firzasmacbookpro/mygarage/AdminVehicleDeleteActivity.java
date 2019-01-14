package com.example.firzasmacbookpro.mygarage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AdminVehicleDeleteActivity extends AppCompatActivity {

    EditText etID;
    Button btnDelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vehicle_delete);

        etID = (EditText)findViewById(R.id.etId);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        pd = new ProgressDialog(AdminVehicleDeleteActivity.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteData();

            }
        });
    }

    private void DeleteData()
    {
        pd.setMessage("Deleting Data ...");
        pd.setCancelable(false);
        pd.show();

        StringRequest delReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley","response : "+ response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(AdminVehicleDeleteActivity.this, "pesan : "+ res.getString("message"),Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(AdminVehicleDeleteActivity.this,AdminVehicleActivity.class));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("volley", "error : "+ error.getMessage());
                Toast.makeText(AdminVehicleDeleteActivity.this,"pesan : Delete Data Failed", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id",etID.getText().toString());
                return map;
            }
        };

        AppControler.getInstance().addToRequestQueue(delReq);

    }
}
