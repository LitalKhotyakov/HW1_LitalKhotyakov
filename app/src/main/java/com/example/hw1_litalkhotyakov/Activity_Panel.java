package com.example.hw1_litalkhotyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_Panel extends AppCompatActivity {

    private ImageView panel_IMG_plane;
    private ImageView[] panel_IMG_hearts;
    private ImageView[] panel_IMG_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        findViews();
        
    }

    private void findViews() {
        panel_IMG_plane = findViewById(R.id.panel_IMG_plane);
        panel_IMG_button = new ImageView[]{
                findViewById(R.id.panel_IMG_left_direction),
                findViewById(R.id.panel_IMG_right_direction)
        };
        panel_IMG_hearts = new ImageView[] {
                findViewById(R.id.panel_IMG_heart1),
                findViewById(R.id.panel_IMG_heart2),
                findViewById(R.id.panel_IMG_heart3)
        };
    }
}