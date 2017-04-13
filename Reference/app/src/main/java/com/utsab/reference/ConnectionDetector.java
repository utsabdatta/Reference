package com.utsab.reference;

/**
 * Created by root on 13/4/17.
 */
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionDetector {
    private Context _context;

    public ConnectionDetector(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)  _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();




//        if (networkConnectivity()) {
//            try {
//                HttpURLConnection urlc = (HttpURLConnection) (new URL(
//                        "http://www.google.com").openConnection());
//                urlc.setRequestProperty("User-Agent", "Test");
//                urlc.setRequestProperty("Connection", "close");
//                urlc.setConnectTimeout(3000);
//                urlc.setReadTimeout(4000);
//                urlc.connect();
//                // networkcode2 = urlc.getResponseCode();
//                return (urlc.getResponseCode() == 200);
//            } catch (IOException e) {
//                return (false);
//            }
//        } else
//            return false;

    }

    private boolean networkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

}