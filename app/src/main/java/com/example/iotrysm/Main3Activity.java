package com.example.iotrysm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {
    String myurl ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myurl ="http://10.0.2.2:8080/Server_MYSQL/app/ms";
        new consultar().execute(myurl);
    }

    private class consultar extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String res = "";
            try {
                res = consultarUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
                res = "error";

            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            int i;
            TableLayout table;
            TableRow row;
            Context contexto = getApplicationContext();
            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                System.out.println(ja.toString());
                table = (TableLayout) findViewById(R.id.tablatemp);
                for (i = 0; i < ja.length(); i++) {
                    row = (TableRow) LayoutInflater.from(contexto).inflate(R.layout.fila, null);
                    JSONObject json1 = new JSONObject();
                    json1 = ja.getJSONObject(i);
                    String fecha = json1.getString("fecha");
                    String temperatura = json1.getString("temp");
                    TextView textf1;
                    TextView textf2;
                    textf1 = (TextView)row.findViewById(R.id.datofecha);
                    textf2 = (TextView)row.findViewById(R.id.datodato);

                    textf1.setText(fecha);
                    textf2.setText(temperatura);

                    table.addView(row);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private String consultarUrl(String myurl) throws IOException {

        InputStream is = null;
        int len = 5000;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();

            is = conn.getInputStream();
            String res = readIt(is, len);
            return res;

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
