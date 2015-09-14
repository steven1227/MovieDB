package com.example.rendongliu.brightomdb.http;

import android.content.Context;

import com.example.rendongliu.brightomdb.R;
import com.icemobile.framework.icehttpconnection.HttpConnectionService;
import com.icemobile.framework.icehttpconnection.NETWORK_PROTOCOL;

import java.io.IOException;

/**
 * Created by rendong.liu on 18/08/15.
 */
public class MovieHttpConnectionService extends HttpConnectionService {
    final private static int STAMPS_READ_TIMEOUT = 1000 * 10;
    final private static int STAMPS_CONNECT_TIMEOUT = 1000 * 2; // to reproduce 5 seconds on device

//    private static final int CACHE_SIZE = 2 * 1024 * 1024; //2Mb
//    public static final String BRIGHT_LOYALTY_ID_HEADER = "bright-loyalty-id";
//    private static final String BRIGHT_PREVIEW = "BRIGHT-PREVIEW";
    private Context context;

    public MovieHttpConnectionService(Context context) {
        this(context, context.getResources().getString(R.string.Movie_base_server));
        this.context = context;
    }

    public MovieHttpConnectionService(Context context, final String webserviceUrl) {
        super(NETWORK_PROTOCOL.HTTP_PUBLIC, webserviceUrl);
//        try {
//            File httpCacheDir = new File(context.getCacheDir(), "http_cache");
//            HttpResponseCache.install(httpCacheDir, CACHE_SIZE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public <T> IceRequestBuilder<T, IOException> createStampsRequestBuilder() {
        IceRequestBuilder<T, IOException> requestBuilder = super.createRequestBuilder()
                .addRequestHeader("Accept", "application/json")
                .addRequestHeader("Content-Type", "application/json")
                .setReadTimeout(STAMPS_READ_TIMEOUT)
                .setConnectionTimeout(STAMPS_CONNECT_TIMEOUT)
                .setErrorAdapter(new OmdbErrorAdapter(context));
        return requestBuilder;
    }
}
