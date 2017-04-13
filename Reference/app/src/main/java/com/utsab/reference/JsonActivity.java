package com.utsab.reference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends AppCompatActivity {


    String jsonString =
            "{ \"utsab\": \"he is studying in sjbit\", \"achievments\": [ { \"hackd\": \"1st prize\" }, { \"hackd\": \"2nd prize\" } ] }"
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("achievments");
            for(int i=0; i<jsonArray.length(); i++)
            {

                Toast.makeText(getApplicationContext(), jsonArray.getJSONObject(i).getString("hackd"), Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(getApplicationContext(), jsonObject.getString("utsab"), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
