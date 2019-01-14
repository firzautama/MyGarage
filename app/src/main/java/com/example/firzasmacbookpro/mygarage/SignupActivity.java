package com.example.firzasmacbookpro.mygarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.firzasmacbookpro.mygarage.Util.AppControler;
import com.example.firzasmacbookpro.mygarage.Util.ServerAPI;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText etNameSignUp, etEmailSignUp, etPasswordSignUp;
    TextView tvSignUp, tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etNameSignUp = (EditText)findViewById(R.id.etNameSignUp);
        etEmailSignUp = (EditText)findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = (EditText)findViewById(R.id.etPasswordSignUp);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertSignupData();
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void InsertSignupData (){

        StringRequest sendData = new StringRequest(Request.Method.POST, "https://firzautama.000webhostapp.com/mgInsertTblUser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(SignupActivity.this, "pesan : " + res.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignupActivity.this,"pesan : Insert Data Fail",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map = new HashMap<>();
                map.put("name",etNameSignUp.getText().toString());
                map.put("email",etEmailSignUp.getText().toString());
                map.put("password",etPasswordSignUp.getText().toString());

                return map;
            }
        };
        AppControler.getInstance().addToRequestQueue(sendData);
    }
}


