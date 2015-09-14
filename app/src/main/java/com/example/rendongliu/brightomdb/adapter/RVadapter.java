package com.example.rendongliu.brightomdb.adapter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rendongliu.brightomdb.R;
import com.example.rendongliu.brightomdb.dao.ListData;
import com.example.rendongliu.brightomdb.dao.MovieData;
import com.example.rendongliu.brightomdb.dao.impl.MovieDaoimpl;
import com.example.rendongliu.brightomdb.fragments.AbstractFragment;
import com.example.rendongliu.brightomdb.fragments.DiagFragment;
import com.icemobile.framework.concurrent.resulthandler.TaskResultHandler;
import com.icemobile.framework.image.data.AsyncImageView;
import com.icemobile.framework.loaders.BaseLoader;
import com.icemobile.framework.loaders.BaseLoaderCallbacks;

import java.io.IOException;
import java.util.List;

/**
 * Created by rendong.liu on 09/09/15.
 */
public class RVadapter extends RecyclerView.Adapter <RVadapter.PersonViewHolder> {

    List<ListData.movie> list;
    protected  Context context;
    protected Activity activity;
    protected LoaderManager loaderManager;
    protected AbstractFragment abstractFragment;


    public RVadapter(List<ListData.movie> list, Context context, LoaderManager loaderManager, Activity activity, AbstractFragment abstractFragment) {
        this.list = list;
        this.context = context;
        this.loaderManager = loaderManager;
        this.activity = activity;
        this.abstractFragment = abstractFragment;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(v);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {
        holder.movieName.setText(list.get(position).getTitle());
        holder.movieYear.setText("Year " + list.get(position).getYear());
        holder.moviePhoto.setImageUrl(null);
        holder.moviePhoto.applyPlaceholder();

        final DiagFragment fragment = new DiagFragment();
        loadCurrentProgram(position,holder.moviePhoto, holder.movieActors, fragment);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View sharedView = holder.cv;
                String transitionName = "fake_name";

                abstractFragment.setExitTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.explode));
                abstractFragment.setExitTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.explode));

                fragment.setSharedElementEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform));
                fragment.setEnterTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.explode));

                FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().addSharedElement(sharedView,transitionName);
                fragment.show(ft, "tag3");

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView movieName;
        TextView movieYear;
        TextView movieActors;
        AsyncImageView moviePhoto;


        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            movieName = (TextView)itemView.findViewById(R.id.movie_name);
            movieYear = (TextView)itemView.findViewById(R.id.movie_year);
            movieActors = (TextView)itemView.findViewById(R.id.movie_actor);
            moviePhoto = (AsyncImageView)itemView.findViewById(R.id.movie_photo);
        }
    }

    private void loadCurrentProgram(final int position, final AsyncImageView moviephoto, final TextView actor ,  final DiagFragment i) {
        loaderManager.restartLoader(position+1, null, new BaseLoaderCallbacks<MovieData, IOException>() {
            @Override
            public BaseLoader<MovieData, IOException> onCreateBaseLoader() {
                return new BaseLoader<MovieData, IOException>(context) {
                    @Override
                    protected void onStartLoading(TaskResultHandler<MovieData, IOException> loaderCallback) {
                        (new MovieDaoimpl(context, list.get(position).getImdbID())).getData(loaderCallback);
                    }
                };
            }

            @Override
            public void onLoaderTaskSuccess(MovieData o) {

                Bundle args = new Bundle();
                args.putSerializable("movie",o);
                i.setArguments(args);

                if (!o.getResponse().equals("False")) {
                    actor.setText("Actors:" + o.getActors());
                    moviephoto.setImageUrl(o.getPoster());

                } else {
                }
            }

            @Override
            public void onLoaderTaskFailed(IOException e) {
                Toast.makeText(context, "The query2 failed due to " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}