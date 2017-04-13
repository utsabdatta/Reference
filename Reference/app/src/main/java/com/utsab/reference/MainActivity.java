package com.utsab.reference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void fragment(View v)
    {
        Intent i = new Intent(this, FragmentActivity.class);
        startActivity(i);
    }

    public void animation(View v)
    {
        Intent i = new Intent(this, AnimationActivity.class);
        startActivity(i);
    }

    public void internet(View v)
    {
        Intent i = new Intent(this, InternetActvity.class);
        startActivity(i);
    }

    public void json(View v)
    {
        Intent i = new Intent(this, JsonActivity.class);
        startActivity(i);
    }
}
