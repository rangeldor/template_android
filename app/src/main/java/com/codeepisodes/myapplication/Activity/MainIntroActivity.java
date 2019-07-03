package com.codeepisodes.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codeepisodes.myapplication.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate ( savedInstanceState );

        setButtonBackVisible ( false );
        setButtonNextVisible ( false );

        addSlide ( new FragmentSlide.Builder ( )
                .background ( R.color.colorPrimary )
                .backgroundDark ( R.color.colorPrimaryDark )
                .fragment ( R.layout.intro_1 )
                .build ( ) );

        addSlide ( new FragmentSlide.Builder ( )
                .background ( R.color.colorPrimary )
                .backgroundDark ( R.color.colorPrimaryDark )
                .fragment ( R.layout.intro_2 )
                .build ( ) );

        addSlide ( new FragmentSlide.Builder ( )
                .background ( R.color.colorPrimary )
                .backgroundDark ( R.color.colorPrimaryDark )
                .fragment ( R.layout.intro_3 )
                .build ( ) );

        addSlide ( new FragmentSlide.Builder ( )
                .background ( R.color.colorPrimary )
                .backgroundDark ( R.color.colorPrimaryDark )
                .fragment ( R.layout.intro_4 )
                .build ( ) );

        addSlide ( new FragmentSlide.Builder ( )
                .background ( R.color.colorPrimary )
                .backgroundDark ( R.color.colorPrimaryDark )
                .fragment ( R.layout.intro_5 )
                .canGoForward ( false )
                .build ( ) );
    }

    public void goToLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
