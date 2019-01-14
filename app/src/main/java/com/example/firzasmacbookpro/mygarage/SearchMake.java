package com.example.firzasmacbookpro.mygarage;

import android.os.AsyncTask;

import com.example.firzasmacbookpro.mygarage.SearchVehicleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchMake extends AsyncTask<Void,Void,Void>{
    String data ="";
    String dataParsed ="";
    String singleParsed ="";

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://firzautama.000webhostapp.com/getAllProduct.php");//tadinya ada error>click lampu merah>click surround with try and catch (URLException)

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //openConnection ada error>click lampu merah>add catch (IOException)
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length() ; i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Bike ID: " + JO.get("bikeID") + "\n"+
                        "Bike Name: " + JO.get("bikeName") + "\n"+
                        "Bike Type: " + JO.get("bikeType") + "\n"+
                        "Bike Maintenance: " + JO.get("bikeMaint") + "\n";

                dataParsed = dataParsed + singleParsed + "\n";

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        SearchVehicleActivity.txtDataFetched.setText(this.dataParsed);
    }
}

