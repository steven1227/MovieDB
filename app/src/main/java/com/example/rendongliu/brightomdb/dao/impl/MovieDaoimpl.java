package com.example.rendongliu.brightomdb.dao.impl;

import android.content.Context;
import android.util.Log;

import com.example.rendongliu.brightomdb.dao.MovieDao;
import com.example.rendongliu.brightomdb.dao.MovieData;
import com.example.rendongliu.brightomdb.http.MovieHttpConnectionService;
import com.icemobile.framework.concurrent.manager.ThreadingManager;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;
import com.icemobile.framework.concurrent.task.Task;
import com.icemobile.framework.icehttpconnection.HTTPMethod;
import com.icemobile.framework.icehttpconnection.HttpConnectionService;
import com.icemobile.framework.icehttpconnection.impl.serializer.GsonInputSerializer;
import com.icemobile.framework.parsing.gson.BaseGSonParser;

import java.io.IOException;

/**
 * Created by rendong.liu on 17/08/15.
 */
public class MovieDaoimpl extends ThreadingManager implements MovieDao {


    private MovieHttpConnectionService httpConnectionService;
    private String param;

    public MovieDaoimpl(Context context, String param) {
        this.httpConnectionService = new MovieHttpConnectionService(context);
        this.context = context;
        this.param = param;
    }

    private Context context;

    @Override
    public void getData(TaskResultHandler<MovieData, IOException> resultHandler) {


        final Task<MovieData, IOException> task = new Task<MovieData, IOException>() {
            @Override
            public MovieData execute() throws IOException {
                MovieData inData;
                try {

                    inData = httpConnectionService.doRequest(getRequestBuilder());
                    if (inData != null) {
//                        putDataToCache(inData);
                    }
                    return inData;


                } catch (IOException ioException) {

                    throw ioException;
                }

            }
        };
        this.executeTask(task, resultHandler);
    }

    public HttpConnectionService.IceRequestBuilder<MovieData, IOException> getRequestBuilder() {

        final GsonInputSerializer<MovieData> gSonSerializer = new GsonInputSerializer<>(new BaseGSonParser<MovieData>(MovieData.class));
        HttpConnectionService.IceRequestBuilder<MovieData, IOException> stampsRequestBuilder = httpConnectionService.createStampsRequestBuilder();
        stampsRequestBuilder.setMethod(HTTPMethod.GET)
                .appendQueryParameter("i",param)
                .appendEncodedPath("")
                .setInputSerializer(gSonSerializer);

        Log.e("Debug_IMDB_??",stampsRequestBuilder.toString());
        return stampsRequestBuilder;
    }
}


