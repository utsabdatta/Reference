package com.example.root.raspian;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SpeechToTextActivity extends AppCompatActivity {

    private TextView txtSpeechInput, output;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private String toRaspberry;
    private EditText getip;
    String current_hosts = "192.168.1.107";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);


        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        output = (TextView) findViewById(R.id.output);
        getip = (EditText) findViewById(R.id.getip);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    public void setip(View v)
    {
            current_hosts = getip.getText().toString();
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        h_count = 2;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    toRaspberry = result.get(0);
                    txtSpeechInput.setText(toRaspberry);
                    new RequestToPi().execute();

                }
                break;
            }

        }
    }

    public String RequestfromPi(String toRaspberry)
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



    String[] hosts = {"192.168.43.50", "192.168.1.107", "192.168.1.124"};
    int h_count = 0;
    /**
     * TODO: Request to Pi
     */

    class RequestToPi extends AsyncTask<String, String, String> {

        String fromRaspberry;
        boolean doesErrorOccurred = false;
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            fromRaspberry = "nothing";
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            try
            {
//                Socket soc=new Socket(hosts[h_count],2005);
                Socket soc=new Socket(current_hosts,2005);
                DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                PrintWriter out = new PrintWriter(soc.getOutputStream(),true);

                out.println(toRaspberry);
                fromRaspberry = in.readLine();
                soc.close();
            }catch(Exception e)
            {
                e.printStackTrace();
                doesErrorOccurred = true;
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            if(doesErrorOccurred) {
                output.setText("Exception Occured");
                h_count++;
                new RequestToPi().execute();
            }
            else
                output.setText(fromRaspberry);
        }

    } // end of AsyncTask

}

