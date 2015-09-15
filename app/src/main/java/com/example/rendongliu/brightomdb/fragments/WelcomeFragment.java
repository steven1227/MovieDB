package com.example.rendongliu.brightomdb.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rendongliu.brightomdb.R;

/**
 * Created by rendong.liu on 08/09/15.
 */
public class WelcomeFragment extends Fragment {

    private TextView textView;
    protected EditText movie_param;
    private static String TAG= "tag1";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, container, false);

        View backgroundimage = view.findViewById(R.id.background);
        Drawable background = backgroundimage.getBackground();
        background.setAlpha(90);

        textView = (TextView)view.findViewById(R.id.textView);
        textView.setText("Movie Movie Go", TextView.BufferType.SPANNABLE);
        textView.setTextColor(Color.WHITE);
        movie_param=(EditText)view.findViewById(R.id.txtOne);


        movie_param.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (movie_param.getRight() - movie_param.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();

                        MovieListFragment fragmentDemo = new MovieListFragment();
                        Bundle args = new Bundle();
                        args.putString("movie", movie_param.getText().toString());
                        fragmentDemo.setArguments(args);

                        fragmentDemo.setEnterTransition(new Slide(Gravity.RIGHT));
                        fragmentDemo.setExitTransition(new Slide(Gravity.LEFT));

                        ft.replace(R.id.your_placeholder, fragmentDemo);
                        ft.addToBackStack(TAG);
                        ft.commit();
                        return true;
                    }
                }
                return false;
            }
        });


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
