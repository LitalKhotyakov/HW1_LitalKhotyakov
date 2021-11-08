package com.example.hw1_litalkhotyakov;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hw1_litalkhotyakov.databinding.ActivityEndGameBinding;

public class EndGameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        ImageView endGame_IMG_back = findViewById(R.id.endGame_IMG_back);

        Glide
                .with(this)
                .load(R.drawable.img_gameover)
                .fitCenter()
                .into(endGame_IMG_back);


    }

}