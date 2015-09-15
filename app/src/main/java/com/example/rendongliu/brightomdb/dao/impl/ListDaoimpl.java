package com.example.rendongliu.brightomdb.dao.impl;

import android.content.Context;
import android.util.Log;

import com.example.rendongliu.brightomdb.dao.ListDao;
import com.example.rendongliu.brightomdb.domain.ListData;
import com.example.rendongliu.brightomdb.http.MovieHttpConnectionService;
import com.example.rendongliu.brightomdb.http.OmdbException;
import com.icemobile.framework.concurrent.manager.ThreadingManager;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;
import com.icemobile.framework.concurrent.task.Task;
import com.icemobile.framework.icehttpconnection.HTTPMethod;
import com.icemobile.framework.icehttpconnection.HttpConnectionService;
import com.icemobile.framework.icehttpconnection.impl.serializer.GsonInputSerializer;
import com.icemobile.framework.parsing.gson.BaseGSonParser;

import java.io.IOException;

/**
 * Created by rendong.liu on 09/09/15.
 */
public class ListDaoimpl  extends ThreadingManager implements ListDao {

    private MovieHttpConnectionService httpConnectionService;
    private String param;

    public ListDaoimpl (Context context, String param) {
        this.httpConnectionService = new MovieHttpConnectionService(context);
        this.context = context;
        this.param = param;
    }

    private Context context;

    @Override
    public void getData(TaskResultHandler<ListData, OmdbException> resultHandler) {


        final Task<ListData, OmdbException> task = new Task<ListData, OmdbException>() {
            @Override
            public ListData execute() throws OmdbException {
                ListData inData;
                try {

                    inData = httpConnectionService.doRequest(getRequestBuilder());
                    if (inData != null) {
                    }
                    return inData;


                } catch (OmdbException ioException) {

                    throw ioException;
                }

            }
        };
        this.executeTask(task, resultHandler);
    }

    public HttpConnectionService.IceRequestBuilder<ListData, OmdbException> getRequestBuilder() {

        final GsonInputSerializer<ListData> gSonSerializer = new GsonInputSerializer<>(new BaseGSonParser<ListData>(ListData.class));
        HttpConnectionService.IceRequestBuilder<ListData, OmdbException> stampsRequestBuilder = httpConnectionService.createStampsRequestBuilder();
        stampsRequestBuilder.setMethod(HTTPMethod.GET)
                .appendQueryParameter("s", param)
                .appendQueryParameter("type","movie")
                .appendEncodedPath("")
                .setInputSerializer(gSonSerializer);

        Log.e("Debug_IMDB_??", stampsRequestBuilder.toString());
        return stampsRequestBuilder;
    }
}
