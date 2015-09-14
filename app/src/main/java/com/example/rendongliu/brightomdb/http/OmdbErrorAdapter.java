package com.example.rendongliu.brightomdb.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.icemobile.framework.icehttpconnection.HttpErrorAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by rendong.liu on 20/08/15.
 */
public class OmdbErrorAdapter implements HttpErrorAdapter<IOException> {
    protected Context context;

    @Override
    public IOException mapClientError(IOException exception) {
        return new IOException(exception.getMessage());
    }

    @Override
    public IOException mapServerError(HttpURLConnection connection) throws IOException {
        return new IOException("Testing2");
    }

    public OmdbErrorAdapter(Context context) {
        this.context = context;
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
