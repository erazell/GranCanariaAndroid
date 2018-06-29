package com.example.janusz.finalapp_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by janusz on 17.06.2018.
 */

public class Splash extends AppCompatActivity {
    ImageView imageSplash;
    TextView textSPlash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageSplash = findViewById(R.id.imageSplash);
        textSPlash = findViewById(R.id.textSplash);
        Animation myAnimation = AnimationUtils.loadAnimation(this,R.anim.myanimation);
        imageSplash.startAnimation(myAnimation);
        textSPlash.startAnimation(myAnimation);
        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
