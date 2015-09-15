package com.example.rendongliu.brightomdb;

import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.example.rendongliu.brightomdb.fragments.WelcomeFragment;
import com.icemobile.framework.image.data.manager.ImageManager;

public class MainActivity extends AppCompatActivity {

    protected ProgressBar progressBar;

    @Override
    protected void onResume() {
        super.onResume();
       progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        OmdbClassFactory brightStampsClassFactory = new OmdbClassFactory(getApplicationContext());
        ImageManager.setImageManager(brightStampsClassFactory.getAsyncImageManager());
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.yourId);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 1, 200);
        animation.setDuration(2000); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        progressBar.setVisibility(View.INVISIBLE);
        animation.start();

        Toolbar toolbar = (Toolbar)findViewById(R.id.my_action_bar_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(" Movie Movie Go ");
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.movies_icon);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new WelcomeFragment());
        ft.commit();

    }

}
