package com.example.root.raspian;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.raspian.testing.MyService;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);

        Intent intent = new Intent(this, SpeechToTextActivity.class);
        startActivity(intent);

    }

    public void send(View v)
    {
        output.setText(RequestPi(input.getText().toString()));
    }

    public String RequestPi(String toRaspberry)
    {

        String fromRaspberry = "nothing";
        try
        {
            Socket soc=new Socket("192.168.1.107",2004);
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);

            out.println(toRaspberry);
            fromRaspberry = in.readLine();
            soc.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  fromRaspberry;
    }
}
