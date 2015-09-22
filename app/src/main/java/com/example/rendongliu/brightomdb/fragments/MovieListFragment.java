package com.example.rendongliu.brightomdb.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rendongliu.brightomdb.R;
import com.example.rendongliu.brightomdb.adapter.RVadapter;
import com.example.rendongliu.brightomdb.domain.ListData;
import com.example.rendongliu.brightomdb.dao.impl.ListDaoimpl;
import com.example.rendongliu.brightomdb.http.OmdbException;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;
import com.icemobile.framework.loaders.BaseLoader;
import com.icemobile.framework.loaders.BaseLoaderCallbacks;

import java.io.IOException;

/**
 * Created by rendong.liu on 08/09/15.
 */
public class MovieListFragment extends Fragment {
    private static int LOADER_ID=0;
    protected String movietitle;
    protected Context context;
    protected RecyclerView rv;
    protected ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movietitle = getArguments().getString("movie");
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("json");
            if(message!=null){

            }

        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        progressBar= (ProgressBar)activity.findViewById(R.id.yourId);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generalinfo , container, false);
        context=getActivity().getApplicationContext();
        rv = (RecyclerView)view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
    }
}
