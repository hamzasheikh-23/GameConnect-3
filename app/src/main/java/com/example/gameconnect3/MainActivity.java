package com.example.gameconnect3;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:yellow, 1:red, 2:empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    int[][] winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void refereshActivity(){
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        this.finish();
    }

    public void reloadGame(View view){

        ImageView reloadImage = (ImageView) findViewById(R.id.restartImageView);

        //create and init an ObjectAnimator
        ObjectAnimator animation;
        animation = ObjectAnimator.ofFloat(reloadImage, "rotation", 0.0f, 720f);

        animation.setDuration(400);

        animation.setRepeatCount(0);

        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        //Add a listener to check when the animation ends
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                refereshActivity();
            }
        });

        animation.start();

    }

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

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

            for (int[] winningState : winningStates) {

                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {

                    String winner,colorString = "#000000";


                    if (activePlayer == 1) {

                        winner = "Yellow";
                        colorString = "#ffd11a";

                    } else {
                        winner = "Red";
                        colorString = "#e60000";
                    }

                    gameActive = false;

                    ImageView reloadImage = (ImageView) findViewById(R.id.restartImageView);
                    reloadImage.animate().translationY(-150).setDuration(500);

                    TextView winnerText = (TextView) findViewById(R.id.winnerTextview);
                    winnerText.setTextColor(Color.parseColor(colorString));
                    winnerText.setText(winner +" has won!");

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}