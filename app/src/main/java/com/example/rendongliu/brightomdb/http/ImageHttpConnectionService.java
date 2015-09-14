package com.example.rendongliu.brightomdb.http;

import android.content.Context;


import com.icemobile.framework.icehttpconnection.HttpConnectionService;
import com.icemobile.framework.icehttpconnection.NETWORK_PROTOCOL;


import java.io.IOException;

/**
 * Created by rendong.liu on 24/08/15.
 */

public class ImageHttpConnectionService extends HttpConnectionService {
    final private static int STAMPS_READ_TIMEOUT = 1000 * 10;
    final private static int STAMPS_CONNECT_TIMEOUT = 1000 * 2; // to reproduce 5 seconds on device

    //    private static final int CACHE_SIZE = 2 * 1024 * 1024; //2Mb
//    public static final String BRIGHT_LOYALTY_ID_HEADER = "bright-loyalty-id";
//    private static final String BRIGHT_PREVIEW = "BRIGHT-PREVIEW";
    private Context context;

//    public ImageHttpConnectionService(Context context, String ImageUrl) {
//        this(context, ImageUrl);
//        this.context = context;
//    }

    public ImageHttpConnectionService(Context context, final String webserviceUrl) {

        super(NETWORK_PROTOCOL.HTTP_PUBLIC, webserviceUrl);
        this.context = context;
        android.util.Log.e("finally", webserviceUrl);
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