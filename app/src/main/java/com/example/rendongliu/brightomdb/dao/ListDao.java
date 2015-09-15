package com.example.rendongliu.brightomdb.dao;

import com.example.rendongliu.brightomdb.domain.ListData;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;

import java.io.IOException;

/**
 * Created by rendong.liu on 09/09/15.
 */
public interface ListDao {
    void getData(final TaskResultHandler<ListData, IOException> resultHandler);
}
