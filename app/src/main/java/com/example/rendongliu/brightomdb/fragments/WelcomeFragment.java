package com.example.rendongliu.brightomdb.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.rendongliu.brightomdb.R;

/**
 * Created by rendong.liu on 08/09/15.
 */
public class WelcomeFragment extends Fragment {

    private TextView textView;
    protected BootstrapEditText movie_param;

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
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Luna.ttf");
        textView.setTypeface(typeface);

        movie_param=(BootstrapEditText)view.findViewById(R.id.txtOne);
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

//
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        AbstractFragment fragmentDemo = AbstractFragment.newInstance(movie_param.getText().toString());
                        ft.setCustomAnimations(R.animator.enter_anim, 0, 0, R.animator.exit_anim);
                        ft.replace(R.id.your_placeholder, fragmentDemo);
                        ft.addToBackStack("tag1");
                        ft.commit();
                        return true;
                    }
                }
                return false;
            }
        });

//        String text = " <font color='white'>Movie </font><font color='white'>Movie</font><font color='white'> Go</font>";
        textView.setText("Movie Movie Go", TextView.BufferType.SPANNABLE);
        textView.setTextColor(Color.WHITE);
        return view;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
