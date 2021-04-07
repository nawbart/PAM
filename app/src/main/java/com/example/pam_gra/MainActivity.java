package com.example.pam_gra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Elements
    private TextView scoreLabel, startLabel;
    private ImageView box, orange, pink, black;

    private int boxY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        box = findViewById(R.id.box);
        orange = findViewById(R.id.orange);
        pink = findViewById(R.id.pink);
        black = findViewById(R.id.black);


        // Initial Positions
        orange.setX(-80.0f);
        orange.setY(-80.0f);
        pink.setX(-80.0f);
        pink.setY(-80.0f);
        black.setX(-80.0f);
        black.setY(-80.0f);

        startLabel.setVisibility(View.INVISIBLE);
        boxY = 500;

    }

    public boolean onTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            boxY -= 20;
        }
        box.setY(boxY);
        return true;
    }
}