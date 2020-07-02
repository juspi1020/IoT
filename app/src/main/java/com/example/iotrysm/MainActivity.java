package com.example.iotrysm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText login, pass;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login= findViewById(R.id.login);
        pass=findViewById(R.id.pass);
        b1=findViewById(R.id.bot1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l = login.getText().toString();
                String p = pass.getText().toString();
                if(l.equals("admin")&& p.equals("1234")){
                    Intent in =new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(in);
                }
                else{
                    login.setText("");
                    pass.setText("");


                }
            }
        });
    }
}
