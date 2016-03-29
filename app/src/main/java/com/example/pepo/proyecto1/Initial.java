package com.example.pepo.proyecto1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class Initial extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.initial_anim);
        ImageView imageView = (ImageView) findViewById(R.id.inicial);
        imageView.startAnimation(animation);


    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startActivity(new Intent("com.example.pepo.proyecto1.MainActivity"));
        }
        return true;
    }//fin del metodo onTouchEvent
}//fin de la clase
