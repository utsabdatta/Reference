package com.utsab.reference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class InternetActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_actvity);

        ConnectionDetector cd = new ConnectionDetector(this);
        if(cd.isConnectingToInternet())
            Toast.makeText(getApplicationContext(), "Internet is Conencted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
    }
}
