package com.example.rendongliu.brightomdb.imageManager;

import com.icemobile.framework.icehttpconnection.HttpErrorAdapter;
import com.icemobile.framework.image.exception.GetImageException;
import com.icemobile.framework.logger.Log;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by rendong.liu on 24/08/15.
 */
public class GetImageErrorAdapter implements HttpErrorAdapter<GetImageException> {
    @Override
    public GetImageException mapClientError(IOException exception) {
        Log.e("Can I see error: ", exception.getMessage().toString());
        return new GetImageException(exception);
    }

    @Override
    public GetImageException mapServerError(HttpURLConnection connection) throws IOException {
        return new GetImageException(new IOException("get image error"));
    }
}
