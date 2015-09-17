package com.example.rendongliu.brightomdb.imageManager;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.rendongliu.brightomdb.http.ImageHttpConnectionService;
import com.example.rendongliu.brightomdb.http.MovieHttpConnectionService;
import com.icemobile.framework.image.data.ImageInfo;
import com.icemobile.framework.image.data.handler.BitmapInputSerializer;
import com.icemobile.framework.image.data.manager.ImageDownloadService;
import com.icemobile.framework.image.exception.GetImageException;

/**
 * Created by rendong.liu on 24/08/15.
 */


public class OmdbDownloadService implements ImageDownloadService {


    private static final String GET_IMAGE = "images";
    public static final String QUERY_PARAMETER_PATH = "path";
    private Context context;
    private ImageHttpConnectionService httpConnectionService;
    private BitmapInputSerializer bitmapInputSerializer;

    public OmdbDownloadService(Context context){
        this.context=context;
        bitmapInputSerializer = new BitmapInputSerializer();
    }

    @Override
    public Bitmap fetchImage(ImageInfo imageInfo) throws GetImageException {
        if(imageInfo.getImagePath().contains("http://")) {
            this.httpConnectionService = new ImageHttpConnectionService(context, imageInfo.getImagePath().replace("http://",""));
        }else {
            this.httpConnectionService = new ImageHttpConnectionService(context, imageInfo.getImagePath());
        }
        MovieHttpConnectionService.IceRequestBuilder<Bitmap, GetImageException> requestBuilder = httpConnectionService.createRequestBuilder();
        requestBuilder.setInputSerializer(bitmapInputSerializer)
                .setErrorAdapter(new GetImageErrorAdapter());
        requestBuilder.appendEncodedPath(GET_IMAGE);
        requestBuilder.appendQueryParameter(QUERY_PARAMETER_PATH, imageInfo.getImagePath());

        return httpConnectionService.doRequest(requestBuilder);
    }
}
