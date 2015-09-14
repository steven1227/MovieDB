package com.example.rendongliu.brightomdb;

import android.content.Context;
import android.os.Handler;

import com.example.rendongliu.brightomdb.http.ImageHttpConnectionService;
import com.example.rendongliu.brightomdb.imageManager.OmdbDownloadService;
import com.icemobile.framework.image.data.cache.BitmapMemoryCacheManager;
import com.icemobile.framework.image.data.manager.ImageDownloadService;
import com.icemobile.framework.image.data.manager.impl.AsyncImageManager;
import com.icemobile.framework.image.data.manager.impl.CachingImageManager;

/**
 * Created by rendong.liu on 24/08/15.
 */
public class OmdbClassFactory {
    private Context context;
    private ImageHttpConnectionService connectionService;
    private ImageDownloadService imageDownloadService;
    private AsyncImageManager asyncImageManager;
    private CachingImageManager cachingImageManager;
    private BitmapMemoryCacheManager bitmapMemoryCacheManager;


    public OmdbClassFactory(Context context) {
        this.context = context;
    }


    private ImageDownloadService getImageDownloadService() {
        if (imageDownloadService == null) {
            imageDownloadService = new OmdbDownloadService(context);
        }
        return imageDownloadService;
    }



    public AsyncImageManager getAsyncImageManager() {
        if (asyncImageManager == null){
            asyncImageManager = new AsyncImageManager(getImageDownloadService(), new Handler(), context);
        }
        return asyncImageManager;
    }


//
//    public CachingImageManager getCachingImageManger() {
//        if (cachingImageManager == null) {
//            cachingImageManager = new CachingImageManager(getAsyncImageManager(), null);
//
//        }
//
//        return cachingImageManager;
//    }
}
