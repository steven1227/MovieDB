package com.example.rendongliu.brightomdb.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
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
        loadCurrentProgram();
        return view;
    }

    private void loadCurrentProgram() {
        getActivity().getLoaderManager().restartLoader(LOADER_ID, null, new BaseLoaderCallbacks<ListData, OmdbException>() {
            @Override
            public BaseLoader<ListData, OmdbException> onCreateBaseLoader() {
                return new BaseLoader<ListData, OmdbException>(getActivity().getApplicationContext()) {
                    @Override
                    protected void onStartLoading(TaskResultHandler<ListData, OmdbException> loaderCallback) {
                        (new ListDaoimpl(context, movietitle)).getData(loaderCallback);
                    }
                };
            }

            @Override
            public void onLoaderTaskSuccess(ListData o) {
                progressBar.setVisibility(View.INVISIBLE);
                if (o.getResponse() == null) {

                    Toast.makeText(context, "The query success:", Toast.LENGTH_SHORT).show();
                    rv.setAdapter(new RVadapter(o.getSearch(),getActivity().getApplicationContext(),getActivity().getLoaderManager(), getActivity().getFragmentManager()));
                } else if (o.getResponse()!=null && o.getResponse().equals("False")){
                    Toast.makeText(context, o.getError(), Toast.LENGTH_SHORT).show();  //add another fragment to show the error image
                }
            }


            @Override
            public void onLoaderTaskFailed(OmdbException e) {

                Toast.makeText(context, "The query1 failed due to " +e.getErrorType() + " " +e.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }


}
