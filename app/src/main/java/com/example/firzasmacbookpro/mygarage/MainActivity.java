package com.example.firzasmacbookpro.mygarage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    EditText etDariOdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDariOdo = (EditText)findViewById(R.id.etDariOdo);

        Intent i = getIntent();
        String text = i.getStringExtra("textOdo");
        etDariOdo.setText(text);
        etDariOdo.setEnabled(false);

        //this is for testing recycler in fragment
        ListMaintenance listMaint = new ListMaintenance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,listMaint);
        fragmentTransaction.commit();


        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            /*case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapFragment()).commit();
                break;
            case R.id.nav_bike:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BikeFragment()).commit();
                break;*/

            case R.id.nav_email:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"firza.utama@icloud.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mail From RideNZ");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi, I have a question...");

                try{
                    startActivity(Intent.createChooser(intent, "How do you want to send the email?"));
                } catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(this,"something's wrong",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.nav_call:
                String number = "262770700";
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                intent1.setData(Uri.parse("calling:" + number));
                if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    startActivity(intent1);
                }else {
                    Toast.makeText(this,"Check your phone's permission",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.nav_facebook:
                WebView webView = new WebView(this);
                setContentView(webView);
                webView.loadUrl("https://www.facebook.com/DUCATI-MONSTER-52120660776/");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
        }
    }
}
