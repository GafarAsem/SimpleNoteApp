package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);

        imageView=findViewById(R.id.splash_imagView);
        textView=findViewById(R.id.splash_textView);

        Animation animationImage= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animat_splash_image);
        animationImage.setStartOffset(1500);

        Animation animationText= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animate_splash_text);
        animationText.setStartOffset(1500);


        imageView.setAnimation(animationImage);
        textView.setAnimation(animationText);

        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        },3500);





    }
}