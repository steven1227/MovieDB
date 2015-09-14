package com.example.rendongliu.brightomdb.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.rendongliu.brightomdb.R;
import com.example.rendongliu.brightomdb.adapter.MovieListAdapter;
import com.example.rendongliu.brightomdb.dao.MovieData;
import com.example.rendongliu.brightomdb.dao.impl.MovieDaoimpl;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;
import com.icemobile.framework.image.data.AsyncImageView;
import com.icemobile.framework.loaders.BaseLoader;
import com.icemobile.framework.loaders.BaseLoaderCallbacks;


import java.io.IOException;

/**
 * Created by rendong.liu on 08/09/15.
 */
public class DetailFragment extends Fragment {
    private static int LOADER_ID=0;
    protected AsyncImageView movie_poster;
    protected BootstrapEditText movie_param;
    protected ListView listView;
    protected Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        movie_poster=(AsyncImageView)view.findViewById(R.id.image_poster);
        movie_param=(BootstrapEditText)view.findViewById(R.id.txtOne);
        listView=(ListView)view.findViewById(R.id.detail_list);

        movie_param.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        movie_param.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (movie_param.getRight() - movie_param.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Log.e("test search button", v.getId() + "");
                        loadCurrentProgram();
                        return true;
                    }
                }
                return false;
            }
        });


        return view;
    }

    private void loadCurrentProgram() {
        getActivity().getLoaderManager().restartLoader(LOADER_ID, null, new BaseLoaderCallbacks<MovieData, IOException>() {
            @Override
            public BaseLoader<MovieData, IOException> onCreateBaseLoader() {
                return new BaseLoader<MovieData, IOException>(getActivity().getApplicationContext()) {
                    @Override
                    protected void onStartLoading(TaskResultHandler<MovieData, IOException> loaderCallback) {
                        (new MovieDaoimpl(context, movie_param.getText().toString())).getData(loaderCallback);
                    }
                };
            }

            @Override
            public void onLoaderTaskSuccess(MovieData o) {
//                movie_text.setText(o.getTitle());
                if (!o.getResponse().equals("False")) {
                    Log.e("IMDB_debug", o.getPoster());
                    Toast.makeText(context, "The query success: " + o.getTitle(), Toast.LENGTH_SHORT).show();
                    listView.setAdapter(new MovieListAdapter(o, context));
                    movie_poster.setImageUrl(o.getPoster());
                } else {
                    Toast.makeText(context, o.getError(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onLoaderTaskFailed(IOException e) {
                Toast.makeText(context, "The query failed due to " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getActivity().getApplicationContext();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detail");
    }
}
