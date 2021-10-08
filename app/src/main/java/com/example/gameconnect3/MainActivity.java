package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //0:yellow, 1:red, 2:empty
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        Log.i("tag", counter.getTag().toString());

        counter.setTranslationY(-1500);
        counter.setRotation(-7200);

        if (activePlayer == 0) {

            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;

        } else {

            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1500).rotation(360).setDuration(1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}