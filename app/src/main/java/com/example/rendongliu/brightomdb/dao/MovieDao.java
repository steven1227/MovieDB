package com.example.rendongliu.brightomdb.dao;

import com.example.rendongliu.brightomdb.domain.MovieData;
import com.example.rendongliu.brightomdb.http.OmdbException;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;

import java.io.IOException;

/**
 * Created by rendong.liu on 17/08/15.
 */
public interface MovieDao {
    void getData(final TaskResultHandler<MovieData, OmdbException> resultHandler);
}
