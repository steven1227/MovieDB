package com.example.rendongliu.brightomdb.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.icemobile.framework.logger.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rendong.liu on 22/09/15.
 */
public class MyService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
   **/
    public MyService() {
        super("Thread-A");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String decodedString = null;
        URL url = null;
        try {
            url = new URL("http://www.omdbapi.com/?s=social");
        HttpURLConnection urlConnection = null;
        urlConnection = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            int c;
            StringBuilder response= new StringBuilder();
            while ((c = in.read()) != -1) {
                response.append( (char)c ) ;
            }
           decodedString = response.toString();
        in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent a = new Intent("custom-event-name");
        a.putExtra("json",decodedString);
        LocalBroadcastManager.getInstance(this).sendBroadcast(a);

    }
}
