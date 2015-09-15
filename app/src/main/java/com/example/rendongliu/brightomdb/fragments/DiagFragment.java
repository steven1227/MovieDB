package com.example.rendongliu.brightomdb.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rendongliu.brightomdb.R;
import com.example.rendongliu.brightomdb.domain.MovieData;
import com.icemobile.framework.image.data.AsyncImageView;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by rendong.liu on 11/09/15.
 */
public class DiagFragment extends SupportBlurDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        MovieData movieData =(MovieData)getArguments().getParcelable("movie");

        AsyncImageView asyncImageView = (AsyncImageView)view.findViewById(R.id.movie_photo);
        asyncImageView.setImageUrl(movieData.getPoster());
        TextView movie_title = (TextView)view.findViewById(R.id.movie_name);
        TextView movie_year = (TextView)view.findViewById(R.id.movie_year);
        TextView movie_content = (TextView)view.findViewById(R.id.content);
        TextView movie_actors = (TextView)view.findViewById(R.id.movie_actor);

        movie_title.setText(movieData.getTitle());
        movie_year.setText(movieData.getYear());
        movie_content.setText(movieData.getPlot());
        movie_actors.setText("Actors: "+movieData.getActors());

        return view;
    }


}














