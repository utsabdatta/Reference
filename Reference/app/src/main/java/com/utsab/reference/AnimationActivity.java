package com.utsab.reference;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimationActivity extends AppCompatActivity {

    Button myanimation;
    View fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        myanimation = (Button) findViewById(R.id.animating_button);
        fragment = findViewById(R.id.animation_fragment);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        myanimation.startAnimation(animation);
        fragment.startAnimation(animation);
    }
}
