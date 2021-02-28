package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        Animation animationImage= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animat_splash_image);
//        animationImage.setDuration(0);
//        Animation animationText= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animate_splash_text);
//        animationText.setDuration(0);




    }
}