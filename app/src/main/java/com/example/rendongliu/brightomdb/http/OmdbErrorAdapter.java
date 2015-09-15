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
public class OmdbErrorAdapter implements HttpErrorAdapter<OmdbException> {
    protected Context context;

    @Override
    public OmdbException mapClientError(IOException exception) {
        return new OmdbException(exception.getMessage());
    }

    @Override
    public OmdbException mapServerError(HttpURLConnection connection) throws IOException {
        return new OmdbException( connection.getResponseMessage(), connection.getResponseCode());
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
