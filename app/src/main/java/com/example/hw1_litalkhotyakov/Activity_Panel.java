package com.example.hw1_litalkhotyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class Activity_Panel extends AppCompatActivity {

    private ImageView panel_IMG_plane;
    private ImageView panel_IMG_background;
    private ImageView[] panel_IMG_hearts;
    private ImageView[] panel_IMG_button;
    private ImageView[][] path;
    private int[][] values;
//    private LinearLayout[] arrA;
//    private LinearLayout[] arrB;
//    private LinearLayout[] arrC;
//    android:background="@drawable/img_background"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        findViews();

//        Glide
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

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = 0;
            }
        }

        checkCrashing();
        randomly();
        updateUI();
        vibrate();

    }


    private void findViews() {
        panel_IMG_plane = findViewById(R.id.panel_IMG_plane);
        panel_IMG_button = new ImageView[]{
                findViewById(R.id.panel_IMG_left_direction),
                findViewById(R.id.panel_IMG_right_direction)
        };
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


//        arrA = new LinearLayout[]{
//                findViewById(R.id.panel_LINL_lineA),
//                findViewById(R.id.panel_LINL_A0),
//                findViewById(R.id.panel_LINL_A1),
//                findViewById(R.id.panel_LINL_A2),
//                findViewById(R.id.panel_LINL_A3),
//                findViewById(R.id.panel_LINL_A4)
//        };
//        arrB = new LinearLayout[]{
//                findViewById(R.id.panel_LINL_lineB),
//                findViewById(R.id.panel_LINL_B0),
//                findViewById(R.id.panel_LINL_B1),
//                findViewById(R.id.panel_LINL_B2),
//                findViewById(R.id.panel_LINL_B3),
//                findViewById(R.id.panel_LINL_B4)
//        };
//        arrC = new LinearLayout[]{
//                findViewById(R.id.panel_LINL_lineC),
//                findViewById(R.id.panel_LINL_C0),
//                findViewById(R.id.panel_LINL_C1),
//                findViewById(R.id.panel_LINL_C2),
//                findViewById(R.id.panel_LINL_C3),
//                findViewById(R.id.panel_LINL_C4)
//        };
    }

    private void randomly() {
        final int random = new Random().nextInt(3);


    }

    private void checkCrashing() {

    }


    private void updateUI() {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                ImageView im = path[i][j];
                if (values[i][j] == 0) {
                    im.setVisibility(View.INVISIBLE);
                } else if (values[i][j] == 1) {
                    im.setVisibility(View.VISIBLE);
                    im.setImageResource(R.drawable.img_bomb);
                } else if (values[i][j] == 2) {
                    im.setVisibility(View.VISIBLE);
                    im.setImageResource(R.drawable.img_bomb);
                }
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