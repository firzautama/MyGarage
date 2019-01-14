package com.example.firzasmacbookpro.mygarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //Button btnUser, btnAdmin;
    EditText etEmail, etPassword;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvLogin = (TextView) findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        /*btnUser = (Button)findViewById(R.id.btnUser);
        btnAdmin = (Button)findViewById(R.id.btnAdmin);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SelectBikeActivity.class);
                startActivity(intent);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AdminVehicleActivity.class);
                startActivity(intent);
            }
        });*/

    }

    public void login(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://firzautama.000webhostapp.com/mgLogin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //response 1 is admin, 2 is user..it's from php code
                        if(response.contains("1")){
                            startActivity(new Intent(getApplicationContext(),AdminVehicleActivity.class));
                        }else if (response.contains("2")){
                            startActivity(new Intent(getApplicationContext(),SelectBikeActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"Wrong email or password" + response,Toast.LENGTH_LONG).show();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("email",etEmail.getText().toString());
                params.put("passwordlogin",etPassword.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);

    }
}
