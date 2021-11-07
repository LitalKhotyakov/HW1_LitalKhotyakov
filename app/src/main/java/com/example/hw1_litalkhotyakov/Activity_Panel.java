package com.example.hw1_litalkhotyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Activity_Panel extends AppCompatActivity {

    private ImageView[] panel_IMG_planes;
    private ImageView panel_IMG_background;
    private ImageView[] panel_IMG_hearts;
    private ImageView panel_IMG_left_direction;
    private ImageView panel_IMG_right_direction;
    private ImageView[][] path;
    private int[][] values;
    private int planeLoc = 1;
    private final int MAX_LIVES = 3;
    private int lives = MAX_LIVES;
    ;

//    android:background="@drawable/img_background"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        findViews();

//        Glide                  \\\\הוא אמר לעשות ככה אבל לא מראה לי תמונה מלאה\\\\
//                .with(this)
//                .load(R.drawable.img_background)
//                .fitCenter()
//                .into(panel_IMG_background);
//
//        <ImageView
//        android:id="@+id/panel_IMG_background"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//                />

        initValMatrix();
        checkCrashing();
        randomly();
        clickDirection();
        updateUI();


    }

    private void MoveBombsAtTouchButton() {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                if (values[i][j] == 1) {
                    values[i][j] = 0;
                    if (j == (values[i].length - 1)) {
                        values[i][j] = 0;
                    } else {
                        j++;
                        values[i][j] = 1;
                    }
                }
            }
        }
    }


    private void clickDirection() {
        panel_IMG_left_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_IMG_planes[planeLoc].setVisibility(View.INVISIBLE);
                planeLoc--;
                if (planeLoc < 0) {
                    planeLoc = 0;
                }
                panel_IMG_planes[planeLoc].setVisibility(View.VISIBLE);


            }
        });

        panel_IMG_right_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_IMG_planes[planeLoc].setVisibility(View.INVISIBLE);
                planeLoc++;
                if (planeLoc > 2) {
                    planeLoc = 2;
                }
                panel_IMG_planes[planeLoc].setVisibility(View.VISIBLE);
                MoveBombsAtTouchButton();
                updateUI();
                randomly();
                updateLivesViews();
                checkCrashing();

            }
        });


    }

    private void initValMatrix() {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = 0;
            }
        }
    }

    private void findViews() {
        panel_IMG_planes = new ImageView[]{
                findViewById(R.id.panel_IMG_plane0),
                findViewById(R.id.panel_IMG_plane1),
                findViewById(R.id.panel_IMG_plane2)
        };
        panel_IMG_left_direction = findViewById(R.id.panel_IMG_left_direction);
        panel_IMG_right_direction = findViewById(R.id.panel_IMG_right_direction);
        panel_IMG_hearts = new ImageView[]{
                findViewById(R.id.panel_IMG_heart1),
                findViewById(R.id.panel_IMG_heart2),
                findViewById(R.id.panel_IMG_heart3)
        };
        path = new ImageView[][]{
                {findViewById(R.id.panel_IMG_bombA0), findViewById(R.id.panel_IMG_bombA1), findViewById(R.id.panel_IMG_bombA2), findViewById(R.id.panel_IMG_bombA3), findViewById(R.id.panel_IMG_bombA4)},
                {findViewById(R.id.panel_IMG_bombB0), findViewById(R.id.panel_IMG_bombB1), findViewById(R.id.panel_IMG_bombB2), findViewById(R.id.panel_IMG_bombB3), findViewById(R.id.panel_IMG_bombB4)},
                {findViewById(R.id.panel_IMG_bombC0), findViewById(R.id.panel_IMG_bombC1), findViewById(R.id.panel_IMG_bombC2), findViewById(R.id.panel_IMG_bombC3), findViewById(R.id.panel_IMG_bombC4)}
        };
        values = new int[path.length][path[0].length];
//        panel_IMG_background = findViewById(R.id.panel_IMG_background);

    }

    private void randomly() {
        final int random = new Random().nextInt(3);
        values[random][0] = 1;


    }

    private void checkCrashing() {
        if (values[planeLoc][4] == 1) {
            Toast.makeText(this, "You crashed", Toast.LENGTH_SHORT).show();
            vibrate();
            lives--;
            updateLivesViews();
        }

        if (lives == 0) {
            finish();
        }
    }


    private void updateUI() {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                ImageView im = path[i][j];
                if (values[i][j] == 0) {
                    im.setVisibility(View.INVISIBLE);
                } else if (values[i][j] == 1) {
                    im.setVisibility(View.VISIBLE);
                } else if (values[i][j] == 2) {
                    im.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateLivesViews() {
        for (int i = panel_IMG_hearts.length - 1; i >= 0; i--) {
            if ((i + 1) > lives) {
                panel_IMG_hearts[i].setVisibility(View.INVISIBLE);
            } else {
                panel_IMG_hearts[i].setVisibility(View.VISIBLE);
            }
        }
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}